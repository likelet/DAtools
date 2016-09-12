package pub;

/*
 * search sequence by name //for Jiaoge
 */


import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedList;


/**
 *
 * @author ZHAO Qi
 * @version jdk 1.7.0
 */
public class FastaSearch {
     //public static final String database="";
     //private Fasta tempfasta=new Fasta("","");
   
   
    public FastaSearch(String inputfile,String database,String output) throws IOException{
        Fasta tempfasta2=new Fasta();
        String namestr="";
        
        FastaReader fr=new FastaReader(database);
        LinkedList<Fasta> lk = fr.getMetaFastaList();

        BufferedReader br = new BufferedReader(new FileReader(new File(inputfile)));
        FileWriter fw = new FileWriter(output);
            
            

        while (br.ready()) {
            namestr = br.readLine().trim();
            System.out.print(namestr);
            for (Iterator it=lk.iterator();it.hasNext();) {
                tempfasta2 = (Fasta) it.next();
                //System.out.println(tempfasta2.getPureName());
                if (tempfasta2.getPureName().startsWith(namestr+" ")) {
                    fw.append(tempfasta2.toString() + "\n");
                    System.out.println("ok");
                    break;
                }
                //continue;
            }
        }
        fw.flush();
        br.close();
        //return tempfasta;
    }
    

    public static void main(String arg[]) throws IOException {
        new FastaSearch("D:/E/2012/20120413/lslmu.txt","D:/E/2012/20120413/Trinity-1s16ge.fasta","D:/E/2012/20120413/lslmusearchresult.txt");
        
    }
}
