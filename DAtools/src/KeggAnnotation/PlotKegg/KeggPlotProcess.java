/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package KeggAnnotation.PlotKegg;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;

/**
 *
 * @author ZHAO Qi
 * @date 2014-10-30 11:01:12
 * @version 1.6.0
 */
public class KeggPlotProcess {

    public KeggPlotProcess(String kegglogfile,String geneExressionFile,String keggmapdir,String outmapDir) {
        HashMap<String, ArrayList<mapitem>> map2mapitem=new Keggmapfile(kegglogfile).getMap2mapitem();
      HashMap<String, geneExpres> map=new geneExpressionfileReader(geneExressionFile).getMap();
        System.out.println("Please wait a few minutes, this process may take some time.");
        try {
//            this.paintmap(map, map2mapitem,keggmapdir, outmapDir);
            
            this.painthtml(map, map2mapitem,keggmapdir, outmapDir);
        } catch (FileNotFoundException ex) {
            Logger.getLogger(KeggPlotProcess.class.getName()).log(Level.SEVERE, "The output dir should be existed", ex);
        }
    }

    
    
    
    
    public void paintmap(HashMap<String, geneExpres> map, HashMap<String, ArrayList<mapitem>> map2mapitem, String mappath, String outpath) throws FileNotFoundException {
        ArrayList tempset = null;

        for (Iterator it = map2mapitem.keySet().iterator(); it.hasNext();) {
            String mapid = (String) it.next();
            BufferedImage bimg;
            try {
                bimg = ImageIO.read(new FileInputStream(mappath + "\\" + mapid + ".png"));
                Graphics2D g2d = (Graphics2D) bimg.getGraphics();
                tempset = map2mapitem.get(mapid);
                for (Iterator it2 = tempset.iterator(); it2.hasNext();) {
                    mapitem mt = (mapitem) it2.next();
                    this.plotsingle(mt, g2d, map);
                }
                ImageIO.write(bimg, "PNG", new FileOutputStream(outpath + "//" + mapid + "_modified.png"));

            } catch (IOException ex) {
                System.out.println(mapid + " is not exists");
            }
//得到Graphics2D 对象

        }
    }

    public void painthtml(HashMap<String, geneExpres> map, HashMap<String, ArrayList<mapitem>> map2mapitem, String mappath, String outpath) throws FileNotFoundException {
        ArrayList tempset = null;

        for (Iterator it = map2mapitem.keySet().iterator(); it.hasNext();) {
            String mapid = (String) it.next();
            try {

                FileWriter fw = new FileWriter(outpath + "//" + mapid + "_modified.html");
                fw.append("<html>\r\n");
                fw.append("<head>\r\n");
                fw.append("<meta http-equiv=\"content-type\" content=\"text/html; charset=utf-8\">\r\n");
                fw.append("<title>map00010</title>\r\n");
                fw.append("<style type=\"text/css\">\r\n");
                fw.append("<!--\r\n");
                fw.append("area {cursor: pointer;}\r\n");
                fw.append("-->\r\n");
                fw.append("</style>\r\n");
                fw.append("<script type=\"text/javascript\">\r\n");
                fw.append("<!--\r\n");
                fw.append("function showInfo(info) {\r\n");
                fw.append("	obj = document.getElementById(\"result\");\r\n");
                fw.append("	obj.innerHTML = \"<div style='cursor: pointer; position: absolute; right: 5px; color: #000;' onclick='javascript: document.getElementById(\\\"result\\\").style.display = \\\"none\\\";' title='close'>X</div>\" + info;\r\n");
                fw.append("	obj.style.top = document.body.scrollTop;\r\n");
                fw.append("	obj.style.left = document.body.scrollLeft;\r\n");
                fw.append("	obj.style.display = \"\";\r\n");
                fw.append("}\r\n");
                fw.append("//->\r\n");
                fw.append("</script>\r\n");
                fw.append("</head>\r\n");
                fw.append("<body>\r\n");
                fw.append("<map name=\""+mapid+"\">\r\n");

                tempset = map2mapitem.get(mapid);
                for (Iterator it2 = tempset.iterator(); it2.hasNext();) {
                    mapitem mt = (mapitem) it2.next();
                    fw.append(this.generateHtmlSinlge(mt, map));
                    fw.append("\r\n");
                }
                fw.append("</map>\r\n");
                fw.append("<center><img src='./"+mapid + "_modified.png' usemap='#"+mapid+"' /></center>\r\n");
                fw.append("<div id='result' style='display: none; position: absolute; width: 50%; border: 1px solid #000; background-color: #fff; filter: alpha(opacity=95); opacity: 0.95; font-size: 12px; padding-right: 20px;' onmouseover=\"javascript: this.style.filter = 'alpha(opacity=100)'; this.style.opacity = 1;\" onmouseout=\"javascript: this.style.filter = 'alpha(opacity=95)'; this.style.opacity = 0.95;\"></div>\r\n");
                fw.append("</body></html>");
                fw.close();

            } catch (IOException ex) {
                System.out.println(mapid + " is not exists");
            }
//得到Graphics2D 对象

        }
    }
    
    
    public void plotsingle(mapitem mt, Graphics2D g2d, HashMap<String, geneExpres> map) {
        ArrayList<String> genelist = mt.getGenelist();
        int[] pos = mt.getPositionlist();

        g2d.setStroke(new BasicStroke(3));
//绘制图案或文字
       int status=getcolorstatus(genelist, map);
        if (status == 0) {
            g2d.setColor(Color.blue);
            g2d.drawRect(pos[0], pos[1], pos[2] - pos[0], pos[3] - pos[1]);
        } else if (status == -1) {
            g2d.setColor(Color.GREEN);
            g2d.drawRect(pos[0], pos[1], pos[2] - pos[0], pos[3] - pos[1]);
        } else if (status == 1) {
            g2d.setColor(Color.red);
            g2d.drawRect(pos[0], pos[1], pos[2] - pos[0], pos[3] - pos[1]);
        } else {
            g2d.setColor(Color.RED);
            g2d.drawLine(pos[0], pos[1], pos[0], pos[3] - pos[1]);
            g2d.drawLine(pos[0], pos[1], (pos[2] - pos[0]) / 2, pos[1]);
            g2d.drawLine(pos[0], pos[3], (pos[2] - pos[0]) / 2, pos[3]);
            g2d.setColor(Color.GREEN);
            g2d.drawLine((pos[2] - pos[0]) / 2, pos[1], pos[2], pos[1]);
            g2d.drawLine(pos[2], pos[3], (pos[2]), pos[1]);
            g2d.drawLine((pos[2] - pos[0]) / 2, pos[3], pos[2], pos[3]);
        }

    }
    
    
    
