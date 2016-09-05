/*
 * Generator DOG XMLproject file by chr region file,region file and fai file
 * region file
 * -------chr start end------
 * fai file 
 */
package DOGProjectGenerator;

import dataformat.FAIterm;
import dataformat.FAItermReader;
import java.awt.Color;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;

/**
 *
 * @author ZHAO Qi
 * @date 2014-3-31 10:40:22
 * @version 1.6.0
 */
public class XMLgenerator {
     private HashMap<String,FAIterm> Chrom2FAImap=null;
     private String regionfilepath;
     private String parameter;

    public XMLgenerator(String faifile,String regionfilepath, String xmlpath) throws FileNotFoundException, IOException {
        FAItermReader fr=new FAItermReader(faifile);
        this.Chrom2FAImap=fr.getChrom2FAImap();
        System.out.println(this.Chrom2FAImap.size());
        this.regionfilepath = regionfilepath;
        NProject p=projectGenerator();
        NProject2XML n2xml=new NProject2XML(xmlpath);
        n2xml.save(p);
    }
    
    public NProject projectGenerator() throws FileNotFoundException, IOException{
        NProject npj=new NProject(3000,800,(float)1.3,this.NProteinlistGenerator());
        return npj;
    }
    public  LinkedList<NProtein> NProteinlistGenerator() throws FileNotFoundException, IOException{
        LinkedList Nplist=new LinkedList();
        HashMap<String,NProtein> npmap=new HashMap<String,NProtein>();
        String parameter;
        int vertical=100;
        for (Iterator it = Chrom2FAImap.keySet().iterator(); it.hasNext();) {
            String tempChr=(String) it.next();
            FAIterm ft=Chrom2FAImap.get(tempChr);
            parameter="1#&%Hide#&%"+ft.getLength()+"#&%Hide#&%5#&%12#&%Null#&%00000_1.jpg#&%false#&%1#&%0,0,0";
            NProtein np=new NProtein(vertical,0,true, (int) ft.getLength(),parameter,Color.BLACK);
            Nplist.add(np);
            //System.out.println(ft.getChr());
            npmap.put(ft.getChr(), np);
            vertical-=20;
        }
        
        BufferedReader br = new BufferedReader(new FileReader(new File(regionfilepath)));
        while (br.ready()) {   
            String regionstr=br.readLine();
            String[] tempa=regionstr.split("\t");
            vertical=npmap.get(tempa[0]).getVertical();
            NComponent nc =this.readRegion(regionstr, vertical);
            npmap.get(tempa[0]).addComponent(nc);
        }
        br.close();
        
        return Nplist;
        
    }
    
    public NComponent readRegion(String str,int vertical){
        String[] stra=str.split("\t");
        String cparameter="Hide#&%Hide#&% #&%Center#&%20#&%1#&%Null#&%00000_1.jpg";
        int start=Integer.parseInt(stra[1]);
        int end =Integer.parseInt(stra[2]);
        int range=end-start;
        int y=vertical;
        int x=(start+end)/2;
        System.out.println(x+"\t"+y);
        NComponent nc=new NComponent(ComponentEnum.Domain,x,y,cparameter,Color.BLACK,Color.BLACK,false,0);
        nc.shapeType=ShapeEnum.Rectangle;
        nc.setColorText(Color.GREEN);
        nc.setiHigh(20);
        nc.setX(start);
        nc.setX2(end);
        
        return nc;
    }
    
    public static void main(String[] args) throws FileNotFoundException, IOException {
        new XMLgenerator("G:\\temp\\dogxmlGenerator\\ZmB73_RefGen_v2.fa.fai","G:\\temp\\dogxmlGenerator\\M_l.txt","G:\\temp\\dogxmlGenerator\\M_l.xml");
    }
}
