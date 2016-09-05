/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package ChIpseqAnalysisPipeline.intersect;

import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 *
 * @author ZHAO Qi
 * @date 2015-4-18 11:32:25
 * @version 1.6.0
 */
public class MACandTempFormatoverlap {
    private ArrayList<MACpeakBed> mcapeaklist;
    private HashMap<String,ArrayList<tempFromat>> tempregionlistmap;
    private static String outputstr="";
    private static String[] strlist;
    

    public MACandTempFormatoverlap(String macfile, String regionfile) {
        this.mcapeaklist = MacPeakparse.getpeaklist(macfile);
        this.tempregionlistmap = tempformatParse.chr2peakList(regionfile);
        System.out.println(mcapeaklist.size());
//        System.out.println(tempregionlistmap.size());
    }
    class processThread extends Thread{
        private List<MACpeakBed> threadMacpeaklist;
        private int threadnum;
        public processThread(List<MACpeakBed> threadMacpeaklist,int threadnum) {
            this.threadMacpeaklist = threadMacpeaklist;
            this.threadnum=threadnum;
        }
        
         public void run() {
            MACpeakBed mb = null;
            tempFromat tf = null;
            ArrayList<tempFromat> templist=null;
            String tempstr;
             String totalstr="";
            for (int i = 0; i < threadMacpeaklist.size(); i++) {
                mb = threadMacpeaklist.get(i);
                System.out.println(mb.getMACpeakID());
                templist=tempregionlistmap.get(mb.getChr());
                System.out.println(templist.size());
                for (int j = 0; j < templist.size(); j++) {
                    tf = templist.get(j);
                  
                        tempstr = positionIntersect.isIntersection(mb.getStart(), mb.getEnd(), tf.getStart(), tf.getEnd());
//                        System.out.println(tempstr);
                        if (tempstr != "No intersect") {
                           totalstr+=mb.toString() + "\t" + tempstr + "\t" + tf.toString()+"\n";
                        }
                   
                    
                }
                
            }
            MACandTempFormatoverlap.strlist[threadnum]=totalstr;
        }
    }
    
    public void process(String outfile,int threadP){
        int scale;
        
        if(this.mcapeaklist.size()%threadP==0){
            scale=mcapeaklist.size()/threadP;
            strlist=new String[threadP];
            for (int i = 0; i < threadP; i++) {
                List<MACpeakBed> templist= mcapeaklist.subList(scale*i, scale*(i+1));
                System.out.println("thread "+i+"start");
                new processThread(templist,i).start();
            }
        }else{
            scale=this.mcapeaklist.size()/threadP;
            strlist=new String[threadP+1];
            for (int i = 0; i < threadP; i++) {
                ArrayList<MACpeakBed> templist=(ArrayList<MACpeakBed>) mcapeaklist.subList(scale*i, scale*(i+1));
                System.out.println("thread "+i+"start");
                new processThread(templist,i).start();
            }
            ArrayList<MACpeakBed> templist=(ArrayList<MACpeakBed>) mcapeaklist.subList(scale*threadP, mcapeaklist.size());
                System.out.println("thread "+scale+1+"start");
                new processThread(templist,threadP).start();
        }
        
        try {
            FileWriter fw = new FileWriter(outfile);
            for (int i = 0; i < strlist.length; i++) {
                MACandTempFormatoverlap.outputstr+=strlist[i];
            }
            fw.append(MACandTempFormatoverlap.outputstr);
            fw.flush();
            fw.close();
        } catch (IOException ex) {
            System.out.println("IO error!");
        }
        
    }
    public static synchronized void addstr(String str) {
        MACandTempFormatoverlap.outputstr += str + "\n";
    }
    
    
    
    public static void main(String[] args) {
        new MACandTempFormatoverlap("F:\\resouces\\projects\\wuyue\\五月\\WY-1_peaks.bed","F:\\resouces\\projects\\wuyue\\五月\\GGGCCCspacedMotifResult.txt").process("F:\\resouces\\projects\\wuyue\\五月\\result.txt", 5);
    }
}
