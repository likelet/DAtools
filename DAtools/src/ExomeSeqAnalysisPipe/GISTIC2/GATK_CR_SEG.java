/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ExomeSeqAnalysisPipe.GISTIC2;

import htsjdk.samtools.util.CollectionUtil;
import java.util.List;
import java.util.stream.Collectors;

/**
 *
 * @author Administrator
 * @since 2019-3-14
 * @coding time 8:58:25
 * @author Qi Zhao
 */
public class GATK_CR_SEG {

    private String chr;
    private int start;
    private int end;
    private int NUM_POINTS_COPY_RATIO;
    private double MEAN_LOG2_COPY_RATIO;

    public GATK_CR_SEG(String chr, String start, String end, String NUM_POINTS_COPY_RATIO, String MEAN_LOG2_COPY_RATIO) {
        this.chr = chr;
        this.start = Integer.parseInt(start);
        this.end = Integer.parseInt(end);
        this.NUM_POINTS_COPY_RATIO = Integer.parseInt(NUM_POINTS_COPY_RATIO);
        this.MEAN_LOG2_COPY_RATIO = Integer.parseInt(MEAN_LOG2_COPY_RATIO);
    }

    public GATK_CR_SEG(String chr, int start, int end, int NUM_POINTS_COPY_RATIO, double MEAN_LOG2_COPY_RATIO) {
        this.chr = chr;
        this.start = start;
        this.end = end;
        this.NUM_POINTS_COPY_RATIO = NUM_POINTS_COPY_RATIO;
        this.MEAN_LOG2_COPY_RATIO = MEAN_LOG2_COPY_RATIO;
    }

    
    
    
    /**
     * Get the value of MEAN_LOG2_COPY_RATIO
     *
     * @return the value of MEAN_LOG2_COPY_RATIO
     */
    public double getMEAN_LOG2_COPY_RATIO() {
        return MEAN_LOG2_COPY_RATIO;
    }

    /**
     * Set the value of MEAN_LOG2_COPY_RATIO
     *
     * @param MEAN_LOG2_COPY_RATIO new value of MEAN_LOG2_COPY_RATIO
     */
    public void setMEAN_LOG2_COPY_RATIO(double MEAN_LOG2_COPY_RATIO) {
        this.MEAN_LOG2_COPY_RATIO = MEAN_LOG2_COPY_RATIO;
    }

    /**
     * Get the value of NUM_POINTS_COPY_RATIO
     *
     * @return the value of NUM_POINTS_COPY_RATIO
     */
    public int getNUM_POINTS_COPY_RATIO() {
        return NUM_POINTS_COPY_RATIO;
    }

    /**
     * Set the value of NUM_POINTS_COPY_RATIO
     *
     * @param NUM_POINTS_COPY_RATIO new value of NUM_POINTS_COPY_RATIO
     */
    public void setNUM_POINTS_COPY_RATIO(int NUM_POINTS_COPY_RATIO) {
        this.NUM_POINTS_COPY_RATIO = NUM_POINTS_COPY_RATIO;
    }

    /**
     * Get the value of end
     *
     * @return the value of end
     */
    public int getEnd() {
        return end;
    }

    /**
     * Set the value of end
     *
     * @param end new value of end
     */
    public void setEnd(int end) {
        this.end = end;
    }

    /**
     * Get the value of start
     *
     * @return the value of start
     */
    public int getStart() {
        return start;
    }

    /**
     * Set the value of start
     *
     * @param start new value of start
     */
    public void setStart(int start) {
        this.start = start;
    }

    /**
     * Get the value of chr
     *
     * @return the value of chr
     */
    public String getChr() {
        return chr;
    }

    /**
     * Set the value of chr
     *
     * @param chr new value of chr
     */
    public void setChr(String chr) {
        this.chr = chr;
    }

    public String toString() {
        final List<?> fields = CollectionUtil.makeList(chr, start, end, NUM_POINTS_COPY_RATIO, MEAN_LOG2_COPY_RATIO);
        String str = fields.stream().map(String::valueOf).collect(Collectors.joining("\t"));
        return str;
    }

}
