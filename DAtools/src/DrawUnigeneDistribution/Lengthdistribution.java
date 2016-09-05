/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package DrawUnigeneDistribution;

import java.util.ArrayList;

/**
 *
 * @author zhaoqi
 */
public class Lengthdistribution {
    public static ArrayList<String> getIntervalList(int min,int max,int step){
        ArrayList<String> intervalList=new ArrayList<String> ();
        for(int i=min;i<=max;i+=step){
           if(i+step<=max){
               intervalList.add(i+"~"+(i+step)+"bp");
           } else{
               intervalList.add(">"+(i)+"pb");
           }
        }
        return intervalList;
    }
    
    //calculate length distribution of fasta seqlength
    public static ArrayList<Integer> findInterval(int min,int max,int step, String fastafile, boolean isUnigene){
       // creat a list for output
        ArrayList<Integer> intervallist=new ArrayList<Integer>();
        for (int j = min; j <=max; j+=step) {
            intervallist.add(0);
        }
        //System.out.println(intervallist.size());
        //read seqlength
        int[] a =TrinityProcess.getSeqLengthList(fastafile, isUnigene);
        
        //count
        for (int i = 0; i < a.length; i++) {
            //System.out.print(a[i]+",");
            int k =(a[i]-min)/step;
            if(k<intervallist.size()){
                intervallist.set(k, intervallist.get(k)+1);
            }else{
                intervallist.set(intervallist.size()-1,intervallist.get(intervallist.size()-1)+1);
            }
        }
        return intervallist;
    }
    
    public static void main(String[] args) {
//        ArrayList<String> templist=Lengthdistribution.getIntervalList(0, 3000, 300);
        ArrayList<Integer> intervallist=Lengthdistribution.findInterval(0, 3000, 300, "F:\\mywork\\project\\yujing\\Trinity.fasta", true);
        //System.out.println(templist.toString());
        System.out.println(intervallist.toString());
    }
}
