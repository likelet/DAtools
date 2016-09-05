package DoseCompensation.ResultStatistic;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.regex.Pattern;
import pub.txtReader;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 * <p>
 * StaticChromeByClass</p>
 * <p>
 * Created on 2016-1-6 16:43:32</p>
 * <p>
 * Author Email: zhaoqi3@mail2.sysu.edu.cn</p>
 *
 * @author ZHAO Qi
 * @date 2016-1-6 16:43:32
 * @version java 1.6.0
 * @version
 */
public class StaticChromeByClass {

    private HashMap<String, String> geneClassMap = new HashMap<String, String>();
    private ArrayList<ReadRPKMfile.RPKMterm> ParseRPKMlist;
    private double maxlimit = 100000000;//threshold for conculated result
    private double minlimit = 0;//threshold for conculated result

    public StaticChromeByClass(String rpkmfile, String mapfile) {
        try {
            geneClassMap = txtReader.getMap(mapfile);
            ParseRPKMlist = new ReadRPKMfile(rpkmfile).getParseRPKM();
        } catch (IOException ex) {
            Logger.getLogger(StaticChromeByClass.class.getName()).log(Level.SEVERE, null, ex);
        }
        this.process();
    }

    public void process() {
        ArrayList<ReadRPKMfile.RPKMterm> normallist_1 = new ArrayList<ReadRPKMfile.RPKMterm>();
        ArrayList<ReadRPKMfile.RPKMterm> normallist_2 = new ArrayList<ReadRPKMfile.RPKMterm>();
        ArrayList<ReadRPKMfile.RPKMterm> xlist_1 = new ArrayList<ReadRPKMfile.RPKMterm>();
        ArrayList<ReadRPKMfile.RPKMterm> xlist_2 = new ArrayList<ReadRPKMfile.RPKMterm>();
        String regex = "^chr[1-9].*";
//         System.out.println(ParseRPKMlist.size());
        for (int i = 0; i < ParseRPKMlist.size(); i++) {
            ReadRPKMfile.RPKMterm rpkmterm = ParseRPKMlist.get(i);
            if (rpkmterm.getRpkm() >= minlimit && rpkmterm.getRpkm() <= maxlimit) {
//               System.out.println(rpkmterm.getChr());
                if (rpkmterm.getChr().equalsIgnoreCase("chrX")) {
                    if (geneClassMap.get(rpkmterm.getChr()).equalsIgnoreCase("1")) {
                        xlist_1.add(rpkmterm);
                    } else {
                        xlist_2.add(rpkmterm);
                    }

                } else if (Pattern.matches(regex, rpkmterm.getChr())) {
                    if (geneClassMap.get(rpkmterm.getChr()).equalsIgnoreCase("1")) {
                        normallist_1.add(rpkmterm);
                    } else {
                        normallist_2.add(rpkmterm);
                    }
                }
            }
        }

        new StaticChrome(normallist_1, xlist_1);
        new StaticChrome(normallist_2, xlist_2);
    }
}
