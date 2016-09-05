/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package VenPlotRscriptGenerator;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.logging.Level;
import java.util.logging.Logger;
import pub.SpecialException;

/**
 *
 * @author ZHAO Qi
 * @date 2014-3-8 22:46:54
 * @version 1.6.0
 */
public class VenPlotRscriptGenerator {

   //private final String[] tittleList;
    private final String title;
    private final String GraphType;//shoud be the type: BMP, JPEG, PNG and TIFF

    public VenPlotRscriptGenerator( String title, String GraphType) {
        //this.tittleList = tittleList;
        if ("null".equals(title)) {
            this.title = "Your Ven Plot";
        } else {
            this.title = title;
        }
        this.GraphType = GraphType;
    }

    public void RscirptGenerator(HashMap<String, ArrayList> datamap, String fileout) {
        FileWriter fw = null;
        try {
           
            String s = "";
            File f=new File(fileout);
            fw = new FileWriter(f);
            s += "library(VennDiagram)\r\n";
            //s+="library(Cairo)\r\n";
            for (Iterator it = datamap.keySet().iterator(); it.hasNext();) {
                String tempstr = (String) it.next();
                ArrayList templist = datamap.get(tempstr);
                s += tempstr + "=c(";
                for (int i = 0; i < templist.size(); i++) {
                    s += "\"" + templist.get(i) + "\",";
                }
                s = s.substring(0, s.length() - 1);
                s += ")\r\n";
            }
            //s += GraphType + "(filename =\"" + f.getParent().replace("\\", "/")+"/test."+GraphType+ "\")\r\n";
            if (datamap.size() == 2) {
                ArrayList arr = new ArrayList();
                for (Iterator it = datamap.keySet().iterator(); it.hasNext();) {
                    String tempstr = (String) it.next();
                    arr.add(tempstr);
                }
                s += "venplot=venn.diagram(list(" + arr.get(0) + "=" + arr.get(0) + "," + arr.get(1) + "=" + arr.get(1) + "),main = \""+title+"\",fill=c(\"red\",\"blue\"),filename=NULL)\r\n";
            } else if (datamap.size() == 3) {
                ArrayList arr = new ArrayList();
                for (Iterator it = datamap.keySet().iterator(); it.hasNext();) {
                    String tempstr = (String) it.next();
                    arr.add(tempstr);
                }
                s += "venplot=venn.diagram(list(" + arr.get(0) + "=" + arr.get(0) + "," + arr.get(1) + "=" + arr.get(1) + "," + arr.get(2) + "=" + arr.get(2) + "),fill=c(\"red\",\"blue\",\"yellow\"),filename=NULL)\r\n";
            } else if (datamap.size() == 4) {
                ArrayList arr = new ArrayList();
                for (Iterator it = datamap.keySet().iterator(); it.hasNext();) {
                    String tempstr = (String) it.next();
                    arr.add(tempstr);
                }
                s += "venplot=venn.diagram(list(" + arr.get(0) + "=" + arr.get(0) + "," + arr.get(1) + "=" + arr.get(1) + "," + arr.get(2) + "=" + arr.get(2) + "," + arr.get(3) + "=" + arr.get(3) + "),fill=c(\"red\",\"blue\",\"yellow\",\"green\"),filename=NULL)\r\n";
            } else {
                ArrayList arr = new ArrayList();
                for (Iterator it = datamap.keySet().iterator(); it.hasNext();) {
                    String tempstr = (String) it.next();
                    arr.add(tempstr);
                }
                s += "venplot=venn.diagram(list(" + arr.get(0) + "=" + arr.get(0) + "," + arr.get(1) + "=" + arr.get(1) + "," + arr.get(2) + "=" + arr.get(2) + "," + arr.get(3) + "=" + arr.get(3) + "," + arr.get(4) + "=" + arr.get(4) + "),fill=c(\"red\",\"blue\",\"yellow\",\"green\",\"purple\"),filename=NULL)\r\n";
            }
            
            s += GraphType + "(filename =\"" + f.getParent().replace("\\", "/")+"/test."+GraphType+ "\",width=600,height=600)\r\n";
            s+="grid.draw(venplot)\r\n";
            s+="dev.off()\r\n";
            
            
           // s+="dev.off()\r\n";
            fw.append(s);
            fw.flush();
            fw.close();
        } catch (IOException ex) {
            Logger.getLogger(VenPlotRscriptGenerator.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                fw.close();
            } catch (IOException ex) {
                Logger.getLogger(VenPlotRscriptGenerator.class.getName()).log(Level.SEVERE, null, ex);
            }
        }

    }
    
