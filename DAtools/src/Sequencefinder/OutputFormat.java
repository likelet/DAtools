/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package Sequencefinder;

import java.util.ArrayList;

/**
 *
 * @author ZHAO Qi
 * @date 2015-7-6 18:04:02
 * @version 1.6.0
 */
public class OutputFormat {

    private String seq;

    private int count=0;

    private int start;

    private int end;
    ArrayList<String> genes=new ArrayList<String>();

    public OutputFormat(String seq, int start, int end) {
        this.seq = seq;
        this.start = start;
        this.end = end;
    }
    
    
    

    /**
     * Get the value of end
     *
     * @return the value of end
     */
    public int getEnd() {
        return end;
    }

    /**
     * Set the value of end
     *
     * @param end new value of end
     */
    public void setEnd(int end) {
        this.end = end;
    }


    /**
     * Get the value of start
     *
     * @return the value of start
     */
    public int getStart() {
        return start;
    }

    /**
     * Set the value of start
     *
     * @param start new value of start
     */
    public void setStart(int start) {
        this.start = start;
    }


    /**
     * Get the value of count
     *
     * @return the value of count
     */
    public int getCount() {
        return count;
    }

    /**
     * Set the value of count
     *
     * @param count new value of count
     */
    public void addcount() {
        this.count ++;
    }


    /**
     * Get the value of seq
     *
     * @return the value of seq
     */
    public String getSeq() {
        return seq;
    }

    /**
     * Set the value of seq
     *
     * @param seq new value of seq
     */
    public void setSeq(String seq) {
        this.seq = seq;
    }

    public void setGenes(String genename) {
        this.genes.add(genename);
    }
  public String getgenestring(){
       String str="";
        for (int i = 0; i < genes.size(); i++) {
            str+=genes.get(i)+";";
        }
        return str;
  }
    @Override
    public String toString() {
        String str="";
        for (int i = 0; i < genes.size(); i++) {
            str+=genes.get(i)+";";
        }
        return  seq + "\t" + count + "\t" + start + "\t" + end + "\t" + str ;
    }
    
    

    
}
