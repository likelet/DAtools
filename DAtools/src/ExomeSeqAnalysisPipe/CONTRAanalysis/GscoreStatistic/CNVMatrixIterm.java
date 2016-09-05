/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ExomeSeqAnalysisPipe.CONTRAanalysis.GscoreStatistic;

import java.util.ArrayList;

/**
 * <p>
 * CNVMatrixIterm</p>
 * <p>
 * Created on 2016-1-8 10:54:04</p>
 * <p>
 * Author Email: zhaoqi3@mail2.sysu.edu.cn</p>
 *
 * @author ZHAO Qi
 * @date 2016-1-8 10:54:04
 * @version java 1.6.0
 * @version
 */
public class CNVMatrixIterm {

    String geneid;
    private ArrayList<Double> cnvlist = new ArrayList<Double>();
    private double ampscore;
    private double delscore;

    public CNVMatrixIterm(String geneid) {
        this.geneid = geneid;
    }

    //cnvlist add CNV
    public void addCNV(double cnv) {
        this.cnvlist.add(cnv);
    }

    //calculated Gscore
    public void calculatedScore() {
        get_Gscore gg = new get_Gscore(cnvlist);
        this.ampscore = gg.getAmpscore();
        this.delscore = gg.getDelscore();

    }

    public String getGeneid() {
        return geneid;
    }

    public ArrayList<Double> getCnvlist() {
        return cnvlist;
    }

    public double getAmpscore() {
        return ampscore;
    }

    public double getDelscore() {
        return delscore;
    }

    public void setCnvlist(ArrayList<Double> cnvlist) {
        this.cnvlist = cnvlist;
    }

    @Override
    public String toString() {
        String arrstr="";
        for (int i = 0; i < cnvlist.size(); i++) {
            arrstr+=String.format("%.2f",cnvlist.get(i))+"\t";
        }
        arrstr.subSequence(0, arrstr.length()-1);
        return  geneid + "\t" + arrstr ;
    }

    
}
