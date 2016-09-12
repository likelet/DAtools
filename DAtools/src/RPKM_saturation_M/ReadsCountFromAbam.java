/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package RPKM_saturation_M;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import net.sf.samtools.SAMFileReader;
import net.sf.samtools.SAMRecord;

/**
 *
 * @author ZHAO Qi
 * @date 2014-5-10 15:43:00
 * @version 1.6.0
 */
public class ReadsCountFromAbam {
    HashMap<String,ArrayList<genomebin>> genomebinmap;
       public void ReadsCountFromAbam(String bamfilepath,HashMap<String ,ArrayList<GeneRPKMoutUnit>> chr2GeneRPKMoutUnitMap){
        File bamfile = new File(bamfilepath);
        SAMFileReader sr = new SAMFileReader(bamfile, new File(bamfile.getAbsolutePath() + ".bai"));
        for (Iterator it = sr.iterator(); it.hasNext();) {
            SAMRecord sitem = (SAMRecord) it.next();
            String  chrom=sitem.getReferenceName();
            int start = sitem.getAlignmentStart();
            int end = sitem.getAlignmentEnd();
        }

    }
       class genomebin{
       
           private String chr;
           
           private int start;
           private int end;
           private String strand = "+";
           private int count;

           /**
            * Get the value of count
            *
            * @return the value of count
            */
           public int getCount() {
               return count;
           }

           /**
            * Set the value of count
            *
            * @param count new value of count
            */
           public void setCount(int count) {
               this.count = count;
           }


           
           
           ;

        public genomebin(String chr, int start, int end) {
            this.chr = chr;
            this.start = start;
            this.end = end;
        }

    /**
     * Get the value of strand
     *
     * @return the value of strand
     */
    public String getStrand() {
               return strand;
           }

           /**
            * Set the value of strand
            *
            * @param strand new value of strand
            */
           public void setStrand(String strand) {
               this.strand = strand;
           }


           /**
            * Get the value of end
            *
            * @return the value of end
            */
           public int getEnd() {
               return end;
           }

           /**
            * Set the value of end
            *
            * @param end new value of end
            */
           public void setEnd(int end) {
               this.end = end;
           }


           /**
            * Get the value of start
            *
            * @return the value of start
            */
           public int getStart() {
               return start;
           }

           /**
            * Set the value of start
            *
            * @param start new value of start
            */
           public void setStart(int start) {
               this.start = start;
           }


           /**
            * Get the value of chr
            *
            * @return the value of chr
            */
           public String getChr() {
               return chr;
           }

           /**
            * Set the value of chr
            *
            * @param chr new value of chr
            */
           public void setChr(String chr) {
               this.chr = chr;
           }

       }
}
