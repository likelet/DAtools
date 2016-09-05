/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package speedseqvcf;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.HashMap;

/**
 *
 * @author zhengyueyuan
 */
public class OtherClassify {
    HashMap<String,Integer> otherMap =new HashMap();
    void FileReader(String inputFile){
        try {
            BufferedReader br = new BufferedReader(new java.io.FileReader(inputFile));
            while(br.ready()){
                 String data=br.readLine();
                 if(!data.startsWith("Chr")){
                     String[] dataArr=data.split("\t");
                     String type=dataArr[4];
                     if(!otherMap.containsKey(type)){
                         otherMap.put(type, 1);
                     }else{
                         int temp=otherMap.get(type);
                         otherMap.put(type, temp + 1);
                     }
                 }
             }
            br.close();
        } catch (IOException ex) {
            ex.printStackTrace();
        }        
    }
    
    void Print(){
        for(String key:otherMap.keySet()){
            System.out.println(key+"\t"+otherMap.get(key));
        }
    }
    
    public static void main(String[] args) {
         OtherClassify otherC =new OtherClassify();
         otherC.FileReader("E:\\同步盘\\数据分析\\kidney\\Annotation\\right_DHG02693_annot_oters.txt");
         otherC.Print();
    }
}
