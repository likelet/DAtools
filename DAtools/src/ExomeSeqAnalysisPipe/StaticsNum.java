/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ExomeSeqAnalysisPipe;


import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.Set;

/**
 *
 * @author ZhaoQi
 */
public class StaticsNum {  
    StaticsNum(String file) throws IOException {
        HashMap<String,Integer> datamap=new HashMap();
        LinkedList<String> poslist=new FilelistSum().getLinkedList();        
        FileWriter fw=new FileWriter(file);
        fw.append("Pos"+"\t"+"Num"+"\n");
        String pos=""; int num=0;
        for(Iterator<String> it=poslist.iterator();it.hasNext();){
            pos=it.next();
            if(datamap.containsKey(pos)){
                num=datamap.get(pos)+1;
            }else{
                num=1;
            }
            datamap.put(pos, num);            
        }
        LinkedList<String> keylist=new LinkedList();
        for(Iterator<String> it=datamap.keySet().iterator();it.hasNext();){
            String key=it.next();
            keylist.add(key);
        }
        Collections.sort(keylist,new Comparedata());
        for(int i=0;i<keylist.size();i++){
            fw.write(keylist.get(i)+"\t"+datamap.get(keylist.get(i)).intValue()+"\n");
        }
        fw.flush();
        fw.close();
      }   
}
