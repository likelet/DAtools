/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ExomeSeqAnalysisPipe.CNVanalysis.CONTRAanalysis.GscoreStatistic;

import java.util.ArrayList;
import pub.myMath;

/**
 * <p>
 * GeneGscoreOutput</p>
 * <p>
 * Created on 2016-1-7 16:40:03</p>
 * <p>
 * Author Email: zhaoqi3@mail2.sysu.edu.cn</p>
 *
 * @author ZHAO Qi
 * @date 2016-1-7 16:40:03
 * @version java 1.6.0
 * @version
 */
public class GeneGscoreOutput {

    private String genename;
    private double ampscore;
    private ArrayList<Double> permuteAmpList=new ArrayList<Double>();
    private double AMP_pvalue;
    private double AMP_qvalue = 0;
    private double delscore;
    private ArrayList<Double> permuteDelList=new ArrayList<Double>();
    private double DEL_pvalue;
    private double DEL_qvalue = 0;

    public GeneGscoreOutput(String genename, double ampscore, double delscore) {
        this.genename = genename;
        this.ampscore = ampscore;
        this.delscore = delscore;
    }

    public GeneGscoreOutput(String genename, double ampscore, double AMP_pvalue, double delscore, double DEL_pvalue) {
        this.genename = genename;
        this.ampscore = ampscore;
        this.AMP_pvalue = AMP_pvalue;
        this.delscore = delscore;
        this.DEL_pvalue = DEL_pvalue;
    }

    public String getGenename() {
        return genename;
    }

    public void setGenename(String genename) {
        this.genename = genename;
    }

    public double getAmpscore() {
        return ampscore;
    }

    public void setAmpscore(double ampscore) {
        this.ampscore = ampscore;
    }

    public double getAMP_pvalue() {
        return myMath.getPermutationPbyESset(permuteAmpList, ampscore);
    }

    public void addAmplist(double permuteAmp){
        this.permuteAmpList.add(permuteAmp);
    }
    public void addDellist(double permuteDel){
        this.permuteDelList.add(permuteDel);
    }


    public double getAMP_qvalue() {
        return AMP_qvalue;
    }

    public void setAMP_qvalue(double AMP_qvalue) {
        this.AMP_qvalue = AMP_qvalue;
    }

    public double getDelscore() {
        return this.delscore;
    }

    public void setDelscore(double delscore) {
        this.delscore = delscore;
    }

    public double getDEL_pvalue() {
        return myMath.getPermutationPbyESset(permuteDelList, delscore);
    }

    public void setDEL_pvalue(double DEL_pvalue) {
        this.DEL_pvalue = DEL_pvalue;
    }

    public double getDEL_qvalue() {
        return DEL_qvalue;
    }

    public void setDEL_qvalue(double DEL_qvalue) {
        this.DEL_qvalue = DEL_qvalue;
    }

    @Override
    public String toString() {
        return genename + "\t" + ampscore + "\t" + this.getAMP_pvalue() + "\t" + AMP_qvalue + "\t" + delscore + "\t" + this.getDEL_pvalue() + "\t" + DEL_qvalue ;
    }

    
}
