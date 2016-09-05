/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package MotifSearch_H.Forpeipei;

/**
 * <p>motif</p>
 * <p>Created on 2015-11-28 10:37:33</p>
 * <p>Author Email: zhaoqi3@mail2.sysu.edu.cn</p>
 * @author ZHAO Qi
 * @date 2015-11-28 10:37:33
 * @version java 1.6.0
 * @version 
 */
public class motif {

    private String Name;
    
    private String motifsequence;

    public motif(String Name, String motifsequence) {
        this.Name = Name;
        this.motifsequence = motifsequence;
    }

    
    
    /**
     * Get the value of motifsequence
     *
     * @return the value of motifsequence
     */
    public String getMotifsequence() {
        return motifsequence;
    }

    /**
     * Set the value of motifsequence
     *
     * @param motifsequence new value of motifsequence
     */
    public void setMotifsequence(String motifsequence) {
        this.motifsequence = motifsequence;
    }


    /**
     * Get the value of Name
     *
     * @return the value of Name
     */
    public String getName() {
        return Name;
    }

    public int getMotiflength() {
        return this.motifsequence.length();
    }

    /**
     * Set the value of Name
     *
     * @param Name new value of Name
     */
    public void setName(String Name) {
        this.Name = Name;
    }

    @Override
    public String toString() {
        return Name + "\t" + motifsequence ;
    }

}
