/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.Lire_tokenizer;

import java.io.BufferedOutputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.nio.charset.Charset;
import opennlp.tools.tokenize.TokenSample;
import opennlp.tools.tokenize.TokenSampleStream;
import opennlp.tools.tokenize.TokenizerME;
import opennlp.tools.tokenize.TokenizerModel;
import opennlp.tools.util.ObjectStream;
import opennlp.tools.util.PlainTextByLineStream;
import opennlp.tools.util.TrainingParameters;

/**
 *
 * @author Shanaka
 */
public class TokenizerTrainer {
    
    String modelFile = null;
    String trainerPath = null;
    private final String basePath = "src\\main\\java\\com\\Lire_tokenizer\\TrainingSets\\";
    
    public TokenizerTrainer(String modal, String trainer){
        this.modelFile = modal;
        this.trainerPath = trainer;
    }
     

    public void train() throws FileNotFoundException, IOException {

        Charset charset = Charset.forName("UNICODE");
        ObjectStream<String> lineStream = new PlainTextByLineStream(new FileInputStream(basePath+trainerPath), charset);
        ObjectStream<TokenSample> sampleStream = new TokenSampleStream(lineStream);

        TokenizerModel model;

        try {
            model = TokenizerME.train("en", sampleStream, true, TrainingParameters.defaultParams());
        } finally {
            sampleStream.close();
        }

        OutputStream modelOut = null;

        try {
            modelOut = new BufferedOutputStream(new FileOutputStream(modelFile));
            model.serialize(modelOut);
        } finally {
            if (modelOut != null) {
                modelOut.close();
            }
        }
    }

}
