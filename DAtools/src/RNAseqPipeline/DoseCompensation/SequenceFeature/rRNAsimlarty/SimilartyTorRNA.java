/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package RNAseqPipeline.DoseCompensation.SequenceFeature.rRNAsimlarty;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedHashMap;
import java.util.logging.Level;
import java.util.logging.Logger;
import pub.Tools;

/**
 *
 * @author zhengyueyuan
 */
public class SimilartyTorRNA {
    private LinkedHashMap<String,Double> blastResultMap =new LinkedHashMap();
    private String fastafile;
    private String blastnpath="blastn";
    private int thread=Tools.thread;

    public SimilartyTorRNA(String fastafile) {
        this.fastafile = fastafile;
        this.process();
    }
    
     public SimilartyTorRNA(String fastafile,int thread) {
        this.fastafile = fastafile;
        this.thread=thread;
        this.process();
    }


    public SimilartyTorRNA(String fastafile, String blastnpath) {
        this.fastafile = fastafile;
        this.blastnpath = blastnpath;
        this.process();
    }
    public SimilartyTorRNA(String fastafile, String blastnpath,int thread) {
        this.fastafile = fastafile;
        this.blastnpath = blastnpath;
        this.thread=thread;
        this.process();
    }
    

    
    private void process() {
        try {
//            Process blastn = Runtime.getRuntime().exec(this.blastnpath+" -h");
            Process blastn = Runtime.getRuntime().exec(this.blastnpath+" -db ref_rRNA -num_threads "+thread+" -query "+fastafile+" -num_alignments 2 -evalue 1e-5 -outfmt 6 ");
            blastn.waitFor();
            BufferedReader br = new BufferedReader(new InputStreamReader(blastn.getInputStream()));
            while (br.ready()) {
                String data = br.readLine();
//                System.out.println(data);
                String[] dataArr =data.split("\t");
                String query = dataArr[0];
                double bitSocre = Double.parseDouble(dataArr[10]);
                if(!blastResultMap.containsKey(query)){
                    blastResultMap.put(query, bitSocre);
                }
            }
        } catch (IOException ex) {
            Logger.getLogger(SimilartyTorRNA.class.getName()).log(Level.SEVERE, null, ex);
        } catch (InterruptedException ex) {
            Logger.getLogger(SimilartyTorRNA.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    public LinkedHashMap<String,Double> getBlastResultMap(){
        return blastResultMap;
    }
    
    public static void main(String[] args) {
        SimilartyTorRNA bs = new SimilartyTorRNA("test.fa");
        LinkedHashMap<String,Double> dataMap =bs.getBlastResultMap();
        for(String key : dataMap.keySet()){
            System.out.println(key+"\t"+dataMap.get(key));
        }
    }
}
