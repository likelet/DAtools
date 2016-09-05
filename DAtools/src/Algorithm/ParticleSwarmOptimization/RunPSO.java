/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package Algorithm.ParticleSwarmOptimization;

/**
 * <p>RunPSO</p>
 * <p>Created on 2015-10-14 17:16:11</p>
 * <p>Author Email: zhaoqi3@mail2.sysu.edu.cn</p>
 * @author ZHAO Qi
 * @date 2015-10-14 17:16:11
 * @version java 1.6.0
 * @version 
 */
public class RunPSO {
    public static void main(String[] args) {
      double Vmax=0.1;//最大速度
      double c1=0.1;//随机因子
      double c2=0.1;//随机因子
      double w=0.1;//惯性系数   
      int popsize=100;//群体大小
      int cirsize=5;//社会关系大小
      double max=5.12;//初始化值域最大值
      double min=-5.12;//初始化值域最小值
      int vectorlength=10;//向量维度
      
      PSOPopulation p = new PSOPopulation(popsize,Vmax,c1,c2,w,max, min, vectorlength,cirsize);
        p.initalizePopulation();
        System.out.println(p.toString());
        while (p.getGbestvalue() != 0) {
            p.UpdatePopulation();
            p.getOptimize(cirsize);
            System.out.println(p.toString());
        }
    }
    
    
}
