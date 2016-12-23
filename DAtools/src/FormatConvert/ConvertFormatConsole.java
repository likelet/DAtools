/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package FormatConvert;

import ExomeSeqAnalysisPipe.ConvertDataFormat.Varscan2Annovar;
import java.io.IOException;
import pub.FunctionClass;
import pub.ToolsforCMD;

/**
 *
 * @author Administrator
 * @since 2016-9-12
 * @coding time 13:36:39
 * @author Qi Zhao
 */
public class ConvertFormatConsole {

    public ConvertFormatConsole(String[] args) throws IOException {
        if (args.length == 1) {
            System.out.println("Convert tabfile into excel format\r\n\t\t"
                    + ToolsforCMD.ANSI_GREEN + "java -jar DAtools.jar -convertFormat -mode tab2excel" + ToolsforCMD.ANSI_RESET
                    + ToolsforCMD.ANSI_CYAN + " inputfile" + ToolsforCMD.ANSI_RESET);
             System.out.println("Extra paramters  ");
             System.out.println(ToolsforCMD.print_ansi_RED("\r\n\t\t-username\t ")
                    + ToolsforCMD.print_ansi_YELLOW("user defined name in output,DEFAULT  as the same with input file\r\n"));
            System.out.println(ToolsforCMD.print_ansi_RED("\r\n\t\t-sep\t")
                    + ToolsforCMD.print_ansi_YELLOW("seperator to parse your tab file\r\n"));
            System.out.println(ToolsforCMD.print_ansi_RED("\r\n\t\t-count\t ")
                    + ToolsforCMD.print_ansi_YELLOW("Merged readscount matrix (RSEM mode only),DEFAUL closed\r\n"));
            System.out.println("Convert varscanOut into Table anovar\r\n\t\t"
                    + ToolsforCMD.ANSI_GREEN + "java -jar DAtools.jar -convertFormat -mode [va]varscan2annovar " + ToolsforCMD.ANSI_RESET
                    + ToolsforCMD.ANSI_CYAN + " <varscanfile> <output>" + ToolsforCMD.ANSI_RESET);
//            System.out.println("Extra paramters  ");
//                    System.out.println("\r\n\t\t-somatic\t filtering germline mutations out\r\n" + ToolsforCMD.ANSI_RESET);
                  
            
        } else if (FunctionClass.getArgsParameter(args, "-mode").equalsIgnoreCase("tab2excel")) {
            tab2excel tb = new tab2excel(args[3]);
            if (FunctionClass.getArgsParameter(args, "-username") != null) {
                tb.setOutput(FunctionClass.getArgsParameter(args, "-username"));
                System.out.println(tb.getOutput() + " is applied for rename outputfile ");
            }
            if (FunctionClass.getArgsParameter(args, "-sep") != null) {
                tb.setSeperator(FunctionClass.getArgsParameter(args, "-sep"));
                System.out.println(tb.getSeperator() + " is applied for rename outputfile ");
            }
            tb.tab2excel();

        } else if (FunctionClass.getArgsParameter(args, "-mode").startsWith("va")) {
            System.out.println("Program start...");
            Varscan2Annovar.Varscan2Annovar("-s", args[3], args[4]);//somatic only
            System.out.println("Finishing file "+args[3]);

        }else {
            System.out.println("Please specified -mode parameters!");
        }
    }
}
