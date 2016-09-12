package pub;

/*
 * get overlap of two string list
 */


import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author ZHAO Qi
 * @date 2014-3-6 10:22:40
 * @version 1.6.0
 */
public class GetOverlap {
    private  ArrayList<String> list1;
    private ArrayList<String> list2;
    private  ArrayList<String> overlap=new ArrayList<String>();
    private  ArrayList uniqueAlist=new ArrayList<String>();
    private ArrayList uniqueBlist=new ArrayList<String>();

    public GetOverlap(String file1, String file2) throws IOException {
        this.list1 = txtReader.readID(file1);
        this.list2 = txtReader.readID(file2);
//        System.out.println(list1.size());
//        System.out.println(list2.size());
        this.getoverlap();
    }
    
    
    
    public void  getoverlap(){
        
        for (int i = 0; i < list1.size(); i++) {
            String tempstr=list1.get(i);
            if(list2.contains(tempstr)){
                overlap.add(tempstr);
            }else{
                uniqueAlist.add(tempstr);
            }
        }
        
        for (int i = 0; i < list2.size(); i++) {
            String tempstr=list2.get(i);
             if(!overlap.contains(tempstr)){
              
                uniqueBlist.add(tempstr);
            }
        }
//        System.out.println(overlap.size());
//         System.out.println(uniqueBlist.size());
//         System.out.println(uniqueAlist.size());
    }
    
    public void print(String file){
        try {
            FileWriter fw = new FileWriter(file);
            for (int i = 0; i < overlap.size(); i++) {
                fw.append(overlap.get(i)+"\r\n");
            }
            fw.flush();
            fw.close();
        } catch (IOException ex) {
            Logger.getLogger(GetOverlap.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    public void print(String overfile, String uniquefile1, String uniquefile2){
        try {
            FileWriter fw = new FileWriter(overfile);
            for (int i = 0; i < overlap.size(); i++) {
                fw.append(overlap.get(i)+"\r\n");
            }
            fw.flush();
            fw.close();
             FileWriter fw2 = new FileWriter(uniquefile1);
            for (int i = 0; i < uniqueAlist.size(); i++) {
                fw2.append(uniqueAlist.get(i)+"\r\n");
            }
            fw2.flush();
            fw2.close();
             FileWriter fw3 = new FileWriter(uniquefile2);
            for (int i = 0; i < uniqueBlist.size(); i++) {
                fw3.append(uniqueBlist.get(i)+"\r\n");
            }
           
            fw3.flush();
            fw3.close();
        } catch (IOException ex) {
            Logger.getLogger(GetOverlap.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public static void main(String[] args) {
        try {
            new GetOverlap("G:\\temp\\file1.txt", "G:\\temp\\file2.txt").print("G:\\temp\\out1", "G:\\temp\\result1.txt", "G:\\temp\\result2.txt");
        } catch (IOException ex) {
            Logger.getLogger(GetOverlap.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
   
}
