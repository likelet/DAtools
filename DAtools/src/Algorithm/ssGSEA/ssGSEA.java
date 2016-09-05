/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package Algorithm.ssGSEA;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;

/**
 * <p>ssGSEA</p>
 * <p>Created on 2015-10-17 11:22:41</p>
 * <p>Author Email: zhaoqi3@mail2.sysu.edu.cn</p>
 * @author ZHAO Qi
 * @date 2015-10-17 11:22:41
 * @version java 1.6.0
 * @version 
 */
public class ssGSEA {

    private HashSet<String> signatureList = new HashSet<String>();
    private ArrayList<SetdataTerm> geneTermlist;
    private double exponent = 1;
    private ArrayList<SetdataTerm> hitTermlist;
    private double sumhitFC;
    private int Nhit;
    private int Ntotal;

    public ssGSEA(String signatureFile, String Studyfile) {
        geneTermlist = DataSetParse.getSetdataList(Studyfile);
        BufferedReader br = null;
        try {
            br = new BufferedReader(new FileReader(new File(signatureFile)));
            String str = "";
            while (br.ready()) {
                str = br.readLine();
                signatureList.add(str);
            }
            br.close();
        } catch (FileNotFoundException ex) {
            System.out.println(signatureFile + " is not found! please check your filepath ");
        } catch (IOException ex) {
            System.out.println("IO error");
        }
        initialHit();
    }

    //初始化
    public void initialHit() {
        sumhitFC = 0;
        for (int i = 0; i < geneTermlist.size(); i++) {
            if (signatureList.contains(geneTermlist.get(i).getGeneName())) {
                this.hitTermlist.add(geneTermlist.get(i));
                sumhitFC += Math.pow(geneTermlist.get(i).getFoldChange(), exponent);
            }
        }
        Nhit = hitTermlist.size();
        Ntotal = geneTermlist.size();
        // get Khit

    }

    //获取第i个基因的khit
    private double getKhit(int m) {
        double Khit = 0;
        for (int i = 0; i < m; i++) {
            if (signatureList.contains(geneTermlist.get(i))) {
                Khit += Math.pow(geneTermlist.get(i).getFoldChange(), exponent);
            }
        }
        return Khit;
    }

    //获取第i个基因的kmis
    private double getKmiss(int m) {
        double Kmis = 0;
        for (int i = 0; i < m; i++) {
            Kmis += 1 / (Ntotal - Nhit);
        }
        return Kmis;
    }

    //获取某个基因的ES
    private double getESindividual(SetdataTerm st) {
        return getKhit(st.getRank()) - getKmiss(st.getRank());
    }

    //获取某个set的富集分数
    private double getSetES() {
        double finalES = 0;
        for (int i = 0; i < geneTermlist.size(); i++) {
            finalES += getESindividual(geneTermlist.get(i));
        }
        return finalES;
    }

}
