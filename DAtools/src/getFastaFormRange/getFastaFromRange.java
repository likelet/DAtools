/*
 * getFasta sequence from a defined range with chr and position range
 */
package getFastaFormRange;

import java.io.IOException;
import java.util.LinkedList;
import pub.Fasta;
import pub.FastaReader;

/**
 *
 * @author ZHAO Qi
 * @date 2014-4-18 14:47:54
 * @version 1.6.0
 */
public class getFastaFromRange {
    public Fasta getSeq(String genomefile, Range range) throws IOException {
        Fasta f = new Fasta();
        f.setPureName(range.getName() + "_" + range.getChr() + ":" + range.getStart() + "-" + range.getEnd());
        System.out.println(f.getName());
        LinkedList<Fasta> fastalist = new FastaReader(genomefile).getFastaList();
        int marker = 1;
        for (int i = 0; i < fastalist.size(); i++) {
            Fasta tamf = fastalist.get(i);
            if (tamf.getPureName().startsWith(range.getChr())) {
                f.setSequence(tamf.getSequence().substring(range.getStart() - 1, range.getEnd() - 1));
                marker = 0;
            }
        }
        if (marker == 1) {
            return null;
        } else {
            return f;
        }
    }
    
    
    public static void main(String[] args) throws IOException {
        Range range=new Range("test","NC_000913.3",4035531,4037072,"+");
        System.out.println(new getFastaFromRange().getSeq("F:\\database\\E.coli\\ecoliK-12.fasta", range).toString());
    }
}
