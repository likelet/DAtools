/*
 * Static the snp frequency in all samples 
 */

package ExomeSeqAnalysisPipe.SNPfrequencyInSamples;

import ExomeSeqAnalysisPipe.VCFReader;
import ExomeSeqAnalysisPipe.VCFrecord;
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

/**
 *
 * @author ZHAO Qi
 * @date 2015-3-26 10:10:18
 * @version 1.6.0
 */
public class SNPfrequencyInSamples {
    ArrayList<HashMap<String,VCFrecord>> allfileSampleList=new ArrayList<HashMap<String,VCFrecord>>();
    HashSet<String> snpset=new HashSet<String>();

    public SNPfrequencyInSamples(String vcfdir,String output) throws IOException {
        File[] filelist=FilelistReader.getFileList(vcfdir, ".vcf");
        this.initialized(filelist);
        this.process(output);
    }
    
    
    
    
    public void initialized(File[] filelist) throws FileNotFoundException, IOException{
        String names="";
        String tempstr="";
        for (int i = 0; i < filelist.length; i++) {
            System.out.println("Read File: "+ filelist[i]);
            BufferedReader br = new BufferedReader(new FileReader(filelist[i]));
            HashMap<String,VCFrecord> tempmap=new HashMap<String,VCFrecord>();
            //FileWriter fw = new FileWriter(outputFile);
            while (br.ready()) {
                tempstr=br.readLine();
                if(tempstr.startsWith("#")){
                }else{
                    VCFrecord tempvcf=VCFReader.getvcfFromStr(tempstr);
                    
                    names=tempvcf.tosimple();
                    snpset.add(names);
                    tempmap.put(names, tempvcf);
                }
            }
            allfileSampleList.add(tempmap);  
            System.out.println("Read File: "+ filelist[i]+"Finished!");
            br.close();
        }
    }
    
    public void process(String outfile) throws IOException{
        FileWriter fw = new FileWriter(new File(outfile));
        int count=0;
        for (Iterator it = snpset.iterator(); it.hasNext();) {
            String str = (String) it.next();
             fw.append(str+"\t");
             for (int i = 0; i < allfileSampleList.size(); i++) {
                if(allfileSampleList.get(i).get(str)!=null){
                    count++;
                }
            }
             fw.append(count+"\n");
             count=0;
        }
        fw.close(); 
    }
    public static void main(String[] args) throws IOException {
     new SNPfrequencyInSamples("F:\\mywork\\project\\Peng\\SamtoolsResult\\process1\\process1Vcf","F:\\mywork\\project\\Peng\\SamtoolsResult\\process1\\process1Vcf\\status");
    }
}
