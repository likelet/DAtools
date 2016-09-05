/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package KeggAnnotation.PlotKegg;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

/**
 *
 * @author ZHAO Qi
 * @date 2014-10-30 10:54:55
 * @version 1.6.0
 */
public class keggmapConfigParse {
public static ArrayList<keggmapConfig> geneExpressfileParser(String expfile) throws FileNotFoundException, IOException{
            ArrayList<keggmapConfig> keggmapConfiglist=new ArrayList<keggmapConfig>();
            BufferedReader br = new BufferedReader(new FileReader(new File(expfile)));
            String tempstr="";
            while (br.ready()) {    
                tempstr=br.readLine().trim();
                String[] str=tempstr.split("\t");
                keggmapConfig ge=new keggmapConfig(str[0],str[1],str[2],str[3]);
                keggmapConfiglist.add(ge);
            }
            br.close();
            return keggmapConfiglist;
        }


}
