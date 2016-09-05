/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ExomeSeqAnalysisPipe.clusterFeatureGenerated;

import ExomeSeqAnalysisPipe.FilelistReader;
import ExomeSeqAnalysisPipe.VCFrecord;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import java.util.logging.Level;
import java.util.logging.Logger;
import pub.Tools;
import pub.txtReader;


/**
 *
 * @author ZHAO Qi
 * @date 2014-2-28 16:04:34
 * @version 1.6.0
 */
public class FeatureGeneraterFinal {

    private final HashMap<String, LinkedList<VCFrecord>> getSampleVcfMap;
    public final HashMap<String, FAIterm> Chrom2FAImap;
    public final int BIN;
    public int TREAD_NUM=Tools.thread;
    public File outfile;
    public ArrayList<String> outlist = new ArrayList<String>();
    public final HashMap index2sampleMap;

//    public FeatureGeneraterFinal(String filedir, String faifile,int bin,int TreadNum) {
//        this.getSampleVcfMap = FilelistReader.getSampleVcfMap(filedir);
//        this.Chrom2FAImap = new FAItermReader(faifile).getChrom2FAImap();
//        System.out.println("initialized finished!");
//        this.BIN=bin;
//        this.TREAD_NUM=TreadNum;
//        this.FGmutiProcess();
//    }
    public FeatureGeneraterFinal(String filedir, String faifile, String mapfile, int bin, int TreadNum) throws FileNotFoundException, IOException {
        this.index2sampleMap = txtReader.getMap(mapfile);
        System.out.println(index2sampleMap.size());
        this.getSampleVcfMap = FilelistReader.getSampleVcfMap(filedir);
        this.Chrom2FAImap = new FAItermReader(faifile).getChrom2FAImap();
        System.out.println("initialized finished!");
        this.BIN = bin;
        this.TREAD_NUM = TreadNum;
        this.FGmutiProcess();
    }

    public void FGmutiProcess() {
        ExecutorService pool = Executors.newFixedThreadPool(TREAD_NUM);//creat a new thread pool
        for (Iterator it = getSampleVcfMap.keySet().iterator(); it.hasNext();) {
            String vcffilepath = (String) it.next();

            FeatureGeneraterThread singleFileCalculatorThread = new FeatureGeneraterThread(vcffilepath, this);
            pool.submit(singleFileCalculatorThread);
        }
        pool.shutdown();
        // wait for termination
        try {
            pool.awaitTermination(Long.MAX_VALUE, TimeUnit.DAYS);
        } catch (InterruptedException ex) {
        }

    }

    public void print(String outfile) {
        {
            FileWriter fw = null;
            try {
                fw = new FileWriter(outfile);
                for (int i = 0; i < this.outlist.size(); i++) {
                    String s = outlist.get(i);

                    fw.append(s);
                }
                fw.flush();
                fw.close();

            } catch (IOException ex) {
                Logger.getLogger(FeatureGeneraterFinal.class.getName()).log(Level.SEVERE, null, ex);
            }
        }

    }

    public static void main(String[] args) throws IOException {
        //new FeatureGeneraterFinal(args[0],args[1],Integer.parseInt(args[2]),Integer.parseInt(args[3])).print(args[4]);
        new FeatureGeneraterFinal("F:\\mywork\\project\\Peng\\snpsatustFinal\\1", "F:\\mywork\\project\\Peng\\ecoli.fa.fai", "F:\\mywork\\project\\Peng\\samplemapping.txt", 1000, 5).print("F:\\mywork\\project\\Peng\\snpstatfinal1.result");
    }
}
