/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Algorithm.ParticleSwarmOptimization;

import java.util.ArrayList;

/**
 * <p>
 * PSOPopulation</p>
 * <p>
 * Created on 2015-10-14 13:56:07</p>
 * <p>
 * Author Email: zhaoqi3@mail2.sysu.edu.cn</p>
 *
 * @author ZHAO Qi
 * @date 2015-10-14 13:56:07
 * @version java 1.6.0
 * @version
 */
public class PSOPopulation {

    private static double Vorignin = 0;
    private double Vmax = 1;
    private double c1 = 1.5;//群体随机因子
    private double c2 = 1.5;//全局随机因子
    private double w = 2;//惯性系数
    private int cirsize = 5;//社会关系大小
    private double max = 5.12;//初始化值域最大值
    private double min = -5.12;//初始化值域最小值
    private int vectorlength = 10;//向量维度

    private double[] GbestParticleVector;
    private double Gbestvalue;
    private ArrayList<Particle> populaionlist = new ArrayList<Particle>();
    private int popsize;
    private int generation;

    public PSOPopulation(int popsize,double Vmax,double c1,double c2,double w,double max, double min, int vlength,int cirsize) {
        this.popsize = popsize;
        this.Vmax=Vmax;
        this.c1=c1;
        this.c2=c2;
        this.w=w;
        this.max=max;
        this.min=min;
        this.vectorlength=vlength;
        this.cirsize=cirsize;
    }

    //初始化并且求种群内部最小值
    public void initalizePopulation() {
        Particle p = initalPartical(0);
        GbestParticleVector = p.getpBestVector();
        Gbestvalue = p.getPbestValue();
        double[] PbestParticleVector;
        double pbestvalue;
        for (int i = 1; i < popsize; i++) {
            p = initalPartical( i);
            populaionlist.add(p);
            pbestvalue = p.getPbestValue();
            if (pbestvalue < Gbestvalue) {
                GbestParticleVector = p.getpBestVector();
                Gbestvalue = pbestvalue;
            }
        }

        generation = 1;
    }

    //get种群内局部最优解,环状结构，size是环状结构的大小
    public void getLocalOptimize(Particle p){
        //获取局部小群体在大群体内部的索引,并且获取局部的最优解
        double tempvalue;
        int[]indx=new int[cirsize];
        for (int i = 0; i < cirsize; i++) {
            indx[i]=p.getParticleID()+i;
            if(indx[i]>=popsize){
                indx[i]=indx[i]-popsize;
            }
            tempvalue=FitnessFunction.evaluateFunction(populaionlist.get(indx[i]));
            if(p.getPbestValue()>tempvalue){
                p.setpBestVector(populaionlist.get(indx[i]).getVectorV());
                p.setPbestValue(tempvalue);
            }
        }
        
    }
    public synchronized void getOptimize(int size) {
        double[] PbestParticleVector;
        double pbestvalue;
        Particle p = null;
        for (int i = 1; i < populaionlist.size(); i++) {
            p = populaionlist.get(i);
            this.getLocalOptimize(p);
            pbestvalue = p.getPbestValue();
            if (pbestvalue < Gbestvalue) {
                GbestParticleVector = p.getpBestVector();
                Gbestvalue = pbestvalue;
            }
        }

    }
    //
    

    //更新种群
    public void UpdatePopulation() {
        for (int i = 0; i < populaionlist.size(); i++) {
            new Update(Vmax, c1, c2, w).getUpdate(populaionlist.get(i), GbestParticleVector);
        }
        this.generation++;
    }

    //输入值域大小和范围 随机初始化变量
    public Particle initalPartical( int id) {
        double[] vec = new double[vectorlength];
        double[] vecV = new double[vectorlength];
        for (int i = 0; i < vec.length; i++) {
            vec[i] = min + Math.random() * (max - min);
            vecV[i] = Vorignin;
        }
        Particle pt = new Particle(1, id, vec, vecV);
        pt.setpBestVector(vec);
        double tempvalue=FitnessFunction.evaluateFunction(pt);
        pt.setPbestValue(tempvalue);
        return pt;
    }

    //更新粒子的速度和位置
    public void getUpdate(Particle p, double[] gbestvector) {
        double[] preV = p.getVectorV();//当前速度

        //更新
        double[] Vnew = new double[preV.length];
        double[] newVector = new double[p.getVector().length];
        for (int i = 0; i < preV.length; i++) {
            //计算更新速度
            Vnew[i] = w * preV[i] + c1 * Math.random() * (p.getpBestVector()[i] - p.getVector()[i]) + c2 * Math.random() * (gbestvector[i] - p.getVector()[i]);
            //限制速度不能超过最大值
            if (newVector[i] > max) {
                newVector[i] = max;
            } else if (newVector[i] < min) {
                newVector[i] = min;
            }
            //更新粒子
            newVector[i] = p.getVector()[i] + Vnew[i];
            if(newVector[i]>max){
                newVector[i]=max;
            }
        }
        p.setGenerationNumber(p.getGenerationNumber() + 1);
        p.setVector(newVector);
        p.setVectorV(Vnew);

    }
    
    
    public double getGbestvalue() {
        return Gbestvalue;
    }

    public void setGbestvalue(double Gbestvalue) {
        this.Gbestvalue = Gbestvalue;
    }

    public ArrayList<Particle> getPopulaionlist() {
        return populaionlist;
    }

    public void setPopulaionlist(ArrayList<Particle> populaionlist) {
        this.populaionlist = populaionlist;
    }

    public int getGeneration() {
        return generation;
    }

    public void setGeneration(int generation) {
        this.generation = generation;
    }

    
    
    
    
    @Override
    public String toString() {
        return  "Gbestvalue=" + Gbestvalue + ", generation=" + generation;
    }

    public static void main(String[] args) {
        PSOPopulation p = new PSOPopulation(100,4,0.3,0.3,0.1,5.12, -5.12, 10,8);
        p.initalizePopulation();
        System.out.println(p.toString());
        while (p.Gbestvalue != 0) {
            p.UpdatePopulation();
            p.getOptimize(5);
            System.out.println(p.toString());
        }
    }

}
