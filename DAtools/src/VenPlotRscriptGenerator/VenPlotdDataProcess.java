/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package VenPlotRscriptGenerator;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.logging.Level;
import java.util.logging.Logger;
import pub.SpecialException;

/**
 *
 * @author ZHAO Qi
 * @date 2014-3-8 21:11:54
 * @version 1.6.0
 */
public class VenPlotdDataProcess {
    
    private final String datafile;

    public VenPlotdDataProcess( String datafile) {
       
        this.datafile = datafile;
    }


    
    public HashMap<String ,ArrayList> dataProcess() throws SpecialException{
       HashMap<String ,ArrayList> tempmap = new HashMap<String ,ArrayList>();
            HashSet groupSet = new HashSet();
        try {
            BufferedReader br = new BufferedReader(new FileReader(new File(this.datafile)));
            while (br.ready()) {     
                String[] temstr=br.readLine().split("\t");
                if(temstr.length!=2){
                    throw new SpecialException("format error, the sep should be \"tab\"! please check your data format!");
                }else if(groupSet.add(temstr[1])){
                    ArrayList list1=new ArrayList();
                    list1.add(temstr[0]);
                    tempmap.put(temstr[1], list1);     
                }else{
                    tempmap.get(temstr[1]).add(temstr[0]);
                }
            }
            br.close();
           
        } catch (IOException ex) {
            Logger.getLogger(VenPlotdDataProcess.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        if(tempmap.size()>5||tempmap.size()<=1){
        throw new SpecialException(" group error , the max group number is 5,the minimun group number is 2! please check your data");
    }
         return tempmap;
    }
}
