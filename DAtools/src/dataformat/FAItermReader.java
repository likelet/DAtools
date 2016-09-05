/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package dataformat;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.LinkedHashMap;

/**
 *
 * @author ZHAO Qi
 * @date 2014-2-28 10:51:58
 * @version 1.6.0
 */
public class FAItermReader {
    private LinkedHashMap<String,FAIterm> Chrom2FAImap=new LinkedHashMap<String,FAIterm>();
    private String faifile;

  
    public FAItermReader(String faifile) {
        this.faifile = faifile;
        this.analysis();
    }
    
    
    
    public void analysis(){ 
        BufferedReader br=null;
        try{
         br= new BufferedReader(new FileReader(new File(faifile)));
         String[] tempstr;
            while (br.ready()) {
                 tempstr=br.readLine().trim().split("\t");
                FAIterm fai = new FAIterm(tempstr[0], Long.parseLong(tempstr[1]), Long.parseLong(tempstr[2]), Long.parseLong(tempstr[3]), Long.parseLong(tempstr[4]));
                this.Chrom2FAImap.put(tempstr[0], fai);
            }
            br.close();
        } catch (IOException ex) {
            System.out.println("Fai file IO error");
        } catch (NumberFormatException ex) {
            System.out.println("NumberFormatException error");
        }

    }

    public LinkedHashMap<String, FAIterm> getChrom2FAImap() {
        return Chrom2FAImap;
    }
}
