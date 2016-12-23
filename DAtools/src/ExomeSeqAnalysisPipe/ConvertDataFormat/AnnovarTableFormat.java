/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package ExomeSeqAnalysisPipe.ConvertDataFormat;

/**
 *
 * @author Administrator
 * @since 2016-12-23
 * @coding time 10:44:15
 * @author Qi Zhao
 */
public class AnnovarTableFormat {

    private String chr;
    private int start;
    private int end;
    private String ref;
    private String alt;
    private String comment;

    public AnnovarTableFormat(String chr, String start, String end, String ref, String alt, String comment) {
        this.chr = chr;
        this.start = Integer.parseInt(start);
        this.end = Integer.parseInt(end);
        this.ref = ref;
        this.alt = alt;
        this.comment = comment;
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

    public String getRef() {
        return ref;
    }

    public void setRef(String ref) {
        this.ref = ref;
    }

    public String getAlt() {
        return alt;
    }

    public void setAlt(String alt) {
        this.alt = alt;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }
    
    
    
}
