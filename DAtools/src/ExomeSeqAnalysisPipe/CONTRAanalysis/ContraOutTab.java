/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ExomeSeqAnalysisPipe.CONTRAanalysis;

/**
 * <p>
 * ContraOutTab</p>
 * <p>
 * Created on 2015-12-14 11:11:02</p>
 * <p>
 * Author Email: zhaoqi3@mail2.sysu.edu.cn</p>
 *
 * @author ZHAO Qi
 * @date 2015-12-14 11:11:02
 * @version java 1.6.0
 * @version
 */
public class ContraOutTab {

    private String Targeted_Region_ID="";
    private double Exon_Number=0;
    private String Gene_Sym;
    private String Chr;
    private double OriStCoordinate;
    private double OriEndCoordinate;
    private double Mean_of_LogRatio=0;
    private double Adjusted_Mean_of_LogRatio=0;
    private double SD_of_LogRatio=0;
    private double Median_of_LogRatio=0;
    private double number_bases=0;
    private double P_Value=0;
    private double Adjusted_P_Value=0;
    private String gain_loss="";
    private double tumour_rd=0;
    private double normal_rd=0;
    private double tumour_rd_ori=0;
    private double normal_rd_ori=0;
    private double MinLogRatio=0;
    private double MaxLogRatio=0;
    private double BinNumber=0;

    public ContraOutTab(String Gene_Sym, String Chr, double OriStCoordinate, double OriEndCoordinate) {
        this.Gene_Sym = Gene_Sym;
        this.Chr = Chr;
        this.OriStCoordinate = OriStCoordinate;
        this.OriEndCoordinate = OriEndCoordinate;
    }

    
    
    
    
    public ContraOutTab(String Targeted_Region_ID, double Exon_Number, String Gene_Sym, String Chr, double OriStCoordinate, double OriEndCoordinate, double Mean_of_LogRatio, double Adjusted_Mean_of_LogRatio, double SD_of_LogRatio, double Median_of_LogRatio, double number_bases, double P_Value, double Adjusted_P_Value, String gain_loss, double tumour_rd, double normal_rd, double tumour_rd_ori, double normal_rd_ori, double MinLogRatio, double MaxLogRatio, double BinNumber) {
        this.Targeted_Region_ID = Targeted_Region_ID;
        this.Exon_Number = Exon_Number;
        this.Gene_Sym = Gene_Sym;
        this.Chr = Chr;
        this.OriStCoordinate = OriStCoordinate;
        this.OriEndCoordinate = OriEndCoordinate;
        this.Mean_of_LogRatio = Mean_of_LogRatio;
        this.Adjusted_Mean_of_LogRatio = Adjusted_Mean_of_LogRatio;
        this.SD_of_LogRatio = SD_of_LogRatio;
        this.Median_of_LogRatio = Median_of_LogRatio;
        this.number_bases = number_bases;
        this.P_Value = P_Value;
        this.Adjusted_P_Value = Adjusted_P_Value;
        this.gain_loss = gain_loss;
        this.tumour_rd = tumour_rd;
        this.normal_rd = normal_rd;
        this.tumour_rd_ori = tumour_rd_ori;
        this.normal_rd_ori = normal_rd_ori;
        this.MinLogRatio = MinLogRatio;
        this.MaxLogRatio = MaxLogRatio;
        this.BinNumber = BinNumber;
    }

    public String getTargeted_Region_ID() {
        return Targeted_Region_ID;
    }

    public void setTargeted_Region_ID(String Targeted_Region_ID) {
        this.Targeted_Region_ID = Targeted_Region_ID;
    }

    public double getExon_Number() {
        return Exon_Number;
    }

    public void setExon_Number(double Exon_Number) {
        this.Exon_Number = Exon_Number;
    }

    public String getGene_Sym() {
        return Gene_Sym;
    }

    public void setGene_Sym(String Gene_Sym) {
        this.Gene_Sym = Gene_Sym;
    }

    public String getChr() {
        return Chr;
    }

    public void setChr(String Chr) {
        this.Chr = Chr;
    }

    public double getOriStCoordinate() {
        return OriStCoordinate;
    }

    public void setOriStCoordinate(double OriStCoordinate) {
        this.OriStCoordinate = OriStCoordinate;
    }

    public double getOriEndCoordinate() {
        return OriEndCoordinate;
    }

    public void setOriEndCoordinate(double OriEndCoordinate) {
        this.OriEndCoordinate = OriEndCoordinate;
    }

    public double getMean_of_LogRatio() {
        return Mean_of_LogRatio;
    }

    public void setMean_of_LogRatio(double Mean_of_LogRatio) {
        this.Mean_of_LogRatio = Mean_of_LogRatio;
    }

    public double getAdjusted_Mean_of_LogRatio() {
        return Adjusted_Mean_of_LogRatio;
    }

    public void setAdjusted_Mean_of_LogRatio(double Adjusted_Mean_of_LogRatio) {
        this.Adjusted_Mean_of_LogRatio = Adjusted_Mean_of_LogRatio;
    }

    public double getSD_of_LogRatio() {
        return SD_of_LogRatio;
    }

    public void setSD_of_LogRatio(double SD_of_LogRatio) {
        this.SD_of_LogRatio = SD_of_LogRatio;
    }

    public double getMedian_of_LogRatio() {
        return Median_of_LogRatio;
    }

    public void setMedian_of_LogRatio(double Median_of_LogRatio) {
        this.Median_of_LogRatio = Median_of_LogRatio;
    }

    public double getNumber_bases() {
        return number_bases;
    }

    public void setNumber_bases(double number_bases) {
        this.number_bases = number_bases;
    }

    public double getP_Value() {
        return P_Value;
    }

    public void setP_Value(double P_Value) {
        this.P_Value = P_Value;
    }

    public double getAdjusted_P_Value() {
        return Adjusted_P_Value;
    }

    public void setAdjusted_P_Value(double Adjusted_P_Value) {
        this.Adjusted_P_Value = Adjusted_P_Value;
    }

    public String getGain_loss() {
        return gain_loss;
    }

    public void setGain_loss(String gain_loss) {
        this.gain_loss = gain_loss;
    }

    public double getTumour_rd() {
        return tumour_rd;
    }

    public void setTumour_rd(double tumour_rd) {
        this.tumour_rd = tumour_rd;
    }

    public double getNormal_rd() {
        return normal_rd;
    }

    public void setNormal_rd(double normal_rd) {
        this.normal_rd = normal_rd;
    }

    public double getTumour_rd_ori() {
        return tumour_rd_ori;
    }

    public void setTumour_rd_ori(double tumour_rd_ori) {
        this.tumour_rd_ori = tumour_rd_ori;
    }

    public double getNormal_rd_ori() {
        return normal_rd_ori;
    }

    public void setNormal_rd_ori(double normal_rd_ori) {
        this.normal_rd_ori = normal_rd_ori;
    }

    public double getMinLogRatio() {
        return MinLogRatio;
    }

    public void setMinLogRatio(double MinLogRatio) {
        this.MinLogRatio = MinLogRatio;
    }

    public double getMaxLogRatio() {
        return MaxLogRatio;
    }

    public void setMaxLogRatio(double MaxLogRatio) {
        this.MaxLogRatio = MaxLogRatio;
    }

    public double getBinNumber() {
        return BinNumber;
    }

    public void setBinNumber(double BinNumber) {
        this.BinNumber = BinNumber;
    }

    

}
