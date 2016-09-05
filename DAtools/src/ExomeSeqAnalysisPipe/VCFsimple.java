/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ExomeSeqAnalysisPipe;

/**
 *
 * @author ZHAO Qi
 * @date 2014-2-20 14:23:56
 * @version 1.6.0
 */
public class VCFsimple {

    public VCFsimple() {
    }
    private String chr;
    private String pos;

    /**
     * Get the value of pos
     *
     * @return the value of pos
     */
    public String getPos() {
        return pos;
    }

    /**
     * Set the value of pos
     *
     * @param pos new value of pos
     */
    public void setPos(String pos) {
        this.pos = pos;
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
        return  chr + "\t" + pos ;
    }

    
}
