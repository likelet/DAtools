/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Algorithm.DifferentialEvolution;

import java.util.ArrayList;

/**
 * <p>
 * Selection</p>
 * <p>
 * Created on 2015-9-22 13:04:22</p>
 * <p>
 * Author Email: zhaoqi3@mail2.sysu.edu.cn</p>
 *
 * @author ZHAO Qi
 * @date 2015-9-22 13:04:22
 * @version java 1.6.0
 * @version
 */
public class Selection {

    private ArrayList<Individual> poplist;
    private ArrayList<Individual> Crolist;
    private ArrayList<Individual> selectlist=new ArrayList<Individual>()  ;

    public Selection(ArrayList<Individual> poplist, ArrayList<Individual> Crolist) {
        this.poplist = poplist;
        this.Crolist = Crolist;
        this.SelectionPopulation();
    }

    public ArrayList<Individual> getSelectlist() {
        return selectlist;
    }

    
    public static void printPerformance(ArrayList<Individual> indlist) {
      for(Individual ind : indlist){
          System.out.print(Selection.evaluateFunction(ind)+"\t");
      }
        System.out.println();
    }
    public static double printPerformanceMin(ArrayList<Individual> indlist) {
        double minvalue=Selection.evaluateFunction(indlist.get(0));
        for (int i = 1; i < indlist.size(); i++) {
            if(minvalue>Selection.evaluateFunction(indlist.get(i))){
                minvalue=Selection.evaluateFunction(indlist.get(i));
            }
        }
        return minvalue;
    }
    
    
    
     //根据贪婪方法生成新群体
    public void SelectionPopulation(){
        for (int i = 0; i < poplist.size(); i++) {
            selectlist.add(SelectionIndividual(poplist.get(i),Crolist.get(i)));
        }
    }
    
    
    //根据贪婪方法选择个体
    public Individual SelectionIndividual(Individual a, Individual b) {
        //最小化问题
        if (Selection.evaluateFunction(a) < Selection.evaluateFunction(b)) {
            return a;
        } else {
            return b;
        }
    }

 //函数来评估选择
    public static double evaluateFunction(Individual ind) {
        double[] vect = ind.getVector();
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
