/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Algorithm.MultipleCorrection;

import pub.OrderBaseRecord;

;


/**
 *
 * @author zhengyueyuan
 */
public class FDRForm implements OrderBaseRecord{
    private double pvalue;
    private int index;
    private double fdr;

    public FDRForm(double pvalue, int index, double fdr) {
        this.pvalue = pvalue;
        this.index = index;
        this.fdr = fdr;
    }

    public double getPvalue() {
        return pvalue;
    }

    public void setPvalue(double pvalue) {
        this.pvalue = pvalue;
    }

    public int getIndex() {
        return index;
    }

    public void setIndex(int index) {
        this.index = index;
    }

    public double getFdr() {
        return fdr;
    }

    public void setFdr(double fdr) {
        this.fdr = fdr;
    }
    

    @Override
    public double getFirst() {
        return index;
    }

    @Override
    public String toString() {
        return "pvalue=" + pvalue + ", index=" + index + ", fdr=" + fdr + '}';
    }
    
    
}
