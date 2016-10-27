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

  
    public static String uid="user2";
    
        
    // need get the iud
    //variables
    //full path to the ffmpeg.exe

  
    public static String ffmpegPath = "C:\\ffmpeg\\bin\\ffmpeg";
   

    //path to the folder where the recorded file to identify the silence is located
   
    
    public static String infilePath = "C:\\xampp\\htdocs\\lireFrontend\\Voice\\php_sinhala\\Direct_upload\\1\\";
    
    //name of the file to identify the silence
   
    // 1.wav name get in PHP soundupload file $uploadpath
    public static String infileName = "1.wav";
    

    //path to the folder where all the silence cut audio are outputted
   
  
      public static String outfilePath = "C:\\xampp\\htdocs\\lireFrontend\\Voice\\php_sinhala\\Affter_ffmpeg\\UVProfileSinhala\\";
        
        
  
    
    //path to the silence trimmed tones. This may be the original path at the web server
   
 
  public static String outTrimmedPath = "C:\\xampp\\htdocs\\lireFrontend\\Voice\\php_sinhala\\Affter_ffmpeg\\UVProfileSinahalaAftertrim\\" ;    
    
    //List which stores the silence detect data from ffmpeg
    public static List<String> silence;

    
    
    public static void main(String[] args) {
        identifySilence();
        splitFromSilence();
        removeSilenceAll();
//        store();
    }

    
    
    //detect silence in the input long audio file with several tones recorded with a little silence between seperate tones
    //put the silence data into an array list
    public static void identifySilence() {
        silence = new ArrayList<>();
        String cmd1 = ffmpegPath + " -i " + infilePath + infileName + " -af silencedetect=noise=-30dB:d=0.1 -f null -";
        System.out.println(cmd1);

        try {
            ProcessBuilder builder = new ProcessBuilder(ffmpegPath,
                    "-i",
                    infilePath + infileName,
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
    public static void splitFromSilence(){
       int cnt = 0;
       int lettercount = 0;
       
        for (String start : silence) {
            System.out.println(start);
            double now = Double.parseDouble(start);
            if(cnt%2 != 0){
                double next = Double.parseDouble(silence.get(cnt+1));
                double duration = Math.round((next - Math.abs(now)) * 10000.0)/10000.0;
                System.out.println("Silence end- "+now+" next silence start- "+next+" duration- "+duration);
                
                
                 
                  
                 JSONObject obj = new JSONObject("{\"1\":[\"3461\",\"3462\",\"3463\",\"3464\",\"3465\",\"3466\",\"3467\",\"3468\",\"3469\",\"3470\"],\"2\":[\"3473\",\"3474\",\"3475\",\"3476\",\"3477\",\"3478\"],\"3\":[\"3482\",\"3483\",\"3484\",\"3485\",\"3486\",\"3487\",\"3488\",\"3489\",\"3490\",\"3491\"],\"4\":[\"3492\",\"3493\",\"3494\",\"3495\",\"3496\",\"3497\",\"3498\",\"3499\",\"3500\",\"3501\"],\"5\":[\"3502\",\"3503\",\"3504\",\"3505\",\"3507\",\"3508\",\"3509\",\"3510\",\"3511\",\"3512\"],\"6\":[\"3513\",\"3514\",\"3515\",\"3517\",\"3520\",\"3521\",\"3522\",\"3523\",\"3524\",\"3526\"],\"7\":[\"3482_3530\",\"3482_3535\",\"3482_3536\",\"3482_3537\",\"3482_3538\",\"3482_3539\",\"3482_3540\",\"3482_3542\",\"3482_3544\",\"3482_3545\"],\"8\":[\"3482_3546\",\"3482_3547\",\"3482_3548\",\"3482_3549\",\"3482_3550\",\"3482_3570\"],\"9\":[\"3484_3530\",\"3484_3535\",\"3484_3536\",\"3484_3537\",\"3484_3538\",\"3484_3539\",\"3484_3540\",\"3484_3542\",\"3484_3544\",\"3484_3535\"],\"10\":[\"3484_3546\",\"3484_3547\",\"3484_3548\",\"3484_3549\",\"3484_3550\"],\"11\":[\"3488_3530\",\"3488_3535\",\"3488_3536\",\"3488_3537\",\"3488_3538\",\"3488_3539\",\"3488_3540\",\"3488_3542\",\"3488_3545\"],\"12\":[\"3488_3546\",\"3488_3547\",\"3488_3548\",\"3488_3549\",\"3488_3553\",\"3488_3570\"],\"13\":[\"3490_3530\",\"3490_3535\",\"3490_3536\",\"3490_3537\",\"3490_3538\",\"3490_3539\",\"3490_3540\",\"3490_3542\",\"3490_3545\"],\"14\":[\"3490_3546\",\"3490_3547\",\"3490_3548\",\"3490_3549\",\"3490_3550\",\"3490_3570\"],\"15\":[\"3495_3530\",\"3495_3535\",\"3495_3536\",\"3495_3537\",\"3495_3538\",\"3495_3539\",\"3495_3540\",\"3495_3542\",\"3495_3545\"],\"16\":[\"3495_3546\",\"3495_3547\",\"3495_3548\",\"3495_3549\",\"3495_3550\",\"3495_3570\"],\"17\":[\"3497_3530\",\"3497_3535\",\"3497_3536\",\"3497_3537\",\"3497_3538\",\"3497_3539\",\"3497_3540\",\"3497_3542\",\"3497_3545\"],\"18\":[\"3497_3546\",\"3497_3547\",\"3497_3548\",\"3497_3549\",\"3497_3550\",\"3497_3570\"],\"19\":[\"3501_3530\",\"3501_3535\",\"3501_3536\",\"3501_3587\",\"3501_3538\",\"3501_3539\",\"3501_3540\",\"3501_3542\",\"3501_3544\",\"3501_3545\"],\"20\":[\"3501_3546\",\"3501_3547\",\"3501_3548\",\"3501_3549\",\"3501_3550\",\"3501_3570\"],\"21\":[\"3503_3530\",\"3503_3535\",\"3503_3536\",\"3503_3537\",\"3503_3538\",\"3503_3539\",\"3503_3540\",\"3503_3542\",\"3503_3544\",\"3503_3545\"],\"22\":[\"3503_3546\",\"3503_3547\",\"3503_3548\",\"3503_3549\",\"3503_3550\",\"3503_3570\"],\"23\":[\"3505_3530\",\"3505_3535\",\"3505_3536\",\"3505_3537\",\"3505_3538\",\"3505_3539\",\"3505_3540\",\"3505_3542\",\"3505_3544\",\"3505_3545\"],\"24\":[\"3505_3546\",\"3505_3547\",\"3505_3548\",\"3505_3549\",\"3505_3550\",\"3505_3570\"],\"25\":[\"3508_3530\",\"3508_3535\",\"3508_3536\",\"3508_3537\",\"3508_3538\",\"3508_3539\",\"3508_3540\",\"3508_3542\",\"3508_3544\",\"3508_3545\"],\"26\":[\"3508_3546\",\"3508_3547\",\"3508_3548\",\"3508_3549\",\"3508_3550\",\"3508_3570\"],\"27\":[\"3510_3530\",\"3510_3535\",\"3510_3536\",\"3510_3537\",\"3510_3538\",\"3510_3539\",\"3510_3540\",\"3510_3542\",\"3510_3545\"],\"28\":[\"3510_3546\",\"3510_3547\",\"3510_3548\",\"3510_3549\",\"3510_3550\",\"3510_3570\"],\"29\":[\"3512_3530\",\"3512_3535\",\"3512_3536\",\"3512_3537\",\"3512_3538\",\"3512_3539\",\"3512_3540\",\"3512_3542\",\"3512_3544\",\"3512_3545\"],\"30\":[\"3512_3546\",\"3512_3547\",\"3512_3548\",\"3512_3549\",\"3512_3550\",\"3512_3570\"],\"31\":[\"3514_3535\",\"3514_3536\",\"3514_3537\",\"3514_3538\",\"3514_3539\",\"3514_3540\",\"3514_3542\",\"3514_3544\",\"3514_3545\"],\"32\":[\"3514_3546\",\"3514_3547\",\"3514_3548\",\"3514_3549\",\"3514_3550\",\"3514_3570\"],\"33\":[\"3515_3530\",\"3515_3535\",\"3515_3536\",\"3515_3537\",\"3515_3538\",\"3515_3539\",\"3515_3540\",\"3515_3542\",\"3515_3544\",\"3515_3545\"],\"34\":[\"3515_3546\",\"3515_3547\",\"3515_3548\",\"3515_3549\",\"3515_3550\",\"3515_3570\"],\"35\":[\"3517_3530\",\"3517_3535\",\"3517_3536\",\"3517_3537\",\"3517_3538\",\"3517_3539\",\"3517_3540\",\"3517_3542\",\"3517_3545\"],\"36\":[\"3517_3546\",\"3517_3547\",\"3517_3548\",\"3517_3549\",\"3517_3550\",\"3517_3570\"],\"37\":[\"3520_3530\",\"3520_3535\",\"3520_3536\",\"3520_3537\",\"3520_3538\",\"3520_3539\",\"3520_3540\",\"3520_3542\",\"3520_3544\",\"3520_3544\"],\"38\":[\"3520_3546\",\"3520_3547\",\"3520_3548\",\"3520_3549\",\"3520_3550\",\"3520_3570\"],\"39\":[\"3523_3530\",\"3523_3535\",\"3523_3536\",\"3523_3537\",\"3523_3538\",\"3523_3539\",\"3523_3540\",\"3523_3542\",\"3523_3544\",\"3523_3545\"],\"40\":[\"3523_3546\",\"3523_3547\",\"3523_3548\",\"3523_3549\",\"3523_3550\",\"3523_3570\"],\"41\":[\"3524_3530\",\"3524_3535\",\"3524_3536\",\"3524_3537\",\"3524_3538\",\"3524_3539\",\"3524_3540\",\"3524_3542\",\"3524_3544\",\"3524_3545\"],\"42\":[\"3524_3546\",\"3524_3547\",\"3524_3548\",\"3524_3549\",\"3524_3550\",\"3524_3570\",\"3525\"]}");
                 String letter = obj.getJSONArray("1").getString(lettercount);
                 System.out.println(letter);
                
                
                
                
                
                String command = ffmpegPath + " -i "+infilePath+infileName+" -acodec copy -t "+duration+" -ss "+now+" "+outfilePath+uid+"_"+letter+".wav";
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
    public static void removeSilenceAll(){
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
    
    
    
    
    
    
    
    


