/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package MetaGenomeAnalysis.RawdataQC;

import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import java.util.logging.Level;
import java.util.logging.Logger;
import pub.FastQ;
import pub.FastQReader;
import pub.Tools;

/**
 * <p>
 * GetNonHumanFastqFromBlastOut</p>
 * <p>
 * Created on 2015-12-17 16:55:11</p>
 * <p>
 * Author Email: zhaoqi3@mail2.sysu.edu.cn</p>
 *
 * @author ZHAO Qi
 * @date 2015-12-17 16:55:11
 * @version java 1.6.0
 * @version
 */
public class GetNonHumanFastqFromBlastOut {

    private String fastqfile;
    private String blastfile;
    private String outfile;
    private String toxid = "9606";
    private HashMap<String, String> readsid2toxid;
    private int passednum = 0;
    private int thread = Tools.thread;
    private  FileWriter fw =null;

    public GetNonHumanFastqFromBlastOut(String fastqfile, String blastfile, String outfile) {
        this.fastqfile = fastqfile;
        this.blastfile = blastfile;
        this.outfile = outfile;
       
        try {
             fw= new FileWriter(outfile);
            this.process();
            fw.close();
        } catch (IOException ex) {
            Logger.getLogger(GetNonHumanFastqFromBlastOut.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void process() throws IOException {
        System.out.println("You are using "+this.thread+" threads for this analysis procedure");
        readsid2toxid = new ParseBlastOut(blastfile).getReadsid2toxid();
        System.out.println("Parse blast output file .......finished!");
        LinkedList<FastQ> fastqlist = new FastQReader(fastqfile).getFastqlist();
        System.out.println("Parse Fastqfile......finished!\t Total " + fastqlist.size() + " reads were processed ");
        try {
            // run paralla
            ExecutorService pool = Executors.newFixedThreadPool(thread);//creat a new thread pool
            int size = fastqlist.size() / thread+1;
            for (int i = 0; i < thread; i++) {
                processThread singleFileCrestrThread = null;
                //run extract thread
                ;
                if ((i + 1) * size > fastqlist.size()) {
                    singleFileCrestrThread = new processThread(fastqlist, i * size, fastqlist.size());
//                    System.out.println(i * size+"\t"+fastqlist.size());
                     pool.submit(singleFileCrestrThread);
                    break;
                } else {
                    singleFileCrestrThread = new processThread(fastqlist, i * size, (i + 1) * size);
//                    System.out.println(i * size+"\t"+(i +1)* size);
                     pool.submit(singleFileCrestrThread);
                }
               
            }
            pool.shutdown();
            pool.awaitTermination(Long.MAX_VALUE, TimeUnit.DAYS);
           // write out 
            
           
         
          
            System.out.println("Finished all tasks! " + passednum + " reads are non-human reads");
        } catch (InterruptedException ex) {
            Logger.getLogger(GetNonHumanFastqFromBlastOut.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    class processThread implements Runnable {

        private LinkedList<FastQ> fastqlist;
        private int startindex;
        private int endindex;

        public processThread(LinkedList<FastQ> fastqlist, int startindex, int endindex) {
            this.fastqlist = fastqlist;
            this.startindex = startindex;
            this.endindex = endindex;
        }

        @Override
        public void run() {
//            System.out.println("run me");
            FastQ fq;
            String s;
            ArrayList<String> resultlist=new ArrayList<String>();
            for (int i = startindex; i < endindex; i++) {
                fq = fastqlist.get(i);
                s = fastqlist.get(i).getDescription().substring(1);
//                System.out.println(s);
                if (readsid2toxid.get(s) == null || !readsid2toxid.get(s).equals(toxid)) {
//                    System.out.println(s);
//                    System.out.println(readsid2toxid.get(s));
                    resultlist.add(fq.toString());
                }
                
            }
            System.out.println(resultlist.size());
            try {
                flushlist(resultlist);
            } catch (IOException ex) {
                Logger.getLogger(GetNonHumanFastqFromBlastOut.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

   
    
    public  synchronized void flushlist( ArrayList<String> list) throws IOException{
         for (String tempar1 : list) {
                    fw.append(tempar1 + "\r\n");
                }
         this.passednum+=list.size();
    }
    public static void main(String[] args) {

        new GetNonHumanFastqFromBlastOut("F:\\百度云同步盘\\resouces\\projects\\魏来老师\\test.fastq", "F:\\百度云同步盘\\resouces\\projects\\魏来老师\\test.blast", "F:\\百度云同步盘\\resouces\\projects\\魏来老师\\test.result");
    }

}
