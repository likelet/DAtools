package RNAseqPipeline;

import Algorithm.MultipleCorrection.FDR;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
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
    private ArrayList<DEGeneForm> countList;
    private HashMap<String, Integer> gene2length;
    private String filename1 = "Sample1";
    private String filename2 = "Sample2";

    public DEGeneAnalysis() {
    }

    public DEGeneAnalysis(String countFile1, String countFile2, String outputFile) throws IOException {
        this.getDEgenePvalue(countFile1, countFile2);
        this.getOutputFile(outputFile);
    }

    public void getDEgenePvalue(String countFile1, String countFile2) throws FileNotFoundException, IOException {
        countList = countList(countFile1, countFile2);

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
//        double tempP = 0;
//       
//        //calculate Pvalue  using R
//        
//        try {
//            File pvalueFile = new File("pvalue.txt");
//            pvalueFile.deleteOnExit();
//            FileWriter fw = new FileWriter(pvalueFile);
//            if (gene2length != null) {
//                for (Iterator<DEGeneForm> it = countList.iterator(); it.hasNext();) {
//                    DEGeneForm countData = it.next();
//                    int sample1Count = countData.getSample1Counts();
//                    int sample2Count = countData.getSample2Counts();
//                    tempP = getPvalue(sample1Count, sample2Count);
//                    countData.setpValue(tempP);
//                    if (gene2length != null) {
//                        this.calculateFPKM_FC(countData);
//                    }
//                    fw.append(tempP + "\n");
//                }
//            } else {
//                for (Iterator<DEGeneForm> it = countList.iterator(); it.hasNext();) {
//                    DEGeneForm countData = it.next();
//                    int sample1Count = countData.getSample1Counts();
//                    int sample2Count = countData.getSample2Counts();
//                    tempP = getPvalue(sample1Count, sample2Count);
//                    countData.setpValue(tempP);
//                    if (gene2length != null) {
//                        this.calculateFPKM_FC(countData);
//                    }
//                    fw.append(tempP + "\n");
//                }
//            }
//
//            fw.flush();
//            fw.close();
//        } catch (IOException ex) {
//            System.out.println("Pvalue.txt write error!");
//        }
//            String fdrstr = calculateFdrByR("pvalue.txt");
//        File fdrfile = new File(fdrstr);
//        fdrfile.deleteOnExit();
//        BufferedReader br = new BufferedReader(new FileReader(fdrfile));
//        br.readLine();
//        ArrayList<Double> fdrlist = new ArrayList<Double>();
//        while (br.ready()) {
//            Double a = Double.parseDouble(br.readLine());
//            fdrlist.add(a);
//        }
//        br.close();
        
        
        
        double tempP = 0;
        ArrayList<Double> plist=new ArrayList<Double>();
        if (gene2length != null) {
                for (Iterator<DEGeneForm> it = countList.iterator(); it.hasNext();) {
                    DEGeneForm countData = it.next();
                    int sample1Count = countData.getSample1Counts();
                    int sample2Count = countData.getSample2Counts();
                    tempP = getPvalue(sample1Count, sample2Count);
                    countData.setpValue(tempP);
                    if (gene2length != null) {
                        this.calculateFPKM_FC(countData);
                    }
                    plist.add(tempP);
                }
            } else {
                for (Iterator<DEGeneForm> it = countList.iterator(); it.hasNext();) {
                    DEGeneForm countData = it.next();
                    int sample1Count = countData.getSample1Counts();
                    int sample2Count = countData.getSample2Counts();
                    tempP = getPvalue(sample1Count, sample2Count);
                    countData.setpValue(tempP);
                    if (gene2length != null) {
                        this.calculateFPKM_FC(countData);
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

    //runR to calculate FDR; return fdrlistFile
    public String calculateFdrByR(String Pvaluefile) throws IOException {
        File pvaluefile = new File(Pvaluefile);
        pvaluefile.deleteOnExit();
        File Rscriptpathfile = new File("calFDR.R");
        Rscriptpathfile.deleteOnExit();
        FileWriter fw = new FileWriter(Rscriptpathfile);

        String outputpath = "fdr.txt";

//        tempfile.deleteOnExit();
        String str = "";

        //System.out.println(dirOut);
//        str = "setwd(\"" + Rscriptdir + "\")\n";
        str += "dataset<-read.table(\"" + Pvaluefile + "\",header=F)\n";
        str += "data<-dataset[,1]\n";
        str += "fdr<-p.adjust(data,method=\"fdr\")\n";
        str += "write.table(fdr,\"" + outputpath + "\",row.names=F)\n";
        fw.append(str);
        fw.flush();
        fw.close();

        Rexe rexe = new Rexe(Rscriptpathfile.getAbsolutePath(), true);
        return outputpath;

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

    private ArrayList<DEGeneForm> countList(String countFile1, String countFile2) throws FileNotFoundException {

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
                countList.add(new DEGeneForm(geneName, sample1Count, sample2Count, 0, 0, 0));
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

    public void setGene2length(HashMap<String, Integer> gene2length) {
        this.gene2length = gene2length;
    }

    public void getOutputFile(String outputFile) {
        try {
            FileWriter fw = new FileWriter(outputFile);
            fw.write("Gene" + "\t" + "Sample1" + "\t" + "Sample2" + "\t" + "sample1_FPKM" + "\t" + "sample2_FPKM" + "\t" + "log2FC" + "\t" + "pValue" + "\t" + "FDR" + "\n");
            for (Iterator<DEGeneForm> it = countList.iterator(); it.hasNext();) {
                DEGeneForm data = it.next();
                fw.write(data.toString() + "\n");
            }
            fw.flush();
            fw.close();

            Workbook wb = new XSSFWorkbook();
            Sheet sheet1 = wb.createSheet("Differential analysis ");
//            Sheet sheet2 = wb.createSheet("gene expression_fpkm");

            CellStyle style = wb.createCellStyle();
            style.setFillForegroundColor(IndexedColors.ORANGE.getIndex());
            style.setFillPattern(CellStyle.SOLID_FOREGROUND);
            String tempstr = "Gene\t" + this.filename1 + "\t" + this.filename2 + "\t" + this.filename1 + "_FPKM\t" + this.filename2 + "_FPKM\tlog2FC\tpValue\tFDR";
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
        System.out.println(deg.getPvalue(22, 5));
        System.out.println(deg.getLnSum(3));
        System.out.println(Math.exp(deg.getLnSum(3)));
        deg.gene2length = GeneLengthFileReader.getgeneLengthMap("H:\\gene_length.txt");
////        new DEGeneAnalysis("E:\\同步盘\\数据分析\\赵老师\\calculation.txt", "E:\\同步盘\\数据分析\\赵老师\\calculation_FDR.txt");
        deg.getDEgenePvalue("F:\\resouces\\projects\\戴阳硕_肖士课题组\\水稻\\定量\\Lox2-BPH-12h.count", "F:\\resouces\\projects\\戴阳硕_肖士课题组\\水稻\\定量\\Lox2-CK-12h.count");
        deg.getOutputFile("out.txt");
//    File Rscriptpathfile = new File("calFDR.R");
//        FileWriter fw = new FileWriter(Rscriptpathfile);
//        String Rscriptdir = Rscriptpathfile.getParent();
//        System.out.println(Rscriptdir);
        //Rscriptdir = Rscriptdir.replace("\\", "/");
    }

}
