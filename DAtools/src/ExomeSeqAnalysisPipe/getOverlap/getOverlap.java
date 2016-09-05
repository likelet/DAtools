/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ExomeSeqAnalysisPipe.getOverlap;

import java.util.ArrayList;

/**
 *
 * @author ZHAO Qi
 * @date 2014-3-26 13:58:19
 * @version 1.6.0
 */
public abstract class getOverlap {
    public  ArrayList<Object> getOverlapList(ArrayList<ArrayList> listcluster){
        //ArrayList list= new ArrayList();
        ArrayList firstlist=listcluster.get(0);
        for (int i = 1; i < listcluster.size(); i++) {
            ArrayList list2=listcluster.get(i);
            System.out.println(list2.size());
            for (int j = 0; j < firstlist.size(); j++) {
                Object tempO=firstlist.get(j);
                if(!this.contains(tempO, list2)){
                    firstlist.remove(j);
                    j--;
                }
            }
            System.out.println(firstlist.size());
        }
        return firstlist;
        
    }
    public  abstract boolean compair(Object o1,Object o2);
    public boolean contains(Object o1, ArrayList list){
        boolean mark=false;
        for (int i = 0; i < list.size(); i++) {
            if(this.compair(o1, list.get(i))){
                mark=true;
                break;
            }
        }
        return mark;
        
    }
}
