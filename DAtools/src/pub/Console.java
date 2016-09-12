package pub;

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
import BedProcess.BedProcessConsole;
import BlastResultXMLsplit.BlastXMLsplit;
import RNAseqPipeline.DoseCompensation.RemoveIntersectRegionFromGTF.RemoveIntersectProcess;
import ExomeSeqAnalysisPipe.ExomeSeqConsole;
import FastQprocess.FastQprocess;
import FastQprocess.FastQprocessConsole;
import FastQprocess.seperateFastQByindex;
import FastaProcess.FastaProcessConsole;
import FormatConvert.exceloperation.ExcelOperation;
import FormatConvert.tab2excel;
import GOannotation.GOmapping;
import FastaProcess.GetFastaByID.extractFastaByID;
import KeggAnnotation.PlotKegg.KeggPlotProcess;
import MetaGenomeAnalysis.RawdataQC.GetNonHumanFastqFromBlastOut;
import Multifile2Matrix.MatrixGenerateConsole;
import RNAseqPipeline.CufflinksOutputProcess.CufflinksOutFileReader;
import RNAseqPipeline.DifferentialExpressionAnalysis.DEGeneAnalysis;
import RNAseqPipeline.DoseCompensation.DoseCompansationConsole;
import RNAseqPipeline.GeneLengthFileReader;
import RNAseqPipeline.RNAseqConsole;
import RNAseqPipeline.runTophat2andHtseq;
import SeperateFataFileByIndexBiFC.SeperateSequenceByIndex;
import SeperateFataFileByIndexBiFC.removeAdaper;
import VenPlotRscriptGenerator.VenPlotConsole;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.xml.parsers.ParserConfigurationException;

/**
 *
 * @author ZHAO Qi
 * @date 2014-3-6 10:32:12
 * @version 1.6.0
 */
public class Console {

