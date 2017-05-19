/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package FastaProcess.FastaDemuplex;

import FastQprocess.FastQprocess;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import static org.apache.poi.hssf.util.HSSFColor.ORANGE.index;
import pub.DNAsequenceProcess;
import pub.Fasta;
import pub.FastaReader;

/**
 *
 * @author Administrator
 * @since 2017-5-19
 * @coding time 9:46:04
 * @author Qi Zhao
 */
public class fastqDemultiplex {
    private LinkedList<Fasta> fastalist = null;
    private HashMap<String,String> index2sample = new HashMap<String,String> ();
    private ArrayList<indexSample> indexlist = new ArrayList<indexSample>();
    private String currentpath;
    private String filename;

    // index file must be tab-delim like
    //              TCTC  WILD
    //              AGAG  mutation
    public fastqDemultiplex(String fastafile,String indexfile) throws IOException {
        FastaReader fr = new FastaReader(fastafile);
        File fqfile=new File(fastafile);
        filename=fqfile.getName();
        fastalist = fr.getFastaList();
        this.indexParse(indexfile);
        seperateProcess();
    }

    public void seperateProcess() throws IOException{
        FastQprocess fqp= new FastQprocess();
        
        Fasta fa=null;
        String index1 = "";
        String index2 = "";
        String temppath = "";
        DNAsequenceProcess dp=new DNAsequenceProcess();
        for (int i = 0; i < indexlist.size(); i++) {
            index1 = indexlist.get(i).getIndex1();
            index2=indexlist.get(i).getIndex2();
            temppath = "."+ File.separator  +filename+"_"+ indexlist.get(i).getSamplename() + ".fasta";
            FileWriter fw = new FileWriter(temppath);
            for (int j = 0; j < fastalist.size(); j++) {
                fa=fastalist.get(j);
                 String str=fa.getSequence();
                if(str.startsWith(index1)&&str.endsWith(index2)){
                    //trim fasta left && right adaptor
                   
                  fa.setSequence(str.substring(index1.length()-1,str.length()-index2.length()-1 ));
                   fw.append(fa.toString()+"\r\n");
                   fastalist.remove(i);
                   j=j--;
                }else if(str.startsWith(dp.getReverseComplimentary(index2))&&str.endsWith(dp.getReverseComplimentary(index2))){
                    fa.setSequence(str.substring(index2.length()-1,str.length()-index1.length()-1 ));
                   fw.append(fa.toString()+"\r\n");
                   fastalist.remove(i);
                   j=j--;  
                }
            }
            fw.flush();
            fw.close();
            System.out.println(index+"\t"+fastalist.size()+" items left'");
        }
        FileWriter fw2 = new FileWriter(currentpath+File.separator+"unkown.fasta");
        System.out.println(fastalist.size());
        for (int i = 0; i < fastalist.size(); i++) {
            fw2.append(fastalist.get(i).toString()+"\t\n");
        }
        fw2.close();
        
    }
    
    
    public void indexParse(String indexSamplefile) throws FileNotFoundException, IOException {
        File indexfile=new File(indexSamplefile);
        currentpath=indexfile.getParent();
         indexSample is=null;
        BufferedReader br = new BufferedReader(new FileReader(indexfile));
        while (br.ready()) {
            String []tempstr = br.readLine().trim().split("\t");
            is= new indexSample(tempstr[0], tempstr[1],tempstr[2]);
            index2sample.put(tempstr[0]+"_"+tempstr[1], tempstr[2]);
            this.indexlist.add(is);
        }
        br.close();
    }
    
    public static void main(String[] args) throws IOException {
        new fastqDemultiplex("D:\\test.fasta","D:\\index.txt");
    }
}
//storage the index and mapfile

class indexSample {

    private String index1;
    private String index2;
    private String samplename;

    public indexSample(String index1,String index2, String samplename) {
        this.index1 = index1;
        this.index2=index2;
        this.samplename = samplename;
    }

    /**
     * Get the value of samplename
     *
     * @return the value of samplename
     */
    public String getSamplename() {
        return samplename;
    }

    /**
     * Set the value of samplename
     *
     * @param samplename new value of samplename
     */
    public void setSamplename(String samplename) {
        this.samplename = samplename;
    }

    /**
     * Get the value of index
     *
     * @return the value of index
     */
    public String getIndex1() {
        return index1;
    }
 public String getIndex2() {
        return index2;
    }
    /**
     * Set the value of index
     *
     * @param index new value of index
     */
    public void setIndex1(String index1) {
        this.index1 = index1;
    }
       public void setIndex2(String index2) {
        this.index2 = index2;
    }


}
