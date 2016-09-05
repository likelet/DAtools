/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package ExomeSeqAnalysisPipe.CONTRAanalysis.GscoreStatistic;

/**
 * <p>ContraGisticMain</p>
 * <p>Created on 2016-1-7 19:17:03</p>
 * <p>Author Email: zhaoqi3@mail2.sysu.edu.cn</p>
 * @author ZHAO Qi
 * @date 2016-1-7 19:17:03
 * @version java 1.6.0
 * @version 
 */
public class ContraGisticMain {

    public ContraGisticMain(String orignfile, String outfile) {
        new GetStatic_fromPermutationList(orignfile, outfile);
    }
}
