/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package ChIpseqAnalysisPipeline.intersect;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

/**
 *
 * @author ZHAO Qi
 * @date 2015-4-18 11:17:57
 * @version 1.6.0
 */
public class MacPeakparse {
    public static ArrayList<MACpeakBed> getpeaklist(String peakfile){
        ArrayList<MACpeakBed> peaklist=new ArrayList<MACpeakBed> ();
        BufferedReader br = null;
        try {
            br = new BufferedReader(new FileReader(new File(peakfile)));
            String str[];
            while (br.ready()) {
                str = br.readLine().split("\t");
                MACpeakBed mb=new MACpeakBed(str[0],Integer.parseInt(str[1]),Integer.parseInt(str[2]),str[3],Double.parseDouble(str[4]));
                peaklist.add(mb);
            }
            br.close();
        } catch (FileNotFoundException ex) {
            System.out.println(peakfile + " is not found! please check your filepath ");
        } catch (IOException ex) {
            System.out.println("IO error");
        }
        return peaklist;
        
        
    } 
    
}
