/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package Multifile2Matrix.CombineRSEMmatrix;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.HashMap;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Administrator
 * @since 2016-9-27
 * @coding time 17:25:41
 * @author Qi Zhao
 */
public class ReadEnsembleMapfile {
public static HashMap<String,String> getEnsembleMap(String filename){
    BufferedReader br=null;
    HashMap<String,String> map=null;
    try {
         map=new HashMap<String,String> ();
        br = new BufferedReader(new java.io.FileReader(new File(filename)));
        while (br.ready()) {
            String dataStr = br.readLine().trim();
            String[] strarr=dataStr.split("\t");
            map.put(strarr[0], dataStr);
        }  
    } catch (FileNotFoundException ex) {
        Logger.getLogger(ReadEnsembleMapfile.class.getName()).log(Level.SEVERE, null, ex);
    } catch (IOException ex) {
        Logger.getLogger(ReadEnsembleMapfile.class.getName()).log(Level.SEVERE, null, ex);
    } finally {
        try {
            br.close();
        } catch (IOException ex) {
            Logger.getLogger(ReadEnsembleMapfile.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
     return map;
}
}
