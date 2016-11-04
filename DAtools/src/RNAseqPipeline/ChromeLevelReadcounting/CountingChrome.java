/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package RNAseqPipeline.ChromeLevelReadcounting;

import RNAseqPipeline.RPKM_saturation_M.GeneRPKMoutUnit;
import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
//import net.sf.samtools.SAMFileReader;
//import net.sf.samtools.SAMRecord;

/**
 *
 * @author Administrator
 * @since 2016-10-10
 * @coding time 15:52:56
 * @author Qi Zhao
 */
public class CountingChrome {
    private String outfile;
    private String bamfile ;

    public CountingChrome(String bamfile) {
        this.bamfile = bamfile;
    }

    public CountingChrome(String outfile, String bamfile) {
        this.outfile = outfile;
        this.bamfile = bamfile;
    }
    
}
//public void ReadsCountFromAbam(String bamfilepath){
//        File bamfile = new File(bamfilepath);
//        SAMFileReader sr = new SAMFileReader(bamfile, new File(bamfile.getAbsolutePath() + ".bai"));
//        for (Iterator it = sr.iterator(); it.hasNext();) {
//            SAMRecord sitem = (SAMRecord) it.next();
//            if(sitem.getFlags()){
//            }
//        }
//
//    }
//}
