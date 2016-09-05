/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ExomeSeqAnalysisPipe;

import ExomeSeqAnalysisPipe.FileReaderAllData;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;

/**
 *
 * @author ZhaoQi
 */
public class FilelistReader {

    public static HashMap<String, LinkedList<VCFrecord>> getSampleVcfMap(String fileDir){
        HashMap<String, LinkedList<VCFrecord>> sampleVcfMap=new HashMap<String, LinkedList<VCFrecord>>();
        File f = null;
         f = new File(fileDir);
        File[] list = f.listFiles();
        
        for (int i = 0; i < list.length; i++) {
            String tempname=list[i].getAbsolutePath();
            LinkedList<VCFrecord> vcflist=new VCFReader(list[i]).getVcflist();
            sampleVcfMap.put(tempname, vcflist);
        }
        
       return sampleVcfMap;
    }
    FilelistReader() throws IOException {
        ArrayList<ChromAndPos> infolist = new ArrayList();
        ArrayList<ChromAndPos> templist = new ArrayList();
        ArrayList<ChromAndPos> comparelist = new ArrayList();
        ArrayList<String> all_datalist = new ArrayList();
        File f = null;
        BufferedReader read = new BufferedReader(new InputStreamReader(System.in));
        System.out.println("Please enter your path:");
        String path = read.readLine();
        f = new File(path);
        File[] list = f.listFiles();
        System.out.println(list[0].getAbsolutePath());
        infolist = new FileReader(list[0].getAbsolutePath()).getInfolist();
        for (int i = 1; i < list.length; i++) {
            templist = (ArrayList<ChromAndPos>) infolist.clone();
            infolist.clear();
            System.out.println(list[i].getAbsolutePath());
            comparelist = new FileReader(list[i].getAbsolutePath()).getInfolist();
            for (Iterator<ChromAndPos> it = comparelist.iterator(); it.hasNext();) {
                ChromAndPos comparedata = it.next();
                String chr1 = comparedata.getChrom();
                String pos1 = comparedata.getPos();
                for (Iterator<ChromAndPos> its = templist.iterator(); its.hasNext();) {
                    ChromAndPos info = its.next();
                    String chr2 = info.getChrom();
                    String pos2 = info.getPos();
                    if (chr1.equals(chr2) && pos1.equals(pos2)) {
                        infolist.add(new ChromAndPos(chr1, pos1));
                        break;
                    }
                }
            }
        }
        for (int i = 0; i < list.length; i++) {
            int flag = 0;
            all_datalist = new FileReaderAllData(list[i].getAbsolutePath()).getAll_datalist();
            FileWriter fw = new FileWriter(list[i].getAbsolutePath() + ".rm.txt");
            for (Iterator<String> it = all_datalist.iterator(); it.hasNext();) {
                String data = it.next();
                if (data.substring(0, 1).equals("#")) {
                    fw.write(data + "\n");
                } else {
                    String[] dataAll = data.split("\t");
                    String pos = dataAll[1];
                    flag = 0;
                    for (Iterator<ChromAndPos> its = infolist.iterator(); its.hasNext();) {
                        ChromAndPos info = its.next();
                        if (pos.equals(info.getPos())) {
                            flag = 1;
                            break;
                        }
                    }
                    if (flag == 0) {
                        fw.write(data + "\n");
                    }
                }
            }
            fw.close();
        }

    }
    
    public static ArrayList<ArrayList> getFileListCluster(String fileDir) throws IOException{
        ArrayList<ArrayList> list=new ArrayList<ArrayList>();
        File f = null;
         f = new File(fileDir);
        File[] Flist = f.listFiles();
        
        for (int i = 0; i < Flist.length; i++) {
            ArrayList templist=FilelistReader.readBase(Flist[i].getAbsolutePath());
            list.add(templist);
        }
        
       return list ;
    }
      public static ArrayList readBase(String inputfile) throws IOException{
        ArrayList list = new ArrayList();
        String str;
        try {            
            BufferedReader br = new BufferedReader(new java.io.FileReader(new File(inputfile)));
            
            //FileWriter fw = new FileWriter(outputFile);
            while (br.ready()) {
                str = br.readLine();
                if(str.startsWith("#")){
                    str = br.readLine();
                } else {
                    String[] tempa=str.split("\t");
                    String str2=tempa[15];
                    if(tempa.length<17){
                        str = tempa[0]+"\t"+ tempa[1]+"\t"+ tempa[2]+"\t"+ tempa[3]+"\t"+ tempa[4]+"\t"+ tempa[5]+"\t"+ tempa[9]+"\t"+tempa[10]+"\t"+ tempa[13]+"\t"+ tempa[14]+"\t"+ tempa[15];
                    }else{
                    str = tempa[0]+"\t"+ tempa[1]+"\t"+ tempa[2]+"\t"+ tempa[3]+"\t"+ tempa[4]+"\t"+ tempa[5]+"\t"+ tempa[9]+"\t"+tempa[10]+"\t"+ tempa[13]+"\t"+ tempa[14]+"\t"+ tempa[15]+"\t"+ tempa[16]+"\t"+ tempa[17]+"\t"+ tempa[18]+"\t"+ tempa[19]+"\t"+ tempa[20];
                    }
                    //System.out.println(str);
                    if (list.contains(str)||"SYNONYMOUS_CODING".equals(str2)) {
                    } else {
                        list.add(str);
                    }
                }
            }
            //fw.flush();
            br.close();
        } catch (FileNotFoundException ex) {
            ex.printStackTrace();
        }
        return list;
    }
}
