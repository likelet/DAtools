/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package Algorithm.ParticleSwarmOptimization;

/**
 * <p>FitnessFunction</p>
 * <p>Created on 2015-10-14 14:48:52</p>
 * <p>Author Email: zhaoqi3@mail2.sysu.edu.cn</p>
 * @author ZHAO Qi
 * @date 2015-10-14 14:48:52
 * @version java 1.6.0
 * @version 
 */
public class FitnessFunction {

    //函数来评估选择
    public static double evaluateFunction(Particle P) {
        double[] vect = P.getVector();
        double tempsum = 0;
        double x = 0;
        for (int i = 0; i < vect.length; i++) {
            x = vect[i];
            tempsum += x * x - 10 * Math.cos(2 * Math.PI * x) + 10;
        }
        //to be continued
        return tempsum;
    }
    
}
