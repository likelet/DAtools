/*
 * loading database of defferent species
 */
package GOannotation;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;

/**
 *
 * @author ZHAO Qi
 * @version jdk 1.7.0
 */
public class loadingDB {
    public static HashSet<Aterm> loadingDB(String dbannotationfile,String obobasefile,int nametype) throws IOException{
        Obobase obobase=new Obobase(obobasefile);
        DBannotation dbannotation=new DBannotation(dbannotationfile,nametype);
         ArrayList<DBterm> annotationlist=dbannotation.getAnnotationlist();
        //ArrayList<Oboterm> obotermlist=obobase.getObolist();
        HashMap goTOtermMAP=obobase.getGoTOtermMap();
        HashSet<Aterm> list=new HashSet<Aterm>();
        
        
        for (int i = 0; i < annotationlist.size(); i++) {
            Oboterm ot= (Oboterm) goTOtermMAP.get(annotationlist.get(i).getGO_ID());
            //Aterm tempop=new Aterm(annotationlist.get(i).getDB(),annotationlist.get(i).getDB_Object_ID(),annotationlist.get(i).getDB_Object_Symbol(),annotationlist.get(i).getGO_ID(),annotationlist.get(i).getAspect(),ot.getName(),ot.getNamespace());
            //tempstr=annotationlist.get(i).toStringSimple()+"\t"+ot.getName()+"\t"+ot.getNamespace();
            Aterm tempop=new Aterm(annotationlist.get(i).getDB_Object_Symbol(),annotationlist.get(i).getGO_ID());
            list.add(tempop);
        }     
        return list;   
    }
}
