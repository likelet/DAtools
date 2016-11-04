/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package ArrayProcessFunction.CollapseProbeName;

import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Administrator
 * @since 2016-11-3
 * @coding time 15:03:57
 * @author Qi Zhao
 */
public class CollapseMainFunction {
    private HashMap<String,ArrayList<String>> Marker2ProbeMap;
    private  HashMap<String, ArrayList<String>> dataTableMap ;
    private ArrayList<String> markernamelist=new ArrayList<String>();
    private String headername;
    private String outfile;
    private String inputdatafile;
    private String inputMapfile;
    private ArrayList<ArrayList<String>> outlist= new ArrayList<ArrayList<String>>();
    
    private String collapseMethod="mean";
    
    
    public CollapseMainFunction(String inputdatafile, String inputMapfile, String outfile) {
        this.outfile = outfile;
        ReadDataMatrix rm=new ReadDataMatrix(inputdatafile);
        headername=rm.getHeadername();
        dataTableMap=rm.getDataTableMap();
        Marker2ProbeMap=MarkerProbeMap.getMarkerProbeMap(inputMapfile);
        this.process();
        this.writeOut();  
    }
    public void process() {
        for (Iterator it = Marker2ProbeMap.keySet().iterator(); it.hasNext();) {
            String keystr = (String) it.next();
            ArrayList<String> probelist = Marker2ProbeMap.get(keystr);
            ArrayList<ArrayList<String>> sublist = new ArrayList<ArrayList<String>>();

            for (int i = 0; i < probelist.size(); i++) {
                if (dataTableMap.get(probelist.get(i)) != null) {
                    sublist.add(dataTableMap.get(probelist.get(i)));
                }

            }
            System.out.println(sublist.size() );
            if (collapseMethod.equalsIgnoreCase("mean") && sublist.size() != 0) {
                outlist.add(GetCollapseList.getMeanArraylist(sublist));
                markernamelist.add(keystr);
            }
        }
        
    }
    public void writeOut(){
        System.out.println("start writing out ");
        try {
            FileWriter fw = new FileWriter(outfile);
            fw.append(this.headername+"\r\n");
            for (int i = 0; i < outlist.size(); i++) {
                fw.append(markernamelist.get(i)+"\t");
                String str="";
                for (int j = 0; j < outlist.get(i).size(); j++) {
                    str=str+outlist.get(i).get(j)+"\t";
                }
                str=str+"\r\n";
                fw.append(str);
            }
            fw.flush();
            fw.close();
        } catch (IOException ex) {
            Logger.getLogger(CollapseMainFunction.class.getName()).log(Level.SEVERE, null, ex);
        }
        System.out.println("Output done ");
    }
    public static void main(String[] args) {
        new CollapseMainFunction("E:\\百度云同步盘\\resouces\\博后project\\骆卉研\\LIHC paper\\dataset\\analysis\\Datamatrix\\hiseq13_meth_77maker.txt"
        ,"E:\\百度云同步盘\\resouces\\博后project\\骆卉研\\LIHC paper\\dataset\\analysis\\MarkerID\\45markerCorrespondingPanlockIDMap.txt",
        "E:\\百度云同步盘\\resouces\\博后project\\骆卉研\\LIHC paper\\dataset\\analysis\\Datamatrix\\hiseq13_meth_77maker_collapes.txt");
    }
}
