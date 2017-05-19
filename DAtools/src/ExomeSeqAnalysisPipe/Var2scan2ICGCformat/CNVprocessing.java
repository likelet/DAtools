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
 * @date 2015-7-30 14:19:42
 * @version 1.6.0
 */
public class CNVprocessing {
  public ICGCcnsmP getICGCcnsmP(VarscanCNVFormat vsf,String analysisid,String sampleid , String mutationid){
        ICGCcnsmP isp=new ICGCcnsmP();
       isp.setAnalysis_id(analysisid);
       isp.setAnalyzed_sample_id(sampleid);
       isp.setMutation_id(mutationid);
        return isp;
    }
    public void processing(String vsfinputfile,String ssmpoutfile,String analysisid,String sampleid ){
        ArrayList<VarscanCNVFormat> vsflist=ParseVarscanClass.ParseVarscanCNVFormat(vsfinputfile);
        
        ArrayList<ICGCcnsmP> ssmplist=new ArrayList<ICGCcnsmP>();
        for (int i = 0; i < vsflist.size(); i++) {
            ICGCcnsmP isp=this.getICGCcnsmP(vsflist.get(i), analysisid, sampleid, "1");
            ssmplist.add(isp);
        }
        
        //write out
       
        try {
            FileWriter fw = new FileWriter(ssmpoutfile);
            fw.append("analysis_id	analyzed_sample_id	mutation_id	start_probe_id	end_probe_id	chromosome	chromosome_start	chromosome_end	chromosome_start_range	chromosome_end_range	mutation_type	copy_number	segment_mean	segment_median	quality_score	probability	is_annotated	verification_status	verification_platform	biological_validation_status	biological_validation_platform	\r\n");
            for (int i = 0; i < ssmplist.size(); i++) {
              fw.append(ssmplist.toString()+"\r\n");
              fw.flush();
            }
            
            fw.close();
        } catch (IOException ex) {
            Logger.getLogger(SomaticeProcessing.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
