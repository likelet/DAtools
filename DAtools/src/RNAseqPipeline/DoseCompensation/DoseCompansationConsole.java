/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package RNAseqPipeline.DoseCompensation;

import RNAseqPipeline.DoseCompensation.RemoveIntersectRegionFromGTF.computeRPKM;
import RNAseqPipeline.DoseCompensation.RemoveIntersectRegionFromGTF.gtf2lengthfile;
import RNAseqPipeline.DoseCompensation.ResultStatistic.StaticChrome;
import RNAseqPipeline.DoseCompensation.ResultStatistic.StaticChromeByClass;
import RNAseqPipeline.DoseCompensation.SequenceFeature.CombineAnalysis;
import java.io.File;
import pub.FilelistReader;
import pub.FunctionClass;
import pub.ToolsforCMD;

/**
 *
 * @author Administrator
 * @since 2016-9-12
 * @coding time 13:54:41
 * @author Qi Zhao
 */
public class DoseCompansationConsole {
    public DoseCompansationConsole (String[] args) {
        
            if (args.length == 1 ||args[1].equalsIgnoreCase("-h")) {
                System.out.println("Convert gtf2lengthfile (Essemble gene only gtf)\r\n\t\t"+
                        ToolsforCMD.ANSI_GREEN+"java -jar DAtools.jar -dose -mode gtf2length"+ToolsforCMD.ANSI_RESET+
                            ToolsforCMD.ANSI_CYAN+" lengthfile gtffile outrpkmfile"+ToolsforCMD.ANSI_RESET);
                System.out.println("Calculating rpkm with length &Counting file\r\n\t\t"+
                        ToolsforCMD.ANSI_GREEN+"java -jar DAtools.jar -dose -mode crpkm"+ToolsforCMD.ANSI_RESET+
                            ToolsforCMD.ANSI_CYAN+" lengthfile countingfile outrpkmfile"+ToolsforCMD.ANSI_RESET);
                System.out.println("Calculating rpkm with gtf &Counting file\r\n\t\t"+
                        ToolsforCMD.ANSI_GREEN+"java -jar DAtools.jar -dose -mode crpkm2"+ToolsforCMD.ANSI_RESET+
                            ToolsforCMD.ANSI_CYAN+" gtf countingfile outrpkmfile"+ToolsforCMD.ANSI_RESET);
                System.out.println("Statistic crpkm outputfile  \r\n\t\t"+
                        ToolsforCMD.ANSI_GREEN+"java -jar DAtools.jar -dose -mode static"+ToolsforCMD.ANSI_RESET+
                            ToolsforCMD.ANSI_CYAN+" inrpkmfile"+ToolsforCMD.ANSI_RESET);
                System.out.println("Statistic crpkm outputfile with *rpkm suffixed (dir) \r\n\t\t"+
                        ToolsforCMD.ANSI_GREEN+"java -jar DAtools.jar -dose -mode staticDir"+ToolsforCMD.ANSI_RESET+
                            ToolsforCMD.ANSI_CYAN+" inrpkmfile"+ToolsforCMD.ANSI_RESET);
                System.out.println("Statistic crpkm outputfile with *rpkm suffixed (dir) \r\n\t\t"+
                        ToolsforCMD.ANSI_GREEN+"java -jar DAtools.jar -dose -mode staticDirClass"+ToolsforCMD.ANSI_RESET+
                            ToolsforCMD.ANSI_CYAN+" inrpkmfile mapfile"+ToolsforCMD.ANSI_RESET);
                System.out.println("Calculating the sequence feature like GCcontent,hexmer entropy and rRNA similarity \r\n\t\t"+
                        ToolsforCMD.ANSI_GREEN+"java -jar DAtools.jar -dose -mode seqFeature"+ToolsforCMD.ANSI_RESET+
                            ToolsforCMD.ANSI_CYAN+" <inputfasta> <outfile> [-thread n]"+ToolsforCMD.ANSI_RESET);
            }else if(FunctionClass.getArgsParameter(args, "-mode").equalsIgnoreCase("gtf2length")){
                new gtf2lengthfile(args[3], args[4]);
            } else if(FunctionClass.getArgsParameter(args, "-mode").equalsIgnoreCase("crpkm")){
                new computeRPKM(args[3], args[4],args[5]);
            }else if(FunctionClass.getArgsParameter(args, "-mode").equalsIgnoreCase("crpkm2")){
                new computeRPKM(args[3], args[4], args[5], false);
            } else if (FunctionClass.getArgsParameter(args, "-mode").equalsIgnoreCase("static")) {
                new StaticChrome(args[3]);
            }else if (FunctionClass.getArgsParameter(args, "-mode").equalsIgnoreCase("staticDir")) {
                File[] filelist = FilelistReader.getFileList(args[3], "rpkm");
                System.out.println("FileName\tNormal Median\t X Median" );
                for (int i = 0; i < filelist.length; i++) {

                    new StaticChrome(filelist[i].getAbsolutePath());
                }
            } else if (FunctionClass.getArgsParameter(args, "-mode").equalsIgnoreCase("staticDirClass")) {
                File[] filelist = FilelistReader.getFileList(args[3], "rpkm");
                System.out.println("FileName\tNormal Median\t X Median" );
                for (int i = 0; i < filelist.length; i++) {

                    new StaticChromeByClass(filelist[i].getAbsolutePath(),args[4]);
                }
            } else if (FunctionClass.getArgsParameter(args, "-mode").equalsIgnoreCase("seqFeature")) {
                System.out.println(ToolsforCMD.startruningSTR());
                if (FunctionClass.getArgsParameter(args, "-thread") != null) {
                   new CombineAnalysis(args[3],args[4],Integer.parseInt(FunctionClass.getArgsParameter(args, "-thread")));
                }else{
                new CombineAnalysis(args[3],args[4]);
                }
            } else {
                System.out.println("Please specified -mode parameters!");
            }
    }
}
