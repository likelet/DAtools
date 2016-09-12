/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package FastaProcess.FastaLengthDistribution;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;
import pub.Fasta;
import pub.FastaReader;

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
    public static ArrayList<Integer> findInterval(int min,int max,int step, String fastafile){
       // creat a list for output
        ArrayList<Integer> intervallist=new ArrayList<Integer>();
        for (int j = min; j <=max; j+=step) {
            intervallist.add(0);
        }
        //System.out.println(intervallist.size());
        //read seqlength
        int[] a =Lengthdistribution.getSeqLengthList(fastafile);
        System.out.println("length info finished!");
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
  
        //System.out.println(templist.toString());
        //System.out.println(intervallist.toString());
    }

    //get sequence length list of a fastafile
    public static int[] getSeqLengthList(String fastaFile){
        int [] a;
       
            LinkedList<Fasta> fastalist=new FastaReader(new File(fastaFile)).getFastaList();
            System.out.println("sequence count: "+fastalist.size());
            a=new int[fastalist.size()];
            for (int i = 0; i < fastalist.size(); i++) {
                a[i]=fastalist.get(i).getSequence().length();     
            }
        
        return a;
    }
}
