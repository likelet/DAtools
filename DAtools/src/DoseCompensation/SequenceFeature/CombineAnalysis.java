/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package DoseCompensation.SequenceFeature;

import DoseCompensation.SequenceFeature.GCcontent.GCContent;
import DoseCompensation.SequenceFeature.HexamerEntropy.GetHexmerEntropy;
import DoseCompensation.SequenceFeature.rRNAsimlarty.SimilartyTorRNA;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.logging.Level;
import java.util.logging.Logger;
import pub.Fasta;
import pub.FastaReader;

/**
 * <p>CombineAnalysis</p>
 * <p>Created on 2015-12-31 15:26:27</p>
 * <p>Author Email: zhaoqi3@mail2.sysu.edu.cn</p>
 * @author ZHAO Qi
 * @date 2015-12-31 15:26:27
 * @version java 1.6.0
 * @version 
 */
public class CombineAnalysis {

    private LinkedHashMap<String, Double> GCmap;
    private LinkedHashMap<String, Double> rRNAsimMap;
    private LinkedHashMap<String, Double> HEmap;
    private ArrayList<String> resultlist=new ArrayList<String>();
    private String fastafile;
    private String outfile;

    public CombineAnalysis(String fastafile,String outfile) {
        this.fastafile=fastafile;
        this.GCmap = new GCContent(fastafile).getGcContent();
        
//        System.out.println(GCmap.size());
        this.rRNAsimMap = new SimilartyTorRNA(fastafile).getBlastResultMap();
        this.HEmap = new GetHexmerEntropy(fastafile).getResultlist();
        this.process();
        this.outfile=outfile;
        this.writeOut();
//        System.out.println(this.print());
    }
     public CombineAnalysis(String fastafile,String outfile,int thread) {
        this.fastafile=fastafile;
        this.GCmap = new GCContent(fastafile).getGcContent();
         System.out.println("GC processed!");
//        System.out.println(GCmap.size());
        this.rRNAsimMap = new SimilartyTorRNA(fastafile,thread).getBlastResultMap();
        System.out.println("rRNA similarity processed!");
        this.HEmap = new GetHexmerEntropy(fastafile,thread).getResultlist();
        System.out.println(" HexmerEntropy processed!");
        this.process();
        this.outfile=outfile;
        System.out.println("Start writing out......");
        this.writeOut();
//        System.out.println(this.print());
    }
    
    public void process() {
        LinkedList<Fasta> falist = new FastaReader(fastafile).getFastaList();
        for (int i = 0; i < falist.size(); i++) {
            String str = falist.get(i).getPureName();
            resultlist.add(str + "\t" + GCmap.get(str) + "\t" +rRNAsimMap.get(str)+"\t"+ HEmap.get(str));
        }
    }
    
    public String print(){
        String str="";
        for (int i = 0; i < resultlist.size(); i++) {
            str+=resultlist.get(i)+"\r\n";
        }
        return str;
    }
    
    public void writeOut(){
        try {
            FileWriter fw = new FileWriter(this.outfile);
            fw.append(this.print());
            fw.flush();
            fw.close();
        } catch (IOException ex) {
            Logger.getLogger(CombineAnalysis.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    public static void main(String[] args) {
        new CombineAnalysis("D:\\Documents\\Tencent Files\\119621414\\FileRecv\\SeqCharacter\\test.txt","test.out");
    }


}
