/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package Algorithm.GeneticInformationEntropy;

import java.util.ArrayList;

/**
 * <p>Output</p>
 * <p>Created on 2015-12-14 15:49:37</p>
 * <p>Author Email: zhaoqi3@mail2.sysu.edu.cn</p>
 * @author ZHAO Qi
 * @date 2015-12-14 15:49:37
 * @version java 1.6.0
 * @version 
 */
public class Output {

    private String Gene;
    private ArrayList<Double> expdata;
    private double GIE;//信息熵

    public Output(String Gene, ArrayList<Double> expdata) {
        this.Gene = Gene;
        this.expdata = expdata;
    }
    
    
    
    

    /**
     * Get the value of Gene
     *
     * @return the value of Gene
     */
    public String getGene() {
        return Gene;
    }

    public ArrayList<Double> getExpdata() {
        return expdata;
    }

    public void setExpdata(ArrayList<Double> expdata) {
        this.expdata = expdata;
    }

    public double getGIE() {
        return GIE;
    }

    public void setGIE(double GIE) {
        this.GIE = GIE;
    }

    
    
    
    /**
     * Set the value of Gene
     *
     * @param Gene new value of Gene
     */
    public void setGene(String Gene) {
        this.Gene = Gene;
    }

    @Override
    public String toString() {
        String str=Gene+"\t";
        for (int i = 0; i < expdata.size(); i++) {
            str+=expdata.get(i)+"\t";
        }
        str+=GIE;
        return str;
    }

    
}
