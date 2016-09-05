/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package ChIpseqAnalysisPipeline.intersect;

/**
 *
 * @author ZHAO Qi
 * @date 2015-4-18 11:28:55
 * @version 1.6.0
 */
public class tempFromat {
private String chr;
    
    private int start;
    
    private int end;
    
    private String seq;
    
    private String strand;

    public tempFromat(String chr, int start, int end, String seq, String strand) {
        this.chr = chr;
        this.start = start;
        this.end = end;
        this.seq = seq;
        this.strand = strand;
    }

    public String getChr() {
        return chr;
    }

    public void setChr(String chr) {
        this.chr = chr;
    }

    public int getStart() {
        return start;
    }

    public void setStart(int start) {
        this.start = start;
    }

    public int getEnd() {
        return end;
    }

    public void setEnd(int end) {
        this.end = end;
    }

    public String getSeq() {
        return seq;
    }

    public void setSeq(String seq) {
        this.seq = seq;
    }

    public String getStrand() {
        return strand;
    }

    public void setStrand(String strand) {
        this.strand = strand;
    }

    @Override
    public String toString() {
        return chr + "\t" +start + "\t" + end + "\t" + seq + "\t" +strand ;
    }

    
    
    
    
}
