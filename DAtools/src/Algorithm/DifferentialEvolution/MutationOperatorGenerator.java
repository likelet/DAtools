/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package Algorithm.DifferentialEvolution;

import java.util.ArrayList;

/**
 * <p>MutationOperatorGenerator</p>
 * <p>Created on 2015-9-22 11:22:34</p>
 * <p>Author Email: zhaoqi3@mail2.sysu.edu.cn</p>
 * @author ZHAO Qi
 * @date 2015-9-22 11:22:34
 * @version java 1.6.0
 * @version 
 */
public class MutationOperatorGenerator {
    //膨胀因子
    private Double Ffactor=0.5;
    //值域最大值列表
    private ArrayList<Double> maxlist;
    //值域最小值列表
    private ArrayList<Double> minlist;
    //群体
    private ArrayList<Individual> poplist; 
    //变异算子
    private ArrayList<Individual> MOlist=new ArrayList<Individual> ();

    public MutationOperatorGenerator(ArrayList<Double> maxlist, ArrayList<Double> minlist, ArrayList<Individual> poplist) {
        this.maxlist = maxlist;
        this.minlist = minlist;
        this.poplist = poplist;
        
        MOlistgenerator();
        
    }
    public MutationOperatorGenerator(Double Ffactor, ArrayList<Double> maxlist, ArrayList<Double> minlist, ArrayList<Individual> poplist) {
        this.Ffactor=Ffactor;
        
        this.maxlist = maxlist;
        this.minlist = minlist;
        this.poplist = poplist;
        MOlistgenerator();
        
    }
    

    public ArrayList<Individual> getMOlist() {
        return MOlist;
    }
    
    
    //计算群体变异算子
    public void MOlistgenerator(){
     
        for (int i = 0; i < poplist.size(); i++) {
            Individual ind=this.MutationOperator(i);
            MOlist.add(ind);
        }
    }
    
   //计算个体变异算子
   public Individual MutationOperator(int i){
       double[] ind=poplist.get(i).getVector();
       int j,z;
       j=(int)(Math.random()*poplist.size());
       while(j==i){
           j=(int)(Math.random()*poplist.size());
       }
       z=(int)(Math.random()*poplist.size());
       while(z==i||z==j){
           z=(int)(Math.random()*poplist.size());
       }
       double[] ind1=poplist.get(j).getVector();
       double[] ind2=poplist.get(z).getVector();
       double[] indnew=new double[ind1.length];
       for (int k = 0; k < ind1.length; k++) {
           indnew[k]=ind[k]+Ffactor*(ind1[k]-ind2[k]);
           //如果这个值超过值域 按照初始化方法重新生成
           if(indnew[k]<minlist.get(k)||indnew[k]>maxlist.get(k)){
               indnew[k]=minlist.get(k)+Math.random()*(maxlist.get(k)-minlist.get(k));
           }
//           System.out.println(indnew[k]);
       }
      Individual moind=new Individual(i,indnew);
      return(moind);
   }
   
    public static void main(String[] args) {
        ArrayList<Double> list1 = new ArrayList<Double>();
        ArrayList<Double> list2 = new ArrayList<Double>();
        for (int i = 0; i < 30; i++) {
            list1.add(5.12);
            list2.add(-5.12);
        }
        ArrayList<Individual> temp= PopulationParser.InitialPopulation(10,list1, list2);
//        System.out.println((int)(Math.random()*temp.size()));
       MutationOperatorGenerator mo=new MutationOperatorGenerator(list1,list2,temp);
        System.out.println(mo.getMOlist().size());
        
    }
}
