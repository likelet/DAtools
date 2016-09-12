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
                    + ToolsforCMD.print_ansi_CYAN( "<dir> <suffix> <outfile>" ) + "\r\n");
            System.out.println("Extra paramters for pipe  \r\n\t\t-col\t user defined col number for combination\r\n");

        } else if (FunctionClass.getArgsParameter(args, "-mode").equalsIgnoreCase("combineMatrix")) {
            if (args.length == 6) {
                System.out.println(ToolsforCMD.startruningSTR());
                Multifile2matrix mm = new Multifile2matrix(args[3], args[4], args[5]);
                if (FunctionClass.getArgsParameter(args, "-col") != null) {
                    mm.setColnumber(FunctionClass.getArgsParameter(args, "-col"));
                    System.out.println(mm.getColnumber() + "of column number is applied for combining ");
                }
            } else {
                System.out.println("args error!");
            }

        } else {
            System.out.println("Please specified -mode parameters!");
        }
    }

}
