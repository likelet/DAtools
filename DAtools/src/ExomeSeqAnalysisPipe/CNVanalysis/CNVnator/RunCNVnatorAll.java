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
 * <p>
 * RunCNVnatorAll</p>
 * <p>
 * Created on 2015-11-2 20:54:47</p>
 * <p>
 * Author Email: zhaoqi3@mail2.sysu.edu.cn</p>
 *
 * @author ZHAO Qi
 * @date 2015-11-2 20:54:47
 * @version java 1.6.0
 * @version
 */
public class RunCNVnatorAll implements Runnable {

    private String[] chrom = {"chr1", "chr2", "chr3", "chr4", "chr5", "chr6", "chr7", "chr8", "chr9", "chr10", "chr11", "chr12", "chr13", "chr14", "chr15", "chr16", "chr17", "chr18", "chr19", "chr20", "chr21", "chr22", "chrX", "chrY", "chrM"};
    private String bamfile;
    private int binsize = 100;
    private String genomepath = "genome.fa";

    public RunCNVnatorAll(String bamfile) {
        this.bamfile = bamfile;
    }

    @Override
    public void run() {
        Process process = null;
        String rootfilestr = "";
        String chromstr = "";
        String finename = bamfile.substring(0, bamfile.length() - 4);
        for (int i = 0; i < chrom.length; i++) {
            rootfilestr += finename + "_" + chrom[i] + ".root ";
            chromstr += chrom[i] + " ";
        }

        // run samtools 
        try {

            int random = (int) (10000000 * Math.random());
            String mergedrootname = finename + ".root";

            String tempfilename = finename + "_" + random + "_mergedroot.sh";
            File tempfile = new File(tempfilename);
//            tempfile.deleteOnExit();
            FileWriter fw = new FileWriter(tempfile);
            fw.append("#!/bin/sh\n");
            fw.append("cnvnator -root " + mergedrootname + " -genome " + genomepath + " -chrom " + chromstr + "-merge " + rootfilestr + "\n");
            fw.append("cnvnator -root " + mergedrootname + " -genome " + genomepath + " -chrom " + chromstr + "-his " + binsize + "\n");
            fw.append("cnvnator -root " + mergedrootname + " -genome " + genomepath + " -chrom " + chromstr + "-stat " + binsize + "\n");
            fw.append("cnvnator -root " + mergedrootname + " -genome " + genomepath + " -chrom " + chromstr + "-partition " + binsize + "\n");
            fw.append("cnvnator -root " + mergedrootname + " -genome " + genomepath + " -chrom " + chromstr + " -call " + binsize + " > " + finename + "_cnv_callresult.txt\n");
            fw.close();

            process = Runtime.getRuntime().exec("chmod 755 " + tempfile.getAbsolutePath());
            process = Runtime.getRuntime().exec("/bin/sh ./" + tempfilename);
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
            System.out.println(bamfile + " run cnvnator left finished!");
        } catch (IOException ex) {
            Logger.getLogger(RunCNVnatorExtractRead.class.getName()).log(Level.SEVERE, null, ex);
        } catch (InterruptedException ex) {
            Logger.getLogger(RunCNVnatorExtractRead.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

}
