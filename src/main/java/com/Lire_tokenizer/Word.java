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
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import opennlp.tools.tokenize.Tokenizer;
import opennlp.tools.tokenize.TokenizerME;
import opennlp.tools.tokenize.TokenizerModel;

/**
 *
 * @author Shanaka
 */
public class Word {

    private String basePath = "src\\main\\java\\com\\Lire_tokenizer\\";
    
    private List<String> letter = new ArrayList<>();
    //private Map<BigInteger, String[]> wordMap;

    public Word() {
    }

    public Word(String word) throws IOException {
        List<String> letterCode = new ArrayList<>();
        letterCode = detectLettersAlgo(word);
        letter.addAll(letterCode);
    }
    
    
    
    public List<String> detectLettersAlgo(String word){

        String out = "";
        List<String> codes = new ArrayList<>();
        
        for(int i=0; i<word.length(); i++){
            String lid = "";
            int code = (int)word.charAt(i);
            
            //punctuation
            if((code == 46) | (code == 33) | (code == 44) | (code == 63) /*| (code == 32)*/)
                lid+=",-"+code;
            
            //vowel
            if (code > 3460 && code < 3479)
                lid+=",-"+code;
            
            //consonant
            else if (code > 3481 && code < 3527)
                lid+=",-"+code;
            
            if (code > 3529 && code < 3571)
                lid+="-"+code+",";
            
            out+=lid;
        }
        
        System.out.println(out);
                
        String[] chunks = out.split(",");
        for(String chunk: chunks){
            if(!chunk.equals(""))
                codes.add(chunk);
        }
        
        return codes;
    }
    
    

    public List<String> detectLettersNLP(String str) throws FileNotFoundException, IOException {
        InputStream is = new FileInputStream(basePath+"TrainingSets\\sinhala-letters_token-trained-withSPLIT");
        TokenizerModel model = new TokenizerModel(is);
        Tokenizer tokenizer = new TokenizerME(model);
        List<String> letterCode = new ArrayList<>();
        String tokens[] = tokenizer.tokenize(str);
        String test = "";
        for (String a : tokens) {
            System.out.println(a);
            letterCode.add(mapLetterToTone(a));
        }
        is.close();

        return letterCode;
    }

