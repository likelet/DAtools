/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package Algorithm.ParticleSwarmOptimization;

/**
 * <p>Particle</p>
 * <p>Created on 2015-10-14 13:50:14</p>
 * <p>Author Email: zhaoqi3@mail2.sysu.edu.cn</p>
 * @author ZHAO Qi
 * @date 2015-10-14 13:50:14
 * @version java 1.6.0
 * @version 
 */
public class Particle {

    private int particleID;//粒子ID
    private int GenerationNumber;//粒子代数
    private double[] vector;//个体向量
    private double[] vectorV;//粒子速度
    private double[] pBestVector;//局部最佳粒子
    private double pbestValue;//局部最佳粒子的值
    
    public Particle(int particleID, int GenerationNumber, double[] vector, double[] vectorV) {
        this.particleID = particleID;
        this.GenerationNumber = GenerationNumber;
        this.vector = vector;
        this.vectorV = vectorV;
    }
    
    
    

    public double[] getVectorV() {
        return vectorV;
    }

    public void setVectorV(double[] vectorV) {
        this.vectorV = vectorV;
    }

    
    
    public int getParticleID() {
        return particleID;
    }

    public int getGenerationNumber() {
        return GenerationNumber;
    }

    public double[] getVector() {
        return vector;
    }

    public void setVector(double[] vector) {
        this.vector = vector;
    }

    public void setParticleID(int particleID) {
        this.particleID = particleID;
    }

    public void setGenerationNumber(int GenerationNumber) {
        this.GenerationNumber = GenerationNumber;
    }

    public double[] getpBestVector() {
        return pBestVector;
    }

    public void setpBestVector(double[] pBestVector) {
        this.pBestVector = pBestVector;
    }

    public double getPbestValue() {
        return pbestValue;
    }

    public void setPbestValue(double pbestValue) {
        this.pbestValue = pbestValue;
    }

    
    
    @Override
    public String toString() {
        String str="";
        for (int i = 0; i < vector.length; i++) {
            str+=vector[i]+",";
        }
        return this.particleID+"\t"+str;
    }
    
    
    
}
