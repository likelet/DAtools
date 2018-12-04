/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package RNAseqPipeline;

import RNAseqPipeline.CountRPKMfromCount.getCountMatrixFromMap;
import RNAseqPipeline.DifferentialExpressionAnalysis.DEGeneAnalysis;
import RNAseqPipeline.DoseCompensation.RemoveIntersectRegionFromGTF.computeRPKM;
import java.io.IOException;
import pub.FunctionClass;
import pub.ToolsforCMD;

/**
 * <p>
 * FastQprocessConsole</p>
 * <p>
 * Created on 2016-1-12 15:29:13</p>
 * <p>
 * Author Email: zhaoqi3@mail2.sysu.edu.cn</p>
 *
 * @author ZHAO Qi
 * @date 2016-1-12 15:29:13
 * @version java 1.6.0
 * @version
 */
public class RNAseqConsole {

    public RNAseqConsole(String[] args) throws IOException {
        if (args.length == 1) {
            System.out.println("Counting reads at chromesome level:   \r\n\t\t"
                    + ToolsforCMD.print_ansi_GREEN("java -jar DAtools.jar -RNAseq -mode countC" )
                            + ToolsforCMD.print_ansi_CYAN( " <bamfile/samfile> <outfile> [strategy: (u)uniqueOnly,(r)RandomAssigned,(a)AllAssigned,(m)meanAssinged]" ) + "\r\n");//parameters
            System.out.println("Calculate RPKM using count & genelength file(single file):   \r\n\t\t"
                    + ToolsforCMD.print_ansi_GREEN("java -jar DAtools.jar -RNAseq -mode cRPKM" )
                            + ToolsforCMD.print_ansi_CYAN( " <Genecountfile> <Genelenghfile> <outfile>" ) + "\r\n");//parameters
            System.out.println("Calculate RPKM matrix using count & genelength file(multiple files):   \r\n\t\t"
                    + ToolsforCMD.print_ansi_GREEN("java -jar DAtools.jar -RNAseq -mode cRPKMm" )
                            + ToolsforCMD.print_ansi_CYAN( " <suffix> <Genelenghfile> <outfile>" ) + "\r\n");//parameters
            System.out.println("RNAseq Differential analysi with readscount by HTseq:   \r\n\t\tCMD \r\n\t\t"
                    + ToolsforCMD.print_ansi_GREEN( "java -jar DAtools.jar -RNAseq -mode pipe" )
                    + ToolsforCMD.print_ansi_CYAN( " <fastq1> <fastq2> <library:bowtie2 library> <gtf>\r\n" ));
            System.out.println("Differential Expression analysis with Poisson Test:   \r\n\t\tCMD \r\n\t\t"
                    + ToolsforCMD.print_ansi_GREEN( "java -jar DAtools.jar -RNAseq -mode poissonDE" )
                    + ToolsforCMD.print_ansi_CYAN( " <countfile1> <countfile2> <outfile> \r\n" )
                    + ToolsforCMD.print_ansi_CYAN( "parameters -gtf" ));
            System.out.println("Extra paramters for pipe  \r\n\t\t-username\t user defined name in output\r\n");
            System.out.println("\r\n\t\t-t\t htseq-count -t parameter:feature type (3rd column in GFF file) to be used, all features of other type are ignored (default, suitable for Ensembl GTF files: exon)\r\n");
            System.out.println("\r\n\t\t-id\t htseq-count -t parameter:GFF attribute to be used as feature ID (default, adapted for Ensembl GTF files: gene_id)");
            System.out.println("\r\n\t\t-s\t htseq-count -s parameter: Whether the data is from a strand-specific assay. Specify 'yes', 'no', or 'reverse' (default: yes). 'reverse' means 'yes' with reversed strand");

        } else if (FunctionClass.getArgsParameter(args, "-mode").equalsIgnoreCase("cRPKM")) {
            if (args.length == 6) {
                System.out.println(ToolsforCMD.startruningSTR());
                new computeRPKM(args[3], args[4], args[5]);
            } else {
                System.out.println("args error!");
            }
        } else if (FunctionClass.getArgsParameter(args, "-mode").equalsIgnoreCase("cRPKMm")) {
            if (args.length == 6) {
                System.out.println(ToolsforCMD.startruningSTR());
                getCountMatrixFromMap gmm = new getCountMatrixFromMap(args[3], args[4], args[5]);
                gmm.process();
            } else {
                System.out.println("args error!");
            }

        } else if (FunctionClass.getArgsParameter(args, "-mode").equalsIgnoreCase("pipe")) {
            runTophat2andHtseq rt = new runTophat2andHtseq(args[4], args[5], args[2], args[3]);
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

        } else if (FunctionClass.getArgsParameter(args, "-mode").equalsIgnoreCase("poissonDE")) {
            DEGeneAnalysis deg = new DEGeneAnalysis(args[3], args[4],args[5]);
            if (FunctionClass.isContainParameter(args, "-gtf")) {
                      deg.setGtffile(FunctionClass.getArgsParameter(args, "-gtf"));
                }
             deg.getDEgenePvalue();
             deg.getOutputFile();
        }else {
            System.out.println("Please specified -mode parameters!");
        }
    }

}
