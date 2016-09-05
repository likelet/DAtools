/*
 This application was used for search kmer in sequence
e.g:  atgcgta 
output: atgc 1 1,3
 */

package Sequencefinder;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import pub.Fasta;
import pub.FastaReader;
import pub.Tools;

/**
 *
 * @author ZHAO Qi
 * @date 2015-7-6 18:08:43
 * @version 1.6.0
 */
public class processSearch {
    private ArrayList<OutputFormat> outlist=new ArrayList<OutputFormat> ();
    private LinkedList<Fasta> dbfastalist;
    private String seq="";
    private int kmer=8;
    public int TREAD_NUM=Tools.thread;

    public processSearch(String fastafile, String fa,int kmer) {
        this.dbfastalist = dbfastalist;
        this.seq = fa;
        this.kmer=kmer;
        dbfastalist=new FastaReader(fastafile).getFastaList();
        System.out.println(dbfastalist.size());
        this.getKmerLlist();
        System.out.println(" Initialized success");
        this.process();
    }
    
    public processSearch(LinkedList<Fasta> list, String fa,int kmer) {
        this.dbfastalist = dbfastalist;
        this.seq = fa;
        this.kmer=kmer;
        dbfastalist=list;
        System.out.println(dbfastalist.size());
        this.getKmerLlist();
        System.out.println(" Initialized success");
        this.process();
    }

    public OutputFormat getKmerFromSeq(int start, int end) {
        OutputFormat of = new OutputFormat(seq.substring(start, end), start, end);
        return of;
    }

    private void getKmerLlist() {
//        System.out.println(seq);
        for (int i = 0; i < seq.length() - kmer; i++) {
//            System.out.println(getKmerFromSeq(i, i + kmer).toString());
            outlist.add(getKmerFromSeq(i, i + kmer));
        }
    }

    private void process() {
        OutputFormat tempstr = null;
        ExecutorService pool = Executors.newFixedThreadPool(TREAD_NUM);//creat a new thread pool
        for (int i = 0; i < outlist.size(); i++) {
            processThread test = new processThread(i);
            pool.submit(test);
        }
        pool.shutdown();
        // wait for termination
        try {
            pool.awaitTermination(Long.MAX_VALUE, TimeUnit.DAYS);
        } catch (InterruptedException ex) {
        }

    }

    class processThread implements Runnable {

        private int n;

        public processThread(int n){
            this.n=n;
        }
        public void run() {
             for (int j = 0; j < dbfastalist.size(); j++) {
                if (isContain(outlist.get(n), dbfastalist.get(j))){
                    outlist.get(n).addcount();
                    outlist.get(n).setGenes(dbfastalist.get(j).getPureName());
                }
            }
        }
        
    }
    
    
    
    private boolean isContain(OutputFormat of, Fasta a){
//        System.out.println(of.getSeq());
//        System.out.println(a.getSequence());
        if(a.getSequence().contains(of.getSeq())){
//            System.out.println(true);
            return true;
        }else{
            return false;
        }
    }

    public ArrayList<OutputFormat> getOutlist() {
        return outlist;
    }
    
    public String print(){
        String str="";
        for (int i = 0; i < outlist.size(); i++) {
           str+=outlist.get(i).toString()+"\n";
        }
        return str;
    }
    
    public static void main(String[] args) {
//        Fasta fa=new Fasta(">test","CTTCGTCGGGGGAGGGCGCTCCACCCGGGCTGGAGTTGCAGAGCCCAGCAGATCCCTGCG");
//        System.out.println(fa.toString());
//        System.out.println(fa.getSequence());
        new processSearch("F:\\resouces\\projects\\LHY\\Promoter\\promoter_EPDnew.fasta","CTTCGTCGGGGGAGGGCGCTCCACCCGGGCTGGAGTTGCAGAGCCCAGCAGATCCCTGCG",20);
    }
}
