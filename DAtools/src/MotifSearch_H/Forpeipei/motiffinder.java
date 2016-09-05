/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package MotifSearch_H.Forpeipei;

import MotifSearch_H.Kmer;
import MotifSearch_H.getKmer;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.logging.Level;
import java.util.logging.Logger;
import pub.DNAsequenceProcess;
import pub.Fasta;
import pub.FastaReader;

/**
 * <p>
 * motiffinder</p>
 * <p>
 * Created on 2015-11-28 10:26:10</p>
 * <p>
 * Author Email: zhaoqi3@mail2.sysu.edu.cn</p>
 *
 * @author ZHAO Qi
 * @date 2015-11-28 10:26:10
 * @version java 1.6.0
 * @version
 */
public class motiffinder {

    public motiffinder(String fastafile, String motiffile, String outfile) {
        LinkedList<Fasta> fastalist = new FastaReader(new File(fastafile)).getFastaList();
        try {
            FileWriter fw = new FileWriter(outfile);
            
            
       
        for (int i = 0; i < fastalist.size(); i++) {
                process(fastalist.get(i), ReadMotifFile.getmotiflist(motiffile), fw);
                System.out.println(i);
            }

            fw.close();
        } catch (IOException ex) {
            Logger.getLogger(motiffinder.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public boolean isMotif(String kmer, motif mf) {
        if (mf.getMotifsequence().toUpperCase().contains("N")) {
            return isMotifContiansN(kmer, mf.getMotifsequence());
        } else {
            return isMotifWithoutN(kmer, mf.getMotifsequence());
        }
    }

    public boolean isMotifWithoutN(String kmer, String sequence) {
        if (kmer.equals(sequence)) {
            return true;
        } else if (kmer.equals(new DNAsequenceProcess().getReverseComplimentary(sequence))) {
            return true;
        } else {
            return false;
        }
    }

    //match motif with N
    public boolean isMotifContiansN(String kmer, String sequence) {
        if (alignSequenceWithN(kmer, sequence) || alignSequenceWithN(kmer, new DNAsequenceProcess().getReverseComplimentary(sequence))) {
            return true;
        } else {
            return false;
        }

    }

    public boolean alignSequenceWithN(String kmer, String sequence) {
        boolean marks = true;
        if (countN(kmer) != countN(sequence)) {
            marks = false;
        } else {
            for (int i = 0; i < kmer.length(); i++) {
                if (kmer.charAt(i) != 'N' && sequence.charAt(i) != 'N') {
                    if (kmer.charAt(i) != sequence.charAt(i)) {
                        marks = false;
                        break;
                    }
                }
            }
        }
        return marks;
    }

    public int countN(String str) {
        int ncount = 0;
        for (int i = 0; i < str.length(); i++) {
            if (str.charAt(i) == 'N') {
                ncount++;
            }
        }
        return ncount;
    }

    public void process(Fasta fa, ArrayList<motif> motiflist, FileWriter fw) {
        try {
            for (int j = 0; j < motiflist.size(); j++) {
                motif mf = motiflist.get(j);
                ArrayList<Kmer> kmerlist = getKmer.getkmerlist(fa.getName(), fa.getSequence(), mf.getMotiflength());
                for (int i = 0; i < kmerlist.size(); i++) {
                    Kmer tempstr = kmerlist.get(i);

                    if (this.isMotif(tempstr.getSeq(), mf)) {
                        fw.append(tempstr.toString() + "\t" + mf.toString() + "\r\n");
//                        System.out.println(tempstr.toString());
                    }
                }

            }
            fw.flush();

        } catch (IOException ex) {
            Logger.getLogger(motiffinder.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    public static void main(String[] args) {
        new motiffinder("F:\\mywork\\project\\佩佩\\WPP_Motif_2015.11.27\\promoter.2kb.fa", "F:\\mywork\\project\\佩佩\\WPP_Motif_2015.11.27\\moriffile.txt", "F:\\mywork\\project\\佩佩\\WPP_Motif_2015.11.27\\promoter_motif_2kb.txt");
    }
}