    public static String mapLetterToTone(String letter) {
        String tone = "01";
        int[] codeArr = null;

        //if letter is a rakaranshaya
//        if (((int) letter.charAt(0) > 3481 && (int) letter.charAt(0) < 3527)
//                && ((int) letter.charAt(1) == 3530)
//                && ((int) letter.charAt(2) == 8205)
//                && ((int) letter.charAt(3) == 3515)) {
//            tone += "rakaranshaya";
//
//            return tone;
//        }

        //check for other letters
        for (int i = 0; i < letter.length(); i++) {
            int code = (int) letter.charAt(i);
            System.out.println("code ="+code);
            //first charactor
            if (i == 0) {

        //if letter is a vowel
                if (code > 3460 && code < 3479) {
                    switch (code) {
                        case 3461:
                            tone += "-" + code;
                            break;
                        case 3462:
                            tone += "-" + code;
                            break;
                        case 3463:
                            tone += "-" + code;
                            break;
                        case 3464:
                            tone += "-" + code;
                            break;
                        case 3465:
                            tone += "-" + code;
                            break;
                        case 3466:
                            tone += "-" + code;
                            break;
                        case 3467:
                            tone += "-" + code;
                            break;
                        case 3468:
                            tone += "-" + code;
                            break;
                        case 3473:
                            tone += "-" + code;
                            break;
                        case 3474:
                            tone += "-" + code;
                            break;
                        case 3475:
                            tone += "-" + code;
                            break;
                        case 3476:
                            tone += "-" + code;
                            break;
                        case 3477:
                            tone += "-" + code;
                            break;
                        case 3478:
                            tone += "-" + code;
                            break;
                    }
                } //if a consonant
                else if (code > 3481 && code < 3527) {
                    switch (code) {
                        case 3482: //ka
                            tone += "-" + code;
                            break;
                        case 3483:
                            tone += "-" + code;
                            break;
                        case 3484: //kae
                            tone += "-" + code;
                            break;
                        case 3485:
                            tone += "-" + code;
                            break;
                        case 3486:
                            tone += "-" + code;
                            break;
                        case 3487:
                            tone += "-" + code;
                            break;
                        case 3488:
                            tone += "-" + code;
                            break;
                        case 3489:
                            tone += "-" + code;
                            break;
                        case 3490:
                            tone += "-" + code;
                            break;
                        case 3491:
                            tone += "-" + code;
                            break;
                        case 3492:
                            tone += "-" + code;
                            break;
                        case 3493:
                            tone += "-" + code;
                            break;
                        case 3494:
                            tone += "-" + code;
                            break;
                        case 3495:
                            tone += "-" + code;
                            break;
                        case 3496:
                            tone += "-" + code;
                            break;
                        case 3497:
                            tone += "-" + code;
                            break;
                        case 3498:
                            tone += "-" + code;
                            break;
                        case 3499:
                            tone += "-" + code;
                            break;
                        case 3500:
                            tone += "-" + code;
                            break;
                        case 3501:
                            tone += "-" + code;
                            break;
                        case 3502:
                            tone += "-" + code;
                            break;
                        case 3503:
                            tone += "-" + code;
                            break;
                        case 3504:
                            tone += "-" + code;
                            break;
                        case 3505:
                            tone += "-" + code;
                            break;
                        case 3507:
                            tone += "-" + code;
                            break;
                        case 3508:
                            tone += "-" + code;
                            break;
                        case 3509:
                            tone += "-" + code;
                            break;
                        case 3510:
                            tone += "-" + code;
                            break;
                        case 3511:
                            tone += "-" + code;
                            break;
                        case 3512:
                            tone += "-" + code;
                            break;
                        case 3513:
                            tone += "-" + code;
                            break;
                        case 3514:
                            tone += "-" + code;
                            break;
                        case 3515:
                            tone += "-" + code;
                            break;
                        case 3517:
                            tone += "-" + code;
                            break;
                        case 3520:
                            tone += "-" + code;
                            break;
                        case 3521:
                            tone += "-" + code;
                            break;
                        case 3522:
                            tone += "-" + code;
                            break;
                        case 3523:
                            tone += "-" + code;
                            break;
                        case 3524:
                            tone += "-" + code;
                            break;
                        case 3525:
                            tone += "-" + code;
                            break;
                        case 3526:
                            tone += "-" + code;
                            break;
                    }
                }
            }

            //second charactor
            if (i == 1) {
                //if letter is a modifire
                if (code > 3529 && code < 3571) {
                    switch (code) {
                        case 3530:
                            tone += "-" + code;
                            break;
                        case 3535:
                            tone += "-" + code;
                            break;
                        case 3536:
                            tone += "-" + code;
                            break;
                        case 3537:
                            tone += "-" + code;
                            break;
                        case 3538:
                            tone += "-" + code;
                            break;
                        case 3539:
                            tone += "-" + code;
                            break;
                        case 3540:
                            tone += "-" + code;
                            break;
                        case 3542:
                            tone += "-" + code;
                            break;
                        case 3544:
                            tone += "-" + code;
                            break;
                        case 3545:
                            tone += "-" + code;
                            break;
                        case 3546:
                            tone += "-" + code;
                            break;
                        case 3547:
                            tone += "-" + code;
                            break;
                        case 3548:
                            tone += "-" + code;
                            break;
                        case 3549:
                            tone += "-" + code;
                            break;
                        case 3550:
                            tone += "-" + code;
                            break;
                        case 3570:
                            tone += "-" + code;
                            break;
                    }
                }
            }
        }

        return tone;
    }

    /**
     * @return the letter
     */
    public List<String> getLetter() {
        return letter;
    }

    /**
     * @param letter the letter to set
     */
    public void setLetter(List<String> letter) {
        this.letter = letter;
    }

}
