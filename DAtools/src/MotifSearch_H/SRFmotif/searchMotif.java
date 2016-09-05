/*
 * MotifType CC(A/T)6GG
 */

package MotifSearch_H.SRFmotif;

import MotifSearch_H.Kmer;
import MotifSearch_H.getKmer;
import java.io.File;
import java.util.ArrayList;
import java.util.LinkedList;
import pub.Fasta;
import pub.FastaReader;

/**
 * <p>searchMotif</p>
 * <p>Created on 2015-10-21 16:35:36</p>
 * <p>Author Email: zhaoqi3@mail2.sysu.edu.cn</p>
 * @author ZHAO Qi
 * @date 2015-10-21 16:35:36
 * @version java 1.6.0
 * @version 
 */
public class searchMotif {

    public searchMotif(String filename,int  length) {
        LinkedList<Fasta> fastalist=new FastaReader(new File(filename)).getFastaList();
        for (int i = 0; i < fastalist.size(); i++) {
            process(fastalist.get(i),length);
        }
    }

    
    
    
    public boolean isMotif(String kmer) {
        if (kmer.startsWith("CC") && kmer.endsWith("GG")) {
            String temp = kmer.substring(2, 7);
            if (temp.contains("C") || temp.contains("G")) {
                return false;
            } else {
                return true;
            } //互补链
        } else if (kmer.startsWith("GG") && kmer.endsWith("CC")) {
            String temp = kmer.substring(2, 7);
            if (temp.contains("C") || temp.contains("G")) {
                return false;
            } else {
                return true;
            }
        } else {
            return false;
        }
    }
    
    //for snail
    public boolean isMotifForsnail(String kmer) {
        if (kmer.startsWith("CC") && kmer.endsWith("GG")) {
            String temp = kmer.substring(2, 7);
            if (temp.equals("AGATAT")) {
                return true;
            } else {
                return false;
            }
        } else {
            return false;
        }
    }
    
    public void process(Fasta fa, int length) {
        ArrayList<Kmer> kmerlist = getKmer.getkmerlist(fa.getName(), fa.getSequence(), length);
        for (int i = 0; i < kmerlist.size(); i++) {
            Kmer tempstr = kmerlist.get(i);
            if (this.isMotif(tempstr.getSeq())) {
                System.out.println(tempstr.toString());
            }
        }

    }
    
    public static void main(String[] args) {
        new searchMotif("F:\\百度云同步盘\\resouces\\projects\\任先越-马俊\\相互作用预测\\allgenes.txt",10);
    }
}
