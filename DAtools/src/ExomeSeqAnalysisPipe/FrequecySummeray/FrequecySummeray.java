/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ExomeSeqAnalysisPipe.FrequecySummeray;

import ExomeSeqAnalysisPipe.FilelistReader;
import ExomeSeqAnalysisPipe.VCFrecord;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;
import pub.Tools;


/**
 *
 * @author ZHAO Qi
 * @date 2014-2-24 15:53:28
 * @version 1.6.0
 */
public class FrequecySummeray {
    
    public HashMap<Integer, output> outputmap = new HashMap<Integer, output>();
    public HashMap<String, LinkedList<VCFrecord>> SampleVcfMap = null;
    private ArrayList<ArrayList> sitelist = new ArrayList<ArrayList>();
    private int thead=Tools.thread;
   
    private String filepath;
    public ArrayList checkedPostionSet = new ArrayList();
    
    public FrequecySummeray(String filedir) {
        this.filepath = filedir;
        this.initialize();
        
    }
    
    public FrequecySummeray(String filedir, int thead) {
        this.filepath = filedir;
        this.initialize();
        System.out.println("initialize completed!");
        int size=this.checkedPostionSet.size();
        int range=size/thead;
        ArrayList<FrequecySummerayThread> threadlist=new ArrayList<FrequecySummerayThread>();
        for (int i = 0; i < thead; i++) {
            if((i+1)*range>=size){
                FrequecySummerayThread ft=new FrequecySummerayThread(this,i*range,size-1);
                System.out.println("thread\t"+i+"\t start");
            ft.start();
            threadlist.add(ft);
            break;
            }
            else{
            FrequecySummerayThread ft=new FrequecySummerayThread(this,i*range,(i+1)*range);
             System.out.println("thread\t"+i+"\t start");
            ft.start();
            threadlist.add(ft);  }  
        }
        
       for (int i = 0; i < threadlist.size(); i++){
            try {
                threadlist.get(i).join();
                outputAdd(threadlist.get(i).getCountlist());
                 System.out.println("thread\t"+i+"\t finished");
            } catch (InterruptedException ex) {
                Logger.getLogger(FrequecySummeray.class.getName()).log(Level.SEVERE, null, ex);
            }
       }
       this.print();
        
    }
    
    
  
    
    public void initialize() {
        File f = null;
        f = new File(filepath);
        File[] list = f.listFiles();
        
        for (int i = 1; i <= list.length; i++) {
            output op = new output(String.valueOf(i));
            outputmap.put(i, op);
        }
        
        SampleVcfMap = FilelistReader.getSampleVcfMap(filepath);
        Set<String> tempset = SampleVcfMap.keySet();
        HashSet tempset2=new HashSet();
     
        for (Iterator it = tempset.iterator(); it.hasNext();) {
            String tempstr = (String) it.next();
            LinkedList templist = SampleVcfMap.get(tempstr);
            for (Iterator it2 = templist.iterator(); it2.hasNext();) {
                VCFrecord vr = (VCFrecord) it2.next();
//                if (!checkedPostionSet.contains(vr.tosimple())) {
//                    checkedPostionSet.add(vr.tosimple());
//                }
               if( tempset2.add(vr.tosimple())){
                                    checkedPostionSet.add(vr.tosimple());
               }

            }
        }
        System.out.println(checkedPostionSet.size());
    }

    public void print() {
        Set tempset = outputmap.keySet();
        for (Iterator it = tempset.iterator(); it.hasNext();) {
            int i = (Integer) it.next();
            System.out.println(i + "\t" + outputmap.get(i));
        }
    }
    
    public void print(String fileout) throws IOException{
      
            FileWriter fw = new FileWriter(fileout);
         Set tempset = outputmap.keySet();
        for (Iterator it = tempset.iterator(); it.hasNext();) {
            int i = (Integer) it.next();
            fw.append(i + "\t" + outputmap.get(i)+"\r\n");
        }   
        fw.flush();
            fw.close();
    }
    public void outputAdd(ArrayList<Integer> countlist ){
        for (int i = 0; i < countlist.size(); i++) {
            outputmap.get(countlist.get(i)).addFreq();
        }
    }
    
    
    public void findCommonsite(String fileDir) {
        HashMap<String, LinkedList<VCFrecord>> SampleVcfMap = FilelistReader.getSampleVcfMap(fileDir);
        Set<String> tempset = SampleVcfMap.keySet();
    }
    
