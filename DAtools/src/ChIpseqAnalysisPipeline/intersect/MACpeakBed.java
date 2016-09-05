/*
 * File format like:
*
chr1	933112	933380	MACS_peak_1	4.16
chr1	1004843	1005225	MACS_peak_2	4.26
chr1	1100807	1101135	MACS_peak_3	5.44
chr1	1136629	1136897	MACS_peak_4	3.09
 */

package ChIpseqAnalysisPipeline.intersect;

/**
 *
 * @author ZHAO Qi
 * @date 2015-4-18 10:47:47
 * @version 1.6.0
 */
public class MACpeakBed {

    private String chr;
    
    private int start;
    
    private int end;
    
    private String MACpeakID;
    
    private double peakscore;

    public MACpeakBed(String chr, int start, int end, String MACpeakID, double peakscore) {
        this.chr = chr;
        this.start = start;
        this.end = end;
        this.MACpeakID = MACpeakID;
        this.peakscore = peakscore;
    }

    
    
    
    /**
     * Get the value of peakscore
     *
     * @return the value of peakscore
     */
    public double getPeakscore() {
        return peakscore;
    }

    /**
     * Set the value of peakscore
     *
     * @param peakscore new value of peakscore
     */
    public void setPeakscore(double peakscore) {
        this.peakscore = peakscore;
    }


    /**
     * Get the value of MACpeakID
     *
     * @return the value of MACpeakID
     */
    public String getMACpeakID() {
        return MACpeakID;
    }

    /**
     * Set the value of MACpeakID
     *
     * @param MACpeakID new value of MACpeakID
     */
    public void setMACpeakID(String MACpeakID) {
        this.MACpeakID = MACpeakID;
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

    @Override
    public String toString() {
        return chr + "\t" + start+ "\t" + end + "\t" + MACpeakID + "\t" + peakscore;
    }

    
    
}
