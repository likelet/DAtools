/*
 * ByBedtools coverage output plot
 */
package DrawUnigeneDistribution;


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
    private String Rscriptpath;
    private String plotpath;
    ArrayList<String> intervallist;
    ArrayList<Integer> countlist;
    boolean isunigene;//判断是否计算所有转录本还是只计算基因内部最长转录本
    String fastapath;
    int min=0;
    int max=3000;
    int step=300;
    private String outname;

    public RscriptGenerator(boolean isunigene, String fastapath,String outname) {
        this.outname=outname;
        this.intervallist = Lengthdistribution.getIntervalList(min, max, step);
        this.countlist = Lengthdistribution.findInterval(min, max, step, fastapath, isunigene);
        System.out.println("Distribution summaried!\r\n ploting");
        this.isunigene = isunigene;
        this.fastapath = fastapath;
        
            File fastfile=new File(fastapath);
         System.out.println(Rscriptpath);
        try {
            this.process(intervallist, countlist);
        } catch (IOException ex) {
            Logger.getLogger(RscriptGenerator.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

  

    public RscriptGenerator(boolean isunigene, String fastapath, int min, int max, int step,String outname) {
        this.outname=outname;
        this.intervallist = Lengthdistribution.getIntervalList(min, max, step);
        this.countlist = Lengthdistribution.findInterval(min, max, step, fastapath, isunigene);
        System.out.println("Distribution summaried!\r\n ploting");
        this.isunigene = isunigene;
        this.fastapath = fastapath;
        this.min = min;
        this.max = max;
        this.step = step;
        
         File fastfile=new File(fastapath);
        try {
            this.process(intervallist, countlist);
        } catch (IOException ex) {
            Logger.getLogger(RscriptGenerator.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    
    
    
    public void process(ArrayList<String> intervallist, ArrayList<Integer> countlist) throws IOException{
       
        String str="";
       
        File Rscriptpathfile=File.createTempFile("tempR", ".R");
        this.Rscriptpath=Rscriptpathfile.getAbsolutePath();
        Rscriptpathfile.deleteOnExit();
        FileWriter fw = new FileWriter(Rscriptpathfile);
        String intervalstr="";
        String integerstri="";
        for (int i = 0; i < intervallist.size(); i++) {
            intervalstr+="\""+intervallist.get(i)+"\",";
            integerstri+=countlist.get(i)+",";
        }
       intervalstr=intervalstr.substring(0, intervalstr.length()-1);
       integerstri=integerstri.substring(0, integerstri.length()-1);
        //System.out.println(intervalstr+"\r\n"+integerstri);
            
            try {
                
                //System.out.println(dirOut);
//                str="setwd(\""+Rscriptdir+"\")\r\n";
                str+="library(\"ggplot2\")\r\n";
                
               
                str+= "dataset<-data.frame(count=c("+integerstri+"),label=c("+intervalstr+"))\r\n";
                
                str+= "p=ggplot(dataset,aes(x=label,y=count))\r\n";
                str+="black.bold.text <- element_text(face = \"bold\", color = \"black\")\r\n";
                str+="blue.bold.italic.12.text <- element_text(face = \"bold.italic\", color = \"blue\", size = 12)\r\n";
                str+= "jpeg(file= \""+this.outname+".jpeg\",width = 14, height = 8, units = 'in',res = 300)\r\n";
                //str+="bitmap(file= \"result.jpeg\",width=14,height=7,type = \"jpeg\", res = 300)\r\n";
                str+= "p+geom_bar(aes(fill = label),size=2,alpha=1,stat=\"identity\")+labs(title = \"Transcript Distribution \")+theme_bw()+scale_x_discrete(limit = c("+intervalstr+"))"
                        //+ "+scale_fill_brewer(palette=\"Spectral\")"
                        + "+theme(axis.text.x = element_text(angle = 45, hjust = 1))"
                        + "+theme(legend.position=\"none\")"
                        + "+xlab(\"Length\")"
                        +"+ylab(\"Count\")"
                        + "+theme(title= black.bold.text,axis.title = black.bold.text)"
                        + "+theme(axis.text = blue.bold.italic.12.text)\r\n";
                str+= "dev.off()\r\n"; 
                str+= "postscript(file= \""+this.outname+".eps\")\r\n";
                //str+="bitmap(file= \"result.jpeg\",width=14,height=7,type = \"jpeg\", res = 300)\r\n";
                str+= "p+geom_bar(aes(fill = label),size=2,alpha=1,stat=\"identity\")+labs(title = \"Transcript Distribution \")+theme_bw()+scale_x_discrete(limit = c("+intervalstr+"))"
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
        return Rscriptpath;
    }

    public String getPlotpath() {
        return plotpath;
    }
    
    
    
    
    
    
    public static void main(String[] args) throws IOException {
         //ArrayList<String> list1=Lengthdistribution.getIntervalList(0, 3000, 300);
         //ArrayList<Integer> list2=Lengthdistribution.findInterval(0, 3000, 300, "F:\\mywork\\project\\玉瑾\\Trinity.fasta", true);
         new RscriptGenerator(true,"F:\\mywork\\project\\yujing\\Trinity.fasta","result");
         
        
    }
}
