/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package DoseCompensation.RemoveIntersectRegionFromGTF;

import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.logging.Level;
import java.util.logging.Logger;
import pub.GTF9;
import pub.GTF9_reader;

/**
 * <p>gtf2lengthfile</p>
 * <p>Created on 2015-12-25 12:37:13</p>
 * <p>Author Email: zhaoqi3@mail2.sysu.edu.cn</p>
 * @author ZHAO Qi
 * @date 2015-12-25 12:37:13
 * @version java 1.6.0
 * @version
 */
public class gtf2lengthfile {
     private int totalreads = 0;
    private HashMap<String, Integer> geneLengthMap2 = new HashMap<String, Integer>();
    private HashMap<String,String> gene2chr=new HashMap<String,String>();

    public gtf2lengthfile() {
    }

    
    
    public gtf2lengthfile(String gtffile,String outfile) {
        ArrayList<GTF9> gtf9list=GTF9_reader.getGTF9list(gtffile);
         for (int i = 0; i < gtf9list.size(); i++) {
            gene2chr.put(gtf9list.get(i).getSpecificAttrbute("gene_id"), gtf9list.get(i).getSeqname());
        }
        generatedLengthFile(gtf9list,outfile);
    }
   
    
    
    
    public void generatedLengthFile(ArrayList<GTF9> gtf9list,String outfile) {
        HashSet<String> nameset=new HashSet<String>(); 
        LinkedHashMap<String,genelength> genelengthlistmap=new LinkedHashMap<String,genelength>();
        for (int i = 0; i < gtf9list.size(); i++) {
            GTF9 gtf=gtf9list.get(i);
            if(nameset.add(gtf.getSpecificAttrbute("gene_id"))){
                genelength gl=new genelength(gtf.getSpecificAttrbute("gene_id"));
                gl.addlength(gtf.getEnd()-gtf.getStart());
                genelengthlistmap.put(gtf.getSpecificAttrbute("gene_id"), gl);
            }else{
                genelengthlistmap.get(gtf.getSpecificAttrbute("gene_id")).addlength(gtf.getEnd()-gtf.getStart());
            }
        }
        
        //write out
        try {
            FileWriter fw = new FileWriter(outfile);
            
            for (Iterator it =  genelengthlistmap.keySet().iterator(); it.hasNext();) {
                String tempstr=(String) it.next();
                geneLengthMap2.put(genelengthlistmap.get(tempstr).getGeneid(), genelengthlistmap.get(tempstr).getLength());
                totalreads+=genelengthlistmap.get(tempstr).getLength();
                fw.append(genelengthlistmap.get(tempstr).toString()+"\n");
            }
            fw.flush();
            fw.close();
        } catch (IOException ex) {
            Logger.getLogger(gtf2lengthfile.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }
    class genelength{
        String geneid;
        int length=0;

        public genelength(String geneid) {
            this.geneid = geneid;
        }
        
        public void addlength(int length){
            this.length+=length;
        }

        public String getGeneid() {
            return geneid;
        }

        public void setGeneid(String geneid) {
            this.geneid = geneid;
        }

        public int getLength() {
            return length;
        }

        public void setLength(int length) {
            this.length = length;
        }

        @Override
        public String toString() {
            return geneid + "\t" + length ;
        }
        
        
    }

    public int getTotalreads() {
        return totalreads;
    }

    public HashMap<String, Integer> getGeneLengthMap2() {
        return geneLengthMap2;
    }

    public HashMap<String, String> getGene2chr() {
        return gene2chr;
    }
    
    
}
