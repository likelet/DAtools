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
public class FileReaderAllData { 
   ArrayList<String> all_datalist=new ArrayList();
   FileReaderAllData(String file){
       try{
           BufferedReader br = new BufferedReader(new java.io.FileReader(new File(file)));
           this.all_datalist=new ArrayList();
           while (br.ready()) { 
               String dataStr = br.readLine().trim();
                   String[] data=dataStr.split("\t");                                 
                   all_datalist.add(dataStr);
               }
           
       }catch (IOException ex) {
    }         
   } 

   public ArrayList<String> getAll_datalist(){
       return all_datalist;
   }
}
   

