/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package ExomeSeqAnalysisPipe.Var2scan2ICGCformat;

/**
 *
 * @author ZHAO Qi
 * @date 2015-7-29 16:34:50
 * @version 1.6.0
 */
public class ICGCssmP {
 public String analysis_id ;//Unique identifier assigned to a computational analysis run that was performed on a single analyzed sample and any associated matched samples using a particular set of methods, tools, etc.; analysis IDs must be unique and must not be reused by a project across subsequent analysis runs. Cannot contain whitespace, semi-colons or special characters. Can contain alphanumeric characters, dashes or underscores.
 public String analyzed_sample_id ;//A unique identifier representing the particular sample that was analyzed; analyzed sample identifiers must be used consistently to relate those described in the sample file and in the related analysis files. Cannot contain whitespace, semi-colons or special characters. Can contain alphanumeric characters, dashes or underscores.
 public String mutation_type ;//Type of mutation
 public String chromosome ;//Name of the chromosome containing the mutation/variation
 public String chromosome_start ;//Chromosome start position of the feature being described.
 public String chromosome_end ;//Chromosome end position of the feature being described. 
 public String chromosome_strand="1" ;//Chromosome strand of mutation. Must always use forward strand (1)
 public String reference_genome_allele ;//Allele in the reference genome. For insertions, use a dash. For all other types of mutations, use forward strand DNA sequence at the reference genome position as specified in chromosome, chromosome_start and chromosome_end
 public String control_genotype ;//Genotype of the control sample. Use a dash for a missing allele.
 public String mutated_from_allele ;//Allele that is believed to be the original allele in the ancestral cell that was mutated in the tumour cell. Use dash for insertions. Mutated allele must be one of the two alleles in the control_genotype
 public String mutated_to_allele ;//The new allele identified in the tumour sample that is believed to be the resulting allele of a somatic mutation event during tumourgenesis. Use dash for deletions. Mutant allele must be one allele and must be one of the alleles defined in tumour_genotype
 public String tumour_genotype ;//Genotype of the tumour sample. Use a dash for each missing base
 public String expressed_allele="-777" ;//For heterozygous SNPs, the dominant allele as revealed by RNA-seq in the tumour sample. When multiple alleles are identified, up to four alleles can be specified, each separated by a comma (no whitespace allowed)
 public String quality_score="-888" ;//Quality score of the mutation call
 public String probability="-999" ;//Probability of the mutation call
 public String total_read_count ;//Total number of raw reads that cover the called mutation
 public String mutant_allele_read_count ;//Total number of raw reads that contain the mutant allele
 public String verification_status="2" ;//Indicate if the mutation/variation has been verified using an orthogonal genotype platform/technology
 public String verification_platform="-999" ;//Identifier of orthogonal platform or technology used to confirm that the mutation/variant is true; identifier should be taken from controlled vocabulary list of platforms recognized by the DCC and should directly correspond to the particular technology (including version) used. If no appropriate term exists for a given platform, please contact the DCC to request an addition to the CV terms.
 public String biological_validation_status="2" ;//Indicate if the mutation/variation has been observed in a larger cohort/sample size, and is not an artifact or consequence of sample selection for sequencing
 public String biological_validation_platform="-999";//Identifier of assay platform or technology used to validate the mutation/variant

    public String getAnalysis_id() {
        return analysis_id;
    }

    public void setAnalysis_id(String analysis_id) {
        this.analysis_id = analysis_id;
    }

    public String getAnalyzed_sample_id() {
        return analyzed_sample_id;
    }

    public void setAnalyzed_sample_id(String analyzed_sample_id) {
        this.analyzed_sample_id = analyzed_sample_id;
    }

    public String getMutation_type() {
        return mutation_type;
    }

    public void setMutation_type(String mutation_type) {
        this.mutation_type = mutation_type;
    }

    public String getChromosome() {
        return chromosome;
    }

    public void setChromosome(String chromosome) {
        this.chromosome = chromosome;
    }

    public String getChromosome_start() {
        return chromosome_start;
    }

    public void setChromosome_start(String chromosome_start) {
        this.chromosome_start = chromosome_start;
    }

    public String getChromosome_end() {
        return chromosome_end;
    }

    public void setChromosome_end(String chromosome_end) {
        this.chromosome_end = chromosome_end;
    }

    public String getChromosome_strand() {
        return chromosome_strand;
    }

    public void setChromosome_strand(String chromosome_strand) {
        this.chromosome_strand = chromosome_strand;
    }

    public String getReference_genome_allele() {
        return reference_genome_allele;
    }

    public void setReference_genome_allele(String reference_genome_allele) {
        this.reference_genome_allele = reference_genome_allele;
    }

    public String getControl_genotype() {
        return control_genotype;
    }

    public void setControl_genotype(String control_genotype) {
        this.control_genotype = control_genotype;
    }

    public String getMutated_from_allele() {
        return mutated_from_allele;
    }

    public void setMutated_from_allele(String mutated_from_allele) {
        this.mutated_from_allele = mutated_from_allele;
    }

    public String getMutated_to_allele() {
        return mutated_to_allele;
    }

    public void setMutated_to_allele(String mutated_to_allele) {
        this.mutated_to_allele = mutated_to_allele;
    }

    public String getTumour_genotype() {
        return tumour_genotype;
    }

    public void setTumour_genotype(String tumour_genotype) {
        this.tumour_genotype = tumour_genotype;
    }

    public String getExpressed_allele() {
        return expressed_allele;
    }

    public void setExpressed_allele(String expressed_allele) {
        this.expressed_allele = expressed_allele;
    }

    public String getQuality_score() {
        return quality_score;
    }

    public void setQuality_score(String quality_score) {
        this.quality_score = quality_score;
    }

    public String getProbability() {
        return probability;
    }

    public void setProbability(String probability) {
        this.probability = probability;
    }

    public String getTotal_read_count() {
        return total_read_count;
    }

    public void setTotal_read_count(String total_read_count) {
        this.total_read_count = total_read_count;
    }

    public String getMutant_allele_read_count() {
        return mutant_allele_read_count;
    }

    public void setMutant_allele_read_count(String mutant_allele_read_count) {
        this.mutant_allele_read_count = mutant_allele_read_count;
    }

    public String getVerification_status() {
        return verification_status;
    }

    public void setVerification_status(String verification_status) {
        this.verification_status = verification_status;
    }

    public String getVerification_platform() {
        return verification_platform;
    }

    public void setVerification_platform(String verification_platform) {
        this.verification_platform = verification_platform;
    }

    public String getBiological_validation_status() {
        return biological_validation_status;
    }

    public void setBiological_validation_status(String biological_validation_status) {
        this.biological_validation_status = biological_validation_status;
    }

    public String getBiological_validation_platform() {
        return biological_validation_platform;
    }

    public void setBiological_validation_platform(String biological_validation_platform) {
        this.biological_validation_platform = biological_validation_platform;
    }

    @Override
    public String toString() {
        return  analysis_id + "\t" + analyzed_sample_id + "\t" + mutation_type + "\t" + chromosome + "\t" + chromosome_start + "\t" + chromosome_end + "\t" + chromosome_strand + "\t" + reference_genome_allele + "\t" + control_genotype + "\t" + mutated_from_allele + "\t" + mutated_to_allele + "\t" + tumour_genotype + "\t" + expressed_allele + "\t" + quality_score + "\t" + probability + "\t" + total_read_count + "\t" + mutant_allele_read_count + "\t" + verification_status + "\t" + verification_platform + "\t" + biological_validation_status + "\t" + biological_validation_platform ;
    }

 
 
}
