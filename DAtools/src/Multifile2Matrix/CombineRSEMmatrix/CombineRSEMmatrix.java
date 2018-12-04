/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package Multifile2Matrix.CombineRSEMmatrix;

import pub.ReadEnsembleMapfile;
import Multifile2Matrix.Multifile2matrix;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Administrator
 * @since 2016-9-27
 * @coding time 17:23:09
 * @author Qi Zhao
 */
public class CombineRSEMmatrix {

    private String dir;
    private String outfile;
    private String col="7";//for fpkm , 2 for reads count , 6 for tpm
    private boolean isgencode=false;
    private boolean isIsformlevel=false;
    private String transcriptMappingfile;
    public  boolean isCount=false;
    private String gtffle="";// gtf file that you used for mapping RSEM
    
    private String suffix=".genes.results";

    public CombineRSEMmatrix(String dir, String outfile) {
        this.dir = dir;
        this.outfile = outfile;
    }

    public CombineRSEMmatrix(String dir, String outfile, String transcriptMappingfile) {
        this.dir = dir;
        this.outfile = outfile;
        this.transcriptMappingfile = transcriptMappingfile;
    }

    public void setTranscriptMappingfile(String transcriptMappingfile) {
        this.transcriptMappingfile = transcriptMappingfile;
    }

    public void setGtffile(String gtffle) {
        this.gtffle = gtffle;
    }

    
    
    public void setIsgencode(boolean isgencode) {
        this.isgencode = isgencode;
    }

    public void setIsIsformlevel(boolean isIsformlevel) {
        this.isIsformlevel = isIsformlevel;
    }
    
    
    
    public void setCol(String col) {
        this.col = col;
    }
   
    
    
    public void process() throws FileNotFoundException{

        HashMap<String, String> ensemblemap = null;
        
        ensemblemap = ReadEnsembleMapfile.getEnsembleMapFromGTF(gtffle);
     
            
        System.out.println(ensemblemap.size());
        Multifile2matrix mm=null;
        if(isIsformlevel){
            suffix=".isoforms.results";
        }
        
        mm=new Multifile2matrix( dir,  this.suffix, outfile);
        mm.setColnumber(col);
        mm.setSkipfirstline(true);
        mm.process();
        ArrayList<String> filelist=mm.getFilelist();
        HashSet<String> allIterm=mm.getAllIterm();
        HashMap<String, ArrayList<String>> filehashstr =mm.getFilehashstr();
        
        
        FileWriter fw;
        try {
            fw = new FileWriter(new File(outfile));
            if (this.gtffle == "") {
                System.out.println("no gtffile input, ignoring id conversion");
                fw.append("ID\t");
            } else if (this.isIsformlevel) {
                fw.append("ID\tTranscriptName\tGeneName\tGeneType\tStrand\t");
            } else  {
                fw.append("ID\tGeneName\tGeneType\t");
            }
            String headerstr="";
            for (Iterator it = filelist.iterator(); it.hasNext();) {
                String tempstr = (String) it.next();
               headerstr+=new File(tempstr).getName().replaceAll(suffix, "") + "\t";
            }
            headerstr=headerstr.substring(0,headerstr.length()-1);
            fw.append(headerstr+"\n");
            for (Iterator it1 = allIterm.iterator(); it1.hasNext();) {
                String rowstr = (String) it1.next();
//                rowstr=rowstr.split("||.")[0];
                String tempstr2;
                if (this.gtffle == null) {
                    tempstr2 = rowstr;
                } else {
                    tempstr2 = ensemblemap.get(rowstr);
                }
                //get count or tpm
                if(this.isCount){
                    for (int i = 0; i < filehashstr.get(rowstr).size(); i++) {
                        tempstr2 = tempstr2 + "\t" + Math.rint(Double.parseDouble(filehashstr.get(rowstr).get(i)));
                    }
                }else{
                    for (int i = 0; i < filehashstr.get(rowstr).size(); i++) {
                        tempstr2 = tempstr2 + "\t" + filehashstr.get(rowstr).get(i);
                    }
                }
                fw.append(tempstr2 + "\n");
            }
            fw.flush();
            fw.close();
        } catch (IOException ex) {
            Logger.getLogger(Multifile2matrix.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }
   
    public static void main(String[] args) throws FileNotFoundException {
       CombineRSEMmatrix cc=new CombineRSEMmatrix("E:\\javatest","E:\\javatest\\result.matrix"); 
           cc.isgencode=true;
       cc.process();
//System.out.println("a.b".split("||.")[0]);
    }
}
