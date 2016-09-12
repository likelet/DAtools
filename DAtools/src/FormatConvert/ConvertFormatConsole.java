/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package FormatConvert;

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
                    System.out.println("\r\n\t\t-username\t user defined name in output\r\n" + ToolsforCMD.ANSI_RESET);
                    System.out.println("\r\n\t\t-sep\t      seperator to parse your tab file\r\n" + ToolsforCMD.ANSI_RESET);
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

        } else {
            System.out.println("Please specified -mode parameters!");
        }
    }
}
