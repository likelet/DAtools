/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package BlastResultProcess;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;

/**
 *
 * @author ZHAO Qi
 * @date 2015-3-24 15:15:37
 * @version 1.6.0
 */
public class TableOutputParser {

    private  ArrayList<TableOut> tableoutList = new ArrayList<TableOut>();
    private HashMap<String,Integer> readsFrequency=new HashMap<String,Integer>();

    public TableOutputParser(String blastoutputfile) {
        this.getTableOutListFromFile(blastoutputfile);
    }
    
    
    
    
    public static TableOut getTableOutbyStr(String str) {
        String[] tempstr = str.split("\t");
        TableOut tb = new TableOut(tempstr[0], tempstr[1], Double.parseDouble(tempstr[2]), Double.parseDouble(tempstr[3]), Double.parseDouble(tempstr[4]), Double.parseDouble(tempstr[5]), Double.parseDouble(tempstr[6]), Double.parseDouble(tempstr[7]), Double.parseDouble(tempstr[8]), Double.parseDouble(tempstr[9]), Double.parseDouble(tempstr[10]), Double.parseDouble(tempstr[11]));
        return tb;
    }

    
    
    public void getTableOutListFromFile(String blastoutputfile) {
        HashSet<String> nameset=new HashSet<String>();
        BufferedReader br = null;
        try {
            br = new BufferedReader(new FileReader(new File(blastoutputfile)));
            String str="";
            while (br.ready()) {
               TableOut tb=  TableOutputParser.getTableOutbyStr(br.readLine());
               tableoutList.add(tb);
               if(nameset.add(tb.getQueryId())){
                   readsFrequency.put(tb.getQueryId(), 1);
               }else{
                   readsFrequency.put(tb.getQueryId(), 2);
               }
            }
            br.close();

        } catch (FileNotFoundException ex) {
            System.out.println(blastoutputfile+" is not found! please check your filepath ");
        } catch (IOException ex) {
            System.out.println("IO error");
        }
    }

    public ArrayList<TableOut> getTableoutList() {
        return tableoutList;
    }

    public HashMap<String, Integer> getReadsFrequency() {
        return readsFrequency;
    }

    
    
}
