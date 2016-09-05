/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package speedseqvcf;

/**
 *
 * @author zhengyueyuan
 */
public class SpeedseqVcf {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        if (args[0].equals("-h")||args[0].equals("-help")) {
           System.out.println("java -jar SpeedseqVcf.jar -VCFFileReader InputFile OutputFile");            
        }else if(args[0].equals("-VCFFileReader")){
             VCFFileReader vcf =new VCFFileReader();
             vcf.FileReader(args[1], args[2], args[3], args[4]);
        }
    }
    
}
