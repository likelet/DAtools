package pub;


import java.io.File;
import java.util.ArrayList;

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */




/**
 *
 * @author Yueyuan
 */
public class FilelistReader {

    public static File[] getFileList(String fileDir){
        
        File f = null;
         f = new File(fileDir);
         
        File[] list = f.listFiles();
        return list;
    }
    
     public static ArrayList<String> getFileArrayList(String fileDir){
        
        File f = null;
         f = new File(fileDir);
         
        File[] list = f.listFiles();
         ArrayList<String> alist=new ArrayList<String>();
         for (int i = 0; i < list.length; i++) {
             alist.add(list[i].getAbsolutePath());
         }
        return alist;
    }
   //filter file with suffix
    public static File[] getFileList(String fileDir,String suffix){
        
        File f = null;
         f = new File(fileDir);
        File[] list = f.listFiles(new MyFilenameFilter(suffix));
        System.out.println("Reading file list number:\t\t"+ list.length);
        return list;
    }
    public static ArrayList<String> getFileArrayList(String fileDir, String suffix) {

        File f = null;
        f = new File(fileDir);
        File[] list = f.listFiles(new MyFilenameFilter(suffix));
        ArrayList<String> alist = new ArrayList<String>();
        for (int i = 0; i < list.length; i++) {
            alist.add(list[i].getAbsolutePath());
        }
        System.out.println("Reading file list number:\t\t" + list.length);
        return alist;
    }
    
    
    //test
    public static void main(String[] args) {
        File[] list=FilelistReader.getFileList("F:\\mywork\\javawork", ".rar");
        for (int i = 0; i < list.length; i++) {
            System.out.println(list[i]);
        }
    }
}
