package RNAseqPipeline.DifferentialExpressionAnalysis;

import Algorithm.MultipleCorrection.FDR;
import pub.ReadEnsembleMapfile;
import RNAseqPipeline.GeneLengthFileReader;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import static java.lang.Math.log;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.IndexedColors;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import pub.Rexe;
import pub.ToolsforCMD;

/*
 * Differential analysis of RNA-seq count data without replicates
 *ref :Audic S, Claverie JM. The significance of digital gene expression profiles. Genome Res. 1997;7:986–995.
 */
/**
 *
 * @author Qi Zhao
 */
public class DEGeneAnalysis {

    private double N;
    private int lib1;
    private int lib2;
    private String countFile1;
    private String countFile2;
    private String outputFile;
    private ArrayList<DEGeneForm> countList;
    private HashMap<String, Integer> gene2length;
    private String filename1 = "Sample1";
    private String filename2 = "Sample2";
    private ArrayList<Double> plist=new ArrayList<Double>();
    public String type="com";
    private HashMap<String,String> ensemblemap= null;
    private String gtffle="";// gtf file that you used for mapping RSEM    

    public DEGeneAnalysis() {
    }

    public void setGtffile(String gtffle) {
        this.gtffle = gtffle;
    }
    
    
    public DEGeneAnalysis(String countFile1, String countFile2, String outputFile) throws IOException {
        if (countFile1.endsWith("genes.results")) {
            System.out.println(ToolsforCMD.print_ansi_CYAN("Parsing your file as RSEM output format, press ctrl+z if not expected "));
            this.type="rsem";
        } 
        this.countFile1=countFile1;
        this.countFile2=countFile2;
        this.outputFile=outputFile;
    }

    public void getDEgenePvalue() throws FileNotFoundException, IOException {
        if (type.equals("rsem")) {
            countList = countListRSEM(countFile1, countFile2);
        }else if (type.equals("htseq")) {
            countList = countListHTseq(countFile1, countFile2);
        }else if (type.equals("com")){
        countList = countListCommon(countFile1, countFile2);
        }

        //table header name defination
        String[] str1 = new File(countFile1).getName().split("\\.");
        String[] str2 = new File(countFile2).getName().split("\\.");
        filename1 = str1[0];
        filename2 = str2[0];
        int k = 0;
        while (filename1.equals(filename2) && str1.length > k) {
            filename1 = filename1 + "_" + str1[k + 1];
            filename2 = filename2 + "_" + str1[k + 1];
            k++;
        }    
               
        double tempP = 0;
        ArrayList<Double> plist=new ArrayList<Double>();
        if (gene2length != null) {
                for (Iterator<DEGeneForm> it = countList.iterator(); it.hasNext();) {
                    DEGeneForm countData = it.next();
                    int sample1Count = countData.getSample1Counts();
                    int sample2Count = countData.getSample2Counts();
                    tempP = getPvalue(sample1Count, sample2Count);
                    countData.setpValue(tempP);
                    
                        this.calculateFPKM_FC(countData);
                    
                    plist.add(tempP);
                }
            } else {
                for (Iterator<DEGeneForm> it = countList.iterator(); it.hasNext();) {
                    DEGeneForm countData = it.next();
                    int sample1Count = countData.getSample1Counts();
                    int sample2Count = countData.getSample2Counts();
                    tempP = getPvalue(sample1Count, sample2Count);
                    countData.setpValue(tempP);
                    if(type.equals("rsem")){
                        double fc=Math.log10((countData.getSample1FPKM()+1)/(countData.getSample2FPKM()+1))/Math.log10(2);
                        countData.setLog2FC(fc);
                    }
                    plist.add(tempP);
                }
            }
        
        
        //Calculater fdr 
        ArrayList<Double> fdrlist=new FDR(plist).getFdrDoublelist();
        for (int i = 0; i < fdrlist.size(); i++) {
            countList.get(i).setFDR(fdrlist.get(i));
        }
    }

    //
    public void calculateFPKM_FC(DEGeneForm countData) {
        double fpkm1 = 0;
        double fpkm2 = 0;
        double FC = 0;
        int readscount1 = countData.getSample1Counts();
        int readscount2 = countData.getSample2Counts();
        String genename = countData.getGeneName();
        double length = (double) this.gene2length.get(genename);
        length = length / 1000;
        double scalelib1 = lib1 / 1000000;
        double scalelib2 = lib2 / 1000000;
        fpkm1 = readscount1 / (length * scalelib1);
        fpkm2 = readscount2 / (length * scalelib2);
        FC = Math.log10((fpkm2 + 1) / (fpkm1 + 1)) / Math.log10(2);
        countData.setSample1FPKM(fpkm1);
        countData.setSample2FPKM(fpkm2);
        countData.setLog2FC(FC);

    }

    private double getPvalue(int x, int y) {
        double lnPvalue = y * log(N) + getLnSum(x + y) - getLnSum(x) - getLnSum(y) - (x + y + 1) * log(1 + N);
        double pValue = Math.exp(lnPvalue);
        return pValue;
    }