    public static void main(String[] args) throws SpecialException, IOException, FileNotFoundException, ParserConfigurationException {
        long start = System.nanoTime();
        String version="2.7.2";
        System.out.println();
        if (args.length == 0) {
            System.out.println(ToolsforCMD.ANSI_YELLOW+ToolsforCMD.getDAtoolstr()+ToolsforCMD.ANSI_RESET);
            System.out.println(ToolsforCMD.ANSI_YELLOW+"Java-based Data Analysis tool for biological data process, version "+version+ToolsforCMD.ANSI_RESET+"\r\n");

            System.out.println("Please input args\n type "+
                    ToolsforCMD.ANSI_GREEN+"java -jar dataAnalsisTools.jar "+ToolsforCMD.ANSI_RESET+ToolsforCMD.ANSI_CYAN+"-h"+ToolsforCMD.ANSI_RESET+" for help\r\n");
        } else if (args[0].endsWith("-h")) {
            System.out.println(ToolsforCMD.ANSI_YELLOW+ToolsforCMD.getDAtoolstr()+ToolsforCMD.ANSI_RESET);
            System.out.println(ToolsforCMD.ANSI_YELLOW+"Java-based Data Analysis tool for biological data process, version "+version+ToolsforCMD.ANSI_RESET+"\r\n");

            System.out.println("Please input args\n Please refers to CMD：java -jar dataAnalsisTools.jar -h for help");
            ArrayList<String> functionlist=new ArrayList<String>();
            functionlist.add("Devided fastq into two file by length :   \r\n\t\t"+
                    ToolsforCMD.ANSI_GREEN+"java -jar DAtools.jar -devidedFastQbylength"+ToolsforCMD.ANSI_RESET+
                        ToolsforCMD.ANSI_CYAN+" length all.fq out1.fq out2.fq"+ToolsforCMD.ANSI_RESET);
           functionlist.add("Exome-seq function    \r\n\t\t"+
                    ToolsforCMD.ANSI_GREEN+"java -jar DAtools.jar -exome"+ToolsforCMD.ANSI_RESET);
            functionlist.add("Extract expression data from cufflinks outputfile :   \r\n\t\t"+
                    ToolsforCMD.ANSI_GREEN+"java -jar DAtools.jar -getcufflinksExpress"+ToolsforCMD.ANSI_RESET+
                        ToolsforCMD.ANSI_CYAN+" cuffout_dir"+ToolsforCMD.ANSI_RESET);
            functionlist.add("Extract fastaSequence by idfile :   \r\n\t\t"+
                    ToolsforCMD.ANSI_GREEN+"java -jar DAtools.jar -extractFastabyID"+ToolsforCMD.ANSI_RESET+
                        ToolsforCMD.ANSI_CYAN+" fastafile idfile(one id in one line) outfile)"+ToolsforCMD.ANSI_RESET);
            functionlist.add("Functions to convert file format :   \r\n\t\t"+
                    ToolsforCMD.ANSI_GREEN+"java -jar DAtools.jar -convertFormat "+ToolsforCMD.ANSI_RESET);
            functionlist.add("Generate a Ven plot by certain data input:   \r\n\t\t"+
                    ToolsforCMD.ANSI_GREEN+"java -jar DAtools.jar -venplot"+ToolsforCMD.ANSI_RESET+
                        ToolsforCMD.ANSI_CYAN+" <datafile> <maintext> <plotType>"+ToolsforCMD.ANSI_RESET);
            functionlist.add("Getovelap of two list:  \r\n\t\t"+
                    ToolsforCMD.ANSI_GREEN+"java -jar DAtools.jar -getoverlap"+ToolsforCMD.ANSI_RESET+
                        ToolsforCMD.ANSI_CYAN+" <filelist1> <filelist2> <outputfile>"+ToolsforCMD.ANSI_RESET);
            functionlist.add("Kegg Analysis :   \r\n\t\t"+
                    ToolsforCMD.ANSI_GREEN+"java -jar DAtools.jar -kegg "+ToolsforCMD.ANSI_RESET);
            functionlist.add("Local GO mapping of gene list :   \r\n\t\t"+
                    ToolsforCMD.ANSI_GREEN+"java -jar DAtools.jar -gomap"+ToolsforCMD.ANSI_RESET+
                        ToolsforCMD.ANSI_CYAN+" <obofile> <annotation> <nametype(1 for uniprotID(new);2 for geneSympol)> <genelistfile> <output>"+ToolsforCMD.ANSI_RESET);
            functionlist.add("Multi mapfile merged to matrix    \r\n\t\t"+
                    ToolsforCMD.ANSI_GREEN+"java -jar DAtools.jar -MM"+ToolsforCMD.ANSI_RESET+
                        ToolsforCMD.ANSI_CYAN+"  dir suffix outputfile  "+ToolsforCMD.ANSI_RESET);
            functionlist.add("Procesing FastQ files :   \r\n\t\t"+
                    ToolsforCMD.ANSI_GREEN+"java -jar DAtools.jar -fastqP"+ToolsforCMD.ANSI_RESET);
            functionlist.add("Process Fastafile    \r\n\t\t"+
                    ToolsforCMD.ANSI_GREEN+"java -jar DAtools.jar -Fasta   "+ToolsforCMD.ANSI_RESET);
            functionlist.add("Remove SUMO-FC adaptors :   \r\n\t\t"+
                    ToolsforCMD.ANSI_GREEN+"java -jar DAtools.jar -removeAdapter"+ToolsforCMD.ANSI_RESET+
                        ToolsforCMD.ANSI_CYAN+" <fastafile> <adapter(TATA,CACA,GAGA)> <type(N/C)>"+ToolsforCMD.ANSI_RESET);
            functionlist.add("RNAseq Differential Expression Analysis(M):   \r\n\t\t"+
                    ToolsforCMD.ANSI_GREEN+"java -jar DAtools.jar -RNAseqDE -mode WR(orSC)"+ToolsforCMD.ANSI_RESET+
                        ToolsforCMD.ANSI_CYAN+" <condition1> <condition2> <outputfile>  "+ToolsforCMD.ANSI_RESET);
            functionlist.add("RNAseq Mappng/quantitifaction(M):   \r\n\t\t"+
                    ToolsforCMD.ANSI_GREEN+"java -jar DAtools.jar -RNAseqpipe"+ToolsforCMD.ANSI_RESET+
                        ToolsforCMD.ANSI_CYAN+" <fastq1> <fastq2> <library> <gtf> "+ToolsforCMD.ANSI_RESET);
            functionlist.add("Seperate fastq File by index :   \r\n\t\t"+
                    ToolsforCMD.ANSI_GREEN+"java -jar DAtools.jar -sepFastq"+ToolsforCMD.ANSI_RESET+
                        ToolsforCMD.ANSI_CYAN+" <fastaqfile> <indexmapfile>"+ToolsforCMD.ANSI_RESET);
            functionlist.add("SepfastaFilebyIndex :   \r\n\t\t"+
                    ToolsforCMD.ANSI_GREEN+"java -jar DAtools.jar -sepFastabyIndex"+ToolsforCMD.ANSI_RESET+
                        ToolsforCMD.ANSI_CYAN+" <fastafile> <indexlist(TATA,CACA,GAGA)>"+ToolsforCMD.ANSI_RESET);
            functionlist.add("Shorter sequencename of genome seq to parse gtg files :   \r\n\t\t"+
                    ToolsforCMD.ANSI_GREEN+"java -jar DAtools.jar -genomenamecut"+ToolsforCMD.ANSI_RESET+
                        ToolsforCMD.ANSI_CYAN+" <genome.fa> <out.fa> "+ToolsforCMD.ANSI_RESET);
            functionlist.add("Split the xml file generated by blast :   \r\n\t\t"+
                    ToolsforCMD.ANSI_GREEN+"java -jar DAtools.jar -BlastXMLsplit"+ToolsforCMD.ANSI_RESET+
                        ToolsforCMD.ANSI_CYAN+" <fileinputxml> <seqnumber>"+ToolsforCMD.ANSI_RESET);
            functionlist.add("Summary the unigenes distribution with trinites output(Decreased) :   \r\n\t\t"+
                    ToolsforCMD.ANSI_GREEN+"java -jar DAtools.jar -unigenedistribution"+ToolsforCMD.ANSI_RESET+
                        ToolsforCMD.ANSI_CYAN+" <isunigene> <trinity.fasta>"+ToolsforCMD.ANSI_RESET);
            functionlist.add("Processing Excel format :   \r\n\t\t"+
                    ToolsforCMD.ANSI_GREEN+"java -jar DAtools.jar -excel "+ToolsforCMD.ANSI_RESET);
            functionlist.add("MetaGenome analysis :   \r\n\t\t"+
                    ToolsforCMD.ANSI_GREEN+"java -jar DAtools.jar -meta"+ToolsforCMD.ANSI_RESET);
            functionlist.add("Dose ComposationFunctions :   \r\n\t\t"+
                    ToolsforCMD.ANSI_GREEN+"java -jar DAtools.jar -dose"+ToolsforCMD.ANSI_RESET);
            functionlist.add("BED Process :   \r\n\t\t"+
                    ToolsforCMD.ANSI_GREEN+"java -jar DAtools.jar -bed"+ToolsforCMD.ANSI_RESET);

            Collections.sort(functionlist);
            for (int i = 0; i < functionlist.size(); i++) {
                System.out.println(ToolsforCMD.ANSI_RED+"========"+i+".\t"+ToolsforCMD.ANSI_RESET+functionlist.get(i));
            }

            System.out.println("Type -updateinfor to view update information");
            System.out.println("Funcion with (M) followed indicates that there are more parameters can be set by typing -h");

            System.out.println("\n\n\nWritten by Qi Zhao(zhaoqi3@mail2.sysu.edu.cn),Ren's Lab ,Key Laboratory of Biocontrol, School of Life Sciences, Sun Yat-sen University  \r\n");

        } else if (args[0].endsWith("-updateinfor")) {
            System.out.println("\r\nUpdate information\r\n");
             
            
            System.out.println("\r\n2.7.2 unique bed function ");
            System.out.println("\r\n2.7.1 add sequence feature calculation ");
            System.out.println("\r\n2.7 Add dose composation analysis function ");
            System.out.println("\r\n2.6 Add meta data process function ");
            System.out.println("\r\n2.5 Add Excel processing function ");
            System.out.println("\r\n2.4 Add fastqProcess function: trimFastqLeftconstantLength ");
            System.out.println("\r\n2.3 Add exome-seq analysis tools 3: runCNVnator parallel  ");
            System.out.println("\r\n2.2 Add exome-seq analysis tools 2: runCrest parallel  ");
            System.out.println("\r\n2.1 Add exome-seq analysis tools 1: varscan output to ICGC upload format  ");
            System.out.println("\r\n2.0 Add exome-seq analysis tools 1: snp frequency status  ");
            System.out.println("\r\n1.9.Add function : Multi mapfile merged to matrix  ");
            System.out.println("\r\n1.8.Add function : Differential Expression Analysis without replicates  "); 
            System.out.println("\r\n1.7.remove function: SUMO-FC quantitifaction,add RNA-seq mapping/quanitification pipeline ");
            System.out.println("\r\n1.6.Add function: SUMO-FC quantitifaction ");
            System.out.println("\r\n1.5.Add function: extract fasta sequence by id) ");
            System.out.println("\r\n1.4.Add function: remove SUMO-FC adaptors(setted inside) ");
            System.out.println("\r\n1.3.Add function: devided fastafile by index ");
            System.out.println("\r\n1.2. Add go mapping based on genelist function");
            System.out.println("\r\n1.1. Modified \"Extract expression data from cufflinks outputfile\", addheader information");
            
            
        } else if (args[0].endsWith("-fastqP")) {
           new FastQprocessConsole(args);
        } else if (args[0].endsWith("-venplot")) {
            if (args.length == 4) {
                // System.out.println();
                System.out.println(ToolsforCMD.startruningSTR());
                VenPlotConsole.plot(args[1], args[2], args[3]);
            } else {
                System.out.println("args error!");
            }

        }  else if (args[0].endsWith("-devidedFastQtrimed")) {
            if (args.length == 5) {
                // System.out.println();
                System.out.println(ToolsforCMD.startruningSTR());
                new FastQprocess(args[2]).dividedBylength(Integer.parseInt(args[1]), args[3], args[4]);
            } else if ((args.length == 3)) {
                new FastQprocess(args[2]).dividedBylength(Integer.parseInt(args[1]), "out1.fq", "out2_fq");
            } else {
                System.out.println("args error!");
            }
        } else if (args[0].endsWith("-getcufflinksExpress")) {
            if (args.length == 2) {
                // System.out.println();
                System.out.println(ToolsforCMD.startruningSTR());
                new CufflinksOutFileReader(args[1] + File.separator + "genes.read_group_tracking", args[1] + File.separator + "expressionTable.xlsx");

            } else {
                System.out.println("args error!");
            }
        } else if (args[0].endsWith("-BlastXMLsplit")) {
            if (args.length == 3) {
                System.out.println(ToolsforCMD.startruningSTR());
                new BlastXMLsplit(args[1], Integer.parseInt(args[2]));
            }
        } else if (args[0].endsWith("-sepFastq")) {
            if (args.length == 3) {
                // System.out.println();
                System.out.println(ToolsforCMD.startruningSTR());
                new seperateFastQByindex(args[1], args[2]);
            } else {
                System.out.println("args error!");
            }

        } else if (args[0].endsWith("-gomap")) {
            if (args.length == 6) {
               // System.out.println();
                //-gomap obofile annotation nametype(1 for uniprotID(new);2 for geneSympol) genelistfile output\r\n");
                System.out.println(ToolsforCMD.startruningSTR());
                GOmapping gomap = new GOmapping(args[2], args[1], Integer.parseInt(args[3]));

                gomap.writeMultyresultlistSimpleFile(args[5], args[4]);

            } else {
                System.out.println("args error!");
            }

        } else if (args[0].endsWith("-sepFastabyIndex")) {
            if (args.length == 3) {
                // System.out.println();
                //-gomap obofile annotation nametype(1 for uniprotID(new);2 for geneSympol) genelistfile output\r\n");
                //String [] indexlib={"TATA","CACA","GAGA"};
                String[] indexlib = args[2].split(",");
                System.out.println(ToolsforCMD.startruningSTR());
                new SeperateSequenceByIndex(args[1], indexlib);

            } else {
                System.out.println("args error!");
            }

        } else if (args[0].endsWith("-removeAdapter")) {
            if (args.length == 4) {
               // System.out.println();
                //-gomap obofile annotation nametype(1 for uniprotID(new);2 for geneSympol) genelistfile output\r\n");
                //String [] indexlib={"TATA","CACA","GAGA"};
                System.out.println(ToolsforCMD.startruningSTR());
                new removeAdaper(args[1], args[2], args[3]); 

            } else {
                System.out.println("args error!");
            }

        } else if (args[0].endsWith("-extractFastabyID")) {
            if (args.length == 4) {
                //默认从第一个文件的id行找到匹配第二个文件id 的fasta序列并输出
                System.out.println(ToolsforCMD.startruningSTR());
                new extractFastaByID(args[1], args[2], args[3]);

            } else {
                System.out.println("args error!");
            }

        } else if (args[0].endsWith("-RNAseqpipe")) {
            new RNAseqConsole(args);
        } else if (args[0].endsWith("-RNAseqDE")) {
            if (args.length == 1) {
                System.out.println("RNAseq Mappng/quantitifaction:   \r\n\t\t"+ToolsforCMD.ANSI_GREEN+"java -jar DAtools.jar -RNAseqDE -mode WR(orSC) <condition1> <condition2> <outputfile>\r\n"+ToolsforCMD.ANSI_RESET);
                System.out.println("Extra paramters  \r\n\t\t-mode\t WR(without replicates),SC(standard comparison) or MF(Multi factors)\r\n"+ToolsforCMD.ANSI_RESET);
                System.out.println(" \r\n\t\t-fpkm\t calculate fpkm by supply a gene length file\r\n"+ToolsforCMD.ANSI_RESET);
            } else {

                DEGeneAnalysis deg = new DEGeneAnalysis();
                if (FunctionClass.getArgsParameter(args, "-fpkm") != null) {
                    //to be finished
                    String lengthfile = FunctionClass.getArgsParameter(args, "-fpkm");
                    deg.setGene2length(GeneLengthFileReader.getgeneLengthMap(lengthfile));

                }
                deg.getDEgenePvalue(args[3], args[4]);
                deg.getOutputFile(args[5]);

            }
        } else if (args[0].endsWith("-MM")) {
            new MatrixGenerateConsole(args);
        }else if (args[0].endsWith("-Fasta")) {
            new FastaProcessConsole(args);
        } else if (args[0].endsWith("-exome")) {
            new ExomeSeqConsole(args);
        } else if (args[0].endsWith("-convertFormat")) {
            new MatrixGenerateConsole(args);
        } else if (args[0].endsWith("-kegg")) {
            if (args.length == 1) {
                System.out.println("Plot kegg blast result with mappfile\r\n\t\t"+ToolsforCMD.ANSI_GREEN+"java -jar DAtools.jar -kegg -mode plotmap kegg.log <geneExressionFile> <keggmapdir> <outmapdir> [options]"+ToolsforCMD.ANSI_RESET);
                
            } else if(FunctionClass.getArgsParameter(args, "-mode").equalsIgnoreCase("plotmap")){
                KeggPlotProcess dp=new KeggPlotProcess(args[3],args[4],args[5],args[6]);
                
            }
        }else if (args[0].endsWith("-excel")) {
            if (args.length == 1) {
                System.out.println("Convert tab file into excel file \r\n\t\t"+ToolsforCMD.ANSI_GREEN+"java -jar DAtools.jar -excel -mode tab2excel <infile>"+ToolsforCMD.ANSI_RESET);
                System.out.println("Merge Excelfile in one \r\n\t\t"+ToolsforCMD.ANSI_GREEN+"java -jar DAtools.jar -excel -mode merge <inputdir> <outfile>"+ToolsforCMD.ANSI_RESET);
                System.out.println("Divid Excelfile into multiples\r\n\t\t"+ToolsforCMD.ANSI_GREEN+"java -jar DAtools.jar -excel -mode <split> <inputfile> outdir>"+ToolsforCMD.ANSI_RESET);
            } else if(FunctionClass.getArgsParameter(args, "-mode").equalsIgnoreCase("tab2excel")){
                new tab2excel(args[3]).tab2excel();
              
            }else if(FunctionClass.getArgsParameter(args, "-mode").equalsIgnoreCase("merge")){
                ExcelOperation.MergeExcel(args[3], args[4]);
            }else if(FunctionClass.getArgsParameter(args, "-mode").equalsIgnoreCase("split")){
                 ExcelOperation.SeperatorExcel2sheet(args[3], args[4]);
            }else {
                System.out.println("Please specified -mode parameters!");
            }
        }else if (args[0].endsWith("-meta")) {
            if (args.length == 1) {
                System.out.println("Extract non human fastq items with blast output \r\n\t\t"+ToolsforCMD.ANSI_GREEN+"java -jar DAtools.jar -exome -mode ehuman <inputFastqfile> <blastOutput> <outputfile"+ToolsforCMD.ANSI_RESET);
                System.out.println("Remove overlap region in gtf \r\n\t\t"+ToolsforCMD.ANSI_GREEN+"java -jar DAtools.jar -exome -mode removeGTF <inputGTFfile> <inputIntersectFile> <outfilename>"+ToolsforCMD.ANSI_RESET);
                
            } else if(FunctionClass.getArgsParameter(args, "-mode").equalsIgnoreCase("ehuman")){
                new GetNonHumanFastqFromBlastOut(args[3], args[4],args[5]);
            }else if(FunctionClass.getArgsParameter(args, "-mode").equalsIgnoreCase("removeGTF")){
                new RemoveIntersectProcess(args[3], args[4], args[5]);
            }
            
            else {
                System.out.println("Please specified -mode parameters!");
            }
        }else if (args[0].endsWith("-dose")) {
            new DoseCompansationConsole(args);
        } else if (args[0].endsWith("-bed")) {
            new BedProcessConsole(args);
        }else {
            System.out.println("command error!");
        }
        long end = System.nanoTime();
        System.out.println("Total running time is " + (end - start) * 10e-10 + "s");
    }
}
