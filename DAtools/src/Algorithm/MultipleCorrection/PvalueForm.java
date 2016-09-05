/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Algorithm.MultipleCorrection;;

import pub.OrderBaseRecord;

/**
 *
 * @author zhengyueyuan
 */
public class PvalueForm implements OrderBaseRecord{
    private double pvalue;
    private int index;

    public PvalueForm(double pvalue, int index) {
        this.pvalue = pvalue;
        this.index = index;
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

    @Override
    public double getFirst() {
       return pvalue;
    }

    @Override
    public String toString() {
        return "pvalue=" + pvalue + ", index=" + index + '}';
    }
    
    
}
