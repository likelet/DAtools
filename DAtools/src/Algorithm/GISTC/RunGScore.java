/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package Algorithm.GISTC;

import java.util.ArrayList;
import pub.ToolsforCMD;

/**
 * <p>RunGScore</p>
 * <p>Created on 2015-11-14 18:08:35</p>
 * <p>Author Email: zhaoqi3@mail2.sysu.edu.cn</p>
 * @author ZHAO Qi
 * @date 2015-11-14 18:08:35
 * @version java 1.6.0
 * @version 
 */
public class RunGScore {
    //paired sample  tumorlist should be paired with normallist exactly
    // amp
    public GscoreTerm getPairedAMPGscore(ArrayList<SampleGeneTerm> Tumorlist, ArrayList<SampleGeneTerm> Normlist, double ampthreshold) {
        double score = 0;
        double frequency = 0;
        double averageAMP = 0;
        if (Tumorlist.size() != Normlist.size()) {
            System.out.println("Tumor and normal sample size is inequality\n please check your sample first! ");
            return null;

        } else {
            double[] cnvlist = new double[Normlist.size()];
            //mark the frequency
            int count=0;
            //mark the summmary
            int sum=0;
            for (int i = 0; i < Tumorlist.size(); i++) {
                cnvlist[i] = ToolsforCMD.getlog2(Tumorlist.get(i).getCnv() / Normlist.get(i).getCnv());
                if(cnvlist[i]>=ampthreshold){
                   count++; 
                }
                sum+=cnvlist[i];
            }
            averageAMP=sum/Tumorlist.size();
            frequency=count/Tumorlist.size();
            score=averageAMP*frequency;
            GscoreTerm gt=new GscoreTerm(Tumorlist.get(0).getFeature(),Tumorlist.get(0).getStart(),Tumorlist.get(0).getEnd(),Tumorlist.get(0).getStrand(),"AMP",score,averageAMP,frequency);
            return gt;
        }

    }
    
    // deletion
    public GscoreTerm getPairedDELGscore(ArrayList<SampleGeneTerm> Tumorlist, ArrayList<SampleGeneTerm> Normlist, double delthreshold) {
        double score = 0;
        double frequency = 0;
        double averageAMP = 0;
        if (Tumorlist.size() != Normlist.size()) {
            System.out.println("Tumor and normal sample size is inequality\n please check your sample first! ");
            return null;

        } else {
            double[] cnvlist = new double[Normlist.size()];
            //mark the frequency
            int count=0;
            //mark the summmary
            int sum=0;
            for (int i = 0; i < Tumorlist.size(); i++) {
                cnvlist[i] = ToolsforCMD.getlog2(Tumorlist.get(i).getCnv() / Normlist.get(i).getCnv());
                if(cnvlist[i]<=delthreshold){
                   count++; 
                }
                sum+=cnvlist[i];
            }
            averageAMP=sum/Tumorlist.size();
            frequency=count/Tumorlist.size();
            score=averageAMP*frequency;
            GscoreTerm gt=new GscoreTerm(Tumorlist.get(0).getFeature(),Tumorlist.get(0).getStart(),Tumorlist.get(0).getEnd(),Tumorlist.get(0).getStrand(),"DEL",score,averageAMP,frequency);
            return gt;
        }

    }
    
    
}
