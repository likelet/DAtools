/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ExomeSeqAnalysisPipe.snpeffanno;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

/**
 *
 * @author 媛子
 */
public class SnpEffOutputFileReader {
    HashMap<String,ArrayList<StatePosForm>> dataMap=new HashMap();
    SnpEffOutputFileReader(String file){
        try {
            BufferedReader br=new BufferedReader(new java.io.FileReader(new File(file)));
            while(br.ready()){
                String data=br.readLine();
                if(!data.substring(0,1).equals("#")){
                    String[] dataArr=data.split("\t");
                    int position=Integer.parseInt(dataArr[1]);
                    String snpEffect=dataArr[15];
                    String geneName=dataArr[10];
                    ArrayList<StatePosForm> dataList=new ArrayList();
                    //String Exon_ID=dataArr[13];
                    if (!snpEffect.startsWith("UPSTREAM")&&!snpEffect.startsWith("DOWNSTREAM")) {
                        if (!dataMap.containsKey(geneName)) {
                            dataList = new ArrayList();
                            dataList.add(new StatePosForm(snpEffect, position));
                            dataMap.put(geneName, dataList);
                        } else {
                            dataMap.get(geneName).add(new StatePosForm(snpEffect, position));
                        }
                    }
                                     
                }
            }
        } catch (IOException ex) {        
        }        
    }
    
    public HashMap<String,ArrayList<StatePosForm>> getDataMap(){
        return dataMap;
    }
}
