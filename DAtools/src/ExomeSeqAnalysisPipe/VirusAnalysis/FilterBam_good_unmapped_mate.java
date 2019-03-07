/*
 * a java version code for https://github.com/umccr/oncoviruses/blob/master/filter_bam_good_unmapped_or_mate.py to visualize virus integration 
 */

package ExomeSeqAnalysisPipe.VirusAnalysis;

import htsjdk.samtools.DefaultSAMRecordFactory;
import htsjdk.samtools.SAMFileWriter;
import htsjdk.samtools.SAMFileWriterFactory;
import htsjdk.samtools.SAMRecord;
import htsjdk.samtools.SamInputResource;
import htsjdk.samtools.SamReader;
import htsjdk.samtools.SamReaderFactory;
import htsjdk.samtools.ValidationStringency;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

/**
 *
 * @author Administrator
 * @since 2019-3-7
 * @coding time 9:43:52
 * @author Qi Zhao
 */
public class FilterBam_good_unmapped_mate {

    public static ArrayList<SAMRecord> resolve_mates(ArrayList<SAMRecord> sammate) {
        ArrayList<SAMRecord> emptyList = new ArrayList<SAMRecord>();
        for (int i = 0; i < sammate.size(); i++) {
            SAMRecord sr = sammate.get(i);
            if (!virusTools.is_good_read(sr)) {
                return (emptyList);
            }
        }
        ArrayList<SAMRecord> mappedmate = new ArrayList<SAMRecord>();
        for (int i = 0; i < sammate.size(); i++) {
            SAMRecord sr = sammate.get(i);
            if (!sr.getMateUnmappedFlag()) {
                mappedmate.add(sr);
            }
        }
        if (mappedmate.size() != 0) {
            for (int i = 0; i < mappedmate.size(); i++) {
                SAMRecord sr = mappedmate.get(i);
                if (virusTools.is_good_mapped_read(sr)) {
                   return (emptyList); 
                }
            }
        }
        return(mappedmate);

    }
    
    //asume input bamfile were sorted by reads name
   
    public static void process(String inputbam, String outbam) throws IOException {
        SamReader reader = SamReaderFactory.makeDefault().open(SamInputResource.of(inputbam));

        ArrayList<SAMRecord> mappedmate = new ArrayList<SAMRecord>();
        SAMFileWriter outputSam = new SAMFileWriterFactory().makeSAMOrBAMWriter(reader.getFileHeader(), true, new File(outbam));
        
        
        int count = 0;
        int marker=0;

        for (SAMRecord samRecord : reader) {
            count++;
            if (count % 1000000 == 0) {
                System.out.println(count + " reads proceeded");
            }
            if(marker==0){
                mappedmate.add(samRecord);
                marker++;
            }else{
                mappedmate.add(samRecord);
                virusTools.resolve_mates2(mappedmate);
                if(mappedmate.size()==0){
                    marker=0;
                }else if(mappedmate.size()==2){
                    for (SAMRecord tempRecord : mappedmate) {
                        outputSam.addAlignment(tempRecord);
                        mappedmate.clear();
                    }
                    marker=0;
                }else{
                    marker=1;
                }
            }
//            if (mappedmate.size() == 0 || mappedmate.get(0).getReadName() == samRecord.getReadName()) {
//                mappedmate.add(samRecord);
//            } else {
//                ArrayList<SAMRecord> templist = FilterBam_good_unmapped_mate.resolve_mates2(mappedmate.get(0),mappedmate.get(1));
//                if(templist.size()!=0){
//                    for (SAMRecord tempRecord : templist) {
//                        outputSam.addAlignment(tempRecord);
//                    }
//                }
//                mappedmate.clear();
//            }
        }

        outputSam.close();
        reader.close();

    }
}
