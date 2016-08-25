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
import opennlp.tools.tokenize.Tokenizer;
import opennlp.tools.tokenize.TokenizerME;
import opennlp.tools.tokenize.TokenizerModel;

/**
 *
 * @author Shanaka
 */
public class Sentense {
    private String basePath = "src\\main\\java\\com\\Lire_tokenizer\\";
    private BigInteger id = BigInteger.ZERO;
    private Map<BigInteger, Word> wordMap = new HashMap<BigInteger, Word>();
    
    public Sentense(){}
    
    public Sentense(String sent) throws FileNotFoundException, IOException{
        String detected[] = detectWordsNLP(sent);
        for(String s : detected){
            id = id.add(BigInteger.ONE);
            wordMap.put(id, new Word(s));            
        }
    }
    
    public String[] detectWordsNLP(String str) throws FileNotFoundException, IOException{
        InputStream is = new FileInputStream(basePath+"TrainingSets\\sinhala-letters_token-trained-withSPLIT");
        TokenizerModel model = new TokenizerModel(is);
        Tokenizer tokenizer = new TokenizerME(model);

        String tokens[] = tokenizer.tokenize(str);
        for (String a : tokens) {
            System.out.println(a);
        }
        is.close();

        return tokens;
    }
    
    //private List<String> letter = new ArrayList<>();
    
    public List<List<String>> directTokenize(String sent) throws IOException{
        Word w = new Word();
        List<List<String>> letters = new ArrayList<>();
        letters.add(w.detectLettersNLP(sent));
        return letters;
    }


    /**
     * @return the wordMap
     */
    public Map<BigInteger, Word> getWordMap() {
        return wordMap;
    }

    /**
     * @param wordMap the wordMap to set
     */
    public void setWordMap(Map<BigInteger, Word> wordMap) {
        this.wordMap = wordMap;
    }
}
