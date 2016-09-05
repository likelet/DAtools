/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package DrawUnigeneDistribution;

import java.io.File;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;
import pub.Fasta;
import pub.FastaReader;

/**
 *
 * @author zhaoqi
 */
public class TrinityProcess {
    
    
    public static HashMap<String, Unigene> getGene2UnigeneMap(String fastaFile){
        HashMap<String, Unigene> gene2UnigeneMap=new HashMap<String, Unigene>();
        
        LinkedList<Fasta> fastalist=new FastaReader(new File(fastaFile)).getFastaList();
        String str="";//判断基因名是否重复
        for (Iterator it = fastalist.iterator(); it.hasNext();) {
            Fasta tempfasta= (Fasta) it.next();
            String [] tempa=tempfasta.getPureName().split(" ")[0].split("_");
            str=tempa[0]+"_"+tempa[1];
            //System.out.println(str);
            if(gene2UnigeneMap.keySet().contains(str)){
                gene2UnigeneMap.get(str).addTranscript(tempfasta);
            }else{
                Unigene ug=new Unigene(tempfasta);
                gene2UnigeneMap.put(str, ug);
            }
        }
        return gene2UnigeneMap;
    }
    
    //get sequence length list of a fastafile
    public static int[] getSeqLengthList(String fastaFile,boolean isUnigene){
        int [] a;
        if(isUnigene){
            HashMap<String, Unigene> gene2UnigeneMap =TrinityProcess.getGene2UnigeneMap(fastaFile);
            //System.out.println("gene2UnigeneMap size=" +gene2UnigeneMap.size());
            a=new int[gene2UnigeneMap.keySet().size()];
            int i=0;
            for (Iterator it = gene2UnigeneMap.keySet().iterator(); it.hasNext();) {
                String tempstr = (String) it.next();
                a[i]=gene2UnigeneMap.get(tempstr).getCurrentLongestTranslength();
                i++;
            }
        }else{
            LinkedList<Fasta> fastalist=new FastaReader(new File(fastaFile)).getFastaList();
            a=new int[fastalist.size()];
            for (int i = 0; i < fastalist.size(); i++) {
                a[i]=fastalist.get(i).getSequence().length();     
            }
        }
        return a;
    }
    
    public static LinkedList<Fasta> getunigenelist(String trinityout){
        HashMap<String, Unigene> gene2UnigeneMap=TrinityProcess.getGene2UnigeneMap(trinityout);
        LinkedList<Fasta> unigenelist=new LinkedList<Fasta>();
        for (Iterator it = gene2UnigeneMap.keySet().iterator(); it.hasNext();) {
            String geid=(String) it.next();
            Fasta fa=gene2UnigeneMap.get(geid).getLongestTranslist();
            unigenelist.add(fa);
        }
        return unigenelist;
    }
    
    //把序列从大到小排序
    public static void resortUnigeneBylength(LinkedList<Fasta> list) {
        Comparator<Fasta> comparator = new Comparator<Fasta>() {
            public int compare(Fasta r1, Fasta r2) {
                //先排序列长度
                if (r1.getSequence().length() != r2.getSequence().length()) {
                    if (r1.getSequence().length() < r2.getSequence().length()) {
                        return 1;
                    } else if (r1.getSequence().length() == r2.getSequence().length()) {
                        return 0;
                    } else {
                        return -1;
                    }

                } else {
                    //姓名也相同则按学号排序
                    return r1.getPureName().compareTo(r2.getPureName());

                }
            }
        };
        Collections.sort(list, comparator);
    }

    //计算N若干
    public static void calculationNnummer(String trinityout,boolean isunigene) {
        LinkedList<Fasta> unigenelist=null;
        if(isunigene){
            unigenelist=TrinityProcess.getunigenelist(trinityout);
        }else{
            unigenelist=new FastaReader(trinityout).getFastaList();
        }
        
        TrinityProcess.resortUnigeneBylength(unigenelist);
        int totallengh=0;
        for (int i = 0; i < unigenelist.size(); i++) {
            totallengh+=unigenelist.get(i).getSequence().length();
        }
        int currentlengh=0;
        int currenti=0;
        for (int i = 0; i < unigenelist.size(); i++) {
            currentlengh+=unigenelist.get(i).getSequence().length();
            if((double)currentlengh/totallengh>=0.1){
                System.out.println("N10 = "+unigenelist.get(i).getSequence().length()+";\t sequence count = "+(i+1));
                currenti=i+1;
                break;
            }
        }
        for (int i = currenti; i < unigenelist.size(); i++) {
            currentlengh+=unigenelist.get(i).getSequence().length();
            if((double)currentlengh/totallengh>=0.2){
                System.out.println("N20 = "+unigenelist.get(i).getSequence().length()+";\t sequence count = "+(i+1));
                currenti=i+1;
                break;
            }
        }
        for (int i = currenti; i < unigenelist.size(); i++) {
            currentlengh+=unigenelist.get(i).getSequence().length();
            if((double)currentlengh/totallengh>=0.3){
                System.out.println("N30 = "+unigenelist.get(i).getSequence().length()+";\t sequence count = "+(i+1));
                currenti=i+1;
                break;
            }
        }
        for (int i = currenti; i < unigenelist.size(); i++) {
            currentlengh+=unigenelist.get(i).getSequence().length();
            if((double)currentlengh/totallengh>=0.4){
                System.out.println("N40 = "+unigenelist.get(i).getSequence().length()+";\t sequence count = "+(i+1));
                currenti=i+1;
                break;
            }
        }
        for (int i = currenti; i < unigenelist.size(); i++) {
            currentlengh+=unigenelist.get(i).getSequence().length();
            if((double)currentlengh/totallengh>=0.5){
                System.out.println("N50 = "+unigenelist.get(i).getSequence().length()+";\t sequence count = "+(i+1));
               currenti=i+1;
                break;
            }
        }
        for (int i = currenti; i < unigenelist.size(); i++) {
            currentlengh+=unigenelist.get(i).getSequence().length();
            if((double)currentlengh/totallengh>=0.6){
                System.out.println("N60 = "+unigenelist.get(i).getSequence().length()+";\t sequence count = "+(i+1));
                currenti=i+1;
                break;
            }
        }
        for (int i = currenti; i < unigenelist.size(); i++) {
            currentlengh+=unigenelist.get(i).getSequence().length();
            if((double)currentlengh/totallengh>=0.7){
                System.out.println("N70 = "+unigenelist.get(i).getSequence().length()+";\t sequence count = "+(i+1));
                currenti=i+1;
                break;
            }
        }
        for (int i = currenti; i < unigenelist.size(); i++) {
            currentlengh+=unigenelist.get(i).getSequence().length();
            if((double)currentlengh/totallengh>=0.8){
                System.out.println("N80 = "+unigenelist.get(i).getSequence().length()+";\t sequence count = "+(i+1));
                currenti=i+1;
                break;
            }
        }
        for (int i = currenti; i < unigenelist.size(); i++) {
            currentlengh+=unigenelist.get(i).getSequence().length();
            if((double)currentlengh/totallengh>=0.9){
                System.out.println("N90 = "+unigenelist.get(i).getSequence().length()+";\t sequence count = "+(i+1));
                currenti=i+1;
                break;
            }
        }
        
    }
    
    public static void main(String[] args) {
        System.out.println("Unigene N number");
        TrinityProcess.calculationNnummer("F:\\mywork\\project\\yujing\\Trinity.fasta",true);
        System.out.println("Transcprit N number");
        TrinityProcess.calculationNnummer("F:\\mywork\\project\\yujing\\Trinity.fasta",false);
    }
    
    
}
