/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package FastaProcess.FastaLengthDistribution;

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
    
    public DrawDistributionPlot(String fastapath, int min, int max, int step,String outpath,boolean isgui){
        RscriptGenerator rg=new RscriptGenerator(  fastapath,  min,  max,  step, outpath);
        String rscriptpath=rg.getRscriptpath();
        String plotpath=rg.getPlotpath();
         Rexe rexe=new Rexe(rscriptpath,true);
         System.out.println("Your plot is located in "+plotpath);
        if (isgui) {
            try {
                Image image = ImageIO.read(new File(plotpath));

                Tools.WindowCenter(new ImageFrame(image));

            } catch (IOException ex) {
                Logger.getLogger(DrawDistributionPlot.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
     public DrawDistributionPlot(String fastapath, int min, int max, int step,String outpath){
        RscriptGenerator rg=new RscriptGenerator(fastapath,  min,  max,  step, outpath);
        String rscriptpath=rg.getRscriptpath();
        String plotpath=rg.getPlotpath();
         Rexe rexe=new Rexe(rscriptpath,true);
         System.out.println("Your plot is located in "+plotpath);
       
    }
    
    
    public static void main(String[] args) {
      //new DrawDistributionPlot(false,"F:\\mywork\\project\\yujing\\Trinity.fasta",200,3000,200,true);
        new DrawDistributionPlot("F:\\mywork\\project\\H7N9\\data\\aa.fasta",200,3000,200,"F:\\mywork\\project\\H7N9\\data\\plot");
    }

}
