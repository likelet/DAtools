/*
 * this pipe line is only worked for human RNA-seq data
 */

package RNAseqPipeline;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.logging.Level;
import java.util.logging.Logger;
import pub.RunBowtie2;

/**
 *
 * @author ZHAO Qi
 * @date 2015-2-10 10:58:18
 * @version 1.6.0
 */
public class runHisatandHtseq {
private String library;
    private String gffpath;
    private String fastqfile1;
    private String fastqfile2;
    private String filename=null;
    public String htseqID=null;
    public String htseqFeature=null;
    //htseq strand specific parameters
    private String strand="no";
    

    public runHisatandHtseq(String library, String gffpath, String fastqfile1,boolean issingle) throws IOException {
        this.library = library;
        this.gffpath = new File(gffpath).getAbsolutePath();
        this.fastqfile1 = new File(fastqfile1).getAbsolutePath();
        File tempfile = new File(fastqfile1);

        this.filename = tempfile.getName().split("\\.")[0];
      
    }
    public runHisatandHtseq(String library, String gffpath, String fastqfile1,String userdefinedname,boolean issingle) throws IOException {
        this.library = library;
        this.gffpath = new File(gffpath).getAbsolutePath();
        this.fastqfile1 = new File(fastqfile1).getAbsolutePath();
        this.filename = userdefinedname;
    
    }

    public runHisatandHtseq(String library, String gffpath, String fastqfile1, String fastqfile2) throws IOException {
        this.library = library;
        this.gffpath = new File(gffpath).getAbsolutePath();
        this.fastqfile1 = new File(fastqfile1).getAbsolutePath();
        this.fastqfile2 = new File(fastqfile2).getAbsolutePath();
        

        
 
    }

     public runHisatandHtseq(String library, String gffpath, String fastqfile1, String fastqfile2,String userdefinedname) throws IOException {
        this.library = library;
        this.gffpath = new File(gffpath).getAbsolutePath();
        this.fastqfile1 = new File(fastqfile1).getAbsolutePath();
        this.fastqfile2 = new File(fastqfile2).getAbsolutePath();
        this.filename = userdefinedname;
     
    }

    public void setFilename(String filename) {
        this.filename = filename;
    }

    public String getFilename() {
        return filename;
    }
    
     
     
     
    
    //运行hisat 并返回mapped.sam文件
    public void runTophat2HTseqGetCountfilePaired() throws IOException {
        if(filename==null){
            File tempfile = new File(fastqfile1);
            this.filename = tempfile.getName().split("\\.")[0];
        }
        Process process = null;
        String tophatoutdir=this.filename + "_thout";
        File tophatoutdirfile=new File(tophatoutdir);
        //run bowtie2
        System.out.println("start run Hisat...");
        String samfile = this.filename + ".sam";
        String bamfile = this.filename + ".bam";
        File tempfile=new File("temp.sh");
//        tempfile.deleteOnExit();
            FileWriter fw = new FileWriter(tempfile);
            fw.append("hisat -o "+tophatoutdir+" -p 10 -G " + this.gffpath + " " + this.library+" "+this.fastqfile1+" "+this.fastqfile2+"\n");
            fw.append("cd "+tophatoutdirfile.getAbsolutePath()+"\n");
            fw.append("samtools sort -n accepted_hits.bam accepted_hits_sort\n");
            //htseq cmd
            fw.append("htseq-count");
            if(this.htseqID!=null){
                fw.append(" -i "+this.htseqID);
            }
            if(this.htseqFeature!=null){
                fw.append(" -t "+this.htseqFeature);
            }
            fw.append(" -s "+this.strand+" -f bam accepted_hits_sort.bam "+this.gffpath+" > "+this.filename+".count \n");
            fw.close();
        
//        String bowtiestr="bowtie2 -p 10 -x " + this.library + " -U " + fastqfile + " -S " + this.filename + ".sam";
//        String sam2bamstr="samtools view -b -S " + samfile + " > " + this.filename + ".bam";
//        String getmappedstr = "samtools view -F 4 " + bamfile + " > " + this.filename + "_mapped.sam ";
//        String[] cmd={bowtiestr,sam2bamstr,getmappedstr};
        //run pipeline
            process = Runtime.getRuntime().exec("chmod 755 " + tempfile.getAbsolutePath());
            process = Runtime.getRuntime().exec("./temp.sh");
        InputStream errorst = process.getErrorStream();
        int result;
        try {
            result = process.waitFor();
            if (result == 0) {
               System.out.println(filename + " Mapping finished!");
                //System.out.println(" SUCCESS! ");
            } else {
                BufferedReader br = new BufferedReader(new InputStreamReader(errorst));
                String errorStr = "";
                while (br.ready()) {
                    errorStr = br.readLine() + "\r\n";
                }
                System.out.println(errorStr);
                br.close();
            }
        } catch (InterruptedException ex) {
            Logger.getLogger(RunBowtie2.class.getName()).log(Level.SEVERE, null, ex);

        }

    }
   
}
