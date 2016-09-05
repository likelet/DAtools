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
public class SNPClassify {

    HashMap<String, ArrayList<String>> snpMap = new HashMap();
    HashMap<String,Integer> extronicMap= new HashMap();
    private double het = 0D;
    private double hom = 0D;
    private double dbSNP138 = 0D;
    private double one = 0D;
    private double dbSNP138AndOne = 0D;
    private double novel = 0D;
    private double ti = 0D;
    private double tv = 0D;

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
                    String extronicFun= dataArr[13];
                    String oneAnnot = dataArr[16];
                    String dbsnp138Annot = dataArr[17];
                    if (af.equals("1")) {
                        hom = hom + 1;
                    } else {
                        het = het + 1;
                    }
                    
                    if (isTranvision(ref, alt)) {
                        ti = ti + 1;
                    } else {
                        tv = tv + 1;
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
                    
                    if(!extronicMap.containsKey(extronicFun)){
                        extronicMap.put(extronicFun, 1);
                    }else{
                        int temp = extronicMap.get(extronicFun);
                        extronicMap.put(extronicFun,temp+1);
                    }

                    if (!snpMap.containsKey(funcRefGene)) {
                        ArrayList<String> dataList = new ArrayList();
                        dataList.add(data);
                        snpMap.put(funcRefGene, dataList);
                    } else {
                        snpMap.get(funcRefGene).add(data);
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
            fw.write("Ti" + "\t" + ti + "\n");
            fw.write("Tv" + "\t" + tv + "\n");
            fw.write("Novel" + "\t" + novel + "\n");
            fw.write("1000_Genome & dbSNP138" + "\t" + dbSNP138AndOne + "\n");
            fw.write("1000_Genome specific" + "\t" + one + "\n");
            fw.write("dbSNP138 specific" + "\t" + dbSNP138 + "\n");
            for(String key:extronicMap.keySet()){
                fw.write(key+"\t"+extronicMap.get(key)+"\n");
            }            
            for (String key : snpMap.keySet()) {
                System.out.println(key);
                fw.write(key + "\t" + snpMap.get(key).size() + "\n");
                FileWriter fw1 = new FileWriter(outFile.split("\\.")[0] + "_" + key + ".txt");
                fw1.write("Chr" + "\t" + "POS" + "\t" + "REF" + "\t" + "ALT" + "\t" + "Type" + "\t" + "QUAL" + "\t" + "FILTER" + "\t" + "DEEPTH" + "\t" + "REF COUNT" + "\t" + "ALT COUNT" + "\t" + "Allele Frequency" + "\t" + "Func.refGene" + "\t" + "Gene.refGene" + "\t" + "ExonicFunc.refGene" + "\t" + "AAChange.refGene" + "\t" + "cytoBand" + "\t" + "1000g2014oct_all" + "\t" + "snp138" + "\n");
                for (String data : snpMap.get(key)) {
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
        SNPClassify snpC = new SNPClassify();
        snpC.FileReader("E:\\同步盘\\数据分析\\kidney\\相同的差异位点注释\\Annot_shared_snp.txt");
        snpC.FileWrite("E:\\同步盘\\数据分析\\kidney\\相同的差异位点注释\\Annot_shared_snp_statics.txt");
    }
}
