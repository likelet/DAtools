/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package ArrayProcessFunction;

import ArrayProcessFunction.CollapseProbeName.CollapseMainFunction;
import pub.FunctionClass;
import pub.ToolsforCMD;

/**
 * <p>ArrayAnalysisConsole</p>
 * <p>Created on 2016-11-4 14:15:41</p>
 * <p>Author Email: zhaoqi3@mail2.sysu.edu.cn</p>
 * @author ZHAO Qi
 * @date 2016-11-4 14:15:41
 * @version java 1.6.0
 * @version 
 */
public class ArrayAnalysisConsole {

    public ArrayAnalysisConsole(String[] args) {
        if (args.length == 1) {
            System.out.println(ToolsforCMD.print_ansi_YELLOW("Collapse probeID into gene Name :   \r\n\t\t")
                    + ToolsforCMD.print_ansi_GREEN("java -jar DAtools.jar -array -mode collapse")
                    + ToolsforCMD.print_ansi_CYAN(" <Gene2ProbMapfile> <IDfeature> <output>"));

        } else if (FunctionClass.getArgsParameter(args, "-mode").equalsIgnoreCase("collapse")) {
            System.out.println(ToolsforCMD.startruningSTR());
            new CollapseMainFunction(args[3], args[4], args[5]);
        } else {
            System.out.println(ToolsforCMD.print_ansi_RED("Command error!"));
        }
    }
}
