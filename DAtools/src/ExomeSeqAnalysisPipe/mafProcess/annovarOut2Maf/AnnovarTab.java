/*
 *$Date: 2015-03-22 15:29:59 for annovar
 */

package ExomeSeqAnalysisPipe.mafProcess.annovarOut2Maf;

/**
 * <p>AnnovarTab</p>
 * <p>Created on 2015-12-22 17:40:10</p>
 * <p>Author Email: zhaoqi3@mail2.sysu.edu.cn</p>
 * @author ZHAO Qi
 * @date 2015-12-22 17:40:10
 * @version java 1.6.0
 * @version 
 */
public class AnnovarTab {
private	String	Chr;
private	String	Start;
private	String	End;
private	String	Ref;
private	String	Alt;
private	String	Func_refGene;
private	String	Gene_refGene;
private	String	GeneDetail_refGene;
private	String	ExonicFunc_refGene;
private	String	AAChange_refGene;
private	String	cytoBand;
private	String	T1000g2014oct_all;
private	String	snp138;

    public AnnovarTab(String Chr, String Start, String End, String Ref, String Alt, String Func_refGene, String Gene_refGene, String GeneDetail_refGene, String ExonicFunc_refGene, String AAChange_refGene, String cytoBand, String T1000g2014oct_all, String snp138) {
        this.Chr = Chr;
        this.Start = Start;
        this.End = End;
        this.Ref = Ref;
        this.Alt = Alt;
        this.Func_refGene = Func_refGene;
        this.Gene_refGene = Gene_refGene;
        this.GeneDetail_refGene = GeneDetail_refGene;
        this.ExonicFunc_refGene = ExonicFunc_refGene;
        this.AAChange_refGene = AAChange_refGene;
        this.cytoBand = cytoBand;
        this.T1000g2014oct_all = T1000g2014oct_all;
        this.snp138 = snp138;
    }

    public String getChr() {
        return Chr;
    }

    public void setChr(String Chr) {
        this.Chr = Chr;
    }

    public String getStart() {
        return Start;
    }

    public void setStart(String Start) {
        this.Start = Start;
    }

    public String getEnd() {
        return End;
    }

    public void setEnd(String End) {
        this.End = End;
    }

    public String getRef() {
        return Ref;
    }

    public void setRef(String Ref) {
        this.Ref = Ref;
    }

    public String getAlt() {
        return Alt;
    }

    public void setAlt(String Alt) {
        this.Alt = Alt;
    }

    public String getFunc_refGene() {
        return Func_refGene;
    }

    public void setFunc_refGene(String Func_refGene) {
        this.Func_refGene = Func_refGene;
    }

    public String getGene_refGene() {
        return Gene_refGene;
    }

    public void setGene_refGene(String Gene_refGene) {
        this.Gene_refGene = Gene_refGene;
    }

    public String getGeneDetail_refGene() {
        return GeneDetail_refGene;
    }

    public void setGeneDetail_refGene(String GeneDetail_refGene) {
        this.GeneDetail_refGene = GeneDetail_refGene;
    }

    public String getExonicFunc_refGene() {
        return ExonicFunc_refGene;
    }

    public void setExonicFunc_refGene(String ExonicFunc_refGene) {
        this.ExonicFunc_refGene = ExonicFunc_refGene;
    }

    public String getAAChange_refGene() {
        return AAChange_refGene;
    }

    public void setAAChange_refGene(String AAChange_refGene) {
        this.AAChange_refGene = AAChange_refGene;
    }

    public String getCytoBand() {
        return cytoBand;
    }

    public void setCytoBand(String cytoBand) {
        this.cytoBand = cytoBand;
    }

    public String getT1000g2014oct_all() {
        return T1000g2014oct_all;
    }

    public void setT1000g2014oct_all(String T1000g2014oct_all) {
        this.T1000g2014oct_all = T1000g2014oct_all;
    }

    public String getSnp138() {
        return snp138;
    }

    public void setSnp138(String snp138) {
        this.snp138 = snp138;
    }

    @Override
    public String toString() {
        return Chr+"\t"+Start+"\t"+End+"\t"+Ref+"\t"+Alt+"\t"+Func_refGene+"\t"+Gene_refGene+"\t"+GeneDetail_refGene+"\t"+ExonicFunc_refGene+"\t"+AAChange_refGene+"\t"+cytoBand+"\t"+T1000g2014oct_all+"\t"+snp138;
    }



}
