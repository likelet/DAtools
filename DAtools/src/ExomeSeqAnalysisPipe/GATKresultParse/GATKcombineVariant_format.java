/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package ExomeSeqAnalysisPipe.GATKresultParse;


import java.util.List;
import java.util.stream.Collectors;
import htsjdk.samtools.util.CollectionUtil;
import java.text.DecimalFormat;

/**
 *
 * @author Administrator
 */
public class GATKcombineVariant_format {
    private String CHROM;
    private String POS;
    private String ID;
    private String REF;
    private String ALT;
    private String QUAL;
    private String FILTER;
    private String INFO;
    private String FORMAT;
    private String[] SampleVariant;

    public GATKcombineVariant_format(String CHROM, String POS, String ID, String REF, String ALT, String QUAL, String FILTER, String INFO, String FORMAT) {
        this.CHROM = CHROM;
        this.POS = POS;
        this.ID = ID;
        this.REF = REF;
        this.ALT = ALT;
        this.QUAL = QUAL;
        this.FILTER = FILTER;
        this.INFO = INFO;
        this.FORMAT = FORMAT;
    }

    public String getCHROM() {
        return CHROM;
    }

    public void setCHROM(String CHROM) {
        this.CHROM = CHROM;
    }

    public String getPOS() {
        return POS;
    }

    public void setPOS(String POS) {
        this.POS = POS;
    }

    public String getID() {
        return ID;
    }

    public void setID(String ID) {
        this.ID = ID;
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

    public String getQUAL() {
        return QUAL;
    }

    public void setQUAL(String QUAL) {
        this.QUAL = QUAL;
    }

    public String getFILTER() {
        return FILTER;
    }

    public void setFILTER(String FILTER) {
        this.FILTER = FILTER;
    }

    public String getINFO() {
        return INFO;
    }

    public void setINFO(String INFO) {
        this.INFO = INFO;
    }

    public String getFORMAT() {
        return FORMAT;
    }

    public void setFORMAT(String FORMAT) {
        this.FORMAT = FORMAT;
    }

    public String[] getSampleVariant() {
        return SampleVariant;
    }

    public void setSampleVariant(String[] SampleVariant) {
        this.SampleVariant = SampleVariant;
    }
    
    public String getFrequency(){
        int mut_sample=0;
        for (int i = 0; i < SampleVariant.length; i++) {
            if(!SampleVariant[i].startsWith(".")){
                mut_sample++;
            }
        }
        DecimalFormat df = new DecimalFormat("0.0000");
        return df.format((double)mut_sample/(double)SampleVariant.length);
    }
    
     public String toString() {
        final List<?> fields = CollectionUtil.makeList(CHROM,POS,ID,REF,ALT,QUAL,FILTER,INFO,FORMAT);
        String str = fields.stream().map(String::valueOf).collect(Collectors.joining("\t"));
        String tempstr = "";
         for (int i = 0; i < SampleVariant.length; i++) {
             tempstr=tempstr+"\t"+SampleVariant[i];
         }
        return str+"\t"+tempstr;
    }
   
            
}
