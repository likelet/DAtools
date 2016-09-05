/*
 * This class was used for summary snpinformation for  chisq test
 * ***********************
 *            isSNP  notSNP
 **************************
 *antibio    | x1  |  x2
 **************************
 *non antibio| x3  |  x4 
 **************************
 */
package ExomeSeqAnalysisPipe.EcoliDataAnalysis;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;

//import static org.apache.commons.math3.stat.inference.TestUtils.chiSquareTest;

/**
 *
 * @author ZHAO Qi
 * @date 2015-4-1 15:02:52
 * @version 1.6.0
 */
public class snpFeatureInformation {

    private String snpinfor = "";

    private ArrayList<Double> chisqPvalue = new ArrayList<Double>();

    public snpFeatureInformation(String snpsinfor, HashMap<String, String> sample2snpmap, HashMap<String,HashMap<String,String>> antibioStatusmap) {
        this.snpinfor = snpsinfor;
        double Pvalue=0;
        String tempstr="";
        for (Iterator it = antibioStatusmap.keySet().iterator(); it.hasNext();) {
            tempstr=(String) it.next();
            this.chisqPvalue.add(getSnpPvalue(sample2snpmap,antibioStatusmap.get(tempstr)));
        }

    }

    public double getSnpPvalue(HashMap<String, String> sample2snpmap, HashMap<String, String> sample2antimap) {
        long snp_antibio = 0;
        long nonsnp_antibio = 0;
        long snp_nonantibio = 0;
        long nonsnp_nonantibio = 0;
        String samplename = "";
        for (Iterator it1 = sample2snpmap.keySet().iterator();
                it1.hasNext();) {
            samplename = (String) it1.next();
            if (sample2snpmap.get(samplename).equalsIgnoreCase("1") && sample2antimap.get(samplename).equalsIgnoreCase("1")) {
                snp_antibio++;
            } else if (sample2snpmap.get(samplename).equalsIgnoreCase("1") && sample2antimap.get(samplename).equalsIgnoreCase("0")) {
                snp_nonantibio++;
            } else if (sample2snpmap.get(samplename).equalsIgnoreCase("0") && sample2antimap.get(samplename).equalsIgnoreCase("1")) {
                nonsnp_antibio++;
            } else if (sample2snpmap.get(samplename).equalsIgnoreCase("0") && sample2antimap.get(samplename).equalsIgnoreCase("0")) {
                nonsnp_nonantibio++;
            }
        }

        System.out.println(snp_antibio
                + "\t" + snp_nonantibio + "\t" + nonsnp_antibio + "\t" + nonsnp_nonantibio);
        long a[][] = new long[2][2];
        a[0][0] = snp_antibio;
        a[0][1] = nonsnp_antibio;
        a[1][0] = snp_nonantibio;
        a[1][1] = nonsnp_nonantibio;

//        return chiSquareTest(a);
return 0;
    }

public String print(){
    String str="";
    str+=this.snpinfor+"\t";
    for (int i = 0; i < this.chisqPvalue.size(); i++) {
        str+=chisqPvalue.get(i)+"\t";
    }
    return str;
}



}
