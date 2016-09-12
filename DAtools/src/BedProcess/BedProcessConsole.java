/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package BedProcess;

import BedProcess.Unibed.uniqueBed;
import BedProcess.intersectBed.GetIntersectBed;
import java.io.IOException;
import pub.FunctionClass;
import pub.ToolsforCMD;

/**
 *
 * @author Zinky
 */
public class BedProcessConsole {
     public BedProcessConsole(String[] args) throws IOException {
         if (args.length == 1) {
                System.out.println("Unique BED file using position information \r\n\t\t"+ToolsforCMD.ANSI_GREEN+"java -jar DAtools.jar -bed -mode uniq in.bed out.bed "+ToolsforCMD.ANSI_RESET+"\r\n");
                System.out.println("Intersect 2 BED file\r\n\t\t"+ToolsforCMD.ANSI_GREEN+"java -jar DAtools.jar -bed -mode intersect in1.bed in2.bed out.bed "+ToolsforCMD.ANSI_RESET+"\r\n");
                
            }  else if(FunctionClass.getArgsParameter(args, "-mode").equalsIgnoreCase("uniq")){
                System.out.println(ToolsforCMD.startruningSTR());
                new uniqueBed(args[3], args[4]);
            }else if(FunctionClass.getArgsParameter(args, "-mode").equalsIgnoreCase("intersect")){
                System.out.println(ToolsforCMD.startruningSTR());
                new GetIntersectBed(args[3], args[4],args[5]);
            }
     }
}
