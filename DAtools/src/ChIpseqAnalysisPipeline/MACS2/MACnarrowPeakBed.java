/*
 * File format like:
*
chr1	933112	933380	MACS_peak_1	4.16
chr1	1004843	1005225	MACS_peak_2	4.26
chr1	1100807	1101135	MACS_peak_3	5.44
chr1	1136629	1136897	MACS_peak_4	3.09
 */

package ChIpseqAnalysisPipeline.MACS2;

import ChIpseqAnalysisPipeline.intersect.*;

/**
 *
 * @author ZHAO Qi
 * @date 2015-4-18 10:47:47
 * @version 1.6.0
 */
public class MACnarrowPeakBed {

    
    //NAME_peaks.narrowPeak is BED6+4 format file which contains the peak locations together with peak summit, pvalue and qvalue. 
    //You can load it to UCSC genome browser. 
    //Definition of some specific columns are: 5th: integer score for display, 7th: fold-change, 8th: -log10pvalue, 9th: -log10qvalue, 10th: relative summit position to peak start. The file can be loaded directly to UCSC genome browser. Remove the beginning track line if you want to analyze it by other tools.
    private String chr;
    
    private int start;
    
    private int end;
    
    private String MACpeakID;
    
    private int displayscore;

    private String bed_6th;

    private double FC;

    private double minus_log10_p;

    private double minus_log10_Q;

    private int R_summit;

    public MACnarrowPeakBed(String chr, int start, int end, String MACpeakID, int displayscore, String bed_6th, double FC, double minus_log10_p, double minus_log10_Q, int R_summit) {
        this.chr = chr;
        this.start = start;
        this.end = end;
        this.MACpeakID = MACpeakID;
        this.displayscore = displayscore;
        this.bed_6th = bed_6th;
        this.FC = FC;
        this.minus_log10_p = minus_log10_p;
        this.minus_log10_Q = minus_log10_Q;
        this.R_summit = R_summit;
    }

    
  
   
    
    
    
    /**
     * Get the value of R_summit
     *
     * @return the value of R_summit
     */
    public int getR_summit() {
        return R_summit;
    }

    /**
     * Set the value of R_summit
     *
     * @param R_summit new value of R_summit
     */
    public void setR_summit(int R_summit) {
        this.R_summit = R_summit;
    }


    /**
     * Get the value of minus_log10_Q
     *
     * @return the value of minus_log10_Q
     */
    public double getMinus_log10_Q() {
        return minus_log10_Q;
    }

    /**
     * Set the value of minus_log10_Q
     *
     * @param minus_log10_Q new value of minus_log10_Q
     */
    public void setMinus_log10_Q(double minus_log10_Q) {
        this.minus_log10_Q = minus_log10_Q;
    }

    /**
     * Get the value of minus_log10_p
     *
     * @return the value of minus_log10_p
     */
    public double getMinus_log10_p() {
        return minus_log10_p;
    }

    /**
     * Set the value of minus_log10_p
     *
     * @param minus_log10_p new value of minus_log10_p
     */
    public void setMinus_log10_p(double minus_log10_p) {
        this.minus_log10_p = minus_log10_p;
    }


    /**
     * Get the value of bed_6th
     *
     * @return the value of bed_6th
     */
    public String getBed_6th() {
        return bed_6th;
    }

    /**
     * Set the value of bed_6th
     *
     * @param bed_6th new value of bed_6th
     */
    public void setBed_6th(String bed_6th) {
        this.bed_6th = bed_6th;
    }

    
   

    /**
     * Get the value of FC
     *
     * @return the value of FC
     */
    public double getFC() {
        return FC;
    }

    /**
     * Set the value of FC
     *
     * @param FC new value of FC
     */
    public void setFC(double FC) {
        this.FC = FC;
    }

    

   
    @Override
    public String toString() {
        return chr + "\t" + start+ "\t" + end + "\t" + MACpeakID + "\t" + displayscore+ "\t" + this.bed_6th+ "\t" + this.minus_log10_p+ "\t" + this.minus_log10_Q;
    }

    
    
}
