/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package ExomeSeqAnalysisPipe.CNVanalysis.CONTRAanalysis.GscoreStatistic;

import Algorithm.MultipleCorrection.FDR;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import java.util.logging.Level;
import java.util.logging.Logger;
import pub.Tools;

/**
 * <p>GetStatic_fromPermutationFile</p>
 * <p>Created on 2016-1-7 16:43:42</p>
 * <p>Author Email: zhaoqi3@mail2.sysu.edu.cn</p>
 * @author ZHAO Qi
 * @date 2016-1-7 16:43:42
 * @version java 1.6.0
 * @version 
 */
public class GetStatic_fromPermutationFile {
    private ArrayList<String> permutationfilelist;
    private String orignfile;
    private ArrayList<GeneGscoreOutput> resultlist =new ArrayList<GeneGscoreOutput>();
    private int thread=Tools.thread;
    private String outfilename;

    public GetStatic_fromPermutationFile() {
    }
    

    
    
    public GetStatic_fromPermutationFile(ArrayList<String> permutationfilelist, String orignfile,String outfile) {
        this.permutationfilelist = permutationfilelist;
        this.orignfile = orignfile;
        this.outfilename=outfile;
        this.initialize();
        this.process();
        this.processFDR();
        this.writeOut();
    }
    
    public void initialize() {
        ArrayList<CNVMatrixIterm> itermlist = this.parseMatrixFile(orignfile);
        System.out.println("Start initialize.");
        for (int i = 0; i < itermlist.size(); i++) {
            itermlist.get(i).calculatedScore();//独立计算一次
            GeneGscoreOutput gout = new GeneGscoreOutput(itermlist.get(i).geneid, itermlist.get(i).getAmpscore(), itermlist.get(i).getDelscore());
            this.resultlist.add(gout);
        }
        System.out.println("initialize finished.");
        System.out.println(resultlist.size());
    }
    public void process(){
         try {
            // run paralla
            ExecutorService pool = Executors.newFixedThreadPool(thread);//creat a new thread pool
            int size = permutationfilelist.size() / thread + 1;
            for (int i = 0; i < thread; i++) {
                runOnefileGScore singleRunOnefileGScore = null;
                //run extract thread
                ;
                if ((i + 1) * size > permutationfilelist.size()) {
                    singleRunOnefileGScore = new runOnefileGScore(permutationfilelist, i * size, permutationfilelist.size());
//                    System.out.println(i * size+"\t"+fastqlist.size());
                    pool.submit(singleRunOnefileGScore);
                    break;
                } else {
                    singleRunOnefileGScore = new runOnefileGScore(permutationfilelist, i * size, (i + 1) * size);
//                    System.out.println(i * size+"\t"+(i +1)* size);
                    pool.submit(singleRunOnefileGScore);
                }

            }
            pool.shutdown();
            pool.awaitTermination(Long.MAX_VALUE, TimeUnit.DAYS);
            // write out 
        } catch (InterruptedException ex) {
            Logger.getLogger(GetStatic_fromPermutationFile.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void processFDR(){
        ArrayList<Double> ampPlist = new ArrayList<Double>();
        ArrayList<Double> delPlist = new ArrayList<Double>();
        for (int i = 0; i < resultlist.size(); i++) {
            ampPlist.add(resultlist.get(i).getAMP_pvalue());
            delPlist.add(resultlist.get(i).getDEL_pvalue());
        }
        ArrayList<Double> ampQlist = new FDR(ampPlist).getFdrDoublelist();
        ArrayList<Double> delQlist = new FDR(delPlist).getFdrDoublelist();
        for (int i = 0; i < resultlist.size(); i++) {
            resultlist.get(i).setAMP_qvalue(ampQlist.get(i));
            resultlist.get(i).setDEL_qvalue(delQlist.get(i));
        }
        
    }

    public void writeOut(){
        try {
            FileWriter fw = new FileWriter(outfilename);
            fw.append("gene\tAMP.Gscore\tAMP.pvalue\tAMP.qvalue\tDEL.Gscore\tDEL.pvalue\tDEL.qvalue\n");
            for (int i = 0; i < this.resultlist.size(); i++) {
                fw.append(resultlist.get(i).toString()+"\n");
            }
            fw.flush();
            fw.close();
        } catch (IOException ex) {
            Logger.getLogger(GetStatic_fromPermutationFile.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    //同步命令
    public synchronized void addPermutationDelList(HashMap<String,Double> delmap){
        for (int i = 0; i < resultlist.size(); i++) {
            String tempstr=resultlist.get(i).getGenename();
            resultlist.get(i).addDellist(delmap.get(tempstr));
        }
    }
     //同步命令
    public synchronized void addPermutationAmpList(HashMap<String,Double> ampmap){
        for (int i = 0; i < resultlist.size(); i++) {
            String tempstr=resultlist.get(i).getGenename();
            resultlist.get(i).addAmplist(ampmap.get(tempstr));
        }
    }
    
    
    class runOnefileGScore implements Runnable{
        private ArrayList<String> permutationfilelist;
        private int startindex;
        private int endindex;

        public runOnefileGScore(ArrayList<String> permutationfilelist, int startindex, int endindex) {
            this.permutationfilelist = permutationfilelist;
            this.startindex = startindex;
            this.endindex = endindex;
        }
        
        
        @Override
        public void run() {
            for (int i = startindex; i <endindex; i++) {
                String file=permutationfilelist.get(i);
                ArrayList<CNVMatrixIterm> itermlist = parseMatrixFile(file);
                HashMap<String,Double> ampmap =new HashMap<String,Double> ();
                HashMap<String,Double> delmap=new HashMap<String,Double> ();
                for (int j = 0; j < itermlist.size(); j++) {
                    itermlist.get(j).calculatedScore();
                    ampmap.put(itermlist.get(j).geneid, itermlist.get(j).getAmpscore());
                    delmap.put(itermlist.get(j).geneid, itermlist.get(j).getDelscore());
                }
                addPermutationAmpList(ampmap);
                addPermutationDelList(delmap);
                
            }
        }
        
        
       
    }
    
    //Parsing matrix file
    public ArrayList<CNVMatrixIterm> parseMatrixFile(String file){
        System.out.println("parsing "+file);
        ArrayList<CNVMatrixIterm> itermlist=new ArrayList<CNVMatrixIterm>();
        BufferedReader br = null;
        boolean header=true;
        try {
            br = new BufferedReader(new FileReader(new File(file)));
            String[] str ;
            while (br.ready()) {
                if(header){
                    br.readLine();
                    header=false;
                }
                str = br.readLine().split("\t");
                if(str.length>1){
                    CNVMatrixIterm iterm=new CNVMatrixIterm(str[0]);
                    for (int i = 1; i < str.length; i++) {
                        iterm.addCNV(Double.parseDouble(str[i]));
                    }
                    itermlist.add(iterm);
                }
            }
            br.close();
        } catch (FileNotFoundException ex) {
            System.out.println(file + " is not found! please check your filepath ");
        } catch (IOException ex) {
            System.out.println("IO error");
        }
        return itermlist;
    }
     
    
     public static void main(String[] args) {
        ArrayList<CNVMatrixIterm> list=new GetStatic_fromPermutationFile().parseMatrixFile("F:\\百度云同步盘\\resouces\\projects\\菊花强\\mutect\\CNA.CONTRA_T.matrix.filter_p0.05.txt");
         System.out.println(list.size());
     }
}
