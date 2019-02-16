/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package ExomeSeqAnalysisPipe.mafProcess.GVC2TCGAmaf;

import htsjdk.samtools.util.CollectionUtil;
import java.util.List;
import java.util.stream.Collectors;

/**
 *
 * @author Administrator
 * @since 2017-11-17
 * @coding time 17:57:33
 * @author Qi Zhao
 */
public class GVCsimp {
private String Chromosome;
private String Position;
private String Ref;
private String Mut;
private String SampleID;
private String Depth;
private String Alt_count;
private String MutAF_tumor;
private String Alleles_tumor;
private String Depth_normal;
private String Alt_count_normal;
private String MutAF_normal;
private String Alleles_normal;
private String consensus_base_tumor;
private String consensus_quality_tumor;
private String snp_quality_tumor;
private String Function_region;
private String Mutation_type;
private String Gene_name;
private String Gene_ID;
private String Codon_change;
private String AA_change;
private String CDNA_change;
private String ExonicFunc;
private String GeneDetail;
private String Primary_site;
private String Cancer_type;
private String COSMIC_ID;
private String M1KG_MAF_ALL;
private String M1KG_MAF_AFR;
private String M1KG_MAF_AMR;
private String M1KG_MAF_EAS;
private String M1KG_MAF_EUR;
private String M1KG_MAF_SAS;
private String GeneSymbol;
private String ClinicalSignificance;
private String PhenotypeIDs;
private String SIFT_pred;
private String Polyphen2_HDIV_pred;
private String Polyphen2_HVAR_pred;
private String LRT_pred;
private String MutationTaster_pred;
private String MutationAssessor_pred;
private String FATHMM_pred;
private String PROVEAN_pred;
private String fathmm_MKL_coding_pred;
private String MetaSVM_pred;
private String MetaLR_pred;
private String GERP_RS;
private String rsID;

    public GVCsimp(String Chromosome, String Position, String Ref, String Mut, String SampleID, String Depth, String Alt_count, String MutAF_tumor, String Alleles_tumor, String Depth_normal, String Alt_count_normal, String MutAF_normal, String Alleles_normal, String consensus_base_tumor, String consensus_quality_tumor, String snp_quality_tumor, String Function_region, String Mutation_type, String Gene_name, String Gene_ID, String Codon_change, String AA_change, String CDNA_change, String ExonicFunc, String GeneDetail, String Primary_site, String Cancer_type, String COSMIC_ID, String M1KG_MAF_ALL, String M1KG_MAF_AFR, String M1KG_MAF_AMR, String M1KG_MAF_EAS, String M1KG_MAF_EUR, String M1KG_MAF_SAS, String GeneSymbol, String ClinicalSignificance, String PhenotypeIDs, String SIFT_pred, String Polyphen2_HDIV_pred, String Polyphen2_HVAR_pred, String LRT_pred, String MutationTaster_pred, String MutationAssessor_pred, String FATHMM_pred, String PROVEAN_pred, String fathmm_MKL_coding_pred, String MetaSVM_pred, String MetaLR_pred, String GERP_RS, String rsID) {
        this.Chromosome = Chromosome;
        this.Position = Position;
        this.Ref = Ref;
        this.Mut = Mut;
        this.SampleID = SampleID;
        this.Depth = Depth;
        this.Alt_count = Alt_count;
        this.MutAF_tumor = MutAF_tumor;
        this.Alleles_tumor = Alleles_tumor;
        this.Depth_normal = Depth_normal;
        this.Alt_count_normal = Alt_count_normal;
        this.MutAF_normal = MutAF_normal;
        this.Alleles_normal = Alleles_normal;
        this.consensus_base_tumor = consensus_base_tumor;
        this.consensus_quality_tumor = consensus_quality_tumor;
        this.snp_quality_tumor = snp_quality_tumor;
        this.Function_region = Function_region;
        this.Mutation_type = Mutation_type;
        this.Gene_name = Gene_name;
        this.Gene_ID = Gene_ID;
        this.Codon_change = Codon_change;
        this.AA_change = AA_change;
        this.CDNA_change = CDNA_change;
        this.ExonicFunc = ExonicFunc;
        this.GeneDetail = GeneDetail;
        this.Primary_site = Primary_site;
        this.Cancer_type = Cancer_type;
        this.COSMIC_ID = COSMIC_ID;
        this.M1KG_MAF_ALL = M1KG_MAF_ALL;
        this.M1KG_MAF_AFR = M1KG_MAF_AFR;
        this.M1KG_MAF_AMR = M1KG_MAF_AMR;
        this.M1KG_MAF_EAS = M1KG_MAF_EAS;
        this.M1KG_MAF_EUR = M1KG_MAF_EUR;
        this.M1KG_MAF_SAS = M1KG_MAF_SAS;
        this.GeneSymbol = GeneSymbol;
        this.ClinicalSignificance = ClinicalSignificance;
        this.PhenotypeIDs = PhenotypeIDs;
        this.SIFT_pred = SIFT_pred;
        this.Polyphen2_HDIV_pred = Polyphen2_HDIV_pred;
        this.Polyphen2_HVAR_pred = Polyphen2_HVAR_pred;
        this.LRT_pred = LRT_pred;
        this.MutationTaster_pred = MutationTaster_pred;
        this.MutationAssessor_pred = MutationAssessor_pred;
        this.FATHMM_pred = FATHMM_pred;
        this.PROVEAN_pred = PROVEAN_pred;
        this.fathmm_MKL_coding_pred = fathmm_MKL_coding_pred;
        this.MetaSVM_pred = MetaSVM_pred;
        this.MetaLR_pred = MetaLR_pred;
        this.GERP_RS = GERP_RS;
        this.rsID = rsID;
    }

