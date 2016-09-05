/*
 * static frequency in a array
 */

package pub;

import java.util.HashMap;
import java.util.Iterator;

/**
 *
 * @author ZHAO Qi
 * @date 2015-3-31 14:51:34
 * @version 1.6.0
 */
public class TableCount {
 public static String getTablestr  (String [] a){
     String str="";
     HashMap<String,Integer> str2count=new HashMap<String,Integer>();
     for (int i = 0; i < a.length; i++) {
         String tempstr=a[i];
         if(str2count.keySet().contains(tempstr)){
             str2count.put(tempstr,str2count.get(tempstr)+1);
         }else{
             str2count.put(tempstr,1);
         }
     }
     for (Iterator it = str2count.keySet().iterator(); it.hasNext();) {
         String  tempstr1=(String)it.next();
         str+=tempstr1+":"+str2count.get(tempstr1)+"||";
     }
     return str;
 }
 
    public static void main(String[] args) {
        String str="asdsad\tasdaf\ta\ta\ta\ta\ta";
        System.out.println(TableCount.getTablestr(str.split("\t")));
    }
}
