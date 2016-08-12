/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.Lire_prosody;

import java.io.IOException;
import net.beadsproject.beads.core.AudioContext;
import net.beadsproject.beads.core.Bead;
import net.beadsproject.beads.data.Sample;
import net.beadsproject.beads.data.SampleManager;
import net.beadsproject.beads.data.audiofile.AudioFileType;
import net.beadsproject.beads.ugens.Envelope;
import net.beadsproject.beads.ugens.Gain;
import net.beadsproject.beads.ugens.GranularSamplePlayer;
import net.beadsproject.beads.ugens.RecordToSample;

/**
 *
 * @author Dell
 */
public class Pitch {
    private float startPitch = 0;
    private float endPitch = 0;
    private String basePath = "";
    
    
    public Pitch(float start, float end, String path){
        
        System.out.println("BEADS! - initializing...");
        
        this.startPitch = start;
        this.endPitch = end;
        this.basePath = path;
    }
    
    
    
    /*
    * @desc 
    * @param String inFile - input file name
    * @return String
    */
    public String changePitch(String inFile, String outFile){
        
        System.out.println("changing pitch");
        
        final AudioContext ac = new AudioContext();
        final Sample outputSample = new Sample(5000D);
        final RecordToSample recordToSample = new RecordToSample(ac, outputSample, RecordToSample.Mode.INFINITE);
        //String audioFile = "C:\\Users\\Dell\\Documents\\NetBeansProjects\\beadzzz_may_22\\src\\audio\\aee.wav";
        String audioFile = basePath+inFile;
        
        GranularSamplePlayer player = new GranularSamplePlayer(ac, SampleManager.sample(audioFile));
        
        Envelope pitchEnvelope = new Envelope(ac, startPitch); //starting pitch- notmal

        System.out.println("Writing to a file trying...");
        //trigger to write and end the file
        Bead myTrigger = new Bead() {
            @Override
            public void messageReceived(Bead message) {
                System.out.println("Processing complete... Writing to file");

                recordToSample.clip();
                Sample sample = recordToSample.getSample();
                
                try {
                    sample.write(outFile, AudioFileType.WAV);
                } catch (IOException e) {
                    System.out.println("Couldn't save file!");
                    e.printStackTrace();
                }
                ac.stop();
            }
        };

        //ending pitch high. and when pitch change over trigger "myTrigger" see above
        pitchEnvelope.addSegment(endPitch, (float) SampleManager.sample(audioFile).getLength(), myTrigger);
        player.setPitch(pitchEnvelope);

        Gain g = new Gain(ac, 2, 1f);
        g.addInput(player);
        ac.out.addInput(g);

        recordToSample.addInput(ac.out);
        ac.out.addDependent(recordToSample);
        
        //following ac.start() it will record while playing. slow, because it takes playback time to record
        //ac.start();
        
        //following ac.runNonRealTime() it will record without playing. fast, it takes fraction of a second
        ac.runNonRealTime();
        System.out.println("---PITCH RETURNED--- outputted to "+outFile);
         return outFile;
        
    }
}
