/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package ExomeSeqAnalysisPipe.CNVanalysis.CNVnator;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * <p>RunCNVnatorExtractRead</p>
 * <p>Created on 2015-10-29 15:55:37</p>
 * <p>Author Email: zhaoqi3@mail2.sysu.edu.cn</p>
 * @author ZHAO Qi
 * @date 2015-10-29 15:55:37
 * @version java 1.6.0
 * @version 
 */
public class RunCNVnatorExtractRead implements Runnable{
    private String bamfile;
    private String chrom;  
    private int binsize=100;
    private String genomepath="genome.fa";
    public RunCNVnatorExtractRead(String bamfile,String chrom) {

        this.bamfile = bamfile;

        this.chrom=chrom;
    }

    @Override
    public void run() {
        Process process = null;
        String finename=bamfile.substring(0, bamfile.length()-4);
        String temproot=finename+"_"+chrom+".root";
            // run samtools 
         try {
                
        int random=(int) (10000000*Math.random());
        String tempname=finename+"_"+random+chrom+".sh";
        File tempfile=new File(tempname);
//        tempfile.deleteOnExit();
            FileWriter fw = new FileWriter(tempfile);
            fw.append("#!/bin/sh\n");
            fw.append("#cnvnator -root "+temproot+" -genome "+genomepath+" -chrom "+chrom+" -tree "+bamfile+"\n");
            fw.append("cnvnator -root "+temproot+" -genome "+genomepath+" -chrom "+chrom+" -his "+binsize+"\n");
            fw.append("cnvnator -root "+temproot+" -genome "+genomepath+" -chrom "+chrom+" -stat "+binsize+"\n");
            fw.append("cnvnator -root "+temproot+" -genome "+genomepath+" -chrom "+chrom+" -partition "+binsize+"\n");
            fw.append("cnvnator -root "+temproot+" -genome "+genomepath+" -chrom "+chrom+" -call "+binsize+" > "+finename+"_"+chrom+"_cnv_callresult.txt\n");
            fw.close();
        
            process = Runtime.getRuntime().exec("chmod 755 " + tempfile.getAbsolutePath());
            process = Runtime.getRuntime().exec("/bin/sh ./"+tempname);
                InputStream errorst = process.getErrorStream();
                int result = process.waitFor();
                if (result == 0) {
                    //System.out.println(" SUCCESS! ");
                } else {
                    BufferedReader br = new BufferedReader(new InputStreamReader(errorst));
                    String errorStr = "";
                    while (br.ready()) {
                        errorStr = br.readLine() + "\r\n";
                    }
                    //System.out.println(errorStr);
                    br.close();
                }
                System.out.println(bamfile + " run cnvnator chrom " + chrom + " finished!");
            } catch (IOException ex) {
                Logger.getLogger(RunCNVnatorExtractRead.class.getName()).log(Level.SEVERE, null, ex);
            } catch (InterruptedException ex) {
                Logger.getLogger(RunCNVnatorExtractRead.class.getName()).log(Level.SEVERE, null, ex);
            }
       
               
    }

}
