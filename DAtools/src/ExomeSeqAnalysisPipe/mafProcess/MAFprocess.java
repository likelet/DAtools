/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ExomeSeqAnalysisPipe.mafProcess;

import ExomeSeqAnalysisPipe.*;
import ExomeSeqAnalysisPipe.CNVanalysis.CNVnator.RunCNVnatorFinal;
import ExomeSeqAnalysisPipe.CNVanalysis.CONTRAanalysis.GscoreStatistic.CNVMatrixIterm;
import ExomeSeqAnalysisPipe.CNVanalysis.CONTRAanalysis.GscoreStatistic.GetStatic_fromContraOutput;
import ExomeSeqAnalysisPipe.CNVanalysis.CONTRAanalysis.GscoreStatistic.GetStatic_fromPermutationList;
import ExomeSeqAnalysisPipe.CNVanalysis.CONTRAanalysis.summaryContra.summaryContraResult;
import ExomeSeqAnalysisPipe.CNVanalysis.CONTRAanalysis.summaryContra.summaryContraResultWithChromeBind;
import ExomeSeqAnalysisPipe.CoverageGenerate.CoverageForExomeSeq;
import ExomeSeqAnalysisPipe.GATKresultParse.FormatTransfer;
import ExomeSeqAnalysisPipe.GISTIC2.GATK_CNV_2_GISTIC;
import java.io.IOException;
import ExomeSeqAnalysisPipe.RunCrest.RunCrestExtractSclipFinal;
import ExomeSeqAnalysisPipe.RunCrest.RunCrestSVfinal;
import ExomeSeqAnalysisPipe.SNPfrequencyInSamples.AnnotedSnpFrequencyInsamples;
import ExomeSeqAnalysisPipe.SNPfrequencyInSamples.SNPfrequencyInSamples;
import ExomeSeqAnalysisPipe.Var2scan2ICGCformat.SomaticeProcessing;
import ExomeSeqAnalysisPipe.VirusAnalysis.FilterBam_good_unmapped_mate;
import ExomeSeqAnalysisPipe.VirusAnalysis.GetReadsMappedTwoDifferentChr;
import ExomeSeqAnalysisPipe.mafProcess.HaploxVcf2Maftools.haploxVcf2MAF;
//import ExomeSeqAnalysisPipe.mafProcess.GVC2TCGAmaf.GVCsimp2tcgaMaf_main;
import ExomeSeqAnalysisPipe.mafProcess.annovarOut2Maf.AnnoTab2MAF;
import ExomeSeqAnalysisPipe.mafProcess.vepMAF2TCGAmaf.VepMAF2TCGAmafMain;
import java.util.ArrayList;
import pub.FunctionClass;
import pub.ToolsforCMD;

/**
 *
 * @author ZHAO Qi
 * @date 2014-3-7 20:24:42
 * @version 1.6.0
 */
public class MAFprocess {

    public MAFprocess(String[] args) throws IOException {
        if (args.length == 1) {
            System.out.println("Convert haplox vcf to maf file  \r\n\t\t" + ToolsforCMD.print_ansi_GREEN( "java -jar DAtools.jar -maf -mode h2maf" )
                    + ToolsforCMD.print_ansi_CYAN( " <vcf> <maf> " ) + "\r\n");
           

        } else if (FunctionClass.getArgsParameter(args, "-mode").equalsIgnoreCase("h2maf")) {
             new haploxVcf2MAF(args[3]).process();
        }else {
            System.out.println("Please specified -mode parameters!");
        }
    }
}
