/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package VenPlotRscriptGenerator;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.logging.Level;
import java.util.logging.Logger;
import pub.Rexe;
import pub.SpecialException;

/**
 *
 * @author ZHAO Qi
 * @date 2014-3-10 14:50:12
 * @version 1.6.0
 */
public class VenPlotConsole {
    public static void plot(String datafile, String maintext, String picType) throws SpecialException {
        try {
            VenPlotdDataProcess dpr = new VenPlotdDataProcess(datafile);//datafile input
            HashMap<String, ArrayList> tempmap = dpr.dataProcess();
            VenPlotRscriptGenerator vpr = new VenPlotRscriptGenerator(maintext, picType);//args[1] refers main txt ;args[2] refers plot type;

            File f = new File(new File(datafile).getAbsolutePath());
            //String temppath=f.getAbsolutePath();
            // File f2=new File(temppath);
            //System.out.println(f2.getParent());

            String RscriptPath = (f.getParent() + "\\test.R").replace("\\", "/");
            File f2 = new File(RscriptPath);
            // f2.deleteOnExit();
            System.out.println(RscriptPath);

            Rexe rexe = new Rexe();
            System.out.println("your systerm is " + System.getProperty("os.name"));
            String sysstr = System.getProperty("os.name");
            if (sysstr.contains("Windows")) {
                vpr.RscirptGenerator(tempmap, RscriptPath);
                rexe.processSingleRscriptWindows(RscriptPath);
            } else {
                vpr.RscirptGeneratorLinuxCairo(tempmap, RscriptPath);
                //rexe.processSingleRscriptLinux(RscriptPath);
            }
           
        } catch (IOException ex) {
            Logger.getLogger(VenPlotConsole.class.getName()).log(Level.SEVERE, null, ex);
        } catch (InterruptedException ex) {
            Logger.getLogger(VenPlotConsole.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }
    public static void main(String[] args) throws SpecialException {
        VenPlotConsole.plot("G:/temp/test.txt", "null", "jpeg");
    }
}
