/*
 * according geneNAME to search goterm and function analysis
 */
package GOannotation;

import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author ZHAO Qi
 * @version jdk 1.7.0
 */
public class GOmapping {
    private int count=0;//用来返回genelist中被查到的基因数
    private HashSet totalgenelist;
    private ArrayList unmmaplist=new ArrayList();
    private ArrayList<DBterm> annotationlist ;
    private ArrayList<Oboterm> obotermlist;
    private HashMap goTOtermMAP;
    //保存annotation term 的简单版本
    private HashSet<Aterm> Alist=new HashSet<Aterm>();
    
    private  HashMap<String, ArrayList> goTOgenelistMap;
    
    private  HashMap<String, ArrayList> genenameTOgolistMap;
    
    
    
    //用来保存查询到的gene，根据go映射到genelist的列表
    private HashMap<String, ArrayList> targetGo2genelistMap=new HashMap<String, ArrayList>();
    //用来存查询到goterm的去冗余list，主要用于输出时的参考list
    private HashSet<String> targetGotermlist= new HashSet<String>();
    
    private ArrayList<DBterm> singleresultlist;
    private ArrayList<ArrayList<DBterm>> multyresultlist = new ArrayList<ArrayList<DBterm>>();
    
    public GOmapping(){}
    
    public GOmapping(String obobasefile) throws IOException{
        Obobase obobase=new Obobase(obobasefile);
         this.obotermlist=obobase.getObolist();
        this.goTOtermMAP=obobase.getGoTOtermMap();
    }
    public GOmapping(String dbannotationfile,String obobasefile,int nametype) throws IOException{
        Obobase obobase=new Obobase(obobasefile);
        DBannotation dbannotation=new DBannotation(dbannotationfile,nametype);
        this.annotationlist=dbannotation.getAnnotationlist();
        this.obotermlist=obobase.getObolist();
        this.goTOtermMAP=obobase.getGoTOtermMap();
        this.genenameTOgolistMap=dbannotation.getGenenameTOgolistMap();
        this.goTOgenelistMap=dbannotation.getGoTOgenelistMap();
        this.totalgenelist=dbannotation.getNamelist();
        this.Alist=loadingDB.loadingDB(dbannotationfile, obobasefile, nametype);
        
        System.out.println("goTOtermMAP = "+goTOtermMAP.size());
        System.out.println("genenameTOgolistMap = "+genenameTOgolistMap.size());
        
        //this.batchAnnotation(genelist);
    }

    public  ArrayList<DBterm>singleSearch(String genename){ 
        singleresultlist=new ArrayList<DBterm>(); 
        for (int i = 0; i < annotationlist.size(); i++) {
            if(genename.equals(annotationlist.get(i).getDB_Object_Symbol())){
                singleresultlist.add(annotationlist.get(i));
            }
        }
        return singleresultlist;
    }
    
    //单独查一个id的注释
    public void singleSearchResult(String genename){
        
        if(this.genenameTOgolistMap.get(genename)!=null){
            ArrayList<String> golist=this.genenameTOgolistMap.get(genename);
            System.out.println(genename);
            for (int i = 0; i < golist.size(); i++) {
                System.out.println(golist.get(i)+"\t"+this.go2term(golist.get(i)));
            }
        }else{
            System.out.println("not annoted");
        }
    }
    
