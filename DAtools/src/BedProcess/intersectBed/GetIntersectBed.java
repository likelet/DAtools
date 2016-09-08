/*
 * This intersect function were used for peak overlap analysis without considering strand

 */
package BedProcess.intersectBed;

import BedProcess.BEDReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Iterator;
import java.util.logging.Level;
import java.util.logging.Logger;
import pub.BED;

/**
 *
 * @author Administrator
 */
public class GetIntersectBed {

//    ArrayList<BED> bedlist1;
//    ArrayList<BED> bedlist2;
    ArrayList<BED> interBedlist = new ArrayList<BED>();
    ArrayList<String> strlist=new ArrayList<String>();
    private String outfile;
    HashMap<String,ArrayList<BED>> map1;
    HashMap<String,ArrayList<BED>> map2;

    public GetIntersectBed() {
    }
    
    

    public GetIntersectBed(String bedfile1, String bedfile2,String outfile) {
        this.map1 = BEDReader.ReadBed5ToMap(bedfile1);
        this.map2 = BEDReader.ReadBed5ToMap(bedfile2);
        this.outfile=outfile;
        this.process();
        try {
            this.writeout();
        } catch (IOException ex) {
            Logger.getLogger(GetIntersectBed.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    

    public void process() {
        int i = 0;
        int j = 0;
//        String[] strOrder = {"chr1", "chr2", "chr3", "chr4", "chr5", "chr6", "chr7", "chr8", "chr9", "chr10", "chr11", "chr12", "chr13", "chr14", "chr15", "chr16", "chr17", "chr18", "chr19", "chr20", "chr21", "chr22", "chrX", "chrY", "chrM"};
//        
        BED bd1;
        BED bd2;
        int m = 0;

        for (Iterator it = map1.keySet().iterator(); it.hasNext();) {
            String keystr = (String) it.next();
//            System.out.println(keystr);
            ArrayList<BED> bedlist1 = map1.get(keystr);
            ArrayList<BED> bedlist2 = map2.get(keystr);
            for (int k = 0; k < bedlist1.size(); k++) {
                bd1 = bedlist1.get(k);
                for (int l = 0; l < bedlist2.size(); l++) {
                    bd2 = bedlist2.get(l);
                    if (isIntersection(bd1, bd2) == 1) {
//                    System.out.println("!");
                        interBedlist.add(getoverlapsregion(bd1, bd2));
                        strlist.add(bd1.printScore() + "\t" + bd2.printScore());
                    }else if (bd2.getChromStart()>bd1.getChromEnd()){
                        break;
                    }
                }
            }
//            while (i < bedlist1.size() && j < bedlist2.size()) {
//                bd1 = bedlist1.get(i);
//                System.out.println(bd1.printScore());
//                bd2 = bedlist2.get(j);
//
//                
//                
//                if (isIntersection(bd1, bd2) == 1) {
////                    System.out.println("!");
//                    interBedlist.add(getoverlapsregion(bd1, bd2));
//                    strlist.add(bd1.printScore() + "\t" + bd2.printScore());
//                    if (bd1.getChromStart() > bd2.getChromStart()) {
//                        j++;
//                    } else {
//                        i++;
//                    }
//
//                } else if (bd1.getChromStart() > bd2.getChromStart()) {
//                    j++;
//                } else {
//                    i++;
//                }
//
//            }
        }


        System.out.println("Length of intersect bed is "+interBedlist.size());
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

        int midDistance = Math.abs((end1 + start1) / 2 -(end2 + start2) / 2);
//    System.out.println(midDistance);
        if (midtotallengh >= midDistance) {
            return 1;
        } else {
            return 0;
        }
    }
    
    public void writeout() throws IOException{
        FileWriter fw = new FileWriter(outfile);
        for (int i = 0;  i<this.interBedlist.size(); i++) {
            fw.append(this.interBedlist.get(i).print5column()+"\t"+strlist.get(i)+"\r\n");
        }
        fw.flush();
        fw.close();  
    }
    public static void main(String[] args) {
        new GetIntersectBed("E:\\project\\金正京\\DMRanalysis-20160821\\withIgG\\overlapbed\\inc\\1vs2.txt","E:\\project\\金正京\\DMRanalysis-20160821\\withIgG\\overlapbed\\inc\\3vs4.txt","E:\\project\\金正京\\DMRanalysis-20160821\\withIgG\\overlapbed\\inc\\result.txt");
//        System.out.println(new GetIntersectBed().isIntersection(new BED("chrX",1,20,"+",""), new BED("chrX",19,20,"+","")));
    }
    
}
