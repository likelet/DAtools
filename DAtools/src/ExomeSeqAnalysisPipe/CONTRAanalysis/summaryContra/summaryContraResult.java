/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ExomeSeqAnalysisPipe.CONTRAanalysis.summaryContra;

import ExomeSeqAnalysisPipe.CONTRAanalysis.ContraOutTab;
import ExomeSeqAnalysisPipe.CONTRAanalysis.GscoreStatistic.CNVMatrixIterm;
import ExomeSeqAnalysisPipe.CONTRAanalysis.ParseContraOutTab;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * <p>
 * summaryContraResult</p>
 * <p>
 * Created on 2016-1-7 15:35:19</p>
 * <p>
 * Author Email: zhaoqi3@mail2.sysu.edu.cn</p>
 *
 * @author ZHAO Qi
 * @date 2016-1-7 15:35:19
 * @version java 1.6.0
 * @version
 */
public class summaryContraResult {

    private LinkedHashMap<String, LinkedHashMap<String,ContraOutTab>> contraOutlist=new LinkedHashMap<String, LinkedHashMap<String,ContraOutTab>>();
    private String inputpairedsamplename;
    private String outputfilename = "CNA.CONTRA.matrix.filter_p0.05.txt";
    private String outputdetails = "CNA.CONTRA.detail_p0.05.txt";
    private LinkedHashMap<String, pairedSample> pairedMap = new LinkedHashMap<String, pairedSample>();
    private ArrayList<CNVMatrixIterm> oringinlist =new ArrayList<CNVMatrixIterm> ();

    public summaryContraResult(String inputpairedsamplename) {
        this.inputpairedsamplename = inputpairedsamplename;
         this.ininiate();
        this.process();
    }

    public summaryContraResult(String inputpairedsamplename, String outname) {
        this.inputpairedsamplename = inputpairedsamplename;
        this.outputfilename = outname;
        this.ininiate();
        this.process();
        this.writeMatrix();
        
    }
     public summaryContraResult(String inputpairedsamplename, String outname,String outname2) {
        this.inputpairedsamplename = inputpairedsamplename;
        this.outputdetails=inputpairedsamplename;
        this.outputfilename = outname;
    }
    
     
     public void ininiate(){
         this.parsePairedSamplefile();
         for (String str:pairedMap.keySet()) {
             this.parseSingleMap(str);
         }
     }
     public void process(){
         int count=0;
         for (String samplestr:contraOutlist.keySet()) {
             HashMap<String,ContraOutTab> tempmap= contraOutlist.get(samplestr);
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
        String filestr = namestr.replace(".bam", "_cnvout") + "/table/CNATable.10rd.10bases.20bins.txt";
        ArrayList<ContraOutTab> genecnvlist = new ParseContraOutTab(filestr).getMergedClist();
        for (int i = 0; i < genecnvlist.size(); i++) {
            id2cnvmap.put(genecnvlist.get(i).getGene_Sym(),genecnvlist.get(i));
        }
        this.contraOutlist.put(namestr, id2cnvmap);
    }

    
    
    
    
    
    public void parsePairedSamplefile() {
        BufferedReader br = null;
        try {
            br = new BufferedReader(new FileReader(new File(inputpairedsamplename)));
            String[] str;
            System.out.println("Reading your paired samplename file, which should be delimited by tab, "
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
            System.out.println("IO error");
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
