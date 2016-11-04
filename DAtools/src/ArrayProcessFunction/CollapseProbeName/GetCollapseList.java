/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package ArrayProcessFunction.CollapseProbeName;

import java.util.ArrayList;

/**
 *
 * @author Administrator
 * @since 2016-11-3
 * @coding time 14:53:30
 * @author Qi Zhao
 */
public class GetCollapseList {
    public static ArrayList<String> getMaxArraylist(ArrayList<ArrayList<String>> subDatalist) {
        ArrayList<String> maxoutlist = new ArrayList<String>();

        // max function
        double tempD = 0;
        for (int i = 0; i < subDatalist.get(0).size(); i++) {
            int NAnumber = 0;
            for (int j = 0; j < subDatalist.size(); j++) {
                if (subDatalist.get(j).get(i) != "NA") {
                    double elemets = Double.parseDouble(subDatalist.get(j).get(i));
                    if (elemets >= tempD) {
                        tempD = elemets;
                    }
                } else {
                    NAnumber++;
                }
            }
            if (subDatalist.size() == NAnumber) {
                maxoutlist.add("NA");
            } else {
                maxoutlist.add(String.valueOf(tempD));
            }
            tempD = 0;
            NAnumber = 0;
        }

        //return
        return maxoutlist;
    }
    
    // get Mean of collapse probe
     public static ArrayList<String> getMeanArraylist(ArrayList<ArrayList<String>> subDatalist) {
        ArrayList<String> meanoutlist = new ArrayList<String>();

        // max function
        double tempD = 0;
         System.out.println(subDatalist.get(0).size());
        for (int i = 0; i < subDatalist.get(0).size(); i++) {
            int NAnumber = 0;
            for (int j = 0; j < subDatalist.size(); j++) {
                if (!"NA" .equals(subDatalist.get(j).get(i))) {
                    double elemets = Double.parseDouble(subDatalist.get(j).get(i));
                    if (elemets >= tempD) {
                        tempD += elemets;
                    }
                } else {
                    NAnumber++;
                }
            }
            if (subDatalist.size() == NAnumber) {
                meanoutlist.add("NA");
            } else {
                meanoutlist.add(String.valueOf(tempD / (subDatalist.size() - NAnumber)));
            }
            tempD = 0;
            NAnumber = 0;
        }
         System.out.println(meanoutlist.size());
        //return
        return meanoutlist;
    }
}
