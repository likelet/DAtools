/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ExomeSeqAnalysisPipe;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.Scanner;

/**
 *
 * @author Yueyuan
 */
public class FilelistSum {

    private LinkedList<String> poslist = new LinkedList();

    FilelistSum() throws IOException {
        LinkedList<VCFrecord> vcflist = new LinkedList();
        this.poslist = new LinkedList();
        File f = null;
        BufferedReader read = new BufferedReader(new InputStreamReader(System.in));
        System.out.println("Please enter you Path:");
        String path = read.readLine();
        f = new File(path);
        File[] list = f.listFiles();
        Scanner sc = new Scanner(System.in);
        System.out.print("Please enter the lowest SNP quality value you want:");
        int lowQual = sc.nextInt();
        System.out.print("Please enter the highest SNP quality value you want:");
        int highQual = sc.nextInt();
        System.out.print("Please enter the lowest DP quality value you want:");
        int lowDP = sc.nextInt();
        System.out.print("Please enter the highest DP quality value you want:");
        int highDP = sc.nextInt();
        System.out.print("Please enter the lowest MQ quality value you want:");
        int lowMQ = sc.nextInt();
        System.out.print("Please enter the highest MQ quality value you want:");
        int highMQ = sc.nextInt();
        for (int i = 0; i < list.length; i++) {
            System.out.println(list[i].getAbsolutePath());
            if (list[i].getName().endsWith(".vcf")) {
                vcflist = new VCFReader(list[i].getAbsolutePath()).getVcflist();
                FileWriter fw = new FileWriter(list[i].getAbsolutePath() + ".choosen.txt");
                fw.write("Chrom" + "\t" + "Pos" + "\t" + "Id" + "\t" + "Ref_Base" + "\t" + "Alt_Base" + "\t" + "SNP_Qual" + "\t" + "Filter" + "\t" + "Genotype" + "\t" + "Deep" + "\t" + "AF1" + "\t" + "AC1" + "\t" + "DP4" + "\t" + "RefNum" + "\t" + "AltNum" + "\t" + "MQ" + "\t" + "FQ" + "\t" + "VDB" + "\t" + "PV4" + "\t" + "GT" + "\t" + "PL" + "\t" + "GQ" + "\n");
                for (Iterator<VCFrecord> it = vcflist.iterator(); it.hasNext();) {
                    VCFrecord data = it.next();
                    float qual = Float.parseFloat(data.getQual());
                    float DP = Float.parseFloat(data.getDP());
                    int MQ = Integer.parseInt(data.getMQ());
                    if (qual >= lowQual && qual <= highQual && DP >= lowDP && DP <= highDP && MQ >= lowMQ && MQ <= highMQ) {
                        poslist.add(data.getPos());
                        fw.write(data + "\n");
                    }
                }
                fw.flush();
                fw.close();
            }
        }
    }

    public LinkedList<String> getLinkedList() {
        return poslist;
    }
}
