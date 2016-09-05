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
public class VCFFileReader {

    public void FileReader(String vcfFile, String SNPFile, String IndelFile, String fiterFile) {
        try {
            BufferedReader br = new BufferedReader(new java.io.FileReader(vcfFile));
            FileWriter fw1 = new FileWriter(SNPFile);
            FileWriter fw2 = new FileWriter(IndelFile);
            FileWriter fw3 = new FileWriter(fiterFile);
            String af = "", type = "", funcRefGene = "", geneRefGene = "",  exonicFunc = "", aaChange = "", cyToBand = "", one = "", ro = "", ao = "", filter = "", dbsnp138 = "";
            fw1.write("Chr"+"\t"+"POS"+"\t"+"REF"+"\t"+"ALT"+"\t"+"Type"+"\t"+"QUAL"+"\t"+"FILTER"+"\t"+"DEEPTH"+"\t"+"REF COUNT"+"\t"+"ALT COUNT"+"\t"+"Allele Frequency"+"\t"+"Func.refGene"+"\t"+"Gene.refGene"+"\t"+"ExonicFunc.refGene"+"\t"+"AAChange.refGene"+"\t"+"cytoBand"+"\t"+"1000g2014oct_all"+"\t"+"snp138"+"\n");
            fw2.write("Chr"+"\t"+"POS"+"\t"+"REF"+"\t"+"ALT"+"\t"+"Type"+"\t"+"QUAL"+"\t"+"FILTER"+"\t"+"DEEPTH"+"\t"+"REF COUNT"+"\t"+"ALT COUNT"+"\t"+"Allele Frequency"+"\t"+"Func.refGene"+"\t"+"Gene.refGene"+"\t"+"ExonicFunc.refGene"+"\t"+"AAChange.refGene"+"\t"+"cytoBand"+"\t"+"1000g2014oct_all"+"\t"+"snp138"+"\n");
            fw3.write("Chr"+"\t"+"POS"+"\t"+"REF"+"\t"+"ALT"+"\t"+"Type"+"\t"+"QUAL"+"\t"+"FILTER"+"\t"+"DEEPTH"+"\t"+"REF COUNT"+"\t"+"ALT COUNT"+"\t"+"Allele Frequency"+"\t"+"Func.refGene"+"\t"+"Gene.refGene"+"\t"+"ExonicFunc.refGene"+"\t"+"AAChange.refGene"+"\t"+"cytoBand"+"\t"+"1000g2014oct_all"+"\t"+"snp138"+"\n");
            int dp = 0;
            double qual = 0.0;
            while (br.ready()) {
                String data = br.readLine();
                if (!data.startsWith("#")) {
                    String[] dataArr = data.split("\t");
                    qual = Double.parseDouble(dataArr[5]);
                    String[] infoArr = dataArr[7].split(";");
                    for (int i = 0; i < infoArr.length; i++) {
                        if (infoArr[i].startsWith("AF=")) {
                            af = infoArr[i].substring(infoArr[i].indexOf("=") + 1);
                        } else if (infoArr[i].startsWith("TYPE=")) {
                            type = infoArr[i].substring(infoArr[i].indexOf("=") + 1);
                        } else if (infoArr[i].startsWith("Func.refGene=")) {
                            funcRefGene = infoArr[i].substring(infoArr[i].indexOf("=") + 1);
                        } else if (infoArr[i].startsWith("Gene.refGene=")) {
                            geneRefGene = infoArr[i].substring(infoArr[i].indexOf("=") + 1);
                        } else if (infoArr[i].startsWith("ExonicFunc.refGene")) {
                            exonicFunc = infoArr[i].substring(infoArr[i].indexOf("=") + 1);
                        } else if (infoArr[i].startsWith("AAChange.refGene")) {
                            aaChange = infoArr[i].substring(infoArr[i].indexOf("=") + 1);
                        } else if (infoArr[i].startsWith("cytoBand")) {
                            cyToBand = infoArr[i].substring(infoArr[i].indexOf("=") + 1);
                        } else if (infoArr[i].startsWith("1000g2014oct_all")) {
                            one = infoArr[i].substring(infoArr[i].indexOf("=") + 1);
                        } else if (infoArr[i].startsWith("snp138=")) {
                            dbsnp138 = infoArr[i].substring(infoArr[i].indexOf("=") + 1);
                        }
                    }
                    String[] formatArr = dataArr[9].split(":");
                    dp = Integer.parseInt(formatArr[1]);
                    ro = formatArr[2];
                    ao = formatArr[4];
                    if (dp >= 5 && qual >= 30) {
                        filter = "PASS";
                    } else if (dp >= 5) {
                        filter = "LowQual";
                    } else if (qual >= 30) {
                        filter = "LowDeep";
                    } else {
                        filter = "LowDeep;LowQual";
                    }
                    if (type.equals("snp")) {
                        if (filter.equals("PASS")) {
                            fw1.write(dataArr[0] + "\t" + dataArr[1] + "\t" + dataArr[3] + "\t" + dataArr[4] + "\t" + type + "\t" + qual + "\t" + filter + "\t" + dp + "\t" + ro + "\t" + ao + "\t" + af + "\t" + funcRefGene + "\t" + geneRefGene + "\t" + exonicFunc + "\t" + aaChange + "\t" + cyToBand + "\t" + one + "\t" + dbsnp138 + "\n");
                        } else {
                            fw3.write(dataArr[0] + "\t" + dataArr[1] + "\t" + dataArr[3] + "\t" + dataArr[4] + "\t" + type + "\t" + qual + "\t" + filter + "\t" + dp + "\t" + ro + "\t" + ao + "\t" + af + "\t" + funcRefGene + "\t" + geneRefGene + "\t" + exonicFunc + "\t" + aaChange + "\t" + cyToBand + "\t" + one + "\t" + dbsnp138 + "\n");
                        }
                    } else {
                        if (filter.equals("PASS")) {
                            fw2.write(dataArr[0] + "\t" + dataArr[1] + "\t" + dataArr[3] + "\t" + dataArr[4] + "\t" + type + "\t" + qual + "\t" + filter + "\t" + dp + "\t" + ro + "\t" + ao + "\t" + af + "\t" + funcRefGene + "\t" + geneRefGene + "\t" + exonicFunc + "\t" + aaChange + "\t" + cyToBand + "\t" + one + "\t" + dbsnp138 + "\n");
                        } else {
                            fw3.write(dataArr[0] + "\t" + dataArr[1] + "\t" + dataArr[3] + "\t" + dataArr[4] + "\t" + type + "\t" + qual + "\t" + filter + "\t" + dp + "\t" + ro + "\t" + ao + "\t" + af + "\t" + funcRefGene + "\t" + geneRefGene + "\t" + exonicFunc + "\t" + aaChange + "\t" + cyToBand + "\t" + one + "\t" + dbsnp138 + "\n");
                        }
                    }
                }
            }
            br.close();
            fw1.flush();
            fw1.close();
            fw2.flush();
            fw2.close();
            fw3.flush();
            fw3.close();
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

//    public static void main(String[] args) {
//        VCFFileReader vcf=new VCFFileReader();
//        vcf.FileReader("E:\\同步盘\\数据分析\\kidney\\test.txt", "E:\\同步盘\\数据分析\\kidney\\test_1.txt", "E:\\同步盘\\数据分析\\kidney\\test_2.txt", "E:\\同步盘\\数据分析\\kidney\\test_3.txt");
//    }

}
