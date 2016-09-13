/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Multifile2Matrix;

import java.io.IOException;
import pub.FunctionClass;
import pub.ToolsforCMD;

/**
 * <p>
 * FastQprocessConsole</p>
 * <p>
 * Created on 2016-1-12 15:29:13</p>
 * <p>
 * Author Email: zhaoqi3@mail2.sysu.edu.cn</p>
 *
 * @author ZHAO Qi
 * @date 2016-1-12 15:29:13
 * @version java 1.6.0
 * @version
 */
public class MatrixGenerateConsole {

    public MatrixGenerateConsole(String[] args) throws IOException {
        if (args.length == 1) {
            System.out.println("Merge multiple mapped file into a matrix:   \r\n\t\t"
                    + ToolsforCMD.print_ansi_GREEN( "java -jar DAtools.jar -MM -mode combineMatrix " )
                    + ToolsforCMD.print_ansi_CYAN( "<dir> <suffix> <outfile> [options]" ) + "\r\n");
            System.out.println(ToolsforCMD.print_ansi_WHITE("Extra paramters for options"));
            System.out.println(ToolsforCMD.print_ansi_RED("\r\n\t\t-col\t ")
                    + ToolsforCMD.print_ansi_YELLOW("User defined col number for combination,DEFAULT 2\r\n"));
            System.out.println(ToolsforCMD.print_ansi_RED("\r\n\t\t-excel\t")
                    + ToolsforCMD.print_ansi_YELLOW("Write out matrix table in excel format,DEFAULT closed\r\n"));
        } else if (FunctionClass.getArgsParameter(args, "-mode").equalsIgnoreCase("combineMatrix")) {
            if (args.length <=9) {
                System.out.println(ToolsforCMD.startruningSTR());
                Multifile2matrix mm = new Multifile2matrix(args[3], args[4], args[5]);
                if (FunctionClass.getArgsParameter(args, "-col") != null) {
                    mm.setColnumber(FunctionClass.getArgsParameter(args, "-col"));
                    System.out.println(mm.getColnumber() + "of column number is applied for combining ");
                }
                 mm.process();
                if (FunctionClass.isContainParameter(args, "-excel")) {
                    mm.writeoutExel();
                }
                mm.writeout();
            } else {
                System.out.println("args error!");
            }
            
        } else {
            System.out.println("Please specified -mode parameters!");
        }
    }

}
