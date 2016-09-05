/*
 * Read Bedfile into List or hashMap
 * 
 */
package pub;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

/**
 *
 * @author ZHAO Qi
 * @date 2013-6-6 17:05:51
 * @version 1.6.0
 */
public class bedReader {
    
    //read BED string into a BED class
    public static BED bedFormat(String bedline){
        String[] strArray =bedline.split("\t");
        if(strArray.length==6){
            BED bd=new BED(strArray[0],Integer.parseInt(strArray[1]),Integer.parseInt(strArray[2]),strArray[3],strArray[5]);
            return bd;
        }else if(strArray.length==12){
             BED bed=new BED( strArray[0],Integer.parseInt(strArray[1]),Integer.parseInt(strArray[2]),strArray[3],Double.parseDouble(strArray[4]),strArray[5],Integer.parseInt(strArray[6]),Integer.parseInt(strArray[7]),strArray[8],Integer.parseInt(strArray[9]),strArray[10],strArray[11]);
             return bed;
        }else {
            return null;
        }    
    }
    
    public static ArrayList<BED> readBedSimple(String bedfile) throws FileNotFoundException, IOException{
        ArrayList<BED> bedlist=new ArrayList<BED>();
        BufferedReader br = new BufferedReader(new FileReader(new File(bedfile)));
        while (br.ready()) {    
            String [] str=br.readLine().trim().split("\t");
            BED bd=new BED(str[0],Integer.parseInt(str[1]),Integer.parseInt(str[2]),str[3],str[5]);
            bedlist.add(bd);
            
        }
        br.close();
        return bedlist;
    }
    
    public static HashMap<String,ArrayList<BED>> getChr2Bed(String bedfile) throws FileNotFoundException, IOException{
         HashMap<String,ArrayList<BED>> BEDmap=new  HashMap<String,ArrayList<BED>>();
        
        
        BufferedReader br = new BufferedReader(new FileReader(new File(bedfile)));
        while (br.ready()) {    
            String bedline=br.readLine().trim();
            BED bd =bedReader.bedFormat(bedline);
            if(BEDmap.keySet().contains(bd.getChrom())){
                BEDmap.get(bd.getChrom()).add(bd);
            }else{
                ArrayList<BED> bedlist=new ArrayList<BED>();
                bedlist.add(bd);
                BEDmap.put(bd.getChrom(), bedlist);
            }    
        }
        br.close();
        return BEDmap;
    }
    
    public static boolean judgeNAT(BED b1,BED b2){
        int lenthDiffer=Math.abs((b1.getChromEnd()-b1.getChromStart())+(b2.getChromEnd()-b2.getChromStart()));
        int positionDiffer=Math.abs((b1.getChromEnd()+b1.getChromStart())-(b2.getChromEnd()+b2.getChromStart()))/2;
        if((!b1.getStrand().equals(b2.getStrand()))&&positionDiffer<=lenthDiffer/2&&b1.getChrom().equals(b2.getChrom())){
            return true;
        }else{
            return false;
        }
        
    }
    
    
    public static boolean judgeIntersect(BED b1,BED b2){
        int lenthDiffer=Math.abs((b1.getChromEnd()-b1.getChromStart())+(b2.getChromEnd()-b2.getChromStart()));
        int positionDiffer=Math.abs((b1.getChromEnd()+b1.getChromStart())-(b2.getChromEnd()+b2.getChromStart()))/2;
        if(b1.getStrand().equals(b2.getStrand())&&positionDiffer<lenthDiffer/2&&b1.getChrom().equals(b2.getChrom())){
            return true;
        }else{
            return false;
        }
        
    }
    
    public static void main(String[] args) {
        BED b1=new BED("chr1",1000,1500,"a","+");
        BED b2=new BED("chr1",1250,1500,"a","-");
        System.out.println(bedReader.judgeIntersect(b1, b2));
    }
}
