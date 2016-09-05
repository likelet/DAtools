/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package Algorithm.DifferentialEvolution;

import java.util.ArrayList;

/**
 * <p>CrossOver</p>
 * <p>Created on 2015-9-22 12:42:25</p>
 * <p>Author Email: zhaoqi3@mail2.sysu.edu.cn</p>
 * @author ZHAO Qi
 * @date 2015-9-22 12:42:25
 * @version java 1.6.0
 * @version 
 */
public class CrossOver {

    //群体
    private ArrayList<Individual> poplist; 
    //变异算子
    private ArrayList<Individual> MOlist;
    //交叉概率
    private double CR=0.9;
    //交叉后的个体
    private ArrayList<Individual> Crolist=new ArrayList<Individual>();

    public CrossOver(ArrayList<Individual> poplist, ArrayList<Individual> MOlist) {
        this.poplist = poplist;
        this.MOlist = MOlist;
        PopCrossOver();
    }

    public CrossOver(double CR, ArrayList<Individual> poplist, ArrayList<Individual> MOlist) {
        this.poplist = poplist;
        this.MOlist = MOlist;
        this.CR=CR;
        PopCrossOver();
    }
    
    public ArrayList<Individual> getCrolist() {
        return Crolist;
    }
    
    
    
    
    //对群体交叉操作
    public void PopCrossOver(){
        for (int i = 0; i < poplist.size(); i++) {
            Crolist.add(CrossOverIndividual(poplist.get(i),MOlist.get(i)));
        }
    }
    
    
    
    
    
    
    
    //单个个体 根据原始ind和变异算子 做交叉操作
    public Individual CrossOverIndividual(Individual ind,Individual MOind){
        double[] indvect=ind.getVector();
        double[] modvect=MOind.getVector();
        double[] crovect=new double[indvect.length];
        
        //首先随机选取一个数字确保交叉后结果与原来结果不同
        int j=(int)Math.random()*indvect.length;
        crovect[j]=modvect[j];
        
        //交叉操作
            //随机数
        double rd;
        for (int i = 0; i < indvect.length; i++) {
            if(i!=j){
                rd=Math.random();
                if(rd<=CR){
                 crovect[i]= modvect[i] ; 
                }else{
                    crovect[i]= indvect[i] ;
                }
            }
        }
        Individual corInd=new Individual(ind.getId(),crovect);
        return corInd;
        
    }
    
}
