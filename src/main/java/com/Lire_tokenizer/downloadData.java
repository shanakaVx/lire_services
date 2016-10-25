/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.Lire_tokenizer;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Shanaka
 */
public class downloadData {
    private final String[] tones;
    private final String basePath = "C:\\xampp\\htdocs\\LireFrontend\\voiceprofiles\\1\\";
    private final String ffmpegPath = "C:\\lire_libraries\\ffmpeg-20160517-git-af3e944-win64-static\\bin\\ffmpeg";
    private final String outfilePath = "C:\\xampp\\htdocs\\LireFrontend\\voiceprofiles\\download\\";

    public downloadData(String[] tne) {
        this.tones = tne;
    }
    
    public String prepare(){
        String fileName = "";
        PrintWriter writer = null;
        try {
            writer = new PrintWriter("C:\\xampp\\htdocs\\LireFrontend\\voiceprofiles\\download\\concats.txt", "UTF-8");
            for (String tone : tones) {
                writer.println("file '"+basePath+tone+"'");
            }
            writer.close();
            fileName = postProcessing();
        } catch (FileNotFoundException | UnsupportedEncodingException ex) {
            Logger.getLogger(downloadData.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            writer.close();
        }
        
    return fileName;
    }
    
    
    
    private String postProcessing(){
        //ffmpeg -f concat -i mylist.txt -c copy output
            System.out.println("------CONCATERNATING-----");
        String fileName = "";
        Date date = new Date();
        SimpleDateFormat sdf = new SimpleDateFormat("MM-dd-yyyy_h-mm-ss");
        String outfileName = "audio"+sdf.format(date)+".wav";
        
        //concatenate
        ProcessBuilder builder = new ProcessBuilder(ffmpegPath,
                "-f",
                "concat",
                "-safe",
                "0",
                "-i",
                "C:\\xampp\\htdocs\\LireFrontend\\voiceprofiles\\download\\concats.txt",
                "-c",
                "copy",
                outfilePath+outfileName);
        builder.redirectErrorStream(true);
        Process p = null;
        
        try {
            p = builder.start();
            BufferedReader r = new BufferedReader(new InputStreamReader(p.getInputStream()));
            while(true){
                System.out.println(r.readLine());
                if (r.readLine() == null || r.readLine().equals(""))
                    break;
            }
            
            fileName = limiter(outfileName);
        } catch (IOException ex) {
            Logger.getLogger(downloadData.class.getName()).log(Level.SEVERE, null, ex);
        }

        
        return fileName;            
    }
    
    
    
    private String limiter(String in){
        System.out.println("------LIMITER-----");
        String fileName = "";
        Date date = new Date();
        SimpleDateFormat sdf = new SimpleDateFormat("MM-dd-yyyy_h-mm-ss");
        String outfileName = "audio"+sdf.format(date)+".wav";
        
        //limiter
        ProcessBuilder builder = new ProcessBuilder(ffmpegPath,
                "-y",
                "-i",
                outfilePath+in,
                "-af",
                "alimiter=2:1:0.1:5:5:0:0:1",
                outfilePath+outfileName);
        builder.redirectErrorStream(true);
        Process p = null;
        
        try {
            p = builder.start();
            BufferedReader r = new BufferedReader(new InputStreamReader(p.getInputStream()));
            System.out.println("starting");
//            while(true){
//                System.out.println(r.readLine());
//                if (r.readLine() == null || r.readLine().equals(""))
//                    break;
//            }
            System.out.println("done");
        } catch (IOException ex) {
            Logger.getLogger(downloadData.class.getName()).log(Level.SEVERE, null, ex);
        }
        return outfileName;       
    }
    
}
