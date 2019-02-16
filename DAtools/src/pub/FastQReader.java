/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pub;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.zip.GZIPInputStream;

/**
 *
 * @author ZHAO Qi
 * @date 2014-8-8 15:16:39
 * @version 1.6.0
 */
public class FastQReader {

    private LinkedList<FastQ> fastqlist = new LinkedList<FastQ>();
    private HashMap<String, FastQ> reads2FastqMap = new HashMap<String,FastQ>();
    private ArrayList<String> readsNamelist= new ArrayList<String>();
    private int maxlength = 0;

    public FastQReader() {
    }

    public FastQReader(String fileinput) throws IOException {
        System.out.println(ToolsforCMD.print_ansi_CYAN("Parsing your file as Fastq, press ctrl+z if not expected with file name :"+fileinput));

        File fqfile = new File(fileinput);
        BufferedReader br = null;
        if (fqfile.getName().endsWith(".gz") || fqfile.getName().endsWith(".gzip")) {
            InputStream in = new GZIPInputStream(new FileInputStream(fqfile));
            br = new BufferedReader(new InputStreamReader(in));
        } else {
            br = new BufferedReader(new FileReader(fqfile));
        }

        int count = 1;
        String tempstr1 = "";
        String tempstr2 = "";
        String tempstr3 = "";
        String tempstr4 = "";
        while (br.ready()) {
            tempstr1 = br.readLine().trim();
            tempstr2 = br.readLine().trim();
            tempstr3 = br.readLine().trim();
            tempstr4 = br.readLine().trim();
            if (tempstr1.startsWith("@") && this.judgeSeq(tempstr2)) {
                FastQ fq = new FastQ(tempstr1, tempstr2, tempstr3, tempstr4);
                String readName=tempstr1.split("/")[0];
                reads2FastqMap.put(readName, fq);
                readsNamelist.add(readName);
                count += 4;
                fastqlist.add(fq);
                if (fq.getSeqlength() > maxlength) {
                    maxlength = fq.getSeqlength();
                }

            } else {
                System.out.println(count + "\t row format error");
                System.out.println("=====================");
                System.out.println(tempstr1 + "\r\n" + tempstr4 + "\r\n" + tempstr3 + "\r\n" + tempstr4);
                System.out.println("=====================");
            }

        }
        br.close();

    }

    public boolean judgeSeq(String seq) {
        String a = "ATCGN.";
        char x;
        boolean mark = true;
        for (int i = 0; i < seq.length(); i++) {
            x = seq.charAt(i);
            if (a.indexOf(String.valueOf(x)) == -1) {
                mark = false;
                break;
            }
        }
        return mark;
    }

    public LinkedList<FastQ> getFastqlist() {
        return this.fastqlist;
    }

    public HashMap<String, FastQ> getReads2FastqMap() {
        return reads2FastqMap;
    }

    public ArrayList<String> getReadsNamelist() {
        return readsNamelist;
    }

    
    
    
    
    public int getMaxlength() {
        return maxlength;
    }

}
