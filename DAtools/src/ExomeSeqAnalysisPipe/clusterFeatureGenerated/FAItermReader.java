/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ExomeSeqAnalysisPipe.clusterFeatureGenerated;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.HashMap;

/**
 *
 * @author ZHAO Qi
 * @date 2014-2-28 10:51:58
 * @version 1.6.0
 */
public class FAItermReader {
    private HashMap<String,FAIterm> Chrom2FAImap=new HashMap<String,FAIterm>();
    private String faifile;

    public FAItermReader() {
    }

    public FAItermReader(String faifile) {
        this.faifile = faifile;
        this.analysis();
    }
    
    
    
    public void analysis(){ 
        try{
        BufferedReader br = new BufferedReader(new FileReader(new File(faifile)));
        while (br.ready()) {   
            String[] tempstr=br.readLine().trim().split("\t");
            FAIterm fai=new FAIterm(tempstr[0],Integer.parseInt(tempstr[1]),Integer.parseInt(tempstr[2]),Integer.parseInt(tempstr[3]),Integer.parseInt(tempstr[4]));
            this.Chrom2FAImap.put(tempstr[0], fai);
        }
        br.close();
        } catch (Exception ex){
            System.out.println("IO error");
        }
        
        
    }

    public HashMap<String, FAIterm> getChrom2FAImap() {
        return Chrom2FAImap;
    }
}
