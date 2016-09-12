/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package FastaProcess.Gff2FeatureFasta;

import java.io.BufferedReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.HashMap;
import java.util.Iterator;

/**
 *
 * @author zhengyueyuan
 */
public class GffToFeatureFasta {
   HashMap<String,StringBuffer> genomeMap=new HashMap();
   GffToFeatureFasta(String file1,String file2,String file3){
       try{
           BufferedReader br1=new BufferedReader(new java.io.FileReader(new java.io.File(file1)));
           BufferedReader br2=new BufferedReader(new java.io.FileReader(new java.io.File(file2)));
           FileWriter fw=new FileWriter(file3);
           this.genomeMap=new HashMap();
           StringBuffer chromSeq=new StringBuffer();
           String chrom="";
           while(br1.ready()){
               String data=br1.readLine();
               if(data.contains(">")){
                   chrom=data.substring(1); 
                   chromSeq=new StringBuffer();
                   genomeMap.put(chrom,chromSeq);                                   
               }else{
                   genomeMap.get(chrom).append(data);
               }
           }
           while(br2.ready()){
               String data=br2.readLine();
               if(!data.substring(0,2).equals("##")){
                   String[] dataArr=data.split("\t");
                   String chromInfo=dataArr[0];
                   String type=dataArr[2];
                   int start=Integer.parseInt(dataArr[3]);
                   int end=Integer.parseInt(dataArr[4]);
                   String strand=dataArr[6];
                   String geneName=dataArr[8].split(";")[1].substring(5); 
                   if(type.equals("mRNA")){
                       fw.append(">"+chromInfo+" "+geneName+" "+start+"-"+end+"\n");
                       if(strand.equals("+")){
                           fw.append(genomeMap.get(chromInfo).substring(start-1, end)+"\n");
                       }else{
                           fw.append(getReverseStrand(genomeMap.get(chromInfo).substring(start-1, end))+"\n");
                       }                         
                   }                  
               }
           }
           fw.flush();
           fw.close();
       }catch (IOException ex){
           
       }
   }
   public String getReverseStrand(String seq){
       String reverseSeq="";
       for(int i=seq.length()-1;i>=0;i--){
          char base=seq.charAt(i);
          if(base=='A'||base=='a'){
              base='T';
          }else if(base =='T' || base=='t'){
              base='A';
          }else if(base =='G' || base=='g'){
              base='C';
          }else if(base =='C' || base=='c'){
              base='G';
          }
          reverseSeq+=base;
       }
       return reverseSeq;
   }
}
