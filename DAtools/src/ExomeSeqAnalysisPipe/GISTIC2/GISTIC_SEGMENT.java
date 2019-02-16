/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package ExomeSeqAnalysisPipe.GISTIC2;

import htsjdk.samtools.util.CollectionUtil;
import java.util.List;
import java.util.stream.Collectors;

/**
 *
 * @author Administrator
 * @since 2018-12-26
 * @coding time 19:11:04
 * @author Qi Zhao
 */
public class GISTIC_SEGMENT {
    private String sample;
    private String chr;
    private int start;
    private int end ;
    private int numMakers;
    private double segCN;

    public GISTIC_SEGMENT(String sample, String chr, int start, int end, int numMakers, double segCN) {
        this.sample = sample;
        this.chr = chr;
        this.start = start;
        this.end = end;
        this.numMakers = numMakers;
        this.segCN = segCN;
    }
    
      public String toString() {
        final List<?> fields = CollectionUtil.makeList(sample,chr,start,end,numMakers,segCN);
        String str = fields.stream().map(String::valueOf).collect(Collectors.joining("\t"));
        return str;
    }
    
}
