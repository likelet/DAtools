/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package GOannotation;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

/**
 *
 * @author ZHAO Qi
 * @version jdk 1.7.0
 */
public final class Obobase {
    private HashMap<String,Oboterm> goTOtermMap=new HashMap<String,Oboterm>();
    private ArrayList<Oboterm> obolist=new ArrayList<Oboterm>();
    
    public Obobase(String obofile) throws IOException{
    this.oboReaderExe(obofile);
}
    public void oboReaderExe(String obofile) throws IOException{
        //ArrayList<Oboterm> termlist = new ArrayList<Oboterm>();
       // Oboterm ot=new Oboterm();
        String str1="";
        int count=0;
        try {            
            BufferedReader br = new BufferedReader(new FileReader(new File(obofile)));
            
            while (br.ready()) {
                String str = br.readLine();
                if(str.equals("[Term]")){
                    Oboterm ot=new Oboterm();
                    str1=br.readLine();
                   while(!str1.equals("")){
                       if(str1.startsWith("id:")){
                           ot.setId(str1.substring(4));
                       }else if(str1.startsWith("name:")){
                           ot.setName(str1.substring(6));
                       }else if(str1.startsWith("namespace:")){
                           ot.setNamespace(str1.substring(11));
                       }else if(str1.startsWith("is_a: ")){
                           ot.setIs_a(str1.substring(6,16)+"\t");
                       }else if(str1.startsWith("is_obsolete:")){
                           ot.setIs_obsolute(str1.substring(13));
                       }else if(str1.startsWith("def")){
                           ot.setDef(str1.split("\"")[1]);
                       }else if(str1.startsWith("relationship:")){
                           ot.setRelationship(str1.substring(14));
                       }
                       
                       str1=br.readLine();
                   }
                   obolist.add(ot);
                   goTOtermMap.put(ot.getId(), ot);
                } 
            }
            br.close();
        } catch (FileNotFoundException ex) {
            ex.printStackTrace();
        }
        //return termlist;
    }

    public HashMap<String, Oboterm> getGoTOtermMap() {
        return goTOtermMap;
    }

    public ArrayList<Oboterm> getObolist() {
        return obolist;
    }
    
    
    
    public void printlist() throws IOException{
        ArrayList list = this.obolist;
        for(int i=0;i<list.size();i++){
            System.out.println(list.get(i).toString());
        }
    }
    public static void main(String arg[]) throws IOException {
        Obobase or=new Obobase("D:\\database\\gene_ontology_ext.obo");
        BufferedReader br = new BufferedReader(new FileReader(new File("G:\\temp\\test\\arabidopsis.txt")));        
        ArrayList idlist=new ArrayList();
        while (br.ready()) {
            idlist.add(br.readLine());
        }
        br.close();
        
        for (int i = 0; i < idlist.size(); i++) {
            Oboterm ot=or.goTOtermMap.get(idlist.get(i));
            System.out.println(ot.getName()+"\t"+ot.getDef());
        }
    }
}
