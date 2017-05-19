/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package Algorithm.ssGSEA;

/**
 *
 * @author Administrator
 * @since 2017-5-15
 * @coding time 18:46:57
 * @author Qi Zhao
 */
public class SignatureDataTerm {

        private String Name;
        private double ES;
        private double Pvalue;
        
        

    /**
     * Get the value of Pvalue
     *
     * @return the value of Pvalue
     */
    public double getPvalue() {
        return Pvalue;
    }

    /**
     * Set the value of Pvalue
     *
     * @param Pvalue new value of Pvalue
     */
    public void setPvalue(double Pvalue) {
        this.Pvalue = Pvalue;
    }


    /**
     * Get the value of ES
     *
     * @return the value of ES
     */
    public double getES() {
        return ES;
    }

    /**
     * Set the value of ES
     *
     * @param ES new value of ES
     */
    public void setES(double ES) {
        this.ES = ES;
    }


    /**
     * Get the value of Name
     *
     * @return the value of Name
     */
    public String getName() {
        return Name;
    }

    /**
     * Set the value of Name
     *
     * @param Name new value of Name
     */
    public void setName(String Name) {
        this.Name = Name;
    }

    
}
