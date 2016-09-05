/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package KeggAnnotation.PlotKegg;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author ZHAO Qi
 * @date 2014-10-30 10:49:13
 * @version 1.6.0
 */
public class Keggmapfile {
    private  HashMap<String,ArrayList<mapitem>> map2mapitem =  new HashMap<String,ArrayList<mapitem>>();
    private ArrayList<mapitem> keggmapConfiglist=new ArrayList<mapitem>();

    public Keggmapfile(String expfile) {
        try {
            this.geneExpressfileParser(expfile);
        } catch (IOException ex) {
            Logger.getLogger(Keggmapfile.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    
    
public void  geneExpressfileParser(String expfile) throws FileNotFoundException, IOException{
            //ArrayList<mapitem> keggmapConfiglist=new ArrayList<mapitem>();
            BufferedReader br = new BufferedReader(new FileReader(new File(expfile)));
            String tempstr="";
            while (br.ready()) {    
                tempstr=br.readLine().trim();
                String[] str=tempstr.split("\t");
                String[] genenames=str[1].split(";");
                ArrayList<String> templist=new ArrayList<String>();
                for (int i = 0; i < genenames.length; i++) {
                    templist.add(genenames[i]);
                }
                
                mapitem ge=new mapitem(str[0],templist,str[2],str[3]);
                keggmapConfiglist.add(ge);
                if(map2mapitem.keySet().contains(str[2])){
                    for (int i = 0; i < templist.size(); i++) {
                        map2mapitem.get(str[2]).add(ge);
                    }
                }else{
                    ArrayList<mapitem> temmaplist=new ArrayList<mapitem> ();
                    temmaplist.add(ge);
                    map2mapitem.put(str[2], temmaplist);
                }
            }
            br.close();
        }

    public HashMap<String, ArrayList<mapitem>> getMap2mapitem() {
        return map2mapitem;
    }

    public ArrayList<mapitem> getKeggmapConfiglist() {
        return keggmapConfiglist;
    }




}

class mapitem{
    
    private String ko;// ko number
    private ArrayList<String> genelist;// mapped genelist
    private String map;//mapID
    private int[] positionlist=new int[4];// position in the map
    

    
    public mapitem(String ko, ArrayList<String> genelist, String map,String position) {
        this.ko = ko;
        this.genelist = genelist;
        this.map = map;
        String[] postionliststr=position.split(",");
        for (int i = 0; i < postionliststr.length; i++) {
            positionlist[i]=Integer.parseInt(postionliststr[i]);
        }
    }

    
    
    
    /**
     * Get the value of ko
     *
     * @return the value of ko
     */
    public String getKo() {
        return ko;
    }

    /**
     * Set the value of ko
     *
     * @param ko new value of ko
     */
    public void setKo(String ko) {
        this.ko = ko;
    }

    public ArrayList<String> getGenelist() {
        return genelist;
    }

    public int[] getPositionlist() {
        return positionlist;
    }

    
    
    
}