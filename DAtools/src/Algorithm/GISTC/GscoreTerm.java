/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package Algorithm.GISTC;

/**
 * <p>GscoreTerm</p>
 * <p>Created on 2015-11-14 18:24:47</p>
 * <p>Author Email: zhaoqi3@mail2.sysu.edu.cn</p>
 * @author ZHAO Qi
 * @date 2015-11-14 18:24:47
 * @version java 1.6.0
 * @version 
 */
public class GscoreTerm {

        private String segID;
    private int start;
    private int end;
    private String strand;
    private String isamply;
    private double gscore;
    private double averAMP;
    private double fre;

    public GscoreTerm(String segID, int start, int end, String strand, String isamply, double gscore, double averAMP, double fre) {
        this.segID = segID;
        this.start = start;
        this.end = end;
        this.strand = strand;
        this.isamply = isamply;
        this.gscore = gscore;
        this.averAMP = averAMP;
        this.fre = fre;
    }

    /**
     * Get the value of fre
     *
     * @return the value of fre
     */
    public double getFre() {
        return fre;
    }

    /**
     * Set the value of fre
     *
     * @param fre new value of fre
     */
    public void setFre(double fre) {
        this.fre = fre;
    }


    /**
     * Get the value of averAMP
     *
     * @return the value of averAMP
     */
    public double getAverAMP() {
        return averAMP;
    }

    /**
     * Set the value of averAMP
     *
     * @param averAMP new value of averAMP
     */
    public void setAverAMP(double averAMP) {
        this.averAMP = averAMP;
    }


    /**
     * Get the value of gscore
     *
     * @return the value of gscore
     */
    public double getGscore() {
        return gscore;
    }

    /**
     * Set the value of gscore
     *
     * @param gscore new value of gscore
     */
    public void setGscore(double gscore) {
        this.gscore = gscore;
    }


    /**
     * Get the value of isamply
     *
     * @return the value of isamply
     */
    public String getIsamply() {
        return isamply;
    }

    /**
     * Set the value of isamply
     *
     * @param isamply new value of isamply
     */
    public void setIsamply(String isamply) {
        this.isamply = isamply;
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
     * Get the value of segID
     *
     * @return the value of segID
     */
    public String getSegID() {
        return segID;
    }

    /**
     * Set the value of segID
     *
     * @param segID new value of segID
     */
    public void setSegID(String segID) {
        this.segID = segID;
    }

    
}
