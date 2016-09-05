/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package ExomeSeqAnalysisPipe.Var2scan2ICGCformat;

import java.util.HashMap;

/**
 *
 * @author ZHAO Qi
 * @date 2015-7-30 12:42:12
 * @version 1.6.0
 */
public class IUPACcode {
public static String  getIUPACcode(String str){
        HashMap<String, String> map = new HashMap<String, String>();
        map.put("A", "A/A");
        map.put("C", "C/C");
        map.put("G", "G/G");
        map.put("T", "T/T");
        map.put("R", "A/G");
        map.put("Y", "C/T");
        map.put("S", "G/C");
        map.put("W", "A/T");
        map.put("K", "G/T");
        map.put("M", "A/C");
        map.put("B", "C/G/T");
        map.put("D", "A/G/T");
        map.put("H", "A/C/T");
        map.put("V", "A/C/G");
        map.put("N", "N");
        return map.get(str);
    }
}
