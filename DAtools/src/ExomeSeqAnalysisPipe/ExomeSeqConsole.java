/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ExomeSeqAnalysisPipe;

import ExomeSeqAnalysisPipe.CNVnator.RunCNVnatorFinal;
import ExomeSeqAnalysisPipe.CONTRAanalysis.GscoreStatistic.CNVMatrixIterm;
import ExomeSeqAnalysisPipe.CONTRAanalysis.GscoreStatistic.GetStatic_fromContraOutput;
import ExomeSeqAnalysisPipe.CONTRAanalysis.GscoreStatistic.GetStatic_fromPermutationList;
import ExomeSeqAnalysisPipe.CONTRAanalysis.summaryContra.summaryContraResult;
import ExomeSeqAnalysisPipe.CONTRAanalysis.summaryContra.summaryContraResultWithChromeBind;
import ExomeSeqAnalysisPipe.CoverageGenerate.CoverageForExomeSeq;
import java.io.IOException;
import ExomeSeqAnalysisPipe.RunCrest.RunCrestExtractSclipFinal;
import ExomeSeqAnalysisPipe.RunCrest.RunCrestSVfinal;
import ExomeSeqAnalysisPipe.SNPfrequencyInSamples.AnnotedSnpFrequencyInsamples;
import ExomeSeqAnalysisPipe.SNPfrequencyInSamples.SNPfrequencyInSamples;
import ExomeSeqAnalysisPipe.Var2scan2ICGCformat.SomaticeProcessing;
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
                System.out.println("Statistic snp frequency in allsamples \r\n\t\t"+ToolsforCMD.ANSI_GREEN+"java -jar DAtools.jar -exome -mode snpfre"+ToolsforCMD.ANSI_RESET+
                        ToolsforCMD.ANSI_CYAN+" dir outfile "+ToolsforCMD.ANSI_RESET+"\r\n");
                System.out.println("Statistic snp frequency in allsamples \r\n\t\t"+ToolsforCMD.ANSI_GREEN+"java -jar DAtools.jar -exome -mode snpEfffre"+ToolsforCMD.ANSI_RESET+
                        ToolsforCMD.ANSI_CYAN+" dir outfile [suffix] "+ToolsforCMD.ANSI_RESET+"\r\n");
                System.out.println("Convert varscan result to ICGC format \r\n\t\t"+ToolsforCMD.ANSI_GREEN+"java -jar DAtools.jar -exome -mode varscanICGC"+ToolsforCMD.ANSI_RESET+
                        ToolsforCMD.ANSI_CYAN+" vsfinputfile ssmpoutfile analysisid sampleid "+ToolsforCMD.ANSI_RESET+"\r\n");
                System.out.println("Run Crest extrat SCLIP pl parallel(require samtools & crest) \r\n\t\t"+ToolsforCMD.ANSI_GREEN+"java -jar DAtools.jar -exome -mode crestExtract"+ToolsforCMD.ANSI_RESET+
                        ToolsforCMD.ANSI_CYAN+" bamfile crestExtractSclipPath genome threadnum "+ToolsforCMD.ANSI_RESET+"\r\n");
                System.out.println("Run Crest extrat SCLIP pl parallel(require samtools & crest) \r\n\t\t"+ToolsforCMD.ANSI_GREEN+"java -jar DAtools.jar -exome -mode crestSV"+ToolsforCMD.ANSI_RESET+
                        ToolsforCMD.ANSI_CYAN+" tumorbamfile [germlinebamfile] genomepath genomebit crestSVpath coverfile hostname threadnum "+ToolsforCMD.ANSI_RESET+"\r\n");
                System.out.println("Run CNVnatorExtract parallel(require samtools & cvnator) \r\n\t\t"+ToolsforCMD.ANSI_GREEN+"java -jar DAtools.jar -exome -mode cnvnator"+ToolsforCMD.ANSI_RESET+
                        ToolsforCMD.ANSI_CYAN+" bamfile threadnum "+ToolsforCMD.ANSI_RESET+"\r\n");
                System.out.println("Run CNVnator (require cvnator) \r\n\t\t"+ToolsforCMD.ANSI_GREEN+"java -jar DAtools.jar -exome -mode cnvnator2"+ToolsforCMD.ANSI_RESET+
                        ToolsforCMD.ANSI_CYAN+" bamfile  "+ToolsforCMD.ANSI_RESET+"\r\n");
                System.out.println("Process Annovar maf file\r\n\t\t"+ToolsforCMD.ANSI_GREEN+"java -jar DAtools.jar -exome -mode annovarTab2maf"+ToolsforCMD.ANSI_RESET+
                        ToolsforCMD.ANSI_CYAN+" infile outfile tumorname normalname  "+ToolsforCMD.ANSI_RESET+"\r\n");
                System.out.println("Process VEP maf file\r\n\t\t"+ToolsforCMD.ANSI_GREEN+"java -jar DAtools.jar -exome -mode vep2tcgaMaf"+ToolsforCMD.ANSI_RESET+
                        ToolsforCMD.ANSI_CYAN+" infile outfile "+ToolsforCMD.ANSI_RESET+"\r\n");
                System.out.println("Processing Contra Output\r\n\t\t"+ToolsforCMD.ANSI_GREEN+"java -jar DAtools.jar -exome -mode contraGscore"+ToolsforCMD.ANSI_RESET+
                        ToolsforCMD.ANSI_CYAN+" folderlist outfile  "+ToolsforCMD.ANSI_RESET+"\r\n");
                System.out.println("Processing Contra Output\r\n\t\t"+ToolsforCMD.ANSI_GREEN+"java -jar DAtools.jar -exome -mode contraMatrix"+ToolsforCMD.ANSI_RESET+
                        ToolsforCMD.ANSI_CYAN+" originfilr outmatrix  "+ToolsforCMD.ANSI_RESET+"\r\n");
                System.out.println("Processing Contra Output\r\n\t\t"+ToolsforCMD.ANSI_GREEN+"java -jar DAtools.jar -exome -mode contraWindowbin"+ToolsforCMD.ANSI_RESET+
                        ToolsforCMD.ANSI_CYAN+" pairedsampletable faifile outmatrix gscorefile  "+ToolsforCMD.ANSI_RESET+"\r\n");
                System.out.println("Summary the coverage of Exome-seq data \r\n\t\t"+ToolsforCMD.ANSI_GREEN+"java -jar DAtools.jar -exome -mode cov"+ToolsforCMD.ANSI_RESET+
                        ToolsforCMD.ANSI_CYAN+" regionfile outfile "+ToolsforCMD.ANSI_RESET+"\r\n");
               
            } else if(FunctionClass.getArgsParameter(args, "-mode").equalsIgnoreCase("snpfre")){
                new SNPfrequencyInSamples(args[3], args[4]);
            }else if(FunctionClass.getArgsParameter(args, "-mode").equalsIgnoreCase("snpEfffre")){
                if(args.length>5){
                    new AnnotedSnpFrequencyInsamples(args[3], args[4],args[5]);  
                }else{
                    new AnnotedSnpFrequencyInsamples(args[3], args[4]);
                }
            } else if (FunctionClass.getArgsParameter(args, "-mode").equalsIgnoreCase("varscanICGC")) {
                if (args.length == 7) {
                    new SomaticeProcessing().processing(args[3], args[4], args[5], args[6]);
                } else {
                    System.out.println("arguments error, please check your parameters");
                }
            } else if (FunctionClass.getArgsParameter(args, "-mode").equalsIgnoreCase("crestExtract")) {
                if (args.length == 7) {
                    new RunCrestExtractSclipFinal(args[3], args[4], args[5],Integer.parseInt(args[6]));
                } else {
                    System.out.println("arguments error, please check your parameters");
                }
            }else if (FunctionClass.getArgsParameter(args, "-mode").equalsIgnoreCase("crestSV")) {
                if (args.length == 10) {
                    new RunCrestSVfinal(args[3], args[4], args[5],args[6], args[7],args[8],Integer.parseInt(args[9]));
                } if(args.length == 11) {
                    new RunCrestSVfinal(args[3], args[4], args[5],args[6], args[7],args[8], args[9],Integer.parseInt(args[10]));
                } else {
                    System.out.println("arguments error, please check your parameters");
                }
            }else if (FunctionClass.getArgsParameter(args, "-mode").equalsIgnoreCase("cnvnator")) {
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
                    new AnnoTab2MAF(args[3],args[4],args[5],args[6]);
                } else {
                    System.out.println("arguments error, please check your parameters");
                }
                
            } else if (FunctionClass.getArgsParameter(args, "-mode").equalsIgnoreCase("vep2tcgaMaf")) {
                if (args.length == 5) {
                    new VepMAF2TCGAmafMain().convertFile(args[3],args[4]);
                } else {
                    System.out.println("arguments error, please check your parameters");
                }
                
            } else if (FunctionClass.getArgsParameter(args, "-mode").equalsIgnoreCase("contraGscore")) {
                if (args.length == 6) {
                    new GetStatic_fromContraOutput(args[3],args[4]);
                } else {
                    System.out.println("arguments error, please check your parameters");
                }
                
            } else if (FunctionClass.getArgsParameter(args, "-mode").equalsIgnoreCase("contraMatrix")) {
                if (args.length == 5) {
                    new summaryContraResult(args[3],args[4]);
                } else {
                    System.out.println("arguments error, please check your parameters");
                }
                
        } else if (FunctionClass.getArgsParameter(args, "-mode").equalsIgnoreCase("contraWindowbin")) {
            if (args.length == 7) {
                ArrayList<CNVMatrixIterm> oringinlist = new summaryContraResultWithChromeBind(args[3], args[4], args[5]).getOringinlist();
                new GetStatic_fromPermutationList(oringinlist, args[6]);
            } else {
                System.out.println("arguments error, please check your parameters");
            }

        } else if (FunctionClass.getArgsParameter(args, "-mode").equalsIgnoreCase("cov")) {
            if (args.length == 5) {
                new CoverageForExomeSeq(args[3],args[4]); 
            } else {
                System.out.println("arguments error, please check your parameters");
            }

        } else {
            System.out.println("Please specified -mode parameters!");
        }
        
