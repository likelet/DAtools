/*
 * An annotation format from varscan2
 */

package ExomeSeqAnalysisPipe.mafProcess.HaploxVcf2Maftools;

import java.util.HashMap;

/**
 *
 * @author Administrator
 * @since 2019-4-12
 * @coding time 18:56:53
 * @author Qi Zhao
 */
public class haploxVcf {
    
    private String CHROM;
    
    private String POS;
    
    private String ID;
    
    private String REF;
    
    private String ALT;
    
    private String QUAL;
    
    private String FILTER;
    
    private String INFO;
    
    private String FORMAT;
    
    private String NORMAL;
    
    private String TUMOR;

    public haploxVcf(String CHROM, String POS, String ID, String REF, String ALT, String QUAL, String FILTER, String INFO, String FORMAT, String NORMAL, String TUMOR) {
        this.CHROM = CHROM;
        this.POS = POS;
        this.ID = ID;
        this.REF = REF;
        this.ALT = ALT;
        this.QUAL = QUAL;
        this.FILTER = FILTER;
        this.INFO = INFO;
        this.FORMAT = FORMAT;
        this.NORMAL = NORMAL;
        this.TUMOR = TUMOR;
    }

    
    
    
    
    /**
     * Get the value of TUMOR
     *
     * @return the value of TUMOR
     */
    public String getTUMOR() {
        return TUMOR;
    }

    /**
     * Set the value of TUMOR
     *
     * @param TUMOR new value of TUMOR
     */
    public void setTUMOR(String TUMOR) {
        this.TUMOR = TUMOR;
    }

    
    /**
     * Get the value of NORMAL
     *
     * @return the value of NORMAL
     */
    public String getNORMAL() {
        return NORMAL;
    }

    /**
     * Set the value of NORMAL
     *
     * @param NORMAL new value of NORMAL
     */
    public void setNORMAL(String NORMAL) {
        this.NORMAL = NORMAL;
    }


    /**
     * Get the value of FORMAT
     *
     * @return the value of FORMAT
     */
    public String getFORMAT() {
        return FORMAT;
    }

    /**
     * Set the value of FORMAT
     *
     * @param FORMAT new value of FORMAT
     */
    public void setFORMAT(String FORMAT) {
        this.FORMAT = FORMAT;
    }


    /**
     * Get the value of INFO
     *
     * @return the value of INFO
     */
    public String getINFO() {
        return INFO;
    }

    /**
     * Set the value of INFO
     *
     * @param INFO new value of INFO
     */
    public void setINFO(String INFO) {
        this.INFO = INFO;
    }


    /**
     * Get the value of FILTER
     *
     * @return the value of FILTER
     */
    public String getFILTER() {
        return FILTER;
    }

    /**
     * Set the value of FILTER
     *
     * @param FILTER new value of FILTER
     */
    public void setFILTER(String FILTER) {
        this.FILTER = FILTER;
    }

    

    /**
     * Get the value of REF
     *
     * @return the value of REF
     */
    public String getREF() {
        return REF;
    }

    /**
     * Set the value of REF
     *
     * @param REF new value of REF
     */
    public void setREF(String REF) {
        this.REF = REF;
    }


    /**
     * Get the value of ALT
     *
     * @return the value of ALT
     */
    public String getALT() {
        return ALT;
    }

    /**
     * Set the value of ALT
     *
     * @param ALT new value of ALT
     */
    public void setALT(String ALT) {
        this.ALT = ALT;
    }


    /**
     * Get the value of ID
     *
     * @return the value of ID
     */
    public String getID() {
        return ID;
    }

    /**
     * Set the value of ID
     *
     * @param ID new value of ID
     */
    public void setID(String ID) {
        this.ID = ID;
    }


    /**
     * Get the value of POS
     *
     * @return the value of POS
     */
    public String getPOS() {
        return POS;
    }

    /**
     * Set the value of POS
     *
     * @param POS new value of POS
     */
    public void setPOS(String POS) {
        this.POS = POS;
    }


    /**
     * Get the value of CHROM
     *
     * @return the value of CHROM
     */
    public String getCHROM() {
        return CHROM;
    }

    /**
     * Set the value of CHROM
     *
     * @param CHROM new value of CHROM
     */
    public void setCHROM(String CHROM) {
        this.CHROM = CHROM;
    }

    
    
    

}
