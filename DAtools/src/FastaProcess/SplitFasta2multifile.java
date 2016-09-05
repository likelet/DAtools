/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package FastaProcess;

import java.io.FileWriter;
import java.io.IOException;
import java.util.LinkedList;
import java.util.logging.Level;
import java.util.logging.Logger;
import pub.Fasta;
import pub.FastaReader;

/**
 * <p>SplitFasta2multifile</p>
 * <p>Created on 2015-11-4 11:14:13</p>
 * <p>Author Email: zhaoqi3@mail2.sysu.edu.cn</p>
 * @author ZHAO Qi
 * @date 2015-11-4 11:14:13
 * @version java 1.6.0
 * @version 
 */
public class SplitFasta2multifile {
    private String fastfile;

    public SplitFasta2multifile(String fastfile) throws IOException {
        this.fastfile = fastfile;
        this.split();
    }
    private void split() throws IOException{
    LinkedList<Fasta> fastalist=new FastaReader(fastfile).getFastaList();
        for (int i = 0; i < fastalist.size(); i++) {
            try {
                FileWriter fw = new FileWriter(fastalist.get(i).getPureName()+".fa");
                fw.append(fastalist.get(i).toString());

                fw.flush();
                fw.close();
            } catch (IOException ex) {
                Logger.getLogger(SplitFasta2multifile.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
    
}
