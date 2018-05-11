package ExomeSeqAnalysisPipe.Annovar;


import java.util.List;
import java.util.stream.Collectors;
import htsjdk.samtools.util.CollectionUtil;
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Qi Zhao
 */
public class AnnovarDB_TXT {
    private String CHROM;
    private String START;
    private String END;
    private String REF;
    private String ALT;
    private double FREQUENCY;

    public AnnovarDB_TXT(String CHROM, String START, String END, String REF, String ALT, double FREQUENCY) {
        this.CHROM = CHROM;
        this.START = START;
        this.END = END;
        this.REF = REF;
        this.ALT = ALT;
        this.FREQUENCY = FREQUENCY;
    }

    public String getCHROM() {
        return CHROM;
    }

    public void setCHROM(String CHROM) {
        this.CHROM = CHROM;
    }

    public String getSTART() {
        return START;
    }

    public void setSTART(String START) {
        this.START = START;
    }

    public String getEND() {
        return END;
    }

    public void setEND(String END) {
        this.END = END;
    }

    public String getREF() {
        return REF;
    }

    public void setREF(String REF) {
        this.REF = REF;
    }

    public String getALT() {
        return ALT;
    }

    public void setALT(String ALT) {
        this.ALT = ALT;
    }

    public double getFREQUENCY() {
        return FREQUENCY;
    }

    public void setFREQUENCY(double FREQUENCY) {
        this.FREQUENCY = FREQUENCY;
    }
    
    public String toString() {
        final List<?> fields = CollectionUtil.makeList(CHROM,START,END,REF,ALT,FREQUENCY);
        String str = fields.stream().map(String::valueOf).collect(Collectors.joining("\t"));
       
        return str;
    }
    
    
    
}
