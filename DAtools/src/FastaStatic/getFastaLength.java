/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package FastaStatic;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.LinkedList;
import java.util.logging.Logger;
import pub.Fasta;
import pub.FastaReader;

/**
 *
 * @author ZHAO Qi
 * @date 2015-3-30 10:56:31
 * @version 1.6.0
 */
public class getFastaLength {

    public static void getfastaLength(String fasta, String outfile) {
        LinkedList<Fasta> fastlist = new FastaReader(new File(fasta)).getFastaList();
        try {
            FileWriter fw = new FileWriter(outfile);
            for (int i = 0; i < fastlist.size(); i++) {
                fw.append(fastlist.get(i).getPureName() + "\t" + fastlist.get(i).getSequence().length() + "\n");
            }

            fw.flush();
            fw.close();
        } catch (IOException ex) {
            System.out.println("IO error");
        }
    }
}
