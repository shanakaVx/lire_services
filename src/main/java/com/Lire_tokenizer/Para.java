/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.Lire_tokenizer;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import opennlp.tools.sentdetect.SentenceDetectorME;
import opennlp.tools.sentdetect.SentenceModel;

/**
 *
 * @author Shanaka Caldera
 */

public class Para {
    
    private String basePath = "src\\main\\java\\com\\Lire_tokenizer\\";

    //private List<Sentense> sent = new ArrayList<Sentense>();
    private BigInteger id = BigInteger.ZERO;
    private Map<BigInteger, Sentense> sentMap = new HashMap<BigInteger,Sentense>();
    
    public Para(){}
    
    public Para(String para) throws FileNotFoundException, IOException{
        String detected[] = detectSentensesNLP(para);
        for(String s : detected){
            id = id.add(BigInteger.ONE);
            sentMap.put(id, new Sentense(s));            
        }
    }
    
    
    public List<List<List<String>>> directTokenize(String para) throws FileNotFoundException, IOException{
        List<List<List<String>>> letters = new ArrayList<>();
        
        String detected[] = detectSentensesNLP(para);
        Sentense sent = new Sentense();
        
        for(String s : detected){
            //identify non letters
            numletter converter = new numletter();
            s = converter.numberToText(s);
            letters.add(sent.directTokenize(s));
        }
        return letters;
    }
    
    
    public String[] detectSentensesNLP(String str) throws FileNotFoundException, IOException{
        InputStream is = new FileInputStream(basePath+"TrainingSets\\sentenceTrained-si");
        SentenceModel model = new SentenceModel(is);
        SentenceDetectorME sdetector = new SentenceDetectorME(model);

        String sentences[] = sdetector.sentDetect(str);
        int sentCount = 1;
        for (String sentence : sentences) {
            System.out.println(sentCount++ +"--"+sentence);
        }
        is.close();

        return sentences;
    }
    
    /**
     * @return the sentMap
     */
    public Map<BigInteger, Sentense> getSentMap() {
        return sentMap;
    }

    /**
     * @param sentMap the sentMap to set
     */
    public void setSentMap(Map<BigInteger, Sentense> sentMap) {
        this.sentMap = sentMap;
    }
    
}