    private double getLnSum(int n) {
        double sum = 0;
        if (n == 0) {
            return 0;
        }

        for (int i = 1; i <= n; i++) {
            sum = sum + Math.log(i);
        }

        return sum;

    }

    private ArrayList<DEGeneForm> countListHTseq(String countFile1, String countFile2) throws FileNotFoundException {

        ArrayList<DEGeneForm> countList = new ArrayList();
        try {
            BufferedReader br1 = new BufferedReader(new java.io.FileReader(countFile1));
            BufferedReader br2 = new BufferedReader(new java.io.FileReader(countFile2));
            int sample1 = 0;
            int sample2 = 0;
            while (br1.ready()) {
                String data1 = br1.readLine();
                String[] dataArr1 = data1.split("\t");
                String geneName = dataArr1[0];
                String data2 = br2.readLine();
                String[] dataArr2 = data2.split("\t");
                int sample1Count = Integer.parseInt(dataArr1[1]);
                sample1 = sample1 + sample1Count;
                int sample2Count = Integer.parseInt(dataArr2[1]);
                sample2 = sample2 + sample2Count;
                countList.add(new DEGeneForm(geneName, sample1Count, sample2Count, 0, 1, 1));
            }
            this.lib1 = sample1;
            this.lib2 = sample2;
            int length = countList.size() - 1;
            //deleted HTseq last 5 lines 
            for (int i = 0; i < 5; i++) {
                lib1 = lib1 - countList.get(length - i).getSample1Counts();
                lib2 = lib2 - countList.get(length - i).getSample2Counts();
                countList.remove(countList.size() - 1);
            }
            N = (double) lib2 / lib1;
        } catch (IOException ex) {
            System.out.println("IO error, pleas check your file");
        }
        return countList;
    }
    private ArrayList<DEGeneForm> countListCommon(String countFile1, String countFile2) throws FileNotFoundException {

        ArrayList<DEGeneForm> countList = new ArrayList();
        try {
            BufferedReader br1 = new BufferedReader(new java.io.FileReader(countFile1));
            BufferedReader br2 = new BufferedReader(new java.io.FileReader(countFile2));
            double sample1 = 0.0;
            double sample2 = 0.0;
            while (br1.ready()) {
                String data1 = br1.readLine();
                String[] dataArr1 = data1.split("\t");
                String geneName = dataArr1[0];
                String data2 = br2.readLine();
                String[] dataArr2 = data2.split("\t");
                double sample1Count = Double.parseDouble(dataArr1[1]);
                sample1 = sample1 + sample1Count;
                double sample2Count =  Double.parseDouble(dataArr2[1]);
                sample2 = sample2 + sample2Count;
                countList.add(new DEGeneForm(geneName, (int) Math.round(sample1Count), (int) Math.round(sample2Count), 0, 1, 1));
            }
            this.lib1 = (int) Math.round(sample1);
            this.lib2 = (int) Math.round(sample2);
            int length = countList.size() - 1;
            N = (double) lib2 / lib1;
        } catch (IOException ex) {
            System.out.println("IO error, pleas check your file");
        }
        return countList;
    }
    private ArrayList<DEGeneForm> countListRSEM(String countFile1, String countFile2) throws FileNotFoundException {

        ArrayList<DEGeneForm> countList = new ArrayList();
        
        if(this.gtffle!=null){
            ensemblemap = ReadEnsembleMapfile.getEnsembleMapFromGTF(gtffle);
        }
        try {
            BufferedReader br1 = new BufferedReader(new java.io.FileReader(countFile1));
            BufferedReader br2 = new BufferedReader(new java.io.FileReader(countFile2));
            System.out.println("Start reading file "+ countFile1);
            System.out.println("Start reading file "+ countFile2);
            double sample1 = 0;
            double sample2 = 0;
            br1.readLine();
            br2.readLine();
            if (ensemblemap != null) {
                while (br1.ready()) {
                    String data1 = br1.readLine();
                    String[] dataArr1 = data1.split("\t");
                    String geneName = dataArr1[0];
                    String data2 = br2.readLine();
                    String[] dataArr2 = data2.split("\t");
                    double sample1Count = Double.parseDouble(dataArr1[4]);
                    sample1 = sample1 + sample1Count;
                    double sample2Count = Double.parseDouble(dataArr2[4]);
                    sample2 = sample2 + sample2Count;
                    DEGeneForm df = new DEGeneForm(geneName, (int) Math.round(sample1Count), (int) Math.round(sample2Count), Double.parseDouble(dataArr1[6]), Double.parseDouble(dataArr2[6]), 0);
                   if(ensemblemap.get(geneName)!=null){
                       df.setGeneNameDetails(ensemblemap.get(geneName).split("\t")[1]);
                       df.setGenetype(ensemblemap.get(geneName).split("\t")[2]);
                   } 
                    countList.add(df);
                }
            }else{
                while (br1.ready()) {
                    String data1 = br1.readLine();
                    String[] dataArr1 = data1.split("\t");
                    String geneName = dataArr1[0];
                    String data2 = br2.readLine();
                    String[] dataArr2 = data2.split("\t");
                    double sample1Count = Double.parseDouble(dataArr1[4]);
                    sample1 = sample1 + sample1Count;
                    double sample2Count = Double.parseDouble(dataArr2[4]);
                    sample2 = sample2 + sample2Count;
                    DEGeneForm df = new DEGeneForm(geneName, (int) Math.round(sample1Count), (int) Math.round(sample2Count), Double.parseDouble(dataArr1[6]), Double.parseDouble(dataArr2[6]), 0);
//                df.setGeneNameDetails(ensemblemap.get(geneName).split("\t")[1]);
//                df.setGenetype(ensemblemap.get(geneName).split("\t")[2]);
                    countList.add(df);
                }
            }
            
            this.lib1 = (int) Math.round(sample1);
            this.lib2 = (int) Math.round(sample2);
            int length = countList.size() - 1;
            N = (double) lib2 / lib1;
        } catch (IOException ex) {
            System.out.println(ToolsforCMD.print_ansi_RED("IO error, pleas check your file"));
        }
        return countList;
    }
    public void setGene2length(HashMap<String, Integer> gene2length) {
        this.gene2length = gene2length;
    }

