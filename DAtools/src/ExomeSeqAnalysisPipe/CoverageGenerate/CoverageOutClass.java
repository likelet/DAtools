/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ExomeSeqAnalysisPipe.CoverageGenerate;

/**
 *
 * @author Zinky
 */
public class CoverageOutClass {
    
    private String sample;
    
    private int targetbase;
    private int seqedBase;
    private double seqRate;
    private double averageCov;
    private double Over5Rate;
    private double Over10Rate;
    private double Over20Rate;
    private double Over30Rate;
    private double Over50Rate;

    public CoverageOutClass(String sample, int targetbase, int seqedBase) {
        this.sample = sample;
        this.targetbase = targetbase;
        this.seqedBase = seqedBase;
    }

    public double getAverageCov() {
        return averageCov;
    }

    public void setAverageCov(double averageCov) {
        this.averageCov = averageCov;
    }

    public double getOver5Rate() {
        return Over5Rate;
    }

    public void setOver5Rate(double Over5Rate) {
        this.Over5Rate = Over5Rate;
    }

    public double getOver10Rate() {
        return Over10Rate;
    }

    public void setOver10Rate(double Over10Rate) {
        this.Over10Rate = Over10Rate;
    }

    public double getOver20Rate() {
        return Over20Rate;
    }

    public void setOver20Rate(double Over20Rate) {
        this.Over20Rate = Over20Rate;
    }

    public double getOver30Rate() {
        return Over30Rate;
    }

    public void setOver30Rate(double Over30Rate) {
        this.Over30Rate = Over30Rate;
    }

    public double getOver50Rate() {
        return Over50Rate;
    }

    public void setOver50Rate(double Over50Rate) {
        this.Over50Rate = Over50Rate;
    }

    
   
    /**
     * Get the value of seqRate
     *
     * @return the value of seqRate
     */
    public double getSeqRate() {
        return seqRate;
    }

    /**
     * Set the value of seqRate
     *
     * @param seqRate new value of seqRate
     */
    public void setSeqRate(double seqRate) {
        this.seqRate = seqRate;
    }


    /**
     * Get the value of seqedBase
     *
     * @return the value of seqedBase
     */
    public int getSeqedBase() {
        return seqedBase;
    }

    /**
     * Set the value of seqedBase
     *
     * @param seqedBase new value of seqedBase
     */
    public void setSeqedBase(int seqedBase) {
        this.seqedBase = seqedBase;
    }


    /**
     * Get the value of targetbase
     *
     * @return the value of targetbase
     */
    public int getTargetbase() {
        return targetbase;
    }

    /**
     * Set the value of targetbase
     *
     * @param targetbase new value of targetbase
     */
    public void setTargetbase(int targetbase) {
        this.targetbase = targetbase;
    }


    /**
     * Get the value of sample
     *
     * @return the value of sample
     */
    public String getSample() {
        return sample;
    }

    /**
     * Set the value of sample
     *
     * @param sample new value of sample
     */
    public void setSample(String sample) {
        this.sample = sample;
    }

    @Override
    public String toString() {
        return sample + "\t" + targetbase + "\t" + seqedBase + "\t" + seqRate + "\t" + averageCov + "\t" + Over5Rate + "\t" + Over10Rate + "\t" + Over20Rate + "\t" + Over30Rate + "\t" + Over50Rate ;
    }

    
}
