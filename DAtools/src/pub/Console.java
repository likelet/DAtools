package pub;

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
import BedProcess.BedProcessConsole;
import BlastResultXMLsplit.BlastXMLsplit;
import DoseCompensation.RemoveIntersectRegionFromGTF.RemoveIntersectProcess;
import DoseCompensation.RemoveIntersectRegionFromGTF.computeRPKM;
import DoseCompensation.RemoveIntersectRegionFromGTF.gtf2lengthfile;
import DoseCompensation.ResultStatistic.StaticChrome;
import DoseCompensation.ResultStatistic.StaticChromeByClass;
import DoseCompensation.SequenceFeature.CombineAnalysis;
import DrawUnigeneDistribution.DrawDistributionPlot;
import DrawUnigeneDistribution.TrinityProcess;
import ExomeSeqAnalysisPipe.ExomeSeqConsole;
import FastQprocess.FastQprocess;
import FastQprocess.FastQprocessConsole;
import FastQprocess.seperateFastQByindex;
import FastaProcess.SplitFasta2multifile;
import FastaStatic.getFastaLength;
import FormatConvert.exceloperation.ExcelOperation;
import FormatConvert.tab2excel;
import GOannotation.GOmapping;
import GetFastaByID.extractFastaByID;
import KeggAnnotation.PlotKegg.KeggPlotProcess;
import MetaGenomeAnalysis.RawdataQC.GetNonHumanFastqFromBlastOut;
import Multifile2Matrix.Multifile2matrix;
import RNAseqCountAndExpressionStatic.CufflinksOutFileReader;
import RNAseqPipeline.DEGeneAnalysis;
import RNAseqPipeline.GeneLengthFileReader;
import RNAseqPipeline.runTophat2andHtseq;
import SeperateFataFileByIndexBiFC.SeperateSequenceByIndex;
import SeperateFataFileByIndexBiFC.removeAdaper;
import VenPlotRscriptGenerator.VenPlotConsole;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
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
            System.out.println(ToolsforCMD.getDAtoolstr());
            System.out.println("Java based Data Analysis tool, version "+version+"\r\n");

            System.out.println("please input args\n type java -jar dataAnalsisTools.jar -h for help\r\n");
        } else if (args[0].endsWith("-h")) {
            System.out.println(ToolsforCMD.getDAtoolstr());
            System.out.println("Java based Data Analysis tool, version "+version+"\r\n");

            System.out.println("please input args\n Please refers to CMD：java -jar dataAnalsisTools.jar -h for help");
            System.out.println("======1. Devided fastq into two file by length :   \r\n\t\tCMD : java -jar DAtools.jar -devidedFastQbylength length all.fq out1.fq out2.fq");
            System.out.println("======2. Draw genelength Distribution :   \r\n\t\tCMD : java -jar DAtools.jar -genelengthDis");
            System.out.println("======3. Exome-seq function    \r\n\t\tCMD : java -jar DAtools.jar -exome   ");
            System.out.println("======4. Extract expression data from cufflinks outputfile :   \r\n\t\tCMD : java -jar DAtools.jar -getcufflinksExpress cuffout_dir");
            System.out.println("======5. Extract fastaSequence by idfile :   \r\n\t\tCMD : java -jar DAtools.jar -extractFastabyID fastafile idfile(one id in one line) outfile)");
            System.out.println("======6. Functions to convert file format :   \r\n\t\tCMD : java -jar DAtools.jar -convertFormat ");
            System.out.println("======7. Generate a Ven plot by certain data input:   \r\n\t\tCMD : java -jar DAtools.jar -venplot datafile maintext plotType");
            System.out.println("======8. Getovelap of two list with unique output:   \r\n\t\tCMD : java -jar DAtools.jar -getoverlap filelist1 filelist2 outputfile uniqueA uniqueB");
            System.out.println("======9. Getovelap of two list:  \r\n\t\tCMD : java -jar DAtools.jar -getoverlap filelist1 filelist2 outputfile");
            System.out.println("======10. Kegg Analysis :   \r\n\t\tCMD : java -jar DAtools.jar -kegg ");
            System.out.println("======11. Local GO mapping of gene list :   \r\n\t\tCMD : java -jar DAtools.jar -gomap obofile annotation nametype(1 for uniprotID(new);2 for geneSympol) genelistfile output");
            System.out.println("======12. Multi mapfile merged to matrix    \r\n\t\tCMD : java -jar DAtools.jar -multi2matrix  dir suffix outputfile  ");
            System.out.println("======13. Procesing FastQ files :   \r\n\t\tCMD : java -jar DAtools.jar -fastqP");
            System.out.println("======14. Process Fastafile    \r\n\t\tCMD : java -jar DAtools.jar -Fasta   ");
            System.out.println("======15. Remove SUMO-FC adaptors :   \r\n\t\tCMD : java -jar DAtools.jar -removeAdapter fastafile adapter(TATA,CACA,GAGA) type(N/C)");
            System.out.println("======16. RNAseq Differential Expression Analysis(M):   \r\n\t\tCMD : java -jar DAtools.jar -RNAseqDE -mode WR(orSC) condition1 condition2 outputfile  ");
            System.out.println("======17. RNAseq Mappng/quantitifaction(M):   \r\n\t\tCMD : java -jar DAtools.jar -RNAseqpipe <fastq1> <fastq2> <library> <gtf> ");
            System.out.println("======18. Seperate fastq File by index :   \r\n\t\tCMD : java -jar DAtools.jar -sepFastq fastaqfile indexmapfile");
            System.out.println("======19. SepfastaFilebyIndex :   \r\n\t\tCMD : java -jar DAtools.jar -sepFastabyIndex fastafile indexlist(TATA,CACA,GAGA)");
            System.out.println("======20. Shorter sequencename of genome seq to parse gtg files :   \r\n\t\tCMD : java -jar DAtools.jar -genomenamecut genome.fa out.fa ");
            System.out.println("======21. split the xml file generated by blast :   \r\n\t\tCMD : java -jar DAtools.jar -BlastXMLsplit fileinputxml seqnumber");
            System.out.println("======22. Summary the unigenes distribution with trinites output(Decreased) :   \r\n\t\tCMD : java -jar DAtools.jar -unigenedistribution isunigene trinity.fasta");
            System.out.println("======23. Processing Excel format :   \r\n\t\tCMD : java -jar DAtools.jar -excel ");
            System.out.println("======24. MetaGenome analysis :   \r\n\t\tCMD : java -jar DAtools.jar -meta");
            System.out.println("======25. Dose ComposationFunctions :   \r\n\t\tCMD : java -jar DAtools.jar -dose");
            System.out.println("======25. BED Process :   \r\n\t\tCMD : java -jar DAtools.jar -bed");



            System.out.println("Type -updateinfor to view update information");
            System.out.println("Funcion with (M) followed indicates that there are more parameters can be set by typing -h");

            System.out.println("\n\n\n\n\n\nWritten by Qi Zhao(zhaoqi3@mail2.sysu.edu.cn),Ren's Lab ,Key Laboratory of Biocontrol, School of Life Sciences, Sun Yat-sen University  \r\n");

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
        } else if (args[0].endsWith("-getoverlap")) {
            if (args.length == 4) {
                try {
                    System.out.println(ToolsforCMD.startruningSTR());
                    new getoverlap(args[1], args[2]).print(args[3]);
                } catch (IOException ex) {
                    Logger.getLogger(Console.class.getName()).log(Level.SEVERE, null, ex);
                }
            } else if (args.length == 6) {
                try {
                    System.out.println(ToolsforCMD.startruningSTR());
                    new getoverlap(args[1], args[2]).print(args[3], args[4], args[5]);
                } catch (IOException ex) {
                    Logger.getLogger(Console.class.getName()).log(Level.SEVERE, null, ex);
                }

            } else {
                System.out.println("args error!");
            }
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
        } else if (args[0].endsWith("-unigenedistribution")) {
            if (args.length == 3) {
                // System.out.println();
                if (args[1].equalsIgnoreCase("true") || args[1].equalsIgnoreCase("false")) {
                    System.out.println("You can also specify the summary bin of unigenelength by follow cmd \r\n\t\tCMD : java -jar DAtools.jar -unigenedistribution isunigene trinity.fasta min max step");
                    System.out.println(ToolsforCMD.startruningSTR());
                    new DrawDistributionPlot(Boolean.parseBoolean(args[1].toLowerCase()), args[2]);
                    TrinityProcess.calculationNnummer(args[2],false);
                }

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
            if (args.length == 1 || args[1].equals("-h")) {
                System.out.println("RNAseq Differential analysi with readscount by HTseq:   \r\n\t\tCMD \r\n\t\tCMD : java -jar DAtools.jar -RNAseqpipe <fastq1> <fastq2> <library:bowtie2 library> <gtf>\r\n");
                System.out.println("Extra paramters  \r\n\t\t-username\t user defined name in output\r\n");
                System.out.println(" \r\n\t\t-t\t htseq-count -t parameter:feature type (3rd column in GFF file) to be used, all features of other type are ignored (default, suitable for Ensembl GTF files: exon)\r\n");
                System.out.println(" \r\n\t\t-id\thtseq-count -t parameter:GFF attribute to be used as feature ID (default, suitable for Ensembl GTF files: gene_id)");
                System.out.println(" \r\n\t\t-s\thtseq-count -s parameter: Whether the data is from a strand-specific assay. Specify 'yes', 'no', or 'reverse' (default: yes). 'reverse' means 'yes' with reversed strand");

            } else {

                runTophat2andHtseq rt = new runTophat2andHtseq(args[3], args[4], args[1], args[2]);
                if (FunctionClass.getArgsParameter(args, "-username") != null) {
                    rt.setFilename(FunctionClass.getArgsParameter(args, "-username"));
                    System.out.println(rt.getFilename() + " is applied for rename outputfile ");
                }
                if (FunctionClass.getArgsParameter(args, "-t") != null) {
                    rt.htseqFeature = FunctionClass.getArgsParameter(args, "-t");
                    System.out.println("In gene feature file " + rt.htseqFeature + " is applied for reads counting");
                }
                if (FunctionClass.getArgsParameter(args, "-s") != null) {
                    rt.htseqFeature = FunctionClass.getArgsParameter(args, "-s");
                    System.out.println("Wth strand specific parameters " + rt.htseqFeature + " is applied for reads counting");
                }

                if (FunctionClass.getArgsParameter(args, "-id") != null) {
                    rt.htseqID = FunctionClass.getArgsParameter(args, "-id");
                    System.out.println("In gene feature file " + rt.htseqID + " is applied for reads counting");
                }
                rt.runTophat2HTseqGetCountfilePaired();
            }
        } else if (args[0].endsWith("-RNAseqDE")) {
            if (args.length == 1) {
                System.out.println("RNAseq Mappng/quantitifaction:   \r\n\t\tCMD : java -jar DAtools.jar -RNAseqDE -mode WR(orSC) condition1 condition2 outputfile\r\n");
                System.out.println("Extra paramters  \r\n\t\t-mode\t WR(without replicates),SC(standard comparison) or MF(Multi factors)\r\n");
                System.out.println(" \r\n\t\t-fpkm\t calculate fpkm by supply a gene length file\r\n");
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
        } else if (args[0].endsWith("-multi2matrix")) {
            if (args.length == 1) {
                System.out.println("RNAseq Mappng/quantitifaction:   \r\n\t\tCMD : java -jar DAtools.jar -multi2matrix dir suffix outfile [columnnumber]\r\n");

            } else if (args.length == 4) {
                new Multifile2matrix(args[1], args[2], args[3]);
            }else if (args.length == 5) {
                new Multifile2matrix(args[1], args[2], args[3],Integer.parseInt(args[4]));
            }  
            else {
                System.out.println("command error!");
            }
        }else if (args[0].endsWith("-Fasta")) {
            if (args.length == 1) {
                System.out.println("get fastalength file \r\n\t\tCMD : java -jar DAtools.jar -Fasta -mode getlength infile.fa outfile");
                System.out.println("get fastalength file \r\n\t\tCMD : java -jar DAtools.jar -Fasta -mode split infile.fa");
            } else if(FunctionClass.getArgsParameter(args, "-mode").equalsIgnoreCase("getlength")){
                getFastaLength.getfastaLength(args[3], args[4]);
            }else if(FunctionClass.getArgsParameter(args, "-mode").equalsIgnoreCase("split")){
                new SplitFasta2multifile(args[3]);
            }else {
                System.out.println("Please specified -mode parameters!");
            }
        } else if (args[0].endsWith("-exome")) {
            new ExomeSeqConsole(args);
        } else if (args[0].endsWith("-convertFormat")) {
            if (args.length == 1) {
                System.out.println("Statistic snp frequency in allsamples \r\n\t\tCMD : java -jar DAtools.jar -convertFormat -mode tab2excel inputfile");
//                System.out.println("Statistic snp frequency in allsamples \r\n\t\tCMD : java -jar DAtools.jar -convertFormat -mode  snpEfffre dir outfile [suffix]");
                System.out.println("Extra paramters  \r\n\t\t-username\t user defined name in output\r\n");
                System.out.println(" \r\n\t\t-sep\t seperator to parse your tab file\r\n");
            } else if(FunctionClass.getArgsParameter(args, "-mode").equalsIgnoreCase("tab2excel")){
                tab2excel tb=new tab2excel(args[3]);
                 if (FunctionClass.getArgsParameter(args, "-username") != null) {
                    tb.setOutput(FunctionClass.getArgsParameter(args, "-username"));
                    System.out.println(tb.getOutput() + " is applied for rename outputfile ");
                }
                if (FunctionClass.getArgsParameter(args, "-sep") != null) {
                   tb.setSeperator(FunctionClass.getArgsParameter(args, "-sep"));
                    System.out.println(tb.getSeperator() + " is applied for rename outputfile ");
                }
               tb.tab2excel();
                
            }else{
                System.out.println("Please specified -mode parameters!");
            }
        }else if (args[0].endsWith("-genelengthDis")) {
            if (args.length == 1) {
                System.out.println("Plot genelenthDistribution by R\r\n\t\tCMD : java -jar DAtools.jar -genelenthDis -mode plotlength fastafile [min max step] [options]");
                System.out.println("Conculate Ns of fasta file \r\n\t\tCMD : java -jar DAtools.jar -genelenthDis -mode ConN fastafile");
                System.out.println("Extra paramters  \r\n\t\t-username\t user defined name in output\r\n");
                System.out.println(" \r\n\t\t-min\t minimum length in axis(default 0)\r\n");
                System.out.println(" \r\n\t\t-max\t maxnum length in axis(default 3000)\r\n");
                System.out.println(" \r\n\t\t-step\t seperator to parse your tab file(default 300)\r\n");
            } else if(FunctionClass.getArgsParameter(args, "-mode").equalsIgnoreCase("plotlength")){
                DrawDistributionPlot dp=new DrawDistributionPlot(args[3]);
                if (FunctionClass.getArgsParameter(args, "-username") != null) {
                   dp.outname=FunctionClass.getArgsParameter(args, "-username");
                }
                if (FunctionClass.getArgsParameter(args, "-min") != null) {
                   dp.min=Integer.parseInt(FunctionClass.getArgsParameter(args, "-min"));
                }
                if (FunctionClass.getArgsParameter(args, "-max") != null) {
                   dp.max=Integer.parseInt(FunctionClass.getArgsParameter(args, "-max"));
                }
                if (FunctionClass.getArgsParameter(args, "-step") != null) {
                   dp.step=Integer.parseInt(FunctionClass.getArgsParameter(args, "-step"));
                }
               dp.process();
            }else if(FunctionClass.getArgsParameter(args, "-mode").equalsIgnoreCase("conN")){
                TrinityProcess.calculationNnummer(args[3],false);
            }
        }else if (args[0].endsWith("-kegg")) {
            if (args.length == 1) {
                System.out.println("Plot kegg blast result with mappfile\r\n\t\tCMD : java -jar DAtools.jar -kegg -mode plotmap kegg.log geneExressionFile keggmapdir outmapdir [options]");
                
            } else if(FunctionClass.getArgsParameter(args, "-mode").equalsIgnoreCase("plotmap")){
                KeggPlotProcess dp=new KeggPlotProcess(args[3],args[4],args[5],args[6]);
                
            }
        }else if (args[0].endsWith("-excel")) {
            if (args.length == 1) {
                System.out.println("Convert tab file into excel file \r\n\t\tCMD : java -jar DAtools.jar -excel -mode tab2excel infile");
                System.out.println("Merge Excelfile in one \r\n\t\tCMD : java -jar DAtools.jar -excel -mode merge inputdir outfile");
                System.out.println("Divid Excelfile into multiples\r\n\t\tCMD : java -jar DAtools.jar -excel -mode split inputfile outdir");
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
                System.out.println("Extract non human fastq items with blast output \r\n\t\tCMD : java -jar DAtools.jar -exome -mode ehuman inputFastqfile blastOutput outputfile");
                System.out.println("Remove overlap region in gtf \r\n\t\tCMD : java -jar DAtools.jar -exome -mode removeGTF inputGTFfile inputIntersectFile outfilename");
                
            } else if(FunctionClass.getArgsParameter(args, "-mode").equalsIgnoreCase("ehuman")){
                new GetNonHumanFastqFromBlastOut(args[3], args[4],args[5]);
            }else if(FunctionClass.getArgsParameter(args, "-mode").equalsIgnoreCase("removeGTF")){
                new RemoveIntersectProcess(args[3], args[4], args[5]);
            }
            
            else {
                System.out.println("Please specified -mode parameters!");
            }
        }else if (args[0].endsWith("-dose")) {
            if (args.length == 1 ||args[1].equalsIgnoreCase("-h")) {
                System.out.println("Convert gtf2lengthfile (Essemble gene only gtf)\r\n\t\tCMD : java -jar DAtools.jar -dose -mode gtf2length lengthfile gtffile outrpkmfile");
                System.out.println("Calculating rpkm with length &Counting file\r\n\t\tCMD : java -jar DAtools.jar -dose -mode crpkm lengthfile countingfile outrpkmfile");
                System.out.println("Calculating rpkm with gtf &Counting file\r\n\t\tCMD : java -jar DAtools.jar -dose -mode crpkm2 gtf countingfile outrpkmfile");
                System.out.println("Statistic crpkm outputfile  \r\n\t\tCMD : java -jar DAtools.jar -dose -mode static inrpkmfile");
                System.out.println("Statistic crpkm outputfile with *rpkm suffixed (dir) \r\n\t\tCMD : java -jar DAtools.jar -dose -mode staticDir inrpkmfile");
                System.out.println("Statistic crpkm outputfile with *rpkm suffixed (dir) \r\n\t\tCMD : java -jar DAtools.jar -dose -mode staticDirClass inrpkmfile mapfile");
                System.out.println("Calculating the sequence feature like GCcontent,hexmer entropy and rRNA similarity \r\n\t\tCMD : java -jar DAtools.jar -dose -mode seqFeature <inputfasta> <outfile> [-thread n]");
            }else if(FunctionClass.getArgsParameter(args, "-mode").equalsIgnoreCase("gtf2length")){
                new gtf2lengthfile(args[3], args[4]);
            } else if(FunctionClass.getArgsParameter(args, "-mode").equalsIgnoreCase("crpkm")){
                new computeRPKM(args[3], args[4],args[5]);
            }else if(FunctionClass.getArgsParameter(args, "-mode").equalsIgnoreCase("crpkm2")){
                new computeRPKM(args[3], args[4], args[5], false);
            } else if (FunctionClass.getArgsParameter(args, "-mode").equalsIgnoreCase("static")) {
                new StaticChrome(args[3]);
            }else if (FunctionClass.getArgsParameter(args, "-mode").equalsIgnoreCase("staticDir")) {
                File[] filelist = FilelistReader.getFileList(args[3], "rpkm");
                System.out.println("FileName\tNormal Median\t X Median" );
                for (int i = 0; i < filelist.length; i++) {

                    new StaticChrome(filelist[i].getAbsolutePath());
                }
            } else if (FunctionClass.getArgsParameter(args, "-mode").equalsIgnoreCase("staticDirClass")) {
                File[] filelist = FilelistReader.getFileList(args[3], "rpkm");
                System.out.println("FileName\tNormal Median\t X Median" );
                for (int i = 0; i < filelist.length; i++) {

                    new StaticChromeByClass(filelist[i].getAbsolutePath(),args[4]);
                }
            } else if (FunctionClass.getArgsParameter(args, "-mode").equalsIgnoreCase("seqFeature")) {
                System.out.println(ToolsforCMD.startruningSTR());
                if (FunctionClass.getArgsParameter(args, "-thread") != null) {
                   new CombineAnalysis(args[3],args[4],Integer.parseInt(FunctionClass.getArgsParameter(args, "-thread")));
                }else{
                new CombineAnalysis(args[3],args[4]);
                }
            } else {
                System.out.println("Please specified -mode parameters!");
            }
        } else if (args[0].endsWith("-bed")) {
            new BedProcessConsole(args);
        }else {
            System.out.println("command error!");
        }
        long end = System.nanoTime();
        System.out.println("Total running time is " + (end - start) * 10e-10 + "s");
    }
}
