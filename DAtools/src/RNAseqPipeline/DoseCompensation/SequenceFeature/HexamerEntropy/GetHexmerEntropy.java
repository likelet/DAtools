/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package RNAseqPipeline.DoseCompensation.SequenceFeature.HexamerEntropy;

import MotifSearch_H.getKmer;
import MotifSearch_H.kmerFre;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import java.util.logging.Level;
import java.util.logging.Logger;
import pub.Fasta;
import pub.FastaReader;
import pub.Tools;

/**
 * <p>
 * GetHexmerEntropy</p>
 * <p>
 * Created on 2015-12-31 14:05:16</p>
 * <p>
 * Author Email: zhaoqi3@mail2.sysu.edu.cn</p>
 *
 * @author ZHAO Qi
 * @date 2015-12-31 14:05:16
 * @version java 1.6.0
 * @version
 */
public class GetHexmerEntropy {

    private LinkedHashMap<String,Double> resultlist = new LinkedHashMap<String,Double>();
    private int thread =Tools.thread;
    private LinkedList<Fasta> fastalist;

    public GetHexmerEntropy(String fastafile) {
        this.fastalist = new FastaReader(fastafile).getFastaList();
        this.process();
    }

    public GetHexmerEntropy(String fastafile, int thread) {
        this.fastalist = new FastaReader(fastafile).getFastaList();
        this.thread = thread;
        this.process();
    }

    
    
    public void process() {
        try {
            // run paralla
            ExecutorService pool = Executors.newFixedThreadPool(thread);//creat a new thread pool
            int size = fastalist.size() / thread + 1;
            for (int i = 0; i < thread; i++) {
                GetHexmerEntropySingle singleFileCrestrThread = null;
                //run extract thread
                ;
                if ((i + 1) * size > fastalist.size()) {
                    singleFileCrestrThread = new GetHexmerEntropySingle(fastalist, i * size, fastalist.size());
//                    System.out.println(i * size+"\t"+fastqlist.size());
                    pool.submit(singleFileCrestrThread);
                    break;
                } else {
                    singleFileCrestrThread = new GetHexmerEntropySingle(fastalist, i * size, (i + 1) * size);
//                    System.out.println(i * size+"\t"+(i +1)* size);
                    pool.submit(singleFileCrestrThread);
                }

            }
            pool.shutdown();
            pool.awaitTermination(Long.MAX_VALUE, TimeUnit.DAYS);
            // write out 
        } catch (InterruptedException ex) {
            Logger.getLogger(GetHexmerEntropy.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public synchronized void combinelist(ArrayList<String> list) {
        for (String tempar1 : list) {
            String[] str=tempar1.split("\t");
            resultlist.put(str[0],Double.parseDouble(str[1]));
        }
    }

    
//    public String print(){
//        String str="";
//        for (int i = 0; i < resultlist.size(); i++) {
//            str=resultlist.get(i)+"\t";
//        }
//        return str;
//    }
    class GetHexmerEntropySingle implements Runnable {

        private LinkedList<Fasta> fastalist;
        private int startindex;
        private int endindex;

        public GetHexmerEntropySingle(LinkedList<Fasta> fastalist, int startindex, int endindex) {
            this.fastalist = fastalist;
            this.startindex = startindex;
            this.endindex = endindex;
        }

        @Override
        public void run() {
//            System.out.println("run me");
            Fasta fa;
            String s;
            ArrayList<String> templist = new ArrayList<String>();
            for (int i = startindex; i < endindex; i++) {
                fa = fastalist.get(i);
                templist.add(fa.getPureName()+ "\t" + GetHexmerEntropyFromFasta(fa));

            }
            combinelist(templist);

        }

        public double GetHexmerEntropyFromFasta(Fasta fa) {
            return getHE(new getKmer().getkmerFrelist(fa.getName(), fa.getSequence(), 6));
        }

        public double getHE(HashMap<String, kmerFre> explist) {
            double HE = 0;
            for (Iterator it = explist.keySet().iterator(); it.hasNext();) {
                String tempstr = (String) it.next();
//                System.out.println(explist.get(tempstr).toString());
                HE += -explist.get(tempstr).getFre() * Math.log(explist.get(tempstr).getFre());
            }
            return HE;
        }
    }

    public LinkedHashMap<String, Double> getResultlist() {
        return resultlist;
    }
    
    

}
