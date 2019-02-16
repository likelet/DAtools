/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package FastQprocess;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.logging.Level;
import java.util.logging.Logger;
import pub.FastQ;
import pub.FastQReader;
import pub.Tools;

/**
 *
 * @author Administrator
 * @since 2018-12-18
 * @coding time 10:11:03
 * @author Qi Zhao
 */
public class MatchFastQ {

    private HashMap<String, FastQ> reads2FastqMap1 ;
    private HashMap<String, FastQ> reads2FastqMap2 ;
    private ArrayList<String> readslist ;
    private String outfilepreFix;

    public MatchFastQ() {
    }

    public MatchFastQ(String fastq1, String fastq2, String outfilepreFix) throws IOException {
        FastQReader fr1=new FastQReader(fastq1);
        FastQReader fr2=new FastQReader(fastq2);
        
        reads2FastqMap1=fr1.getReads2FastqMap();
        reads2FastqMap2=fr2.getReads2FastqMap();
        readslist=fr1.getReadsNamelist();     
        this.outfilepreFix = outfilepreFix;
    }
    
    public void process() {
         StringBuffer readstr1 = new StringBuffer();
        StringBuffer readstr2 = new StringBuffer();
        int count1=0;
        for (int i = 0; i < readslist.size(); i++) {
            String tmpreadName=readslist.get(i);
            if(reads2FastqMap1.get(tmpreadName)!=null&&reads2FastqMap2.get(tmpreadName)!=null){
                readstr1.append(reads2FastqMap1.get(tmpreadName).toString()+"\n");
                readstr2.append(reads2FastqMap2.get(tmpreadName).toString()+"\n");
                count1++;
            }
        }
        
        System.out.println("filtered Reads Number = "+ count1 );
        try {
            Tools.writeIntoGZ(readstr1.toString(), outfilepreFix+"_1.fq.gz");
        } catch (UnsupportedEncodingException ex) {
            Logger.getLogger(MatchFastQ.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(MatchFastQ.class.getName()).log(Level.SEVERE, null, ex);
        }
        try {
            Tools.writeIntoGZ(readstr2.toString(), outfilepreFix+"_2.fq.gz");
        } catch (UnsupportedEncodingException ex) {
            Logger.getLogger(MatchFastQ.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(MatchFastQ.class.getName()).log(Level.SEVERE, null, ex);
        }
        
}
    
    
    
    
    
    
    
}
