/*
 Process Fastq File in memory efficient mode
 */
package FastQprocess;

import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;
import java.util.zip.GZIPInputStream;
import pub.FastQ;

/**
 * <p>
 * FastaQprocessMemory</p>
 * <p>
 * Created on 2016-1-11 12:17:19</p>
 * <p>
 * Author Email: zhaoqi3@mail2.sysu.edu.cn</p>
 *
 * @author ZHAO Qi
 * @date 2016-1-11 12:17:19
 * @version java 1.6.0
 * @version
 */
public class FastaQprocessMemory {

    public void processPairedFileEffi(int left, int right, String file1, String file2) throws IOException {
        //read two fastq

        FileInputStream inputStream1 = null;
        FileInputStream inputStream2 = null;
        Scanner sc1 = null;

        Scanner sc2 = null;
        String tempstr11;
        String tempstr12;
        String tempstr13;
        String tempstr14;
        String tempstr21;
        String tempstr22;
        String tempstr23;
        String tempstr24;
        FastQ fq;
        int PairCount = 0;
        int totalread = 0;
        String outfile1 = file1 + "_trimed_L" + left + "_R" + right + ".txt";
        String outfile2 = file2 + "_trimed_L" + left + "_R" + right + ".txt";
        try {

            inputStream1 = new FileInputStream(file1);
            inputStream2 = new FileInputStream(file2);
            if (file1.endsWith(".gz") || file1.endsWith(".gzip")) {
                GZIPInputStream gzinputstream1 = new GZIPInputStream(inputStream1);
                GZIPInputStream gzinputstream2 = new GZIPInputStream(inputStream2);
                sc1 = new Scanner(gzinputstream1, "UTF-8");
                sc2 = new Scanner(gzinputstream2, "UTF-8");
            } else {
                sc1 = new Scanner(inputStream1, "UTF-8");
                sc2 = new Scanner(inputStream2, "UTF-8");
            }

            FileWriter fw1 = new FileWriter(outfile1);
            FileWriter fw2 = new FileWriter(outfile2);
            while (sc1.hasNextLine() && sc2.hasNextLine()) {
                tempstr11 = sc1.nextLine();
                tempstr12 = sc1.nextLine();
                tempstr13 = sc1.nextLine();
                tempstr14 = sc1.nextLine();
                tempstr21 = sc2.nextLine();
                tempstr22 = sc2.nextLine();
                tempstr23 = sc2.nextLine();
                tempstr24 = sc2.nextLine();
                if (left + right >= tempstr12.length()) {
                    System.out.println("Trim length exced max reads length, please check your parameters");
                    break;
                } else {
                    if (tempstr11.split("\\s+")[0].equals(tempstr21.split("\\s+")[0])) {
                        PairCount++;
                        FastQ fq1 = new FastQ(tempstr11, tempstr12, tempstr13, tempstr14);
                        FastQ fq2 = new FastQ(tempstr21, tempstr22, tempstr23, tempstr24);
                        this.trim(fq1, left, right);
                        this.trim(fq2, left, right);
                        fw1.append(fq1.toString() + "\n");
                        fw2.append(fq2.toString() + "\n");
                    }

                    // System.out.println(line);
                    fw2.flush();
                    fw1.flush();
                }
            }
            System.out.println("total reads = " + totalread);

            fw1.close();

            fw2.close();
            // note that Scanner suppresses exceptions
            if (sc1.ioException() != null) {
                throw sc1.ioException();
            }
            if (sc2.ioException() != null) {
                throw sc2.ioException();
            }
        } finally {
            if (inputStream1 != null) {
                inputStream1.close();
            }
            if (sc1 != null) {
                sc1.close();
            }
            if (inputStream2 != null) {
                inputStream2.close();
            }
            if (sc2 != null) {
                sc2.close();
            }

        }

        System.out.println("Paired reads:\t" + PairCount);

    }

