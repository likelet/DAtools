/*
 * Class range default 1-based genome
 */
package FastaProcess.getFastaFormRange;

/**
 *
 * @author ZHAO Qi
 * @date 2014-4-18 14:49:58
 * @version 1.6.0
 */
public class Range {

    protected String name;
    protected String Chr;
    protected int start;
    protected int end;
    protected String strand;

    public Range(String name, String Chr, int start, int end, String strand) {
        this.name = name;
        this.Chr = Chr;
        this.start = start;
        this.end = end;
        this.strand = strand;
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
     * Get the value of Chr
     *
     * @return the value of Chr
     */
    public String getChr() {
        return Chr;
    }

    /**
     * Set the value of Chr
     *
     * @param Chr new value of Chr
     */
    public void setChr(String Chr) {
        this.Chr = Chr;
    }

    /**
     * Get the value of name
     *
     * @return the value of name
     */
    public String getName() {
        return name;
    }

    /**
     * Set the value of name
     *
     * @param name new value of name
     */
    public void setName(String name) {
        this.name = name;
    }

    
}
