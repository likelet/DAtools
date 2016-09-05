/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Algorithm.MultipleCorrection;

import pub.AscendingOrder;
import java.io.BufferedReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author zhengyueyuan
 */
public class FDR {

    private ArrayList<PvalueForm> pValueList = new ArrayList();
    private String outputFile;
    private ArrayList<FDRForm> fdrList = new ArrayList();
    private ArrayList<Double> fdrDoublelist=new ArrayList<Double>();

    public FDR(String pValueFile, String outputFile) {
        this.outputFile = outputFile;
        try {
            BufferedReader br = new BufferedReader(new java.io.FileReader(pValueFile));
            int index = 0;
            while (br.ready()) {
                index++;
                Double pvalue = Double.parseDouble(br.readLine());
                pValueList.add(new PvalueForm(pvalue, index));
            }
            br.close();
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        this.process();
        this.WriteData();
    }

   public FDR(ArrayList<Double> pvalueList, String outputFile) {
        this.outputFile = outputFile;
        int index = 0;
        for (Double pvalue : pvalueList) {
            index++;
            pValueList.add(new PvalueForm(pvalue, index));
        }
        this.process();
        this.WriteData();
    }
    public FDR(ArrayList<Double> pvalueList) {
        int index = 0;
        for (Double pvalue : pvalueList) {
            index++;
            pValueList.add(new PvalueForm(pvalue, index));
        }
        this.process();
        this.WriteList();
    }

    public FDR(LinkedList<Double> pvalueList, String outputFile) {
        this.outputFile = outputFile;
        int index = 0;
        for (Double pvalue : pvalueList) {
            index++;
            pValueList.add(new PvalueForm(pvalue, index));
        }
        this.process();
        this.WriteData();
    }

    public FDR(Double[] pvalueArray, String outputFile) {
        this.outputFile = outputFile;
        int index = 0;
        for (Double pvalue : pvalueArray) {
            index++;
            pValueList.add(new PvalueForm(pvalue, index));
        }
        this.process();
        this.WriteData();
    }

    private void process() {
        Collections.sort(pValueList, new AscendingOrder());
        int p_length = pValueList.size();
        double FDR = 0;
        int rank = 0;
        LinkedList<Double> frdValueList = new LinkedList();
        for (PvalueForm pvalueRecord : pValueList) {
            rank = rank + 1;
            FDR = pvalueRecord.getPvalue() * p_length / rank;
            frdValueList.add(FDR);
            fdrList.add(new FDRForm(pvalueRecord.getPvalue(), pvalueRecord.getIndex(), FDR));
        }
        int index = 0;
        while (true) {
            if (frdValueList.size() > 0) {
                double min = getMin(frdValueList);
                int minIndex = 0;
                for (int i = 0; i < frdValueList.size(); i++) {
                    if (frdValueList.get(i) == min) {
                        minIndex = i;
                        for (int k = 0; k <= minIndex; k++) {
                            fdrList.get(index + k).setFdr(min);
                            frdValueList.remove(0);
                        }
                        break;
                    }
                }
                index = index + (minIndex + 1);
            } else {
                break;
            }
        }
        Collections.sort(fdrList, new AscendingOrder());
    }

    private void WriteData() {
        try {
            FileWriter fw = new FileWriter(outputFile);
            for (FDRForm fdrRecord : fdrList) {
                fw.write(fdrRecord.getPvalue() + "\t" + fdrRecord.getFdr() + "\n");
            }
            fw.flush();
            fw.close();
        } catch (IOException ex) {
            Logger.getLogger(FDR.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    private void WriteList(){
        for (FDRForm fdrRecord : fdrList) {
                this.fdrDoublelist.add(fdrRecord.getFdr());
            }
    }

    public ArrayList<Double> getFdrDoublelist() {
        return fdrDoublelist;
    }

    
    
    public static double getMin(List<Double> ins) {
        Object[] objs = ins.toArray();
        Arrays.sort(objs);
        return Double.valueOf(String.valueOf(objs[0]));
    }

    public static void main(String[] args) {
//        new FDR(ArrayList<Double> templist).fdrDoublelist;
    }
}
