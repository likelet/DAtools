/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package ChIpseqAnalysisPipeline.intersect;

/**
 *
 * @author ZHAO Qi
 * @date 2015-4-18 10:53:18
 * @version 1.6.0
 */
public class positionIntersect {
public static String isIntersection(int start1,int end1,int start2,int end2){
    int midtotallengh=((end1-start1)+(end2-start2))/2;
//    System.out.println(midtotallengh);
    
    int midDistance=Math.abs((end1+start1)/2+(end2+start2)/2);
//    System.out.println(midDistance);
    if(midtotallengh>midDistance){
        return "No intersect";
    }else if(midtotallengh==midDistance){
        return "Cover";
    }else{
        return"intersect";
    }
}

public static String isIntersectionBydesitance(int start1,int end1,int start2,int end2,int destance){
    int midtotallengh=((end1-start1)+(end2-start2))/2;
    int midDistance=Math.abs((end1+start1)/2+(end2+start2)/2);
    
    if(midtotallengh+destance>midDistance){
        return "No intersect";
    }else if(midtotallengh==midDistance){
        return "Cover";
    }else{
        return"intersect";
    }
}

    public static void main(String[] args) {
        System.out.println(positionIntersect.isIntersection(1, 100, 3, 4));
    }
}
