/*
 * SUMO-FC quantification
 */
package statAlignReadsInChr;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.logging.Level;
import java.util.logging.Logger;
import net.sf.samtools.SAMFileReader;
import net.sf.samtools.SAMRecord;

/**
 *
 * @author ZHAO Qi
 * @date 2015-2-4 16:04:13
 * @version 1.6.0
 */
public class StatAlignReadsInChr {

    private HashSet<String> nameset = new HashSet<String>();
    private HashMap<String,ChrOutTerm> outmap=new HashMap<String,ChrOutTerm>();
    private String outfile;
    private SAMFileReader sr;
    private BufferedReader br;
    private String gfffile;

    public StatAlignReadsInChr(String bamfile, String gfffile,String outfile) throws FileNotFoundException, IOException {
        sr = new SAMFileReader(new File(bamfile));
        this.outfile = outfile;
        this.gfffile=gfffile;
        this.intialOutmap();
        this.processBam();
        this.write();
        sr.close();
    }
     public StatAlignReadsInChr(String samfile, String gfffile,String outfile,String issam) throws FileNotFoundException, IOException {
         br = new BufferedReader(new FileReader(new File(samfile)));
        this.outfile = outfile;
        this.gfffile=gfffile;
        this.intialOutmap();
        this.processSam();
        this.write();
    }

    public void intialOutmap() throws FileNotFoundException, IOException{
        BufferedReader br = new BufferedReader(new FileReader(new File(gfffile)));
        String tempstr="";
        while (br.ready()) {  
            tempstr=br.readLine().trim().split("\t")[0];
            if(nameset.add(tempstr)){
               ChrOutTerm ct=new ChrOutTerm(tempstr); 
               outmap.put(tempstr, ct);
            }
        }
        br.close();
    }
    public void processBam() {
        String tempchr;
        for (Iterator it = sr.iterator(); it.hasNext();) {
            SAMRecord sam=(SAMRecord) it.next();
            if (!sam.getReadUnmappedFlag()) {
                tempchr=sam.getReferenceName();
               outmap.get(tempchr).addCount();
            }
        }
    }
    
    public void processSam() throws IOException {
        String tempchr;
        while (br.ready()) {
            String temstr=br.readLine();
            if(!temstr.startsWith("@SQ")){
               tempchr=temstr.split("\t")[2];
               outmap.get(tempchr).addCount();
            }
        }
        br.close();
       
    }
    
    public void write(){
        try {
            FileWriter fw = new FileWriter(outfile);
            for (Iterator it = nameset.iterator(); it.hasNext();) {
                String str=(String)it.next();
                fw.append(outmap.get(str).toString()+"\r\n");
            }
            fw.flush();
            fw.close();
        } catch (IOException ex) {
            Logger.getLogger(StatAlignReadsInChr.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public static void main(String[] args) throws IOException {
        new StatAlignReadsInChr("F:\\resouces\\projects\\SUMO-FC\\S1C_TATA_mapped.sam","F:\\resouces\\projects\\SUMO-FC\\humanorfForHtseq.gff","F:\\resouces\\projects\\SUMO-FC\\count.result","true");
    }
}
