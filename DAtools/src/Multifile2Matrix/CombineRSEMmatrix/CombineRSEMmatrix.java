/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package Multifile2Matrix.CombineRSEMmatrix;

import Multifile2Matrix.Multifile2matrix;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Administrator
 * @since 2016-9-27
 * @coding time 17:23:09
 * @author Qi Zhao
 */
public class CombineRSEMmatrix {

    private String dir;
    private String outfile;
    private String col="6";

    public CombineRSEMmatrix(String dir, String outfile) {
        this.dir = dir;
        this.outfile = outfile;
    }

    public void setCol(String col) {
        this.col = col;
    }
   
    
    
    public void process(){
        
         HashMap<String,String> ensemblemap= ReadEnsembleMapfile.getEnsembleMap(new InputStreamReader(this.getClass().getResourceAsStream("/Multifile2Matrix/CombineRSEMmatrix/ensembleGENEmapfile")));
        Multifile2matrix mm=new Multifile2matrix( dir,  "genes.results", outfile);
        mm.setColnumber("6");
        mm.process();
        ArrayList<String> filelist=mm.getFilelist();
        HashSet<String> allIterm=mm.getAllIterm();
        HashMap<String, ArrayList<String>> filehashstr =mm.getFilehashstr();
        
        
        FileWriter fw;
        try {
            fw = new FileWriter(new File(outfile));

            fw.append("ID\tGeneName\tGeneType\t");
            for (Iterator it = filelist.iterator(); it.hasNext();) {
                String tempstr = (String) it.next();
                fw.append(tempstr + "\t");
            }
            fw.append("\r\n");
            for (Iterator it1 = allIterm.iterator(); it1.hasNext();) {
                String rowstr = (String) it1.next();

                String tempstr2 = ensemblemap.get(rowstr);
                for (int i = 0; i < filehashstr.get(rowstr).size(); i++) {
                    tempstr2 = tempstr2 + "\t" + filehashstr.get(rowstr).get(i);
                }
                fw.append(tempstr2 + "\r\n");
            }
            fw.flush();
            fw.close();
        } catch (IOException ex) {
            Logger.getLogger(Multifile2matrix.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }
   

}
