/*
 * unique bed items using start and end information
 I also consitdered the score information which indicated the copy number of reads during sequence level deduplication stop
 */
package BedProcess.Unibed;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.HashMap;
import java.util.Iterator;
import pub.BED;

/**
 *
 * @author Zinky
 */
public class uniqueBed {
    private String  inputfile;
    private String  outfile;

    public uniqueBed(String inputfile, String outfile) {
        this.inputfile = inputfile;
        this.outfile = outfile;
        this.process();
    }
     public uniqueBed(String inputfile) {
        this.inputfile = inputfile;
        this.outfile = inputfile+"-remove.bed";
        this.process();
    }
    
    public void process(){
        try {
            String  tempstr;
            int totalcount=0;
            int removecount=0;
            BufferedReader br = new BufferedReader(new java.io.FileReader(new File(inputfile)));
            HashMap<String,BED> datamap=new HashMap<String,BED>();
            
            while (br.ready()) {                
                totalcount++;
                String[] dataStr = br.readLine().trim().split("\t");
                BED bd=new BED(dataStr[0],Integer.parseInt(dataStr[1]),Integer.parseInt(dataStr[2]),dataStr[3],Double.parseDouble(dataStr[4]),dataStr[5]);
                tempstr=dataStr[0]+dataStr[1]+dataStr[2]+dataStr[5];
                if (datamap.keySet().contains(tempstr)) {
                    BED bd2 = datamap.get(tempstr);
                    if (bd2.getScore() > bd.getScore()) {
                        datamap.put(tempstr, bd2);
                    }
                    removecount++;
                } else {
                    datamap.put(tempstr, bd);
                }
            }
            System.out.println("The number of total bed items is " +totalcount);
            System.out.println("The number of removed items is "+removecount);
            System.out.println("writing....");
            FileWriter fw = new FileWriter(outfile);
            for (Iterator it = datamap.keySet().iterator(); it.hasNext();) {
                String  keystr = (String) it.next();
                fw.append(datamap.get(keystr).printScore()+"\r\n");
            }
            
            fw.flush();
            fw.close();
        } catch (IOException ex) {
        }        
    }
}
