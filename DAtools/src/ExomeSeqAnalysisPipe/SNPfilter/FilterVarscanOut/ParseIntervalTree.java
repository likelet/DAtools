/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package ExomeSeqAnalysisPipe.SNPfilter.FilterVarscanOut;

import ExomeSeqAnalysisPipe.VCFrecord;
import ExomeSeqAnalysisPipe.Var2scan2ICGCformat.VarscanCNVFormat;
import htsjdk.samtools.util.IntervalTree;

import java.util.ArrayList;
import pub.BED;

/**
 *
 * @author Administrator
 * @since 2017-5-9
 * @coding time 13:43:05
 * @author Qi Zhao
 */
public class ParseIntervalTree {
    public IntervalTree getSCNVintervalTreeWithExtendBase(ArrayList<VarscanCNVFormat> SCNVlist, int extendBaseNumber){
         IntervalTree CNVIntervalTree = new IntervalTree();
         VarscanCNVFormat vscnv=null;
         for (int i = 0; i < SCNVlist.size(); i++) {
          vscnv=SCNVlist.get(i);
          CNVIntervalTree.put(Integer.parseInt(vscnv.getChr_start())-extendBaseNumber, Integer.parseInt(vscnv.getChr_stop())+extendBaseNumber, vscnv);
        }
         return CNVIntervalTree;
    }
    
    public IntervalTree getSCNVintervalTree(ArrayList<VarscanCNVFormat> SCNVlist){
         IntervalTree CNVIntervalTree = new IntervalTree();
         VarscanCNVFormat vscnv=null;
         for (int i = 0; i < SCNVlist.size(); i++) {
          vscnv=SCNVlist.get(i);
          CNVIntervalTree.put(Integer.parseInt(vscnv.getChr_start()), Integer.parseInt(vscnv.getChr_stop()), vscnv);
        }
         return CNVIntervalTree;
    }
    
    public IntervalTree getInDelIntervalTreeWithExtendBase(ArrayList<VCFrecord> Indellist, int extendBaseNumber){
         IntervalTree CNVIntervalTree = new IntervalTree();
         VCFrecord vcf=null;
         int start;
         int end;
         for (int i = 0; i < Indellist.size(); i++) {
          vcf=Indellist.get(i);
          //start=Integer.parseInt(vcf.get)
         // CNVIntervalTree.put(-extendBaseNumber, Integer.parseInt(vscnv.getChr_stop())+extendBaseNumber, vcf);
        }
         return CNVIntervalTree;
    }
 
    
    public IntervalTree getBEDIntervalTreeWithExtendBase(ArrayList<BED> SCNVlist, int extendBaseNumber){
         IntervalTree CNVIntervalTree = new IntervalTree();
         BED bed=null;
         for (int i = 0; i < SCNVlist.size(); i++) {
          bed=SCNVlist.get(i);
          CNVIntervalTree.put(bed.getChromStart()-extendBaseNumber, bed.getChromEnd()+extendBaseNumber, bed);
        }
         return CNVIntervalTree;
    }
        
}
