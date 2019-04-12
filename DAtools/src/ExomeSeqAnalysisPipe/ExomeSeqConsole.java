/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ExomeSeqAnalysisPipe;

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
public class ExomeSeqConsole {

    public ExomeSeqConsole(String[] args) throws IOException {
        if (args.length == 1) {
            System.out.println("Statistic snp frequency in allsamples \r\n\t\t" + ToolsforCMD.print_ansi_GREEN( "java -jar DAtools.jar -exome -mode snpfre" )
                    + ToolsforCMD.print_ansi_CYAN( " dir outfile " ) + "\r\n");
            System.out.println("Statistic snp frequency in allsamples \r\n\t\t" + ToolsforCMD.print_ansi_GREEN( "java -jar DAtools.jar -exome -mode snpEfffre" )
                    + ToolsforCMD.print_ansi_CYAN( " dir outfile [suffix] " ) + "\r\n");
            System.out.println("Convert varscan result to ICGC format \r\n\t\t" + ToolsforCMD.print_ansi_GREEN( "java -jar DAtools.jar -exome -mode varscanICGC" )
                    + ToolsforCMD.print_ansi_CYAN( " vsfinputfile ssmpoutfile analysisid sampleid " ) + "\r\n");
            System.out.println("Run Crest extrat SCLIP pl parallel(require samtools & crest) \r\n\t\t" + ToolsforCMD.print_ansi_GREEN( "java -jar DAtools.jar -exome -mode crestExtract" )
                    + ToolsforCMD.print_ansi_CYAN( " bamfile crestExtractSclipPath genome threadnum " ) + "\r\n");
            System.out.println("Run Crest extrat SCLIP pl parallel(require samtools & crest) \r\n\t\t" + ToolsforCMD.print_ansi_GREEN( "java -jar DAtools.jar -exome -mode crestSV" )
                    + ToolsforCMD.print_ansi_CYAN( " tumorbamfile [germlinebamfile] genomepath genomebit crestSVpath coverfile hostname threadnum " ) + "\r\n");
            System.out.println("Run CNVnatorExtract parallel(require samtools & cvnator) \r\n\t\t" + ToolsforCMD.print_ansi_GREEN( "java -jar DAtools.jar -exome -mode cnvnator" )
                    + ToolsforCMD.print_ansi_CYAN( " bamfile threadnum " ) + "\r\n");
            System.out.println("Run CNVnator (require cvnator) \r\n\t\t" + ToolsforCMD.print_ansi_GREEN( "java -jar DAtools.jar -exome -mode cnvnator2" )
                    + ToolsforCMD.print_ansi_CYAN( " bamfile  " ) + "\r\n");
            System.out.println("Process Annovar maf file\r\n\t\t" + ToolsforCMD.print_ansi_GREEN( "java -jar DAtools.jar -exome -mode annovarTab2maf" )
                    + ToolsforCMD.print_ansi_CYAN( " infile outfile tumorname normalname  " ) + "\r\n");
            System.out.println("Process GATKcombineVariant file to annovar DB\r\n\t\t" + ToolsforCMD.print_ansi_GREEN( "java -jar DAtools.jar -exome -mode gatk2annodb" )
                    + ToolsforCMD.print_ansi_CYAN( " input.vcf output.txt" ) + "\r\n");
            System.out.println("Process VEP maf file\r\n\t\t" + ToolsforCMD.print_ansi_GREEN( "java -jar DAtools.jar -exome -mode vep2tcgaMaf" )
                    + ToolsforCMD.print_ansi_CYAN( " infile outfile " ) + "\r\n");
            System.out.println("Process gvc 2 maf file\r\n\t\t" + ToolsforCMD.print_ansi_GREEN( "java -jar DAtools.jar -exome -mode gvc2tcgaMaf" )
                    + ToolsforCMD.print_ansi_CYAN( " infile outfile [sampleID] " ) + "\r\n");
            System.out.println("Processing Contra Output\r\n\t\t" + ToolsforCMD.print_ansi_GREEN( "java -jar DAtools.jar -exome -mode contraGscore" )
                    + ToolsforCMD.print_ansi_CYAN( " folderlist outfile  " ) + "\r\n");
            System.out.println("Processing Contra Output\r\n\t\t" + ToolsforCMD.print_ansi_GREEN( "java -jar DAtools.jar -exome -mode contraMatrix" )
                    + ToolsforCMD.print_ansi_CYAN( " originfilr outmatrix  " ) + "\r\n");
            System.out.println("Processing Contra Output\r\n\t\t" + ToolsforCMD.print_ansi_GREEN( "java -jar DAtools.jar -exome -mode contraWindowbin" )
                    + ToolsforCMD.print_ansi_CYAN( " pairedsampletable faifile outmatrix gscorefile  " ) + "\r\n");
            System.out.println("Summary the coverage of Exome-seq data \r\n\t\t" + ToolsforCMD.print_ansi_GREEN( "java -jar DAtools.jar -exome -mode cov" )
                    + ToolsforCMD.print_ansi_CYAN( " regionfile outfile " ) + "\r\n");
            System.out.println("get one mapped paired reads from bamfile \r\n\t\t" + ToolsforCMD.print_ansi_GREEN( "java -jar DAtools.jar -exome -mode virus1" )
                    + ToolsforCMD.print_ansi_CYAN( " inputbam outbam " ) + "\r\n");
             System.out.println("get bam reads mapped to differ chromose \r\n\t\t" + ToolsforCMD.print_ansi_GREEN( "java -jar DAtools.jar -exome -mode virus2" )
                    + ToolsforCMD.print_ansi_CYAN( " inputbam outbam " ) + "\r\n");
             System.out.println("Convert GATK CNV result to gistic input \r\n\t\t" + ToolsforCMD.print_ansi_GREEN( "java -jar DAtools.jar -exome -mode gatkcnv2gistic" )
                    + ToolsforCMD.print_ansi_CYAN( " <CR.SEG> [outfilename]" ) + "\r\n");

        } else if (FunctionClass.getArgsParameter(args, "-mode").equalsIgnoreCase("snpfre")) {
            new SNPfrequencyInSamples(args[3], args[4]);
        } else if (FunctionClass.getArgsParameter(args, "-mode").equalsIgnoreCase("snpEfffre")) {
            if (args.length > 5) {
                System.out.println(ToolsforCMD.startruningSTR());
                new AnnotedSnpFrequencyInsamples(args[3], args[4], args[5]);
            } else {
                System.out.println(ToolsforCMD.startruningSTR());
                new AnnotedSnpFrequencyInsamples(args[3], args[4]);
            }
        } else if (FunctionClass.getArgsParameter(args, "-mode").equalsIgnoreCase("varscanICGC")) {
            if (args.length == 7) {
                System.out.println(ToolsforCMD.startruningSTR());
                new SomaticeProcessing().processing(args[3], args[4], args[5], args[6]);
            } else {
                System.out.println("arguments error, please check your parameters");
            }
        } else if (FunctionClass.getArgsParameter(args, "-mode").equalsIgnoreCase("crestExtract")) {
            if (args.length == 7) {
                System.out.println(ToolsforCMD.startruningSTR());
                new RunCrestExtractSclipFinal(args[3], args[4], args[5], Integer.parseInt(args[6]));
            } else {
                System.out.println("arguments error, please check your parameters");
            }
        } else if (FunctionClass.getArgsParameter(args, "-mode").equalsIgnoreCase("crestSV")) {
            if (args.length == 10) {
                System.out.println(ToolsforCMD.startruningSTR());
                System.out.println(ToolsforCMD.startruningSTR());
                new RunCrestSVfinal(args[3], args[4], args[5], args[6], args[7], args[8], Integer.parseInt(args[9]));
            }
            if (args.length == 11) {
                System.out.println(ToolsforCMD.startruningSTR());
                new RunCrestSVfinal(args[3], args[4], args[5], args[6], args[7], args[8], args[9], Integer.parseInt(args[10]));
            } else {
                System.out.println("arguments error, please check your parameters");
            }
        } else if (FunctionClass.getArgsParameter(args, "-mode").equalsIgnoreCase("cnvnator")) {
            if (args.length == 5) {
                new RunCNVnatorFinal(args[3], Integer.parseInt(args[4]));
            } else {
                System.out.println("arguments error, please check your parameters");
            }
        } else if (FunctionClass.getArgsParameter(args, "-mode").equalsIgnoreCase("cnvnator2")) {
            if (args.length == 4) {
                new RunCNVnatorFinal(args[3]);
            } else {
                System.out.println("arguments error, please check your parameters");
            }
        } else if (FunctionClass.getArgsParameter(args, "-mode").equalsIgnoreCase("annovarTab2maf")) {
            if (args.length == 7) {
                System.out.println(ToolsforCMD.startruningSTR());
                new AnnoTab2MAF(args[3], args[4], args[5], args[6]);
            } else {
                System.out.println("arguments error, please check your parameters");
            }

        } else if (FunctionClass.getArgsParameter(args, "-mode").equalsIgnoreCase("vep2tcgaMaf")) {
            if (args.length == 5) {
                System.out.println(ToolsforCMD.startruningSTR());
                new VepMAF2TCGAmafMain().convertFile(args[3], args[4]);
            } else {
                System.out.println("arguments error, please check your parameters");
            }

        } else if (FunctionClass.getArgsParameter(args, "-mode").equalsIgnoreCase("gvc2tcgaMaf")) {
//            if (args.length == 5) {
//                new GVCsimp2tcgaMaf_main().GVCsimp2tcgaMaf_main(args[3], args[4]);
//            } if (args.length == 6) {
//                new GVCsimp2tcgaMaf_main().GVCsimp2tcgaMaf_main(args[3], args[4],args[5]);
//            } else {
//                System.out.println("arguments error, please check your parameters");
//            }

        }else if (FunctionClass.getArgsParameter(args, "-mode").equalsIgnoreCase("contraGscore")) {
            if (args.length == 6) {
                System.out.println(ToolsforCMD.startruningSTR());
                new GetStatic_fromContraOutput(args[3], args[4]);
            } else {
                System.out.println("arguments error, please check your parameters");
            }

        } else if (FunctionClass.getArgsParameter(args, "-mode").equalsIgnoreCase("contraMatrix")) {
            if (args.length == 5) {
                new summaryContraResult(args[3], args[4]);
            } else {
                System.out.println("arguments error, please check your parameters");
            }

        } else if (FunctionClass.getArgsParameter(args, "-mode").equalsIgnoreCase("contraWindowbin")) {
            if (args.length == 7) {
                ArrayList<CNVMatrixIterm> oringinlist = new summaryContraResultWithChromeBind(args[3], args[4], args[5]).getOringinlist();
               System.out.println(ToolsforCMD.startruningSTR());
                new GetStatic_fromPermutationList(oringinlist, args[6]);
            } else {
                System.out.println("arguments error, please check your parameters");
            }

        } else if (FunctionClass.getArgsParameter(args, "-mode").equalsIgnoreCase("gatk2annodb")) {
            if (args.length == 5) {
                System.out.println(ToolsforCMD.startruningSTR());
                FormatTransfer.GATKcombineVariant_To_AnnovarDB(args[3], args[4]);
            } else {
                System.out.println("arguments error, please check your parameters");
            }
        }else if (FunctionClass.getArgsParameter(args, "-mode").equalsIgnoreCase("cov")) {
            if (args.length == 5) {
                System.out.println(ToolsforCMD.startruningSTR());
                new CoverageForExomeSeq(args[3], args[4]);
            } else {
                System.out.println("arguments error, please check your parameters");
            }

        } else if (FunctionClass.getArgsParameter(args, "-mode").equalsIgnoreCase("virus1")) {
            if (args.length == 5) {
                System.out.println(ToolsforCMD.startruningSTR());
                FilterBam_good_unmapped_mate.process(args[3], args[4]);
            } else {
                System.out.println("arguments error, please check your parameters");
            }

        }else if (FunctionClass.getArgsParameter(args, "-mode").equalsIgnoreCase("virus2")) {
            if (args.length == 5) {
                System.out.println(ToolsforCMD.startruningSTR());
                GetReadsMappedTwoDifferentChr.process(args[3], args[4]);
            } else {
                System.out.println("arguments error, please check your parameters");
            }

        }else if (FunctionClass.getArgsParameter(args, "-mode").equalsIgnoreCase("gatkcnv2gistic")) {
            if (args.length == 4) {
                System.out.println(ToolsforCMD.startruningSTR());
                new GATK_CNV_2_GISTIC(args[3]).processAll();
            }else if (args.length == 5) {
                System.out.println(ToolsforCMD.startruningSTR());
                new GATK_CNV_2_GISTIC(args[3],args[4]).processAll();
            }else {
                System.out.println("arguments error, please check your parameters");
            }

        }else {
            System.out.println("Please specified -mode parameters!");
        }
    }
}
