/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package BatchDrawCoverageByR;

import java.io.IOException;
import pub.Rexe;

/**
 *
 * @author ZHAO Qi
 * @date 2014-3-7 19:35:09
 * @version 1.6.0
 */
public class DrawCoveragePlot {
    public static void main(String[] args) throws IOException {
        new RscriptGenerator().RscriptGenerator("F:\\resouces\\projects\\ruibo\\human\\coverage", "F:\\resouces\\projects\\ruibo\\human\\coverageR");
         Rexe rexe=new Rexe("F:\\resouces\\projects\\ruibo\\human\\coverageR");
    }
}
