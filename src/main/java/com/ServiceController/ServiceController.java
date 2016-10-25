/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ServiceController;

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

/**
 *
 * @author Lire 2016
 */
@RestController
public class ServiceController {
    
    String basePath = "C:\\xampp\\htdocs\\LireFrontend\\voiceprofiles\\1\\";
    
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
* Calls the sentence tree function
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
* Calls the direct tokenizing function
*/    
    @RequestMapping(
        value = "/tokenize/directTokenize",
        method = RequestMethod.GET,
        produces = MediaType.APPLICATION_JSON_VALUE
    )
    public List<List<List<String>>> directTokenize(@RequestParam(value = "text", defaultValue = "") String text)
    throws FileNotFoundException, IOException
    {
        Para p = new Para();
        return(p.directTokenize(text));
    }
    
    
  
/*
* Calls the pitch and timing change function
*/
    
    @RequestMapping(
            value = "/prosody/changeprosody",
            method = RequestMethod.GET)
    
    public String changePitch(@RequestParam(value = "filename", defaultValue = "null") String infile,
                              @RequestParam(value = "pitch", defaultValue = "N") String pitch,
                              @RequestParam(value = "timing", defaultValue = "N") String timing) 
    {
        String outPut = infile+" "+pitch;
                
        //change timing first
        if("L".equals(timing)){
            if(filePresent("L-"+infile))
                return "L-"+infile;
            
            //code
            
            infile = "L-"+infile;
        }

        else if("S".equals(timing)){
            if(filePresent("S-"+infile))
                return "S-"+infile;
            
            //code
            
            infile = "S-"+infile;
        }

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
* Calls the timing change function
*/
    
    @RequestMapping(
            value = "/prosody/changetiming",
            method = RequestMethod.GET)
    public String changeTiming(@RequestParam(value = "filename", defaultValue = "null") String infile,
                              @RequestParam(value = "timing", defaultValue = "N") String timing) 
    {
        
        //code to call timing
        
        return "Still not implemented - timing part!";
    }
    
    
/*
* Calls the recording splitting and saving function
*/
    
    //Kim do your function here looking at above functions. I have  made a class in com.Lire_recording
    //use that class
    
    @RequestMapping(value = "/download", 
                    method = RequestMethod.GET)
    @ResponseBody
    public String download(@RequestParam(value = "tones", defaultValue = "null") String tones){
      System.out.println(tones);
      String parts[] = tones.split(",");
      
      downloadData dwn = new downloadData(parts);      
      return dwn.prepare();
    }
    
    
}
