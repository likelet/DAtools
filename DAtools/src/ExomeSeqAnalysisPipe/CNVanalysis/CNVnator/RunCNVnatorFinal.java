/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ExomeSeqAnalysisPipe.CNVanalysis.CNVnator;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import pub.Tools;

/**
 * <p>
 RunCNVnatorFinal</p>
 * <p>
 * Created on 2015-10-29 16:15:31</p>
 * <p>
 * Author Email: zhaoqi3@mail2.sysu.edu.cn</p>
 *
 * @author ZHAO Qi
 * @date 2015-10-29 16:15:31
 * @version java 1.6.0
 * @version
 */
public class RunCNVnatorFinal {

    private String bamfile;

    private int thenum = Tools.thread;
    private String[] chrom = {"chr1", "chr2", "chr3", "chr4", "chr5", "chr6", "chr7", "chr8", "chr9", "chr10", "chr11", "chr12", "chr13", "chr14", "chr15", "chr16", "chr17", "chr18", "chr19", "chr20", "chr21", "chr22", "chrX", "chrY", "chrM"};

   

      public RunCNVnatorFinal(String bamfile, int num) {
        this.bamfile = bamfile;
        thenum=num;
        this.FGmutiProcess();
    }   
      //runMerge&call cnv
      public RunCNVnatorFinal(String bamfile) {
        this.bamfile = bamfile;
        this.runmergeAndCNV();
    }
    
    
    
    public void FGmutiProcess() {
        ExecutorService pool = Executors.newFixedThreadPool(thenum);//creat a new thread pool
        for (int i = 0; i < chrom.length; i++) {
            String chromstr = chrom[i];
            //run extract thread
            RunCNVnatorExtractRead singleFileCrestrThread = new RunCNVnatorExtractRead(bamfile, chromstr);
            pool.submit(singleFileCrestrThread);
        }
        pool.shutdown();
        // wait for termination
        try {
            pool.awaitTermination(Long.MAX_VALUE, TimeUnit.DAYS);
        } catch (InterruptedException ex) {
        }

    }
    
    public void runmergeAndCNV() {
        ExecutorService pool = Executors.newFixedThreadPool(1);
        RunCNVnatorAll laststep = new RunCNVnatorAll(bamfile);
        pool.submit(laststep);
        pool.shutdown();
        try {
            pool.awaitTermination(Long.MAX_VALUE, TimeUnit.DAYS);
        } catch (InterruptedException ex) {
        }

    }
}
