/*
 * ByBedtools coverage output plot
 */
package BatchDrawCoverageByR;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import pub.FilelistReader;

/**
 *
 * @author ZHAO Qi
 * @date 2014-3-7 18:50:04
 * @version 1.6.0
 */
public class RscriptGenerator {
    public void RscriptGenerator(String dirIn, String dirOut) throws IOException{
        File[] coveragelist=FilelistReader.getFileList(dirIn);
        String str="";
        FileWriter fw = null;
        for (int i = 0; i < coveragelist.length; i++) {
            
            try {
                File f=coveragelist[i];
                String s=f.getName().split("\\.")[0];
                fw = new FileWriter(dirOut+"\\"+s+".R");
                dirOut=dirOut.replace("\\", "/");
                //System.out.println(dirOut);
                str="setwd(\""+dirOut+"\")\r\n";
                str+="library(\"ggplot2\")\r\n";
                str+= "data<-read.table(\""+f.getAbsolutePath().replace("\\", "/") +"\",header=F)\r\n";
                str+= "colnames(data)<-c(\"Chr\",\"Coverage\",\"Positions\",\"ChrLength\",\"Percentage\")\r\n";
                str+= "p=ggplot(data,aes(x=Coverage,y=Positions))\r\n";
                str+= "jpeg(file= \""+dirOut+"/"+s+".jpeg\", height = 800, width =800 )\r\n";
                str+= "p+geom_line(aes(colour = Positions),size=2,alpha=1)+labs(title = \"Coverage Distribution of Sample "+s+"\")+geom_vline(xintercept=50,color=\"red\",linetype = \"longdash\")\r\n";
                str+= "dev.off()\r\n"; 
                fw.append(str);
                
                fw.flush();
                
            } catch (IOException ex) {
                Logger.getLogger(RscriptGenerator.class.getName()).log(Level.SEVERE, null, ex);
            } 
           
        }
        fw.close();
    }
    
    public static void main(String[] args) throws IOException {
        new RscriptGenerator().RscriptGenerator("F:\\mywork\\project\\Peng\\coverage", "F:\\mywork\\project\\Peng\\coverageR");
    }
}
