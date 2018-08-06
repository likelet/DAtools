/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package ExomeSeqAnalysisPipe.CNVanalysis.ADTex;

/**
 *
 * @author Administrator
 * @since 2018-8-1
 * @coding time 15:42:49
 * @author Qi Zhao
 */
public class ADTexCNVterm {

    private String chr;
    private int exon_start;
    private int exon_end;
    private int cnv;
    private double tumor_DOC;
    private double control_DOC;
    private double rationormalized_after_smoothing;
    private int CNV_start;
    private int CNV_end;
    private double seg_mean;

    public ADTexCNVterm(String chr, String exon_start, String exon_end, String cnv, String tumor_DOC, String control_DOC, String rationormalized_after_smoothing, String CNV_start, String CNV_end, String seg_mean) {
        this.chr = chr;
        this.exon_start = Integer.parseInt(exon_start);
        this.exon_end = Integer.parseInt(exon_end);
        this.cnv = Integer.parseInt(cnv);
        this.tumor_DOC = Double.parseDouble(tumor_DOC);
        this.control_DOC = Double.parseDouble(control_DOC);
        this.rationormalized_after_smoothing = Double.parseDouble(rationormalized_after_smoothing);
        this.CNV_start = Integer.parseInt(CNV_start);
        this.CNV_end = Integer.parseInt(CNV_end);
        this.seg_mean = Double.parseDouble(seg_mean);
    }

    
    
    
    /**
     * Get the value of seg_mean
     *
     * @return the value of seg_mean
     */
    public double getSeg_mean() {
        return seg_mean;
    }

    /**
     * Set the value of seg_mean
     *
     * @param seg_mean new value of seg_mean
     */
    public void setSeg_mean(double seg_mean) {
        this.seg_mean = seg_mean;
    }

    

    /**
     * Get the value of CNV_end
     *
     * @return the value of CNV_end
     */
    public int getCNV_end() {
        return CNV_end;
    }

    /**
     * Set the value of CNV_end
     *
     * @param CNV_end new value of CNV_end
     */
    public void setCNV_end(int CNV_end) {
        this.CNV_end = CNV_end;
    }


    /**
     * Get the value of CNV_start
     *
     * @return the value of CNV_start
     */
    public int getCNV_start() {
        return CNV_start;
    }

    /**
     * Set the value of CNV_start
     *
     * @param CNV_start new value of CNV_start
     */
    public void setCNV_start(int CNV_start) {
        this.CNV_start = CNV_start;
    }


    /**
     * Get the value of rationormalized_after_smoothing
     *
     * @return the value of rationormalized_after_smoothing
     */
    public double getRationormalized_after_smoothing() {
        return rationormalized_after_smoothing;
    }

    /**
     * Set the value of rationormalized_after_smoothing
     *
     * @param rationormalized_after_smoothing new value of
     * rationormalized_after_smoothing
     */
    public void setRationormalized_after_smoothing(double rationormalized_after_smoothing) {
        this.rationormalized_after_smoothing = rationormalized_after_smoothing;
    }

    /**
     * Get the value of control_DOC
     *
     * @return the value of control_DOC
     */
    public double getControl_DOC() {
        return control_DOC;
    }

    /**
     * Set the value of control_DOC
     *
     * @param control_DOC new value of control_DOC
     */
    public void setControl_DOC(double control_DOC) {
        this.control_DOC = control_DOC;
    }


    /**
     * Get the value of tumor_DOC
     *
     * @return the value of tumor_DOC
     */
    public double getTumor_DOC() {
        return tumor_DOC;
    }

    /**
     * Set the value of tumor_DOC
     *
     * @param tumor_DOC new value of tumor_DOC
     */
    public void setTumor_DOC(double tumor_DOC) {
        this.tumor_DOC = tumor_DOC;
    }


    /**
     * Get the value of cnv
     *
     * @return the value of cnv
     */
    public int getCnv() {
        return cnv;
    }

    /**
     * Set the value of cnv
     *
     * @param cnv new value of cnv
     */
    public void setCnv(int cnv) {
        this.cnv = cnv;
    }


    /**
     * Get the value of exon_end
     *
     * @return the value of exon_end
     */
    public int getExon_end() {
        return exon_end;
    }

    /**
     * Set the value of exon_end
     *
     * @param exon_end new value of exon_end
     */
    public void setExon_end(int exon_end) {
        this.exon_end = exon_end;
    }


    /**
     * Get the value of exon_start
     *
     * @return the value of exon_start
     */
    public int getExon_start() {
        return exon_start;
    }

    /**
     * Set the value of exon_start
     *
     * @param exon_start new value of exon_start
     */
    public void setExon_start(int exon_start) {
        this.exon_start = exon_start;
    }


    /**
     * Get the value of chr
     *
     * @return the value of chr
     */
    public String getChr() {
        return chr;
    }

    /**
     * Set the value of chr
     *
     * @param chr new value of chr
     */
    public void setChr(String chr) {
        this.chr = chr;
    }

    
}
