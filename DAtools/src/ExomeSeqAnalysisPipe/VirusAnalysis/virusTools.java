/*
 * sub reads intergrate site with map both human and virus bamfile
 */

package ExomeSeqAnalysisPipe.VirusAnalysis;

import htsjdk.samtools.SAMRecord;
import java.util.ArrayList;

/**
 *
 * @author Administrator
 * @since 2019-3-7
 * @coding time 17:09:05
 * @author Qi Zhao
 */
public class virusTools {
public static boolean is_good_mapped_read(SAMRecord sr){
     if(sr.getMappingQuality()==0){
        return(false);
    }
      if(Math.abs(sr.getAlignmentEnd()-sr.getAlignmentStart())<60){
        return(false);
    }
       if(sr.getReadUnmappedFlag()){
        return(false);
    }
        if(!sr.isSecondaryOrSupplementary()){
        return(false);
    }
         if(sr.getDuplicateReadFlag()){
        return(false);
    }
    
      return(true);
}
public static boolean is_good_read(SAMRecord sr){
 
    if(sr.getReadFailsVendorQualityCheckFlag()){
        return(false);
    }
    // assume all reads are high quality reads after initial QC approach
    return(true);
}

 public static void resolve_mates2(ArrayList<SAMRecord> sammate) {
        SAMRecord sr1=sammate.get(0);
        SAMRecord sr2=sammate.get(1);
        if(sr1.getReadName()!=sr2.getReadName()){
            sammate.remove(0);
        } else {
            if (!virusTools.is_good_read(sr1) || !virusTools.is_good_read(sr2)) {
                sammate.clear();
            }
            if (virusTools.is_good_mapped_read(sr1) || virusTools.is_good_mapped_read(sr2)) {
            }
        }
    }
 
  public static void get_reads_from_the_differ_chrome(ArrayList<SAMRecord> sammate) {
        SAMRecord sr1=sammate.get(0);
        SAMRecord sr2=sammate.get(1);
        if(sr1.getReadName()!=sr2.getReadName()){
            sammate.remove(0);
        } else {
            if (!virusTools.is_good_read(sr1) || !virusTools.is_good_read(sr2)) {
                sammate.clear();
            }
            if (!virusTools.is_good_mapped_read(sr1) || !virusTools.is_good_mapped_read(sr2)) {
                sammate.clear();
            }
            if (sr1.getContig()==sr2.getContig()) {
                sammate.clear();
            }
        }
    }


}
