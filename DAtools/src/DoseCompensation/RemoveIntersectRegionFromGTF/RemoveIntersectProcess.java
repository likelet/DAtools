/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package DoseCompensation.RemoveIntersectRegionFromGTF;

import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.logging.Level;
import java.util.logging.Logger;
import pub.GTF9;
import pub.GTF9_reader;

/**
 * <p>RemoveIntersectProcess</p>
 * <p>Created on 2015-12-24 10:55:18</p>
 * <p>Author Email: zhaoqi3@mail2.sysu.edu.cn</p>
 * @author ZHAO Qi
 * @date 2015-12-24 10:55:18
 * @version java 1.6.0
 * @version 
 */
public class RemoveIntersectProcess {

    private String inputGTFfile;
    private String inputIntersectFile;
    private String outfilename;
    private ArrayList<GTF9> outgtf=new ArrayList<GTF9>();

    public RemoveIntersectProcess(String inputGTFfile, String inputIntersectFile, String outfilename) {
        this.inputGTFfile = inputGTFfile;
        this.inputIntersectFile = inputIntersectFile;
        this.outfilename = outfilename;
        this.process();
        new gtf2lengthfile().generatedLengthFile(outgtf, outfilename+".lengthfile");
    }

    public String getInputGTFfile() {
        return inputGTFfile;
    }

    
    
    
    public void process() {
        ArrayList<GTF9> gtflist=GTF9_reader.getGTF9list(inputGTFfile);
//        System.out.println(gtflist.size());
        HashMap<String,ArrayList<IntersectBed>> str2bedMap=new ParseIntersectBed(inputIntersectFile).getStr2bedMap();
        
        String str=null;
        int count=1;
        try {
            FileWriter fw = new FileWriter(outfilename);
            for (int i = 0; i < gtflist.size(); i++) {
                GTF9 gtf=gtflist.get(i);
                str=gtf.getSeqname()+"_"+(gtf.getStart()-1)+"_"+gtf.getEnd()+"_"+gtf.getStrand();
//                System.out.println("\t"+str);
                
                
//            System.out.println(str);
                if(str2bedMap.get(str)==null){
                    fw.append(gtf.toString()+"\n");
//                    System.out.println(gtf.toString() );
                }else{
                    
                    str=removeInterRegionAndGetStr(gtf,str2bedMap.get(str));
                    
                    if(str!=""){
                          count++;
                        fw.append(str+"\n");
//                        System.out.println(str);
                    }
                }
                
            }
//            System.out.println(count);
            fw.flush();
            fw.close();
        } catch (IOException ex) {
            Logger.getLogger(RemoveIntersectProcess.class.getName()).log(Level.SEVERE, null, ex);
        }    
    }
    
    //get rid of intersect region from gtfterms
    public String removeInterRegionAndGetStr(GTF9 gtf, ArrayList<IntersectBed> iblist) {
        Collections.sort(iblist,new CompareIntersectionBed());
//        System.out.println(iblist.toString());
        int i = 0;
        String str = "";
        for (; i < iblist.size(); i++) {
//          System.out.println(iblist.get(i).getStart());
            if (i == 0) {
                GTF9 gtf1 = new GTF9(gtf.getSeqname(), gtf.getSource(), gtf.getFeature(), gtf.getStart(), iblist.get(i).getStart(), gtf.getScore(), gtf.getStrand(), gtf.getFrame(), gtf.getAttrbute());
//                System.out.println(gtf1.toString());
                if (gtf1.getEnd() - gtf1.getStart() >= 25) {
//                    System.out.println(gtf1.getSpecificAttrbute("gene_name"));
                    gtf1.setSpecificAttrbute("gene_name", gtf1.getSpecificAttrbute("gene_name") + "_" + i);
                    str += gtf1.toString() + "\n";
                    this.outgtf.add(gtf1);
                }
            }
            if ((i + 1) != iblist.size()) {
                GTF9 gtf1 = new GTF9(gtf.getSeqname(), gtf.getSource(), gtf.getFeature(), iblist.get(i).getEnd(), iblist.get(i + 1).getStart(), gtf.getScore(), gtf.getStrand(), gtf.getFrame(), gtf.getAttrbute());
//                 System.out.println(gtf1.toString());
                if (gtf1.getEnd() - gtf1.getStart() >= 25) {
                    gtf1.setSpecificAttrbute("gene_name", gtf1.getSpecificAttrbute("gene_name") + "_" + i);
                    str += gtf1.toString() + "\n";
                    this.outgtf.add(gtf1);
                }
            }
        }
//        System.out.println(i);
        GTF9 gtf1 = new GTF9(gtf.getSeqname(), gtf.getSource(), gtf.getFeature(), iblist.get(i-1).getEnd(), gtf.getEnd(), gtf.getScore(), gtf.getStrand(), gtf.getFrame(), gtf.getAttrbute());
//        System.out.println(gtf1.toString());
        if (gtf1.getEnd() - gtf1.getStart() >= 25) {
            gtf1.setSpecificAttrbute("gene_name", gtf1.getSpecificAttrbute("gene_name") + "_" + i);
            str += gtf1.toString() + "\n";
            this.outgtf.add(gtf1);
        }
        if(str!=""){
            str=str.substring(0,str.length()-1);
        }
//        System.out.println(str);
        return str;
    }

    
    
    class CompareIntersectionBed implements Comparator {

    @Override
    public int compare(Object o1, Object o2) {
        IntersectBed num1=(IntersectBed) o1;
        IntersectBed num2=(IntersectBed) o2;
        if(num1.getStart()<num2.getStart()){
            return -1;
        }else if(num1.getStart()==num2.getStart()){
            return 0;
        }else{
            return 1;
        }       
    }

  
}
    
    
    public static void main(String[] args) {
        new RemoveIntersectProcess("F:\\百度云同步盘\\resouces\\projects\\splicing-进化\\gtf\\exon.gtf","F:\\百度云同步盘\\resouces\\projects\\splicing-进化\\gtf\\protein_intersect.bed","F:\\百度云同步盘\\resouces\\projects\\splicing-进化\\gtf\\intersect_free.gtf");
    }
}
