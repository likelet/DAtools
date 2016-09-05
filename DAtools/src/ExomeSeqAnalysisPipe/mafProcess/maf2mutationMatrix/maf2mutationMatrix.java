/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package ExomeSeqAnalysisPipe.mafProcess.maf2mutationMatrix;

import ExomeSeqAnalysisPipe.mafProcess.vepMAF2TCGAmaf.MAFby_Tool;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.LinkedHashMap;
import java.util.LinkedHashSet;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * <p>maf2mutationMatrix</p>
 * <p>Created on 2016-1-20 16:34:11</p>
 * <p>Author Email: zhaoqi3@mail2.sysu.edu.cn</p>
 * @author ZHAO Qi
 * @date 2016-1-20 16:34:11
 * @version java 1.6.0
 * @version 
 */
public class maf2mutationMatrix {

    private ArrayList<MAFby_Tool> maflist;
    private String outfile;

    public maf2mutationMatrix(String maffile,String outfile) {
        maflist=ParseVepMAF.getMAFlist(maffile);
        this.outfile=outfile;
        this.process();
    }
    
    public void process(){
        LinkedHashMap<String,gene2mutationSample> genelistmap=new LinkedHashMap<String,gene2mutationSample>();
        HashSet<String> genename=new HashSet<String> ();
        LinkedHashSet<String> samplename=new LinkedHashSet<String> ();
        for (int i = 0; i < maflist.size(); i++) {
            MAFby_Tool mt=maflist.get(i);
            samplename.add(mt.getTumor_Sample_Barcode());
            if(genename.add(mt.getHugo_Symbol())){
                gene2mutationSample geneline=new gene2mutationSample(mt.getHugo_Symbol());
                geneline.ismutatedMap.put(mt.getTumor_Sample_Barcode(), 1);
                genelistmap.put(mt.getHugo_Symbol(), geneline);
            }else{
                genelistmap.get(mt.getHugo_Symbol()).ismutatedMap.put(mt.getTumor_Sample_Barcode(), 1);
            }
        }
        
        try {
            FileWriter fw = new FileWriter(outfile);
            fw.append("Gene\t");
            for (String str:samplename) {
                fw.append(str+"\t");
                
            }
            fw.append("\n");
            for(String gene:genename){
                  gene2mutationSample geneline =genelistmap.get(gene);
                  fw.append(gene+"\t");
                  for (String samplenamestr:samplename) {
                      LinkedHashMap<String,Integer> ismutatedMap=geneline.getIsmutatedMap();
                      
                      if(ismutatedMap.get(samplenamestr)!=null){
                          fw.append(1+"\t");
                      }else{
                          fw.append(0+"\t");
                      }
                       
                    
                }
                  fw.append("\n");
            }
               
            
            fw.flush();
            fw.close();
        } catch (IOException ex) {
            Logger.getLogger(maf2mutationMatrix.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    
    class gene2mutationSample{
        private String gene;
        private LinkedHashMap<String,Integer> ismutatedMap=new LinkedHashMap<String,Integer>();

        public gene2mutationSample(String gene) {
            this.gene = gene;
        }

        
        
        public String getGene() {
            return gene;
        }

        public void setGene(String gene) {
            this.gene = gene;
        }

        public LinkedHashMap<String, Integer> getIsmutatedMap() {
            return ismutatedMap;
        }

        public void setIsmutatedMap(LinkedHashMap<String, Integer> ismutatedMap) {
            this.ismutatedMap = ismutatedMap;
        }
        
        
    }
    
    public static void main(String[] args) {
        new maf2mutationMatrix("F:\\百度云同步盘\\resouces\\projects\\曾昭雷项目\\pb_genepanel.maf.txt","F:\\百度云同步盘\\resouces\\projects\\曾昭雷项目\\pb_genepanel.matrix");
    }
}
