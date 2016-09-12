/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package Multifile2Matrix;

import RNAseqPipeline.*;
import RNAseqPipeline.CountRPKMfromCount.getCountMatrixFromMap;
import RNAseqPipeline.DoseCompensation.RemoveIntersectRegionFromGTF.computeRPKM;
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
public class MatrixGenerateConsole {

    public MatrixGenerateConsole(String[] args) throws IOException {
         if (args.length == 1) {
                System.out.println("Merge multiple mapped file into a matrix:   \r\n\t\t"+
                        ToolsforCMD.ANSI_GREEN+"java -jar DAtools.jar -MM -mode combineMatrix "+ToolsforCMD.ANSI_RESET+
                            ToolsforCMD.ANSI_CYAN+"dir suffix outfile"+ToolsforCMD.ANSI_RESET+"\r\n");
         } else if (FunctionClass.getArgsParameter(args, "-mode").equalsIgnoreCase("combineMatrix")) {
                if (args.length == 6) {
                    System.out.println(ToolsforCMD.startruningSTR());
                    new Multifile2matrix(args[3],args[4],args[5]);
                 } else {
                    System.out.println("args error!");
                }
            
            }  else {
                System.out.println("Please specified -mode parameters!");
            }
    }

}
