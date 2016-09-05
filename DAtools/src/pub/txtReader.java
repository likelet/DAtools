package pub;



/*
 * 将文件读取到一个ArrayList中
 */


import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;

/**
 *
 * @author ZHAO Qi
 * @version jdk 1.7.0
 */
public class txtReader {
    public ArrayList readBase(String inputfile) throws IOException{
        ArrayList list = new ArrayList();
        String str;
        try {            
            BufferedReader br = new BufferedReader(new FileReader(new File(inputfile)));
            //FileWriter fw = new FileWriter(outputFile);
            while (br.ready()) {
                str = br.readLine();
                //System.out.println(str);
                list.add(str);
            }
            //fw.flush();
            br.close();
        } catch (FileNotFoundException ex) {

        }
        return list;
    }
    public static ArrayList readID(String IDlist) throws IOException{
        ArrayList<String> list = new ArrayList<String>();          
            BufferedReader br = new BufferedReader(new FileReader(new File(IDlist)));
            //FileWriter fw = new FileWriter(outputFile);
            while (br.ready()) {
                String str = br.readLine();
                list.add(str);
            }
            //fw.flush();
            br.close();
       
        return list;
    }
    
    //MAPid在total中的位置放回对应total中的item
    
    public void Map(String input,String IDlist, String fileout) throws IOException{
        ArrayList<String> idlist=this.readID(IDlist);
        ArrayList<String> inputlist=this.readBase(input); 
        //System.out.println(inputlist.size());
        //System.out.println(idlist.size());
        FileWriter fw = new FileWriter(new File(fileout));
        
        
        String str1="";
        int count=0;
        //String[] tempa=new String[5];
        for (Iterator it = idlist.iterator(); it.hasNext();) {
            str1=(String) it.next();
            //System.out.println(str1);
            fw.append(str1);
            str1=str1.split("\t")[0];
            for (Iterator it2 = inputlist.iterator(); it2.hasNext();) {
                String tempstr=(String) it2.next();
                //tempa=tempstr.split("\t");
                //System.out.println(tempstr);
                //if(tempstr.split("\t")[0].equals(str1)||tempstr.split("\t")[1].equals(str1))
                if(tempstr.startsWith(str1+"\t")){
                    count=1;
                    //System.out.println(tempstr);
                    //fw.append(tempstr.split("\t")[0] +"\\\\");
                    fw.append("\t"+tempstr) ;
                    break;
                }
                
            }
            if(count==0){
                fw.append("\tnot mapped");
                //System.out.println(str1+"\tnot mapped");
            }
            count=0;
            fw.append("\r\n");
        }
        fw.close();
    }
    
    //取出pubmedID
    public void pubmedReader(String file) throws IOException{
        ArrayList Alist=txtReader.readID(file);
        for (Iterator it = Alist.iterator(); it.hasNext();) {
            String tempstr=(String)it.next();
            String[] a=tempstr.split(";\\s");
            System.out.print(a[0]+"\t");
            for (int i = 0; i < a.length; i++) {
                String[] a1=a[i].split(";");
                for (int j = 0; j < a1.length; j++) {
                    if(a1[j].startsWith("PubMed")){
                    String  tempstr2=a1[j];
                        System.out.print(tempstr2+";");
                    
                    }
                }
            }
            System.out.println();
                    
        }
    }
    
    public static void fastaEnter(String fileinput, String fileoutput) throws FileNotFoundException, IOException{
        BufferedReader br = new BufferedReader(new FileReader(new File(fileinput))); 
        
        FileWriter fw = new FileWriter(new File(fileoutput));
      
        while (br.ready()) {
            String tempstr=br.readLine();
            if(tempstr.startsWith(">")) {
                fw.append(tempstr+"\r");
            }else{
                while(tempstr.length()>80){
                    fw.append(tempstr.subSequence(0, 80)+"\r");
                    tempstr=tempstr.substring(80);
                }
                if (tempstr.length()<81) {
                    fw.append(tempstr);
                }
                fw.append("\r");
            }
            
        }
        br.close();
          fw.close();
    }
    
    public static HashMap<String, String> getMap(String mapfile) throws FileNotFoundException, IOException{
        HashMap map=new HashMap();
        BufferedReader br = new BufferedReader(new FileReader(new File(mapfile)));
        while (br.ready()) {  
            String[] tempstr =br.readLine().split("\t");
            map.put(tempstr[0], tempstr[1]);
        }
        br.close();
          
        return map;
      
    }
    public static void main(String arg[]) throws IOException {
        //txtReader tr=new txtReader();
        //tr.Map("D:\\mywork\\2012\\20120615\\分析结果\\heatmap\\Cell Death and Cell Cycle\\total.txt","D:\\mywork\\2012\\20120717\\Ubiquinone Biosynthesis\\Ubiquinone Biosynthesis.txt");
        //txtReader.fastaEnter("D:\\mywork\\project\\秦老师数据库项目\\process\\outputPfasta.txt", "D:\\testP.txt");
        txtReader tr=new txtReader();
        tr.Map("F:\\database\\human-HG19\\ncRNA\\humanlncRNAlength.gtf", "F:\\database\\human-HG19\\ncRNA\\testid.txt", "F:\\database\\human-HG19\\ncRNA\\result.txt");
    }
}
