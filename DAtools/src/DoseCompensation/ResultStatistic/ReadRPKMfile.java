/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DoseCompensation.ResultStatistic;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

/**
 * <p>
 * ReadRPKMfile</p>
 * <p>
 * Created on 2015-12-25 14:56:02</p>
 * <p>
 * Author Email: zhaoqi3@mail2.sysu.edu.cn</p>
 *
 * @author ZHAO Qi
 * @date 2015-12-25 14:56:02
 * @version java 1.6.0
 * @version
 */
public class ReadRPKMfile {

    public ArrayList<RPKMterm> ParseRPKM = new ArrayList<RPKMterm>();

    private String inputfile;

    public ReadRPKMfile(String inputfile) {
        this.inputfile = inputfile;
        this.process();
    }

    public void process() {
        BufferedReader br = null;
        try {
            br = new BufferedReader(new FileReader(new File(inputfile)));
            String[] str;
            while (br.ready()) {
                str = br.readLine().trim().split("\t");
                if (str[4].equals("unappliable")) {
                } else {
                    RPKMterm rt = new RPKMterm(str[0], str[1], Integer.parseInt(str[2]), Integer.parseInt(str[3]), Double.parseDouble(str[4]));
                    ParseRPKM.add(rt);
                }
            }
            br.close();
        } catch (FileNotFoundException ex) {
            System.out.println(inputfile + " is not found! please check your filepath ");
        } catch (IOException ex) {
            System.out.println("IO error");
        }
    }

    class RPKMterm {

        private String genename;
        private String chr;
        private int count;
        private int elength;
        private double rpkm;

     
        public RPKMterm(String genename, String chr, int count, int elength, double rpkm) {
            this.genename = genename;
            this.chr = chr;
            this.count = count;
            this.elength = elength;
            this.rpkm = rpkm;
        }

        public String getChr() {
            return chr;
        }
        
        

        public String getGenename() {
            return genename;
        }

        public void setGenename(String genename) {
            this.genename = genename;
        }

        public int getCount() {
            return count;
        }

        public void setCount(int count) {
            this.count = count;
        }

        public int getElength() {
            return elength;
        }

        public void setElength(int elength) {
            this.elength = elength;
        }

        public double getRpkm() {
            return rpkm;
        }

        public void setRpkm(double rpkm) {
            this.rpkm = rpkm;
        }

    }

    public String getInputfile() {
        return inputfile;
    }

    
    public ArrayList<RPKMterm> getParseRPKM() {
        return ParseRPKM;
    }
    
    
}