//        if(args.length==0){
//            System.out.println("please input args\n type java -jar SNPstatus.jar -h for help");
//        }else if(args[0].endsWith("-h")){
//            System.out.println("snpNumber Distribution:  java -jar SNPstatus.jar -ClusterFeture simpleDir genome.fa.fai sampleMappingfile windowsNumber ThreadNumber output");
//            System.out.println("snp density in samples: java -jar SNPstatus.jar -FrequencySummary simpleDir ThreadNumber OutputFile");
//        } else if (args[0].equals("-ClusterFeture")) {
//            if (args.length == 6) {
//                try {
//                    new FeatureGeneraterFinal(args[1],args[2],args[3],Integer.parseInt(args[4]),Integer.parseInt(args[5])).print(args[6]);
//                } catch (IOException ex) {
//                    Logger.getLogger(ExomeSeqConsole.class.getName()).log(Level.SEVERE, null, ex);
//                }
//            } else {
//                System.out.println("args error!");
//            }
//        }else if(args[0].equals("-FrequencySummary")){
//            try {
//                new FrequecySummeray(args[1],Integer.parseInt(args[2])).print(args[3]);
//            } catch (IOException ex) {
//                Logger.getLogger(ExomeSeqConsole.class.getName()).log(Level.SEVERE, null, ex);
//            }
//        } else {
//            System.out.println("command error!");
//        }
    }
}