    public int vcfApeartimes(HashMap<String, LinkedList<VCFrecord>> SampleVcfMap, String potition) {
        int count = 0;
        VCFrecord vr = null;
        LinkedList list = null;
        Set<String> tempset = SampleVcfMap.keySet();
        for (Iterator it = tempset.iterator(); it.hasNext();) {
            String tempstr = (String) it.next();
            list = SampleVcfMap.get(tempstr);
            //System.out.println(list.size());
            for (Iterator it2 = list.iterator(); it2.hasNext();) {
                vr = (VCFrecord) it2.next();
                if (vr.tosimple().equals(potition)) {
                    count++;
                    break;
                }
            }
        }
        return count;
    }
    
//    public static void main(String[] args) throws IOException {
//        new FrequecySummeray("G:\\javatest\\test2",4).print();
//        new FrequecySummeray(args[0],Integer.parseInt(args[1])).print(args[2]);
//    }
//    
    class output {
        
        public output() {
        }
        
        public output(String samples) {
            this.samples = samples;
        }
        private String samples;
        private int freq = 0;
        ArrayList<String> vcflist = new ArrayList<String>();

        /**
         * Get the value of freq
         *
         * @return the value of freq
         */
        public int getFreq() {
            return freq;
        }

        /**
         * Set the value of freq
         *
         * @param freq new value of freq
         */
        public void setFreq(int freq) {
            this.freq = freq;
        }
        
        public synchronized void addFreq() {
            this.freq++;
        }

        /**
         * Get the value of samples
         *
         * @return the value of samples
         */
        public String getSamples() {
            return samples;
        }

        /**
         * Set the value of samples
         *
         * @param samples new value of samples
         */
        public void setSamples(String samples) {
            this.samples = samples;
        }
        
        public synchronized void addVcf(String vr) {
            this.vcflist.add(vr);
            
        }
        
        public void setVcflist(ArrayList<String> vcflist) {
            this.vcflist = vcflist;
        }
        
        @Override
        public String toString() {
            return "samples=" + samples + ", freq=" + freq;
        }
    }
}

class FrequecySummerayThread extends Thread {

    private FrequecySummeray fs;
    private int start;
    private int end;
    private ArrayList<Integer> countlist=new ArrayList<Integer>();
    
    public FrequecySummerayThread(FrequecySummeray fs, int start, int end) {
        this.fs = fs;
        this.start = start;
        this.end = end;
    }
    
    @Override
    public void run() {
        ArrayList<String> tempset = fs.checkedPostionSet;
        int count = 0;
        for (int i = start; i < end; i++) {
            String vr = (String) tempset.get(i);
            count = vcfApeartimes(fs.SampleVcfMap, vr);
            //System.out.println(count);
            countlist.add(count);
            //fs.outputmap.get(count).addVcf(vr);
        }
    }

    public ArrayList<Integer> getCountlist() {
        return countlist;
    }

    
    
   int vcfApeartimes(HashMap<String, LinkedList<VCFrecord>> SampleVcfMap, String potition) {
        int count = 0;
        VCFrecord vr = null;
        LinkedList list = null;
        Set<String> tempset = SampleVcfMap.keySet();
        for (Iterator it = tempset.iterator(); it.hasNext();) {
            String tempstr = (String) it.next();
            list = SampleVcfMap.get(tempstr);
            //System.out.println(list.size());
            for (Iterator it2 = list.iterator(); it2.hasNext();) {
                vr = (VCFrecord) it2.next();
                if (vr.tosimple().equals(potition)) {
                    count++;
                    break;
                }
            }
        }
        return count;
    }
    public static void main(String[] args) throws IOException {
     new FrequecySummeray("F:\\mywork\\project\\Peng\\SamtoolsResult\\TotalEnronment\\annotationfile",6).print("F:\\mywork\\project\\Peng\\SamtoolsResult\\TotalEnronment\\genelevelSnpStatus.xlsx");   
    }
}
