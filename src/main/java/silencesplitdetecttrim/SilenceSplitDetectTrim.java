/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package silencesplitdetecttrim;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JFileChooser;
import org.json.JSONObject;


/**
 *
 * @author VCALDSH
 */




public class SilenceSplitDetectTrim {

  
    public String uid="user2";  
    // need get the iud
    //variables
    //full path to the ffmpeg.exe
    public String ffmpegPath = "C:\\ffmpeg\\bin\\ffmpeg";

    //path to the folder where the recorded file to identify the silence is located
    public String infilePath = "C:\\xampp\\htdocs\\lireFrontend\\Voice\\php_sinhala\\Direct_upload\\";
    
    //name of the file to identify the silence
    // 1.wav name get in PHP soundupload file $uploadpath
    public String infileName = "1.wav";

    //path to the folder where all the silence cut audio are outputted

     // public static String outfilePath = "C:\\xampp\\htdocs\\lireFrontend\\Voice\\php_sinhala\\Affter_ffmpeg\\UVProfileSinhala\\";
    public String outfilePath = "C:\\xampp\\htdocs\\lireFrontend\\voiceprofiles\\1\\";
       
    //path to the silence trimmed tones. This may be the original path at the web server
    public String outTrimmedPath = "C:\\xampp\\htdocs\\lireFrontend\\Voice\\php_sinhala\\Affter_ffmpeg\\UVProfileSinahalaAftertrim\\" ;    
    
    //List which stores the silence detect data from ffmpeg
    public List<String> silence;
    private String folder;

    
    
//    public static void main(String[] args) {
//        identifySilence();
//        splitFromSilence();
//        removeSilenceAll();
////        store();
//    }

    public SilenceSplitDetectTrim(String uid, String folder, String fileName){
        this.uid = uid;
        this.folder = folder;
        this.infileName = fileName;
      //  this.infileName += ".wav";
        
        System.out.println("Started!!!!---");
        
//        identifySilence();
//        splitFromSilence();
//        removeSilenceAll();
    }
    
    //detect silence in the input long audio file with several tones recorded with a little silence between seperate tones
    //put the silence data into an array list
    public void identifySilence() {
        silence = new ArrayList<>();
        System.out.println("Infil path -- " + infilePath + folder + "\\" + infileName );
        String cmd1 = ffmpegPath + " -i " + infilePath + folder + "\\" + infileName + " -af silencedetect=noise=-30dB:d=0.1 -f null -";
        System.out.println(cmd1);

        try {
            ProcessBuilder builder = new ProcessBuilder(ffmpegPath,
                    "-i",
                    infilePath + folder + "\\" + infileName,
                    "-af",
                    "silencedetect=noise=-30dB:d=0.1",
                    "-f",
                    "null",
                    "-");
            builder.redirectErrorStream(true);
            Process p = builder.start();
            BufferedReader r = new BufferedReader(new InputStreamReader(p.getInputStream()));
            String line;
            String[] splited;
            while (true) {
                line = r.readLine();
                if (line == null || line.equals("")) {
                    break;
                } else if (line.startsWith("[silencedetect")) {
                    splited = line.split("\\s+");
                    silence.add(splited[4]);
                }
            }
        } catch (Exception e) {
            System.out.println(e);
        }
        System.out.println("Silence Detecting done... detected " + silence.size() + " starts and ends");
    }
    
    
    
