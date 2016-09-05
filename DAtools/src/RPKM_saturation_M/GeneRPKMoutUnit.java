/*
 *with a geneModel, the basic information and readscount info ,RPKM value will be contained
 */

package RPKM_saturation_M;

import pub.BED;

/**
 *
 * @author ZHAO Qi
 * @date 2014-5-10 14:53:59
 * @version 1.6.0
 */
public class GeneRPKMoutUnit extends BED{

  
    private int readscount=0;
    private double rpkm=0;
    private int geneEffectLength=0;

    public GeneRPKMoutUnit(String chrom, int chromStart, int chromEnd, String name, double score, String strand, int thickStart, int thickEnd, String itemRgb, int blockCount, String blockSizes, String blockStart) {
        super(chrom, chromStart, chromEnd, name, score, strand, thickStart, thickEnd, itemRgb, blockCount, blockSizes, blockStart);
        String[]exonslength=this.getBlockSizes().split(",");
        for (int i = 0; i < exonslength.length; i++) {
            geneEffectLength+=Integer.parseInt(exonslength[i]);
        }
    }

  

    


    
    /**
     * Get the value of geneEffectLength
     *
     * @return the value of geneEffectLength
     */
    public int getGeneEffectLength() {
        return geneEffectLength;
    }

    /**
     * Set the value of geneEffectLength
     *
     * @param geneEffectLength new value of geneEffectLength
     */
    public void setGeneEffectLength(int geneEffectLength) {
        this.geneEffectLength = geneEffectLength;
    }

    /**
     * Get the value of rpkm
     *
     * @return the value of rpkm
     */
    public double getRpkm() {
        return rpkm;
    }

    /**
     * Set the value of rpkm
     *
     * @param rpkm new value of rpkm
     */
    public void setRpkm(double rpkm) {
        this.rpkm = rpkm;
    }


    /**
     * Get the value of readscount
     *
     * @return the value of readscount
     */
    public int getReadscount() {
        return readscount;
    }

    /**
     * Set the value of readscount
     *
     * @param readscount new value of readscount
     */
    public void setReadscount(int readscount) {
        this.readscount = readscount;
    }


    /**
     * Get the value of strand
     *
     * @return the value of strand
     */
   
    public void readscountAdd(){
        this.readscount++;
    }
    
    
}
