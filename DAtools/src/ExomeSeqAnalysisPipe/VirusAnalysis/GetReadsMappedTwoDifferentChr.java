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
import java.io.FilenameFilter;
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
        String samplename= new File(inputbam).getName().split("_")[0];

        ArrayList<SAMRecord> mappedmate = new ArrayList<SAMRecord>();
        SAMFileWriter outputSam = new SAMFileWriterFactory().setCreateIndex(false).makeSAMOrBAMWriter(reader.getFileHeader(), true, new File(outbam));
        int count = 0;
        int viruscount=0;
        int intercount=0;
        for (SAMRecord samRecord : reader) {
            count++;
            if (count % 1000000 == 0) {
//                System.out.println(count + " reads proceeded");
            }
            if(!virusTools.is_good_mapped_read(samRecord)) continue;
            if(!virusTools.is_good_read(samRecord)) continue;
            if(samRecord.getContig()!=samRecord.getMateReferenceName()){
                outputSam.addAlignment(samRecord);
                intercount++;
            }else{
                viruscount++;
            }
        }
        System.out.println(samplename+"\tTotal\t"+intercount+"\treads count supporting intergration into human genome " );
        System.out.println(samplename+"\tTotal\t"+viruscount+"\tReads mapped to virus" );

        outputSam.close();
        reader.close();

        BuildBamIndex.buildIndexOfBam(outbam);
    }

    public static void main(String[] args) throws IOException {
//        GetReadsMappedTwoDifferentChr.process("E:\\data\\Virus\\T1471TRA_to_human_EBV.bam", "E:\\data\\Virus\\T1471TRA_forIGV.bam");
        
          File directory = new File("E:\\data\\Virus");
        File[] fileList = directory.listFiles(new FilenameFilter() {
            @Override
            public boolean accept(File dir, String name) {
                return name.endsWith("_to_human_EBV.bam");
            }
        });
        for (File file : fileList){
            GetReadsMappedTwoDifferentChr.process(file.getAbsolutePath(),file.getAbsolutePath().split("_")[0]+"_for_IGV.bam");
        }
    
        
        
    }
}
