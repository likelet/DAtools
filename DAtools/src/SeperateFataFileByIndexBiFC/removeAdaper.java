package SeperateFataFileByIndexBiFC;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */



import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.HashMap;
import java.util.logging.Level;
import java.util.logging.Logger;
import pub.Fasta;
import pub.SequenceVerifier;

/**
 *
 * @author zhaoqi
 */
public class removeAdaper {
     private BufferedReader br;
    private SequenceVerifier sequenceVerifier;
    private String adapter="";
    private String type="";
    private String NF="cgccctgagcaaagaccccaacgagaagcgcgatcacatggtcctgctggagttcgtgaccgccgccgggatcactctcggcatggacgagctgtacaagtttgtacaaaaaagcaggct".toUpperCase();
    private String NR="cgaccactgtgctggcgaattagcCGACCACTGTGCTGGCGAATTAGCTTCTGCAGGTCGACTCTAGAGGATCCCCGGGGAATTATCAACCACTTTGTACAAGAAAGCTGGGT".toUpperCase();
    private String CF="cacctcggttctatcgattgatcaacaagtttgtacaaaaaagcaggct".toUpperCase();
    private String CR="CGGGGATCAACCACTTTGTACAAGAAAGCTGGGT".toUpperCase();

    public removeAdaper(String fastafile,String adapter,String type) throws IOException {
        this.adapter = adapter;
        NF=adapter+NF;
        NR=adapter+NR;
        CF=adapter+CF;
        CR=adapter+CR;
        try {
            br = new BufferedReader(new FileReader(new File(fastafile)));
            sequenceVerifier = new SequenceVerifier();
            this.process();
        } catch (FileNotFoundException ex) {
            ex.printStackTrace();
        }
    }
    public void process() throws IOException {
       
        String str = "";
        try {
            //construct hasmap
                str = adapter+"_trim.fasta";
                FileWriter fw = new FileWriter(str);
     

            Fasta fasta = new Fasta();
            String tempstr = "";
            int nf=0;
            int nr=0;
            int cf=0;
            int cr=0;
            while (br.ready()) {
                //System.out.println(count);
                tempstr = br.readLine();
                //String tempa[]=br.readLine().split(" ");
                if (tempstr.startsWith(">")) {

                    fasta.setName(tempstr);
                } else {
                    fasta.setSequence(tempstr);
                    if(this.type.equals("N")){
                        if(tempstr.startsWith(NR)){
                            fw.append(fasta.getName()+"\r\n");
                           fw.append(tempstr.substring(NR.length()+3)+"\n");
                           nr++;
                        }else if(tempstr.startsWith(NF)){
                           fw.append(tempstr.substring(NF.length()+3)+"\n");
                           nf++;
                        }
                        fw.flush();
                    }else if(this.type.equals("C")){
                        if(tempstr.startsWith(CR)){
                            fw.append(fasta.getName()+"\r\n");
                           fw.append(tempstr.substring(CR.length()+3)+"\n");
                           cr++;
                        }else if(tempstr.startsWith(CF)){
                            fw.append(fasta.getName()+"\r\n");
                           fw.append(tempstr.substring(CF.length()+3)+"\n");
                           cf++;
                        }
                        fw.flush();
                    }
                       
                    
                    
                }
            }
            //close fw
          fw.close();
          System.out.println("nr\tnf\tcr\tcf\r\n"+nr+"\t"+nf+"\t"+cr+"\t"+cf);
        } catch (IOException ex) {
            Logger.getLogger(SeperateSequenceByIndex.class.getName()).log(Level.SEVERE, null, ex);
        }

    }
    public static void main(String[] args) throws IOException {
    removeAdaper a=new removeAdaper("D:\\TATA_index.fasta","TATA","N");
//        String a="TATACGGGGATCAACCACTTTGTACAAGAAAGCTGGGT";
//        String b="TATACGGGGATCAACCACTTTTTACAAGAAAGCTGGGTCGGCGCGCCCCTAACCTCCCGTCTGCTGCTGGAACACATCAATTGTATCTTCATCCTCCATTTCCAACTGTGCAGGTGTGTCTGTTTCATTGATTGGTTGCCCGTCAAATCGGAATCTGATCTGCCTCATTGACAATCCCTGTC";
//        String c="TATACGGGGATCAACCACTTTGTACAAGAAAGCTGGGTCGGCGCGCCCCTAACCTCCCGTCTGCTGCTGGAACACATCAATTGTATCTTCATCCTCCATTTCCAACTGTGCAGGTGTGTCTGTTTCATTGATTGGTTGCCCGTCAAATCGGAATCTGATCTGCCTCATTGACAATCCCTGTCGTTCACAATA";
//        
    System.out.println(a.CF+"\r\n"+a.CR+"\r\n"+a.NF+"\r\n"+a.NR+"\r\n");
         //System.out.println("CGGGGATCAACCACTTTGTACAAGAAAGCTGGGT".toUpperCase());
    }
   
}
