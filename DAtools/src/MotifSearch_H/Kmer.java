/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package MotifSearch_H;

/**
 * <p>Kmer</p>
 * <p>Created on 2015-10-21 16:43:08</p>
 * <p>Author Email: zhaoqi3@mail2.sysu.edu.cn</p>
 * @author ZHAO Qi
 * @date 2015-10-21 16:43:08
 * @version java 1.6.0
 * @version 
 */
public class Kmer {

    private String seqname;
    
    private int start;
    
    private int end;
    
    private String seq;

    public Kmer(String seqname, int start, int end, String seq) {
        this.seqname = seqname;
        this.start = start;
        this.end = end;
        this.seq = seq.toUpperCase();
    }
    
    
    
    
    

    /**
     * Get the value of seq
     *
     * @return the value of seq
     */
    public String getSeq() {
        return seq;
    }

    /**
     * Set the value of seq
     *
     * @param seq new value of seq
     */
    public void setSeq(String seq) {
        this.seq = seq;
    }

    

    /**
     * Get the value of end
     *
     * @return the value of end
     */
    public int getEnd() {
        return end;
    }

    /**
     * Set the value of end
     *
     * @param end new value of end
     */
    public void setEnd(int end) {
        this.end = end;
    }


    /**
     * Get the value of start
     *
     * @return the value of start
     */
    public int getStart() {
        return start;
    }

    /**
     * Set the value of start
     *
     * @param start new value of start
     */
    public void setStart(int start) {
        this.start = start;
    }


    /**
     * Get the value of seqname
     *
     * @return the value of seqname
     */
    public String getSeqname() {
        return seqname;
    }

    /**
     * Set the value of seqname
     *
     * @param seqname new value of seqname
     */
    public void setSeqname(String seqname) {
        this.seqname = seqname;
    }

    @Override
    public String toString() {
        return seqname + "\t" + start + "\t" + end + "\t" + seq ;
    }

    
}
