/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package ExomeSeqAnalysisPipe.CNVanalysis.ADTex;

import BedProcess.BEDReader;
import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import pub.BED;
import pub.FilelistReader;

/**
 *
 * @author Administrator
 * @since 2018-8-1
 * @coding time 16:08:54
 * @author Qi Zhao
 */
public class ADTexProcess {

    public void SummaryADTexCNVresult(String targetBed, String dir, String suffix, String outfile) {
        ArrayList<BED> bedlist = BEDReader.ReadBed5(targetBed);
        File[] list = FilelistReader.getFileList(dir, suffix);

        //parsing files 
        HashMap<String, HashMap<String, ADTexCNVterm>> filehash = new HashMap<String, HashMap<String, ADTexCNVterm>>();
        for (int i = 0; i < list.length; i++) {
            File file = list[i];
            String tempsampleName = file.getName();
            HashMap<String, ADTexCNVterm> temphash = ParseADTexCNVfile.parseCNVfile(file);
            filehash.put(tempsampleName, temphash);
        }
        
        // format data and get matrix 
        
        for (int i = 0; i < bedlist.size(); i++) {
            BED tempbed = bedlist.get(i);
            String tempstr=tempbed.getChrom()+":"+tempbed.getChromStart()+"-"+tempbed.getChromEnd();
            bedlist.get(i).setScore(i);
        }
    }
}