    public GVCsimp(String Chromosome, String Position, String Ref, String Mut, String SampleID, String Depth, String Alt_count, String Function_region, String Mutation_type, String Gene_name, String Gene_ID, String Codon_change, String AA_change, String CDNA_change, String ExonicFunc, String GeneDetail, String ClinicalSignificance, String PhenotypeIDs, String SIFT_pred, String Polyphen2_HDIV_pred, String Polyphen2_HVAR_pred, String MutationTaster_pred, String MutationAssessor_pred, String FATHMM_pred, String PROVEAN_pred, String fathmm_MKL_coding_pred) {
        this.Chromosome = Chromosome;
        this.Position = Position;
        this.Ref = Ref;
        this.Mut = Mut;
        this.SampleID = SampleID;
        this.Depth = Depth;
        this.Alt_count = Alt_count;
        this.Function_region = Function_region;
        this.Mutation_type = Mutation_type;
        this.Gene_name = Gene_name;
        this.Gene_ID = Gene_ID;
        this.Codon_change = Codon_change;
        this.AA_change = AA_change;
        this.CDNA_change = CDNA_change;
        this.ExonicFunc = ExonicFunc;
        this.GeneDetail = GeneDetail;
        this.ClinicalSignificance = ClinicalSignificance;
        this.PhenotypeIDs = PhenotypeIDs;
        this.SIFT_pred = SIFT_pred;
        this.Polyphen2_HDIV_pred = Polyphen2_HDIV_pred;
        this.Polyphen2_HVAR_pred = Polyphen2_HVAR_pred;
        this.MutationTaster_pred = MutationTaster_pred;
        this.MutationAssessor_pred = MutationAssessor_pred;
        this.FATHMM_pred = FATHMM_pred;
        this.PROVEAN_pred = PROVEAN_pred;
        this.fathmm_MKL_coding_pred = fathmm_MKL_coding_pred;
    }

    
    
    
    
    public String getChromosome() {
        return Chromosome;
    }

