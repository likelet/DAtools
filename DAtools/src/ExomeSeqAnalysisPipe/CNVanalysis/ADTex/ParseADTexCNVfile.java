/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package ExomeSeqAnalysisPipe.CNVanalysis.ADTex;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.HashMap;
import java.util.logging.Level;
import java.util.logging.Logger;
import pub.ToolsforCMD;

/**
 *
 * @author Administrator
 * @since 2018-8-1
 * @coding time 15:49:20
 * @author Qi Zhao
 */
public class ParseADTexCNVfile {
    public static HashMap<String, ADTexCNVterm> parseCNVfile(File adtexcnvfile) {
        BufferedReader br=null;
        HashMap<String, ADTexCNVterm> admap=new HashMap<String, ADTexCNVterm>();
        try {
            System.out.println("Parsing " +ToolsforCMD.print_ansi_GREEN(adtexcnvfile.getName()));
            
            br = new BufferedReader(new java.io.FileReader(adtexcnvfile));
            while (br.ready()) {
                String tempstr=br.readLine();
                if(tempstr.startsWith("chr")) continue;
                String[] dataStr = tempstr.trim().split("\t");
                ADTexCNVterm at=new ADTexCNVterm(dataStr[0],dataStr[1],dataStr[2],dataStr[3],dataStr[4],dataStr[5],dataStr[6],dataStr[7],dataStr[8],dataStr[9]);
                admap.put(dataStr[0]+":"+dataStr[1]+"-"+dataStr[2], at);
            }   
        } catch (FileNotFoundException ex) {
            Logger.getLogger(ParseADTexCNVfile.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(ParseADTexCNVfile.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                br.close();
            } catch (IOException ex) {
                Logger.getLogger(ParseADTexCNVfile.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return admap;
    }

}
