/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pub;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author ZHAO Qi
 * @date 2015-2-6 15:43:57
 * @version 1.6.0
 */
public class RunBowtie2 {

    private String library;
    private String gffpath;
    private String fastqfile;
    private String filename;

    public RunBowtie2(String library, String gffpath, String fastqfile) {
        this.library = library;
        this.gffpath = gffpath;
        this.fastqfile = fastqfile;
        File tempfile = new File(fastqfile);

        this.filename = tempfile.getName().split("\\.")[0];
    }
    public RunBowtie2(String library, String gffpath, String fastqfile,String userdefinedname) {
        this.library = library;
        this.gffpath = gffpath;
        this.fastqfile = fastqfile;
        File tempfile = new File(fastqfile);

        this.filename = userdefinedname;
    }

    //运行bowtie 并返回mapped.sam文件
    public String runBowtieGetSam() throws IOException {
        Process process = null;

        //run bowtie2
        System.out.println("start run bowtie2...");
        String samfile = this.filename + ".sam";
        String bamfile = this.filename + ".bam";
        File tempfile=new File("temp.sh");
        tempfile.deleteOnExit();
            FileWriter fw = new FileWriter(tempfile);
            fw.append("bowtie2 -p 10 -x " + this.library + " -U " + fastqfile + " -S " + this.filename + ".sam\n");
            fw.append("samtools view -b -S " + samfile + " > " + this.filename + ".bam\n");
            fw.append("samtools view -F 4 " + bamfile + " > " + this.filename + "_mapped.sam\n");
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
               return this.filename + "_mapped.sam";
                //System.out.println(" SUCCESS! ");
            } else {
                BufferedReader br = new BufferedReader(new InputStreamReader(errorst));
                String errorStr = "";
                while (br.ready()) {
                    errorStr = br.readLine() + "\r\n";
                }
                System.out.println(errorStr);
                br.close();
                return null;
            }
        } catch (InterruptedException ex) {
            Logger.getLogger(RunBowtie2.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }

    }
    
    //to be continued
    public String runBowtieGetSamBatch() throws IOException {
        Process process = null;

        //run bowtie2
        System.out.println("start run bowtie2...");
        String samfile = this.filename + ".sam";
        String bamfile = this.filename + ".bam";
        File tempfile=new File("temp.sh");
        tempfile.deleteOnExit();
            FileWriter fw = new FileWriter(tempfile);
            fw.append("bowtie2 -p 10 -x " + this.library + " -U " + fastqfile + " -S " + this.filename + ".sam\n");
            fw.append("samtools view -b -S " + samfile + " > " + this.filename + ".bam\n");
            fw.append("samtools view -F 4 " + bamfile + " > " + this.filename + "_mapped.sam\n");
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
                System.out.println("===============================================");
               return this.filename + "_mapped.sam";
                //System.out.println(" SUCCESS! ");
            } else {
                BufferedReader br = new BufferedReader(new InputStreamReader(errorst));
                String errorStr = "";
                while (br.ready()) {
                    errorStr = br.readLine() + "\r\n";
                }
                System.out.println(errorStr);
                br.close();
                return null;
            }
        } catch (InterruptedException ex) {
            Logger.getLogger(RunBowtie2.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }

    }
}
