/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package RNAseqPipeline;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author ZHAO Qi
 * @date 2015-3-20 16:24:00
 * @version 1.6.0
 */
public class GeneLengthFileReader {

    public HashMap<String, Integer> geneLengthMap2 = new HashMap<String, Integer>();
    private String lengthfile;

    public GeneLengthFileReader(String lengthfile) {
        this.lengthfile = lengthfile;
        this.process();
    }

    public void process() {
        BufferedReader br = null;
        try {
            br = new BufferedReader(new FileReader(new File(lengthfile)));
            String[] str;

            while (br.ready()) {
                str = br.readLine().split("\t");
                geneLengthMap2.put(str[0], Integer.parseInt(str[1]));
            }
        } catch (FileNotFoundException ex) {
            Logger.getLogger(GeneLengthFileReader.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(GeneLengthFileReader.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                br.close();
            } catch (IOException ex) {
                Logger.getLogger(GeneLengthFileReader.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

 
    public HashMap<String, Integer> getGeneLengthMap2() {
        return geneLengthMap2;
    }
    
    

    public static HashMap<String, Integer> getgeneLengthMap(String lengthFile) throws FileNotFoundException, IOException {
        HashMap<String, Integer> genelengthMap = new HashMap<String, Integer>();
        BufferedReader br = new BufferedReader(new FileReader(new File(lengthFile)));
        String[] str;
        while (br.ready()) {
            str = br.readLine().split("\t");
            genelengthMap.put(str[0], Integer.parseInt(str[1]));
        }
        br.close();
        return genelengthMap;
    }

}
