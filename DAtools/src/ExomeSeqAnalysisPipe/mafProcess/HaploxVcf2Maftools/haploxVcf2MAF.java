/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package ExomeSeqAnalysisPipe.mafProcess.HaploxVcf2Maftools;

import ExomeSeqAnalysisPipe.mafProcess.MAF_maftools;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

/**
 *
 * @author Administrator
 * @since 2019-4-12
 * @coding time 19:18:54
 * @author Qi Zhao
 */
public class haploxVcf2MAF {
    String infile;
    String outfile;
    String sampleName;

    public haploxVcf2MAF(String infile, String outfile, String sampleName) {
        this.infile = infile;
        this.outfile = outfile;
        this.sampleName = sampleName;
    }

    public haploxVcf2MAF(String infile, String outfile) {
        this.infile = infile;
        this.outfile = outfile;
        File inFile=new File(infile);
        this.sampleName=inFile.getName().split("_")[0];
    }

    public haploxVcf2MAF(String infile) {
        this.infile = infile;
        File inFile=new File(infile);
        this.sampleName=inFile.getName().split("_")[0];
        this.outfile = inFile.getParent()+"\\"+inFile.getName().split("_")[0]+".maf";
    }
    
    
    
    

    public void process() throws IOException{
        
        FileWriter fw = new FileWriter(this.outfile);
        
       
        int count=0;
         BufferedReader br = new BufferedReader(new java.io.FileReader(new File(infile)));
           String tempstr="";
           while (br.ready()) {
               tempstr=br.readLine();
               if(tempstr.startsWith("#")) continue; 
               
               haploxVcf hvcf = haploxVcftoMAFfunction.strTohaploxVcf(tempstr);
               if(count==0){
                  fw.append(MAF_maftools.mafheader+"\t"+getInfoHeadString(hvcf)+"\n");  
               }
               count++;
             fw.append(singleVcf2MAF(hvcf).toString()+"\n");
             if(count%10000==0){
                 System.out.println(count+" iterms were processed");
             }
             fw.flush();
        }
        
        fw.close();
    }
    
    
    
    public MAF_maftools singleVcf2MAF(haploxVcf hvcf){
        MAF_maftools maf= new MAF_maftools();
        HashMap<String,String> INFORmap =haploxVcftoMAFfunction.GetINFORmap(hvcf.getINFO());
        HashMap<String,String> Normalmap =haploxVcftoMAFfunction.GetNORMAL_FORMATmap(hvcf.getFORMAT(),hvcf.getNORMAL());
        HashMap<String,String> Tumormap = haploxVcftoMAFfunction.GetTUMOR_FORMATmap(hvcf.getFORMAT(),hvcf.getTUMOR());
        maf.setHugo_Symbol(INFORmap.get("Gene.refGene"));
        maf.setChromosome(hvcf.getCHROM());
        maf.setStart_Position(hvcf.getPOS());
        maf.setEnd_position(hvcf.getPOS());
        maf.setReference_Allele(hvcf.getREF());
        maf.setTumor_Seq_Allele1(hvcf.getALT());
        if(hvcf.getALT().length()+hvcf.getREF().length()>2){
            maf.setVariant_Type("INDEL");
        }else{
            maf.setVariant_Type("SNP");
        }
        maf.setTumor_Sample_Barcode(sampleName);
        maf.setVariant_Classification(INFORmap.get("ExonicFunc.refGene"));
        maf.setProtein_Change(INFORmap.get("AAChange.refGene"));
        maf.setI_TumorVAF_WU(Tumormap.get("FREQ"));
        maf.setOuthers(haploxVcftoMAFfunction.getInfoString(INFORmap));
        return maf;
    }
    
    public String getInfoHeadString(haploxVcf hvcf){
      
        HashMap<String,String> INFORmap =haploxVcftoMAFfunction.GetINFORmap(hvcf.getINFO());

        return haploxVcftoMAFfunction.getInfoStringHeader(INFORmap);
    }
    
    
    public static void main(String[] args) throws IOException {
        new haploxVcf2MAF("E:\\data\\test\\SZ20181219084WHB-6_cfdna_451plus_18411_snv_annovar.hg19_multianno.vcf").process();
    }
    
}
