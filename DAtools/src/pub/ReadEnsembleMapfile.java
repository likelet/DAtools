/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package pub;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.logging.Level;
import java.util.logging.Logger;
import pub.GTF9;
import pub.GTF9_reader;

/**
 *
 * @author Administrator
 * @since 2016-9-27
 * @coding time 17:25:41
 * @author Qi Zhao
 */
public class ReadEnsembleMapfile {
public static HashMap<String,String> getEnsembleMap(InputStreamReader in){
    
    BufferedReader br=null;
    HashMap<String,String> map=null;
    try {
         map=new HashMap<String,String> ();
          
        br = new BufferedReader(in);
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

public static HashMap<String,String> getEnsembleMapWithTranscriptID(InputStreamReader in){
    BufferedReader br=null;
    HashMap<String,String> map=null;
    try {
         map=new HashMap<String,String> ();
          
        br = new BufferedReader(in);
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
public static HashMap<String,String> getEnsembleMapFromGTF(String GTFfile){
     System.out.println(ToolsforCMD.print_ansi_CYAN("Parsing GTF file " + GTFfile));
    ArrayList<GTF9> gtflist=GTF9_reader.getGTF9list(GTFfile, "gene");
    HashMap<String,String> map=null;
         map=new HashMap<String,String> ();
          
        for (int i = 0; i < gtflist.size(); i++) {
            GTF9 tempgtf=gtflist.get(i);
            String tempstr=tempgtf.getSpecificAttrbute("gene_id")+"\t"+tempgtf.getSpecificAttrbute("gene_name")+"\t"+tempgtf.getSpecificAttrbute("gene_type");
            map.put(tempgtf.getSpecificAttrbute("gene_id"), tempstr);
        }
    
     return map;
}


    public static void main(String[] args) {
//        ClassLoader classLoader = getClass.getClassLoader();
//        HashMap<String,String> ensemblemap= ReadEnsembleMapfile.getEnsembleMap(this.getClass().getResource("src\\Multifile2Matrix\\CombineRSEMmatrix\\ensembleGENEmapfile").getFile());
    
    }
}
