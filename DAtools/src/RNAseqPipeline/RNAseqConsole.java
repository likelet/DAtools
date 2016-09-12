/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package RNAseqPipeline;

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
public class RNAseqConsole {

    public RNAseqConsole(String[] args) throws IOException {
         if (args.length == 1) {
                System.out.println("Calculate RPKM using count & genelength file(single file):   \r\n\t\t"+ToolsforCMD.ANSI_GREEN+"java -jar DAtools.jar -RNAseq -mode cRPKM Genecountfile Genelenghfile outfile"+ToolsforCMD.ANSI_RESET+"\r\n");
                System.out.println("Calculate RPKM matrix using count & genelength file(multiple file):   \r\n\t\t"+ToolsforCMD.ANSI_GREEN+"java -jar DAtools.jar -RNAseq -mode cRPKMm suffix Genelenghfile outfile"+ToolsforCMD.ANSI_RESET+"\r\n");
       
         } else if (FunctionClass.getArgsParameter(args, "-mode").equalsIgnoreCase("cRPKM")) {
                if (args.length == 6) {
                    // System.out.println();
                    System.out.println(ToolsforCMD.startruningSTR());
                    new computeRPKM(args[3],args[4],args[5]);
                 } else {

                    System.out.println("args error!");
                }
            
            } else if (FunctionClass.getArgsParameter(args, "-mode").equalsIgnoreCase("cRPKMm")) {
                if (args.length == 6) {
                    // System.out.println();
                    System.out.println(ToolsforCMD.startruningSTR());
                    getCountMatrixFromMap gmm=new getCountMatrixFromMap(args[3],args[4],args[5]);
                    gmm.process();
                 } else {

                    System.out.println("args error!");
                }
            
            }  else {
                System.out.println("Please specified -mode parameters!");
            }
    }

}
