/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package ExomeSeqAnalysisPipe.GATKresultParse;


import ExomeSeqAnalysisPipe.mafProcess.GVC2TCGAmaf.GVCsimp_2_tcga4maf;
import ExomeSeqAnalysisPipe.Annovar.AnnovarDB_TXT;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Arrays;
import java.util.logging.Level;
import java.util.logging.Logger;
import pub.ToolsforCMD;

/**
 *
 * @author Administrator
 */
public class FormatTransfer {
    public static void GATKcombineVariant_To_AnnovarDB(String input_gatk_combine_file, String output_annovar_txt){
        BufferedReader br;
        try {
            br = new BufferedReader(new java.io.FileReader(new File(input_gatk_combine_file)));
             FileWriter fw = new FileWriter(output_annovar_txt);
             int countline=0;
       while (br.ready()) {
           countline++;
            String dataStr = br.readLine().trim();
            if(dataStr.startsWith("#"))continue;
            
            GATKcombineVariant_format gbv = FormatTransfer.parsingGATKcombineVariant(dataStr);
            if(gbv==null){
                 System.out.println(ToolsforCMD.print_ansi_RED(String.valueOf(countline))+" line is skipped due to null GATKcombineVariant object\r\n");
                continue;
            }
            AnnovarDB_TXT at = new AnnovarDB_TXT(gbv.getCHROM(),gbv.getPOS(),gbv.getPOS(),gbv.getREF(),gbv.getALT().split(",")[0],Double.parseDouble(gbv.getFrequency()));
            //refine indel range from  
            if(at.getREF().length()!=at.getALT().length() && at.getREF().length()>1){
               String ref = at.getREF().substring(1, at.getREF().length());
               String alt = "-";
               at.setREF(ref);
               at.setALT(alt);
               at.setSTART(String.valueOf(Integer.parseInt(at.getSTART())));
               at.setEND(String.valueOf(Integer.parseInt(at.getEND())+at.getREF().length()));
           } else if (at.getREF().length() != at.getALT().length() && at.getREF().length() == 1) {
               String ref = "-";
               String tempstr=at.getALT();
               String alt = tempstr.substring(1, tempstr.length());
               at.setREF(ref);
               at.setALT(alt);
               at.setSTART(String.valueOf(Integer.parseInt(at.getSTART())));
               at.setEND(String.valueOf(Integer.parseInt(at.getEND())+at.getREF().length()));
           }
            fw.append(at.toString()+"\n");
            fw.flush();
        }

        fw.close();
        } catch (FileNotFoundException ex) {
            Logger.getLogger(FormatTransfer.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(FormatTransfer.class.getName()).log(Level.SEVERE, null, ex);
        }
       
       

      
         
    }
    
 //Parse function   
   public static GATKcombineVariant_format parsingGATKcombineVariant(String str){
       String[] aa=str.split("\t");
       if(aa.length>0){
           GATKcombineVariant_format tempC=new GATKcombineVariant_format(aa[0],aa[1],aa[2],aa[3],aa[4],aa[5],aa[6],aa[7],aa[8]);
           tempC.setSampleVariant(Arrays.copyOfRange(aa,9,aa.length-1));
           return(tempC);
       }else{
          return(null);
       }
       
        
   }
   //test code s
    public static void main(String[] args) {
        String tempstr="C".split(",")[1];
               String alt = tempstr.substring(1, tempstr.length());
        System.out.println(alt);   
    }
}