    public void getOutputFile() {
        System.out.println(ToolsforCMD.print_ansi_CYAN("Writing Out ..."));
        try {
            
            String tempstr =null;
            if(type.equals("rsem")){
               tempstr = "Gene\t" + this.filename1 + "\t" + this.filename2 + "\t" + this.filename1 + "_FPKM\t" + this.filename2 + "_FPKM\tlog2FC\tpValue\tFDR\tGeneName\tType";
            }else{
               tempstr = "Gene\t" + this.filename1 + "\t" + this.filename2 + "\t" + this.filename1 + "_FPKM\t" + this.filename2 + "_FPKM\tlog2FC\tpValue\tFDR";
            }
          
            FileWriter fw = new FileWriter(outputFile+".tsv");
            fw.write(tempstr);
            if (type.equals("rsem")) {
                for (Iterator<DEGeneForm> it = countList.iterator(); it.hasNext();) {
                    DEGeneForm data = it.next();
                    fw.write(data.toString2() + "\n");
                }
            } else {
                for (Iterator<DEGeneForm> it = countList.iterator(); it.hasNext();) {
                    DEGeneForm data = it.next();
                    fw.write(data.toString() + "\n");
                }
            }
            fw.flush();
            fw.close();

            Workbook wb = new XSSFWorkbook();
            Sheet sheet1 = wb.createSheet("Differential analysis ");
//            Sheet sheet2 = wb.createSheet("gene expression_fpkm");

            CellStyle style = wb.createCellStyle();
            style.setFillForegroundColor(IndexedColors.ORANGE.getIndex());
            style.setFillPattern(CellStyle.SOLID_FOREGROUND);
            
            String[] titleArr = tempstr.split("\t");
            Row row1 = sheet1.createRow((short) 0);
            for (int i = 0; i < titleArr.length; i++) {
                String colTitle = titleArr[i];
                Cell cell1 = row1.createCell(i);
                cell1.setCellValue(colTitle);
                cell1.setCellStyle(style);
            }

            int rowIndex = 1;
            for (Iterator<DEGeneForm> it = countList.iterator(); it.hasNext();) {
                DEGeneForm degform = it.next();
                Row row = sheet1.createRow(rowIndex);
//                Cell cell = row.createCell((short) 0);
                row.createCell(0).setCellValue(degform.getGeneName());
                row.createCell(1).setCellValue(degform.getSample1Counts());
                row.createCell(2).setCellValue(degform.getSample2Counts());
                row.createCell(3).setCellValue(degform.getSample1FPKM());
                row.createCell(4).setCellValue(degform.getSample2FPKM());
                row.createCell(5).setCellValue(degform.getLog2FC());
                row.createCell(6).setCellValue(degform.getpValue());
                row.createCell(7).setCellValue(degform.getFDR());
                if (type.equals("rsem")) {
                    row.createCell(8).setCellValue(degform.getGeneNameDetails());
                    row.createCell(9).setCellValue(degform.getGenetype());
                }
                rowIndex++;
            }

            FileOutputStream fileOut = new FileOutputStream(outputFile + ".xlsx");
            wb.write(fileOut);
            fileOut.close();
            System.out.println("Excel verson of output is generated and located in " + outputFile + ".xlsx");

        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    public static void main(String[] args) throws IOException {

        DEGeneAnalysis deg = new DEGeneAnalysis();
        deg.N = 1.2989;
        System.out.println(deg.getPvalue(1, 1));
        System.out.println(deg.getLnSum(3));
        System.out.println(Math.exp(deg.getLnSum(3)));


    }

}
