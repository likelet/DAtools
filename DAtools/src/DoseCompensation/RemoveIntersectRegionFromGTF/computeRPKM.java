/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DoseCompensation.RemoveIntersectRegionFromGTF;

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
    private HashMap<String, Integer> countmap;
    private HashMap<String,String> gene2chr;
    private int totalreads=0;
    private String outfile;

    public computeRPKM(String genelengthfile, String genecountfile, String outfile) {
        GeneLengthFileReader gr = new GeneLengthFileReader(genelengthfile);
        lengthmap = gr.getGeneLengthMap2();
        this.outfile = outfile;
        countmap = this.readCountFile(genecountfile);
        this.process();
    }
      public computeRPKM(String gtffile, String genecountfile, String outfile,boolean fromgtf) {
       gtf2lengthfile gf=new gtf2lengthfile( gtffile, outfile+".lengthfile");   
//        this.totalreads = gf.getTotalreads();
        lengthmap = gf.getGeneLengthMap2();
        this.outfile = outfile;
        countmap = this.readCountFile(genecountfile);
        gene2chr=gf.getGene2chr();
        this.process();
        new File(outfile+".lengthfile").delete();
        
    }


    public void process() {
        try {
            FileWriter fw = new FileWriter(outfile);
            int count = 0;
            int elength = 0;
            System.out.println(totalreads);
            for (Iterator it = countmap.keySet().iterator(); it.hasNext();) {
                String tempstr = (String) it.next();
                fw.append(tempstr + "\t");
                fw.append(gene2chr.get(tempstr)+"\t");
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

    public HashMap<String, Integer> readCountFile(String countfile) {
        HashMap<String, Integer> rcmap = new HashMap<String, Integer>();
        BufferedReader br = null;
        try {
            br = new BufferedReader(new FileReader(new File(countfile)));
            String[] str;
            while (br.ready()) {
                str = br.readLine().trim().split("\t");
                rcmap.put(str[0], Integer.parseInt(str[1]));
                this.totalreads+=Integer.parseInt(str[1]);
            }
            br.close();
        } catch (FileNotFoundException ex) {
            System.out.println(countfile + " is not found! please check your filepath ");
        } catch (IOException ex) {
            System.out.println("IO error");
        }
        return rcmap;
    }

    private double getRPKM(int count, int length) {
        return count *(10e6/totalreads)*(10e3/length);
    }
    
    class geneRPKM{
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
            return  geneid + "\t" + chr + "\r" + rpkm ;
        }
        
        
    }
    
    public static void main(String[] args) {
        new computeRPKM("F:\\百度云同步盘\\resouces\\projects\\splicing-进化\\gtf\\intersect_free.gtf","F:\\百度云同步盘\\resouces\\projects\\splicing-进化\\gtf\\SRR014262_25bp_free.count","F:\\百度云同步盘\\resouces\\projects\\splicing-进化\\gtf\\SRR014262_25bp_free.fpkm",false);
    }
}
