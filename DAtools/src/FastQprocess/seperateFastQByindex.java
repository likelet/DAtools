/*
 * seperate fastaq by index and remove index
 */
package FastQprocess;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import pub.FastQ;
import pub.FastQReader;

/**
 *
 * @author ZHAO Qi
 * @date 2014-11-1 12:25:17
 * @version 1.6.0
 */
public class seperateFastQByindex {

    private LinkedList<FastQ> fastqlist = null;
    private HashMap<String,String> index2sample = new HashMap<String,String> ();
    private ArrayList<indexSample> indexlist = new ArrayList<indexSample>();
    private String currentpath;
    private String filename;

    // index file must be tab-delim like
    //              TCTC  WILD
    //              AGAG  mutation
    public seperateFastQByindex(String fastqfile,String indexfile) throws IOException {
        FastQReader fr = new FastQReader(fastqfile);
        File fqfile=new File(fastqfile);
        filename=fqfile.getName();
        fastqlist = fr.getFastqlist();
        this.indexParse(indexfile);
        seperateProcess();
    }

    public void seperateProcess() throws IOException{
        FastQprocess fqp= new FastQprocess();
        
        FastQ fq=null;
        String index = "";
        String temppath = "";
        for (int i = 0; i < indexlist.size(); i++) {
            index = indexlist.get(i).getIndex();
            temppath = "."+ File.separator  +filename+"_"+ indexlist.get(i).getSamplename() + ".fq";
            FileWriter fw = new FileWriter(temppath);
            for (int j = 0; j < fastqlist.size(); j++) {
                fq=fastqlist.get(j);
                if(fq.getSequence().startsWith(index)){
                   fq=fqp.trimFastq(fq, "left", index.length());
                   fw.append(fq.toString()+"\r\n");
                   fastqlist.remove(i);
                   j=j--;
                }
            }
            fw.flush();
            fw.close();
            System.out.println(index+"\t"+fastqlist.size()+" items left'");
        }
        FileWriter fw2 = new FileWriter(currentpath+File.separator+"unkown.fq");
        System.out.println(fastqlist.size());
        for (int i = 0; i < fastqlist.size(); i++) {
            fw2.append(fastqlist.get(i).toString()+"\t\n");
        }
        fw2.close();
        
    }
    
    
    public void indexParse(String indexSamplefile) throws FileNotFoundException, IOException {
        File indexfile=new File(indexSamplefile);
        currentpath=indexfile.getParent();
         indexSample is=null;
        BufferedReader br = new BufferedReader(new FileReader(indexfile));
        while (br.ready()) {
            String tempstr = br.readLine().trim();
            is= new indexSample(tempstr.split("\t")[0], tempstr.split("\t")[1]);
            index2sample.put(tempstr.split("\t")[0], tempstr.split("\t")[1]);
            this.indexlist.add(is);
        }
        br.close();
    }
    
    public static void main(String[] args) throws IOException {
        new seperateFastQByindex("D:\\test.fq","D:\\index.txt");
    }
}
//storage the index and mapfile

class indexSample {

    private String index;
    private String samplename;

    public indexSample(String index, String samplename) {
        this.index = index;
        this.samplename = samplename;
    }

    /**
     * Get the value of samplename
     *
     * @return the value of samplename
     */
    public String getSamplename() {
        return samplename;
    }

    /**
     * Set the value of samplename
     *
     * @param samplename new value of samplename
     */
    public void setSamplename(String samplename) {
        this.samplename = samplename;
    }

    /**
     * Get the value of index
     *
     * @return the value of index
     */
    public String getIndex() {
        return index;
    }

    /**
     * Set the value of index
     *
     * @param index new value of index
     */
    public void setIndex(String index) {
        this.index = index;
    }

}
