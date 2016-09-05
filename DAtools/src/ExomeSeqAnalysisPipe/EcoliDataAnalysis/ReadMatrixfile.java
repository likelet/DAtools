/*
 * Two matrix file ;
 */

package ExomeSeqAnalysisPipe.EcoliDataAnalysis;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author ZHAO Qi
 * @date 2015-4-1 16:58:57
 * @version 1.6.0
 */
public class ReadMatrixfile {
    // key: snp information ;value sample2issnp map
    HashMap<String,HashMap<String,String>> snpsampleStatusmap=new  HashMap<String,HashMap<String,String>>();
    ArrayList<String> samplenames=new ArrayList<String>();
    String headerstr="";
    //key: antibio . value: sample2isanti map
    HashMap<String,HashMap<String,String>> antibioStatusmap=new  HashMap<String,HashMap<String,String>>();
    public ReadMatrixfile(String snpmatrix,String antibioMapmatrix,String output){
        this.initialize(snpmatrix, antibioMapmatrix);
        this.process(output);
    }
    
    public void initialize(String snpmatrix,String antibioMapmatrix){
        // initialize antibioStatusmap
        BufferedReader br = null;
        ArrayList<String> antbiolist=new ArrayList<String>();
        String []str=null;
        int mark=0;
        try {
            br = new BufferedReader(new FileReader(new File(antibioMapmatrix)));
            while (br.ready()) {
                if (mark == 0) {
                    headerstr = br.readLine();
                    str = headerstr.split("\t");
                    for (int i = 1; i < str.length; i++) {
                        antbiolist.add(str[i]);
                        HashMap<String, String> tempmap = new HashMap<String, String>();
                        antibioStatusmap.put(str[i], tempmap);
                    }
                } else {
                    str = br.readLine().split("\t");
                    for (int i = 1; i < str.length; i++) {
                        antibioStatusmap.get(antbiolist.get(i-1)).put(str[0].toLowerCase(), str[i]);
                    }

                }
                mark=1;
            }
            br.close();
            
        } catch (FileNotFoundException ex) {
            System.out.println(snpmatrix + " is not found! please check your filepath ");
        } catch (IOException ex) {
            System.out.println("IO error");
        }
        
        // initialize snpsampleStatusmap
        BufferedReader br2 = null;
        
        String []str2=null;

        int mark2=0;
        try {
            br2 = new BufferedReader(new FileReader(new File(snpmatrix)));
            while (br2.ready()) {
                if (mark2 == 0) {
                    str2 = br2.readLine().split("\t");
                    for (int i = 0; i < str2.length; i++) {
                        samplenames.add(str2[i]);
                    }
                } else {
                    str2 = br2.readLine().split("\t");
                    HashMap<String,String> tempmap=new HashMap<String,String>();
                    for (int i = 1; i < str2.length; i++) {
                        
                        tempmap.put(samplenames.get(i).toLowerCase(), str2[i]);
                    }
                    snpsampleStatusmap.put(str2[0],tempmap);
                   
                }
                 mark2 = 1;

            }
            br2.close();
        } catch (FileNotFoundException ex) {
            System.out.println(snpmatrix + " is not found! please check your filepath ");
        } catch (IOException ex) {
            System.out.println("IO error");
        }
        System.out.println("initialized process finished");
    }
    
    public void process(String outfile){
        HashMap<String,String>  temphashmap=null;
//        ArrayList<snpFeatureInformation> resultlist=new ArrayList<snpFeatureInformation>();
        try {
            FileWriter fw = new FileWriter(outfile);
            fw.append(headerstr+"\n");
        
        String tempstr="";
        for (Iterator it = snpsampleStatusmap.keySet().iterator(); it.hasNext();) {
            tempstr=(String) it.next();
            temphashmap=snpsampleStatusmap.get(tempstr);
            snpFeatureInformation snpInfromation=new snpFeatureInformation(tempstr,temphashmap,this.antibioStatusmap);
            fw.append(snpInfromation.print()+"\n");
        }
            fw.flush();
            fw.close();
        } catch (IOException ex) {
            System.out.println("IO error!");
        }
    }
    
    public static void main(String[] args) {
        new ReadMatrixfile("F:\\mywork\\project\\Peng\\SamtoolsResult\\TotalEnronment\\ChisqTest\\snpFrequency.txt","F:\\mywork\\project\\Peng\\SamtoolsResult\\TotalEnronment\\ChisqTest\\antibio.txt","F:\\mywork\\project\\Peng\\SamtoolsResult\\TotalEnronment\\ChisqTest\\result.txt");
    }
}
