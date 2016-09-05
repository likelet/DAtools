/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package ExomeSeqAnalysisPipe.RunCrest;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * <p>RunCrestExtractThread</p>
 * <p>Created on 2015-10-29 15:55:37</p>
 * <p>Author Email: zhaoqi3@mail2.sysu.edu.cn</p>
 * @author ZHAO Qi
 * @date 2015-10-29 15:55:37
 * @version java 1.6.0
 * @version 
 */
public class RunCrestExtractThread implements Runnable{
    private String bamfile;
    private String genomepath;
    private String extractSClipPath;
    private String chrom;

    public RunCrestExtractThread(String bamfile, String extractSClipPath, String genomepath,String chrom) {
        this.bamfile = bamfile;
        this.genomepath = genomepath;
        this.extractSClipPath = extractSClipPath;
        this.chrom=chrom;
    }

    @Override
    public void run() {
        Process process = null;
        String tempbamname=bamfile.substring(0, bamfile.length()-4)+"_"+chrom+".bam";
            // run samtools 
            try {
                
        int random=(int) (10000000*Math.random());
        String tempname="temp_runextractSClip"+random+chrom+".sh";
        File tempfile=new File(tempname);
        tempfile.deleteOnExit();
            FileWriter fw = new FileWriter(tempfile);
            fw.append("#!/bin/sh\n");
            fw.append("samtools view -b " + bamfile + " "+ chrom + " > "+tempbamname+"\n");
             fw.append("samtools index "+tempbamname+"\n");
            fw.append("perl "+extractSClipPath+"  -i "+tempbamname+" --ref_genome "+genomepath+"\n");
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
                System.out.println(bamfile + " run samtools extract chrom " + chrom + " finished!");
            } catch (IOException ex) {
                Logger.getLogger(RunCrestExtractThread.class.getName()).log(Level.SEVERE, null, ex);
            } catch (InterruptedException ex) {
                Logger.getLogger(RunCrestExtractThread.class.getName()).log(Level.SEVERE, null, ex);
            }
       
               
    }

}
