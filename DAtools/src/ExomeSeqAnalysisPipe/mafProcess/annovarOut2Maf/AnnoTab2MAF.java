/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ExomeSeqAnalysisPipe.mafProcess.annovarOut2Maf;

import ExomeSeqAnalysisPipe.mafProcess.vepMAF2TCGAmaf.tcga2_4Maf;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * <p>
 * AnnoTab2MAF</p>
 * <p>
 * Created on 2015-12-23 9:47:09</p>
 * <p>
 * Author Email: zhaoqi3@mail2.sysu.edu.cn</p>
 *
 * @author ZHAO Qi
 * @date 2015-12-23 9:47:09
 * @version java 1.6.0
 * @version
 */
public class AnnoTab2MAF {

    private String input;
    private String output;
    private String tumorname;
    private String normalname;
    private boolean isFilter = false;

    public AnnoTab2MAF(String input, String output, String tumorname, String normalname) {
        this.input = input;
        this.output = output;
        this.tumorname = tumorname;
        this.normalname = normalname;
        this.process();
    }

    public tcga2_4Maf covert(AnnovarTab at, String tumorsample, String normalsample) {
        String[] arrar = at.getAAChange_refGene().split(",");
        tcga2_4Maf mt = new tcga2_4Maf();
        mt.setHugo_Symbol(at.getGene_refGene());
//        System.out.println(arrar.length);
        if ((!at.getAAChange_refGene().equals(".")) && arrar.length >= 1) {
//            System.out.println(at.getAAChange_refGene());
            if (at.getFunc_refGene().equals("exonic")) {
//                System.out.println(arrar[0].split("\\:")[4]);
                String[] stra = arrar[0].split("\\:");
                for (int i = 0; i < stra.length; i++) {
                    if (stra[i].startsWith("p")) {
                        mt.setAAChange(stra[i]);
                    } else if (stra[i].startsWith("c")) {
                        mt.setChromChange(stra[i]);
                    }

                }

//                System.out.println(mt.getAAChange());
            } else {
//                System.out.println(at.getAAChange_refGene());
//                System.out.println(arrar[0]);
                mt.setChromChange(arrar[0].split("\\:")[1]);
            }
        }
        mt.setVariant_Classification(at.getFunc_refGene());
        mt.setVariant_Type(at.getExonicFunc_refGene());
        mt.setMutation_Status("Somatic");
        mt.setCenter("SYSUCC");
        mt.setDbSNP_RS(at.getSnp138());
        mt.setStart_position(at.getStart());
        mt.setEnd_position(at.getEnd());
        mt.setChromosome(at.getChr());
        mt.setReference_Allele(at.getRef());
        mt.setTumor_Seq_Allele1(at.getAlt());
        mt.setTumor_Sample_UUID(tumorsample);
        mt.setTumor_Sample_Barcode(tumorsample);
        mt.setMatched_Norm_Sample_Barcode(normalsample);
        mt.setMatched_Norm_Sample_UUID(normalsample);
        return mt;
    }

    public void process() {
        ArrayList<AnnovarTab> atlist = Parse_AnnovarTab.getAnnovarTablist(input);
        try {
            FileWriter fw = new FileWriter(output);
//            System.out.println(atlist.size());
            for (int i = 0; i < atlist.size(); i++) {
                tcga2_4Maf tm = covert(atlist.get(i), tumorname, normalname);
//                System.out.println(tm.toString());
                if (isFilter && tm.getVariant_Classification().equals("exonic")) {
                    fw.append(tm.toString() + "\r\n");
                } else {
                    fw.append(tm.toString() + "\r\n");
                }

            }
            fw.flush();
            fw.close();
        } catch (IOException ex) {
            Logger.getLogger(AnnoTab2MAF.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public static void main(String[] args) {
        new AnnoTab2MAF("F:\\百度云同步盘\\resouces\\projects\\菊花强\\annotation\\ID503_1348_T_multianno.txt", "F:\\百度云同步盘\\resouces\\projects\\菊花强\\annotation\\ID503_1348_T_multianno.maf", "test", "test2");
    }

}
