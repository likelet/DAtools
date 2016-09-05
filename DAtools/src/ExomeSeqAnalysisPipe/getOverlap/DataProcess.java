/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ExomeSeqAnalysisPipe.getOverlap;

import ExomeSeqAnalysisPipe.FilelistReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;


/**
 *
 * @author ZHAO Qi
 * @date 2014-3-26 14:42:34
 * @version 1.6.0
 */
public class DataProcess {
    public void ClusterOverlap(String dir, String outfile) throws IOException{
        ArrayList<ArrayList> listcluster=FilelistReader.getFileListCluster(dir);
        getOverlap go=new getOverlap(){

            @Override
            public boolean compair(Object o1, Object o2) {
               if(o1.equals(o2)){
                   return true;
               }else {
                   return false;
               }
            }
        };
        ArrayList resultlist=go.getOverlapList(listcluster);
        try {
            FileWriter fw = new FileWriter(outfile);
            for (int i = 0; i < resultlist.size(); i++) {
                fw.append(resultlist.get(i)+"\r\n");
            }
            fw.flush();
            fw.close();
        } catch (IOException ex) {
            Logger.getLogger(DataProcess.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public static void main(String[] args) throws IOException {
        DataProcess dp=new DataProcess();
        dp.ClusterOverlap("F:\\mywork\\project\\Peng\\annotationFinal\\cluster2", "F:\\mywork\\project\\Peng\\annotationFinal\\cluster2ComonSnp.txt");
    }
}
