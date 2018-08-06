/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package pub;

/**
 *
 * @author ZHAO Qi
 * @date 2013-6-6 16:59:20
 * @version 1.6.0
 */
public class BED {
    private String chrom;//The name of the chromosome (e.g. chr3, chrY, chr2_random) or scaffold (e.g. scaffold10671).
    private int chromStart;// The starting position of the feature in the chromosome or scaffold. The first base in a chromosome is numbered 0.
    private int chromEnd;//The ending position of the feature in the chromosome or scaffold. The chromEnd base is not included in the display of the feature. For example, the first 100 bases of a chromosome are defined as chromStart=0, chromEnd=100, and span the bases numbered 0-99.
    private String name="";//Defines the name of the BED line. This label is displayed to the left of the BED line in the Genome Browser window when the track is open to full display mode or directly to the left of the item in pack mode. 
    private double score=0;//A score between 0 and 1000. If the track line useScore attribute is set to 1 for this annotation data set, the score value will determine the level of gray in which this feature is displayed (higher numbers = darker gray). This table shows the Genome Browser's translation of BED score values into shades of gray: 
    private String strand="";// Defines the strand - either '+' or '-'. 
    private int thickStart=0;//The starting position at which the feature is drawn thickly (for example, the start codon in gene displays). 
    private int thickEnd=0;//The ending position at which the feature is drawn thickly (for example, the stop codon in gene displays)
    private String itemRgb=""; //An RGB value of the form R,G,B (e.g. 255,0,0). If the track line itemRgb attribute is set to "On", this RBG value will determine the display color of the data contained in this BED line. NOTE: It is recommended that a simple color scheme (eight colors or less) be used with this attribute to avoid overwhelming the color resources of the Genome Browser and your Internet browser. 
    private int blockCount=0;//The number of blocks (exons) in the BED line. 
    private String blockSizes="";//A comma-separated list of the block sizes. The number of items in this list should correspond to blockCount
    private String blockStart="";//A comma-separated list of block starts. All of the blockStart positions should be calculated relative to chromStart. The number of items in this list should correspond to blockCount. 

    public BED() {
    }

    public BED(String chrom, int chromStart, int chromEnd) {
        this.chrom = chrom;
        this.chromStart = chromStart;
        this.chromEnd = chromEnd;
    }

     public BED(String chrom, int chromStart, int chromEnd,String genename) {
        this.chrom = chrom;
        this.chromStart = chromStart;
        this.chromEnd = chromEnd;
        this.name=genename;
    }

    
    public BED(String chrm,int start,int end, String name, String strand) {
        this.chrom=chrm;
        this.chromStart=start;
        this.chromEnd=end;
        this.name=name;
        this.strand=strand;
    }
     public BED(String chrm,int start,int end, String name,double cnv, String strand) {
        this.chrom=chrm;
        this.chromStart=start;
        this.chromEnd=end;
        this.name=name;
        this.strand=strand;
        this.score=cnv;
    }


    public BED(String chrom, int chromStart, int chromEnd, String name, double score, String strand, int thickStart, int thickEnd, String itemRgb, int blockCount, String blockSizes, String blockStart) {
        this.chrom = chrom;
        this.chromStart = chromStart;
        this.chromEnd = chromEnd;
        this.name = name;
        this.score = score;
        this.strand = strand;
        this.thickStart = thickStart;
        this.thickEnd = thickEnd;
        this.itemRgb = itemRgb;
        this.blockCount = blockCount;
        this.blockSizes = blockSizes;
        this.blockStart = blockStart;
    }

    
    
    public int getBlockCount() {
        return blockCount;
    }

    public void setBlockCount(int blockCount) {
        this.blockCount = blockCount;
    }

    public String getBlockSizes() {
        return blockSizes;
    }

    public void setBlockSizes(String blockSizes) {
        this.blockSizes += blockSizes+",";
    }

    public String getBlockStart() {
        return blockStart;
    }

    public void setBlockStart(String blockStart) {
        this.blockStart += blockStart+",";
    }

   

    public String getChrom() {
        return chrom;
    }

    public void setChrom(String chrom) {
        this.chrom = chrom;
    }

    public int getChromEnd() {
        return chromEnd;
    }

    public void setChromEnd(int chromEnd) {
        this.chromEnd = chromEnd;
    }

    public int getChromStart() {
        return chromStart;
    }

    public void setChromStart(int chromStart) {
        this.chromStart = chromStart;
    }

    public String getItemRgb() {
        return itemRgb;
    }

    public void setItemRgb(String itemRgb) {
        this.itemRgb = itemRgb;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getScore() {
        return score;
    }

    public void setScore(double score) {
        this.score = score;
    }

    public String getStrand() {
        return strand;
    }

    public void setStrand(String strand) {
        this.strand = strand;
    }

    public int getThickEnd() {
        return thickEnd;
    }

    public void setThickEnd(int thickEnd) {
        this.thickEnd = thickEnd;
    }

    public int getThickStart() {
        return thickStart;
    }

    public void setThickStart(int thickStart) {
        this.thickStart = thickStart;
    }

    public String print12column(){
        String tempstr="";
        tempstr=chrom+"\t"+chromStart+"\t"+chromEnd+"\t"+name+"\t"+score+"\t"+strand+"\t"+thickStart+"\t"+thickEnd+"\t"+itemRgb+"\t"+blockCount+"\t"+blockSizes+"\t"+blockStart;
        tempstr=tempstr.substring(0,tempstr.length()-1);
        return tempstr;
    }
    public String print5column(){
        String tempstr="";
        tempstr=chrom+"\t"+chromStart+"\t"+chromEnd+"\t"+name+"\t"+strand;
        
        return tempstr;
    }
    public String print3column(){
        String tempstr="";
        tempstr=chrom+"\t"+chromStart+"\t"+chromEnd;
        
        return tempstr;
    }
    public String printScore(){
        String tempstr="";
        int a=(int) score;
        tempstr=chrom+"\t"+chromStart+"\t"+chromEnd+"\t"+name+"\t"+a+"\t"+strand;
        
        return tempstr;
    }
 
}
