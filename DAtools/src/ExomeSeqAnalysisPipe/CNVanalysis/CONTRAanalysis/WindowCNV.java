/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ExomeSeqAnalysisPipe.CNVanalysis.CONTRAanalysis;

import java.util.ArrayList;

/**
 * <p>
 * WindowCNV</p>
 * <p>
 * Created on 2016-1-16 13:31:29</p>
 * <p>
 * Author Email: zhaoqi3@mail2.sysu.edu.cn</p>
 *
 * @author ZHAO Qi
 * @date 2016-1-16 13:31:29
 * @version java 1.6.0
 * @version
 */
public class WindowCNV {

    private String genename;
    private ArrayList<ContraOutTab> exonlist = new ArrayList<ContraOutTab>();
    private int start;
    private int end;
    private String chr;

    public WindowCNV(String genename) {
        this.genename = genename;
    }

    public WindowCNV(String genename, int start, int end, String chr) {
        this.genename = genename;
        this.start = start;
        this.end = end;
        this.chr = chr;
    }

    public String getGenename() {
        return genename;
    }

    public void setGenename(String genename) {
        this.genename = genename;
    }

    public ArrayList<ContraOutTab> getExonlist() {
        return exonlist;
    }

    public int getStart() {
        return start;
    }

    public void setStart(int start) {
        this.start = start;
    }

    public int getEnd() {
        return end;
    }

    public void setEnd(int end) {
        this.end = end;
    }

    public String getChr() {
        return chr;
    }

    public void setChr(String chr) {
        this.chr = chr;
    }

    public void addContraOutTab(ContraOutTab ct) {
        this.exonlist.add(ct);
    }

    public ContraOutTab getWindowTab() {
        if (exonlist.size() == 0) {
            ContraOutTab windowCNVtab = new ContraOutTab(genename, this.chr, this.start, this.end);
            return windowCNVtab;
        } else {
            ContraOutTab ct = exonlist.get(0);
            String Targeted_Region_ID = ct.getTargeted_Region_ID();
            double Exon_Number = exonlist.size();
            String Gene_Sym = this.genename;
            String Chr = ct.getChr();
            double OriStCoordinate = this.start;
            double OriEndCoordinate = this.end;
            double Mean_of_LogRatio = ct.getMean_of_LogRatio();
            double Adjusted_Mean_of_LogRatio = ct.getAdjusted_Mean_of_LogRatio();
            double SD_of_LogRatio = ct.getSD_of_LogRatio();
            double Median_of_LogRatio = ct.getMedian_of_LogRatio();
            double number_bases = ct.getNumber_bases();
            double P_Value = ct.getP_Value();
            double Adjusted_P_Value = ct.getAdjusted_P_Value();
            String gain_loss = ct.getGain_loss();
            double tumour_rd = ct.getTumour_rd();
            double normal_rd = ct.getNormal_rd();
            double tumour_rd_ori = ct.getTumour_rd_ori();
            double normal_rd_ori = ct.getNormal_rd_ori();
            double MinLogRatio = ct.getMinLogRatio();
            double MaxLogRatio = ct.getMaxLogRatio();
            double BinNumber = ct.getBinNumber();

//            int effcount = 0;
//            if (ct.getMean_of_LogRatio() != 0) {
//                Mean_of_LogRatio += ct.getMean_of_LogRatio();
//            } else {
//                effcount++;
//            }
            int effcount = exonlist.size();
            for (int i = 1; i < exonlist.size(); i++) {
                ContraOutTab ct1 = exonlist.get(i);
                if (ct1.getMean_of_LogRatio() != 0) {
                    Mean_of_LogRatio += ct1.getMean_of_LogRatio();
                } else {
                    effcount++;
                }

                Adjusted_Mean_of_LogRatio += ct1.getAdjusted_Mean_of_LogRatio();
                SD_of_LogRatio += ct1.getSD_of_LogRatio();
                P_Value += ct1.getP_Value();
                Adjusted_P_Value += ct1.getAdjusted_P_Value();
                tumour_rd += ct1.getTumour_rd();
                normal_rd += ct1.getNormal_rd();
                tumour_rd_ori += ct1.getTumour_rd_ori();
                normal_rd_ori += ct1.getNormal_rd_ori();
                MinLogRatio = Math.min(MinLogRatio, ct1.getMinLogRatio());
                MaxLogRatio = Math.max(MinLogRatio, ct1.getMaxLogRatio());
            }

            Mean_of_LogRatio = Mean_of_LogRatio / effcount;
            Adjusted_Mean_of_LogRatio = Adjusted_Mean_of_LogRatio / effcount;
            SD_of_LogRatio = SD_of_LogRatio / effcount;
            P_Value = P_Value / effcount;
            Adjusted_P_Value = Adjusted_P_Value / effcount;
            tumour_rd = tumour_rd / effcount;
            normal_rd = normal_rd / effcount;
            tumour_rd_ori = tumour_rd_ori / effcount;
            normal_rd_ori = normal_rd_ori / effcount;
//         geneCTab=Targeted_Region_ID+"\t"+Exon_Number+"\t"+Gene_Sym+"\t"+Chr+"\t"+OriStCoordinate+"\t"+OriEndCoordinate+"\t"+Mean_of_LogRatio/exonlist.size()+"\t"+Adjusted_Mean_of_LogRatio/exonlist.size()+"\t"+SD_of_LogRatio/exonlist.size()+"\t"+Median_of_LogRatio/exonlist.size()+"\t"+number_bases+"\t"+P_Value/exonlist.size()+"\t"+Adjusted_P_Value/exonlist.size()+"\t"+gain_loss+"\t"+tumour_rd/exonlist.size()+"\t"+normal_rd/exonlist.size()+"\t"+tumour_rd_ori/exonlist.size()+"\t"+normal_rd_ori/exonlist.size()+"\t"+MinLogRatio+"\t"+MaxLogRatio+"\t"+BinNumber;
            ContraOutTab windowCNVtab = new ContraOutTab(Targeted_Region_ID, Exon_Number, Gene_Sym, Chr, OriStCoordinate, OriEndCoordinate, Mean_of_LogRatio, Adjusted_Mean_of_LogRatio, SD_of_LogRatio, Median_of_LogRatio, number_bases, P_Value, Adjusted_P_Value, gain_loss, tumour_rd, normal_rd, tumour_rd_ori, normal_rd_ori, MinLogRatio, MaxLogRatio, BinNumber);
            return windowCNVtab;
        }
    }
}