    public void setChromosome(String Chromosome) {
        this.Chromosome = Chromosome;
    }

    public String getPosition() {
        return Position;
    }

    public void setPosition(String Position) {
        this.Position = Position;
    }

    public String getRef() {
        return Ref;
    }

    public void setRef(String Ref) {
        this.Ref = Ref;
    }

    public String getMut() {
        return Mut;
    }

    public void setMut(String Mut) {
        this.Mut = Mut;
    }

    public String getSampleID() {
        return SampleID;
    }

    public void setSampleID(String SampleID) {
        this.SampleID = SampleID;
    }

    public String getDepth() {
        return Depth;
    }

    public void setDepth(String Depth) {
        this.Depth = Depth;
    }

    public String getAlt_count() {
        return Alt_count;
    }

    public void setAlt_count(String Alt_count) {
        this.Alt_count = Alt_count;
    }

    public String getMutAF_tumor() {
        return MutAF_tumor;
    }

    public void setMutAF_tumor(String MutAF_tumor) {
        this.MutAF_tumor = MutAF_tumor;
    }

    public String getAlleles_tumor() {
        return Alleles_tumor;
    }

    public void setAlleles_tumor(String Alleles_tumor) {
        this.Alleles_tumor = Alleles_tumor;
    }

    public String getDepth_normal() {
        return Depth_normal;
    }

    public void setDepth_normal(String Depth_normal) {
        this.Depth_normal = Depth_normal;
    }

    public String getAlt_count_normal() {
        return Alt_count_normal;
    }

    public void setAlt_count_normal(String Alt_count_normal) {
        this.Alt_count_normal = Alt_count_normal;
    }

    public String getMutAF_normal() {
        return MutAF_normal;
    }

    public void setMutAF_normal(String MutAF_normal) {
        this.MutAF_normal = MutAF_normal;
    }

    public String getAlleles_normal() {
        return Alleles_normal;
    }

    public void setAlleles_normal(String Alleles_normal) {
        this.Alleles_normal = Alleles_normal;
    }

    public String getConsensus_base_tumor() {
        return consensus_base_tumor;
    }

    public void setConsensus_base_tumor(String consensus_base_tumor) {
        this.consensus_base_tumor = consensus_base_tumor;
    }

    public String getConsensus_quality_tumor() {
        return consensus_quality_tumor;
    }

    public void setConsensus_quality_tumor(String consensus_quality_tumor) {
        this.consensus_quality_tumor = consensus_quality_tumor;
    }

    public String getSnp_quality_tumor() {
        return snp_quality_tumor;
    }

    public void setSnp_quality_tumor(String snp_quality_tumor) {
        this.snp_quality_tumor = snp_quality_tumor;
    }

    public String getFunction_region() {
        return Function_region;
    }

    public void setFunction_region(String Function_region) {
        this.Function_region = Function_region;
    }

    public String getMutation_type() {
        return Mutation_type;
    }

    public void setMutation_type(String Mutation_type) {
        this.Mutation_type = Mutation_type;
    }

    public String getGene_name() {
        return Gene_name;
    }

    public void setGene_name(String Gene_name) {
        this.Gene_name = Gene_name;
    }

    public String getGene_ID() {
        return Gene_ID;
    }

    public void setGene_ID(String Gene_ID) {
        this.Gene_ID = Gene_ID;
    }

    public String getCodon_change() {
        return Codon_change;
    }

    public void setCodon_change(String Codon_change) {
        this.Codon_change = Codon_change;
    }

    public String getAA_change() {
        return AA_change;
    }

    public void setAA_change(String AA_change) {
        this.AA_change = AA_change;
    }

    public String getCDNA_change() {
        return CDNA_change;
    }

    public void setCDNA_change(String CDNA_change) {
        this.CDNA_change = CDNA_change;
    }

    public String getExonicFunc() {
        return ExonicFunc;
    }

