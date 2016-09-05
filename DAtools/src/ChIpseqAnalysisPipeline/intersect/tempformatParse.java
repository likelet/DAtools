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
import java.util.HashMap;
import java.util.HashSet;

/**
 *
 * @author ZHAO Qi
 * @date 2015-4-18 11:32:40
 * @version 1.6.0
 */
public class tempformatParse {
 public static ArrayList<tempFromat> getpeaklist(String peakfile){
        ArrayList<tempFromat> peaklist=new ArrayList<tempFromat> ();
        BufferedReader br = null;
        try {
            br = new BufferedReader(new FileReader(new File(peakfile)));
            String str[];
            while (br.ready()) {
                str = br.readLine().split("\t");
                tempFromat mb=new tempFromat(str[0],Integer.parseInt(str[1]),Integer.parseInt(str[2]),str[3],str[4]);
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
 
 public static  HashMap<String,ArrayList<tempFromat>> chr2peakList(String peakfile){
        HashMap<String,ArrayList<tempFromat>> chr2peakList=new  HashMap<String,ArrayList<tempFromat>> (); 
        HashSet<String> chrlist=new HashSet<String>();
        BufferedReader br = null;
        try {
            br = new BufferedReader(new FileReader(new File(peakfile)));
            String str[];
            while (br.ready()) {
                str = br.readLine().split("\t");
                tempFromat mb=new tempFromat(str[0],Integer.parseInt(str[1]),Integer.parseInt(str[2]),str[3],str[4]);
                if(chrlist.add(str[0])){
                    ArrayList<tempFromat> tflist=new ArrayList<tempFromat>();
                    tflist.add(mb);
                    chr2peakList.put(str[0], tflist);
                }else{
                    chr2peakList.get(str[0]).add(mb);
                }
            }
            br.close();
        } catch (FileNotFoundException ex) {
            System.out.println(peakfile + " is not found! please check your filepath ");
        } catch (IOException ex) {
            System.out.println("IO error");
        }
        return chr2peakList;
        
        
    } 
}
