/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package ExomeSeqAnalysisPipe.CNVanalysis.CONTRAanalysis.summaryContra;

import ExomeSeqAnalysisPipe.CNVanalysis.CONTRAanalysis.ContraOutTab;
import ExomeSeqAnalysisPipe.CNVanalysis.CONTRAanalysis.GscoreStatistic.CNVMatrixIterm;
import ExomeSeqAnalysisPipe.CNVanalysis.CONTRAanalysis.SegementWindows;
import dataformat.FAIterm;
import dataformat.FAItermReader;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * <p>summaryContraResultWithChromeBind</p>
 * <p>Created on 2016-1-16 15:06:22</p>
 * <p>Author Email: zhaoqi3@mail2.sysu.edu.cn</p>
 * @author ZHAO Qi
 * @date 2016-1-16 15:06:22
 * @version java 1.6.0
 * @version 
 */
public class summaryContraResultWithChromeBind {
 private LinkedHashMap<String, LinkedHashMap<String,ContraOutTab>> contraOutlist=new LinkedHashMap<String, LinkedHashMap<String,ContraOutTab>>();
    private String inputpairedsamplename;
    private String outputfilename = "CNA.CONTRA.matrix.filter_p0.05.txt";
    private String outputdetails = "CNA.CONTRA.detail_p0.05.txt";
    private LinkedHashMap<String, pairedSample> pairedMap = new LinkedHashMap<String, pairedSample>();
    private ArrayList<CNVMatrixIterm> oringinlist =new ArrayList<CNVMatrixIterm> ();
    private LinkedHashMap<String, FAIterm> Chrom2FAImap;

    public summaryContraResultWithChromeBind(String inputpairedsamplename,String faifile) {
        this.inputpairedsamplename = inputpairedsamplename;
        this.Chrom2FAImap=new FAItermReader(faifile).getChrom2FAImap();
         this.ininiate();
        this.process();
    }

    public summaryContraResultWithChromeBind(String inputpairedsamplename, String faifile, String outname) {
        this.inputpairedsamplename = inputpairedsamplename;
        this.outputfilename = outname;
        this.Chrom2FAImap=new FAItermReader(faifile).getChrom2FAImap();
        this.ininiate();
        this.process();
        this.writeMatrix();

    }

    public void ininiate() {
        this.parsePairedSamplefile();
        for (String str : pairedMap.keySet()) {
             this.parseSingleMap(str);
         }
     }
     public void process(){
         int count=0;
         for (String samplestr:contraOutlist.keySet()) {
             LinkedHashMap<String,ContraOutTab> tempmap= contraOutlist.get(samplestr);
            if(count==0){
               for(String genename:tempmap.keySet()){
                   CNVMatrixIterm ct=new CNVMatrixIterm(genename);
                   if(tempmap.get(ct.getGeneid())!=null){
                        ct.addCNV(tempmap.get(ct.getGeneid()).getMean_of_LogRatio());
                    }else{
                        ct.addCNV(0);
                    }
                   oringinlist.add(ct);
               }
            }else{
                for (int i = 0; i < oringinlist.size(); i++) {
                    if(tempmap.get(oringinlist.get(i).getGeneid())!=null){
                        oringinlist.get(i).addCNV(tempmap.get(oringinlist.get(i).getGeneid()).getMean_of_LogRatio());
                    }else{
                        oringinlist.get(i).addCNV(0);
                    }
                    
                }
  
            }
             count++;
         }
     }
//    public ArrayList<ContraOutTab> parseSingleOut(String namestr) {
//        String filestr = namestr.replace(".bam", "_cnvout") + "/table/CNATable.10rd.10bases.20bins.txt";
//        return new ParseContraOutTab(filestr).getMergedClist();
//    }
    public void parseSingleMap(String namestr) {
        LinkedHashMap<String,ContraOutTab> id2cnvmap=new LinkedHashMap<String,ContraOutTab>();
//        String filestr ="G:\\temp\\test\\"+namestr+ "/table/CNATable.10rd.10bases.20bins.txt";
        String filestr =namestr+ "_cnvout/table/CNATable.10rd.10bases.20bins.txt";
        ArrayList<ContraOutTab> binlist = new SegementWindows(Chrom2FAImap,filestr).getbinlistTab();
        System.out.println(binlist.size());
        for (int i = 0; i < binlist.size(); i++) {
            id2cnvmap.put(binlist.get(i).getGene_Sym(),binlist.get(i));
        }
        this.contraOutlist.put(namestr, id2cnvmap);
    }

    
    
    
    
    
    public void parsePairedSamplefile() {
        BufferedReader br = null;
        try {
            br = new BufferedReader(new FileReader(new File(inputpairedsamplename)));
            String[] str;
            System.out.println("Reading your paired samplename file, which should be delimed by tablar, "
                    + "the first column is tumorname and the 2nd is normalname");
            while (br.ready()) {
                str = br.readLine().split("\t");
                pairedSample ps = new pairedSample(str[0], str[1]);
                this.pairedMap.put(str[0], ps);
            }
            br.close();
        } catch (FileNotFoundException ex) {
            System.out.println(inputpairedsamplename + " is not found! please check your filepath ");
        } catch (IOException ex) {
            System.out.println("read sample file IO error");
        }
    }

    public void writeMatrix(){
        String namestr="gene\t";
        for(String str:pairedMap.keySet()){
            namestr+=str+"\t";
        }
        namestr=namestr.substring(0,namestr.length()-1);
        try {
            FileWriter fw = new FileWriter(outputfilename);
            fw.append(namestr+"\n");
            for (int i = 0; i < oringinlist.size(); i++) {
                fw.append(oringinlist.get(i).toString()+"\n");
            }
            fw.flush();
            fw.close();
        } catch (IOException ex) {
            Logger.getLogger(summaryContraResult.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public LinkedHashMap<String, LinkedHashMap<String, ContraOutTab>> getContraOutlist() {
        return contraOutlist;
    }

    public String getInputpairedsamplename() {
        return inputpairedsamplename;
    }

    public String getOutputfilename() {
        return outputfilename;
    }

    public String getOutputdetails() {
        return outputdetails;
    }

    public LinkedHashMap<String, pairedSample> getPairedMap() {
        return pairedMap;
    }

    public ArrayList<CNVMatrixIterm> getOringinlist() {
        return oringinlist;
    }
    
    
    
    
    class pairedSample {

        private String uniqename;
        private String tumorname;
        private String normalname;

        public pairedSample(String tumorname, String normalname) {
            this.uniqename = tumorname;
            this.tumorname = tumorname;
            this.normalname = normalname;
        }

        public String getUniqename() {
            return uniqename;
        }

        public void setUniqename(String uniqename) {
            this.uniqename = uniqename;
        }

        public String getTumorname() {
            return tumorname;
        }

        public void setTumorname(String tumorname) {
            this.tumorname = tumorname;
        }

        public String getNormalname() {
            return normalname;
        }

        public void setNormalname(String normalname) {
            this.normalname = normalname;
        }

    }
    class detailsClass{
        private String geneid;
        private double meanlogr;
        private double medianlogr;
        private double qvalue;
        private double pvalue;
        
    }
}
