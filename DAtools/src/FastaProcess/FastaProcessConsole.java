/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package FastaProcess;

import FastaProcess.DrawUnigeneDistribution.DrawDistributionPlot;
import FastaProcess.DrawUnigeneDistribution.TrinityProcess;
import FastaProcess.FastaDemuplex.fastqDemultiplex;
import FastaProcess.FastaStatic.getFastaLength;
import FastaProcess.GetFastaByID.extractFastaByID;
import java.io.IOException;
import pub.FunctionClass;
import pub.ToolsforCMD;

/**
 *
 * @author Administrator
 * @since 2016-9-12
 * @coding time 13:44:57
 * @author Qi Zhao
 */
public class FastaProcessConsole {

    public FastaProcessConsole(String[] args) throws IOException {
      
        if (args.length == 1) {
                System.out.println("Plot genelenthDistribution by R\r\n\t\t" + 
                        ToolsforCMD.ANSI_GREEN + "java -jar DAtools.jar -Fasta -mode plotlength" + ToolsforCMD.ANSI_RESET+//mode
                            ToolsforCMD.ANSI_CYAN + " fastafile [min max step] [options]" + ToolsforCMD.ANSI_RESET);
                System.out.println("Conculate Ns of fasta file \r\n\t\t" + 
                        ToolsforCMD.ANSI_GREEN + "java -jar DAtools.jar -Fasta -mode ConN" + ToolsforCMD.ANSI_RESET+//mode
                            ToolsforCMD.ANSI_CYAN + " fastafile" + ToolsforCMD.ANSI_RESET);
                System.out.println("Get fastalength file \r\n\t\t"+
                        ToolsforCMD.ANSI_GREEN+"java -jar DAtools.jar -Fasta -mode getlength" + ToolsforCMD.ANSI_RESET+//mode
                            ToolsforCMD.ANSI_CYAN + " infile.fa outfile"+ToolsforCMD.ANSI_RESET);
                System.out.println("Split Fasta file \r\n\t\t"+
                        ToolsforCMD.ANSI_GREEN+"java -jar DAtools.jar -Fasta -mode split" + ToolsforCMD.ANSI_RESET+//mode
                            ToolsforCMD.ANSI_CYAN + " infile.fa"+ToolsforCMD.ANSI_RESET);
                System.out.println("demultiplex Fasta file \r\n\t\t"+
                        ToolsforCMD.ANSI_GREEN+"java -jar DAtools.jar -Fasta -mode deplex" + ToolsforCMD.ANSI_RESET+//mode
                            ToolsforCMD.ANSI_CYAN + " infile.fa"+ToolsforCMD.ANSI_RESET+ ToolsforCMD.ANSI_CYAN + " indexSample.txt"+ToolsforCMD.ANSI_RESET);
                 System.out.println("Extract Fasta sequences from Big fastafile using IDlist \r\n\t\t" + 
                        ToolsforCMD.ANSI_GREEN + "java -jar DAtools.jar -Fasta -mode extractbyID" + ToolsforCMD.ANSI_RESET+//mode
                            ToolsforCMD.ANSI_CYAN + " fastafile IDfile Outputfile" + ToolsforCMD.ANSI_RESET);
            
               //extra parameters 
                System.out.println("Extra paramters for plotlength/ConN");
                        System.out.println("\r\n\t\t-username\t user defined name in output\r\n");
                        System.out.println("\r\n\t\t-min\t      minimum length in axis(default 0)\r\n");
                        System.out.println("\r\n\t\t-max\t      maxnum length in axis(default 3000)\r\n");
                        System.out.println("\r\n\t\t-step\t     seperator to parse your tab file(default 300)\r\n");
            } else if(FunctionClass.getArgsParameter(args, "-mode").equalsIgnoreCase("plotlength")){
                DrawDistributionPlot dp=new DrawDistributionPlot(args[3]);
                if (FunctionClass.getArgsParameter(args, "-username") != null) {
                   dp.outname=FunctionClass.getArgsParameter(args, "-username");
                }
                if (FunctionClass.getArgsParameter(args, "-min") != null) {
                   dp.min=Integer.parseInt(FunctionClass.getArgsParameter(args, "-min"));
                }
                if (FunctionClass.getArgsParameter(args, "-max") != null) {
                   dp.max=Integer.parseInt(FunctionClass.getArgsParameter(args, "-max"));
                }
                if (FunctionClass.getArgsParameter(args, "-step") != null) {
                   dp.step=Integer.parseInt(FunctionClass.getArgsParameter(args, "-step"));
                }
               dp.process();
            }else if(FunctionClass.getArgsParameter(args, "-mode").equalsIgnoreCase("conN")){
                TrinityProcess.calculationNnummer(args[3],false);
            }else if(FunctionClass.getArgsParameter(args, "-mode").equalsIgnoreCase("getlength")){
                getFastaLength.getfastaLength(args[3], args[4]);
            }else if(FunctionClass.getArgsParameter(args, "-mode").equalsIgnoreCase("split")){
                new SplitFasta2multifile(args[3]);
            }else if(FunctionClass.getArgsParameter(args, "-mode").equalsIgnoreCase("extractbyID")){
                new extractFastaByID(args[3],args[4],args[5]);
            }else if(FunctionClass.getArgsParameter(args, "-mode").equalsIgnoreCase("deplex")){
                new fastqDemultiplex(args[3],args[4]);
            }else{
                System.out.println("Please specified -mode parameters!");
            }
        
    }

}
