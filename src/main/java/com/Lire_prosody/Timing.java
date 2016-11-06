/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.Lire_prosody;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Dell
 */
public class Timing {

    private String ffmpegPath = "C:\\lire_libraries\\ffmpeg-20160517-git-af3e944-win64-static\\bin\\ffmpeg";
    private String Filep = "";
    private String infileName = "";
    private float timing = 0.0f;
    private String prefix;

    public Timing(String ffmpeg, String Filepath, String infileName, float time, String prfx) throws Exception {
        //this.ffmpegPath = ffmpeg;
        this.Filep = Filepath;
        this.infileName = infileName;
        this.timing = time;
        this.prefix = prfx;
        //this.timingstatus = timeStatus;
    }

    public String ChangeTiming() {
        //range 0.5-2.0 , normal = 1.0
        String outfilename = prefix + infileName;
        try {
            Runtime.getRuntime().exec(
                    ffmpegPath + " "
                    + "-i " + Filep + infileName + " "
                    + "-filter:a \"atempo=" + timing + "\" -vn "
                    + Filep + outfilename);
        } catch (IOException ex) {
            Logger.getLogger(Timing.class.getName()).log(Level.SEVERE, null, ex);
        }
        return outfilename;
    }

}
