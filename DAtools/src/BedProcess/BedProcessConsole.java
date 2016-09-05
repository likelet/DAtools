/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package BedProcess;

import ExomeSeqAnalysisPipe.SNPfrequencyInSamples.SNPfrequencyInSamples;
import java.io.IOException;
import pub.FunctionClass;

/**
 *
 * @author Zinky
 */
public class BedProcessConsole {
     public BedProcessConsole(String[] args) throws IOException {
         if (args.length == 1) {
                System.out.println("Unique BED file using position information \r\n\t\tCMD : java -jar dataAnalysisTools.jar -bed -mode uniq in.bed out.bed");
                
            }  else if(FunctionClass.getArgsParameter(args, "-mode").equalsIgnoreCase("uniq")){
                new uniqueBed(args[3], args[4]);
            }
     }
}
