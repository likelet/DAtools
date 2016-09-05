/*
 shulfle genes cnv in CNV matrix to calculating distribution of Gscore
 */

package ExomeSeqAnalysisPipe.CONTRAanalysis.GscoreStatistic;

import java.util.ArrayList;
import java.util.Collections;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import java.util.logging.Level;
import java.util.logging.Logger;
import pub.Tools;

/**
 * <p>PermuterCNVMatrixIterm</p>
 * <p>Created on 2016-1-8 10:56:02</p>
 * <p>Author Email: zhaoqi3@mail2.sysu.edu.cn</p>
 * @author ZHAO Qi
 * @date 2016-1-8 10:56:02
 * @version java 1.6.0
 * @version 
 */
public class PermuterCNVMatrixIterm {
    private  ArrayList<CNVMatrixIterm> oringin ;
    private int thread=Tools.thread;
    private  ArrayList<ArrayList<CNVMatrixIterm>> permutationlist=new ArrayList<ArrayList<CNVMatrixIterm>> ();
    private int permutaionNum=1000;

    public PermuterCNVMatrixIterm(ArrayList<CNVMatrixIterm> oringin) {
        this.oringin = oringin;
        this.process();
    }
    
    public void process(){
        try {
            // run paralla
//            System.out.println("ok");
            ExecutorService pool = Executors.newFixedThreadPool(thread);//creat a new thread pool
            int size = permutaionNum / thread + 1;
            for (int i = 0; i < thread; i++) {
                 PermuterCNVMatrixItermThread permuterCNVMatrixItermThread = null;
                //run extract thread
                ;
                if ((i + 1) * size > permutaionNum) {
                    permuterCNVMatrixItermThread = new PermuterCNVMatrixItermThread( i * size, permutaionNum);
//                    System.out.println(i * size+"\t"+fastqlist.size());
                    pool.submit(permuterCNVMatrixItermThread);
                    break;
                } else {
                    permuterCNVMatrixItermThread = new PermuterCNVMatrixItermThread( i * size, (i + 1) * size);
//                    System.out.println(i * size+"\t"+(i +1)* size);
                    pool.submit(permuterCNVMatrixItermThread);
                }

            }
            pool.shutdown();
            pool.awaitTermination(Long.MAX_VALUE, TimeUnit.DAYS);
            // write out 
        } catch (InterruptedException ex) {
            Logger.getLogger(GetStatic_fromPermutationFile.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    
    
    //synchronized add templist to result list
    public synchronized void listadd(ArrayList<ArrayList<CNVMatrixIterm>> templist){
        int length=permutationlist.size();
        for (int i = 0; i < templist.size(); i++) {
            this.permutationlist.add(templist.get(i));
        }
        int length2=permutationlist.size();
        System.out.println("Finish permutation "+length+"\tto\t"+length2);
    }
    
    //function class
 
    public void print(ArrayList<CNVMatrixIterm> list){
        for (int i = 0; i < list.size(); i++) {
            System.out.println(list.get(i).toString());
        }
    }

    public ArrayList<CNVMatrixIterm> getOringin() {
        return oringin;
    }

    public ArrayList<ArrayList<CNVMatrixIterm>> getPermutationlist() {
        return permutationlist;
    }
    
    
    
    class PermuterCNVMatrixItermThread implements Runnable{
        private ArrayList<ArrayList<CNVMatrixIterm>> templist=new  ArrayList<ArrayList<CNVMatrixIterm>> ();  ;
        private int startindex;
        private int endindex;

        
        public PermuterCNVMatrixItermThread(int startindex, int endindex) {
            this.startindex = startindex;
            this.endindex = endindex;
        }
        
        @Override
        public void run() {
            for (int i = startindex; i < endindex; i++) {
                templist.add(permute(oringin));
//                System.out.println(i+" finished");
            }
            listadd(templist);
         }
           public ArrayList<CNVMatrixIterm> permute(ArrayList<CNVMatrixIterm> oringin) {
        ArrayList<CNVMatrixIterm> outlist = new ArrayList<CNVMatrixIterm>();

        ArrayList<Double> templistall = new ArrayList<Double>();
        for (int i = 0; i < oringin.size(); i++) {
            CNVMatrixIterm cm = new CNVMatrixIterm(oringin.get(i).getGeneid());
            outlist.add(cm);
            for (double a : oringin.get(i).getCnvlist()) {
                templistall.add(a);
            }
        }
        Collections.shuffle(templistall);
        int count = 0;
        while (count != templistall.size()) {
            for (int i = 0; i < outlist.size(); i++) {
                outlist.get(i).addCNV(templistall.get(count));
                count++;
            }
        }

        return outlist;
    }
        
    }
    
    
    public static void main(String[] args) {
//        ArrayList<CNVMatrixIterm>  list=new GetStatic_fromPermutationFile().parseMatrixFile("E:\\blast\\FORTEST.txt");
//        PermuterCNVMatrixIterm.print(list);
//        System.out.println("==========================");
//        PermuterCNVMatrixIterm.print(PermuterCNVMatrixIterm.permute(list));
        
    }
}
