/*
 * draw fasta lengh distribution
 */
package FastaProcess.FastaLengthDistribution;


import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import pub.FilelistReader;

/**
 *
 * @author ZHAO Qi
 * @date 2014-10-10 18:50:04
 * @version 1.7.0
 */
public class RscriptGenerator {
    private File Rscriptpath;
    private String plotpath;
    ArrayList<String> intervallist;
    ArrayList<Integer> countlist;
    String fastapath;
    int min=0;
    int max=3000;
    int step=300;

    public RscriptGenerator(String fastapath,String outpath) {
        this.intervallist = Lengthdistribution.getIntervalList(min, max, step);
        this.countlist = Lengthdistribution.findInterval(min, max, step, fastapath);
        System.out.println("Distribution summaried!\r\n ploting");
        this.fastapath = fastapath;
       this.plotpath=outpath;

        try {
            this.process(intervallist, countlist);
        } catch (IOException ex) {
            Logger.getLogger(RscriptGenerator.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

  

    public RscriptGenerator(String fastapath, int min, int max, int step,String outpath) {
        this.intervallist = Lengthdistribution.getIntervalList(min, max, step);
        System.out.println("intervallist finished!");
        this.countlist = Lengthdistribution.findInterval(min, max, step, fastapath);
        System.out.println("Distribution summaried!\r\n ploting");
        this.fastapath = fastapath;
        this.min = min;
        this.max = max;
        this.step = step;
         this.plotpath=outpath;
         File fastfile=new File(fastapath);
        try {
            this.process(intervallist, countlist);
        } catch (IOException ex) {
            Logger.getLogger(RscriptGenerator.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    
    
    
    public void process(ArrayList<String> intervallist, ArrayList<Integer> countlist) throws IOException{
       
        String str="";

        String intervalstr="";
        String integerstri="";
        for (int i = 0; i < intervallist.size(); i++) {
            intervalstr+="\""+intervallist.get(i)+"\",";
            integerstri+=countlist.get(i)+",";
        }
       intervalstr=intervalstr.substring(0, intervalstr.length()-1);
       integerstri=integerstri.substring(0, integerstri.length()-1);
        //System.out.println(intervalstr+"\r\n"+integerstri);
        Rscriptpath = File.createTempFile("tempR", ".R");
        Rscriptpath.deleteOnExit();
        FileWriter fw = new FileWriter(Rscriptpath);

        //System.out.println(intervalstr+"\r\n"+integerstri);
        try {

            String Rscriptdir = Rscriptpath.getParent();
            plotpath = plotpath + ".png";
            plotpath = plotpath.replace("\\", "/");
                //System.out.println(dirOut);
                str="setwd(\""+Rscriptdir+"\")\r\n";
                str+="library(\"ggplot2\")\r\n";
                
               
                str+= "dataset<-data.frame(count=c("+integerstri+"),label=c("+intervalstr+"))\r\n";
                
                str+= "p=ggplot(dataset,aes(x=label,y=count))\r\n";
                str+="black.bold.text <- element_text(face = \"bold\", color = \"black\")\r\n";
                str+="blue.bold.italic.12.text <- element_text(face = \"bold.italic\", color = \"blue\", size = 12)\r\n";
                 str += "png(\"" + plotpath + "\", type=\"cairo\",units=\"in\",width = 10, height = 10,pointsize=5.2,res=300)\r\n";
                //str+="bitmap(file= \"result.jpeg\",width=14,height=7,type = \"jpeg\", res = 300)\r\n";
                str+= "p+geom_bar(aes(fill = label),size=2,alpha=1)+labs(title = \"Transcript Distribution \")+theme_bw()+scale_x_discrete(limit = c("+intervalstr+"))"
                        //+ "+scale_fill_brewer(palette=\"Spectral\")"
                        + "+theme(axis.text.x = element_text(angle = 45, hjust = 1))"
                        + "+theme(legend.position=\"none\")"
                        + "+xlab(\"Length\")"
                        +"+ylab(\"Count\")"
                        + "+theme(title= black.bold.text,axis.title = black.bold.text)"
                        + "+theme(axis.text = blue.bold.italic.12.text)\r\n";
                str+= "dev.off()\r\n"; 
                fw.append(str);
                

                
            } catch (IOException ex) {
                Logger.getLogger(RscriptGenerator.class.getName()).log(Level.SEVERE, null, ex);
            } 
           
        
        fw.close();
    }

    public String getRscriptpath() {
        return Rscriptpath.getAbsolutePath();
    }

    public String getPlotpath() {
        return plotpath;
    }
    
    
    
    
    
    
    public static void main(String[] args) throws IOException {
         //ArrayList<String> list1=Lengthdistribution.getIntervalList(0, 3000, 300);
         //ArrayList<Integer> list2=Lengthdistribution.findInterval(0, 3000, 300, "F:\\mywork\\project\\玉瑾\\Trinity.fasta", true);
         new RscriptGenerator("F:\\mywork\\project\\yujing\\Trinity.fasta","F:\\mywork\\project\\yujing\\plot");
         
        
    }
}
