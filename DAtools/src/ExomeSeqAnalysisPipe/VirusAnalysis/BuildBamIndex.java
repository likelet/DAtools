/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package ExomeSeqAnalysisPipe.VirusAnalysis;

import htsjdk.samtools.BAMIndexer;
import htsjdk.samtools.SAMException;
import htsjdk.samtools.SAMFileHeader;
import htsjdk.samtools.SamReader;
import htsjdk.samtools.SamReaderFactory;
import htsjdk.samtools.ValidationStringency;
import htsjdk.samtools.util.CloserUtil;
import htsjdk.samtools.util.IOUtil;
import java.io.File;

/**
 *
 * @author Administrator
 * @since 2019-3-8
 * @coding time 10:48:12
 * @author Qi Zhao
 */
public class BuildBamIndex {
public static void buildIndexOfBam(String inputBam){
    File bamFile=new File(inputBam);
    File baiFile=new File(inputBam+".bai");        
SamReaderFactory.setDefaultValidationStringency(ValidationStringency.SILENT);
       final SamReader bam;

           // input from a normal file
           IOUtil.assertFileIsReadable(bamFile);
           bam = SamReaderFactory.makeDefault().referenceSequence(null)
                   .enable(SamReaderFactory.Option.INCLUDE_SOURCE_IN_RECORDS)
                   .open(bamFile);

       if (bam.type() != SamReader.Type.BAM_TYPE) {
           throw new SAMException("Input file must be bam file, not sam file.");
       }

       if (!bam.getFileHeader().getSortOrder().equals(SAMFileHeader.SortOrder.coordinate)) {
           throw new SAMException("Input bam file must be sorted by coordinate");
       }

       BAMIndexer.createIndex(bam, baiFile);

       CloserUtil.close(bam);
}
    public static void main(String[] args) {
        BuildBamIndex.buildIndexOfBam("E:\\data\\Virus\\T1471TRA_to_human_EBV.bam");
    }
}