    public String generateHtmlSinlge(mapitem mt, HashMap<String, geneExpres> map){
        String temstr="";
        ArrayList<String> genelist = mt.getGenelist();
        String genenames="";
        for (int i = 0; i < genelist.size(); i++) {
            genenames+=genelist.get(i)+",";
        }
        genenames=mt.getKo()+": "+genenames.substring(0,genenames.length()-1);
        int[] pos = mt.getPositionlist();
        int status=getcolorstatus(genelist, map);
        if (status == -1) {
           temstr="<area shape='rect' coords='"+pos[0]+", "+pos[1]+", "+pos[2]+", "+pos[3]+"' onmouseover='javascript: showInfo(\"<ul><li style=\\\"color: green;\\\">Unigene<ul><li>"+genenames+"</li></ul></li></ul>\");' onclick=\" window.open('http://www.genome.jp/dbget-bin/www_bget?pathway+"+mt.getKo()+"')\"/>"; 
        } else if (status == 1) {
            temstr="<area shape='rect' coords='"+pos[0]+", "+pos[1]+", "+pos[2]+", "+pos[3]+"' onmouseover='javascript: showInfo(\"<ul><li style=\\\"color: red;\\\">Unigene<ul><li>"+genenames+"</li></ul></li></ul>\");'  onclick=\" window.open('http://www.genome.jp/dbget-bin/www_bget?pathway+"+mt.getKo()+"')\"/>"; 
        } else{
           temstr="<area shape='rect' coords='"+pos[0]+", "+pos[1]+", "+pos[2]+", "+pos[3]+"' onmouseover='javascript: showInfo(\"<ul><li style=\\\"color: blue;\\\">Unigene<ul><li>"+genenames+"</li></ul></li></ul>\");'  onclick=\" window.open('http://www.genome.jp/dbget-bin/www_bget?pathway+"+mt.getKo()+"')\"/>"; 
        }
           return temstr;
    }
//返回kom内部基因的上下调情况，不上调也不下调0，上调1，下调-1，有上调和有下调2

    public int getcolorstatus(ArrayList<String> genelist, HashMap<String, geneExpres> map) {
        int upmark = 0;
        int downmark = 0;
        for (int i = 0; i < genelist.size(); i++) {
            String tempgene = genelist.get(i);
            if (map.get(tempgene) != null && map.get(tempgene).isIsup()) {
                upmark = 1;
            } else if (map.get(tempgene) != null && !map.get(tempgene).isIsup()) {
                downmark = 1;
            }
        }
        if (upmark + downmark == 0) {
            return 0;
        } else if (upmark + downmark == 2) {
            return 2;
        } else {
            return upmark - downmark;
        }

    }

    public static void main(String[] args) throws FileNotFoundException {
        new KeggPlotProcess("G:\\keggScriptBGI\\result\\kegg.log","G:\\keggScriptBGI\\used_data\\kegg_map_data\\Z4&Z6_DEGene.txt","G:\\keggScriptBGI\\used_data\\map", "G:\\keggScriptBGI\\used_data\\result");
    }
    
    
}
