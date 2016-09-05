/*
 读取数据初始化群体获取群体list
 */
package Algorithm.DifferentialEvolution;

import java.util.ArrayList;

/**
 * <p>
 * PopulationParser</p>
 * <p>
 * Created on 2015-9-22 10:41:19</p>
 * <p>
 * Author Email: zhaoqi3@mail2.sysu.edu.cn</p>
 *
 * @author ZHAO Qi
 * @date 2015-9-22 10:41:19
 * @version java 1.6.0
 * @version
 */
public class PopulationParser {

    //种群大小 
    private  int popsize;
   
    ArrayList<Double> maxlist;
    ArrayList<Double> minlist;
    ArrayList<Individual> poplist;
    public PopulationParser(int popsize, ArrayList<Double> maxlist, ArrayList<Double> minlist) {
        this.maxlist = maxlist;
        this.minlist = minlist;
        poplist = PopulationParser.InitialPopulation(popsize,maxlist, minlist);
    }

    public PopulationParser(int popsize,String maxstr, String minstr) {
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
        poplist = PopulationParser.InitialPopulation(popsize,maxlist, minlist);
    }

 

    

    public ArrayList<Individual> getPoplist() {
        return poplist;
    }

    //初始化种群
    public static ArrayList<Individual> InitialPopulation( int popsize,ArrayList<Double> maxlist, ArrayList<Double> minlist) {
        ArrayList<Individual> poplist = new ArrayList<Individual>();
        for (int i = 0; i < popsize; i++) {
            Individual ind = PopulationParser.getRandomIndividual(i, maxlist, minlist);
            poplist.add(ind);
        }
        return poplist;
    }

    //初始化个体
    public static Individual getRandomIndividual(int number, ArrayList<Double> maxlist, ArrayList<Double> minlist) {
        double[] vect = new double[maxlist.size()];
        for (int i = 0; i < maxlist.size(); i++) {
            vect[i] = PopulationParser.getRandomIndividualSingle(maxlist.get(i), minlist.get(i));
        }
        Individual ind = new Individual(number, vect);
        return ind;
    }

    //初始个体的某一个个体 的一维 Xij(0)=Xmin+random(0,1)(Xmax-Xmin)
    public static double getRandomIndividualSingle(double max, double min) {
        return min + Math.random() * (max - min);
    }
    public static void main(String[] args) {
         ArrayList<Double> list1 = new ArrayList<Double>();
        ArrayList<Double> list2 = new ArrayList<Double>();
        for (int i = 0; i < 30; i++) {
            list1.add(5.12);
            list2.add(-5.12);
        }
        ArrayList<Individual> temp= PopulationParser.InitialPopulation(10,list1, list2);
//        Individual ind=PopulationParser.getRandomIndividual(1,list1, list2);
        
      Selection.printPerformance(temp);
    }
}
