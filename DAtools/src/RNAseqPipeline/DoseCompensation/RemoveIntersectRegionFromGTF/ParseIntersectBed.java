/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package RNAseqPipeline.DoseCompensation.RemoveIntersectRegionFromGTF;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

/**
 * <p>ParseIntersectBed</p>
 * <p>Created on 2015-12-24 10:25:26</p>
 * <p>Author Email: zhaoqi3@mail2.sysu.edu.cn</p>
 * @author ZHAO Qi
 * @date 2015-12-24 10:25:26
 * @version java 1.6.0
 * @version 
 */
public class ParseIntersectBed {
private HashMap<String,ArrayList<IntersectBed>> str2bedMap=new  HashMap<String,ArrayList<IntersectBed>>();
private String intersectbedfile;

    public ParseIntersectBed(String intersectbedfile) {
        this.intersectbedfile = intersectbedfile;
        this.process();
    }

    public void process() {
        BufferedReader br = null;
        try {
            br = new BufferedReader(new FileReader(new File(intersectbedfile)));
            String[] str;
            while (br.ready()) {
                str = br.readLine().split("\t");
                String tempstr1 = str[0] + "_" + str[1] + "_" + str[2] + "_" + str[3];
                String tempstr2 = str[5] + "_" + str[6] + "_" + str[7] + "_" + str[8];
//                System.out.println(tempstr1);
                IntersectBed ib1 = new IntersectBed(str[0], Integer.parseInt(str[1]), Integer.parseInt(str[2]), str[3], str[4]);
                IntersectBed ib2 = new IntersectBed(str[5], Integer.parseInt(str[6]), Integer.parseInt(str[7]), str[8], str[9]);
                IntersectBed ib=getoverlapsregion(ib1,ib2);
                System.out.println(ib.toString());
//                System.out.println(ib.toString());
                if(!str2bedMap.keySet().contains(tempstr1)){
                    ArrayList<IntersectBed> templist1=new ArrayList<IntersectBed>();
                    templist1.add(ib);
                    str2bedMap.put(tempstr1, templist1);
                }else{
                    str2bedMap.get(tempstr1).add(ib);
                }
                if(!str2bedMap.keySet().contains(tempstr2)){
                    ArrayList<IntersectBed> templist2=new ArrayList<IntersectBed>();
                    templist2.add(ib);
                    str2bedMap.put(tempstr2, templist2);
                }else{
                    str2bedMap.get(tempstr2).add(ib);
                }
                
//                  System.out.println(ib.toString());

            }
            br.close();
        } catch (FileNotFoundException ex) {
            System.out.println(intersectbedfile + " is not found! please check your filepath ");
        } catch (IOException ex) {
            System.out.println("IO error");
        }
    }
    
    public IntersectBed getoverlapsregion(IntersectBed ib1, IntersectBed ib2) {
        IntersectBed ib = new IntersectBed();
        ib.setChr(ib1.getChr());
        if (ib2.getStart() >= ib1.getStart()) {
            ib.setStart(ib2.getStart()+1);
            if (ib2.getEnd() < ib1.getEnd()) {
                ib.setEnd(ib2.getEnd());
            } else {
                ib.setEnd(ib1.getEnd());
            }
        } else {
            ib.setStart(ib1.getStart()+1);
            if (ib2.getEnd() < ib1.getEnd()) {
                ib.setEnd(ib2.getEnd());
            } else {
                ib.setEnd(ib1.getEnd());
            }
        }
      
        return ib;
    }

    public HashMap<String, ArrayList<IntersectBed>> getStr2bedMap() {
        return str2bedMap;
    }
    
    
}
