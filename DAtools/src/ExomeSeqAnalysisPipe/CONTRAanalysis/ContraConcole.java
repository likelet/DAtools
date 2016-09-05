/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package ExomeSeqAnalysisPipe.CONTRAanalysis;

import ExomeSeqAnalysisPipe.CONTRAanalysis.GscoreStatistic.CNVMatrixIterm;
import ExomeSeqAnalysisPipe.CONTRAanalysis.GscoreStatistic.GetStatic_fromPermutationList;
import ExomeSeqAnalysisPipe.CONTRAanalysis.summaryContra.summaryContraResultWithChromeBind;
import java.util.ArrayList;

/**
 * <p>ContraConcole</p>
 * <p>Created on 2016-1-16 15:25:43</p>
 * <p>Author Email: zhaoqi3@mail2.sysu.edu.cn</p>
 * @author ZHAO Qi
 * @date 2016-1-16 15:25:43
 * @version java 1.6.0
 * @version 
 */
public class ContraConcole {
    public static void main(String[] args) {
        ArrayList<CNVMatrixIterm> oringinlist =new summaryContraResultWithChromeBind("G:\\temp\\test\\T_paired.list","G:\\temp\\test\\genome.fa.fai","G:\\temp\\test\\my.matrix").getOringinlist();
        new GetStatic_fromPermutationList(oringinlist,"G:\\temp\\test\\my.gscore");
    }
}
