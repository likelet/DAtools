/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package FastQprocess;

import java.io.FileWriter;
import java.io.IOException;
import java.util.Iterator;
import java.util.LinkedList;
import pub.FastQ;
import pub.FastQReader;

/**
 *
 * @author ZHAO Qi
 * @date 2014-8-8 15:34:25
 * @version 1.6.0
 */
public class FastQprocess {

    private LinkedList<FastQ> fastqlist = null;
    private int maxlength = 0;

    public FastQprocess() {
    }

    
    
    
    public FastQprocess(String fastqfile) throws IOException {
        FastQReader fr = new FastQReader(fastqfile);
        fastqlist = fr.getFastqlist();
        maxlength = fr.getMaxlength();
    }

    //seperate FataQfile by seq length , file1 small than length;
    public void dividedBylength(int length, String file1, String file2) {
        try {
            FileWriter fw1 = new FileWriter(file1);
            FileWriter fw2 = new FileWriter(file2);
            for (Iterator it = fastqlist.iterator(); it.hasNext();) {
                FastQ fq = (FastQ) it.next();
                if (fq.getSequence().length() <= length) {
                    fw1.append(fq.toString() + "\n");
                } else {
                    fw2.append(fq.toString() + "\n");
                }
            }
            fw1.flush();
            fw1.close();
            fw2.flush();
            fw2.close();
        } catch (IOException ex) {
            System.out.println("IO error");
        }
    }
    
   
     public void retainLengthFastq(int length, String file1) {
        try {
            FileWriter fw1 = new FileWriter(file1);
            for (Iterator it = fastqlist.iterator(); it.hasNext();) {
                FastQ fq = (FastQ) it.next();
                fw1.append(fq.getDescription() + "\n");
                    fw1.append(fq.getSequence().subSequence(0, length) + "\n");
                 fw1.append(fq.getVariant() + "\n");
                 
                    fw1.append(fq.getQuality().subSequence(0, length) + "\n");
               
            }
            fw1.flush();
            fw1.close();
        } catch (IOException ex) {
            System.out.println("IO error");
        }
    }

    //trim fastQ & filter by left &right
    public void trimFastq(int left, int right, int minlength, String file1, String file2) {
        int totalreads = fastqlist.size();
        int passedreads = 0;
        int failedreads = 0;
        int templength=0;
        try {
            if (left + right >= this.maxlength) {
                System.out.println("Trim length exced max reads length, please check your parameters");
            } else {
                FileWriter fw1 = new FileWriter(file1);
                FileWriter fw2 = new FileWriter(file2);
                for (Iterator it = fastqlist.iterator(); it.hasNext();) {
                    FastQ fq = (FastQ) it.next();
                    if (left + right >= fq.getSeqlength()) {
                        System.out.println("Trim length exced reads length:" + fq.getDescription() + " please check your parameters");
                        break;
                    } else {
                        templength= fq.getSeqlength();//to avoid trimed lengh was get again
                        fq.setSequence(fq.getSequence().substring(left, templength - right));
                        fq.setQuality(fq.getQuality().substring(left, templength - right));

                        if (fq.getSeqlength() >= minlength) {
                            fw1.append(fq.toString() + "\n");
                            passedreads++;
                        } else {
                            fw2.append(fq.toString() + "\n");
                            failedreads++;
                        }
                    }
                }

                System.out.println("total reads = " + totalreads + "\n Trimed reads longer than minlength = " + passedreads + "\n Trimed reads shorter than minlength = " + failedreads);
                fw1.flush();
                fw1.close();
                fw2.flush();
                fw2.close();
            }
        } catch (IOException ex) {
            System.out.println("IO error");
        }
    }

    //for zheng
    // trim left & cut all sequence with minlength left (from right)
     public void trimFastqLeftconstantLength(int left, int minlength, String file1) {
         System.out.println("Read data finished!\n start process");
        int totalreads = fastqlist.size();
        int passedreads = 0;
        int failedreads = 0;
        int templength=0;
        try {
            if (left >= this.maxlength) {
                System.out.println("Trim length exced max reads length, please check your parameters");
            } else {
                FileWriter fw1 = new FileWriter(file1);
                for (Iterator it = fastqlist.iterator(); it.hasNext();) {
                    FastQ fq = (FastQ) it.next();
                    if (left+minlength >= fq.getSeqlength()) {
                        System.out.println("Trim length exced reads length:" + fq.getDescription() + " please check your parameters(skipped this item)");
                            failedreads++;
                    } else {
                        fq.setSequence(fq.getSequence().substring(left, minlength +left));
                        fq.setQuality(fq.getQuality().substring(left, minlength + left));

                        if (fq.getSeqlength() >= minlength) {
                            fw1.append(fq.toString() + "\n");
                            passedreads++;
                        } else {
                            failedreads++;
                        }
                    }
                    int size=totalreads/10;
                    if((passedreads+failedreads)%size==0){
                        System.out.println((passedreads+failedreads)*10/size+"% reads were processed!");
                    }
                }

                
                System.out.println("total reads = " + totalreads + "\n Trimed reads longer than minlength = " + passedreads + "\n Trimed reads shorter than minlength = " + failedreads);
                fw1.flush();
                fw1.close();

            }
        } catch (IOException ex) {
            System.out.println("IO error");
        }
    }
    
    public void trimFastqByminiLength(int right, int lengthcondition, String file1) {
        int totalreads = fastqlist.size();
        int passedreads = 0;
        int failedreads = 0;
        int templength=0;
        try {
            if (right >= this.maxlength) {
                System.out.println("Trim length exced max reads length, please check your parameters");
            } else {
                FileWriter fw1 = new FileWriter(file1);

                for (Iterator it = fastqlist.iterator(); it.hasNext();) {
                    FastQ fq = (FastQ) it.next();
                    if (right >= fq.getSeqlength()) {
                        System.out.println("Trim length exced reads length:" + fq.getDescription() + " please check your parameters");
                        break;
                    } else {

                        if (fq.getSeqlength() >= lengthcondition) {
                            templength= fq.getSeqlength();
                            fq.setSequence(fq.getSequence().substring(0, templength - right));
                            fq.setQuality(fq.getQuality().substring(0, templength - right));
                            fw1.append(fq.toString() + "\n");
                            passedreads++;
                        } else {
                            fw1.append(fq.toString() + "\n");
                            failedreads++;
                        }
                    }
                }

                System.out.println("total reads = " + totalreads + "\n Trimed reads longer than minlength = " + passedreads + "\n Trimed reads shorter than minlength = " + failedreads);
                fw1.flush();
                fw1.close();
            }
        } catch (IOException ex) {
            System.out.println("IO error");
        }
    }
    
    
    public FastQ trimFastq(FastQ fq,String LorR,int length){
        int orignlength=fq.getSequence().length();
        if(LorR.equals("left")){
            fq.setSequence(fq.getSequence().substring(length));
            fq.setQuality(fq.getQuality().substring(length));
        }else{
            fq.setSequence(fq.getSequence().substring(0,orignlength-length));
            fq.setQuality(fq.getQuality().substring(0,orignlength-length));
        }
        return fq;
    }
}
