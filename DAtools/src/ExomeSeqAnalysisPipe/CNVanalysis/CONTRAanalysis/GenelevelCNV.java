/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ExomeSeqAnalysisPipe.CNVanalysis.CONTRAanalysis;

import java.util.ArrayList;

/**
 * <p>
 * GenelevelCNV</p>
 * <p>
 * Created on 2015-12-15 16:00:24</p>
 * <p>
 * Author Email: zhaoqi3@mail2.sysu.edu.cn</p>
 *
 * @author ZHAO Qi
 * @date 2015-12-15 16:00:24
 * @version java 1.6.0
 * @version
 */
public class GenelevelCNV {

    private String genename;
    private ArrayList<ContraOutTab> exonlist = new ArrayList<ContraOutTab>();
    private String geneCTab;

    public GenelevelCNV(String genename) {
        this.genename = genename;
    }

    public void addContraOutTab(ContraOutTab ct) {
        this.exonlist.add(ct);
    }

    public ContraOutTab getGeneCTab() {
        ContraOutTab ct=exonlist.get(0);
        String Targeted_Region_ID=ct.getTargeted_Region_ID();
        double Exon_Number=exonlist.size();
        String Gene_Sym=ct.getGene_Sym();
        String Chr=ct.getChr();
        double OriStCoordinate=ct.getOriStCoordinate();
        double OriEndCoordinate=exonlist.get(exonlist.size()-1).getOriEndCoordinate();
        double Mean_of_LogRatio=0;
        double Adjusted_Mean_of_LogRatio=0;
        double SD_of_LogRatio=0;
        double Median_of_LogRatio=0;
        double number_bases=0;
        double P_Value=0;
        double Adjusted_P_Value=0;
        String gain_loss=ct.getGain_loss();
        double tumour_rd=0;
        double normal_rd=0;
        double tumour_rd_ori=0;
        double normal_rd_ori=0;
        double MinLogRatio=ct.getMinLogRatio();
        double MaxLogRatio=ct.getMaxLogRatio();
        double BinNumber = ct.getBinNumber();

        for (int i = 1; i < exonlist.size(); i++) {
            ContraOutTab ct1=exonlist.get(i);
            Mean_of_LogRatio += ct1.getMean_of_LogRatio();
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

        Mean_of_LogRatio = Mean_of_LogRatio / exonlist.size();
        Adjusted_Mean_of_LogRatio = Adjusted_Mean_of_LogRatio / exonlist.size();
        SD_of_LogRatio = SD_of_LogRatio / exonlist.size();
        P_Value = P_Value / exonlist.size();
        Adjusted_P_Value = Adjusted_P_Value / exonlist.size();
        tumour_rd = tumour_rd / exonlist.size();
        normal_rd = normal_rd / exonlist.size();
        tumour_rd_ori = tumour_rd_ori / exonlist.size();
        normal_rd_ori = normal_rd_ori / exonlist.size();
//         geneCTab=Targeted_Region_ID+"\t"+Exon_Number+"\t"+Gene_Sym+"\t"+Chr+"\t"+OriStCoordinate+"\t"+OriEndCoordinate+"\t"+Mean_of_LogRatio/exonlist.size()+"\t"+Adjusted_Mean_of_LogRatio/exonlist.size()+"\t"+SD_of_LogRatio/exonlist.size()+"\t"+Median_of_LogRatio/exonlist.size()+"\t"+number_bases+"\t"+P_Value/exonlist.size()+"\t"+Adjusted_P_Value/exonlist.size()+"\t"+gain_loss+"\t"+tumour_rd/exonlist.size()+"\t"+normal_rd/exonlist.size()+"\t"+tumour_rd_ori/exonlist.size()+"\t"+normal_rd_ori/exonlist.size()+"\t"+MinLogRatio+"\t"+MaxLogRatio+"\t"+BinNumber;
       ContraOutTab geneCTab=new ContraOutTab(Targeted_Region_ID,Exon_Number,Gene_Sym,Chr,OriStCoordinate,OriEndCoordinate,Mean_of_LogRatio,Adjusted_Mean_of_LogRatio,SD_of_LogRatio,Median_of_LogRatio,number_bases,P_Value,Adjusted_P_Value,gain_loss,tumour_rd,normal_rd,tumour_rd_ori,normal_rd_ori,MinLogRatio,MaxLogRatio,BinNumber);
        return geneCTab;
    }
    
    
}
