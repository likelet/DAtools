/*
 * 统计blast输出的tab格式中 reads比对基因序列以后 每个基因有多少个reads比对上，其中reads比对在多个基因reads被丢弃掉
 */

package BlastResultProcess;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.logging.Level;
import java.util.logging.Logger;
import pub.FilelistReader;

/**
 *
 * @author ZHAO Qi
 * @date 2015-3-24 15:28:23
 * @version 1.6.0
 */
public class BlastResultAnalysisModule {

    private HashMap<String, ArrayList<TableOut>> subject2querylistmap = new HashMap<String, ArrayList<TableOut>>();

    //eval is threshold of filter result
    public BlastResultAnalysisModule(String blasttabfile,String outfile) {
        TableOutputParser top=new TableOutputParser(blasttabfile);
        System.out.println(top.getTableoutList().size());
        System.out.println(top.getReadsFrequency().size());
        this.SummaryQuerycountBySubjects(top.getTableoutList(), top.getReadsFrequency(), 0);
        this.WriteOut(outfile);
    }

    
    
    
    public void SummaryQuerycountBySubjects(ArrayList<TableOut> tablelist, HashMap<String,Integer> readsFrequency,double eval) {
        HashSet nameset = new HashSet();
        for (int i = 0; i < tablelist.size(); i++) {
            TableOut tb = tablelist.get(i);
//            System.out.println(tb.toString());
            if (readsFrequency.get(tb.getQueryId())==1) {
                if (nameset.add(tb.getSubjectId())) {
                    ArrayList<TableOut> templist = new ArrayList<TableOut>();
                    templist.add(tb);
                    this.subject2querylistmap.put(tb.getSubjectId(), templist);
                } else {
                    this.subject2querylistmap.get(tb.getSubjectId()).add(tb);
                }
            }
        }
    }
    
    public void WriteOut(String output){
        try {
            FileWriter fw = new FileWriter(output);
            for (Iterator it = subject2querylistmap.keySet().iterator(); it.hasNext();) {
                String tempstr=(String) it.next();
                fw.append(tempstr+"\t"+subject2querylistmap.get(tempstr).size()+"\r\n");
        }
            fw.flush();
            fw.close();
        } catch (IOException ex) {
          
        }
        
    }
    
    
    
    
    
    
    
    public static void main(String[] args) {
        File[] Filelist=FilelistReader.getFileList("F:\\resouces\\projects\\zengmusheng\\BlastAnalysis\\爱建");
        for (int i = 0; i < Filelist.length; i++) {
            if(Filelist[i].getName().endsWith("266_1_ebv.txt")){
                String filepath=Filelist[i].getAbsolutePath();
                System.out.println(filepath);
                System.out.println(filepath+".out");
                new BlastResultAnalysisModule(filepath,filepath+".out");
    
            }
        }
    }
}
