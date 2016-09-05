/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DoseCompensation.SequenceFeature.GCcontent;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import pub.Fasta;
import pub.FastaReader;

/**
 *
 * @author zhengyueyuan
 */
public class GCContent {

    LinkedHashMap<String, Double> gcContentMap = new LinkedHashMap();
    private LinkedList<Fasta> fastaList;

    public GCContent() {
    }
    
    
    public GCContent(String fastaFile) {
        this.fastaList = new FastaReader(fastaFile).getFastaList();
        this.process();
    }

    public void process(){
    for (Fasta fastaData : fastaList) {
            int length = fastaData.getSequence().length();
            String seq = fastaData.getSequence();
            seq = seq.replaceAll("[ATN]", "");
            double GC = (double) seq.length() / length;
            gcContentMap.put(fastaData.getPureName(), GC);
        }
    }
    
    public LinkedHashMap<String, Double> getGcContent() {
        return gcContentMap;
    }

    public static void main(String[] args) {
        GCContent gcc = new GCContent("E:\\同步盘\\NetBeans\\SeqCharacter\\ref_rRNA.fasta");
        LinkedHashMap<String, Double> gcContentMap = gcc.getGcContent();
        for (String seq : gcContentMap.keySet()) {
            System.out.println(seq + "\t" + gcContentMap.get(seq));
        }

    }

}
