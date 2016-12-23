/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package ExomeSeqAnalysisPipe.ConvertDataFormat;

import ExomeSeqAnalysisPipe.Var2scan2ICGCformat.VarscanSomaticFormat;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

/**
 *
 * @author ZHAO Qi
 * @date 2015-7-29 17:03:29
 * @version 1.6.0
 */
public class Varscan2Annovar {

    public static void Varscan2Annovar(String type, String varscanSomaticfile, String AnnovarFile) {
        //ArrayList<VarscanSomaticFormat> templist = new ArrayList<VarscanSomaticFormat>();
        BufferedReader br = null;
        PrintWriter outFile = null;
        try {
            br = new BufferedReader(new FileReader(new File(varscanSomaticfile)));
            br.readLine();//headline
            outFile = new PrintWriter(AnnovarFile);
            //outFile = new PrintWriter(varscanSomaticfile + ".anr");
            String str[];
            int count = 0;
            while (br.ready()) {
               
                str = br.readLine().split("\t");
                //VarscanSomaticFormat vsf = new VarscanSomaticFormat(str[0], str[1], str[2], str[3], str[4], str[5], str[6], str[7], str[8], str[9], str[10], str[11], str[12], str[13], str[14], str[15], str[16], str[17], str[18], str[19], str[20], str[21], str[22]);
                //templist.add(vsf);
                if((type.contains("-g") && str[12].startsWith("Germline")) || (type.contains("-s") && str[12].startsWith("Somatic")))
                {
                    String anrStr = String.valueOf(str[0].replace("chr", "") + "\t" + str[1] + "\t") +str[1]+ "\t" + str[2] + "\t" + str[3] + "\t";
                    StringBuilder comment = new StringBuilder();
                    for(int i = 4; i < str.length; i++)
                    comment.append(";").append(str[i]);
                    anrStr = anrStr + "\t" + comment.toString().substring(1);
                    outFile.println(anrStr);
                }
            }
            br.close();
            outFile.close();
        } catch (FileNotFoundException ex) {
            System.out.println(varscanSomaticfile + " is not found! please check your filepath ");
        } catch (IOException ex) {
            System.out.println("IO error");
        }
        //return templist;

    }

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
    
    /*
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
*/
    
}
