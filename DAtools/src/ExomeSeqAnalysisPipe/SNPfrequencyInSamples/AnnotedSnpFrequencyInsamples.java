/*
 * from a list of snpeff annotation file to a snp matrix of each sample 
 */

package ExomeSeqAnalysisPipe.SNPfrequencyInSamples;

import ExomeSeqAnalysisPipe.VCFReader;
import ExomeSeqAnalysisPipe.VCFrecord;
import ExomeSeqAnalysisPipe.snpeffanno.SnpEffRecord;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import pub.FilelistReader;
import pub.TableCount;

/**
 *
 * @author ZHAO Qi
 * @date 2015-3-31 12:54:19
 * @version 1.6.0
 */
public class AnnotedSnpFrequencyInsamples {
    HashMap<String,HashMap<String,SnpEffRecord>> allfileSampleList=new HashMap<String,HashMap<String,SnpEffRecord>>();
    HashSet<String> snpset=new HashSet<String>();
    String filenames="";

    public AnnotedSnpFrequencyInsamples(String vcfdir,String output) throws IOException {
        File[] filelist=FilelistReader.getFileList(vcfdir, "anno.txt");
        this.initialized(filelist);
        this.process(output);
    }
     public AnnotedSnpFrequencyInsamples(String vcfdir,String output,String suffix) throws IOException {
        File[] filelist=FilelistReader.getFileList(vcfdir, suffix);
        this.initialized(filelist);
        this.process(output);
    }
    
     public AnnotedSnpFrequencyInsamples(String vcfdir,String chr,int pos) throws IOException {
        File[] filelist=FilelistReader.getFileList(vcfdir, "anno.txt");
        this.initialized(filelist);
         System.out.println(this.search(chr, pos));
    }
    
    
    
    
    public void initialized(File[] filelist) throws FileNotFoundException, IOException{
        String names="";
        String tempstr="";
        for (int i = 0; i < filelist.length; i++) {
            System.out.println("Read File: "+ filelist[i]);
            filenames+=filelist[i].getName()+"\t";
            BufferedReader br = new BufferedReader(new FileReader(filelist[i]));
            HashMap<String,SnpEffRecord> tempmap=new HashMap<String,SnpEffRecord>();
            //FileWriter fw = new FileWriter(outputFile);
            while (br.ready()) {
                tempstr=br.readLine();
                if(tempstr.startsWith("#")){
                }else if(tempstr.contains("NON_SYNONYMOUS_CODING")){
                    SnpEffRecord tempsnpeffrecord=new SnpEffRecord(tempstr,false);
                    
                    names=tempsnpeffrecord.tosimple();
                    snpset.add(names);
                    tempmap.put(tempsnpeffrecord.getChromo()+"\t"+tempsnpeffrecord.getPosition(), tempsnpeffrecord);
                }
            }
            allfileSampleList.put(filelist[i].getName(),tempmap);  
            System.out.println("Read File: "+ filelist[i]+"\tFinished!");
            br.close();
        }
    }
    
    public void process(String outfile) throws IOException{
        FileWriter fw = new FileWriter(new File(outfile));
        String filenamestr="";
        int count=0;
        String codonalt="";
        String aaalt="";
        String statusstr="";
        fw.append("Chromesome	position	Located Gene	samples contains this site	Alter Code	Alter AA\t"+filenames+"\n");
//        for (Iterator it3 = allfileSampleList.keySet().iterator(); it3.hasNext();)  {
//                 filenamestr=(String) it3.next();
//                 System.out.print(filenamestr+"\t");
//        }
//        
        for (Iterator it = snpset.iterator(); it.hasNext();) {
            String str = (String) it.next();
            
             fw.append(str.replace("\t\t", "\t")+"\t");
             for (Iterator it2 = allfileSampleList.keySet().iterator(); it2.hasNext();)  {
                 filenamestr=(String) it2.next();
                if(allfileSampleList.get(filenamestr).get(str.split("\t\t")[0])!=null){
                    count++;
                    codonalt+= allfileSampleList.get(filenamestr).get(str.split("\t\t")[0]).getOld_codon_New_codon()+"\t";
                    aaalt+= allfileSampleList.get(filenamestr).get(str.split("\t\t")[0]).getOld_AA_new_AA()+"\t";
                    statusstr+="1\t";
                }else{
                    statusstr+="0\t";
                }
            }
             codonalt=TableCount.getTablestr(codonalt.split("\t"));
             codonalt=codonalt.substring(0, codonalt.length()-2);
             aaalt=TableCount.getTablestr(aaalt.split("\t"));
             aaalt=aaalt.substring(0, aaalt.length()-2);
             fw.append(count+"\t"+codonalt+"\t"+aaalt+"\t"+statusstr+"\n");
//             fw.append(count+"\n");
             codonalt ="";
             aaalt="";
             statusstr="";
             count=0;
             fw.flush();
        }
        fw.close(); 
    }
    public String search (String chr,int pos){
        String str=chr+"\t"+pos;
        String outstr="";
          for (int i = 0; i < allfileSampleList.size(); i++) {
                if(allfileSampleList.get(i).get(str)!=null){
                   outstr+= allfileSampleList.get(i).get(str).getOld_codon_New_codon()+"\n";
                }
            }
          return outstr;
    }
    public static void main(String[] args) throws IOException {
     new AnnotedSnpFrequencyInsamples("F:\\mywork\\project\\Peng\\SamtoolsResult\\process2\\oceanAnnotation","F:\\mywork\\project\\Peng\\SamtoolsResult\\process2\\oceanAnnotation\\status.txt");
//    new AnnotedSnpFrequencyInsamples("F:\\mywork\\project\\Peng\\SamtoolsResult\\process1\\annoationResult","NC_000913",1301999);
    }
}