    //对genenamelist进行批量注释 并将结果存放在go2genelist 用于后面结果的列出
    public void batchAnnotation(HashSet<String> genenamelist) {
        String genename = "";
        System.out.println("genenamelist.size = "+ genenamelist.size());
        String goterm;
        for (Iterator it = genenamelist.iterator(); it.hasNext();) {
            
        
            genename =(String) it.next();
            if (this.genenameTOgolistMap.get(genename) != null) {
                ArrayList<String> golist = this.genenameTOgolistMap.get(genename);
                //System.out.println(genename);
                
                for (int j = 0; j < golist.size(); j++) {
                    goterm=golist.get(j);
                    if (this.targetGotermlist.add(goterm)) {
                        ArrayList tempgenelist = new ArrayList();
                        tempgenelist.add(genename);
                        this.targetGo2genelistMap.put(goterm, tempgenelist);
                    } else {
                        this.targetGo2genelistMap.get(goterm).add(genename);
                    }
                }
                
            } else {
                this.unmmaplist.add(genename);
            }
            
        }
        
        count=genenamelist.size()-unmmaplist.size();
        System.out.println("targetGo2genelistMap = "+targetGo2genelistMap.size());
        System.out.println("unmaplist = "+unmmaplist.size());
    }
    //批量注释
    public ArrayList<ArrayList<DBterm>> getMultyresultlist(HashSet<String> genenamelist) {
        for (Iterator it = genenamelist.iterator(); it.hasNext();) {
            
            String gene=(String) it.next();
            ArrayList templist=this.singleSearch(gene);
            if(templist.isEmpty()){
                System.out.println("not annotated");
            }else{
                multyresultlist.add(templist);
            }
            
        }
        return multyresultlist;
    }
    
    
    //返回简单版本的批量基因注释的结果
    public HashSet<String> getMultyresultlistSimple(HashSet<String> genenamelist){
        HashSet<String> temphs = new HashSet<String>();
        int mark = 0;
        //System.out.println(genenamelist.size());
        for (Iterator it = genenamelist.iterator(); it.hasNext();) {
            
            String gene=(String) it.next();

            for (Iterator it2 = Alist.iterator(); it2.hasNext();) {
                Aterm tempOP = (Aterm) it2.next();
                if (gene.equals(tempOP.getDB_Object_Symbol())) {
                    mark = 1;
                    //Output tempOP=new Output(str[0],str[1],str[2],str[3],str[4],str[5],str[6]);
                    temphs.add(tempOP.getDB_Object_Symbol()+"\t"+tempOP.getGO_ID());
                }

            }
            if (mark == 1) {
                count++;
            }
            mark = 0;
        }
        return temphs;
    }
    
    
    //返回简单版本的只有个别条目（）基因名、go注释号、和功能注释
    public ArrayList<Aterm> singleSearchSimple(String genename,HashSet<Aterm> list){
        ArrayList<Aterm> resultlist = new ArrayList<Aterm>();
        //String [] str=new String [7];
        for (Iterator it = list.iterator(); it.hasNext();) {
            Aterm tempOP=(Aterm)it.next();
            if(genename.equals(tempOP.getDB_Object_Symbol())){
                //Output tempOP=new Output(str[0],str[1],str[2],str[3],str[4],str[5],str[6]);
                resultlist.add(tempOP);
            }
        }
        return resultlist;
        
    }
    //返回A term 注释版本的文件写出
    public void writeMultyresultlistSimpleFile(String outfile,String idfile){
        try {
            FileWriter fw = new FileWriter(outfile);
            HashSet<String> resultlist= getMultyresultlistSimple(IDlist.IDlist(idfile));
            for (Iterator it = resultlist.iterator(); it.hasNext();) {
                String tm= (String) it.next();
                fw.append(tm+"\r\n");
            }
            fw.flush();
            fw.close();
        } catch (IOException ex) {
            System.out.println("IO error!");
        }
    }

    //返回单个查询的结果，DBterm的形式
    public ArrayList<DBterm> getSingleresultlist() {
        return singleresultlist;
    }

    public int getCount() {
        return count;
    }
    
    
    //返回go对应term的查询结果
    public String go2term(String goid){
       Oboterm ob=(Oboterm) this.goTOtermMAP.get(goid);
        if (ob==null) {
            System.out.println(goid+"\t not find");
            return null;
        }else{
        return ob.getName();
        }
    }

    public HashMap<String, ArrayList> getTargetGo2genelistMap() {
        return targetGo2genelistMap;
    }

    public HashSet<String> getTargetGotermlist() {
        return targetGotermlist;
    }

    public ArrayList getUnmmaplist() {
        return unmmaplist;
    }

    public HashMap<String, ArrayList> getGoTOgenelistMap() {
        return goTOgenelistMap;
    }

    public HashMap getGoTOtermMAP() {
        return goTOtermMAP;
    }

    public HashSet getTotalgenelist() {
        return totalgenelist;
    }
    
    
    
    public static void main(String arg[]) throws IOException {
         
        GOmapping gomap=new GOmapping("D:\\database\\GO\\gene_association.goa_human","D:\\database\\GO\\gene_ontology_ext.obo",2);
        
        gomap.writeMultyresultlistSimpleFile("D:\\annotationResult.txt","D:\\DEGene.txt");
//        HashSet<Aterm> list=loadingDB.loadingDB("E:/database/GO/gene_association/gene_association.tair","E:\\database\\GO\\gene_ontology_ext.obo");
//        //System.out.println(list.size());
//        ArrayList<Aterm> templist =gomap.singleSearchSimple("AT2G05220",list);
//        for (int i = 0; i < templist.size(); i++) {
//            System.out.println(templist.get(i).toString());
//        }
    }
    
}
