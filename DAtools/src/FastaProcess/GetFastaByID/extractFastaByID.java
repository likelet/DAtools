/*
 * extract fasta sequences by id list file
 */

package FastaProcess.GetFastaByID;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.logging.Level;
import java.util.logging.Logger;
import pub.Fasta;
import pub.FastaReader;
import pub.txtReader;

/**
 *
 * @author zhaoqi
 */
public class extractFastaByID {
    private HashMap<String,Fasta> fastalistmap=new HashMap<String,Fasta>();
    private ArrayList<String> idlist;
    private String outfile;

    public extractFastaByID(String fastafile,String idlistfile,String outfile) throws IOException {
        
        this.idlist = txtReader.readID(idlistfile);
        this.outfile=outfile;
        LinkedList<Fasta> fastalist = new FastaReader(fastafile).getFastaList();
        //设定 比对条件（默认是id空格分隔的第一个字符串）
        for (Iterator it = fastalist.iterator(); it.hasNext();) {
            Fasta fa = (Fasta)it.next();
//            System.out.println(fa.getPureName());
            fastalistmap.put(fa.getPureName().split("\\s+")[0], fa);
        }
        this.process();
    }
    
    //getFastaseqeuence by id file
    public void process() {
        try {
            Fasta tempfa=null;
            String tempstr ="";
            FileWriter fw = new FileWriter(new File(outfile));
            String tempid="";
            for (int i = 0; i < idlist.size(); i++) {
                tempid=idlist.get(i);
                if(fastalistmap.get(tempid)!=null){
                    
                    
                    //reset fasta discription string add orf size 
                    tempfa=fastalistmap.get(tempid);
                    tempstr=tempfa.getPureName();
                    System.out.println(idlist.get(i)+"\t"+tempfa.getSequence().length());
//                    String [] tempa=tempstr.split("\\|");
//                    tempfa.setName(">"+tempa[0]+"|"+tempa[1]+"|ORF_SIZE:"+tempfa.getSequence().length()+"|"+tempa[2]+"|"+tempa[3]+"|");
//                     tempfa.setPureName(tempa[0]+"|"+tempa[1]+"|ORF_SIZE:"+tempfa.getSequence().length()+"|"+tempa[2]+"|"+tempa[3]+"|");
//                    System.out.println(tempfa.getPureName());
                     //
                    
                    //write out
                    fw.append(tempfa.toString()+"\r\n");
                }else{
                    System.out.println(tempid+":\tnot found");
                }
                
            }
            fw.close();
        } catch (IOException ex) {
            Logger.getLogger(extractFastaByID.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public static void main(String[] args) throws IOException {
        new extractFastaByID("F:\\resouces\\projects\\SUMO-FC\\database\\Human_sequence.fa","F:\\resouces\\projects\\SUMO-FC\\database\\IDpair.txt","F:\\resouces\\projects\\SUMO-FC\\database\\cherripikcingOnly.fa");
    }
    
}
