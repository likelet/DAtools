/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package pub;

import java.util.HashMap;

/**
 *
 * @author zhaoqi
 */
public class DNAsequenceProcess {
    private HashMap<String,String> complimentaryMap=new HashMap<String,String> ();

    public DNAsequenceProcess() {
        complimentaryMap.put("A", "T");
        complimentaryMap.put("T", "A");
        complimentaryMap.put("C", "G");
        complimentaryMap.put("G", "C");  
        complimentaryMap.put("N", "N"); 
        complimentaryMap.put("a", "t");
        complimentaryMap.put("t", "a");
        complimentaryMap.put("c", "g");
        complimentaryMap.put("g", "c");  
    }
    
 //返回一段序列的反向互补序列   
    public  String getReverseComplimentary(String str){
    String tempstr="";
    String temps="";
     
    for (int i = str.length()-1; i >=0; i--) {
        temps=String.valueOf(str.charAt(i));
        temps=complimentaryMap.get(temps);
        tempstr+=temps;
    }
    return tempstr;
}
    
    public static void main(String[] args) {
        System.out.println(new DNAsequenceProcess().getReverseComplimentary("GAGACACCTCGGTTCTATCGATTGATCAACAAGTTTGTACAAAAAAGCAGGCT"));
        System.out.println(new DNAsequenceProcess().getReverseComplimentary("GAGACGGGGATCAACCACTTTGTACAAGAAAGCTGGGT"));
        System.out.println(new DNAsequenceProcess().getReverseComplimentary("GAGAcgccctgagcaaagaccccaacgagaagcgcgatcacatggtcctgctggagttcgtgaccgccgccgggatcactctcggcatggacgagctgtacaagtttgtacaaaaaagcaggct".toUpperCase()));
        System.out.println(new DNAsequenceProcess().getReverseComplimentary("GAGAcgaccactgtgctggcgaattagcCGACCACTGTGCTGGCGAATTAGCTTCTGCAGGTCGACTCTAGAGGATCCCCGGGGAATTATCAACCACTTTGTACAAGAAAGCTGGGT".toUpperCase()));
        
    }    
}
