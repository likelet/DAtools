/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package ExomeSeqAnalysisPipe.VirusAnalysis;

import htsjdk.samtools.SAMFileWriter;
import htsjdk.samtools.SAMFileWriterFactory;
import htsjdk.samtools.SAMRecord;
import htsjdk.samtools.SamInputResource;
import htsjdk.samtools.SamReader;
import htsjdk.samtools.SamReaderFactory;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

/**
 *
 * @author Administrator
 * @since 2019-3-7
 * @coding time 17:08:43
 * @author Qi Zhao
 */
public class GetReadsMappedTwoDifferentChr {
    // reads were assumed sorted by reads name 
public static void process(String inputbam, String outbam) throws IOException {
        SamReader reader = SamReaderFactory.makeDefault().open(SamInputResource.of(inputbam));

        ArrayList<SAMRecord> mappedmate = new ArrayList<SAMRecord>();
        SAMFileWriter outputSam = new SAMFileWriterFactory().setCreateIndex(true).makeSAMOrBAMWriter(reader.getFileHeader(), true, new File(outbam));
        
        
        int count = 0;
        int marker=0;

        for (SAMRecord samRecord : reader) {
            count++;
            if (count % 1000000 == 0) {
                System.out.println(count + " reads proceeded");
            }
            if(samRecord.getContig()!=samRecord.getMateReferenceName()&&virusTools.is_good_mapped_read(samRecord)&&virusTools.is_good_read(samRecord) ){
               outputSam.addAlignment(samRecord);
                System.out.println("ok");
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

    public static void main(String[] args) throws IOException {
        GetReadsMappedTwoDifferentChr.process("E:\\data\\Virus\\to_human_EBV_byname_less_sorted.bam", "E:\\data\\Virus\\to_human_EBV_byname_less_sorted_proceed.bam");
    }
}
