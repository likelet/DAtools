/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package MotifSearch_H;

import java.util.ArrayList;
import java.util.HashMap;

/**
 *
 * @author ZHAO Qi
 * @date 2014-11-7 13:03:09
 * @version 1.6.0
 */
public class getKmer {

    public static ArrayList<Kmer> getkmerlist(String seqname, String str, int length) {
        ArrayList<Kmer> klist = new ArrayList<Kmer>();
        String tempstr = null;
        for (int i = 0; i < str.length() - length + 1; i++) {
            tempstr = str.substring(i, i + length);
            Kmer kmer = new Kmer(seqname, i, i + length, tempstr);
            klist.add(kmer);
        }
        return klist;
    }

    public HashMap<String, kmerFre> getkmerFrelist(String seqname, String str, int length) {
        HashMap<String, kmerFre> kmap = new HashMap<String, kmerFre>();
        String tempstr = null;

        int totalcount = str.length() - length + 1;
        for (int i = 0; i < str.length() - length + 1; i++) {
//            totalcount++;
            tempstr = str.substring(i, i + length);
            if (kmap.keySet().contains(tempstr)) {
                kmap.get(tempstr).setCount();
//               if(tempstr.equals("GCCGGC")){
//                   System.out.println(i+"\t"+(i+ length));
//               }
            } else {
//                if(tempstr.equals("GCCGGC")){
//                   System.out.println(i+"\t"+(i+ length));
//               }
                kmerFre kmerfre = new kmerFre(seqname, i, i + length, tempstr, totalcount);
                kmerfre.setCount();
                kmap.put(tempstr, kmerfre);
            }

        }
        return kmap;
    }
}
