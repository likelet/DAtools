/*
 * seperate fastafile with known index information ;
 * fasta file must be formated with sequence in a line
 */
package SeperateFataFileByIndexBiFC;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.HashMap;
import java.util.logging.Level;
import java.util.logging.Logger;
import pub.DNAsequenceProcess;
import pub.FastQ;
import pub.Fasta;
import pub.SequenceVerifier;

/**
 *
 * @author ZHAO Qi
 * @date 2015-1-31 15:33:09
 * @version 1.6.0
 */
public class SeperateSequenceByIndex {

    private String[] indexLib;
    private BufferedReader br;
    private SequenceVerifier sequenceVerifier;
    private String namestr="";

    public SeperateSequenceByIndex(String fastafile, String[] indexLib) {
        this.indexLib = indexLib;
        File inputfile=new File(fastafile);
        this.namestr=inputfile.getName().split("\\.")[0];
        try {
            br = new BufferedReader(new FileReader(inputfile));
            sequenceVerifier = new SequenceVerifier();
            this.process();
        } catch (FileNotFoundException ex) {
            ex.printStackTrace();
        }
    }
    public SeperateSequenceByIndex(String fastafile, String[] indexLib,String fastq) {
        this.indexLib = indexLib;
        try {
            br = new BufferedReader(new FileReader(new File(fastafile)));
            sequenceVerifier = new SequenceVerifier();
            this.processFastq();
        } catch (FileNotFoundException ex) {
            ex.printStackTrace();
        }
    }
    public void process() {
        HashMap<String, FileWriter> index2filemap = new HashMap<String, FileWriter>();
        DNAsequenceProcess dp =new DNAsequenceProcess();
        String str = "";
        try {
            //construct hasmap
            for (int i = 0; i < indexLib.length; i++) {
                str = this.namestr+"_"+indexLib[i] + "_index.fasta";
                FileWriter fw = new FileWriter(str);
                index2filemap.put(indexLib[i], fw);
            }

            Fasta fasta = new Fasta();
            String tempstr = "";
            while (br.ready()) {
                //System.out.println(count);
                tempstr = br.readLine();
                //String tempa[]=br.readLine().split(" ");
                if (tempstr.startsWith(">")) {

                    fasta.setName(tempstr);
                } else {
                    fasta.setSequence(tempstr);
                    for (int i = 0; i < indexLib.length; i++) {
                        //以index開始或者其反向互補結尾的
                        if (fasta.getSequence().startsWith(indexLib[i])||fasta.getSequence().endsWith(dp.getReverseComplimentary(indexLib[i]))) {
                            index2filemap.get(indexLib[i]).append(fasta.toString() + "\n");
                            index2filemap.get(indexLib[i]).flush();
                            break;
                        }

                    }
                }
            }
            //close fw
            for (int i = 0; i < indexLib.length; i++) {
                str = indexLib[i];
                index2filemap.get(str).close();
            }
        } catch (IOException ex) {
            Logger.getLogger(SeperateSequenceByIndex.class.getName()).log(Level.SEVERE, null, ex);
        }

    }
//split fastq
    public void processFastq() {
        DNAsequenceProcess dp=new DNAsequenceProcess();
        HashMap<String, FileWriter> index2filemap = new HashMap<String, FileWriter>();
        String str = "";
        try {
            //construct hasmap
            for (int i = 0; i < indexLib.length; i++) {
                str = indexLib[i] + "_index.fastq";
                FileWriter fw = new FileWriter(str);
                index2filemap.put(indexLib[i], fw);
            }

            String tempstr = "";
            int count = 0;
            String tempstr1 = "";
            String tempstr2 = "";
            String tempstr3 = "";
            String tempstr4 = "";
            while (br.ready()) {
                //System.out.println(count);
                tempstr1 = br.readLine().trim();
                tempstr2 = br.readLine().trim();
                tempstr3 = br.readLine().trim();
                tempstr4 = br.readLine().trim();
                if (tempstr1.startsWith("@") && this.judgeSeq(tempstr2)) {
                    FastQ fq = new FastQ(tempstr1, tempstr2, tempstr3, tempstr4);
                    count += 4;
                    for (int i = 0; i < indexLib.length; i++) {
                        //判断序列是否以index开头或者index的反向互补序列结尾
                        //if (fq.getSequence().startsWith(indexLib[i])||dp.getReverseComplimentary(fq.getSequence()).startsWith((indexLib[i]))) {
                      
                    if (fq.getSequence().startsWith(indexLib[i])) {
                        index2filemap.get(indexLib[i]).append(fq.toString() + "\r\n");
                        index2filemap.get(indexLib[i]).flush();
                        break;
                    }

                }
                } else {
                    System.out.println(count + "\t row format error");
                    System.out.println("=====================");
                    System.out.println(tempstr1 + "\r\n" + tempstr4 + "\r\n" + tempstr3 + "\r\n" + tempstr4);
                    System.out.println("=====================");
                }
                

            }
            //close fw
            for (int i = 0; i < indexLib.length; i++) {
                str = indexLib[i];
                index2filemap.get(str).close();
            }
        } catch (IOException ex) {
            Logger.getLogger(SeperateSequenceByIndex.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    public boolean judgeSeq(String seq) {
        String a = "ATCGN.";
        char x;
        boolean mark = true;
        for (int i = 0; i < seq.length(); i++) {
            x = seq.charAt(i);
            if (a.indexOf(String.valueOf(x)) == -1) {
                mark = false;
                break;
            }
        }
        return mark;
    }
    

    public static void main(String[] args) {
        String[] indexlib = {"TATA", "CACA", "GAGA"};
        new SeperateSequenceByIndex("F:\\resouces\\projects\\SUMO-FC\\rawdata\\assembling\\H-S2-N_u.fasta", indexlib);
    }

}
