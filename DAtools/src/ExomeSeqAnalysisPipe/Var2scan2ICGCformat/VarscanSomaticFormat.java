/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package ExomeSeqAnalysisPipe.Var2scan2ICGCformat;

/**
 *
 * @author ZHAO Qi
 * @date 2015-7-29 16:56:13
 * @version 1.6.0
 */
public class VarscanSomaticFormat {
 public String chrom;//CHROM;//Chromosome or reference name
 public String position;//POS;//Position from pileup (1-based)
 public String ref;//REF;//Reference base at this position
 public String var;//ALT;//Variant base seen in tumor
 public String normal_reads1;//RD (col 10);//Reads supporting reference in normal
 public String normal_reads2;//AD (col 10);//Reads supporting variant in normal
 public String normal_var_freq;//FREQ(col 10);//Variant allele frequency in normal
 public String normal_gt;//GT (col 10);//Consensus genotype call in normal
 public String tumor_reads1;//RD (col 11);//Reads supporting reference in tumor
 public String tumor_reads2;//AD (col 11);//Reads supporting variant in tumor
 public String tumor_var_freq;//FREQ(col 11);//Variant allele frequency in tumor
 public String tumor_gt;//GT (col 11);//Consensus genotype call in tumor
 public String somatic_status;//SS (col 8);//Somatic status call (Germline, Somatic, LOH, or Unknown)
 public String variant_p_value;//GPV (col 8);//Variant p-value for Germline events
 public String somatic_p_value;//SPV (col 8);//Somatic p-value for Somatic/LOH events
 public String tumor_reads1_plus;//DP4 (col 11);//Tumor reference-supporting reads on + strand
 public String tumor_reads1_minus;//DP4 (col 11);//Tumor reference-supporting reads on - strand
 public String tumor_reads2_plus;//DP4 (col 11);//Tumor variant-supporting reads on + strand
 public String tumor_reads2_minus  ;//DP4 (col 11);//Tumor variant-supporting reads on - strand
 public String normal_reads1_plus;//DP4 (col 10);//Normal reference-supporting reads on + strand
 public String normal_reads1_minus;//DP4 (col 10);//Normal reference-supporting reads on - strand
 public String normal_reads2_plus;//DP4 (col 10);//Normal variant-supporting reads on + strand
 public String normal_reads2_minus;//DP4 (col 10);//Normal variant-supporting reads on - strand 

    public VarscanSomaticFormat(String chrom, String position, String ref, String var, String normal_reads1, String normal_reads2, String normal_var_freq, String normal_gt, String tumor_reads1, String tumor_reads2, String tumor_var_freq, String tumor_gt, String somatic_status, String variant_p_value, String somatic_p_value, String tumor_reads1_plus, String tumor_reads1_minus, String tumor_reads2_plus, String tumor_reads2_minus, String normal_reads1_plus, String normal_reads1_minus, String normal_reads2_plus, String normal_reads2_minus) {
        this.chrom = chrom;
        this.position = position;
        this.ref = ref;
        this.var = var;
        this.normal_reads1 = normal_reads1;
        this.normal_reads2 = normal_reads2;
        this.normal_var_freq = normal_var_freq;
        this.normal_gt = normal_gt;
        this.tumor_reads1 = tumor_reads1;
        this.tumor_reads2 = tumor_reads2;
        this.tumor_var_freq = tumor_var_freq;
        this.tumor_gt = tumor_gt;
        this.somatic_status = somatic_status;
        this.variant_p_value = variant_p_value;
        this.somatic_p_value = somatic_p_value;
        this.tumor_reads1_plus = tumor_reads1_plus;
        this.tumor_reads1_minus = tumor_reads1_minus;
        this.tumor_reads2_plus = tumor_reads2_plus;
        this.tumor_reads2_minus = tumor_reads2_minus;
        this.normal_reads1_plus = normal_reads1_plus;
        this.normal_reads1_minus = normal_reads1_minus;
        this.normal_reads2_plus = normal_reads2_plus;
        this.normal_reads2_minus = normal_reads2_minus;
    }

 
 
    public String getChrom() {
        return chrom;
    }

