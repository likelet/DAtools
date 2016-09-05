/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ExomeSeqAnalysisPipe.clusterFeatureGenerated;

import ExomeSeqAnalysisPipe.VCFReader;
import ExomeSeqAnalysisPipe.VCFrecord;
import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.Set;



/**
 *
 * @author ZHAO Qi
 * @date 2014-2-28 11:04:02
 * @version 1.6.0
 */
class FeatureGeneraterThread implements Runnable {

    private HashMap<String, output> chr2outputMap = new HashMap<String, output>();
    private File vcffile;
    private HashMap<String, FAIterm> Chrom2FAImap;
    private int bin;
    private LinkedList<VCFrecord> vcflist;
    private ArrayList templist;
    private FeatureGeneraterFinal fgf;

    public FeatureGeneraterThread(String vcffile, FeatureGeneraterFinal fgf) {
        this.vcffile = new File(vcffile);
        this.Chrom2FAImap = fgf.Chrom2FAImap;
        this.bin = fgf.BIN;
        vcflist = new VCFReader(vcffile).getVcflist();
        this.fgf=fgf;
    }

    public void SingleSampleFeatureGenerater() {
        this.chr2outputMapInitialize(Chrom2FAImap, bin);
        //System.out.println(vcflist.size());
        for (int i = 0; i < vcflist.size(); i++) {
            VCFrecord vr = vcflist.get(i);
            int index = Integer.parseInt(vr.getPos()) / bin;
            chr2outputMap.get(vr.getChrom()).binstat[index]++;
        }

    }

    public void chr2outputMapInitialize(HashMap<String, FAIterm> Chrom2FAImap, int bin) {
        chr2outputMap = new HashMap<String, output>();
        Set<String> chrset = Chrom2FAImap.keySet();
        for (Iterator it = chrset.iterator(); it.hasNext();) {
            String chrstr = (String) it.next();
            output op = new output(chrstr, Chrom2FAImap.get(chrstr).getLength(), bin);
            chr2outputMap.put(chrstr, op);
        }

    }

    void print() {
        String s="";
        for (Iterator it = chr2outputMap.keySet().iterator(); it.hasNext();) {
            String tempstr = (String) it.next();
            //System.out.println(vcffile.getName() + "\t" + tempstr + "\t" + chr2outputMap.get(tempstr).toString() + "\r\n");
            s=vcffile.getName().split("\\.")[0];
            System.out.println(s);
            fgf.outlist.add(s + "\t" + chr2outputMap.get(tempstr).toString() + "\r\n");
            //fgf.outlist.add(fgf.index2sampleMap.get(s) + "\t" + chr2outputMap.get(tempstr).toString() + "\r\n");
        }
        
    }

    public static void main(String[] args) {

        HashMap<String, FAIterm> Chrom2FAImap = new FAItermReader("G:\\javatest\\ecoli.fa.fai").getChrom2FAImap();
        //FeatureGeneraterThread fs = new FeatureGeneraterThread("G:\\javatest\\test2\\NHD1737-71_bowtie2_sorted_rmdup_M.vcf", Chrom2FAImap, 10000);
//        fs.SingleSampleFeatureGenerater();
//        fs.print();
    }

    @Override
    public void run() {
        this.SingleSampleFeatureGenerater();
        //System.out.println(vcffile);
        this.print();
    }

    class output {

        private String chr;
        private int[] binstat;

        public output() {
        }

        public output(String chr, int Chrlength, int bin) {
            this.chr = chr;
            if (bin == -1) {
            } else {
                int size = Chrlength / bin;
                if (Chrlength % bin == 0) {
                    this.binstat = new int[size];
                } else {
                    this.binstat = new int[size + 1];
                }
            }
            for (int i = 0; i < binstat.length; i++) {
                binstat[i] = 0;

            }
        }

        public int[] getBinstat() {
            return binstat;
        }

        /**
         * Get the value of chr
         *
         * @return the value of chr
         */
        public String getChr() {
            return chr;
        }

        /**
         * Set the value of chr
         *
         * @param chr new value of chr
         */
        public void setChr(String chr) {
            this.chr = chr;
        }

        @Override
        public String toString() {
            String s = "";
            for (int i = 0; i < binstat.length; i++) {
                s += binstat[i] + "\t";
            }
            return s;
        }
    }
}
