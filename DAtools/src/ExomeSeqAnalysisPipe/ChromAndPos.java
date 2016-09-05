/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ExomeSeqAnalysisPipe;

/**
 *
 * @author Yueyuan
 */
public class ChromAndPos {
   private String chrom;
   private String pos;
   ChromAndPos(String chrom,String pos){
       this.chrom=chrom;
       this.pos=pos;
   }
   public String getChrom(){
       return chrom;
   }
   public String getPos(){
       return pos;
   }
   public String toString(){
       return chrom+"\t"+pos;
   }
}
