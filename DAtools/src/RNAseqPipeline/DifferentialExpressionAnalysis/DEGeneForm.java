package RNAseqPipeline.DifferentialExpressionAnalysis;
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author zhengyueyuan
 */
public class DEGeneForm {
    private String geneName;
    private int sample1Counts;
    private int sample2Counts;
    private double sample1FPKM;
    private double sample2FPKM;
    private double log2FC;
    private double pValue;
    private double FDR;

    public DEGeneForm(String geneName, int sample1Counts, int sample2Counts, double sample1FPKM, double sample2FPKM, double log2FC) {
        this.geneName = geneName;
        this.sample1Counts = sample1Counts;
        this.sample2Counts = sample2Counts;
        this.sample1FPKM = sample1FPKM;
        this.sample2FPKM = sample2FPKM;
        this.log2FC = log2FC;
    }

    public DEGeneForm(String geneName, int sample1Counts, int sample2Counts, double sample1FPKM, double sample2FPKM, double log2FC, double pValue) {
        this.geneName = geneName;
        this.sample1Counts = sample1Counts;
        this.sample2Counts = sample2Counts;
        this.sample1FPKM = sample1FPKM;
        this.sample2FPKM = sample2FPKM;
        this.log2FC = log2FC;
        this.pValue = pValue;
    }
    
    public DEGeneForm(String geneName, int sample1Counts, int sample2Counts, double sample1FPKM, double sample2FPKM, double log2FC, double pValue, double FDR) {
        this.geneName = geneName;
        this.sample1Counts = sample1Counts;
        this.sample2Counts = sample2Counts;
        this.sample1FPKM = sample1FPKM;
        this.sample2FPKM = sample2FPKM;
        this.log2FC = log2FC;
        this.pValue = pValue;
        this.FDR = FDR;
    }

    public String getGeneName() {
        return geneName;
    }

    public void setGeneName(String geneName) {
        this.geneName = geneName;
    }

    public int getSample1Counts() {
        return sample1Counts;
    }

    public void setSample1Counts(int sample1Counts) {
        this.sample1Counts = sample1Counts;
    }

    public int getSample2Counts() {
        return sample2Counts;
    }

    public void setSample2Counts(int sample2Counts) {
        this.sample2Counts = sample2Counts;
    }

    public double getSample1FPKM() {
        return sample1FPKM;
    }

    public void setSample1FPKM(double sample1FPKM) {
        this.sample1FPKM = sample1FPKM;
    }

    public double getSample2FPKM() {
        return sample2FPKM;
    }

    public void setSample2FPKM(double sample2FPKM) {
        this.sample2FPKM = sample2FPKM;
    }

    public double getLog2FC() {
        return log2FC;
    }

    public void setLog2FC(double log2FC) {
        this.log2FC = log2FC;
    }

    public double getpValue() {
        return pValue;
    }

    public void setpValue(double pValue) {
        this.pValue = pValue;
    }

    public double getFDR() {
        return FDR;
    }

    public void setFDR(double FDR) {
        this.FDR = FDR;
    }

    @Override
    public String toString() {
        return geneName + "\t" + sample1Counts + "\t" + sample2Counts + "\t" + sample1FPKM + "\t" + sample2FPKM + "\t" + log2FC + "\t" + pValue + "\t" + FDR ;
    }
    
            
}
