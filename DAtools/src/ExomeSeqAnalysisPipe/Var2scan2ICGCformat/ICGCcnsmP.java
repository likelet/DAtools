/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package ExomeSeqAnalysisPipe.Var2scan2ICGCformat;

/**
 *
 * @author ZHAO Qi
 * @date 2015-7-29 16:35:10
 * @version 1.6.0
 */
public class ICGCcnsmP {
public String analysis_id;//Unique identifier assigned to a computational analysis run that was performed on a single analyzed sample and any associated matched samples using a particular set of methods, tools, etc.; analysis IDs must be unique and must not be reused by a project across subsequent analysis runs. Cannot contain whitespace, semi-colons or special characters. Can contain alphanumeric characters, dashes or underscores.
public String analyzed_sample_id;//A unique identifier representing the particular sample that was analyzed; analyzed sample identifiers must be used consistently to relate those described in the sample file and in the related analysis files
public String mutation_id;//Unique identifier assigned to a single mutation observation; must be unique within the scope of all data produced by a project
public String start_probe_id="-999";//Probe id containing the chromosome_start if array platform was used
public String end_probe_id="-999";//Probe id containing the chromosome_end if array platform was used
public String chromosome;//Name of the chromosome containing the mutation/variation
public String chromosome_start;//Chromosome start position of the feature being described
public String chromosome_end;//Chromosome end position of the feature being described
public String chromosome_start_range;//Number of bases around chromosome_start that may contain the start position
public String chromosome_end_range;//Number of bases around chromosome_end that may contain the end position
public String mutation_type;//Type of mutation
public String copy_number;//DNA copy number estimated
public String segment_mean;//Mean LRR per segment
public String segment_median;//Median LRR per segment
public String quality_score;//Quality score of the observation
public String probability;//Probability of the observation
public String is_annotated="1";//Indicate if the mutation/variation is annotated in dbSNP
public String verification_status="1";//Indicate if the mutation/variation has been verified using an independent orthogonal genotype platform/technology
public String verification_platform="60";//Identifier of orthogonal platform or technology used to confirm that the mutation/variant is true; identifier should be taken from controlled vocabulary list of platforms recognized by the DCC and should directly correspond to the particular technology (including version) used. If no appropriate term exists for a given platform, please contact the DCC to request an addition to the CV terms.
public String biological_validation_status="1";//Indicate if the mutation/variation has been observed in a larger cohort/sample size, and is not an artifact or consequence of sample selection for sequencing
public String biological_validation_platform="60";//Identifier of assay platform or technology used to validate the mutation/variant

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

    public String getMutation_id() {
        return mutation_id;
    }

    public void setMutation_id(String mutation_id) {
        this.mutation_id = mutation_id;
    }

    public String getStart_probe_id() {
        return start_probe_id;
    }

    public void setStart_probe_id(String start_probe_id) {
        this.start_probe_id = start_probe_id;
    }

    public String getEnd_probe_id() {
        return end_probe_id;
    }

    public void setEnd_probe_id(String end_probe_id) {
        this.end_probe_id = end_probe_id;
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

    public String getChromosome_start_range() {
        return chromosome_start_range;
    }

    public void setChromosome_start_range(String chromosome_start_range) {
        this.chromosome_start_range = chromosome_start_range;
    }

    public String getChromosome_end_range() {
        return chromosome_end_range;
    }

    public void setChromosome_end_range(String chromosome_end_range) {
        this.chromosome_end_range = chromosome_end_range;
    }

    public String getMutation_type() {
        return mutation_type;
    }

    public void setMutation_type(String mutation_type) {
        this.mutation_type = mutation_type;
    }

    public String getCopy_number() {
        return copy_number;
    }

    public void setCopy_number(String copy_number) {
        this.copy_number = copy_number;
    }

    public String getSegment_mean() {
        return segment_mean;
    }

    public void setSegment_mean(String segment_mean) {
        this.segment_mean = segment_mean;
    }

    public String getSegment_median() {
        return segment_median;
    }

    public void setSegment_median(String segment_median) {
        this.segment_median = segment_median;
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

    public String getIs_annotated() {
        return is_annotated;
    }

    public void setIs_annotated(String is_annotated) {
        this.is_annotated = is_annotated;
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
        return analysis_id+"\t"+analyzed_sample_id+"\t"+mutation_id+"\t"+start_probe_id+"\t"+end_probe_id+"\t"+chromosome+"\t"+chromosome_start+"\t"+chromosome_end+"\t"+chromosome_start_range+"\t"+chromosome_end_range+"\t"+mutation_type+"\t"+copy_number+"\t"+segment_mean+"\t"+segment_median+"\t"+quality_score+"\t"+probability+"\t"+is_annotated+"\t"+verification_status+"\t"+verification_platform+"\t"+biological_validation_status+"\t"+biological_validation_platform;
    }

    
}
