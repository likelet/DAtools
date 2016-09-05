/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package Algorithm.GISTC;

/**
 * <p>SampleGeneTerm</p>
 * <p>Created on 2015-11-14 18:03:04</p>
 * <p>Author Email: zhaoqi3@mail2.sysu.edu.cn</p>
 * @author ZHAO Qi
 * @date 2015-11-14 18:03:04
 * @version java 1.6.0
 * @version 
 */
public class SampleGeneTerm {

    private String Feature;
    private int start;
    private int end;
    private String strand="+";
    private double cnv;

    public SampleGeneTerm(String Feature, int start, int end, double cnv) {
        this.Feature = Feature;
        this.start = start;
        this.end = end;
        this.cnv = cnv;
    }

    
    
    /**
     * Get the value of cnv
     *
     * @return the value of cnv
     */
    public double getCnv() {
        return cnv;
    }

    /**
     * Set the value of cnv
     *
     * @param cnv new value of cnv
     */
    public void setCnv(double cnv) {
        this.cnv = cnv;
    }


    /**
     * Get the value of strand
     *
     * @return the value of strand
     */
    public String getStrand() {
        return strand;
    }

    /**
     * Set the value of strand
     *
     * @param strand new value of strand
     */
    public void setStrand(String strand) {
        this.strand = strand;
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
     * Get the value of Feature
     *
     * @return the value of Feature
     */
    public String getFeature() {
        return Feature;
    }

    /**
     * Set the value of Feature
     *
     * @param Feature new value of Feature
     */
    public void setFeature(String Feature) {
        this.Feature = Feature;
    }

    @Override
    public String toString() {
        return  Feature + "\t" + start + "\t" + end + "\t" + strand + "\t" + cnv ;
    }

    
}
