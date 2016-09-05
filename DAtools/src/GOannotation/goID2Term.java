/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package GOannotation;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

/**
 *
 * @author ZHAO Qi
 * @date 2013-4-10 10:41:51
 * @version 1.6.0
 */
public class goID2Term {
    //根据go的id从obo里面拿对应的term信息
    public static String goID2Term(String goid,Obobase ob){
        if(ob.getGoTOtermMap().get(goid)!=null){
        String name=ob.getGoTOtermMap().get(goid).getName()+"\t"+ob.getGoTOtermMap().get(goid).getDef();
        return name;
        }else{
            return "not mapped";
        }
        
    }
    
    public static ArrayList getTermlistByGOid(ArrayList golist,Obobase ob){
        ArrayList termlist=new ArrayList();
        String name="";
        for (int i = 0; i < golist.size(); i++) {
            name=goID2Term.goID2Term((String)golist.get(i), ob);
            termlist.add(name);
        }
        
        return termlist;
    }
    //query GO discription of a certain GO ID in large scale;
    public static void goID2TermBatch(String obofile, String idfile, String outfile) throws IOException{
        Obobase ob=new Obobase(obofile);
        System.out.println(ob.getObolist().size());
        FileWriter fw = new FileWriter(new File(outfile));
        
        ArrayList golist=new ArrayList();
        String  str="";
        BufferedReader br = new BufferedReader(new FileReader(new File(idfile)));
        while (br.ready()) {     
            str=br.readLine();
            fw.append(str+"\t"+goID2Term.goID2Term(str, ob)+"\r\n");
        }
        br.close();
        fw.close();
    }
    
    public static void main(String[] args) throws FileNotFoundException, IOException {
        goID2Term.goID2TermBatch("F:\\database\\GO\\gene_ontology_ext.obo", "G:\\temp\\goterm.txt", "G:\\temp\\gotermM.txt");
       
    }
}
