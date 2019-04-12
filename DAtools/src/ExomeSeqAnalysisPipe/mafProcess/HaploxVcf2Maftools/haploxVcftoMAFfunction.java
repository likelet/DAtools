/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package ExomeSeqAnalysisPipe.mafProcess.HaploxVcf2Maftools;

import htsjdk.samtools.util.CollectionUtil;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.stream.Collectors;

/**
 *
 * @author Administrator
 * @since 2019-4-12
 * @coding time 19:12:11
 * @author Qi Zhao
 */
public class haploxVcftoMAFfunction {
       public static  ArrayList<haploxVcf> ParsehaploxVcf(String vcffile ) throws FileNotFoundException, IOException{
           
           ArrayList<haploxVcf> templist=new ArrayList<haploxVcf> ();
           
           BufferedReader br = new BufferedReader(new java.io.FileReader(new File(vcffile)));
           String tempstr="";
           while (br.ready()) {
               tempstr=br.readLine();
               if(tempstr.startsWith("#")) continue; 
               String[] dataStr = tempstr.trim().split("\t");
               templist.add(new haploxVcf(dataStr[0],dataStr[1],dataStr[2],dataStr[3],dataStr[4],dataStr[5],dataStr[6],dataStr[7],dataStr[8],dataStr[9],dataStr[10]));
               
           }
           return templist;
       }
       public static  haploxVcf strTohaploxVcf(String vstr ) {
           String [] dataStr=vstr.split("\t");
           haploxVcf hvcf=new haploxVcf(dataStr[0],dataStr[1],dataStr[2],dataStr[3],dataStr[4],dataStr[5],dataStr[6],dataStr[7],dataStr[8],dataStr[9],dataStr[10]);
           return hvcf;
       }
       
       public static HashMap<String,String> GetINFORmap(String INFO){
        HashMap<String,String>  informap=new HashMap<String,String> ();
        String [] tempfeature= INFO.split(";");
        for (int i = 0; i < tempfeature.length; i++) {
            if(!tempfeature[i].contains("=")) continue; 
            String [] tempa =tempfeature[i].split("=");
            informap.put(tempa[0], tempa[1]);
        }
        
        return(informap);
    }
    
    
    public static HashMap<String,String> GetNORMAL_FORMATmap(String FORMAT, String NORMAL  ){
        HashMap<String,String>  normalmap=new HashMap<String,String> ();
        String [] formatfeature= FORMAT.split(":");
        String [] normalfeatuue= NORMAL.split(":");
        for (int i = 0; i < formatfeature.length; i++) {
            normalmap.put(formatfeature[i], normalfeatuue[i]);
        }
        return(normalmap);
    }
    
    public static HashMap<String,String> GetTUMOR_FORMATmap(String FORMAT, String TUMOR){
        HashMap<String,String>  normalmap=new HashMap<String,String> ();
        String [] formatfeature= FORMAT.split("\\:");
        String [] tumorfeature= TUMOR.split("\\:");
        for (int i = 0; i < formatfeature.length; i++) {
            normalmap.put(formatfeature[i], tumorfeature[i]);
        }
        return(normalmap);
    }
    
    
    public static String getInfoString(HashMap<String,String> informap){
        final List<String> fields = CollectionUtil.makeList();
        
        ArrayList<String> keylist=new ArrayList<String>(informap.keySet());
        
        
        for (String key:keylist) {
            fields.add(informap.get(key));
        }
        String str = fields.stream().map(String::valueOf).collect(Collectors.joining("\t"));
        return str;
    }
    
     public static String getInfoStringHeader(HashMap<String,String> informap){
        final List<String> fields = CollectionUtil.makeList();
         ArrayList<String> keylist=new ArrayList<String>(informap.keySet());
        for (String key:informap.keySet()) {
            fields.add(key);
        }
        String str = fields.stream().map(String::valueOf).collect(Collectors.joining("\t"));
        return str;
    }
       
       
       
       
       
}
