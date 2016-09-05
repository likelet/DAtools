/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ExomeSeqAnalysisPipe;

/**
 *
 * @author ZhaoQi
 */
public class VCFrecord {
    private String chrom;
    private String pos;
    private String id;
    private String ref;
    private String alt;
    private String qual;
    private String filter;
    private String genotype;
    private String DP;    
    private String AF1;
    private String AC1;
    private String DP4;
    private String MQ;
    private String FQ;
    private String VDB;
    private String PV4;
    private String GT;
    private String PL;
    private int refNum;
    private int altNum;
    private String GQ;
    public void setChrom(String chrom){
        this.chrom=chrom;
    }
    public String getChrom(){
        return chrom;
    }
    public void setPos(String pos){
        this.pos=pos;
    }
    public String getPos(){
        return pos;
    }
    public void setId(String id){
        this.id=id;
    }
    public String getId(){
        return id;
    }
    public void setRef(String ref){
        this.ref=ref;
    }
    public String getRef(){
        return ref;
    }
    public void setAlt(String alt){
        this.alt=alt;
    }
    public String getAlt(){
        return alt;
    }
    public void setQual(String qual){
        this.qual=qual;
    }
    public String getQual(){
        return qual;
    }
    public void setFilter(String filter){
        this.filter=filter;
    }
    public String getFilter(){
        return filter;
    }
    public void setGenotype(String genotype){
        this.genotype=genotype;
    }
    public String getGenotype(){
        return genotype;
    }
    public void setDP(String DP){
        this.DP=DP;
    }
    public String getDP(){
        return DP;
    }
    public void setDP4(String DP4){
        this.DP4=DP4;
    }
    public String getDP4(){
        return DP4;
    }
    public void setAF1(String AF1){
        this.AF1=AF1;
    }
    public String getAF1(){
        return AF1;
    }
    public void setAC1(String AC1){
        this.AC1=AC1;
    }
    public String getAC1(){
        return AC1;
    }
    public void setMQ(String MQ){
        this.MQ=MQ;
    }
    public String getMQ(){
        return MQ; 
    }
    public void setFQ(String FQ){
        this.FQ=FQ;
    }
    public String getFQ(){
        return FQ;
    }
    public void setVDB(String VDB){
        this.VDB=VDB;
    }
    public String getVDB(){
        return VDB;
    }
    public void setPV4(String PV4){
        this.PV4=PV4;
    }
    public String getPV4(){
        return PV4;
    }
    public void setGT(String GT){
        this.GT=GT;
    }
    public String getGT(){
        return GT;
    }
    public void setPL(String PL){
        this.PL=PL;
    }
    public String getPL(){
        return PL;
    }
    public void setRefNum(int refNum){
        this.refNum=refNum;
    }
    public int getRefNum(){
        return refNum;
    }
    public void setAltNum(int altNum){
        this.altNum=altNum;
    }
    public int getAltNum(){
        return altNum;
    }
    public void setGQ(String GQ){
        this.GQ=GQ;
    }
    public String getGQ(){
        return GQ;
    }
    public String toString(){
        return chrom+"\t"+pos+"\t"+id+"\t"+ref+"\t"+alt+"\t"+qual+"\t"+filter+"\t"+genotype+"\t"+DP+"\t"+AF1+"\t"+AC1+"\t"+DP4+"\t"+refNum+"\t"+altNum+"\t"+MQ+"\t"+FQ+"\t"+VDB+"\t"+PV4+"\t"+GT+"\t"+PL+"\t"+GQ; 
    }
    public String tosimple(){
        return chrom+"\t"+pos;
    }
}
