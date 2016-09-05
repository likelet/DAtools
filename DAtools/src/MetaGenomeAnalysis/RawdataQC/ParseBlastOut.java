/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package MetaGenomeAnalysis.RawdataQC;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;

/**
 * <p>
 * ParseBlastOut</p>
 * <p>
 * Created on 2015-12-17 16:47:35</p>
 * <p>
 * Author Email: zhaoqi3@mail2.sysu.edu.cn</p>
 *
 * @author ZHAO Qi
 * @date 2015-12-17 16:47:35
 * @version java 1.6.0
 * @version
 */
public class ParseBlastOut {
    private String inputfile;

    private ArrayList<BlastOutFormat1> Blist =new ArrayList<BlastOutFormat1>();
    private HashSet<String> readidSet =new HashSet<String>();
    private HashMap<String,String> readsid2toxid=new HashMap<String,String>();

    public ParseBlastOut(String inputfile) {
        this.inputfile = inputfile;
        this.getBlastOutFormat1_List();
    }

    
    
    public void getBlastOutFormat1_List() {

        // get best hit
        BufferedReader br = null;
        try {
            br = new BufferedReader(new FileReader(new File(inputfile)));
            String[] str;
            while (br.ready()) {
                str = br.readLine().trim().split("\t");
                if (readidSet.add(str[0])) {
                    BlastOutFormat1 bf1 = new BlastOutFormat1(str[0], str[1], str[2], str[3], str[4], str[5], str[6], str[7], str[8], str[9], str[10], str[11], str[12]);
//                    Blist.add(bf1);
                    readsid2toxid.put(str[0], str[12]);
//                    System.out.println(str[0]+"    "+str[12]);
//                    System.out.println(str[12]);
                }
            }
            br.close();
        } catch (FileNotFoundException ex) {
            System.out.println(inputfile + " is not found! please check your filepath ");
        } catch (IOException ex) {
            System.out.println("IO error");
        }
    }

    public ArrayList<BlastOutFormat1> getBlist() {
        return Blist;
    }

    public void setBlist(ArrayList<BlastOutFormat1> Blist) {
        this.Blist = Blist;
    }

    public HashSet<String> getReadidSet() {
        return readidSet;
    }

    public void setReadidSet(HashSet<String> readidSet) {
        this.readidSet = readidSet;
    }

    public HashMap<String, String> getReadsid2toxid() {
        return readsid2toxid;
    }

    

}
