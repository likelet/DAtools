/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package pub;

/**
 *
 * @author ZHAO Qi
 * @date 2014-8-8 15:10:00
 * @version 1.6.0
 */
public class FastQ {
    
    private String description;//description of this FASTQ formatted sequence.
    
    private String sequence;
    
    private String variant;
    
    private String quality;
    
    private int seqlength=0;

    public FastQ() {
    }

    public FastQ(String description, String sequence, String variant, String quality) {
        this.description = description;
        this.sequence = sequence;
        this.variant = variant;
        this.quality = quality;
       
    }
    
    

    /**
     * Get the value of quality
     *
     * @return the value of quality
     */
    public String getQuality() {
        return quality;
    }

    /**
     * Set the value of quality
     *
     * @param quality new value of quality
     */
    public void setQuality(String quality) {
        this.quality = quality;
    }


    
    /**
     * Get the value of variant
     *
     * @return the value of variant
     */
    public String getVariant() {
        return variant;
    }

    /**
     * Set the value of variant
     *
     * @param variant new value of variant
     */
    public void setVariant(String variant) {
        this.variant = variant;
    }


    /**
     * Get the value of sequence
     *
     * @return the value of sequence
     */
    public String getSequence() {
        return sequence;
    }

    /**
     * Set the value of sequence
     *
     * @param sequence new value of sequence
     */
    public void setSequence(String sequence) {
        this.sequence = sequence;
    }


    /**
     * Get the value of description
     *
     * @return the value of description
     */
    public String getDescription() {
        return description;
    }

    /**
     * Set the value of description
     *
     * @param description new value of description
     */
    public void setDescription(String description) {
        this.description = description;
    }

    public int getSeqlength() {
        return this.sequence.length();
    }

    
    
    
    @Override
    public String toString(){
        String s="";
        s=this.description+"\n"+this.sequence+"\n"+this.variant+"\n"+this.quality;
        return s;
    }
}
