/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package ExomeSeqAnalysisPipe.mafProcess.annovarOut2Maf;

/**
 * <p>MAFrecord</p>
 * <p>Created on 2015-12-22 17:50:35</p>
 * <p>Author Email: zhaoqi3@mail2.sysu.edu.cn</p>
 * @author ZHAO Qi
 * @date 2015-12-22 17:50:35
 * @version java 1.6.0
 * @version 
 */
public class MAFrecord {
    private String hugoSymbol;
    private String chromosome;
    private int startPosition;
    private int endPosition;
    private int strand;
    private String variantClassification;
    private String variantType;
    private String refAllele;
    private String tumorAllele;
    private String sampleID;

    public MAFrecord() {
    }

    
    public MAFrecord(String hugoSymbol, String chromosome, int startPosition, int endPosition, int strand, String variantClassification, String variantType, String refAllele, String tumorAllele, String sampleID) {
        this.hugoSymbol = hugoSymbol;
        this.chromosome = chromosome;
        this.startPosition = startPosition;
        this.endPosition = endPosition;
        this.strand = strand;
        this.variantClassification = variantClassification;
        this.variantType = variantType;
        this.refAllele = refAllele;
        this.tumorAllele = tumorAllele;
        this.sampleID = sampleID;
    }

    
    public String getVariantType() {
        return variantType;
    }

    public void setVariantType(String variantType) {
        this.variantType = variantType;
    }

    public String getSampleID() {
        return sampleID;
    }

    public void setSampleID(String sampleID) {
        this.sampleID = sampleID;
    }

    public String getHugoSymbol() {
        return hugoSymbol;
    }

    public void setHugoSymbol(String hugoSymbol) {
        this.hugoSymbol = hugoSymbol;
    }

    public String getChromosome() {
        return chromosome;
    }

    public void setChromosome(String chromosome) {
        this.chromosome = chromosome;
    }

    public int getStartPosition() {
        return startPosition;
    }

    public void setStartPosition(int startPosition) {
        this.startPosition = startPosition;
    }

    public int getEndPosition() {
        return endPosition;
    }

    public void setEndPosition(int endPosition) {
        this.endPosition = endPosition;
    }

    public int getStrand() {
        return strand;
    }

    public void setStrand(int strand) {
        this.strand = strand;
    }

    public String getVariantClassification() {
        return variantClassification;
    }

    public void setVariantClassification(String variantClassification) {
        this.variantClassification = variantClassification;
    }

    public String getRefAllele() {
        return refAllele;
    }

    public void setRefAllele(String refAllele) {
        this.refAllele = refAllele;
    }

    public String getTumorAllele() {
        return tumorAllele;
    }

    public void setTumorAllele(String tumorAllele) {
        this.tumorAllele = tumorAllele;
    }
    
    
    public String toString()
    {
        String retStr;
        retStr = chromosome + "-" + startPosition + "-" + endPosition + "-" + strand + "-" + refAllele + "-" + tumorAllele + "-" + variantType + "-" + sampleID;
        return retStr;
    }


}
