/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ExomeSeqAnalysisPipe;

import java.util.ArrayList;
import java.util.Comparator;

/**
 *
 * @author Yueyuan
 */
public class Comparedata implements Comparator {

    @Override
    public int compare(Object o1, Object o2) {
        String num1=(String) o1;
        String num2=(String) o2;
        if(Integer.parseInt(num1)<Integer.parseInt(num2)){
            return -1;
        }else if(Integer.parseInt(num1)==Integer.parseInt(num2)){
            return 0;
        }else{
            return 1;
        }       
    }

  
}
