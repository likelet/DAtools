/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package Algorithm.ssGSEA;

/**
 * <p>PermutationPvalueComputation</p>
 * <p>Created on 2015-10-17 16:39:35</p>
 * <p>Author Email: zhaoqi3@mail2.sysu.edu.cn</p>
 * @author ZHAO Qi
 * @date 2015-10-17 16:39:35
 * @version java 1.6.0
 * @version 
 */
public class PermutationPvalueComputation {

    public static double getPermutationPbyESset(double[] ESset,double ES){
        int rightnum=0;
        for (int i = 0; i < ESset.length; i++) {
            if(ESset[i]>=ES){
                rightnum++;
            }
        }
        return rightnum/ESset.length;
    }
    
}
