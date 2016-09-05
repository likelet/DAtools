/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package Algorithm.ParticleSwarmOptimization;

/**
 * <p>Update</p>
 * <p>Created on 2015-10-14 15:10:06</p>
 * <p>Author Email: zhaoqi3@mail2.sysu.edu.cn</p>
 * @author ZHAO Qi
 * @date 2015-10-14 15:10:06
 * @version java 1.6.0
 * @version 
 */
public class Update {
    private  double Vmax=1;

    private  double c1=1.5;//随机因子
    private  double c2=1.5;//随机因子
    private  double w=2;//惯性系数

    public Update() {
    }

    
    
//    private int generation;
    public Update(double Vmax,double c1,double c2,double w) {
        this.Vmax=Vmax;
        this.c1=c1;
        this.c2=c2;
        this.w=w;
    }
    
    
    public void getUpdate(Particle p, double[] gbestvector) {
        double[] preV = p.getVectorV();//当前速度

        //更新
        double[] Vnew = new double[preV.length];
        double[] newVector = new double[p.getVector().length];
        for (int i = 0; i < preV.length; i++) {
            //计算更新速度
            Vnew[i] = w * preV[i] + c1 * Math.random() * (p.getpBestVector()[i] - p.getVector()[i]) + c2 * Math.random() * (gbestvector[i] - p.getVector()[i]);
            //限制速度不能超过最大值
            if (Vnew[i] > Vmax) {
                Vnew[i] = Vmax;
            }
            //更新粒子
            newVector[i] = p.getVector()[i] + Vnew[i];
        }
        p.setGenerationNumber(p.getGenerationNumber() + 1);
        p.setVector(newVector);
        p.setVectorV(Vnew);

    }

}
