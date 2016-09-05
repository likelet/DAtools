/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ExomeSeqAnalysisPipe.Var2scan2ICGCformat;

/**
 *
 * @author ZHAO Qi
 * @date 2015-7-29 17:10:23
 * @version 1.6.0
 */
public class VarscanCNVFormat {

    public String chrom;//Chromosome or reference name
    public String chr_start;//Start position of a contiguous copy number rgion (1-based)
    public String chr_stop;//Stop position of a contiguous copy number rgion (1-based)
    public String normal_depth;//Average sequence depth in the normal
    public String tumor_depth;//Average sequence depth in the tumor
    public String log2_ratio;//Log-base-2 ratio of the tumor/normal depth ratio
    public String gc_content;//Proportion of GC bases in the region, between 0 and 100 (v2.2.7 and later)

    public VarscanCNVFormat(String chrom, String chr_start, String chr_stop, String normal_depth, String tumor_depth, String log2_ratio, String gc_content) {
        this.chrom = chrom;
        this.chr_start = chr_start;
        this.chr_stop = chr_stop;
        this.normal_depth = normal_depth;
        this.tumor_depth = tumor_depth;
        this.log2_ratio = log2_ratio;
        this.gc_content = gc_content;
    }

    public String getChrom() {
        return chrom;
    }

    public void setChrom(String chrom) {
        this.chrom = chrom;
    }

    public String getChr_start() {
        return chr_start;
    }

    public void setChr_start(String chr_start) {
        this.chr_start = chr_start;
    }

    public String getChr_stop() {
        return chr_stop;
    }

    public void setChr_stop(String chr_stop) {
        this.chr_stop = chr_stop;
    }

    public String getNormal_depth() {
        return normal_depth;
    }

    public void setNormal_depth(String normal_depth) {
        this.normal_depth = normal_depth;
    }

    public String getTumor_depth() {
        return tumor_depth;
    }

    public void setTumor_depth(String tumor_depth) {
        this.tumor_depth = tumor_depth;
    }

    public String getLog2_ratio() {
        return log2_ratio;
    }

    public void setLog2_ratio(String log2_ratio) {
        this.log2_ratio = log2_ratio;
    }

    public String getGc_content() {
        return gc_content;
    }

    public void setGc_content(String gc_content) {
        this.gc_content = gc_content;
    }

    @Override
    public String toString() {
        return chrom + "\t" + chr_start + "\t" + chr_stop + "\t" + normal_depth + "\t" + normal_depth + "\t" + tumor_depth + "\t" + log2_ratio + "\t" + gc_content;
    }

}
