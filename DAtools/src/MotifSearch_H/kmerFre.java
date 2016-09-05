/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package MotifSearch_H;

/**
 * <p>
 * kmerfre</p>
 * <p>
 * Created on 2015-12-31 14:38:35</p>
 * <p>
 * Author Email: zhaoqi3@mail2.sysu.edu.cn</p>
 *
 * @author ZHAO Qi
 * @date 2015-12-31 14:38:35
 * @version java 1.6.0
 * @version
 */
public class kmerFre extends Kmer {

    private int count = 0;
    private int totalcount;
    private double fre;

    public kmerFre(String seqname, int start, int end, String seq, int totalcount) {
        super(seqname, start, end, seq);
        this.totalcount = totalcount;
    }

    public int getCount() {
        return count;
    }

    public void setCount() {
        this.count++;
    }

    public int getTotalcount() {
        return totalcount;
    }

    public void setTotalcount(int totalcount) {
        this.totalcount = totalcount;
    }

    public double getFre() {
        return (double) count / totalcount;
    }

    @Override
    public String toString() {
        return super.toString()+"\t" + count + "\t" + totalcount + "\t" + this.getFre() ;
    }

}