    public void RscirptGeneratorLinuxCairo(HashMap<String, ArrayList> datamap, String fileout) {
        FileWriter fw = null;
        try {
           
            String s = "";
            File f=new File(fileout);
            fw = new FileWriter(f);
            s += "library(VennDiagram)\r\n";
            s+="library(Cairo)\r\n";
            for (Iterator it = datamap.keySet().iterator(); it.hasNext();) {
                String tempstr = (String) it.next();
                ArrayList templist = datamap.get(tempstr);
                s += tempstr + "=c(";
                for (int i = 0; i < templist.size(); i++) {
                    s += "\"" + templist.get(i) + "\",";
                }
                s = s.substring(0, s.length() - 1);
                s += ")\r\n";
            }
            //s += GraphType + "(filename =\"" + f.getParent().replace("\\", "/")+"/test."+GraphType+ "\")\r\n";
            if (datamap.size() == 2) {
                ArrayList arr = new ArrayList();
                for (Iterator it = datamap.keySet().iterator(); it.hasNext();) {
                    String tempstr = (String) it.next();
                    arr.add(tempstr);
                }
                s += "venplot=venn.diagram(list(" + arr.get(0) + "=" + arr.get(0) + "," + arr.get(1) + "=" + arr.get(1) + "),main = \""+title+"\",fill=c(\"red\",\"blue\"),filename=NULL)\r\n";
            } else if (datamap.size() == 3) {
                ArrayList arr = new ArrayList();
                for (Iterator it = datamap.keySet().iterator(); it.hasNext();) {
                    String tempstr = (String) it.next();
                    arr.add(tempstr);
                }
                s += "venplot=venn.diagram(list(" + arr.get(0) + "=" + arr.get(0) + "," + arr.get(1) + "=" + arr.get(1) + "," + arr.get(2) + "=" + arr.get(2) + "),fill=c(\"red\",\"blue\",\"yellow\"),filename=NULL)\r\n";
            } else if (datamap.size() == 4) {
                ArrayList arr = new ArrayList();
                for (Iterator it = datamap.keySet().iterator(); it.hasNext();) {
                    String tempstr = (String) it.next();
                    arr.add(tempstr);
                }
                s += "venplot=venn.diagram(list(" + arr.get(0) + "=" + arr.get(0) + "," + arr.get(1) + "=" + arr.get(1) + "," + arr.get(2) + "=" + arr.get(2) + "," + arr.get(3) + "=" + arr.get(3) + "),fill=c(\"red\",\"blue\",\"yellow\",\"green\"),filename=NULL)\r\n";
            } else {
                ArrayList arr = new ArrayList();
                for (Iterator it = datamap.keySet().iterator(); it.hasNext();) {
                    String tempstr = (String) it.next();
                    arr.add(tempstr);
                }
                s += "venplot=venn.diagram(list(" + arr.get(0) + "=" + arr.get(0) + "," + arr.get(1) + "=" + arr.get(1) + "," + arr.get(2) + "=" + arr.get(2) + "," + arr.get(3) + "=" + arr.get(3) + "," + arr.get(4) + "=" + arr.get(4) + "),fill=c(\"red\",\"blue\",\"yellow\",\"green\",\"purple\"),filename=NULL)\r\n";
            }
            
            String cairostr="";
            if(GraphType.equals("jpg")){
                cairostr="CairoJPEG";
            }else if(GraphType.equals("png")){
                 cairostr="CairoPNG";
            }else if(GraphType.equals("tiff")){
                 cairostr="CairoTIFF";
            }else if(GraphType.equals("pdf")){
                 cairostr="CairoPDF";
            }else if(GraphType.equals("svg")){
                 cairostr="CairoSVG";
            }
            s += cairostr + "(filename =\"" + f.getParent().replace("\\", "/")+"/test."+GraphType+ "\",width=480,height=480,bg = \"transparent\")\r\n";
            s+="grid.draw(venplot)\r\n";
            s+="dev.off()\r\n";
            
            
           // s+="dev.off()\r\n";
            fw.append(s);
            fw.flush();
            fw.close();
        } catch (IOException ex) {
            Logger.getLogger(VenPlotRscriptGenerator.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                fw.close();
            } catch (IOException ex) {
                Logger.getLogger(VenPlotRscriptGenerator.class.getName()).log(Level.SEVERE, null, ex);
            }
        }

    }
    
    public static void main(String[] args) throws SpecialException {
        System.out.println(System.getProperty("os.name"));
        VenPlotdDataProcess dpr=new VenPlotdDataProcess("G:\\temp\\test.txt");
        HashMap<String, ArrayList> tempmap=dpr.dataProcess();
        VenPlotRscriptGenerator vpr=new VenPlotRscriptGenerator("null","bmp");
        
        //System.out.println(f.getAbsolutePath());
        vpr.RscirptGenerator(tempmap, "G:\\temp\\test.R");
    }
}
