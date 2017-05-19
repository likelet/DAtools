/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package ExomeSeqAnalysisPipe.Var2scan2ICGCformat;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

/**
 *
 * @author ZHAO Qi
 * @date 2015-7-29 17:03:29
 * @version 1.6.0
 */
public class ParseVarscanClass {

    public static ArrayList<VarscanSomaticFormat> ParseVarscanSomaticFormat(String varscanSomaticfile) {
        ArrayList<VarscanSomaticFormat> templist = new ArrayList<VarscanSomaticFormat>();
        BufferedReader br = null;
        try {
            br = new BufferedReader(new FileReader(new File(varscanSomaticfile)));
            String str[];
            int count = 0;
            while (br.ready()) {
               
                    str = br.readLine().split("\t");
                    VarscanSomaticFormat vsf = new VarscanSomaticFormat(str[0], str[1], str[2], str[3], str[4], str[5], str[6], str[7], str[8], str[9], str[10], str[11], str[12], str[13], str[14], str[15], str[16], str[17], str[18], str[19], str[20], str[21], str[22]);
                    templist.add(vsf);
                

            }
            br.close();
        } catch (FileNotFoundException ex) {
            System.out.println(varscanSomaticfile + " is not found! please check your filepath ");
        } catch (IOException ex) {
            System.out.println("IO error");
        }
        return templist;
    }
    
    //Parse CNV format
     public static ArrayList<VarscanCNVFormat> ParseVarscanCNVFormat(String VarscanCNVFormatfile) {
        ArrayList<VarscanCNVFormat> templist = new ArrayList<VarscanCNVFormat>();
        BufferedReader br = null;
        try {
            br = new BufferedReader(new FileReader(new File(VarscanCNVFormatfile)));
            String str[];
            int count = 0;
            while (br.ready()) {
                if (count == 0) {
                    br.readLine();
                    count++;
                } else {
                    str = br.readLine().split("\t");
                    VarscanCNVFormat vsf = new VarscanCNVFormat(str[0], str[1], str[2], str[3], str[4], str[5], str[6]);
                    templist.add(vsf);
                }

            }
            br.close();
        } catch (FileNotFoundException ex) {
            System.out.println(VarscanCNVFormatfile + " is not found! please check your filepath ");
        } catch (IOException ex) {
            System.out.println("IO error");
        }
        return templist;
    }
    
}
