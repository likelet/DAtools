/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Algorithm.GeneticInformationEntropy;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

/**
 * <p>
 * ReadDataMatrix</p>
 * <p>
 * Created on 2015-12-14 15:29:47</p>
 * <p>
 * Author Email: zhaoqi3@mail2.sysu.edu.cn</p>
 *
 * @author ZHAO Qi
 * @date 2015-12-14 15:29:47
 * @version java 1.6.0
 * @version
 */
public class ReadDataMatrix {

    private ArrayList<Individual> Indlist = new ArrayList<Individual>();
    private ArrayList<Output> outlist = new ArrayList<Output>();
    public boolean isheader = true;
    public String sep = "\t";
    private String filename;

    public ReadDataMatrix(String filename) {
        this.filename = filename;
        this.process();
    }

    private void process() {
        BufferedReader br = null;
        try {
            br = new BufferedReader(new FileReader(new File(filename)));
            String str[];
            int count = 0;
            if (isheader) {
                while (br.ready()) {
                    str = br.readLine().split(sep);
                    if (count == 0) {

                    } else {
                        ArrayList<Double> list = new ArrayList<Double>();
                        for (int i = 1; i < str.length; i++) {
                            list.add(Double.parseDouble(str[i]));
                        }
                        Output out = new Output(str[0], list);
                        this.outlist.add(out);
                    }
                    count++;
                }
            } else {
                while (br.ready()) {
                    str = br.readLine().split(sep);
                    ArrayList<Double> list = new ArrayList<Double>();
                    for (int i = 1; i < str.length; i++) {
                        list.add(Double.parseDouble(str[i]));
                    }
                    Output out = new Output(str[0], list);
                    this.outlist.add(out);
                }
            }

            br.close();
        } catch (FileNotFoundException ex) {
            System.out.println(filename + " is not found! please check your filepath ");
        } catch (IOException ex) {
            System.out.println("IO error");
        }

    }

    private void processIndvidual() {
        BufferedReader br = null;
        try {
            br = new BufferedReader(new FileReader(new File(filename)));
            String str[];
            int count = 0;
            if (isheader) {
                while (br.ready()) {
                    str = br.readLine().split(sep);
                    if (count == 0) {
                        //skip first str
                        for (int i = 1; i < str.length; i++) {
                            Individual ind = new Individual(str[i]);
                            Indlist.add(ind);
                        }
                    } else {
                        for (int i = 1; i < str.length; i++) {
                            Indlist.get(i).addData(str[0], Double.parseDouble(str[i]));
                        }
                    }
                    count++;
                }
            } else {
                while (br.ready()) {
                    str = br.readLine().split(sep);
                    if (count == 0) {
                        //skip first str
                        for (int i = 1; i < str.length; i++) {
                            Individual ind = new Individual("");
                            Indlist.add(ind);
                            Indlist.get(i).addData(str[0], Double.parseDouble(str[i]));
                        }
                    } else {
                        for (int i = 1; i < str.length; i++) {
                            Indlist.get(i).addData(str[0], Double.parseDouble(str[i]));
                        }
                    }
                    count++;
                }
            }

            br.close();
        } catch (FileNotFoundException ex) {
            System.out.println(filename + " is not found! please check your filepath ");
        } catch (IOException ex) {
            System.out.println("IO error");
        }
    }

    public ArrayList<Individual> getIndlist() {
        return Indlist;
    }

    public ArrayList<Output> getOutlist() {
        return outlist;
    }

}
