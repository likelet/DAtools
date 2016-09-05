/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ExomeSeqAnalysisPipe.snpeffanno;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;

/**
 *
 * @author 媛子
 */
public class GeneFileReader {
    ArrayList<String> geneList=new ArrayList();
    GeneFileReader(String geneFile) throws FileNotFoundException{
        try{
            BufferedReader br=new BufferedReader(new java.io.FileReader(new File(geneFile)));
            while(br.ready()){
                String data=br.readLine();
                geneList.add(data);
            }
        }catch(IOException ex){
            
        }
    }
    
    public ArrayList<String> getGeneList(){
        return geneList;
    }
}
