/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package RPKM_saturation_M;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import net.sf.samtools.SAMFileReader;

/**
 *
 * @author ZHAO Qi
 * @date 2014-5-10 15:43:00
 * @version 1.6.0
 */
public class ReadsCountFromAbam {
       public void ReadsCountFromAbam(String bamfilepath,HashMap<String ,ArrayList<GeneRPKMoutUnit>> chr2GeneRPKMoutUnitMap){
           File bamfile=new File(bamfilepath);
           SAMFileReader sr=new SAMFileReader(bamfile,new File(bamfile.getAbsolutePath() + ".bai"));
           
           
           
       }
}
