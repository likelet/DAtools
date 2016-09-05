/*
 reference
1. Hasegawa M, Yano TA (1975) Entropy of the genetic information and evolution. Orig Life 6: 
219-227.
2. Kimura M (1980) A simple method for estimating evolutionary rates of base substitutions through 
comparative studies of nucleotide sequences. J Mol Evol 16: 111-120.
3. Lewontin RC (1972) The Apportionment of Human Diversity. Evolutionary Biology: 381-398.
4. Ohno Y, Narangajavana J, Yamamoto A, Hattori T, Kagaya Y, et al. (2011) Ectopic gene expression 
and organogenesis in Arabidopsis mutants missing BRU1 required for genome maintenance. 
Genetics 189: 83-95.
5. Teschendorff AE, Severini S (2010) Increased entropy of signal transduction in the cancer 
metastasis phenotype. BMC Syst Biol 4: 104.
6. van Wieringen WN, van der Vaart AW (2011) Statistical analysis of the cancer cell's molecular 
entropy using high-throughput data. Bioinformatics 27: 556-563.
7. West J, Bianconi G, Severini S, Teschendorff AE (2012) Differential network entropy reveals cancer 
system hallmarks. Sci Rep 2: 802.
8. Hausser J, Strimmer K (2009) Entropy Inference and the James-Stein Estimator, with Application to 
Nonlinear Gene Association Networks. Journal of Machine Learning Research 10: 1469-1484.
9. International Cancer Genome C, Hudson TJ, Anderson W, Artez A, Barker AD, et al. (2010
 */

package Algorithm.GeneticInformationEntropy;

import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * <p>GIEcalculator</p>
 * <p>Created on 2015-12-14 15:54:50</p>
 * <p>Author Email: zhaoqi3@mail2.sysu.edu.cn</p>
 * @author ZHAO Qi
 * @date 2015-12-14 15:54:50
 * @version java 1.6.0
 * @version 
 */
public class GIEcalculator {
    private  String infile;
    private String outfile;
    private ArrayList<Output> outputlist;
    public GIEcalculator(String infile, String outfile) {
        this.infile = infile;
        this.outfile = outfile;
        
    }
   
    private void process(){
    outputlist=new ReadDataMatrix(infile).getOutlist();
    GIEcalculatorProcess(outputlist);
        try {
            FileWriter fw = new FileWriter(outfile);
            for (int i = 0; i < outputlist.size(); i++) {
                 fw.append(outputlist.get(i).toString()+"\n");
            }
           
            fw.flush();
            fw.close();
        } catch (IOException ex) {
            Logger.getLogger(GIEcalculator.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    
    private void GIEcalculatorProcess(ArrayList<Output> outputlist ){
        for (int i = 0; i < outputlist.size(); i++) {
            outputlist.get(i).setGIE(this.getGIE(outputlist.get(i).getExpdata()));
        }
    }
    
    private ArrayList<Double> getplist(ArrayList<Double> explist){
        ArrayList<Double> plist=new ArrayList<Double>();
        double absum=0;
        for (int i = 0; i < explist.size(); i++) {
            absum+=Math.abs(explist.get(i));
        }
        for (int i = 0; i < explist.size(); i++) {
            plist.add(Math.abs(explist.get(i))/absum);
        }
        return plist;
    }
    
    private double getGIE(ArrayList<Double> explist){
        ArrayList<Double> plist=this.getplist(explist);
        double GIE=0;
        for (int i = 0; i < plist.size(); i++) {
            GIE+=-plist.get(i)*Math.log(plist.get(i));
        }
        return GIE;
    }
    
    public static void main(String[] args) {
        new GIEcalculator("","");
    }
}
