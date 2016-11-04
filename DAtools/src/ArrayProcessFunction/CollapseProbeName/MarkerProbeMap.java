/*
 * marker Probe file format
M   p1
M   p2
M2  p3
M2  p4
m3  p5
 */

package ArrayProcessFunction.CollapseProbeName;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Administrator
 * @since 2016-11-3
 * @coding time 14:46:51
 * @author Qi Zhao
 */
public class MarkerProbeMap {
    public static HashMap<String,ArrayList<String>> getMarkerProbeMap(String mapfile){
        System.out.println("Parsing marker and probe Mapfile ： " + mapfile);
        HashMap<String,ArrayList<String>> resultmap=new HashMap<String,ArrayList<String>>();
        BufferedReader br = null;
        HashSet<String> tempset=new HashSet<String>();
        try {
            br = new BufferedReader(new java.io.FileReader(new File(mapfile)));
            try {
                while (br.ready()) {
                    String[] dataStr = br.readLine().trim().split("\t");
                    if(tempset.add(dataStr[0])){
                        ArrayList<String> tempa =new ArrayList<String>();
                        tempa.add(dataStr[1]);
                        resultmap.put(dataStr[0], tempa);
                    }else{
                        resultmap.get(dataStr[0]).add(dataStr[1]);
                    }
                    
                }
            } catch (IOException ex) {
                Logger.getLogger(MarkerProbeMap.class.getName()).log(Level.SEVERE, null, ex);
            }
        } catch (FileNotFoundException ex) {
            Logger.getLogger(MarkerProbeMap.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                br.close();
            } catch (IOException ex) {
                Logger.getLogger(MarkerProbeMap.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
          System.out.println("Parsing marker and probe Mapfile finished！ ： " + mapfile);
        return resultmap;
    }
    
}
