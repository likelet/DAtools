/*
 * Motif file is tab-delimed file with two columns inside
 motifname  motif1  motif2
 */

package MotifSearch_H.Forpeipei;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

/**
 * <p>ReadMotifFile</p>
 * <p>Created on 2015-11-28 10:38:47</p>
 * <p>Author Email: zhaoqi3@mail2.sysu.edu.cn</p>
 * @author ZHAO Qi
 * @date 2015-11-28 10:38:47
 * @version java 1.6.0
 * @version 
 */
public class ReadMotifFile {
public static ArrayList<motif> getmotiflist(String motiffile){
    ArrayList<motif> motiflist=new ArrayList<motif>();
    BufferedReader br = null;
    try {
        br = new BufferedReader(new FileReader(new File(motiffile)));
        String[] str;
        while (br.ready()) {
            str = br.readLine().split("\t");
            if(str.length==2){
                motif mf=new motif(str[0],str[1]);
                motiflist.add(mf);
            }else if(str.length>2){
                for (int i = 1; i < str.length; i++) {
                   motif mf=new motif(str[0],str[i]);
                motiflist.add(mf); 
                }
            }
            
            
        }
        br.close();
    } catch (FileNotFoundException ex) {
        System.out.println(motiffile + " is not found! please check your filepath ");
    } catch (IOException ex) {
        System.out.println("IO error");
    }
    return motiflist;
} 
}
