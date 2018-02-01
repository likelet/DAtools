/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package ExomeSeqAnalysisPipe.mafProcess;

import java.util.HashMap;

/**
 *
 * @author Administrator
 * @since 2017-12-8
 * @coding time 15:09:08
 * @author Qi Zhao
 */
public class vcf_function_class {
// parsing mutect information clomuns
    public static HashMap<String,String> getmap(String info){
        HashMap<String,String> tempmap=new HashMap<String,String>();
        String [] tempstr= info.split(";");
        tempmap.put("GI", tempstr[0]);
        tempmap.put("AD", tempstr[1]);
        tempmap.put("BQ", tempstr[2]);
        tempmap.put("DP", tempstr[3]);
        tempmap.put("FA", tempstr[4]);
        tempmap.put("SS", tempstr[5]);
        int refD=Integer.parseInt(tempstr[1].split(",")[0]);
        int altD=Integer.parseInt(tempstr[1].split(",")[2]);
        int totalDP=refD+altD;
        tempmap.put("totalD", String.valueOf(totalDP));
        tempmap.put("altD", String.valueOf(altD));
        return tempmap;
    }
    
}
class mutect_info{
    String GT="";
    String AD="";
    String BQ="";
    String DP="";
    String FA="";
    String SS="";
    
    public mutect_info() {
    }

    public String getGT() {
        return GT;
    }

    public void setGT(String GT) {
        this.GT = GT;
    }

    public String getAD() {
        return AD;
    }

    public void setAD(String AD) {
        this.AD = AD;
    }

    public String getBQ() {
        return BQ;
    }

    public void setBQ(String BQ) {
        this.BQ = BQ;
    }

    public String getDP() {
        return DP;
    }

    public void setDP(String DP) {
        this.DP = DP;
    }

    public String getFA() {
        return FA;
    }

    public void setFA(String FA) {
        this.FA = FA;
    }

    public String getSS() {
        return SS;
    }

    public void setSS(String SS) {
        this.SS = SS;
    }
    

}