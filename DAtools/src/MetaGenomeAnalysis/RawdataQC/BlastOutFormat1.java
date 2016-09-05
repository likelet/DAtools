/*
 * Blast out format with user defined
 qseqid sseqid pident length mismatch gapopen qstart qend sstart send evalue bitscore staxids
 CMD:
 time blastn -task megablast -db $ntbase -query $input -out ${input%%.fa}.blast -evalue 10e-5 -num_threads 22 -num_alignments 1 -outfmt '6 qseqid sseqid pident length mismatch gapopen qstart qend sstart send evalue bitscore staxids'
 */
package MetaGenomeAnalysis.RawdataQC;

/**
 * <p>
 BlastOutFormat1</p>
 * <p>
 * Created on 2015-12-17 16:44:12</p>
 * <p>
 * Author Email: zhaoqi3@mail2.sysu.edu.cn</p>
 *
 * @author ZHAO Qi
 * @date 2015-12-17 16:44:12
 * @version java 1.6.0
 * @version
 */
public class BlastOutFormat1 {

    private String qseqid;
    private String sseqid;
    private String pident;
    private String length;
    private String mismatch;
    private String gapopen;
    private String qstart;
    private String qend;
    private String sstart;
    private String send;
    private String evalue;
    private String bitscore;
    private String staxids;

    public BlastOutFormat1(String qseqid, String sseqid, String pident, String length, String mismatch, String gapopen, String qstart, String qend, String sstart, String send, String evalue, String bitscore, String staxids) {
        this.qseqid = qseqid;
        this.sseqid = sseqid;
        this.pident = pident;
        this.length = length;
        this.mismatch = mismatch;
        this.gapopen = gapopen;
        this.qstart = qstart;
        this.qend = qend;
        this.sstart = sstart;
        this.send = send;
        this.evalue = evalue;
        this.bitscore = bitscore;
        this.staxids = staxids;
    }

    public String getQseqid() {
        return qseqid;
    }

    public void setQseqid(String qseqid) {
        this.qseqid = qseqid;
    }

    public String getSseqid() {
        return sseqid;
    }

    public void setSseqid(String sseqid) {
        this.sseqid = sseqid;
    }

    public String getPident() {
        return pident;
    }

    public void setPident(String pident) {
        this.pident = pident;
    }

    public String getLength() {
        return length;
    }

    public void setLength(String length) {
        this.length = length;
    }

    public String getMismatch() {
        return mismatch;
    }

    public void setMismatch(String mismatch) {
        this.mismatch = mismatch;
    }

    public String getGapopen() {
        return gapopen;
    }

    public void setGapopen(String gapopen) {
        this.gapopen = gapopen;
    }

    public String getQstart() {
        return qstart;
    }

    public void setQstart(String qstart) {
        this.qstart = qstart;
    }

    public String getQend() {
        return qend;
    }

    public void setQend(String qend) {
        this.qend = qend;
    }

    public String getSstart() {
        return sstart;
    }

    public void setSstart(String sstart) {
        this.sstart = sstart;
    }

    public String getSend() {
        return send;
    }

    public void setSend(String send) {
        this.send = send;
    }

    public String getEvalue() {
        return evalue;
    }

    public void setEvalue(String evalue) {
        this.evalue = evalue;
    }

    public String getBitscore() {
        return bitscore;
    }

    public void setBitscore(String bitscore) {
        this.bitscore = bitscore;
    }

    public String getStaxids() {
        return staxids;
    }

    public void setStaxids(String staxids) {
        this.staxids = staxids;
    }

    @Override
    public String toString() {
        return "BlastOutFormat1{" + "qseqid=" + qseqid + ", sseqid=" + sseqid + ", pident=" + pident + ", length=" + length + ", mismatch=" + mismatch + ", gapopen=" + gapopen + ", qstart=" + qstart + ", qend=" + qend + ", sstart=" + sstart + ", send=" + send + ", evalue=" + evalue + ", bitscore=" + bitscore + ", staxids=" + staxids + '}';
    }
    
    

}
