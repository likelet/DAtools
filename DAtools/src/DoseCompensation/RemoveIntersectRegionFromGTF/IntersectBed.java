/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package DoseCompensation.RemoveIntersectRegionFromGTF;

/**
 * <p>IntersectBed</p>
 * <p>Created on 2015-12-24 10:19:19</p>
 * <p>Author Email: zhaoqi3@mail2.sysu.edu.cn</p>
 * @author ZHAO Qi
 * @date 2015-12-24 10:19:19
 * @version java 1.6.0
 * @version 
 */
public class IntersectBed {
    private String chr;
    private int start;
    private int end;
    private String strand;
    private String type;

    public IntersectBed() {
    }

    public IntersectBed(String chr, int start, int end, String strand, String type) {
        this.chr = chr;
        this.start = start;
        this.end = end;
        this.strand = strand;
        this.type = type;
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

    public String getStrand() {
        return strand;
    }

    public void setStrand(String strand) {
        this.strand = strand;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    @Override
    public String toString() {
        return chr + "\t" + start + "\t" + end + "\t" + strand + "\t" + type ;
    }
    
}
