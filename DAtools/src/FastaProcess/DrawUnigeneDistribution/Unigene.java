/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package FastaProcess.DrawUnigeneDistribution;

import java.util.ArrayList;
import pub.Fasta;

/**
 *
 * @author zhaoqi
 */
public class Unigene {
    private ArrayList<Fasta> translist;
    private Fasta longestTranslist;
    private int currentLongestTranslength=0;
    private String geneid="";

    public Unigene(Fasta trans) {
       translist=new ArrayList<Fasta> ();
       String [] tempa=trans.getPureName().split(" ")[0].split("_");
            geneid=tempa[0]+"_"+tempa[1];
       //geneid=trans.getPureName().split(" ")[0].split("_")[1];
        this.addTranscript(trans);
        currentLongestTranslength=trans.getSequence().length();
        longestTranslist=trans;
        
    }

    
    
    public ArrayList<Fasta> getTranslist() {
        return translist;
    }

    public void addTranscript(Fasta trans) {
        this.translist.add(trans);
        if(trans.getSequence().length()>=currentLongestTranslength){
            currentLongestTranslength=trans.getSequence().length();
            longestTranslist=trans;
        }
    }

    public Fasta getLongestTranslist() {
        return longestTranslist;
    }

    public String getGeneid() {
        return geneid;
    }

    public void setGeneid(String geneid) {
        this.geneid = geneid;
    }

    public int getCurrentLongestTranslength() {
        return currentLongestTranslength;
    }
    
    
    
    
}
