/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Multifile2Matrix;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import pub.FilelistReader;

/**
 *
 * @author ZHAO Qi
 * @date 2015-3-24 16:32:04
 * @version 1.6.0
 */
public class Multifile2matrix {

    private HashSet<String> allIterm = new HashSet<String>();
    private HashMap<String, HashMap> filehash = new HashMap<String, HashMap>();
    private HashMap<String, ArrayList<String>> filehashstr = new HashMap<String, ArrayList<String>>();
    private int colnumber = 2;
    private int listsize;

    public Multifile2matrix() {
    }

    public Multifile2matrix(String dir, String suffix, String fileout) {
        this.initialize(dir, suffix);
        this.process(fileout);
    }

    public Multifile2matrix(String dir, String suffix, String fileout, int colnumber) {
        this.initializeSimple(dir, suffix);
        this.colnumber = colnumber;
        this.processJustmerge(fileout);
    }

    

    public void initialize(String dir, String suffix) {
        File[] filelist = FilelistReader.getFileList(dir, suffix);
        for (int i = 0; i < filelist.length; i++) {
            System.out.println(filelist[i].getName());
        }
        for (int i = 0; i < filelist.length; i++) {
            HashMap<String, String> tempmap = this.getSinglefilemap(filelist[i]);
            String tempname = filelist[i].getName();
            filehash.put(tempname, tempmap);
        }
    }
    
    public void initializeSimple(String dir, String suffix) {
        File[] filelist = FilelistReader.getFileList(dir, suffix);
        for (int i = 0; i < filelist.length; i++) {
            System.out.println(filelist[i].getName());
        }
        for (int i = 0; i < filelist.length; i++) {
            ArrayList<String> tempmap = this.getSinglefileString(filelist[i]);
            String tempname = filelist[i].getName();
            filehashstr.put(tempname, tempmap);
        }
    }

    public HashMap<String, String> getSinglefilemap(File file) {
        HashMap<String, String> tempmap = new HashMap<String, String>();

        BufferedReader br = null;
        try {
            br = new BufferedReader(new FileReader(file));
            while (br.ready()) {
                String[] str = br.readLine().split("\t");
//                System.out.println(str.length);
                if (str.length < this.colnumber) {
                    System.out.println(file + " exceed maxcol error!");
                } else {
                    tempmap.put(str[0], str[colnumber - 1]);
                    allIterm.add(str[0]);
                }
            }
            br.close();
        } catch (FileNotFoundException ex) {
            System.out.println(file + " is not found! please check your filepath ");
        } catch (IOException ex) {
            System.out.println("IO  test error");
        }
        return tempmap;
    }

    public void process(String fileout) {
        try {
            FileWriter fw = new FileWriter(fileout);
            fw.append("ID\t");
            for (Iterator it = filehash.keySet().iterator(); it.hasNext();) {
                String tempstr = (String) it.next();
                fw.append(tempstr + "\t");
            }

            fw.append("\r\n");
            for (Iterator it1 = allIterm.iterator(); it1.hasNext();) {
                String rowstr = (String) it1.next();
                fw.append(rowstr + "\t");
                for (Iterator it2 = filehash.keySet().iterator(); it2.hasNext();) {
                    String filename = (String) it2.next();
                    HashMap tempmap = filehash.get(filename);
                    if (tempmap.get(rowstr) != null) {
                        fw.append(tempmap.get(rowstr) + "\t");
                    } else {
                        fw.append("0\t");
                    }
                }
                fw.append("\r\n");
            }
            fw.flush();
            fw.close();
        } catch (IOException ex) {
            System.out.println("IO error");
        }

    }

    public ArrayList<String> getSinglefileString(File file) {
//        HashMap<String, String> tempmap = new HashMap<String, String>();
        ArrayList<String> list = new ArrayList<String>();
        BufferedReader br = null;
        try {
            br = new BufferedReader(new FileReader(file));
            while (br.ready()) {
                String[] str = br.readLine().split("\t");
//                System.out.println(str.length);
                if (str.length < this.colnumber) {
                    System.out.println(file + " exceed maxcol error!");
                } else {
                    list.add(str[colnumber - 1]);
                }
            }
            br.close();
        } catch (FileNotFoundException ex) {
            System.out.println(file + " is not found! please check your filepath ");
        } catch (IOException ex) {
            System.out.println("IO  test error");
        }
        listsize=list.size();
        return list;
    }

    public void processJustmerge(String fileout) {
        try {
            FileWriter fw = new FileWriter(fileout);
            for (Iterator it = filehash.keySet().iterator(); it.hasNext();) {
                String tempstr = (String) it.next();
                fw.append(tempstr + "\t");
            }

            fw.append("\n");
            for (int i = 0; i < listsize; i++) {
                for (Iterator it2 = filehashstr.keySet().iterator(); it2.hasNext();) {
                    String filename = (String) it2.next();
                    ArrayList<String> templist = filehashstr.get(filename);

                    fw.append(templist.get(i) + "\t");

                }
                fw.append("\n");
            }
            fw.flush();
            fw.close();
        } catch (IOException ex) {
            System.out.println("IO error");
        }

    }

    public static void main(String[] args) {
        new Multifile2matrix("F:\\resouces\\projects\\SUMO-FC\\鞠静薇", "tagcount", "F:\\resouces\\projects\\SUMO-FC\\鞠静薇\\result.matrix");
    }
}
