/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package Algorithm.GeneticInformationEntropy;

import java.util.HashMap;

/**
 * <p>Individual</p>
 * <p>Created on 2015-12-14 15:21:48</p>
 * <p>Author Email: zhaoqi3@mail2.sysu.edu.cn</p>
 * @author ZHAO Qi
 * @date 2015-12-14 15:21:48
 * @version java 1.6.0
 * @version 
 */
public class Individual {
    private  String IndName;
    private  HashMap<String,Double> gene2expresionmap;

    public Individual(String IndName) {
        this.IndName = IndName;
        gene2expresionmap=new HashMap<String,Double>();
    }
   public void addData(String gene,double exp){
       gene2expresionmap.put(gene, exp);
   }
    
}
