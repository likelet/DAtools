package ExomeSeqAnalysisPipe.RunCrest;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.logging.Level;
import java.util.logging.Logger;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 * <p>
 * RunCrestSVthread</p>
 * <p>
 * Created on 2015-11-3 16:11:26</p>
 * <p>
 * Author Email: zhaoqi3@mail2.sysu.edu.cn</p>
 *
 * @author ZHAO Qi
 * @date 2015-11-3 16:11:26
 * @version java 1.6.0
 * @version
 */
public class RunCrestSVthread implements Runnable {

    private String tumorbamfile;
    private String germlinebamfile;
    private String genomepath;
    private String genomebit;
    private String crestSVpath;
    private String coverfile;
    private String chrom;
    private String hostname="localhost";
    private String hostport = "9000";

    public RunCrestSVthread(String tumorbamfile, String genomepath, String genomebit, String crestSVpath, String coverfile, String chrom, String hostname) {
        this.tumorbamfile = tumorbamfile;
        this.genomepath = genomepath;
        this.genomebit = genomebit;
        this.crestSVpath = crestSVpath;
        this.coverfile = coverfile;
        this.chrom = chrom;
        this.hostname = hostname;
    }

    public RunCrestSVthread(String tumorbamfile, String germlinebamfile, String genomepath, String genomebit, String crestSVpath, String coverfile, String chrom, String hostname) {
        this.tumorbamfile = tumorbamfile;
        this.germlinebamfile = germlinebamfile;
        this.genomepath = genomepath;
        this.genomebit = genomebit;
        this.crestSVpath = crestSVpath;
        this.coverfile = coverfile;
        this.chrom = chrom;
        this.hostname = hostname;
    }

    @Override
    public void run() {
        Process process = null;;
        // run samtools 
        try {

            int random = (int) (10000000 * Math.random());
            String tempname = "temp_" + random + chrom + ".sh";
            String outname = this.tumorbamfile + "_" + chrom;
            File tempfile = new File(tempname);
//            tempfile.deleteOnExit();
            FileWriter fw = new FileWriter(tempfile);
            fw.append("#!/bin/sh\n");
//            fw.append("gfServer start "+this.hostname+" "+this.hostport+" "+this.genomebit+"\n");
            if (this.germlinebamfile != null) {
                fw.append("perl " + crestSVpath + " -l 150 -f " + this.coverfile + " -p "+outname+ "-d " + this.tumorbamfile + " -g " + this.germlinebamfile + " --ref_genome " + this.genomepath + "  -t " + this.genomebit + " --blatserver " + this.hostname + " --blatport " + this.hostport + " -r " + chrom+"\n");

            } else {
                fw.append("perl " + crestSVpath + " -l 150 -f " + this.coverfile + " -p "+outname+ " -d " + this.tumorbamfile + " --ref_genome " + this.genomepath + "  -t " + this.genomebit + " --blatserver " + this.hostname + " --blatport " + this.hostport + " -r " + chrom+"\n");
            }
//            fw.append("gfServer start "+this.hostname+" "+this.hostport+"\n");
            fw.close();

            process = Runtime.getRuntime().exec("chmod 755 " + tempfile.getAbsolutePath());
            process = Runtime.getRuntime().exec("/bin/sh ./" + tempname);
            InputStream errorst = process.getErrorStream();
            int result = process.waitFor();
            if (result == 0) {
                //System.out.println(" SUCCESS! ");
            } else {
                BufferedReader br = new BufferedReader(new InputStreamReader(errorst));
                String errorStr = "";
                while (br.ready()) {
                    errorStr = br.readLine() + "\r\n";
                }
                //System.out.println(errorStr);
                br.close();
            }
            System.out.println(tumorbamfile + " run crestSV of " + chrom + " finished!");
        } catch (IOException ex) {
            Logger.getLogger(RunCrestExtractThread.class.getName()).log(Level.SEVERE, null, ex);
        } catch (InterruptedException ex) {
            Logger.getLogger(RunCrestExtractThread.class.getName()).log(Level.SEVERE, null, ex);
        }

    }
}
