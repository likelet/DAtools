package speedseqvcf;

import java.io.BufferedReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.HashSet;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author zhengyueyuan
 */
public class ShareSNP {

    private double file1Numer = 0D;
    private double file2Numer = 0D;
    private double sharedNumber = 0D;

    private void FileReader(String inputFile1, String inputFile2, String shareFile) {
        HashSet<String> snpPosSet = new HashSet<String>();
        try {
            BufferedReader br = new BufferedReader(new java.io.FileReader(inputFile1));
            BufferedReader br2 = new BufferedReader(new java.io.FileReader(inputFile2));
            FileWriter fw = new FileWriter(shareFile);
            fw.write("Chr	POS	REF	ALT	Type	QUAL	FILTER	DEEPTH	REF COUNT	ALT COUNT	Allele Frequency	Func.refGene	Gene.refGene	ExonicFunc.refGene	AAChange.refGene	cytoBand	1000g2014oct_all	snp138"+"\t");
            while (br.ready()) {
                String data = br.readLine();
                if (!data.startsWith("Chr")) {
                    String[] dataArr = data.split("\t");
                    String snpPos = dataArr[0] + "\t" + dataArr[1] + "\t" + dataArr[2] + "\t" + dataArr[3];
                    snpPosSet.add(snpPos);
                }
            }
            br.close();
            while (br2.ready()) {
                String data2 = br2.readLine();
                if (!data2.startsWith("Chr")) {
                    String[] dataArr = data2.split("\t");
                    String snpPos = dataArr[0] + "\t" + dataArr[1] + "\t" + dataArr[2] + "\t" + dataArr[3];
                    if (snpPosSet.contains(snpPos)) {
                        fw.write(data2 + "\n");
                        sharedNumber = sharedNumber + 1;
                    } else {
                        file2Numer = file2Numer + 1;
                    }
                }
            }
            file1Numer = snpPosSet.size() - sharedNumber;
            System.out.println("Share Number:"+"\t"+sharedNumber);
            System.out.println("File1 Specific:"+"\t"+file1Numer);
            System.out.println("File2 Specific:"+"\t"+file2Numer);
            br2.close();
            fw.flush();
            fw.close();
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }
    
    public static void main(String[] args) {
         ShareSNP shareS= new ShareSNP();
         shareS.FileReader("E:\\同步盘\\数据分析\\kidney\\Annotation\\left_DHG02692-1_annot_oters.txt", "E:\\同步盘\\数据分析\\kidney\\Annotation\\right_DHG02693_annot_oters.txt", "E:\\同步盘\\数据分析\\kidney\\Annotation\\Annot_shared_others.txt");
    }
}
