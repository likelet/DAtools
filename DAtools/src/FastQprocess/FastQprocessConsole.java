/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package FastQprocess;

import java.io.IOException;
import pub.FunctionClass;
import pub.ToolsforCMD;

/**
 * <p>FastQprocessConsole</p>
 * <p>Created on 2016-1-12 15:29:13</p>
 * <p>Author Email: zhaoqi3@mail2.sysu.edu.cn</p>
 * @author ZHAO Qi
 * @date 2016-1-12 15:29:13
 * @version java 1.6.0
 * @version 
 */
public class FastQprocessConsole {

    public FastQprocessConsole(String[] args) throws IOException {
         if (args.length == 1) {
                System.out.println("Trim fastq by constant length :   \r\n\t\t"+ToolsforCMD.ANSI_GREEN+"java -jar DAtools.jar -fastqP -mode trimedFastqFilter"+ToolsforCMD.ANSI_RESET+
                        ToolsforCMD.ANSI_CYAN+" left right filterlength all.fq/gz passed.fq failed.fq "+ToolsforCMD.ANSI_RESET+"\r\n");
                System.out.println("Trim fastq by constant length in memory efficient mode (single end) :   \r\n\t\t"+ToolsforCMD.ANSI_GREEN+"java -jar DAtools.jar -fastqP -mode EStrimedFastqFilter"+ToolsforCMD.ANSI_RESET+
                        ToolsforCMD.ANSI_CYAN+" left right all.fq/gz out.fq "+ToolsforCMD.ANSI_RESET+"\r\n");
                System.out.println("Trim fastq by constant length in memory efficient mode (paired end) :   \r\n\t\t"+ToolsforCMD.ANSI_GREEN+"java -jar DAtools.jar -fastqP -mode EPtrimedFastqFilter"+ToolsforCMD.ANSI_RESET+
                        ToolsforCMD.ANSI_CYAN+" left right fastq1/gz fastq2/gz failed.fq "+ToolsforCMD.ANSI_RESET+"\r\n");               
                System.out.println("Trim fastq by constant length only for those longer than x :   \r\n\t\t"+ToolsforCMD.ANSI_GREEN+"java -jar DAtools.jar -fastqP -mode trimedFastqFilterByminiLength"+ToolsforCMD.ANSI_RESET+
                        ToolsforCMD.ANSI_CYAN+" right lengthcondation all.fq/gz passed.fq "+ToolsforCMD.ANSI_RESET+"\r\n");
                System.out.println("Trim fastq by constant length and retain constant reads :   \r\n\t\t"+ToolsforCMD.ANSI_GREEN+"java -jar DAtools.jar -fastqP -mode trimZheng"+ToolsforCMD.ANSI_RESET+
                        ToolsforCMD.ANSI_CYAN+" left constantlength all.fq/gz "+ToolsforCMD.ANSI_RESET+"\r\n");
                System.out.println("Trim fastq by constant length and retain constant reads :   \r\n\t\t"+ToolsforCMD.ANSI_GREEN+"java -jar DAtools.jar -fastqP -mode retainLength"+ToolsforCMD.ANSI_RESET+
                        ToolsforCMD.ANSI_CYAN+" left inputfastq/gz outfastq/gz "+ToolsforCMD.ANSI_RESET+"\r\n");
            } else if (FunctionClass.getArgsParameter(args, "-mode").equalsIgnoreCase("trimedFastqFilter")) {
                if (args.length == 9) {
                    // System.out.println();
                    System.out.println(ToolsforCMD.startruningSTR());
                    new FastQprocess(args[6]).trimFastq(Integer.parseInt(args[3]), Integer.parseInt(args[4]), Integer.parseInt(args[5]), args[6], args[7]);
                } else if ((args.length == 7)) {
                    System.out.println(ToolsforCMD.startruningSTR());
                    new FastQprocess(args[6]).trimFastq(Integer.parseInt(args[3]), Integer.parseInt(args[4]), Integer.parseInt(args[5]), "passed.fq", "failed.fq");
                } else {

                    System.out.println("args error!");
                }
            } else if (FunctionClass.getArgsParameter(args, "-mode").equalsIgnoreCase("EStrimedFastqFilter")) {
                if (args.length == 7) {
                    // System.out.println();
                    System.out.println(ToolsforCMD.startruningSTR());
                    new FastaQprocessMemory().processSingleFileEffi(Integer.parseInt(args[3]), Integer.parseInt(args[4]), args[5], args[6]);
                }  else {   
                    System.out.println("args error!");
                }
            } else if (FunctionClass.getArgsParameter(args, "-mode").equalsIgnoreCase("EPtrimedFastqFilter")) {
                if (args.length == 7) {
                    // System.out.println();
                    System.out.println(ToolsforCMD.startruningSTR());
                    new FastaQprocessMemory().processPairedFileEffi(Integer.parseInt(args[3]), Integer.parseInt(args[4]), args[5], args[6]);
                }  else {   
                    System.out.println("args error!");
                }
            }else if (FunctionClass.getArgsParameter(args, "-mode").equalsIgnoreCase("trimedFastqFilterByminiLength")) {
                if (args.length == 7) {
                    // System.out.println();
                    System.out.println(ToolsforCMD.startruningSTR());
                    new FastQprocess(args[5]).trimFastqByminiLength(Integer.parseInt(args[3]), Integer.parseInt(args[4]), args[5]);
                } else if ((args.length == 6)) {
                    System.out.println(ToolsforCMD.startruningSTR());
                    new FastQprocess(args[5]).trimFastqByminiLength(Integer.parseInt(args[2]), Integer.parseInt(args[5]), "passed.fq");
                } else {
                    System.out.println("args error!");
                }
            } else if (FunctionClass.getArgsParameter(args, "-mode").equalsIgnoreCase("trimZheng")) {
                if (args.length == 6) {
                    System.out.println(ToolsforCMD.startruningSTR());
                    String outname = ToolsforCMD.replaceSuffix(args[5],".fq.gz","_passed.fq");
                    new FastQprocess(args[5]).trimFastqLeftconstantLength(Integer.parseInt(args[3]), Integer.parseInt(args[4]), outname);
                } else {
                    System.out.println("arguments error, please check your parameters");
                }
            }  else if (FunctionClass.getArgsParameter(args, "-mode").equalsIgnoreCase("retainLength")) {
                if (args.length == 6) {
                    System.out.println(ToolsforCMD.startruningSTR());
                    String outname = ToolsforCMD.replaceSuffix(args[5],".fq.gz","_passed.fq");
                    new FastQprocess(args[4]).retainLengthFastq(Integer.parseInt(args[3]), outname);
                } else {
                    System.out.println("arguments error, please check your parameters");
                }
            }  else {
                System.out.println("Please specified -mode parameters!");
            }
    }

}