    public void setChrom(String chrom) {
        this.chrom = chrom;
    }

    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position;
    }

    public String getRef() {
        return ref;
    }

    public void setRef(String ref) {
        this.ref = ref;
    }

    public String getVar() {
        return var;
    }

    public void setVar(String var) {
        this.var = var;
    }

    public String getNormal_reads1() {
        return normal_reads1;
    }

    public void setNormal_reads1(String normal_reads1) {
        this.normal_reads1 = normal_reads1;
    }

    public String getNormal_reads2() {
        return normal_reads2;
    }

    public void setNormal_reads2(String normal_reads2) {
        this.normal_reads2 = normal_reads2;
    }

    public String getNormal_var_freq() {
        return normal_var_freq;
    }

    public void setNormal_var_freq(String normal_var_freq) {
        this.normal_var_freq = normal_var_freq;
    }

    public String getNormal_gt() {
        return normal_gt;
    }

    public void setNormal_gt(String normal_gt) {
        this.normal_gt = normal_gt;
    }

    public String getTumor_reads1() {
        return tumor_reads1;
    }

    public void setTumor_reads1(String tumor_reads1) {
        this.tumor_reads1 = tumor_reads1;
    }

    public String getTumor_reads2() {
        return tumor_reads2;
    }

    public void setTumor_reads2(String tumor_reads2) {
        this.tumor_reads2 = tumor_reads2;
    }

    public String getTumor_var_freq() {
        return tumor_var_freq;
    }

    public void setTumor_var_freq(String tumor_var_freq) {
        this.tumor_var_freq = tumor_var_freq;
    }

    public String getTumor_gt() {
        return tumor_gt;
    }

    public void setTumor_gt(String tumor_gt) {
        this.tumor_gt = tumor_gt;
    }

    public String getSomatic_status() {
        return somatic_status;
    }

    public void setSomatic_status(String somatic_status) {
        this.somatic_status = somatic_status;
    }

    public String getVariant_p_value() {
        return variant_p_value;
    }

    public void setVariant_p_value(String variant_p_value) {
        this.variant_p_value = variant_p_value;
    }

    public String getSomatic_p_value() {
        return somatic_p_value;
    }

    public void setSomatic_p_value(String somatic_p_value) {
        this.somatic_p_value = somatic_p_value;
    }

    public String getTumor_reads1_plus() {
        return tumor_reads1_plus;
    }

    public void setTumor_reads1_plus(String tumor_reads1_plus) {
        this.tumor_reads1_plus = tumor_reads1_plus;
    }

    public String getTumor_reads1_minus() {
        return tumor_reads1_minus;
    }

    public void setTumor_reads1_minus(String tumor_reads1_minus) {
        this.tumor_reads1_minus = tumor_reads1_minus;
    }

    public String getTumor_reads2_plus() {
        return tumor_reads2_plus;
    }

    public void setTumor_reads2_plus(String tumor_reads2_plus) {
        this.tumor_reads2_plus = tumor_reads2_plus;
    }

    public String getTumor_reads2_minus() {
        return tumor_reads2_minus;
    }

    public void setTumor_reads2_minus(String tumor_reads2_minus) {
        this.tumor_reads2_minus = tumor_reads2_minus;
    }

    public String getNormal_reads1_plus() {
        return normal_reads1_plus;
    }

    public void setNormal_reads1_plus(String normal_reads1_plus) {
        this.normal_reads1_plus = normal_reads1_plus;
    }

    public String getNormal_reads1_minus() {
        return normal_reads1_minus;
    }

    public void setNormal_reads1_minus(String normal_reads1_minus) {
        this.normal_reads1_minus = normal_reads1_minus;
    }

    public String getNormal_reads2_plus() {
        return normal_reads2_plus;
    }

    public void setNormal_reads2_plus(String normal_reads2_plus) {
        this.normal_reads2_plus = normal_reads2_plus;
    }

    public String getNormal_reads2_minus() {
        return normal_reads2_minus;
    }

    public void setNormal_reads2_minus(String normal_reads2_minus) {
        this.normal_reads2_minus = normal_reads2_minus;
    }

 
    @Override
    public String toString() {
        return chrom + "\t" + position + "\t" + ref + "\t" + var + "\t" + normal_reads1 + "\t" + normal_reads2 + "\t" + normal_var_freq + "\t" + normal_gt + "\t" + tumor_reads1 + "\t" + tumor_reads2 + "\t" + tumor_var_freq + "\t" + tumor_gt + "\t" + somatic_status + "\t" + variant_p_value + "\t" + somatic_p_value + "\t" + tumor_reads1_plus + "\t" + tumor_reads1_minus + "\t" + tumor_reads2_plus + "\t" + tumor_reads2_minus +"\t" + normal_reads1_plus + "\t" + normal_reads1_minus + "\t" + normal_reads2_plus + "\t" + normal_reads2_minus;
    }

 
 
}
