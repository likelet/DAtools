/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package ExomeSeqAnalysisPipe.Var2scan2ICGCformat;

import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author ZHAO Qi
 * @date 2015-7-29 17:31:43
 * @version 1.6.0
 */
public class SomaticeProcessing {

    public ICGCssmP getICGCssmP(VarscanSomaticFormat vsf,String analysisid,String sampleid , String mutationtype){
        ICGCssmP isp=new ICGCssmP();
        isp.setAnalysis_id(analysisid);
        isp.setAnalyzed_sample_id(sampleid);
        isp.setMutation_type(mutationtype);//tobe adjust
        isp.setChromosome(vsf.getChrom().replaceAll("[cC]hr", ""));
        isp.setChromosome_start(vsf.getPosition());
        isp.setChromosome_end(String.valueOf(Integer.parseInt(vsf.getPosition())));
        isp.setReference_genome_allele(vsf.getRef());
        
        isp.setControl_genotype(IUPACcode.getIUPACcode(vsf.getNormal_gt()));
        isp.setTumour_genotype(IUPACcode.getIUPACcode(vsf.getTumor_gt()));
        
        isp.setMutated_from_allele(vsf.getRef());
        isp.setMutated_to_allele(vsf.getVar());
        int totalreads=Integer.parseInt(vsf.getTumor_reads1())+Integer.parseInt(vsf.getTumor_reads2());
        int varreads=Integer.parseInt(vsf.getTumor_reads2());
        isp.setTotal_read_count(String.valueOf(totalreads));
        isp.setMutant_allele_read_count(String.valueOf(varreads));
        isp.setProbability(String.valueOf(1-Double.parseDouble(vsf.getSomatic_p_value())));
        return isp;
    }
    public void processing(String vsfinputfile,String ssmpoutfile,String analysisid,String sampleid ){
        ArrayList<VarscanSomaticFormat> vsflist=ParseClass.ParseVarscanSomaticFormat(vsfinputfile);
        
        ArrayList<ICGCssmP> ssmplist=new ArrayList<ICGCssmP>();
        for (int i = 0; i < vsflist.size(); i++) {
            ICGCssmP isp=this.getICGCssmP(vsflist.get(i), analysisid, sampleid, "1");
            ssmplist.add(isp);
        }
        
        //write out
       
        try {
            FileWriter fw = new FileWriter(ssmpoutfile);
            fw.append("analysis_id	analyzed_sample_id	mutation_type	chromosome	chromosome_start	chromosome_end	chromosome_strand	reference_genome_allele	control_genotype	mutated_from_allele	mutated_to_allele	tumour_genotype	expressed_allele	quality_score	probability	total_read_count	mutant_allele_read_count	verification_status	verification_platform	biological_validation_status	biological_validation_platform\r\n");
            for (int i = 0; i < ssmplist.size(); i++) {
              fw.append(ssmplist.get(i).toString()+"\r\n");
              fw.flush();
            }
            
            fw.close();
        } catch (IOException ex) {
            Logger.getLogger(SomaticeProcessing.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    public static void main(String[] args) {
//        String a="Chr8";   
//        System.out.println(a.replaceAll("[cC]hr",""));
        new SomaticeProcessing().processing("test.snp", "test.ssmp", "test1", "test2");
    }
}
