/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ExomeSeqAnalysisPipe.CNVanalysis.CONTRAanalysis;

import dataformat.FAIterm;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.LinkedHashMap;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import pub.Tools;

/**
 * <p>
 * SegementWindows</p>
 * <p>
 * Created on 2016-1-16 13:33:54</p>
 * <p>
 * Author Email: zhaoqi3@mail2.sysu.edu.cn</p>
 *
 * @author ZHAO Qi
 * @date 2016-1-16 13:33:54
 * @version java 1.6.0
 * @version
 */
public class SegementWindows {

    private LinkedHashMap<String, ArrayList<WindowCNV>> chrCNVlistmap = new LinkedHashMap<String, ArrayList<WindowCNV>>();
    private LinkedHashMap<String, ArrayList<ContraOutTab>> cmap;
    private LinkedHashMap<String, FAIterm> Chrom2FAImap;
    //windowCNV contraOutTablelist;
    private ArrayList<ContraOutTab> ctlist=new ArrayList<ContraOutTab>();
    private int binsize = 100000;
    private int thread = Tools.thread;

    public SegementWindows( LinkedHashMap<String, FAIterm> Chrom2FAImap, String contraTablefile) {
        this.Chrom2FAImap = Chrom2FAImap;
        this.cmap = new ParseContraOutTab(contraTablefile, true).getCmap();
        this.initializeChrCNVlistmap();
        this.initialContrOutTabInmap();
    }

    public void initializeChrCNVlistmap() {
        int start;
        int end;
        String windowname;
        for (String chr : Chrom2FAImap.keySet()) {
            ArrayList<WindowCNV> cnvwindowlist = new ArrayList<WindowCNV>();
            FAIterm fai = Chrom2FAImap.get(chr);
            int size = (int) (fai.getLength() / binsize);
            for (int i = 0; i < size; i++) {
                start = i * binsize + 1;
                end = (i+1) * (binsize);
                if (end > fai.getLength()) {
                    end = (int) fai.getLength();
                }
                windowname = chr + ":" + start + "-" + end;
                WindowCNV wcnv1 = new WindowCNV(windowname, start, end, chr);
                cnvwindowlist.add(wcnv1);

            }
//            System.out.println(chr+"\t"+cnvwindowlist.size());
            chrCNVlistmap.put(chr, cnvwindowlist);
        }

        System.out.println("divide chromesome into bins with " + this.binsize + " (finished) ");
    }

    public void initialContrOutTabInmap() {

        // run paralla
        ExecutorService pool = Executors.newFixedThreadPool(thread);//creat a new thread pool
        int size = chrCNVlistmap.keySet().size();
        for (String chr : chrCNVlistmap.keySet()) {
//            System.out.println(chr);
            ArrayList<WindowCNV> wclist = chrCNVlistmap.get(chr);
            ArrayList<ContraOutTab> cntralist = cmap.get(chr);
            addContraOutTabInBinwindow(wclist, cntralist);
            this.addbinTab(wclist);
        }     // write out 

    }

     public void addContraOutTabInBinwindow(ArrayList<WindowCNV> wclist, ArrayList<ContraOutTab> cntralist) {
            Collections.sort(cntralist,new listcompareble());
        int i = 0;
        int j = 0;
        WindowCNV wc;
        ContraOutTab cc;
        for (; i < wclist.size() && j < cntralist.size();) {

            wc = wclist.get(i);
            cc = cntralist.get(j);
            if (isInBin(wc, cc).equalsIgnoreCase("small")) {
                j++;
            } else if (isInBin(wc, cc).equalsIgnoreCase("big")) {
                i++;
            } else {
                wclist.get(i).addContraOutTab(cc);
                j++;
            }
//               System.out.println("i "+i+"\tj "+j);
            }
        }


          
             
//            System.out.println("run mark");
            
            
            
        

        private String isInBin(WindowCNV wc, ContraOutTab cc) {
            int mid = (int) ((cc.getOriStCoordinate() + cc.getOriEndCoordinate()) / 2);
            if (mid < wc.getStart()) {
                return "small";
            } else if (mid >= wc.getStart() && mid <= wc.getEnd()) {
                return "in";
            } else {
                return "big";
            }
        }
    

    public LinkedHashMap<String, ArrayList<WindowCNV>> getChrCNVlistmap() {
        return chrCNVlistmap;
    }

    public void addbinTab(ArrayList<WindowCNV> wclist) {
//        System.out.println(wclist.size());
        for (int i = 0; i < wclist.size(); i++) {
            ctlist.add(wclist.get(i).getWindowTab());
        }
            
        
    }

    public ArrayList<ContraOutTab> getbinlistTab() {
        return ctlist;
    }
    
    class listcompareble implements Comparator {
        
        @Override
        public int compare(Object b1, Object b2) {
            // 先按age排序
            ContraOutTab c1 = (ContraOutTab) b1;
            ContraOutTab c2 = (ContraOutTab) b2;
            if (c1.getOriStCoordinate() > c2.getOriStCoordinate()) {
                return 1;
            } else if (c1.getOriStCoordinate() < c2.getOriStCoordinate()) {
                return -1;
            } else {
                return 0;
            }
        }
        
    }
}
