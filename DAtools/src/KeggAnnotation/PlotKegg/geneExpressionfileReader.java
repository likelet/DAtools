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
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author ZHAO Qi
 * @date 2014-10-30 10:42:32
 * @version 1.6.0
 */
public class geneExpressionfileReader {
    private HashMap<String, geneExpres> map = new HashMap<String, geneExpres>();
    private ArrayList<geneExpres> geneExpresslist =new ArrayList<geneExpres> ();

    public geneExpressionfileReader(String expfile) {
        try {
            this.geneExpresslist=this.geneExpressfileParser(expfile);
        } catch (IOException ex) {
            Logger.getLogger(geneExpressionfileReader.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    
    
    
        public ArrayList<geneExpres> geneExpressfileParser(String expfile) throws FileNotFoundException, IOException{
            ArrayList<geneExpres> genelist=new ArrayList<geneExpres>();
            BufferedReader br = new BufferedReader(new FileReader(new File(expfile)));
            String tempstr="";
            while (br.ready()) {    
                tempstr=br.readLine().trim();
                String[] str=tempstr.split("\t");
                geneExpres ge=new geneExpres(str[0],Double.parseDouble(str[1]));
                genelist.add(ge);
                map.put(str[0], ge);
            }
            br.close();
            return genelist;
        }

    public HashMap<String, geneExpres> getMap() {
        return map;
    }

    public ArrayList<geneExpres> getGeneExpresslist() {
        return geneExpresslist;
    }
     
       
        
        
}
class geneExpres{
    
    private String geneID;
    private double foldchange;
    private double pvalue;  
    private boolean isup;

    
    
    
    public geneExpres(String geneID, double foldchange) {
        this.geneID = geneID;
        this.foldchange = foldchange;
        if(foldchange>0){
            isup=true;
        }else{
            isup=false;
        }
    }

    public geneExpres(String geneID, double foldchange, double pvalue) {
        this.geneID = geneID;
        this.foldchange = foldchange;
        this.pvalue = pvalue;
        if(foldchange>0){
            isup=true;
        }else{
            isup=false;
        }
    }

    
    
    
    /**
     * Get the value of isup
     *
     * @return the value of isup
     */
    public boolean isIsup() {
        return isup;
    }

    /**
     * Set the value of isup
     *
     * @param isup new value of isup
     */
    public void setIsup(boolean isup) {
        this.isup = isup;
    }


    /**
     * Get the value of pvalue
     *
     * @return the value of pvalue
     */
    public double getPvalue() {
        return pvalue;
    }

    /**
     * Set the value of pvalue
     *
     * @param pvalue new value of pvalue
     */
    public void setPvalue(double pvalue) {
        this.pvalue = pvalue;
    }


    /**
     * Get the value of foldchange
     *
     * @return the value of foldchange
     */
    public double getFoldchange() {
        return foldchange;
    }

    /**
     * Set the value of foldchange
     *
     * @param foldchange new value of foldchange
     */
    public void setFoldchange(double foldchange) {
        this.foldchange = foldchange;
    }

    

    /**
     * Get the value of geneID
     *
     * @return the value of geneID
     */
    public String getGeneID() {
        return geneID;
    }

    /**
     * Set the value of geneID
     *
     * @param geneID new value of geneID
     */
    public void setGeneID(String geneID) {
        this.geneID = geneID;
    }

}