/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package RNAseqPipeline.CufflinksOutputProcess;

/**
 *
 * @author Yueyuan
 */
public class RNAseqCountAndExpressionStatic {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        
        
        new CufflinksOutFileReader("F:\\resouces\\projects\\ruibo\\longyan\\genes.read_group_tracking","F:\\resouces\\projects\\ruibo\\longyan\\Expression.xlsx");
//        if(args[0].equals("-h")){
//            System.out.println("java -jar RNAseqCountAndExpressionStatic.jar -r inputfile outputfile");
//        }else if(args[0].equals("-r")){
//    CufflinksOutFileReaderw FileReader(args[1],args[2]);
//        }else{
//            System.out.println("Input format is wrong, please try to use <java -jar RNAseqCountAndExpressionStatic.jar -h> for help");
//        }
        
    }
}
