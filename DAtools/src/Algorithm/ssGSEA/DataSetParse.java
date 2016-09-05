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
import java.util.Collections;

/**
 * <p>DataSetParse</p>
 * <p>Created on 2015-10-17 11:16:52</p>
 * <p>Author Email: zhaoqi3@mail2.sysu.edu.cn</p>
 * @author ZHAO Qi
 * @date 2015-10-17 11:16:52
 * @version java 1.6.0
 * @version 
 */
public class DataSetParse {
public static  ArrayList<SetdataTerm> getSetdataList(String GeneSetfile){
    ArrayList<SetdataTerm> datalist=new ArrayList<SetdataTerm>();
    BufferedReader br = null;
    String [] stra=null;
    try {
        br = new BufferedReader(new FileReader(new File(GeneSetfile)));
        String str = "";
        while (br.ready()) {
            str = br.readLine();
            stra=str.split("\t");
            SetdataTerm st=new SetdataTerm(stra[0],Double.parseDouble(stra[1]));
            datalist.add(st);
        }
        br.close();
    } catch (FileNotFoundException ex) {
//        System.out.println(DataSetParse + " is not found! please check your filepath ");
    } catch (IOException ex) {
//        System.out.println("IO error");
    }
    Collections.sort(datalist,new SetdataCompare());
    for (int i = 0; i < datalist.size(); i++) {
        datalist.get(i).setRank(i);
    }
    return datalist;
    
}
    
    
}
