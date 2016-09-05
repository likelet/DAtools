/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ExomeSeqAnalysisPipe;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.util.LinkedList;

/**
 *
 * @author ZhaoQi
 */
public class VCFReader {

    private LinkedList<String> infolist = new LinkedList();
    private LinkedList<VCFrecord> vcflist = new LinkedList();

    public VCFReader(String file) {
        try {
            BufferedReader br = new BufferedReader(new java.io.FileReader(new File(file)));
            VCFrecord vcfdata = new VCFrecord();
            int flag = 0;
            while (br.ready()) {
                String strLine = br.readLine();
                if (strLine.substring(0, 1).equals("#")) {
                    infolist.add(strLine);
                } else {
                    String[] strAll = strLine.split("\t");
                    vcfdata.setChrom(strAll[0]);
                    vcfdata.setPos(strAll[1]);
                    vcfdata.setId(strAll[2]);
                    vcfdata.setRef(strAll[3]);
                    vcfdata.setAlt(strAll[4]);
                    vcfdata.setQual(strAll[5]);
                    vcfdata.setFilter(strAll[6]);
                    String[] info = strAll[7].split(";");
                    String DP = "";
                    String VDB = "";
                    String AF1 = "";
                    String AC1 = "";
                    String MQ = "";
                    String FQ = "";
                    String PV4 = "";
                    String genotype = "";
                    String DP4 = "";
                    int refNum = 0;
                    int altNum = 0;
                    for (int i = 0; i < info.length; i++) {
                        if (info[i].equals("INDEL")) {
                            flag = 1;
                        }
//                       else if(info[i].substring(0,3).equals("DP=")) DP=info[i].substring(3);
//                       else if(info[i].substring(0,4).equals("VDB=")) VDB=info[i].substring(4);
//                       else if(info[i].substring(0,4).equals("AF1=")) AF1=info[i].substring(4);
//                       else if(info[i].substring(0,4).equals("AC1=")) AC1=info[i].substring(4);
//                       else if(info[i].substring(0,4).equals("DP4=")) {DP4=info[i].substring(4); String[] DP4info=DP4.split(",");refNum=Integer.parseInt(DP4info[0])+Integer.parseInt(DP4info[1]);altNum=Integer.parseInt(DP4info[2])+Integer.parseInt(DP4info[3]);}
//                       else if(info[i].substring(0,3).equals("MQ=")) MQ=info[i].substring(3);
//                       else if(info[i].substring(0,3).equals("FQ=")) FQ=info[i].substring(3);
//                       else if(info[i].substring(0,4).equals("PV4=")) PV4=info[i].substring(4);                       
                    }
                    if (flag == 1) {
                        genotype = "INDEL";
                    } else {
                        genotype = "SNP";
                    }
                    flag = 0;
                    vcfdata.setGenotype(genotype);
                    vcfdata.setDP(DP);
                    vcfdata.setVDB(VDB);
                    vcfdata.setAF1(AF1);
                    vcfdata.setAC1(AC1);
                    vcfdata.setDP4(DP4);
                    vcfdata.setRefNum(refNum);
                    vcfdata.setAltNum(altNum);
                    vcfdata.setMQ(MQ);
                    vcfdata.setFQ(FQ);
                    vcfdata.setPV4(PV4);
                    String[] info1 = strAll[9].split(":");
                    String GT = info1[0];
                    String PL = info1[1];
                    String GQ = info1[3];
                    vcfdata.setGT(GT);
                    vcfdata.setPL(PL);
                    vcfdata.setGQ(GQ);
                    vcflist.add(vcfdata);
                    //System.out.println(vcfdata);
                    vcfdata = new VCFrecord();
                }
            }
        } catch (IOException ex) {
        }
    }

