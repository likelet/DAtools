/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package DoseCompensation.ResultStatistic;

import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.regex.Pattern;

/**
 * <p>StaticChrome</p>
 * <p>Created on 2015-12-25 15:07:41</p>
 * <p>Author Email: zhaoqi3@mail2.sysu.edu.cn</p>
 * @author ZHAO Qi
 * @date 2015-12-25 15:07:41
 * @version java 1.6.0
 * @version 
 */
public class StaticChrome {

    private String rpkmfile;//for columns: gene count length rpkm;
    private ArrayList<ReadRPKMfile.RPKMterm> ParseRPKMlist;
   
    
    private double maxlimit=100000000;//threshold for conculated result
    private double minlimit=0;//threshold for conculated result
    
    private ArrayList<ReadRPKMfile.RPKMterm> normallist = new ArrayList<ReadRPKMfile.RPKMterm>();
    private ArrayList<ReadRPKMfile.RPKMterm> Xlist = new ArrayList<ReadRPKMfile.RPKMterm>();

    public StaticChrome(ArrayList<ReadRPKMfile.RPKMterm> normallist,ArrayList<ReadRPKMfile.RPKMterm> Xlist) {
        this.normallist=normallist;
        this.Xlist=Xlist;
        this.getNormAndXtrimmedMedianRPKM();
    }

    
    
    
    public StaticChrome(String rpkmfile) {
        this.rpkmfile = rpkmfile;
        ParseRPKMlist=new ReadRPKMfile(rpkmfile).getParseRPKM();
//        System.out.println(ParseRPKMlist.size());
        this.filterAndClusterData();
//        this.getNormAndXmedianRPKM();
        this.getNormAndXtrimmedMedianRPKM();
        
    }
    
  
    
    

    
    public void filterAndClusterData(){
       //filter data 
         String regex = "^chr[1-9].*";  
//         System.out.println(ParseRPKMlist.size());
       for (int i = 0; i < ParseRPKMlist.size(); i++) {
           ReadRPKMfile.RPKMterm rpkmterm = ParseRPKMlist.get(i);
           if (rpkmterm.getRpkm() >= minlimit && rpkmterm.getRpkm() <= maxlimit) {
//               System.out.println(rpkmterm.getChr());
               if (rpkmterm.getChr().equalsIgnoreCase("chrX")) {
                   Xlist.add(rpkmterm);
               } else if(Pattern.matches(regex, rpkmterm.getChr())){
                   normallist.add(rpkmterm);
               }
           }
       }
}
  
    
    
   public void getNormAndXmedianRPKM() {
        double[] normalarr = new double[normallist.size()];
        double[] xarr = new double[Xlist.size()];;
        for (int i = 0; i < normallist.size(); i++) {
            normalarr[i] = normallist.get(i).getRpkm();
        }
        for (int i = 0; i < Xlist.size(); i++) {
            xarr[i] = Xlist.get(i).getRpkm();
        }
       
        System.out.println(new File(rpkmfile).getName()+"\t"+this.getMedian(normalarr)+"\t"+ this.getMedian(xarr));


    }
   //further analysis without top 20% and bottom 20%
      public void getNormAndXtrimmedMedianRPKM() {
        double[] normalarr = new double[normallist.size()];
        double[] xarr = new double[Xlist.size()];;
        for (int i = 0; i < normallist.size(); i++) {
            normalarr[i] = normallist.get(i).getRpkm();
        }
        for (int i = 0; i < Xlist.size(); i++) {
            xarr[i] = Xlist.get(i).getRpkm();
        }
//          System.out.println(xarr.length);
//          System.out.println(Xlist.size());
//          System.out.println(normallist.size());
//       System.out.println("FileName\tNormal Median\t X Median" );
        System.out.println(new File(rpkmfile).getName()+"\t"+getTrimmedBottomMedian(normalarr,0.2)+"\t"+ getTrimmedBottomMedian(xarr,0.2));
//       System.out.println(Median);
        //static data 
    }
   

    public double getMedian(double[] numArray) {
        Arrays.sort(numArray);
        double median;
        if (numArray.length % 2 == 0) {
            median = ((double) numArray[numArray.length / 2] + (double) numArray[numArray.length / 2 - 1]) / 2;
        } else {
            median = (double) numArray[numArray.length / 2];
        }
        return median;
    }
    public double getTrimmedBottomMedian(double[] numArray,double percentage) {
        Arrays.sort(numArray);
        int trimmedstart=(int) (numArray.length*percentage);
        int leftlength=numArray.length-trimmedstart;
        double median;
        if ((leftlength) % 2 == 0) {
            median = ((double) numArray[leftlength / 2+trimmedstart] + (double) numArray[leftlength / 2+trimmedstart - 1]) / 2;
        } else {
            median = (double) numArray[leftlength / 2+trimmedstart];
        }
        return median;
    }

    public static void main(String[] args) {
        new StaticChrome("F:\\百度云同步盘\\resouces\\projects\\splicing-进化\\gtf\\SRR015341_25bp_free.rpkm");
//        File[] filelist=FilelistReader.getFileList("F:\\百度云同步盘\\resouces\\projects\\splicing-进化\\testRNAseq");
//        
//        for (int i = 0; i < filelist.length; i++) {
//             
//            
//            new StaticChrome(filelist[i].getAbsolutePath());
//        }
        String regex = "^[0-9].*";
        System.out.println(Pattern.matches(regex, "1a"));
    }
    
}
