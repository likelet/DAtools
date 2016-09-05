/*
 * read GTF with 9 columns
 */

package pub;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

/**
 * <p>GTF9_reader</p>
 * <p>Created on 2015-12-24 11:25:50</p>
 * <p>Author Email: zhaoqi3@mail2.sysu.edu.cn</p>
 * @author ZHAO Qi
 * @date 2015-12-24 11:25:50
 * @version java 1.6.0
 * @version 
 */
public class GTF9_reader {
    public static ArrayList<GTF9>getGTF9list(String inputfile){
        String att="exon";
        ArrayList<GTF9> gtflist=new ArrayList<GTF9>();
        BufferedReader br = null;
        try {
            br = new BufferedReader(new FileReader(new File(inputfile)));
            String []str ;
            while (br.ready()) {
                str = br.readLine().split("\t");
                if (str[0].startsWith("#") || str.length == 1) {
                    System.out.println("skip lines started with # or unformated.");
                } else if(str[2].equals("exon")){
               
                    GTF9 gtf = new GTF9(str[0], str[1], str[2], Integer.parseInt(str[3]), Integer.parseInt(str[4]), str[5], str[6], str[7], str[8]);
                    gtflist.add(gtf);
                }

            }
            br.close();
        } catch (FileNotFoundException ex) {
            System.out.println(inputfile + " is not found! please check your filepath ");
        } catch (IOException ex) {
            System.out.println("IO error");
        }
        return gtflist;
    }
}
