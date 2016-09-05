/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package speedseqvcf;

import java.io.BufferedReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author zhengyueyuan
 */
public class IndelClassify {

    HashMap<String, ArrayList<String>> IndelsMap = new HashMap();
    HashMap<String, Integer> IndelExtronicMap = new HashMap();
    private double het = 0D;
    private double hom = 0D;
    private double dbSNP138 = 0D;
    private double one = 0D;
    private double dbSNP138AndOne = 0D;
    private double novel = 0D;

    private void FileReader(String inputFile) {
        try {
            BufferedReader br = new BufferedReader(new java.io.FileReader(inputFile));
            while (br.ready()) {
                String data = br.readLine();
                if (!data.startsWith("Chr")) {
                    String[] dataArr = data.split("\t");
                    String ref = dataArr[2];
                    String alt = dataArr[3];
                    String af = dataArr[10];
                    String funcRefGene = dataArr[11];
                    String extronicFun = dataArr[13];
                    String oneAnnot = dataArr[16];
                    String dbsnp138Annot = dataArr[17];
                    if (af.equals("1")) {
                        hom = hom + 1;
                    } else {
                        het = het + 1;
                    }

                    
                    if (oneAnnot.equals(".") && dbsnp138Annot.equals(".")) {
                        novel = novel + 1;
                    } else if (oneAnnot.equals(".")) {
                        dbSNP138 = dbSNP138 + 1;
                    } else if (dbsnp138Annot.equals(".")) {
                        one = one + 1;
                    } else {
                        dbSNP138AndOne = dbSNP138AndOne + 1;
                    }

                    if (!IndelExtronicMap.containsKey(extronicFun)) {
                        IndelExtronicMap.put(extronicFun, 1);
                    } else {
                        int temp = IndelExtronicMap.get(extronicFun);
                        IndelExtronicMap.put(extronicFun, temp + 1);
                    }

                    if (!IndelsMap.containsKey(funcRefGene)) {
                        ArrayList<String> dataList = new ArrayList();
                        dataList.add(data);
                        IndelsMap.put(funcRefGene, dataList);
                    } else {
                        IndelsMap.get(funcRefGene).add(data);
                    }
                }
            }
            br.close();
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    private void FileWrite(String outFile) {
        try {
            FileWriter fw = new FileWriter(outFile);
            fw.write("FunGene" + "\t" + "Number" + "\n");
            fw.write("Hom" + "\t" + hom + "\n");
            fw.write("Het" + "\t" + het + "\n");
            fw.write("Novel" + "\t" + novel + "\n");
            fw.write("1000_Genome & dbSNP138" + "\t" + dbSNP138AndOne + "\n");
            fw.write("1000_Genome specific" + "\t" + one + "\n");
            fw.write("dbSNP138 specific" + "\t" + dbSNP138 + "\n");
            for (String key : IndelExtronicMap.keySet()) {
                fw.write(key + "\t" + IndelExtronicMap.get(key) + "\n");
            }
            for (String key : IndelsMap.keySet()) {
                System.out.println(key);
                fw.write(key + "\t" + IndelsMap.get(key).size() + "\n");
                FileWriter fw1 = new FileWriter(outFile.split("\\.")[0] + "_" + key + ".txt");
                fw1.write("Chr" + "\t" + "POS" + "\t" + "REF" + "\t" + "ALT" + "\t" + "Type" + "\t" + "QUAL" + "\t" + "FILTER" + "\t" + "DEEPTH" + "\t" + "REF COUNT" + "\t" + "ALT COUNT" + "\t" + "Allele Frequency" + "\t" + "Func.refGene" + "\t" + "Gene.refGene" + "\t" + "ExonicFunc.refGene" + "\t" + "AAChange.refGene" + "\t" + "cytoBand" + "\t" + "1000g2014oct_all" + "\t" + "snp138" + "\n");
                for (String data : IndelsMap.get(key)) {
                    fw1.write(data + "\n");
                }
                fw1.flush();
                fw1.close();
            }
            fw.flush();
            fw.close();
        } catch (IOException ex) {
            Logger.getLogger(SNPClassify.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public boolean isTranvision(String ref, String alt) {
        String a = "AG";
        if (a.contains(ref) && a.contains(alt)) {
            return true;
        } else if (!a.contains(ref) && !a.contains(alt)) {
            return true;
        } else {
            return false;
        }
    }

    public static void main(String[] args) {
        IndelClassify indelC = new IndelClassify();
        indelC.FileReader("E:\\同步盘\\数据分析\\kidney\\相同的差异位点注释\\Annot_shared_indels.txt");
        indelC.FileWrite("E:\\同步盘\\数据分析\\kidney\\相同的差异位点注释\\Annot_shared_indels_statics.txt");
    }
}
