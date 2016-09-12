/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package FastaProcess.DrawUnigeneDistribution;

import java.awt.Image;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import pub.ImageFrame;
import pub.Rexe;
import pub.Tools;

/**
 *
 * @author ZHAO Qi
 * @date 2014-10-11 9:24:34
 * @version 1.6.0
 */
public class DrawDistributionPlot {
    public String outname="result";
    String fastapath;
    public int min=0;
    public int max=3000;
    public int step=300;
    public boolean isgui = false;
    public boolean isunigene = false;

    public DrawDistributionPlot(String fastapath) {
        this.fastapath = fastapath;
    }

    public DrawDistributionPlot(boolean isunigene, String fastapath) {
        RscriptGenerator rg = new RscriptGenerator(isunigene, fastapath, outname);
        String rscriptpath = rg.getRscriptpath();
        String plotpath = rg.getPlotpath();
        Rexe rexe = new Rexe(rscriptpath, true);
        System.out.println("Your plot is located in " + plotpath);

    }

    public void process() {
         RscriptGenerator rg=new RscriptGenerator(isunigene,  fastapath,  min,  max,  step,outname);
        String rscriptpath=rg.getRscriptpath();
        
         Rexe rexe=new Rexe(rscriptpath,true);
        
         
     }
    public static void main(String[] args) {
      new DrawDistributionPlot("F:\\mywork\\project\\yujing\\Trinity.fasta").process();
        System.out.println(System.getenv().get("os.names"));
    }

}
