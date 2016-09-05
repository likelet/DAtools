/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ExomeSeqAnalysisPipe.mafProcess.vepMAF2TCGAmaf;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import pub.txtReader;

/**
 * <p>
 * VepMAF2TCGAmafMain</p>
 * <p>
 * Created on 2015-11-18 11:30:08</p>
 * <p>
 * Author Email: zhaoqi3@mail2.sysu.edu.cn</p>
 *
 * @author ZHAO Qi
 * @date 2015-11-18 11:30:08
 * @version java 1.6.0
 * @version
 */
public class VepMAF2TCGAmafMain {

    public void convertFile(String maffile, String targetFile) {

        BufferedReader br = null;
        try {
            FileWriter fw = new FileWriter(targetFile);

            br = new BufferedReader(new FileReader(new File(maffile)));
            String[] str;
            while (br.ready()) {
                String temstr = br.readLine();
                temstr = temstr.replace("\t\t", "\t.\t");
                temstr = replaceLast(temstr, "\t", "\t.");
//                temstr=temstr.replace("\t.\n", "\t\n");
                if (temstr.startsWith("#") || temstr.startsWith("Hugo_Symbol")) {
                } else {
                    str = temstr.split("\t");
//                System.out.println(str.length);
                    MAFby_Tool mt = new MAFby_Tool(str[0], str[1], str[2], str[3], str[4], str[5], str[6], str[7], str[8], str[9], str[10], str[11], str[12], str[13], str[14], str[15], str[16], str[17], str[18], str[19], str[20], str[21], str[22], str[23], str[24], str[25], str[26], str[27], str[28], str[29], str[30], str[31], str[32], str[33], str[34], str[35], str[36], str[37], str[38], str[39], str[40], str[41], str[42], str[43], str[44], str[45], str[46], str[47], str[48], str[49], str[50], str[51], str[52], str[53], str[54], str[55], str[56], str[57], str[58], str[59], str[60], str[61], str[62], str[63], str[64], str[65], str[66], str[67], str[68], str[69], str[70], str[71], str[72], str[73], str[74], str[75], str[76], str[77], str[78], str[79], str[80], str[81], str[82], str[83], str[84], str[85], str[86], str[87], str[88], str[89], str[90], str[91], str[92], str[93], str[94], str[95], str[96], str[97], str[98], str[99], str[100], str[101], str[102], str[103], str[104], str[105], str[106], str[107]);
                    tcga2_4Maf tm = convert.convert(mt);
                    fw.append(tm.toString() + "\r\n");
                    fw.flush();
                }
            }
            br.close();

            fw.close();
        } catch (FileNotFoundException ex) {
            System.out.println(this + " is not found! please check your filepath ");
        } catch (IOException ex) {
            System.out.println("IO error");
        }
    }
//filtered function

    public void convertFile(String maffile, String filterIDlist, String targetFile) throws IOException {

        ArrayList<String> IDlist = txtReader.readID(filterIDlist);

        BufferedReader br = null;
        try {
            FileWriter fw = new FileWriter(targetFile);
            fw.append("Hugo_Symbol	Entrez_Gene_Id	Center	NCBI_Build	Chromosome	Start_position	End_position	Strand	Variant_Classification	Variant_Type	Reference_Allele	Tumor_Seq_Allele1	Tumor_Seq_Allele2	dbSNP_RS	dbSNP_Val_Status	Tumor_Sample_Barcode	Matched_Norm_Sample_Barcode	Match_Norm_Seq_Allele1	Match_Norm_Seq_Allele2	Tumor_Validation_Allele1	Tumor_Validation_Allele2	Match_Norm_Validation_Allele1	Match_Norm_Validation_Allele2	Verification_Status	Validation_Status	Mutation_Status	Sequencing_Phase	Sequence_Source	Validation_Method	Score	BAM_File	Sequencer	Tumor_Sample_UUID	Matched_Norm_Sample_UUID	COSMIC_Codon	COSMIC_Gene	Transcript_Id	Exon	ChromChange	AAChange	Genome_Plus_Minus_10_Bp	Drug_Target	TTotCov	TVarCov	NTotCov	NVarCov	dbSNPPopFreq");
            br = new BufferedReader(new FileReader(new File(maffile)));
            String[] str;
            while (br.ready()) {
                String temstr = br.readLine();
                temstr = temstr.replace("\t\t", "\t.\t");
                temstr = replaceLast(temstr, "\t", "\t.");
//                temstr=temstr.replace("\t.\n", "\t\n");
                if (temstr.startsWith("#") || temstr.startsWith("Hugo_Symbol")) {
                } else {
                    str = temstr.split("\t");
//                System.out.println(str.length);
                    MAFby_Tool mt = new MAFby_Tool(str[0], str[1], str[2], str[3], str[4], str[5], str[6], str[7], str[8], str[9], str[10], str[11], str[12], str[13], str[14], str[15], str[16], str[17], str[18], str[19], str[20], str[21], str[22], str[23], str[24], str[25], str[26], str[27], str[28], str[29], str[30], str[31], str[32], str[33], str[34], str[35], str[36], str[37], str[38], str[39], str[40], str[41], str[42], str[43], str[44], str[45], str[46], str[47], str[48], str[49], str[50], str[51], str[52], str[53], str[54], str[55], str[56], str[57], str[58], str[59], str[60], str[61], str[62], str[63], str[64], str[65], str[66], str[67], str[68], str[69], str[70], str[71], str[72], str[73], str[74], str[75], str[76], str[77], str[78], str[79], str[80], str[81], str[82], str[83], str[84], str[85], str[86], str[87], str[88], str[89], str[90], str[91], str[92], str[93], str[94], str[95], str[96], str[97], str[98], str[99], str[100], str[101], str[102], str[103], str[104], str[105], str[106], str[107]);
                    tcga2_4Maf tm = convert.convert(mt);
                    if (IDlist.contains(mt.getHugo_Symbol())) {
                        fw.append(tm.toString() + "\r\n");

                        fw.flush();
                    }
                }
            }
            br.close();

            fw.close();
        } catch (FileNotFoundException ex) {
            System.out.println(this + " is not found! please check your filepath ");
        } catch (IOException ex) {
            System.out.println("IO error");
        }
    }

    public String replaceLast(String text, String regex, String replacement) {
        return text.replaceFirst("(?s)" + regex + "(?!.*?" + regex + ")", replacement);
    }

    public static void main(String[] args) throws IOException {
        new VepMAF2TCGAmafMain().convertFile("F:\\百度云同步盘\\resouces\\projects\\菊花强\\mutect\\mutectM_T\\metasisM_vs_T.MAF", "F:\\百度云同步盘\\resouces\\projects\\菊花强\\mutect\\mutectM_T\\\\list.txt", "F:\\百度云同步盘\\resouces\\projects\\菊花强\\mutect\\mutectM_T\\metasisM_vs_T_TCGA.MAF");
    }
}
