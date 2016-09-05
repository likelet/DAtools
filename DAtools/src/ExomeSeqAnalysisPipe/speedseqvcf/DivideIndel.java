/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package speedseqvcf;

import java.io.BufferedReader;
import java.io.FileWriter;
import java.io.IOException;

/**
 *
 * @author zhengyueyuan
 */
public class DivideIndel {
     DivideIndel(String inputFile,String indelsFile,String otherFile){
         try {
             BufferedReader br = new BufferedReader(new java.io.FileReader(inputFile));
             FileWriter fw = new FileWriter(indelsFile);
             FileWriter fw1 = new FileWriter(otherFile);
             fw.write("Chr"+"\t"+"POS"+"\t"+"REF"+"\t"+"ALT"+"\t"+"Type"+"\t"+"QUAL"+"\t"+"FILTER"+"\t"+"DEEPTH"+"\t"+"REF COUNT"+"\t"+"ALT COUNT"+"\t"+"Allele Frequency"+"\t"+"Func.refGene"+"\t"+"Gene.refGene"+"\t"+"ExonicFunc.refGene"+"\t"+"AAChange.refGene"+"\t"+"cytoBand"+"\t"+"1000g2014oct_all"+"\t"+"snp138"+"\n");
             fw1.write("Chr"+"\t"+"POS"+"\t"+"REF"+"\t"+"ALT"+"\t"+"Type"+"\t"+"QUAL"+"\t"+"FILTER"+"\t"+"DEEPTH"+"\t"+"REF COUNT"+"\t"+"ALT COUNT"+"\t"+"Allele Frequency"+"\t"+"Func.refGene"+"\t"+"Gene.refGene"+"\t"+"ExonicFunc.refGene"+"\t"+"AAChange.refGene"+"\t"+"cytoBand"+"\t"+"1000g2014oct_all"+"\t"+"snp138"+"\n");            
             while(br.ready()){
                 String data=br.readLine();
                 if(!data.startsWith("Chr")){
                     String[] dataArr=data.split("\t");
                     String type=dataArr[4];
                     if(type.equals("ins")||type.equals("del")){
                         fw.write(data+"\n");
                     }else{
                         fw1.write(data+"\n");
                     }
                 }
             }
             fw.flush();
             fw.close();
             fw1.flush();
             fw1.close();
         } catch (IOException ex) {
             ex.printStackTrace();
         }         
     }
     
     public static void main(String[] args) {
        new DivideIndel("E:\\同步盘\\数据分析\\kidney\\Annotation\\right_DHG02693_annot_indel.txt","E:\\同步盘\\数据分析\\kidney\\Annotation\\right_DHG02693_annot_indels.txt","E:\\同步盘\\数据分析\\kidney\\Annotation\\right_DHG02693_annot_oters.txt");   
    }
}
