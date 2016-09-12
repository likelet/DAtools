package pub;



/*
 * 将文件读取到一个ArrayList中
 */


//import .*;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;

/**
 *
 * @author ZHAO Qi
 * @version jdk 1.7.0
 */
public class RmReplicate {
    private HashSet<String> list;
    private ArrayList<String> alist;

    public HashSet<String> getList() {
        return list;
    }
    
    public RmReplicate() {
    }
    
    public void rmReplicate(String inputfile) throws IOException{
        list = new HashSet<String>();
        this.alist=new ArrayList<String>();
        try {            
            BufferedReader br = new BufferedReader(new FileReader(new File(inputfile)));
            //FileWriter fw = new FileWriter(outputFile);
            while (br.ready()) {
                //String str = br.readLine().trim();
                //System.out.println(str);
                if(list.add(br.readLine().trim()))
                    alist.add(br.readLine().trim());
            }
            //fw.flush();
            br.close();
        } catch (FileNotFoundException ex) {
            
        }
        //alist=alist1;
        //return list;
    }
    public void writeFile(String fileinput ,String fileoutname) throws IOException{
        this.rmReplicate(fileinput);
        try {
            FileWriter fw = new FileWriter(fileoutname);
            for (Iterator it = alist.iterator(); it.hasNext();) {
                fw.append(it.next().toString()+"\n");
            }
            fw.flush();
        } catch (FileNotFoundException ex) {
            
        }
    }
    public static void main(String arg[]) throws IOException {
        RmReplicate tr= new RmReplicate();
        tr.writeFile("D:\\E\\2012\\20120509\\protein anontation\\humanbackground\\humanAnnotationProteinlist.txt", "D:\\E\\2012\\20120509\\protein anontation\\humanbackground\\humanAnnotationProteinlistM.txt");
        //tr.RmReplicate("GENE.txt");
       
    }
    
}
