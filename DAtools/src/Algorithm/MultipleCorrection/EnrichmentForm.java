/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package Algorithm.MultipleCorrection;;

/**
 *
 * @author zhengyueyuan
 */
public class EnrichmentForm {
    private String goTerm;
    private int queryitem;
    private int querytotal;
    private int refitem;
    private int reftotal;
    private double pValue;
    private double FDR;
    private String entries;
    private String entries_ref;
    private String goName;

    public EnrichmentForm(String goTerm, int queryitem, int querytotal, int refitem, int reftotal, double pValue, String entries, String entries_ref) {
        this.goTerm = goTerm;
        this.queryitem = queryitem;
        this.querytotal = querytotal;
        this.refitem = refitem;
        this.reftotal = reftotal;
        this.pValue = pValue;
        this.entries = entries;
        this.entries_ref = entries_ref;
    }
    
    public EnrichmentForm(String goTerm, String goName,int queryitem, int querytotal, int refitem, int reftotal, double pValue, double FDR) {
        this.goTerm = goTerm;
        this.goName=goName;
        this.queryitem = queryitem;
        this.querytotal = querytotal;
        this.refitem = refitem;
        this.reftotal = reftotal;
        this.pValue = pValue;
        this.FDR = FDR;       
    }

    public EnrichmentForm(String goTerm, int queryitem, int querytotal, int refitem, int reftotal, double pValue, double FDR) {
        this.goTerm = goTerm;
        this.queryitem = queryitem;
        this.querytotal = querytotal;
        this.refitem = refitem;
        this.reftotal = reftotal;
        this.pValue = pValue;
        this.FDR = FDR;       
    }

    public String getGoName() {
        return goName;
    }

    public void setGoName(String goName) {
        this.goName = goName;
    }    

    public double getFDR() {
        return FDR;
    }

    public void setFDR(double FDR) {
        this.FDR = FDR;
    }
    

    public String getGoTerm() {
        return goTerm;
    }

    public void setGoTerm(String goTerm) {
        this.goTerm = goTerm;
    }

    public int getQueryitem() {
        return queryitem;
    }

    public void setQueryitem(int queryitem) {
        this.queryitem = queryitem;
    }

    public int getQuerytotal() {
        return querytotal;
    }

    public void setQuerytotal(int querytotal) {
        this.querytotal = querytotal;
    }

    public int getRefitem() {
        return refitem;
    }

    public void setRefitem(int refitem) {
        this.refitem = refitem;
    }

    public int getReftotal() {
        return reftotal;
    }

    public void setReftotal(int reftotal) {
        this.reftotal = reftotal;
    }

    public double getpValue() {
        return pValue;
    }

    public void setpValue(double pValue) {
        this.pValue = pValue;
    }

    public String getEntries() {
        return entries;
    }

    public void setEntries(String entries) {
        this.entries = entries;
    }

    public String getEntries_ref() {
        return entries_ref;
    }

    public void setEntries_ref(String entries_ref) {
        this.entries_ref = entries_ref;
    }
    
}
