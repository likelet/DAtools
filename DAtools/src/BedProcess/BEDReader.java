/*
 * now support 3 or 5 columns bed read
 */
package BedProcess;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.logging.Level;
import java.util.logging.Logger;
import pub.BED;

/**
 *
 * @author Administrator
 */
public class BEDReader {

    public static ArrayList<BED> ReadBed5(String bedfile) {
        ArrayList<BED> bedlist = new ArrayList<BED>();
        System.out.println("Read bed format file"+bedfile);
        BufferedReader br;
        try {
            br = new BufferedReader(new java.io.FileReader(new File(bedfile)));
            while (br.ready()) {
                String[] dataStr = br.readLine().trim().split("\t");
                BED bd ;
                if(dataStr.length==5){
                    bd = new BED(dataStr[0], Integer.parseInt(dataStr[1]), Integer.parseInt(dataStr[2]), dataStr[3], dataStr[4]);
                }else{
                    bd = new BED(dataStr[0], Integer.parseInt(dataStr[1]), Integer.parseInt(dataStr[2]));
                }
                bedlist.add(bd);
            }
        } catch (FileNotFoundException ex) {
            Logger.getLogger(BEDReader.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(BEDReader.class.getName()).log(Level.SEVERE, null, ex);
        }
        Collections.sort(bedlist, new CompareBed());
        System.out.println("The entries of "+bedfile+" is "+bedlist.size());
        return bedlist;
    }
    
    public static HashMap<String,ArrayList<BED>> ReadBed5ToMap(String bedfile) {
        HashMap<String,ArrayList<BED>> bedset=new HashMap<String,ArrayList<BED>>();
        HashSet<String> nameset=new HashSet<String>();
        
        System.out.println("Read bed format file"+bedfile);
        int count=0;
        BufferedReader br;
        try {
            br = new BufferedReader(new java.io.FileReader(new File(bedfile)));
            while (br.ready()) {
                String[] dataStr = br.readLine().trim().split("\t");
                count++;
                BED bd ;
                if(dataStr.length==5){
                    bd = new BED(dataStr[0], Integer.parseInt(dataStr[1]), Integer.parseInt(dataStr[2]), dataStr[3], dataStr[4]);
                }else{
                    bd = new BED(dataStr[0], Integer.parseInt(dataStr[1]), Integer.parseInt(dataStr[2]));
                }
               
                if(nameset.add(bd.getChrom())){
                    ArrayList<BED> bedlist = new ArrayList<BED>();
                    bedlist.add(bd);
                    bedset.put(bd.getChrom(), bedlist);
                }else{
                    bedset.get(bd.getChrom()).add(bd);
                }
                
            }
        } catch (FileNotFoundException ex) {
            Logger.getLogger(BEDReader.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(BEDReader.class.getName()).log(Level.SEVERE, null, ex);
        }
        for (Iterator it = bedset.keySet().iterator(); it.hasNext();) {
            String keystr = (String) it.next();
            Collections.sort(bedset.get(keystr), new CompareBedWithoutChr());
        }
        
        System.out.println("The entries of "+bedfile+" is "+count);
        return bedset;
    }
}


//Dec
class CompareBed implements Comparator {

    @Override
    public int compare(Object o1, Object o2) {
        BED num1 = (BED) o1;
        BED num2 = (BED) o2;
        String[] strOrder = {"chr1", "chr2", "chr3", "chr4", "chr5", "chr6", "chr7", "chr8", "chr9", "chr10", "chr11", "chr12", "chr13", "chr14", "chr15", "chr16", "chr17", "chr18", "chr19", "chr20", "chr21", "chr22", "chrX", "chrY", "chrM"};
        if (num1.getChrom().equalsIgnoreCase(num2.getChrom())) {
            if (num1.getChromStart() < num2.getChromEnd()) {
                return -1;
            } else if (num1.getChromStart() == num2.getChromStart()) {
                return 0;
            } else {
                return 1;
            }
        } else if (Arrays.asList(strOrder).indexOf(num1.getChrom().toLowerCase()) < Arrays.asList(strOrder).indexOf(num2.getChrom().toLowerCase())) {
            return -1;
        } else {
            return 1;
        }
    }
    
    

}
class CompareBedWithoutChr implements Comparator {

    @Override
    public int compare(Object o1, Object o2) {
        BED num1 = (BED) o1;
        BED num2 = (BED) o2;
            if (num1.getChromStart() < num2.getChromEnd()) {
                return -1;
            } else if (num1.getChromStart() == num2.getChromStart()) {
                return 0;
            } else {
                return 1;
            }
        
      
    }
    
    

}