/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package ExomeSeqAnalysisPipe.mafProcess.GVC2TCGAmaf;

import ExomeSeqAnalysisPipe.mafProcess.MAF_maftools;
import ExomeSeqAnalysisPipe.mafProcess.vepMAF2TCGAmaf.tcga2_4Maf;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;

/**
 *
 * @author Administrator
 * @since 2017-11-17
 * @coding time 19:17:01
 * @author Qi Zhao
 */
public class GVCsimp2tcgaMaf_main {

    public void GVCsimp2tcgaMaf_main(String simpfile, String outfile) throws FileNotFoundException, IOException {
        BufferedReader br = new BufferedReader(new java.io.FileReader(new File(simpfile)));
        FileWriter fw = new FileWriter(outfile);

        //skip header 
        br.readLine();
         fw.append("Hugo_Symbol	Entrez_Gene_Id	Center	NCBI_Build	Chromosome	Start_Position	End_position	Strand	Variant_Classification	Variant_Type	Reference_Allele	Tumor_Seq_Allele1	Tumor_Seq_Allele2	Tumor_Sample_Barcode	Protein_Change	i_TumorVAF	i_transcript_name\n");
        while (br.ready()) {
            String dataStr = br.readLine().trim();
            GVCsimp gv = GVCsimp_2_tcga4maf.parsingGVCsimp(dataStr);
            MAF_maftools tm = GVCsimp_2_tcga4maf.GVCsimp_2_tcga4maf_forMaftools_covert(gv);
            fw.append(tm.toString()+"\n");
            fw.flush();
        }

        fw.close();
    }
    
    public static void main(String[] args) throws IOException {
        new GVCsimp2tcgaMaf_main().GVCsimp2tcgaMaf_main("E:\\ExtraProject\\java\\DAtools\\DAtools\\NT_BDA_1_1.Somatic.WES.snv.simp", "E:\\ExtraProject\\java\\DAtools\\DAtools\\test.txt");
    }
}
