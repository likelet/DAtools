/*
 * This intersect function were used for peak overlap analysis
 */
package BedProcess;

import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import pub.BED;

/**
 *
 * @author Administrator
 */
public class GetIntersectBed {

    ArrayList<BED> bedlist1;
    ArrayList<BED> bedlist2;
    ArrayList<BED> interBedlist = new ArrayList<BED>();
    private String outfile;

    public GetIntersectBed(String bedfile1, String bedfile2,String outfile) {
        this.bedlist1 = BEDReader.ReadBed5(bedfile1);
        this.bedlist2 = BEDReader.ReadBed5(bedfile2);
        this.outfile=outfile;
        this.process();
    }
    
    

    public void process() {
        int i = 0;
        int j = 0;
        String[] strOrder = {"chr1", "chr2", "chr3", "chr4", "chr5", "chr6", "chr7", "chr8", "chr9", "chr10", "chr11", "chr12", "chr13", "chr14", "chr15", "chr16", "chr17", "chr18", "chr19", "chr20", "chr21", "chr22", "chrX", "chrY", "chrM"};
        BED bd1;
        BED bd2;
        while (i <= bedlist1.size() || j <= bedlist2.size()) {
            bd1 = bedlist1.get(i);
            bd2 = bedlist2.get(j);
            if (bd1.getChrom().equalsIgnoreCase(bd2.getChrom())) {
                if (isIntersection(bd1, bd2) == 1) {
                    interBedlist.add(getoverlapsregion(bd1, bd2));
                    i++;
                    j++;
                } else if (bd1.getChromStart() > bd2.getChromStart()) {
                    i++;
                } else {
                    j++;
                }
            } else if (Arrays.asList(strOrder).indexOf(bd1.getChrom().toLowerCase()) < Arrays.asList(strOrder).indexOf(bd2.getChrom().toLowerCase())) {
                i++;
            } else {
                j++;
            }
        }
    }

    public BED getoverlapsregion(BED ib1, BED ib2) {
        BED ib = new BED();
        ib.setChrom(ib1.getChrom());
        if (ib2.getChromStart() >= ib1.getChromStart()) {
            ib.setChromStart(ib2.getChromStart());
            if (ib2.getChromEnd() < ib1.getChromEnd()) {
                ib.setChromEnd(ib2.getChromEnd());
            } else {
                ib.setChromEnd(ib1.getChromEnd());
            }
        } else {
            ib.setChromStart(ib1.getChromStart() );
            if (ib2.getChromEnd() < ib1.getChromEnd()) {
                ib.setChromEnd(ib2.getChromEnd());
            } else {
                ib.setChromEnd(ib1.getChromEnd());
            }
        }

        return ib;
    }

    public int isIntersection(BED bd1, BED bd2) {

        int start1 = bd1.getChromStart();

        int end1 = bd1.getChromEnd();

        int start2 = bd2.getChromStart();
        int end2 = bd2.getChromEnd();
        int midtotallengh = ((end1 - start1) + (end2 - start2)) / 2;
//    System.out.println(midtotallengh);

        int midDistance = Math.abs((end1 + start1) / 2 + (end2 + start2) / 2);
//    System.out.println(midDistance);
        if (midtotallengh > midDistance) {
            return 0;
        } else {
            return 1;
        }
    }
    
    public void writeout() throws IOException{
        FileWriter fw = new FileWriter(outfile);
        for (int i = 0;  i<this.interBedlist.size(); i++) {
            fw.append(this.interBedlist.get(i).print5column()+"\t");
        }
        fw.flush();
        fw.close();  
    }
    
}