    //split the audio file from the silence and make seperate files for each recorded tone
    //new tone files will have the start and end time as the file name
    public void splitFromSilence(){
       int cnt = 0;
       int lettercount = 0;
       
       System.out.println("Labbak");
       
        for (String start : silence) {
            System.out.println(start);
            double now = Double.parseDouble(start);
            if(cnt%2 != 0){
                double next = Double.parseDouble(silence.get(cnt+1));
                double duration = Math.round((next - Math.abs(now)) * 10000.0)/10000.0;
                System.out.println("Silence end- "+now+" next silence start- "+next+" duration- "+duration);
                
                
                 
                 JSONObject obj = new JSONObject("{\"1\":[\"3461\",\"3462\",\"3463\",\"3464\",\"3465\",\"3466\",\"3467\",\"3468\",\"3469\",\"3470\"],\"2\":[\"3473\",\"3474\",\"3475\",\"3476\",\"3477\",\"3478\"],\"3\":[\"3482\",\"3483\",\"3484\",\"3485\",\"3486\",\"3487\",\"3488\",\"3489\",\"3490\",\"3491\"],\"4\":[\"3492\",\"3493\",\"3494\",\"3495\",\"3496\",\"3497\",\"3498\",\"3499\",\"3500\",\"3501\"],\"5\":[\"3502\",\"3503\",\"3504\",\"3505\",\"3507\",\"3508\",\"3509\",\"3510\",\"3511\",\"3512\"],\"6\":[\"3513\",\"3514\",\"3515\",\"3517\",\"3520\",\"3521\",\"3522\",\"3523\",\"3524\",\"3526\"],\"7\":[\"3482-3530\",\"3482-3535\",\"3482-3536\",\"3482-3537\",\"3482-3538\",\"3482-3539\",\"3482-3540\",\"3482-3542\",\"3482-3544\",\"3482-3545\"],\"8\":[\"3482-3546\",\"3482-3547\",\"3482-3548\",\"3482-3549\",\"3482-3550\",\"3482-3570\"],\"9\":[\"3484-3530\",\"3484-3535\",\"3484-3536\",\"3484-3537\",\"3484-3538\",\"3484-3539\",\"3484-3540\",\"3484-3542\",\"3484-3544\",\"3484-3535\"],\"10\":[\"3484-3546\",\"3484-3547\",\"3484-3548\",\"3484-3549\",\"3484-3550\"],\"11\":[\"3488-3530\",\"3488-3535\",\"3488-3536\",\"3488-3537\",\"3488-3538\",\"3488-3539\",\"3488-3540\",\"3488-3542\",\"3488-3545\"],\"12\":[\"3488-3546\",\"3488-3547\",\"3488-3548\",\"3488-3549\",\"3488-3553\",\"3488-3570\"],\"13\":[\"3490-3530\",\"3490-3535\",\"3490-3536\",\"3490-3537\",\"3490-3538\",\"3490-3539\",\"3490-3540\",\"3490-3542\",\"3490-3545\"],\"14\":[\"3490-3546\",\"3490-3547\",\"3490-3548\",\"3490-3549\",\"3490-3550\",\"3490-3570\"],\"15\":[\"3495-3530\",\"3495-3535\",\"3495-3536\",\"3495-3537\",\"3495-3538\",\"3495-3539\",\"3495-3540\",\"3495-3542\",\"3495-3545\"],\"16\":[\"3495-3546\",\"3495-3547\",\"3495-3548\",\"3495-3549\",\"3495-3550\",\"3495-3570\"],\"17\":[\"3497-3530\",\"3497-3535\",\"3497-3536\",\"3497-3537\",\"3497-3538\",\"3497-3539\",\"3497-3540\",\"3497-3542\",\"3497-3545\"],\"18\":[\"3497-3546\",\"3497-3547\",\"3497-3548\",\"3497-3549\",\"3497-3550\",\"3497-3570\"],\"19\":[\"3501-3530\",\"3501-3535\",\"3501-3536\",\"3501-3587\",\"3501-3538\",\"3501-3539\",\"3501-3540\",\"3501-3542\",\"3501-3544\",\"3501-3545\"],\"20\":[\"3501-3546\",\"3501-3547\",\"3501-3548\",\"3501-3549\",\"3501-3550\",\"3501-3570\"],\"21\":[\"3503-3530\",\"3503-3535\",\"3503-3536\",\"3503-3537\",\"3503-3538\",\"3503-3539\",\"3503-3540\",\"3503-3542\",\"3503-3544\",\"3503-3545\"],\"22\":[\"3503-3546\",\"3503-3547\",\"3503-3548\",\"3503-3549\",\"3503-3550\",\"3503-3570\"],\"23\":[\"3505-3530\",\"3505-3535\",\"3505-3536\",\"3505-3537\",\"3505-3538\",\"3505-3539\",\"3505-3540\",\"3505-3542\",\"3505-3544\",\"3505-3545\"],\"24\":[\"3505-3546\",\"3505-3547\",\"3505-3548\",\"3505-3549\",\"3505-3550\",\"3505-3570\"],\"25\":[\"3508-3530\",\"3508-3535\",\"3508-3536\",\"3508-3537\",\"3508-3538\",\"3508-3539\",\"3508-3540\",\"3508-3542\",\"3508-3544\",\"3508-3545\"],\"26\":[\"3508-3546\",\"3508-3547\",\"3508-3548\",\"3508-3549\",\"3508-3550\",\"3508-3570\"],\"27\":[\"3510-3530\",\"3510-3535\",\"3510-3536\",\"3510-3537\",\"3510-3538\",\"3510-3539\",\"3510-3540\",\"3510-3542\",\"3510-3545\"],\"28\":[\"3510-3546\",\"3510-3547\",\"3510-3548\",\"3510-3549\",\"3510-3550\",\"3510-3570\"],\"29\":[\"3512-3530\",\"3512-3535\",\"3512-3536\",\"3512-3537\",\"3512-3538\",\"3512-3539\",\"3512-3540\",\"3512-3542\",\"3512-3544\",\"3512-3545\"],\"30\":[\"3512-3546\",\"3512-3547\",\"3512-3548\",\"3512-3549\",\"3512-3550\",\"3512-3570\"],\"31\":[\"3514-3535\",\"3514-3536\",\"3514-3537\",\"3514-3538\",\"3514-3539\",\"3514-3540\",\"3514-3542\",\"3514-3544\",\"3514-3545\"],\"32\":[\"3514-3546\",\"3514-3547\",\"3514-3548\",\"3514-3549\",\"3514-3550\",\"3514-3570\"],\"33\":[\"3515-3530\",\"3515-3535\",\"3515-3536\",\"3515-3537\",\"3515-3538\",\"3515-3539\",\"3515-3540\",\"3515-3542\",\"3515-3544\",\"3515-3545\"],\"34\":[\"3515-3546\",\"3515-3547\",\"3515-3548\",\"3515-3549\",\"3515-3550\",\"3515-3570\"],\"35\":[\"3517-3530\",\"3517-3535\",\"3517-3536\",\"3517-3537\",\"3517-3538\",\"3517-3539\",\"3517-3540\",\"3517-3542\",\"3517-3545\"],\"36\":[\"3517-3546\",\"3517-3547\",\"3517-3548\",\"3517-3549\",\"3517-3550\",\"3517-3570\"],\"37\":[\"3520-3530\",\"3520-3535\",\"3520-3536\",\"3520-3537\",\"3520-3538\",\"3520-3539\",\"3520-3540\",\"3520-3542\",\"3520-3544\",\"3520-3544\"],\"38\":[\"3520-3546\",\"3520-3547\",\"3520-3548\",\"3520-3549\",\"3520-3550\",\"3520-3570\"],\"39\":[\"3523-3530\",\"3523-3535\",\"3523-3536\",\"3523-3537\",\"3523-3538\",\"3523-3539\",\"3523-3540\",\"3523-3542\",\"3523-3544\",\"3523-3545\"],\"40\":[\"3523-3546\",\"3523-3547\",\"3523-3548\",\"3523-3549\",\"3523-3550\",\"3523-3570\"],\"41\":[\"3524-3530\",\"3524-3535\",\"3524-3536\",\"3524-3537\",\"3524-3538\",\"3524-3539\",\"3524-3540\",\"3524-3542\",\"3524-3544\",\"3524-3545\"],\"42\":[\"3524-3546\",\"3524-3547\",\"3524-3548\",\"3524-3549\",\"3524-3550\",\"3524-3570\",\"3525\"]}"); 
                 String letter = obj.getJSONArray(folder).getString(lettercount);
                 System.out.println(letter);
                
                
                
                System.out.println("Outputeeddddd paaaaaaaaaaathhh eka - " + outfilePath+uid+"-"+letter+".wav");
                
                String command = ffmpegPath + " -i "+infilePath + folder + "\\" +infileName+" -acodec copy -t "+duration+" -ss "+now+" "+outfilePath+uid+"-"+letter+".wav";
                System.out.println(command);
                
                lettercount ++;
                
                                            
                try {
                    Runtime.getRuntime().exec(command);
                } catch (IOException ex) {
                    System.out.println("SORRY! CANNOT RUN THE COMMAND! "+ex);
                }
                if(cnt == silence.size()-3)
                    break;
                cnt++;
            } 
            else
                cnt++;
        }
    }
    
    
    //reads a whole folder of WAV files and trim their silence one by one and save them in a different location with new names
    //here you can specify the new file name to be in the Lire's naming convection 
    //and directly save them in the folder structure where they can be directly accessed by the web front end
    public void removeSilenceAll(){
        File folder = new File(outfilePath);
        File[] listOfFiles = folder.listFiles();

        for (int i = 0; i < listOfFiles.length; i++) {
            if (listOfFiles[i].isFile()) {
                System.out.println("cutting - " + listOfFiles[i].getName());

                String infile = outfilePath + listOfFiles[i].getName();
                String outfile = outTrimmedPath + listOfFiles[i].getName();

                try {
                    Runtime.getRuntime().exec(ffmpegPath + " -i " + infile + " -af silenceremove=1:0:-33dB:-1:0:-33dB " + outfile);
                } catch (IOException ex) {
                    Logger.getLogger(SilenceSplitDetectTrim.class.getName()).log(Level.SEVERE, null, ex);
                }
                
                System.out.println("Trim Done!");
            }
        } 
    }
    
}
    
    
    
    
    
    
    
    


