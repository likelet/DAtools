/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ExomeSeqAnalysisPipe.mafProcess.maf2mutationMatrix;

import ExomeSeqAnalysisPipe.mafProcess.vepMAF2TCGAmaf.MAFby_Tool;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import pub.Tools;

/**
 * <p>
 * ParseVepMAF</p>
 * <p>
 * Created on 2016-1-20 16:22:45</p>
 * <p>
 * Author Email: zhaoqi3@mail2.sysu.edu.cn</p>
 *
 * @author ZHAO Qi
 * @date 2016-1-20 16:22:45
 * @version java 1.6.0
 * @version
 */
public class ParseVepMAF {

    public static ArrayList<MAFby_Tool> getMAFlist(String maffile) {
        ArrayList<MAFby_Tool> mflist = new ArrayList<MAFby_Tool>();
        BufferedReader br = null;
        try {
            br = new BufferedReader(new FileReader(new File(maffile)));
            String[] str;
            while (br.ready()) {
                String temstr = br.readLine();
                temstr = temstr.replace("\t\t", "\t.\t");
                temstr = Tools.replaceLast(temstr, "\t", "\t.");
//                temstr=temstr.replace("\t.\n", "\t\n");
                if (temstr.startsWith("#") || temstr.startsWith("Hugo_Symbol")) {
                } else {
                    str = temstr.split("\t");
//                System.out.println(str.length);
                    MAFby_Tool mt = new MAFby_Tool(str[0], str[1], str[2], str[3], str[4], str[5], str[6], str[7], str[8], str[9], str[10], str[11], str[12], str[13], str[14], str[15], str[16], str[17], str[18], str[19], str[20], str[21], str[22], str[23], str[24], str[25], str[26], str[27], str[28], str[29], str[30], str[31], str[32], str[33], str[34], str[35], str[36], str[37], str[38], str[39], str[40], str[41], str[42], str[43], str[44], str[45], str[46], str[47], str[48], str[49], str[50], str[51], str[52], str[53], str[54], str[55], str[56], str[57], str[58], str[59], str[60], str[61], str[62], str[63], str[64], str[65], str[66], str[67], str[68], str[69], str[70], str[71], str[72], str[73], str[74], str[75], str[76], str[77], str[78], str[79], str[80], str[81], str[82], str[83], str[84], str[85], str[86], str[87], str[88], str[89], str[90], str[91], str[92], str[93], str[94], str[95], str[96], str[97], str[98], str[99], str[100], str[101], str[102], str[103], str[104], str[105], str[106], str[107]);
                    mflist.add(mt);

                }
            }

            br.close();
        } catch (IOException ex) {
            Logger.getLogger(ParseVepMAF.class.getName()).log(Level.SEVERE, null, ex);
        }

        return mflist;
    }
}
