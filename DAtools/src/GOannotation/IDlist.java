/*
 * read the IDfile into IDlist
 */
package GOannotation;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author ZHAO Qi
 * @version jdk 1.7.0
 */
public class IDlist {
    public static HashSet<String> IDlist(String IDfile) throws IOException{
        HashSet<String> list = new HashSet<String>();
        try {            
            BufferedReader br = new BufferedReader(new FileReader(new File(IDfile)));
            //FileWriter fw = new FileWriter(outputFile);
            while (br.ready()) {
                String str = br.readLine().trim();
                list.add(str);
            }
           //fw.flush();
            br.close();
        } catch (FileNotFoundException ex) {
            
        }
        
        return list;
    }
    
    public static String IDstr(String IDfile) {
        HashSet <String> strlist=new HashSet<String>();
        String tempstr ="";
         try {            
            BufferedReader br = new BufferedReader(new FileReader(new File(IDfile)));
            //FileWriter fw = new FileWriter(outputFile);
            while (br.ready()) {
                String str = br.readLine().trim();
               // System.out.println(str);
                if (strlist.add(str)&&!tempstr.equals("")) {
                    tempstr=tempstr+"\r\n"+str;
                }else if(strlist.add(str)&&tempstr.equals("")){
                    tempstr=str;
            }
                
            }
           //fw.flush();
            br.close();
        } catch (IOException ex) {
            Logger.getLogger(IDlist.class.getName()).log(Level.SEVERE, null, ex);
        } 
        return tempstr;
        
    }
    
    public static void main(String[] args)  {
            System.out.println(IDlist.IDstr("gui/GOanalysis/src/exmaplegenelist.txt"));
        
    }
}
