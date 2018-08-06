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
 * get_Gscore</p>
 * <p>
 * Created on 2016-1-7 16:11:56</p>
 * <p>
 * Author Email: zhaoqi3@mail2.sysu.edu.cn</p>
 *
 * @author ZHAO Qi
 * @date 2016-1-7 16:11:56
 * @version java 1.6.0
 * @version
 */
public class get_Gscore {

    private double upthreshold = .5;
    private double downthreshold = -.5;
    private double ampscore=0;
    private double delscore=0;
    private ArrayList<Double> datalist;

    public get_Gscore(ArrayList<Double> datalist) {
        this.datalist = datalist;
        this.getscore();
    }
    
    

    public void getscore( ) {
        ArrayList<Double> amplist = new ArrayList<Double>();
        ArrayList<Double> dellist = new ArrayList<Double>();
        for (int i = 0; i < datalist.size(); i++) {
            if (datalist.get(i) > 0.5) {
                amplist.add(datalist.get(i));
            } else if (datalist.get(i) < -0.5) {
                dellist.add(datalist.get(i));
            }
        }
        if (amplist.size() > 0) {
            ampscore = myMath.getArrayMean(amplist) * amplist.size() / datalist.size();
        }
        if (dellist.size() > 0) {
            delscore = myMath.getArrayMean(dellist) * dellist.size() / datalist.size();
        }
    }

    public double getAmpscore() {
        return ampscore;
    }

    public double getDelscore() {
        return delscore;
    }


}
