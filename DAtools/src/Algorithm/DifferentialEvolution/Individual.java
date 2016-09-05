/*
 * 种群中的个体，是个N维的向量
 */

package Algorithm.DifferentialEvolution;

/**
 * <p>Individual</p>
 * <p>Created on 2015-9-22 10:35:05</p>
 * <p>Author Email: zhaoqi3@mail2.sysu.edu.cn</p>
 * @author ZHAO Qi
 * @date 2015-9-22 10:35:05
 * @version java 1.6.0
 * @version 
 */
public class Individual {
    private  int id;
    private double[] vector;

    public Individual(int id, double[] vector) {
        this.id = id;
        this.vector = vector;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public double[] getVector() {
        return vector;
    }

    public void setVector(double[] vector) {
        this.vector = vector;
    }

    @Override
    public String toString() {
        String str="";
        for (int i = 0; i < vector.length; i++) {
            str+=vector[i]+"\t";
        }
        return id + "\t" + str;
    }
    
    
}
