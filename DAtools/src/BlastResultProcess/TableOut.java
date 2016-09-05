/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package BlastResultProcess;

/**
 *
 * @author ZHAO Qi
 * @date 2015-3-24 15:07:41
 * @version 1.6.0
 */
public class TableOut {

    private String queryId;
    private String subjectId;
    private double percIdentity;
    private double alnLength;
    private double mismatchCount;
    private double gapOpenCount;
    private double queryStart;
    private double queryEnd;
    private double subjectStart;
    private double subjectEnd;
    private double eVal;
    private double bitScore;

    public TableOut(String queryId, String subjectId, double percIdentity, double alnLength, double mismatchCount, double gapOpenCount, double queryStart, double queryEnd, double subjectStart, double subjectEnd, double eVal, double bitScore) {
        this.queryId = queryId;
        this.subjectId = subjectId;
        this.percIdentity = percIdentity;
        this.alnLength = alnLength;
        this.mismatchCount = mismatchCount;
        this.gapOpenCount = gapOpenCount;
        this.queryStart = queryStart;
        this.queryEnd = queryEnd;
        this.subjectStart = subjectStart;
        this.subjectEnd = subjectEnd;
        this.eVal = eVal;
        this.bitScore = bitScore;
    }
    
    public String getQueryId() {
        return queryId;
    }

    public void setQueryId(String queryId) {
        this.queryId = queryId;
    }

    public String getSubjectId() {
        return subjectId;
    }

    public void setSubjectId(String subjectId) {
        this.subjectId = subjectId;
    }

    public double getPercIdentity() {
        return percIdentity;
    }

    public void setPercIdentity(double percIdentity) {
        this.percIdentity = percIdentity;
    }

    public double getAlnLength() {
        return alnLength;
    }

    public void setAlnLength(double alnLength) {
        this.alnLength = alnLength;
    }

    public double getMismatchCount() {
        return mismatchCount;
    }

    public void setMismatchCount(double mismatchCount) {
        this.mismatchCount = mismatchCount;
    }

    public double getGapOpenCount() {
        return gapOpenCount;
    }

    public void setGapOpenCount(double gapOpenCount) {
        this.gapOpenCount = gapOpenCount;
    }

    public double getQueryStart() {
        return queryStart;
    }

    public void setQueryStart(double queryStart) {
        this.queryStart = queryStart;
    }

    public double getQueryEnd() {
        return queryEnd;
    }

    public void setQueryEnd(double queryEnd) {
        this.queryEnd = queryEnd;
    }

    public double getSubjectStart() {
        return subjectStart;
    }

    public void setSubjectStart(double subjectStart) {
        this.subjectStart = subjectStart;
    }

    public double getSubjectEnd() {
        return subjectEnd;
    }

    public void setSubjectEnd(double subjectEnd) {
        this.subjectEnd = subjectEnd;
    }

    public double geteVal() {
        return eVal;
    }

    public void seteVal(double eVal) {
        this.eVal = eVal;
    }

    public double getBitScore() {
        return bitScore;
    }

    public void setBitScore(double bitScore) {
        this.bitScore = bitScore;
    }

    @Override
    public String toString() {
        return  queryId + "\t" + subjectId + "\t" + percIdentity + "\t" + alnLength + "\t" + mismatchCount + "\t" + gapOpenCount + "\t" + queryStart + "\t" + queryEnd + "\t" + subjectStart + "\t" + subjectEnd + "\t" + eVal + "\t" + bitScore ;
    }
    
    
    
    
    
    
    
}
