/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ExomeSeqAnalysisPipe.RunCrest;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import pub.Tools;

/**
 * <p>
 * RunCrestExtractSclipFinal</p>
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
public class RunCrestExtractSclipFinal {

    private String bamfile;
    private String genomepath;
    private String extractSClipPath;
    private int thenum = Tools.thread;
    private String[] chrom = {"chr1", "chr2", "chr3", "chr4", "chr5", "chr6", "chr7", "chr8", "chr9", "chr10", "chr11", "chr12", "chr13", "chr14", "chr15", "chr16", "chr17", "chr18", "chr19", "chr20", "chr21", "chr22", "chrX", "chrY", "chrM"};

    public RunCrestExtractSclipFinal(String bamfile, String genomepath, String extractSClipPath) {
        this.bamfile = bamfile;
        this.genomepath = genomepath;
        this.extractSClipPath = extractSClipPath;
        this.FGmutiProcess();
    }

      public RunCrestExtractSclipFinal(String bamfile, String genomepath, String extractSClipPath,int num) {
        this.bamfile = bamfile;
        this.genomepath = genomepath;
        this.extractSClipPath = extractSClipPath;
        thenum=num;
        this.FGmutiProcess();
    }

    
    
    
    public void FGmutiProcess() {
        ExecutorService pool = Executors.newFixedThreadPool(thenum);//creat a new thread pool
        for (int i = 0; i < chrom.length; i++) {
            String chromstr = chrom[i];
            //run extract thread
            RunCrestExtractThread singleFileCrestrThread = new RunCrestExtractThread(bamfile, genomepath, extractSClipPath,chrom[i]);
            pool.submit(singleFileCrestrThread);
        }
        pool.shutdown();
        // wait for termination
        try {
            pool.awaitTermination(Long.MAX_VALUE, TimeUnit.DAYS);
        } catch (InterruptedException ex) {
        }

    }
}
