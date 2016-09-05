/*
 * change Filename By mapfile
 */
package ChangeFileNameByMap;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;

import java.util.HashMap;
import pub.*;


/**
 *
 * @author ZHAO Qi
 * @date 2014-3-7 17:52:55
 * @version 1.6.0
 */
public class ChangeFileName {
      
      public void ChangeFileName(String dir, String mapfile) throws FileNotFoundException, IOException{
          HashMap index2sampleMap=txtReader.getMap(mapfile);
          File[] flist=FilelistReader.getFileList(dir);
          for (int i = 0; i < flist.length; i++) {
              //one should modified this line to parse your filenames
              String[] tempa=flist[i].getName().split("_");
              String s=tempa[0];
              System.out.println(s);
//              System.out.println(flist[i].getParent());
              if((String)index2sampleMap.get(s)==null){
                  System.out.println(s);
              }else{
                  System.out.println((String)index2sampleMap.get(s));
                  flist[i].renameTo(new File(flist[i].getParent()+"\\"+(String)index2sampleMap.get(s)+".anno.txt"));
              }
              
          }
          
      }
      
      public static void main(String[] args) throws FileNotFoundException, IOException {
        new ChangeFileName().ChangeFileName("F:\\mywork\\project\\Peng\\SamtoolsResult\\process2\\annotationResult", "F:\\mywork\\project\\Peng\\SamtoolsResult\\process2\\samplelist.txt");
    }
}
