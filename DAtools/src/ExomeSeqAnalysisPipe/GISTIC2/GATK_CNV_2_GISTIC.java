/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ExomeSeqAnalysisPipe.GISTIC2;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

/**
 *
 * @author Administrator
 * @since 2019-3-14
 * @coding time 8:50:19
 * @author Qi Zhao
 */
public class GATK_CNV_2_GISTIC {

    private File inputFile;
    private String samplename; 
    private String outputFile;

    
     public GATK_CNV_2_GISTIC(String inputFile) {
        this.inputFile = new File(inputFile);
        this.samplename= this.inputFile.getName().split("\\.")[0];
        outputFile=samplename+".gistic.seg";
         
    }
    

    public GATK_CNV_2_GISTIC(String inputFile, String outputFile) {
        this.inputFile = new File(inputFile);
        this.outputFile = outputFile;
        this.samplename= this.inputFile.getName().split(".")[0];
    }

    public ArrayList<GATK_CR_SEG> parseInput() throws FileNotFoundException, IOException {
        System.out.println("Parsing "+inputFile.getName());
        BufferedReader br = new BufferedReader(new java.io.FileReader(inputFile));
        ArrayList<GATK_CR_SEG> gatk_cr_list =new ArrayList<GATK_CR_SEG> ();
        while (br.ready()) {  
            String temps=br.readLine();
            if(temps.startsWith("@")||temps.startsWith("CONTIG")) continue;
            String[] dataStr = temps.trim().split("\t");
            gatk_cr_list.add(new GATK_CR_SEG(dataStr[0],Integer.parseInt(dataStr[1]),Integer.parseInt(dataStr[2]),Integer.parseInt(dataStr[3]),Double.parseDouble(dataStr[4])));
        }
        return(gatk_cr_list);
    }

    public ArrayList<GISTIC_SEGMENT> GATK_CNV_2_GISTIC_process(ArrayList<GATK_CR_SEG> gatk_cr_list) {
        ArrayList<GISTIC_SEGMENT> gistic_seg_list = new ArrayList<GISTIC_SEGMENT>();
        for (GATK_CR_SEG gatk_cr : gatk_cr_list) {
            String str = gatk_cr.getChr();
            if (str.startsWith("chr"));
            str = str.substring(3);
            gistic_seg_list.add(new GISTIC_SEGMENT(this.samplename, str, gatk_cr.getStart(), gatk_cr.getEnd(), gatk_cr.getNUM_POINTS_COPY_RATIO(), gatk_cr.getMEAN_LOG2_COPY_RATIO()));
        }
        return (gistic_seg_list);
    }
    
     public ArrayList<GISTIC_SEGMENT> GATK_CNV_2_GISTIC_process() throws IOException {
         ArrayList<GATK_CR_SEG> gatk_cr_list=this.parseInput();
        ArrayList<GISTIC_SEGMENT> gistic_seg_list = new ArrayList<GISTIC_SEGMENT>();
        for (GATK_CR_SEG gatk_cr : gatk_cr_list) {
            String str = gatk_cr.getChr();
            if (str.startsWith("chr"));
            str = str.substring(3);
            gistic_seg_list.add(new GISTIC_SEGMENT(this.samplename, str, gatk_cr.getStart(), gatk_cr.getEnd(), gatk_cr.getNUM_POINTS_COPY_RATIO(), gatk_cr.getMEAN_LOG2_COPY_RATIO()));
        }
        return (gistic_seg_list);
    }
    /**
     * Get the value of gatk_cr_list
     *
     * @return the value of gatk_cr_list
     */
     public void writeOut(ArrayList<GISTIC_SEGMENT> gistic_seg_list ) throws IOException{
         FileWriter fw = new FileWriter(outputFile);
         for (GISTIC_SEGMENT gisticseg : gistic_seg_list){
             fw.append(gisticseg.toString()+System.lineSeparator());
         }
         fw.flush();
         fw.close();
         
     }
     public void processAll() throws IOException{
         ArrayList<GISTIC_SEGMENT> gistic_seg_list =this.GATK_CNV_2_GISTIC_process();
         this.writeOut(gistic_seg_list);
         System.out.println("Converting "+inputFile.getName()+ " Done!");
     }
     
    
    public static void main(String[] args) throws IOException {
        new GATK_CNV_2_GISTIC("C:\\Users\\Administrator\\Desktop\\383926T.cr.seg").processAll();
    }

}
