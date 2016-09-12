/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package pub;

import java.util.ArrayList;

/**
 * <p>MyMath</p>
 * <p>Created on 2016-1-7 16:32:26</p>
 * <p>Author Email: zhaoqi3@mail2.sysu.edu.cn</p>
 * @author ZHAO Qi
 * @date 2016-1-7 16:32:26
 * @version java 1.6.0
 * @version 
 */
public class MyMath {

    public static double getArrayMean(ArrayList<Double> list) {
        double d = 0;
        if (list.size() > 0) {
            for (int i = 0; i < list.size(); i++) {
                d += list.get(i);
            }
            return d / list.size();
        } else {
            System.out.println(list + "size 0, cannot calculate mean!");
            return 0;
        }

    }
    //get pvalue of exprience distribution
    public static double getPermutationPbyESset(ArrayList<Double> permutationlist, double originscore) {
        int rightnum = 0;
        for (int i = 0; i < permutationlist.size(); i++) {
            if (permutationlist.get(i) >= originscore) {
                rightnum++;
            }
        }
        return (double)rightnum / permutationlist.size();
    }

}
