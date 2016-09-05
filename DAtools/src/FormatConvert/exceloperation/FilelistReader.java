package FormatConvert.exceloperation;


import java.io.File;

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
}
