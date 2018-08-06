/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package ExomeSeqAnalysisPipe.CNVanalysis.CONTRAanalysis;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.HashSet;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * <p>ParseContraOutTab</p>
 * <p>Created on 2015-12-14 11:15:45</p>
 * <p>Author Email: zhaoqi3@mail2.sysu.edu.cn</p>
 * @author ZHAO Qi
 * @date 2015-12-14 11:15:45
 * @version java 1.6.0
 * @version 
 */
public class ParseContraOutTab {
    private ArrayList<ContraOutTab> clist=new ArrayList<ContraOutTab>();
    private ArrayList<GenelevelCNV> geneclist=new ArrayList<GenelevelCNV>();
    private double pthreshold=0.05;//filter Contra result
    
    private LinkedHashMap<String,ArrayList<ContraOutTab>> cmap=new LinkedHashMap<String, ArrayList<ContraOutTab>>();

    
    public  ParseContraOutTab(String infile,String outfile){
        this.Parse(infile);
        this.WriteGenelist(outfile);
    }
    
    public  ParseContraOutTab(String infile){
        this.Parse(infile);
    }
     public  ParseContraOutTab(String infile,boolean ismap){
        this.ParseMap(infile);
    }

    
    public  void  Parse(String tabfile){
        System.out.println("Parsing "+tabfile);
        HashSet<String> genename=new HashSet<String>(); 
            BufferedReader br = null;
            try {
                br = new BufferedReader(new FileReader(new File(tabfile)));
                String []str ;
                
                while (br.ready()) {
                    str = br.readLine().trim().split("\t");
                    if (str[0].startsWith("Targ")||str[11].equalsIgnoreCase("NA")) {
                    } else {
                        ContraOutTab ct = new ContraOutTab(str[0], Double.parseDouble(str[1]), str[2].trim(), str[3], Double.parseDouble(str[4]), Double.parseDouble(str[5]), Double.parseDouble(str[6]), Double.parseDouble(str[7]), Double.parseDouble(str[8]), Double.parseDouble(str[9]), Double.parseDouble(str[10]), Double.parseDouble(str[11]), Double.parseDouble(str[12]), str[13], Double.parseDouble(str[14]), Double.parseDouble(str[15]), Double.parseDouble(str[16]), Double.parseDouble(str[17]), Double.parseDouble(str[18]), Double.parseDouble(str[19]), Double.parseDouble(str[20]));
                        if (ct.getP_Value() > this.pthreshold) {
                        } else {
                            
                            if (genename.add(str[2].trim())) {
                                GenelevelCNV gc = new GenelevelCNV(str[2].trim());
                                gc.addContraOutTab(ct);
                                geneclist.add(gc);
                            } else {
                                geneclist.get(geneclist.size() - 1).addContraOutTab(ct);
                            }
                        }
                    }

                }
                br.close();
 
                
            } catch (FileNotFoundException ex) {
                System.out.println(tabfile + " is not found! please check your filepath ");
            } catch (IOException ex) {
                System.out.println("parse non mapping IO error");
            }
            
    }
    
    
    public  void  ParseMap(String tabfile){
        System.out.println("Parsing "+tabfile);
        HashSet<String> chrome=new HashSet<String>(); 
            BufferedReader br = null;
            try {
                br = new BufferedReader(new FileReader(new File(tabfile)));
                String []str ;
                while (br.ready()) {
                    str = br.readLine().trim().split("\t");
//                    System.out.println(tabfile);
//                    System.out.println("read table length "+str.length);
                    if (str[0].startsWith("Targ")||str[12].equalsIgnoreCase("NA")) {
                    } else {
                        ContraOutTab ct = new ContraOutTab(str[0], Double.parseDouble(str[1]), str[2].trim(), str[3], Double.parseDouble(str[4]), Double.parseDouble(str[5]), Double.parseDouble(str[6]), Double.parseDouble(str[7]), Double.parseDouble(str[8]), Double.parseDouble(str[9]), Double.parseDouble(str[10]), Double.parseDouble(str[11]), Double.parseDouble(str[12]), str[13], Double.parseDouble(str[14]), Double.parseDouble(str[15]), Double.parseDouble(str[16]), Double.parseDouble(str[17]), Double.parseDouble(str[18]), Double.parseDouble(str[19]), Double.parseDouble(str[20]));
                        if (ct.getAdjusted_P_Value() > this.pthreshold) {
                            ct.setMean_of_LogRatio(0);
                        }
                        if (chrome.add(str[3].trim())) {
                            ArrayList<ContraOutTab> templist = new ArrayList<ContraOutTab>();
                            templist.add(ct);
                            cmap.put(ct.getChr(), templist);
                            
                        } else {
                            cmap.get(ct.getChr()).add(ct);
                        }
                        
                    }
                }
                br.close();
 
                
            } catch (FileNotFoundException ex) {
                System.out.println(tabfile + " is not found! please check your filepath ");
            } catch (IOException ex) {
                System.out.println("parsing tab map  IO error");
            }
            
    }
    
    
    
    public void WriteGenelist(String outname){
        try {
            FileWriter fw = new FileWriter(outname);
            for (int i = 0; i < geneclist.size(); i++) {
                fw.append(geneclist.get(i).toString()+"\r\n");
            }
            fw.flush();
            fw.close();
        } catch (IOException ex) {
            Logger.getLogger(ParseContraOutTab.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public ArrayList<ContraOutTab> getClist() {
        return clist;
    }

    public ArrayList<ContraOutTab> getMergedClist() {
        ArrayList<ContraOutTab> mergedClist=new ArrayList<ContraOutTab>();
        for (int i = 0; i <geneclist.size() ; i++) {
            GenelevelCNV gc=geneclist.get(i);
            
            mergedClist.add(gc.getGeneCTab());
        }
        System.out.println("merged list size\t = \t"+mergedClist.size() );
        return mergedClist;
    }

    public LinkedHashMap<String, ArrayList<ContraOutTab>> getCmap() {
        return cmap;
    }
    
    
    
    public static void main(String[] args) {
        new ParseContraOutTab("","");
    }
}
