/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package ExomeSeqAnalysisPipe.mafProcess.GVC2TCGAmaf;

import ExomeSeqAnalysisPipe.mafProcess.MAF_maftools;
import ExomeSeqAnalysisPipe.mafProcess.vepMAF2TCGAmaf.MAFby_Tool;
import ExomeSeqAnalysisPipe.mafProcess.vepMAF2TCGAmaf.tcga2_4Maf;

/**
 *
 * @author Administrator
 * @since 2017-11-17
 * @coding time 18:07:08
 * @author Qi Zhao
 */
public class GVCsimp_2_tcga4maf {
public static tcga2_4Maf GVCsimp_2_tcga4maf_covert(GVCsimp mfbt){
    tcga2_4Maf tcgaMAF=new tcga2_4Maf();
    tcgaMAF.setHugo_Symbol(mfbt.getGene_name());
    tcgaMAF.setEntrez_Gene_Id(mfbt.getGene_ID());
    tcgaMAF.setCenter("SYSUCC");
    tcgaMAF.setNCBI_Build("hg19");
    tcgaMAF.setChromosome(mfbt.getChromosome());
    tcgaMAF.setStart_position(mfbt.getPosition());
    tcgaMAF.setEnd_position(mfbt.getPosition());
    tcgaMAF.setStrand("+");
    tcgaMAF.setVariant_Classification(mfbt.getMutation_type());
    tcgaMAF.setVariant_Type("SNP");
    tcgaMAF.setReference_Allele(mfbt.getRef());
    tcgaMAF.setTumor_Seq_Allele1(mfbt.getMut());
    tcgaMAF.setTumor_Seq_Allele2(".");
    tcgaMAF.setDbSNP_RS(mfbt.getRsID());
    tcgaMAF.setDbSNP_Val_Status("by1000genomes");
    tcgaMAF.setTumor_Sample_Barcode(mfbt.getSampleID());
    tcgaMAF.setMatched_Norm_Sample_Barcode(".");
   
    
    tcgaMAF.setMatch_Norm_Seq_Allele1(mfbt.getMutAF_normal());
    tcgaMAF.setMatch_Norm_Seq_Allele2("0");
    tcgaMAF.setTumor_Validation_Allele1(mfbt.getMut());
    tcgaMAF.setTumor_Validation_Allele2(mfbt.getMut());
    tcgaMAF.setMatch_Norm_Validation_Allele1(mfbt.getRef());
    tcgaMAF.setMatch_Norm_Validation_Allele2(mfbt.getRef());
    tcgaMAF.setVerification_Status("Unknown");
    tcgaMAF.setValidation_Status("Untested");
    tcgaMAF.setSequencing_Phase("No");
    tcgaMAF.setSequence_Source("WXS");
    tcgaMAF.setValidation_Method("No");
    tcgaMAF.setScore("NA");
    tcgaMAF.setBAM_File("NA");
    tcgaMAF.setSequencer("Illumina HiSeq");
    tcgaMAF.setTumor_Sample_UUID("NA");
    tcgaMAF.setMatched_Norm_Sample_UUID("NA");
    tcgaMAF.setMutation_Status("Somatic");
    tcgaMAF.setAAChange(mfbt.getAA_change());
    tcgaMAF.setTranscript_Id(mfbt.getExonicFunc());
    tcgaMAF.setExon(mfbt.getExonicFunc());
    tcgaMAF.setChromChange(mfbt.getCDNA_change());
    return tcgaMAF;
}
public static MAF_maftools GVCsimp_2_tcga4maf_forMaftools_covert(GVCsimp mfbt){
    MAF_maftools maftoolsMAF=new MAF_maftools();
    maftoolsMAF.setHugo_Symbol(mfbt.getGene_name());
    maftoolsMAF.setEntrez_Gene_Id(mfbt.getGene_ID());
    maftoolsMAF.setCenter("SYSUCC");
    maftoolsMAF.setNCBI_Build("hg19");
    maftoolsMAF.setChromosome(mfbt.getChromosome());
    maftoolsMAF.setStart_Position(mfbt.getPosition());
    maftoolsMAF.setEnd_position(mfbt.getPosition());
    maftoolsMAF.setStrand("+");
    maftoolsMAF.setVariant_Classification(mfbt.getMutation_type());
    maftoolsMAF.setVariant_Type("SNP");
    maftoolsMAF.setReference_Allele(mfbt.getRef());
    maftoolsMAF.setTumor_Seq_Allele1(mfbt.getMut());
    maftoolsMAF.setTumor_Seq_Allele2(".");
    maftoolsMAF.setTumor_Sample_Barcode(mfbt.getSampleID());
    maftoolsMAF.setProtein_Change(mfbt.getAA_change());
    maftoolsMAF.setI_transcript_name(mfbt.getExonicFunc());
    maftoolsMAF.setI_TumorVAF_WU(mfbt.getMutAF_tumor());
   return maftoolsMAF;
}

public static MAF_maftools GVCsimp_2_tcga4maf_forMaftools_covert(GVCsimp mfbt,String SampleID){
    MAF_maftools maftoolsMAF=new MAF_maftools();
    maftoolsMAF.setHugo_Symbol(mfbt.getGene_name());
    maftoolsMAF.setEntrez_Gene_Id(mfbt.getGene_ID());
    maftoolsMAF.setCenter("SYSUCC");
    maftoolsMAF.setNCBI_Build("hg19");
    maftoolsMAF.setChromosome(mfbt.getChromosome());
    maftoolsMAF.setStart_Position(mfbt.getPosition());
    maftoolsMAF.setEnd_position(mfbt.getPosition());
    maftoolsMAF.setStrand("+");
    maftoolsMAF.setVariant_Classification(mfbt.getMutation_type());
    maftoolsMAF.setVariant_Type("SNP");
    maftoolsMAF.setReference_Allele(mfbt.getRef());
    maftoolsMAF.setTumor_Seq_Allele1(mfbt.getMut());
    maftoolsMAF.setTumor_Seq_Allele2(".");
    maftoolsMAF.setTumor_Sample_Barcode(SampleID);
    maftoolsMAF.setProtein_Change(mfbt.getAA_change());
    maftoolsMAF.setI_transcript_name(mfbt.getExonicFunc());
    maftoolsMAF.setI_TumorVAF_WU(mfbt.getMutAF_tumor());
   return maftoolsMAF;
}

//public static GVCsimp parsingGVCsimp(String str){
//    String []stringa=str.split("\t");
//    GVCsimp pgs;
//    if(stringa.length==50){
//         pgs = new GVCsimp(stringa[0],stringa[1],stringa[2],stringa[3],stringa[4],stringa[5],stringa[6],stringa[7],stringa[8],stringa[9],stringa[10],stringa[11],stringa[12],stringa[13],stringa[14],stringa[15],stringa[16],stringa[17],stringa[18],stringa[19],stringa[20],stringa[21],stringa[22],stringa[23],stringa[24],stringa[25],stringa[26],stringa[27],stringa[28],stringa[29],stringa[30],stringa[31],stringa[32],stringa[33],stringa[34],stringa[35],stringa[36],stringa[37],stringa[38],stringa[39],stringa[40],stringa[41],stringa[42],stringa[43],stringa[44],stringa[45],stringa[46],stringa[47],stringa[48],stringa[49]);
//    }else{
//         pgs = new GVCsimp(stringa[0],stringa[1],stringa[2],stringa[3],stringa[4],stringa[5],stringa[6],stringa[7],stringa[8],stringa[9],stringa[10],stringa[11],stringa[12],stringa[13],stringa[14],stringa[15],stringa[16],stringa[17],stringa[18],stringa[19],stringa[20],stringa[21],stringa[22],stringa[23],stringa[24],stringa[25],stringa[26]);
//       
//    }
//        return pgs;
//} 
}