    public void setExonicFunc(String ExonicFunc) {
        this.ExonicFunc = ExonicFunc;
    }

    public String getGeneDetail() {
        return GeneDetail;
    }

    public void setGeneDetail(String GeneDetail) {
        this.GeneDetail = GeneDetail;
    }

    public String getPrimary_site() {
        return Primary_site;
    }

    public void setPrimary_site(String Primary_site) {
        this.Primary_site = Primary_site;
    }

    public String getCancer_type() {
        return Cancer_type;
    }

    public void setCancer_type(String Cancer_type) {
        this.Cancer_type = Cancer_type;
    }

    public String getCOSMIC_ID() {
        return COSMIC_ID;
    }

    public void setCOSMIC_ID(String COSMIC_ID) {
        this.COSMIC_ID = COSMIC_ID;
    }

    public String getM1KG_MAF_ALL() {
        return M1KG_MAF_ALL;
    }

    public void setM1KG_MAF_ALL(String M1KG_MAF_ALL) {
        this.M1KG_MAF_ALL = M1KG_MAF_ALL;
    }

    public String getM1KG_MAF_AFR() {
        return M1KG_MAF_AFR;
    }

    public void setM1KG_MAF_AFR(String M1KG_MAF_AFR) {
        this.M1KG_MAF_AFR = M1KG_MAF_AFR;
    }

    public String getM1KG_MAF_AMR() {
        return M1KG_MAF_AMR;
    }

    public void setM1KG_MAF_AMR(String M1KG_MAF_AMR) {
        this.M1KG_MAF_AMR = M1KG_MAF_AMR;
    }

    public String getM1KG_MAF_EAS() {
        return M1KG_MAF_EAS;
    }

    public void setM1KG_MAF_EAS(String M1KG_MAF_EAS) {
        this.M1KG_MAF_EAS = M1KG_MAF_EAS;
    }

    public String getM1KG_MAF_EUR() {
        return M1KG_MAF_EUR;
    }

    public void setM1KG_MAF_EUR(String M1KG_MAF_EUR) {
        this.M1KG_MAF_EUR = M1KG_MAF_EUR;
    }

    public String getM1KG_MAF_SAS() {
        return M1KG_MAF_SAS;
    }

    public void setM1KG_MAF_SAS(String M1KG_MAF_SAS) {
        this.M1KG_MAF_SAS = M1KG_MAF_SAS;
    }

    public String getGeneSymbol() {
        return GeneSymbol;
    }

    public void setGeneSymbol(String GeneSymbol) {
        this.GeneSymbol = GeneSymbol;
    }

    public String getClinicalSignificance() {
        return ClinicalSignificance;
    }

    public void setClinicalSignificance(String ClinicalSignificance) {
        this.ClinicalSignificance = ClinicalSignificance;
    }

    public String getPhenotypeIDs() {
        return PhenotypeIDs;
    }

    public void setPhenotypeIDs(String PhenotypeIDs) {
        this.PhenotypeIDs = PhenotypeIDs;
    }

    public String getSIFT_pred() {
        return SIFT_pred;
    }

    public void setSIFT_pred(String SIFT_pred) {
        this.SIFT_pred = SIFT_pred;
    }

    public String getPolyphen2_HDIV_pred() {
        return Polyphen2_HDIV_pred;
    }

    public void setPolyphen2_HDIV_pred(String Polyphen2_HDIV_pred) {
        this.Polyphen2_HDIV_pred = Polyphen2_HDIV_pred;
    }

    public String getPolyphen2_HVAR_pred() {
        return Polyphen2_HVAR_pred;
    }

    public void setPolyphen2_HVAR_pred(String Polyphen2_HVAR_pred) {
        this.Polyphen2_HVAR_pred = Polyphen2_HVAR_pred;
    }

    public String getLRT_pred() {
        return LRT_pred;
    }

    public void setLRT_pred(String LRT_pred) {
        this.LRT_pred = LRT_pred;
    }

