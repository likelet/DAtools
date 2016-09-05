/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package ExomeSeqAnalysisPipe.mafProcess.vepMAF2TCGAmaf;

/**
 * <p>convert</p>
 * <p>Created on 2015-11-18 10:40:43</p>
 * <p>Author Email: zhaoqi3@mail2.sysu.edu.cn</p>
 * @author ZHAO Qi
 * @date 2015-11-18 10:40:43
 * @version java 1.6.0
 * @version 
 */
public class convert {
public static tcga2_4Maf convert(MAFby_Tool mfbt){
    tcga2_4Maf tcgaMAF=new tcga2_4Maf();
    tcgaMAF.setHugo_Symbol(mfbt.getHugo_Symbol());
    tcgaMAF.setEntrez_Gene_Id(mfbt.getEntrez_Gene_Id());
    tcgaMAF.setCenter(mfbt.getCenter());
    tcgaMAF.setNCBI_Build(mfbt.getNCBI_Build());
    tcgaMAF.setChromosome(mfbt.getChromosome());
    tcgaMAF.setStart_position(mfbt.getStart_Position());
    tcgaMAF.setEnd_position(mfbt.getEnd_Position());
    tcgaMAF.setStrand(mfbt.getStrand());
    tcgaMAF.setVariant_Classification(mfbt.getVariant_Classification());
    tcgaMAF.setVariant_Type(mfbt.getVariant_Type());
    tcgaMAF.setReference_Allele(mfbt.getReference_Allele());
    tcgaMAF.setTumor_Seq_Allele1(mfbt.getTumor_Seq_Allele1());
    tcgaMAF.setTumor_Seq_Allele2(mfbt.getTumor_Seq_Allele2());
    tcgaMAF.setDbSNP_RS(mfbt.getDbSNP_RS());
    tcgaMAF.setDbSNP_Val_Status(mfbt.getDbSNP_Val_Status());
    if(!mfbt.getTumor_Sample_Barcode().equals("Tumor_Sample_Barcode")){
        tcgaMAF.setTumor_Sample_Barcode("T"+mfbt.getTumor_Sample_Barcode());
    }else{
        tcgaMAF.setTumor_Sample_Barcode(mfbt.getTumor_Sample_Barcode());
    }
    if(!mfbt.getMatched_Norm_Sample_Barcode().equals("Matched_Norm_Sample_Barcode")){
        tcgaMAF.setMatched_Norm_Sample_Barcode("N"+mfbt.getMatched_Norm_Sample_Barcode());
    }else{
        tcgaMAF.setMatched_Norm_Sample_Barcode(mfbt.getMatched_Norm_Sample_Barcode());
    }
    
    tcgaMAF.setMatch_Norm_Seq_Allele1(mfbt.getMatch_Norm_Seq_Allele1());
    tcgaMAF.setMatch_Norm_Seq_Allele2(mfbt.getMatch_Norm_Seq_Allele2());
    tcgaMAF.setTumor_Validation_Allele1(mfbt.getTumor_Validation_Allele1());
    tcgaMAF.setTumor_Validation_Allele2(mfbt.getTumor_Validation_Allele2());
    tcgaMAF.setMatch_Norm_Validation_Allele1(mfbt.getMatch_Norm_Validation_Allele1());
    tcgaMAF.setMatch_Norm_Validation_Allele2(mfbt.getMatch_Norm_Validation_Allele2());
    tcgaMAF.setVerification_Status(mfbt.getVerification_Status());
    tcgaMAF.setValidation_Status(mfbt.getValidation_Status());
    tcgaMAF.setSequencing_Phase(mfbt.getSequencing_Phase());
    tcgaMAF.setSequence_Source(mfbt.getSequence_Source());
    tcgaMAF.setValidation_Method(mfbt.getValidation_Method());
    tcgaMAF.setScore(mfbt.getScore());
    tcgaMAF.setBAM_File(mfbt.getBAM_File());
    tcgaMAF.setSequencer(mfbt.getSequencer());
    tcgaMAF.setTumor_Sample_UUID(mfbt.getTumor_Sample_UUID());
    tcgaMAF.setMatched_Norm_Sample_UUID(mfbt.getMatched_Norm_Sample_UUID());
    tcgaMAF.setMutation_Status("Somatic");
    tcgaMAF.setAAChange(mfbt.getHGVSp_Short());
    tcgaMAF.setTranscript_Id(mfbt.getTranscript_ID());
    if(mfbt.getEXON().length()!=1){
         tcgaMAF.setExon("exon"+mfbt.getEXON().split("||/")[0]);
    }
   tcgaMAF.setChromChange(mfbt.getHGVSc());
    
    return tcgaMAF;
}
}