    public VCFReader(File file) {
        try {
            BufferedReader br = new BufferedReader(new java.io.FileReader(file));
            VCFrecord vcfdata = new VCFrecord();
            int flag = 0;
            while (br.ready()) {
                String strLine = br.readLine();
                if (strLine.substring(0, 1).equals("#")) {
                    infolist.add(strLine);
                } else {
                    String[] strAll = strLine.split("\t");
                    vcfdata.setChrom(strAll[0]);
                    vcfdata.setPos(strAll[1]);
                    vcfdata.setId(strAll[2]);
                    vcfdata.setRef(strAll[3]);
                    vcfdata.setAlt(strAll[4]);
                    vcfdata.setQual(strAll[5]);
                    vcfdata.setFilter(strAll[6]);
                    String[] info = strAll[7].split(";");
                    String DP = "";
                    String VDB = "";
                    String AF1 = "";
                    String AC1 = "";
                    String MQ = "";
                    String FQ = "";
                    String PV4 = "";
                    String genotype = "";
                    String DP4 = "";
                    int refNum = 0;
                    int altNum = 0;
                    for (int i = 0; i < info.length; i++) {
                        if (info[i].equals("INDEL")) {
                            flag = 1;
                        } else if (info[i].substring(0, 3).equals("DP=")) {
                            DP = info[i].substring(3);
                        }
//                       else if(info[i].substring(0,4).equals("VDB=")) VDB=info[i].substring(4);
//                       else if(info[i].substring(0,4).equals("AF1=")) AF1=info[i].substring(4);
//                       else if(info[i].substring(0,4).equals("AC1=")) AC1=info[i].substring(4);
//                       else if(info[i].substring(0,4).equals("DP4=")) {DP4=info[i].substring(4); String[] DP4info=DP4.split(",");refNum=Integer.parseInt(DP4info[0])+Integer.parseInt(DP4info[1]);altNum=Integer.parseInt(DP4info[2])+Integer.parseInt(DP4info[3]);}
//                       else if(info[i].substring(0,3).equals("MQ=")) MQ=info[i].substring(3);
//                       else if(info[i].substring(0,3).equals("FQ=")) FQ=info[i].substring(3);
//                       else if(info[i].substring(0,4).equals("PV4=")) PV4=info[i].substring(4);                       
                    }
                    if (flag == 1) {
                        genotype = "INDEL";
                    } else {
                        genotype = "SNP";
                    }
                    flag = 0;
                    vcfdata.setGenotype(genotype);
                    vcfdata.setDP(DP);
                    vcfdata.setVDB(VDB);
                    vcfdata.setAF1(AF1);
                    vcfdata.setAC1(AC1);
                    vcfdata.setDP4(DP4);
                    vcfdata.setRefNum(refNum);
                    vcfdata.setAltNum(altNum);
                    vcfdata.setMQ(MQ);
                    vcfdata.setFQ(FQ);
                    vcfdata.setPV4(PV4);
                    String[] info1 = strAll[9].split(":");
                    String GT = info1[0];
                    String PL = info1[1];
                    String GQ = info1[3];
                    vcfdata.setGT(GT);
                    vcfdata.setPL(PL);
                    vcfdata.setGQ(GQ);
                    vcflist.add(vcfdata);
                    //System.out.println(vcfdata);
                    vcfdata = new VCFrecord();
                }
            }
        } catch (IOException ex) {
        }
    }

    public static VCFrecord getvcfFromStr(String str) {
        int flag = 0;
        VCFrecord vcfdata = new VCFrecord();
        String[] strAll = str.split("\t");
        vcfdata.setChrom(strAll[0]);
        vcfdata.setPos(strAll[1]);
        vcfdata.setId(strAll[2]);
        vcfdata.setRef(strAll[3]);
        vcfdata.setAlt(strAll[4]);
        vcfdata.setQual(strAll[5]);
        vcfdata.setFilter(strAll[6]);
        String[] info = strAll[7].split(";");
        String DP = "";
        String VDB = "";
        String AF1 = "";
        String AC1 = "";
        String MQ = "";
        String FQ = "";
        String PV4 = "";
        String genotype = "";
        String DP4 = "";
        int refNum = 0;
        int altNum = 0;
        for (int i = 0; i < info.length; i++) {
            if (info[i].equals("INDEL")) {
                flag = 1;
            }
//                       else if(info[i].substring(0,3).equals("DP=")) DP=info[i].substring(3);
//                       else if(info[i].substring(0,4).equals("VDB=")) VDB=info[i].substring(4);
//                       else if(info[i].substring(0,4).equals("AF1=")) AF1=info[i].substring(4);
//                       else if(info[i].substring(0,4).equals("AC1=")) AC1=info[i].substring(4);
//                       else if(info[i].substring(0,4).equals("DP4=")) {DP4=info[i].substring(4); String[] DP4info=DP4.split(",");refNum=Integer.parseInt(DP4info[0])+Integer.parseInt(DP4info[1]);altNum=Integer.parseInt(DP4info[2])+Integer.parseInt(DP4info[3]);}
//                       else if(info[i].substring(0,3).equals("MQ=")) MQ=info[i].substring(3);
//                       else if(info[i].substring(0,3).equals("FQ=")) FQ=info[i].substring(3);
//                       else if(info[i].substring(0,4).equals("PV4=")) PV4=info[i].substring(4);                       
        }
        if (flag == 1) {
            genotype = "INDEL";
        } else {
            genotype = "SNP";
        }
        flag = 0;
        vcfdata.setGenotype(genotype);
        vcfdata.setDP(DP);
        vcfdata.setVDB(VDB);
        vcfdata.setAF1(AF1);
        vcfdata.setAC1(AC1);
        vcfdata.setDP4(DP4);
        vcfdata.setRefNum(refNum);
        vcfdata.setAltNum(altNum);
        vcfdata.setMQ(MQ);
        vcfdata.setFQ(FQ);
        vcfdata.setPV4(PV4);
        String[] info1 = strAll[9].split(":");
        String GT = info1[0];
        String PL = info1[1];
        String GQ = info1[3];
        vcfdata.setGT(GT);
        vcfdata.setPL(PL);
        vcfdata.setGQ(GQ);
        return vcfdata;
                   //System.out.println(vcfdata);

    }

    public LinkedList<String> getInfolist() {
        return infolist;
    }

    public LinkedList<VCFrecord> getVcflist() {
        return vcflist;
    }
}
