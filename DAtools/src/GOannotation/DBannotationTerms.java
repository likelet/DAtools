/*
 * 以term为标签统计每个term下基因的个数和基因
 */
package GOannotation;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;

/**
 *
 * @author ZHAO Qi
 * @version jdk 1.7.0
 */
public class DBannotationTerms {
    private ArrayList<ArrayList<String>> termlist=new ArrayList<ArrayList<String>>();
    private HashSet<String> golist =new HashSet<String>();
    private HashMap<String,Integer> goTOgenesMap=new HashMap<String,Integer>() ;
    private ArrayList<Integer> geneNumberlist=new ArrayList<Integer> ();
    
    public DBannotationTerms(HashSet<Aterm> list){
        for (Iterator it = list.iterator(); it.hasNext();) {
            Aterm op=(Aterm) it.next();
            if(golist.add(op.getGO_ID())){
                ArrayList<String> templist = new ArrayList<String>();
                templist.add(op.getDB_Object_Symbol());
                termlist.add(templist);
                goTOgenesMap.put(op.getGO_ID(), termlist.size()-1);
            }else{
                termlist.get(goTOgenesMap.get(op.getGO_ID())).add(op.getDB_Object_Symbol());
            }
        }    
    
    
   //计算每个term里面的基因的数据
        if(termlist!=null){
            for (Iterator it = golist.iterator(); it.hasNext();) {
                String str=(String) it.next();
                int tempnumber=termlist.get(goTOgenesMap.get(str)).size();
                geneNumberlist.add(tempnumber);
            }
        }
    }

    public ArrayList<Integer> getGeneNumberlist() {
        return geneNumberlist;
    }

    public HashMap<String, Integer> getGoTOgenesMap() {
        return goTOgenesMap;
    }

    public HashSet<String> getGolist() {
        return golist;
    }

    public ArrayList<ArrayList<String>> getTermlist() {
        return termlist;
    }
    
    
    
    public static void main(String arg[]) throws IOException {
        HashSet<Aterm> temhs=loadingDB.loadingDB("","",2);
        DBannotationTerms dbat=new DBannotationTerms(temhs);
        for (Iterator it = dbat.getGolist().iterator(); it.hasNext();) {
            String gostr=(String) it.next();
            System.out.print(gostr+"\t");
            ArrayList<String> templist = dbat.getTermlist().get(dbat.getGoTOgenesMap().get(gostr));
            System.out.print(templist.size()+"\n");
            for (int i = 0; i < templist.size(); i++) {
                System.out.print(templist.get(i)+";");
            }
            System.out.println();
        }
    }
}