    public String getMutationTaster_pred() {
        return MutationTaster_pred;
    }

    public void setMutationTaster_pred(String MutationTaster_pred) {
        this.MutationTaster_pred = MutationTaster_pred;
    }

    public String getMutationAssessor_pred() {
        return MutationAssessor_pred;
    }

    public void setMutationAssessor_pred(String MutationAssessor_pred) {
        this.MutationAssessor_pred = MutationAssessor_pred;
    }

    public String getFATHMM_pred() {
        return FATHMM_pred;
    }

    public void setFATHMM_pred(String FATHMM_pred) {
        this.FATHMM_pred = FATHMM_pred;
    }

    public String getPROVEAN_pred() {
        return PROVEAN_pred;
    }

    public void setPROVEAN_pred(String PROVEAN_pred) {
        this.PROVEAN_pred = PROVEAN_pred;
    }

    public String getFathmm_MKL_coding_pred() {
        return fathmm_MKL_coding_pred;
    }

    public void setFathmm_MKL_coding_pred(String fathmm_MKL_coding_pred) {
        this.fathmm_MKL_coding_pred = fathmm_MKL_coding_pred;
    }

    public String getMetaSVM_pred() {
        return MetaSVM_pred;
    }

    public void setMetaSVM_pred(String MetaSVM_pred) {
        this.MetaSVM_pred = MetaSVM_pred;
    }

    public String getMetaLR_pred() {
        return MetaLR_pred;
    }

    public void setMetaLR_pred(String MetaLR_pred) {
        this.MetaLR_pred = MetaLR_pred;
    }

    public String getGERP_RS() {
        return GERP_RS;
    }

    public void setGERP_RS(String GERP_RS) {
        this.GERP_RS = GERP_RS;
    }

    public String getRsID() {
        return rsID;
    }

    public void setRsID(String rsID) {
        this.rsID = rsID;
    }


    






 public String toString2() {
        final List<?> fields = CollectionUtil.makeList(Chromosome,Position,Ref,Mut,SampleID,Depth,Alt_count,MutAF_tumor,Alleles_tumor,Depth_normal,Alt_count_normal,MutAF_normal,Alleles_normal,consensus_base_tumor,consensus_quality_tumor,snp_quality_tumor,Function_region,Mutation_type,Gene_name,Gene_ID,Codon_change,AA_change,CDNA_change,ExonicFunc,GeneDetail,Primary_site,Cancer_type,COSMIC_ID,M1KG_MAF_ALL,M1KG_MAF_AFR,M1KG_MAF_AMR,M1KG_MAF_EAS,M1KG_MAF_EUR,M1KG_MAF_SAS,GeneSymbol,ClinicalSignificance,PhenotypeIDs,SIFT_pred,Polyphen2_HDIV_pred,Polyphen2_HVAR_pred,LRT_pred,MutationTaster_pred,MutationAssessor_pred,FATHMM_pred,PROVEAN_pred,fathmm_MKL_coding_pred,MetaSVM_pred,MetaLR_pred,GERP_RS,rsID);
        String str = fields.stream().map(String::valueOf).collect(Collectors.joining("\t"));
        return str;
    }
 public String toStringindel() {
        final List<?> fields = CollectionUtil.makeList(Chromosome,Position,Ref,Mut,SampleID,Depth,Alt_count,Function_region,Mutation_type,Gene_name,Gene_ID,Codon_change,AA_change,CDNA_change,ExonicFunc,GeneDetail,GeneSymbol,ClinicalSignificance,PhenotypeIDs,SIFT_pred,Polyphen2_HDIV_pred,Polyphen2_HVAR_pred,MutationTaster_pred,MutationAssessor_pred,FATHMM_pred,PROVEAN_pred,fathmm_MKL_coding_pred);
        String str = fields.stream().map(String::valueOf).collect(Collectors.joining("\t"));
        return str;
    }
}
