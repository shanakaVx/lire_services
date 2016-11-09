/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ServiceController;

import com.Lire_Authentication.ConnectionManager;
import com.Lire_prosody.*;
import com.Lire_tokenizer.*;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.math.BigInteger;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import silencesplitdetecttrim.SilenceSplitDetectTrim;

/**
 *
 * @author Lire 2016
 */
@RestController
public class ServiceController {
    
    String basePath = "C:\\xampp\\htdocs\\LireFrontend\\voiceprofiles\\1\\";
    private String appKey = "";
    private final ConnectionManager con = new ConnectionManager("jdbc:mysql://localhost:3306/lire", "root", "");
    private final int tokenLength = 20;
     
/*
* Shows a help at the base URL
*/
    @RequestMapping(
        value = "/",
        method = RequestMethod.GET,
        produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity home(@RequestParam(value = "text", defaultValue = "txt") String text) throws IOException{
        String message = "WELCOME TO LIRE SERVICES | THIS IS THE ROOT | PLEASE CALL THE RELEVANT PATH \n"
                + "copyright(c) 2016 SLIIT Tone based voice synthesizer framework for voice based application development | Lire Version 0.1";
        
        return new ResponseEntity<>(message, HttpStatus.OK);
    }
    

    
   /*
    *
    * @desc login a user to the system
    * @return returns the app key
    *
    */
    @RequestMapping(
        value = "/login",
        method = RequestMethod.GET
        )
    public String login(@RequestParam(value = "un", defaultValue = "txt") String un,
                                @RequestParam(value = "pw", defaultValue = "txt") String pw)
    {
        //this.con = new ConnectionManager("jdbc:mysql://localhost:3306/lire", "root", "");
        appKey = con.login(un, pw);
        if(appKey.equals("error!"))
            return appKey;
        return appKey;
   
    }
    
    
  /*
    *
    * @desc login a user to the system
    * @return returns the app key
    *
    */
    @RequestMapping(
        value = "/logout",
        method = RequestMethod.GET)
    public ResponseEntity logout(@RequestParam(value = "un", defaultValue = "txt") String un,
                                @RequestParam(value = "appk", defaultValue = "txt") String appk)
    {
        if(!(con.checkLogin(appk)))
            return new ResponseEntity<>("You have to login first", HttpStatus.NOT_FOUND);
        
        String msg = con.logOut(un, appk);
        
        if(msg.equals("error!"))
            return new ResponseEntity<>(msg, HttpStatus.NOT_FOUND);
        
        return new ResponseEntity<>(msg, HttpStatus.OK);
    }
    
    
    /*
    *
    * @desc Register a user in the db
    * @return returns number of rows created, otherwise "error"
    *
    */
    @RequestMapping(
        value = "/register",
        method = RequestMethod.GET)
    public String register(@RequestParam(value = "mail", defaultValue = "txt") String mail,
                           @RequestParam(value = "name", defaultValue = "txt") String name,
                           @RequestParam(value = "pw", defaultValue = "txt") String pw)
    {
        String msg = con.register(mail, name, pw);
        return msg;
    }
    
    
    
    /*
    *
    * @desc get the user id when the app key is given
    * @return returns the user id
    *
    */
    @RequestMapping(
        value = "/getuid",
        method = RequestMethod.GET,
        produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity getUid(@RequestParam(value = "appk", defaultValue = "txt") String appk){
        String id = con.getUid(appk);
        
        if(id.equals("No UID found"))
            return new ResponseEntity<>(id, HttpStatus.NOT_FOUND);
        
        return new ResponseEntity<>(id, HttpStatus.OK);
    }
    
    
        /*
    *
    * @desc get the user id when the app key is given
    * @return returns the user id
    *
    */
    @RequestMapping(
        value = "/getuname",
        method = RequestMethod.GET)
    public String getUname(@RequestParam(value = "appk", defaultValue = "txt") String appk){
        String id = con.getUname(appk);
        return id;
    }
    
    

    /*
    *
    * @desc get the current user id
    * @return returns the current user id
    *
    */
    @RequestMapping(
        value = "/getuidphp",
        method = RequestMethod.GET,
        produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity getUidPhp(){
        String id = con.getUidPhp();
        
        if(id.equals("No UID found"))
            return new ResponseEntity<>(id, HttpStatus.NOT_FOUND);
        
        return new ResponseEntity<>(id, HttpStatus.OK);
    }
    
    
    
    /*
    *
    * @desc get the scentence tree
    * @return
    *
    */
    @RequestMapping(
        value = "/tokenize/sentenceTree",
        method = RequestMethod.GET,
        produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Collection<Sentense>> Tokenize(@RequestParam(value = "text", defaultValue = "") String text) throws IOException{
        Para p = new Para(text);
        Map<BigInteger, Sentense> sentMap = p.getSentMap();
        Collection<Sentense> tokens = sentMap.values();

        return new ResponseEntity<>(tokens, HttpStatus.OK);
    }
    

   /*
    *
    * @desc direct tokenize form scentence to letters
    * @return returns the array of tokenized letters
    *
    */   
    @RequestMapping(
        value = "/tokenize/directTokenize",
        method = RequestMethod.GET,
        produces = MediaType.APPLICATION_JSON_VALUE
    )
    public List<List<List<String>>> directTokenize(@RequestParam(value = "text", defaultValue = "") String text,
                                                   @RequestParam(value = "appk", defaultValue = "") String appk)
    throws FileNotFoundException, IOException
    {
        //checking if the free token length exceeded and the user is logged in
        if((text.length()>tokenLength) && !(con.checkLogin(appk))){
            System.out.println("needLogin");
            return null;
        }
        
        Para p = new Para();
        return(p.directTokenize(text));
    }
    
    
  
   /*
    *
    * @desc prosody is now Depricated!
    * @return returns the rendered file name
    *
    */ 
    @RequestMapping(
            value = "/prosody/changeprosody",
            method = RequestMethod.GET)
    
    public String changePitch(@RequestParam(value = "filename", defaultValue = "null") String infile,
                              @RequestParam(value = "pitch", defaultValue = "N") String pitch,
                              @RequestParam(value = "appk", defaultValue = "") String appk) 
    {
        
        if(!(con.checkLogin(appk)))
            return "needLogin";
        
        String outPut = infile+" "+pitch;
                
//        //change timing first
//        if("L".equals(timing)){
//            if(filePresent("L-"+infile))
//                return "L-"+infile;
//            
//            //code
//            
//            infile = "L-"+infile;
//        }
//
//        else if("S".equals(timing)){
//            if(filePresent("S-"+infile))
//                return "S-"+infile;
//            
//            //code
//            
//            infile = "S-"+infile;
//        }

        //change pitch
        if("H".equals(pitch)){
            if(filePresent("H-"+infile))
                return "H-"+infile;
            
            Pitch p = new Pitch(1.18f, 1.18f, basePath);
            String outFile = basePath+"H-"+infile;

            System.out.println("out file - "+outFile);
            outPut = p.changePitch(infile, outFile);
            System.out.println("pitch returned - "+outPut);
            infile = "H-"+infile;
        }
        else if("L".equals(pitch)){
            if(filePresent("L-"+infile))
                return "L-"+infile;
            
            Pitch p = new Pitch(0.88f, 0.88f, basePath);
            String outFile = basePath+"L-"+infile;
            outPut = p.changePitch(infile, outFile);
            System.out.println("pitch returned - "+outPut);
            infile = "L-"+infile;
        }

        return infile;
    }
    
    
    private boolean filePresent(String infile){
        File f = new File(basePath + infile);
        if(f.exists() && !f.isDirectory()) { 
            System.out.println("File exsists and returning same filename " + basePath + infile);
            return true;
        }
        else
            return false;
    }
    
    
     
    /*
    *
    * @desc change the timing of the audio file
    * @return returns the timining chaged file name
    *
    */
    @RequestMapping(
            value = "/prosody/changetiming",
            method = RequestMethod.GET)
    public String changeTiming(@RequestParam(value = "filename", defaultValue = "null") String infile,
                               @RequestParam(value = "timing", defaultValue = "N") String timing,
                               @RequestParam(value = "appk", defaultValue = "") String appk) {

        if(!(con.checkLogin(appk)))
            return "needLogin";
        
        float time = 0.0f;
        String prefix = "";
        String output = "";
        //code to call timing
        if ("LT".equals(timing)) {
            time = 1.2f;
            prefix = "LT-";
        } else if ("ST".equals(timing)) {
            time = 0.5f;
            prefix = "ST-";
        }
        try {
            Timing timing12 = new Timing("", "C:\\xampp\\htdocs\\LireFrontend\\voiceprofiles\\1\\", infile, time, prefix);
            output = timing12.ChangeTiming();
        } catch (Exception e) {
        }

        return output;
    }
    
    
    
    /*
    *
    * @desc Records the user's audio
    * @return returns success on successful audio decoding
    *
    */
    @RequestMapping(
            value = "/recording/record",
            method = RequestMethod.POST)
    public String splitSilence(@RequestParam(value = "uid", defaultValue = "07") String uid,
                              @RequestParam(value = "folder", defaultValue = "1") String folder,
                              @RequestParam(value="fname", defaultValue = "FILE") String fname,
                              @RequestParam(value = "appk", defaultValue = "") String appk) 
    {
        System.out.println("Recording");
        if(!(con.checkLogin(appk)))
            return "You need to login";

        SilenceSplitDetectTrim silence  = new SilenceSplitDetectTrim(uid, folder, fname);
        silence.identifySilence();
        silence.splitFromSilence();
        //silence.removeSilenceAll();
        
        return "........Trimming! user ID - " +uid +" Folder ID - "+ folder +" Filr name - " + fname;
    }
    
    
    
    /*
    *
    * @desc downloads the rendered audio in wav format
    * @return returns downlaod path
    *
    */
    @RequestMapping(value = "/download", 
                    method = RequestMethod.GET)
    @ResponseBody
    public String download(@RequestParam(value = "tones", defaultValue = "null") String tones,
                           @RequestParam(value = "appk", defaultValue = "") String appk){
        
        if(!(con.checkLogin(appk)))
            return "needLogin";
        
      System.out.println(tones);
      String parts[] = tones.split(",");
      
      downloadData dwn = new downloadData(parts);      
      return dwn.prepare();
    }
    
 
}
