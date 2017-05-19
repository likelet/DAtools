/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package Algorithm.ssGSEA;

import java.util.Comparator;

/**
 * <p>SetdataCompare</p>
 * <p>Created on 2015-10-17 11:04:53</p>
 * <p>Author Email: zhaoqi3@mail2.sysu.edu.cn</p>
 * @author ZHAO Qi
 * @date 2015-10-17 11:04:53
 * @version java 1.6.0
 * @version 
 */
//按照降序根据FC排列
public class SetdataCompare implements Comparator<GeneDataTerm> {

    public int compare(GeneDataTerm o1, GeneDataTerm o2) {
        if (o1.getFoldChange() < o2.getFoldChange()) {
            return -1;
        } else if (o1.getFoldChange() > o2.getFoldChange()) {
            return 1;
        } else {
            return 0;
        }
    }

}
