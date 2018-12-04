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

    private String geneName="";
    private int sample1Counts=0;
    private int sample2Counts=0;
    private double sample1FPKM=0;
    private double sample2FPKM=0;
    private double log2FC=0;
    private double pValue=1;
    private double FDR=1;
    private String geneNameDetails="";// for RSEM
    private String genetype="";//RSEM

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

    public String getGeneNameDetails() {
        return geneNameDetails;
    }

    public void setGeneNameDetails(String geneNameDetails) {
        this.geneNameDetails = geneNameDetails;
    }

    public String getGenetype() {
        return genetype;
    }

    public void setGenetype(String genetype) {
        this.genetype = genetype;
    }

    @Override
    public String toString() {
        return geneName + "\t" + sample1Counts + "\t" + sample2Counts + "\t" + sample1FPKM + "\t" + sample2FPKM + "\t" + log2FC + "\t" + pValue + "\t" + FDR;
    }

    public String toString2() {
        return geneName  + "\t" + sample1Counts + "\t" + sample2Counts + "\t" + sample1FPKM + "\t" + sample2FPKM + "\t" + log2FC + "\t" + pValue + "\t" + FDR+ "\t" + geneNameDetails + "\t" + genetype;
    }

}
