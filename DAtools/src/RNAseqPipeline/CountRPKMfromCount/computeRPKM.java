/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package RNAseqPipeline.CountRPKMfromCount;

import RNAseqPipeline.DoseCompensation.RemoveIntersectRegionFromGTF.gtf2lengthfile;
import RNAseqPipeline.GeneLengthFileReader;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * <p>
 * computeRPKM</p>
 * <p>
 * Created on 2015-12-25 12:42:05</p>
 * <p>
 * Author Email: zhaoqi3@mail2.sysu.edu.cn</p>
 *
 * @author ZHAO Qi
 * @date 2015-12-25 12:42:05
 * @version java 1.6.0
 * @version
 */
public class computeRPKM {

    private HashMap<String, Integer> lengthmap;
    private HashMap<String, Integer> countmap = new HashMap<String, Integer>();
    private HashMap<String, Double> rpkmtmap = new HashMap<String, Double>();
    private HashMap<String, String> gene2chr;
    private int totalreads = 0;
    private String outfile;

    //read file option
    private int column = 2;
    private boolean isheader = true;
    private String sep = "\t";

    public computeRPKM() {
    }

    //gene length file using gtf as input 
    public computeRPKM(String genelengthfile, String genecountfile, String outfile) {
        GeneLengthFileReader gr = new GeneLengthFileReader(genelengthfile);
        lengthmap = gr.getGeneLengthMap2();
        this.outfile = outfile;
        readCountFile(genecountfile);
        this.process();
    }

    //for internal usage
    public computeRPKM(String gtffile, String genecountfile) {
        gtf2lengthfile gf = new gtf2lengthfile(gtffile, outfile + ".lengthfile");
        lengthmap = gf.getGeneLengthMap2();
        this.outfile = outfile;
        readCountFile(genecountfile);
        gene2chr = gf.getGene2chr();
        this.process();
    }

    public computeRPKM(HashMap<String, Integer> genelengthMap, String genecountfile) {
//        GeneLengthFileReader gr = new GeneLengthFileReader(genelengthfile);
        lengthmap = genelengthMap;
        readCountFile(genecountfile);
        processInternal();
    }

    public void processInternal() {

        int count ;
        int elength ;
        System.out.println(totalreads);
        for (Iterator it = countmap.keySet().iterator(); it.hasNext();) {
            String tempstr = (String) it.next();
            count = countmap.get(tempstr);
            if (lengthmap.get(tempstr) != null) {
                elength = lengthmap.get(tempstr);

//                    System.out.println(this.getRPKM(count, elength));
                double rpkm = getRPKM(count, elength);
                this.rpkmtmap.put(tempstr, rpkm);
            } else {
                this.rpkmtmap.put(tempstr, -1.0);
                System.out.println("Length information of gene "+tempstr+" is unavailable");
            }

        }
    }

    public void process() {
        try {

            FileWriter fw = new FileWriter(outfile);
            int count = 0;
            int elength = 0;
            System.out.println(totalreads);
            for (Iterator it = countmap.keySet().iterator(); it.hasNext();) {
                String tempstr = (String) it.next();
                count = countmap.get(tempstr);
                fw.append(countmap.get(tempstr) + "\t");
                if (lengthmap.get(tempstr) != null) {
                    elength = lengthmap.get(tempstr);
                    fw.append(elength + "\t");

//                    System.out.println(this.getRPKM(count, elength));
                    fw.append(this.getRPKM(count, elength) + "\n");
                } else {
                    fw.append("unappliable\t");

                    fw.append("unappliable\n");
                }

            }

            fw.flush();
            fw.close();
        } catch (IOException ex) {
            Logger.getLogger(computeRPKM.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public HashMap<String, Integer> getCountmap() {
        return countmap;
    }

    public HashMap<String, Double> getRpkmtmap() {
        return rpkmtmap;
    }

    public void readCountFile(String countfile) {
       
        BufferedReader br = null;
        int count = 0;
        try {
            br = new BufferedReader(new FileReader(new File(countfile)));
            String[] str;
            if (isheader) {
                br.readLine();
            }
            while (br.ready()) {
                str = br.readLine().trim().split(sep);
                countmap.put(str[0], Integer.parseInt(str[column - 1]));
                this.totalreads += Integer.parseInt(str[column - 1]);
            }
            br.close();
        } catch (FileNotFoundException ex) {
            System.out.println(countfile + " is not found! please check your filepath ");
        } catch (IOException ex) {
            System.out.println("IO error");
        }
    }

    private double getRPKM(int count, int length) {
        return count * (10e6 / totalreads) * (10e3 / length);
    }

    public int getTotalreads() {
        return totalreads;
    }

    class geneRPKM {

        private String geneid;
        private String chr;
        private double rpkm;

        public geneRPKM(String geneid, String chr, double rpkm) {
            this.geneid = geneid;
            this.chr = chr;
            this.rpkm = rpkm;
        }

        public String getGeneid() {
            return geneid;
        }

        public void setGeneid(String geneid) {
            this.geneid = geneid;
        }

        public String getChr() {
            return chr;
        }

        public void setChr(String chr) {
            this.chr = chr;
        }

        public double getRpkm() {
            return rpkm;
        }

        public void setRpkm(double rpkm) {
            this.rpkm = rpkm;
        }

        @Override
        public String toString() {
            return geneid + "\t" + chr + "\r" + rpkm;
        }

    }

    public static void main(String[] args) {
    
//        new computeRPKM("F:\\百度云同步盘\\resouces\\projects\\splicing-进化\\gtf\\intersect_free.gtf","F:\\百度云同步盘\\resouces\\projects\\splicing-进化\\gtf\\SRR014262_25bp_free.count","F:\\百度云同步盘\\resouces\\projects\\splicing-进化\\gtf\\SRR014262_25bp_free.fpkm",false);
    }
}
