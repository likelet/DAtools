/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package statAlignReadsInChr;

/**
 *
 * @author ZHAO Qi
 * @date 2015-2-4 16:05:06
 * @version 1.6.0
 */
public class ChrOutTerm {

    private String chromesome;
    
    private int count=0;

    public ChrOutTerm(String chromesome) {
        this.chromesome = chromesome;
    }
    
    

    /**
     * Get the value of count
     *
     * @return the value of count
     */
    
    public int getCount() {
        return count;
    }

    /**
     * Set the value of count
     *
     * @param count new value of count
     */
    public void setCount(int count) {
        this.count = count;
    }
    public void addCount() {
        this.count ++;
    }


    /**
     * Get the value of chromesome
     *
     * @return the value of chromesome
     */
    public String getChromesome() {
        return chromesome;
    }

    /**
     * Set the value of chromesome
     *
     * @param chromesome new value of chromesome
     */
    public void setChromesome(String chromesome) {
        this.chromesome = chromesome;
    }

    @Override
    public String toString() {
        return chromesome + "\t" + count;
    }

    
    
}
