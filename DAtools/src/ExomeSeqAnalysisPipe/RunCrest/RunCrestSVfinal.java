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
 * <p>RunCrestSVfinal</p>
 * <p>Created on 2015-10-31 16:25:36</p>
 * <p>Author Email: zhaoqi3@mail2.sysu.edu.cn</p>
 * @author ZHAO Qi
 * @date 2015-10-31 16:25:36
 * @version java 1.6.0
 * @version 
 */
public class RunCrestSVfinal {
private String tumorbamfile;
    private String germlinebamfile;
    private String genomepath;
    private String genomebit;
    private String crestSVpath;
    private String coverfile;
    private String hostname;
    private int thenum = Tools.thread;
    private String[] chrom = {"chr1", "chr2", "chr3", "chr4", "chr5", "chr6", "chr7", "chr8", "chr9", "chr10", "chr11", "chr12", "chr13", "chr14", "chr15", "chr16", "chr17", "chr18", "chr19", "chr20", "chr21", "chr22", "chrX", "chrY", "chrM"};

    public RunCrestSVfinal(String tumorbamfile, String germlinebamfile, String genomepath, String genomebit, String crestSVpath, String coverfile, String hostname,int num) {
        this.tumorbamfile = tumorbamfile;
        this.germlinebamfile = germlinebamfile;
        this.genomepath = genomepath;
        this.genomebit = genomebit;
        this.crestSVpath = crestSVpath;
        this.coverfile = coverfile;
        this.hostname = hostname;
        this.thenum=num;
        this.FGmutiProcess();
    }
    
    public RunCrestSVfinal(String tumorbamfile, String genomepath, String genomebit, String crestSVpath, String coverfile, String hostname,int num) {
        this.tumorbamfile = tumorbamfile;
        this.genomepath = genomepath;
        this.genomebit = genomebit;
        this.crestSVpath = crestSVpath;
        this.coverfile = coverfile;
        this.hostname = hostname;
        this.thenum=num;
        this.FGmutiProcess();
    }
    
    
    
    public void FGmutiProcess() {
        ExecutorService pool = Executors.newFixedThreadPool(thenum);//creat a new thread pool
        for (int i = 0; i < chrom.length; i++) {
            String chromstr = chrom[i];
            //run extract thread
            if (this.germlinebamfile != null) {
                RunCrestSVthread singleFileCrestrThread = new RunCrestSVthread(tumorbamfile, genomepath, genomebit, crestSVpath, coverfile, chrom[i], hostname);
                pool.submit(singleFileCrestrThread);
            } else {
                RunCrestSVthread singleFileCrestrThread = new RunCrestSVthread(tumorbamfile,germlinebamfile, genomepath, genomebit, crestSVpath, coverfile, chrom[i], hostname);
                pool.submit(singleFileCrestrThread);
            }

        }
        pool.shutdown();
        // wait for termination
        try {
            pool.awaitTermination(Long.MAX_VALUE, TimeUnit.DAYS);
        } catch (InterruptedException ex) {
        }

    }
}
