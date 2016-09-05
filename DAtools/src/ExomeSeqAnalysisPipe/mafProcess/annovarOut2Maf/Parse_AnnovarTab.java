/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package ExomeSeqAnalysisPipe.mafProcess.annovarOut2Maf;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

/**
 * <p>Parse_AnnovarTab</p>
 * <p>Created on 2015-12-22 17:48:28</p>
 * <p>Author Email: zhaoqi3@mail2.sysu.edu.cn</p>
 * @author ZHAO Qi
 * @date 2015-12-22 17:48:28
 * @version java 1.6.0
 * @version 
 */
public class Parse_AnnovarTab {
    
    public static ArrayList<AnnovarTab> getAnnovarTablist(String inputfile){
        BufferedReader br = null;
        boolean skipheader=true;
        ArrayList<AnnovarTab> annolist=new ArrayList<AnnovarTab>();
        try {
            br = new BufferedReader(new FileReader(new File(inputfile)));
            String[] str ;
            while (br.ready()) {
                str = br.readLine().split("\t");
                if(skipheader){
                    str = br.readLine().split("\t");
                    skipheader=false;
                }
                AnnovarTab at=new AnnovarTab(str[0],str[1],str[2],str[3],str[4],str[5],str[6],str[7],str[8],str[9],str[10],str[11],str[12]);
                annolist.add(at);
            }
            br.close();
        } catch (FileNotFoundException ex) {
            System.out.println(inputfile + " is not found! please check your filepath ");
        } catch (IOException ex) {
            System.out.println("IO error");
        }
        return(annolist);
    }
}
