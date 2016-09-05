/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package Algorithm.DifferentialEvolution;

import java.util.ArrayList;

/**
 * <p>DifferentialEvolutionMain</p>
 * <p>Created on 2015-9-22 13:19:41</p>
 * <p>Author Email: zhaoqi3@mail2.sysu.edu.cn</p>
 * @author ZHAO Qi
 * @date 2015-9-22 13:19:41
 * @version java 1.6.0
 * @version 
 */
public class DifferentialEvolutionMain {
    //迭代的代数
    private int Gm=100000;
    //膨胀因子
    private Double Ffactor=0.5;
    //交叉概率
    private double CR=0.9;
    
    //构建向量所需要的边界条件
    private ArrayList<Double> maxlist;
    private ArrayList<Double> minlist;
    
    //变异算子 临时变量
    private ArrayList<Individual> MOlist;
    //交叉算子 临时变量
    private ArrayList<Individual> Crolist;
    //种群大小
    private int popsize=100;
    
    public DifferentialEvolutionMain(String maxstr, String minstr) {
         ArrayList<Double> list = new ArrayList<Double>();
        String[] strtemp = maxstr.split("\t");
        ArrayList<Double> list2 = new ArrayList<Double>();
        String[] strtemp2 = minstr.split("\t");
        for (int i = 0; i < strtemp2.length; i++) {
            list.add(Double.parseDouble(strtemp[i]));
            list2.add(Double.parseDouble(strtemp2[i]));
        }
        this.maxlist = list;
        this.minlist = list2;
        this.processMain();
    }
    
    //auto generate list 
    //size 是向量维数
    
    public DifferentialEvolutionMain(Double maxvalue, Double minvalue,int size) {
        ArrayList<Double> list1 = new ArrayList<Double>();
        ArrayList<Double> list2 = new ArrayList<Double>();
        for (int i = 0; i < size; i++) {
            list1.add(maxvalue);
            list2.add(minvalue);
        }
        this.maxlist = list1;
        this.minlist = list2;
        this.processMain();
    }
    
    
    
    //
    public void processMain() {
        int currentG = 1;
        double currentF = Ffactor;
        double cut=1;
        ArrayList<Individual> poplist = PopulationParser.InitialPopulation(popsize,maxlist, minlist);
        System.out.println(Selection.printPerformanceMin(poplist));
        ArrayList<Individual> selectlist;
        while (currentG < Gm && cut!=0) {
            System.out.println("G 1===============\t "+ currentG);
            selectlist = process(currentF, poplist);
            cut=Selection.printPerformanceMin(selectlist);
            System.out.println(cut);
            //更新代数
            currentG++;
            //更新膨胀因子
            currentF = 0.5;
            poplist=selectlist;
        }

    }

    //一次迭代
    public ArrayList<Individual> process(Double currentFfactor, ArrayList<Individual> poplist) {
        //变异算子
        MutationOperatorGenerator MOG = new MutationOperatorGenerator(currentFfactor, maxlist, minlist, poplist);
        System.out.println("变异算子");
        MOlist = MOG.getMOlist();
        //交叉算子
        CrossOver CO = new CrossOver(CR, poplist, MOlist);
        Crolist = CO.getCrolist();
        System.out.println("交叉算子");
        //返回选择的结果
        return new Selection(poplist, Crolist).getSelectlist();
    }

    //当前代数，自适应膨胀因子计算公式
    //G为当前代数
    public double CurrentFfactor(int G){
        double lamda=Math.exp(1-(Gm/(Gm-G+1)));
        return Ffactor*Math.pow(2, lamda);
    }
    
    
    public static void main(String[] args) {

//        System.out.println(Math.random());
        new DifferentialEvolutionMain(5.12,-5.12,10);
    }
    
}
