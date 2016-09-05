/*
 * To storage the method called by class
 */

package RPKM_saturation_M;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

/**
 *
 * @author ZHAO Qi
 * @date 2014-5-10 14:24:22
 * @version 1.6.0
 */
public class Function {
    public static GeneRPKMoutUnit GeneRPKMoutUnitFormat(String bedline){
        String[] strArray =bedline.split("\t");
        if(strArray.length==12){
             GeneRPKMoutUnit geneRpkmUnit=new GeneRPKMoutUnit( strArray[0],Integer.parseInt(strArray[1]),Integer.parseInt(strArray[2]),strArray[3],Double.parseDouble(strArray[4]),strArray[5],Integer.parseInt(strArray[6]),Integer.parseInt(strArray[7]),strArray[8],Integer.parseInt(strArray[9]),strArray[10],strArray[11]);
             return geneRpkmUnit;
        }else {
            return null;
        }    
    }
   
    
       public static HashMap<String,ArrayList<GeneRPKMoutUnit>> getChr2GeneRPKMoutUnitMap(String bedfile) throws FileNotFoundException, IOException{
         HashMap<String,ArrayList<GeneRPKMoutUnit>> chr2GeneRPKMoutUnitMap=new  HashMap<String,ArrayList<GeneRPKMoutUnit>>();
        
        
        BufferedReader br = new BufferedReader(new FileReader(new File(bedfile)));
        while (br.ready()) {    
            String bedline=br.readLine().trim();
            GeneRPKMoutUnit bd =Function.GeneRPKMoutUnitFormat(bedline);
            if(chr2GeneRPKMoutUnitMap.keySet().contains(bd.getChrom())){
                chr2GeneRPKMoutUnitMap.get(bd.getChrom()).add(bd);
            }else{
                ArrayList<GeneRPKMoutUnit> bedlist=new ArrayList<GeneRPKMoutUnit>();
                bedlist.add(bd);
                chr2GeneRPKMoutUnitMap.put(bd.getChrom(), bedlist);
            }    
        }
        br.close();
        return chr2GeneRPKMoutUnitMap;
    }
  
}
