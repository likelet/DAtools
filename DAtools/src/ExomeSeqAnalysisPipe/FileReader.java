/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ExomeSeqAnalysisPipe;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

/**
 *
 * @author Yueyuan
 */
public class FileReader {
   ArrayList<ChromAndPos> infolist=new ArrayList(); 
   ArrayList<String> all_datalist=new ArrayList();
   FileReader(String file){
       try{
           BufferedReader br = new BufferedReader(new java.io.FileReader(new File(file)));
           this.infolist=new ArrayList();
           this.all_datalist=new ArrayList();
           while (br.ready()) { 
               String dataStr = br.readLine().trim();
               if(!dataStr.substring(0,1).equals("#")){
                   String[] data=dataStr.split("\t");
                   String chr=data[0];
                   String pos=data[1];
                   infolist.add(new ChromAndPos(chr,pos));
                   all_datalist.add(dataStr);
               }
           }
       }catch (IOException ex) {
    }         
   }

    
   public ArrayList<ChromAndPos> getInfolist(){
       return infolist;
   }
   public ArrayList<String> getAll_datalist(){
       return all_datalist;
   }
}
