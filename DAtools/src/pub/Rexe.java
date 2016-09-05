/*
 * exe R script in windows
 */
package pub;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author ZHAO Qi
 * @date 2014-3-7 18:43:10
 * @version 1.6.0
 */
public class Rexe {

    ArrayList<File> filelist = new ArrayList<File>();

    public Rexe(String Rdir) {
        try {
            this.processRscript(Rdir);
        } catch (IOException ex) {
            Logger.getLogger(Rexe.class.getName()).log(Level.SEVERE, null, ex);
        } catch (InterruptedException ex) {
            Logger.getLogger(Rexe.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public Rexe() {
    }

    public Rexe(String Rpath, boolean isSingle) {
        try {
//            this.processSingleRscriptWindows(Rpath);
            System.out.println("your systerm is " + System.getProperty("os.name"));
            String sysstr = System.getProperty("os.name");
            if (sysstr.contains("Windows")) {
                this.processSingleRscriptWindows(Rpath);
            } else {

                this.processSingleRscriptLinux(Rpath);
            }
        } catch (IOException ex) {
            Logger.getLogger(Rexe.class.getName()).log(Level.SEVERE, null, ex);
        } catch (InterruptedException ex) {
            Logger.getLogger(Rexe.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void getFileList(File file) {

        File[] files = file.listFiles();
        if (files != null) {
            for (File f : files) {
                if (f.isDirectory()) {
                    this.getFileList(f);
                } else {
                    this.filelist.add(f);
                    //System.out.println(f.getAbsolutePath());
                }

            }
        }
    }

    public void processRscript(String Rdir) throws IOException, InterruptedException {

        this.getFileList(new File(Rdir));
        for (int i = 0; i < filelist.size(); i++) {
            File tempfile = filelist.get(i);
            if (tempfile.getName().endsWith(".R")) {
                Process process = null;
                //process = Runtime.getRuntime().exec("cd F:/mywork/project/MTanalysis/ForR");
                process = Runtime.getRuntime().exec("\"E:\\Program Files\\R\\R-3.1.2\\bin\\x64\\Rcmd.exe\" BATCH " + tempfile.getAbsolutePath());
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
                System.out.println(tempfile.getName() + " finished!");

            }
        }
        File[] outlist = FilelistReader.getFileList(Rdir);
        for (File f : outlist) {
            if (f.getName().contains(".Rout")) {
                f.delete();
            }
        }

    }

    //run in linux ,Rscript must be in evorinment
    public void processSingleRscriptLinux(String Rscript) throws IOException, InterruptedException {

        File tempfile = new File(Rscript);
        if (tempfile.getName().endsWith(".R")) {
            Process process = null;
            //process = Runtime.getRuntime().exec("cd F:/mywork/project/MTanalysis/ForR");
            process = Runtime.getRuntime().exec("chmod 755 " + tempfile.getAbsolutePath());
            process = Runtime.getRuntime().exec("Rscript " + tempfile.getAbsolutePath());
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
            System.out.println(tempfile.getName() + " finished!");

        }
    }

    public void processSingleRscriptWindows(String Rscript) throws IOException, InterruptedException {

        File tempfile = new File(Rscript);
        if (tempfile.getName().endsWith(".R")) {
            Process process = null;
            //process = Runtime.getRuntime().exec("cd F:/mywork/project/MTanalysis/ForR");
            process = Runtime.getRuntime().exec("\"E:\\Program Files\\R\\R-3.1.2\\bin\\x64\\Rcmd.exe\" BATCH " + tempfile.getAbsolutePath());
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
            System.out.println(tempfile.getName() + " finished!");
//                File[] outlist=FilelistReader.getFileList();
//                  for(File f : outlist){
//            if(f.getName().contains(".Rout")){
//                f.delete();
//            }

        }
    }

}
