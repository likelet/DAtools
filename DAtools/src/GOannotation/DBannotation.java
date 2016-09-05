/*
 * this class read database and return a list which contains each DBterm,and term number
 */
package GOannotation;

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
 * @version jdk 1.7.0
 */
public class DBannotation {
    private  HashMap<String, ArrayList> genenameTOgolistMap=new HashMap<String, ArrayList>();
    private  HashMap<String, ArrayList> goTOgenelistMap=new HashMap<String, ArrayList>();
    private HashSet<String> golist=new HashSet<String>();
    private HashSet<String> namelist = new HashSet<String>();
    private ArrayList<DBterm> annotationlist = new ArrayList<DBterm>();
    private int backgroundNumber=0;
    
    public DBannotation(String annotationfile,int nametype) throws IOException{
        this.DBreader(annotationfile,nametype);
    }
    
    public void DBreader(String annotationfile,int nametype) throws IOException{
        
        //HashSet<String> namelist = new HashSet<String>();
        
        String [] str;
                  
            BufferedReader br = new BufferedReader(new FileReader(new File(annotationfile)));
           
            while (br.ready()) {
                //String str1 = br.readLine();
                String str1 = br.readLine().trim();
                //read annotationfiles without first line
                if(!str1.startsWith("!")){
                    str=str1.split("\t");
                    if (str.length>2) {
                        DBterm dt=new DBterm(str[0],str[1],str[2],str[3],str[4],str[5],str[6],str[7],str[8],str[9],str[10],str[11],str[12],str[13],str[14]);
                        //初始化gene2golistMap
                        if (namelist.add(str[nametype])) {
                            ArrayList<String> gotemplist=new ArrayList<String>();
                            gotemplist.add(str[4]);
                            genenameTOgolistMap.put(str[nametype], gotemplist);
                        }else{
                            if(!genenameTOgolistMap.get(str[nametype]).contains(str[4])){
                            genenameTOgolistMap.get(str[nametype]).add(str[4]);
                            }
                        }
                        //初始化go2gonelist MAP
                        if(golist.add(str[4]))
                            {
                            ArrayList<String> genelist=new ArrayList<String>();
                            genelist.add(str[nametype]);
                            goTOgenelistMap.put(str[4], genelist);
                        }else{
                            if(!goTOgenelistMap.get(str[4]).contains(str[nametype])){
                            goTOgenelistMap.get(str[4]).add(str[nametype]);
                            }
                        }
                        //namelist.add(str[2]);
                    annotationlist.add(dt);
                    }else{
                        //用来读取较为简单两列的注释文件：两列分别为gene name 和goID
                        DBterm dt=new DBterm(str[0],str[1]);
                    //初始化gene2golistMap
                        if (namelist.add(str[0])) {
                            ArrayList<String> gotemplist=new ArrayList<String>();
                            gotemplist.add(str[1]);
                            genenameTOgolistMap.put(str[0], gotemplist);
                        }else{
                            genenameTOgolistMap.get(str[0]).add(str[1]);
                        }
                        //初始化go2gonelist MAP
                        if(golist.add(str[1]))
                            {
                            ArrayList<String> genelist=new ArrayList<String>();
                            genelist.add(str[0]);
                            goTOgenelistMap.put(str[1], genelist);
                        }else{
                            goTOgenelistMap.get(str[1]).add(str[0]);
                        }
                    annotationlist.add(dt);
                    }
                    //DBterm dt=new DBterm(str[0],str[3]);
                    
                    
                    //genenameTOgoMap.put(str[2],str[4]);
                    //backgroundNumber++;
                }       
            }
            backgroundNumber=namelist.size();

            System.out.println(backgroundNumber);
            br.close();
       
        //return list;
    }

    
 
    
    public int getBackgroundNumber() {
        
        return backgroundNumber;
    }

    /*public HashMap<String, String> getGenenameTOgoMap() {
        return genenameTOgoMap;
    }
     * 
     */

    public ArrayList<DBterm> getAnnotationlist() {
        return annotationlist;
    }

    public HashMap<String, ArrayList> getGenenameTOgolistMap() {
        return genenameTOgolistMap;
    }

    public HashMap<String, ArrayList> getGoTOgenelistMap() {
        return goTOgenelistMap;
    }

    public HashSet<String> getGolist() {
        return golist;
    }

    public HashSet<String> getNamelist() {
        return namelist;
    }
    
    
    
    public static void main(String arg[]) throws IOException {
        DBannotation db=new DBannotation("F:\\database\\GO\\human\\gene_association.goa_human",1);
        //ArrayList list=new DBannotation("F:\\database\\GO\\human\\gene_association.goa_human").annotationlist;
        System.out.println(db.genenameTOgolistMap.size());
        System.out.println(db.goTOgenelistMap.size());
    
//        for (int i = 0; i < list.size(); i++) {
//            DBterm dt=(DBterm) list.get(i);
//            System.out.println(dt.getDB_Object_Symbol()+"\t"+dt.getGO_ID()+"\t");     
//        }
        
    }
}
