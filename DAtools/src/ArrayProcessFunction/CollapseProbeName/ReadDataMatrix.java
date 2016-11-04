/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package ArrayProcessFunction.CollapseProbeName;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Administrator
 * @since 2016-11-3
 * @coding time 15:05:23
 * @author Qi Zhao
 */
public class ReadDataMatrix {
    private  HashMap<String, ArrayList<String>> dataTableMap = new HashMap<String, ArrayList<String>>();
    private String headername;
    private String file;

    public ReadDataMatrix(String file) {
        this.file = file;
        this.process();
    }
    
    
    public void process() {
        BufferedReader br = null;
        try {
            br = new BufferedReader(new java.io.FileReader(new File(file)));
            //header information kept
            if(br.ready()){
               headername= br.readLine();
            }
            System.out.println("Parsing data file " +file);
            // run parsing steps
            while (br.ready()) {
                String[] dataStr = br.readLine().trim().split("\t");
                ArrayList<String> tempa=new ArrayList<String>();
                for (int i = 1; i < dataStr.length; i++) {
                    tempa.add(dataStr[i]);
                }
                dataTableMap.put(dataStr[0], tempa);
                
            }
        } catch (FileNotFoundException ex) {
            Logger.getLogger(ReadDataMatrix.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(ReadDataMatrix.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                br.close();
            } catch (IOException ex) {
                Logger.getLogger(ReadDataMatrix.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        System.out.println("Parsing finished! " + file);
    }

    public HashMap<String, ArrayList<String>> getDataTableMap() {
        return dataTableMap;
    }

    public String getHeadername() {
        return headername;
    }


}