    public void processSingleFileEffi(int left, int right, String file1, String outfile) throws IOException {
        //read two fastq

        FileInputStream inputStream1 = null;
        Scanner sc1 = null;
        String tempstr11;
        String tempstr12;
        String tempstr13;
        String tempstr14;
        FastQ fq;
        int PairCount = 0;
        int totalread = 0;
        try {
            inputStream1 = new FileInputStream(file1);
            if (file1.endsWith(".gz") || file1.endsWith(".gzip")) {
                GZIPInputStream gzinputstream1 = new GZIPInputStream(inputStream1);
                sc1 = new Scanner(gzinputstream1, "UTF-8");
            } else {
                sc1 = new Scanner(inputStream1, "UTF-8");
            }
            System.out.println("used memory (Mb): "
                    + (Runtime.getRuntime().totalMemory() - Runtime.getRuntime().freeMemory()) / 1024 / 1024);
            FileWriter fw1 = new FileWriter(outfile);
            while (sc1.hasNextLine()) {
                tempstr11 = sc1.nextLine();
                tempstr12 = sc1.nextLine();
                tempstr13 = sc1.nextLine();
                tempstr14 = sc1.nextLine();
                if (left + right >= tempstr12.length()) {
                    System.out.println("Trim length exced max reads length, please check your parameters");
                    break;
                } else {

                    PairCount++;
                    FastQ fq1 = new FastQ(tempstr11, tempstr12, tempstr13, tempstr14);

                    this.trim(fq1, left, right);

                    fw1.append(fq1.toString() + "\n");
                    // System.out.println(line);

                    fw1.flush();
                }
            }
            System.out.println("total reads = " + totalread);

            fw1.close();

            // note that Scanner suppresses exceptions
            if (sc1.ioException() != null) {
                throw sc1.ioException();
            }

        } finally {
            if (inputStream1 != null) {
                inputStream1.close();
            }
            if (sc1 != null) {
                sc1.close();
            }

        }
    }

     public void processPairedFileEffi_filtered_length(int lengthThreshold, String file1, String file2) throws IOException {
        //read two fastq

        FileInputStream inputStream1 = null;
        FileInputStream inputStream2 = null;
        Scanner sc1 = null;

        Scanner sc2 = null;
        String tempstr11;
        String tempstr12;
        String tempstr13;
        String tempstr14;
        String tempstr21;
        String tempstr22;
        String tempstr23;
        String tempstr24;
        FastQ fq;
        int PairCount = 0;
        int totalread = 0;
        String outfile1 = file1 + "_R1.fq";
        String outfile2 = file2 + "_R2.fq";
        try {

            inputStream1 = new FileInputStream(file1);
            inputStream2 = new FileInputStream(file2);
            if (file1.endsWith(".gz") || file1.endsWith(".gzip")) {
                GZIPInputStream gzinputstream1 = new GZIPInputStream(inputStream1);
                GZIPInputStream gzinputstream2 = new GZIPInputStream(inputStream2);
                sc1 = new Scanner(gzinputstream1, "UTF-8");
                sc2 = new Scanner(gzinputstream2, "UTF-8");
            } else {
                sc1 = new Scanner(inputStream1, "UTF-8");
                sc2 = new Scanner(inputStream2, "UTF-8");
            }

            FileWriter fw1 = new FileWriter(outfile1);
            FileWriter fw2 = new FileWriter(outfile2);
            while (sc1.hasNextLine() && sc2.hasNextLine()) {
                tempstr11 = sc1.nextLine();
                tempstr12 = sc1.nextLine();
                tempstr13 = sc1.nextLine();
                tempstr14 = sc1.nextLine();
                tempstr21 = sc2.nextLine();
                tempstr22 = sc2.nextLine();
                tempstr23 = sc2.nextLine();
                tempstr24 = sc2.nextLine();
                if(tempstr12.length()<=lengthThreshold) continue;
                if(tempstr22.length()<=lengthThreshold) continue;
                    if (tempstr11.split("\\s+")[0].equals(tempstr21.split("\\s+")[0])) {
                        PairCount++;
                        FastQ fq1 = new FastQ(tempstr11, tempstr12, tempstr13, tempstr14);
                        FastQ fq2 = new FastQ(tempstr21, tempstr22, tempstr23, tempstr24);

                        fw1.append(fq1.toString() + "\n");
                        fw2.append(fq2.toString() + "\n");
                    }

                    // System.out.println(line);
                    fw2.flush();
                    fw1.flush();
                
            }
            System.out.println("total reads = " + totalread);

            fw1.close();

            fw2.close();
            // note that Scanner suppresses exceptions
            if (sc1.ioException() != null) {
                throw sc1.ioException();
            }
            if (sc2.ioException() != null) {
                throw sc2.ioException();
            }
        } finally {
            if (inputStream1 != null) {
                inputStream1.close();
            }
            if (sc1 != null) {
                sc1.close();
            }
            if (inputStream2 != null) {
                inputStream2.close();
            }
            if (sc2 != null) {
                sc2.close();
            }

        }

        System.out.println("Paired reads:\t" + PairCount);

    }

    
    
    public void trim(FastQ fq, int left, int right) {

        if (left + right >= fq.getSeqlength()) {
            System.out.println("Trim length exced reads length:" + fq.getDescription() + " please check your parameters");
        } else {
            int templength = fq.getSeqlength();//to avoid trimed lengh was get again
            fq.setSequence(fq.getSequence().substring(left, templength - right));
            fq.setQuality(fq.getQuality().substring(left, templength - right));

        }

    }
}
