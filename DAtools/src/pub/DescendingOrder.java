/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pub;;

import java.util.Comparator;

/**
 *
 * @author zhengyueyuan
 */
public class DescendingOrder implements Comparator {

    @Override
    public int compare(Object o1, Object o2) {
        OrderBaseRecord b1 = (OrderBaseRecord) o1;
        OrderBaseRecord b2 = (OrderBaseRecord) o2;
        if (b1.getFirst() > b2.getFirst()) {
            return -1;
        } else if (b1.getFirst() < b2.getFirst()) {
            return 1;
        } else {
            return 0;

        }
    }
}
