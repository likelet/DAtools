/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package Algorithm.ssGSEA;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import org.apache.commons.math3.stat.Frequency;

/**
 * <p>ssGSEA</p>
 * <p>Created on 2015-10-17 11:22:41</p>
 * <p>Author Email: zhaoqi3@mail2.sysu.edu.cn</p>
 * @author ZHAO Qi
 * @date 2015-10-17 11:22:41
 * @version java 1.6.0
 * @version 
 */
public class ssGSEA {

    private HashSet<String> signatureList = new HashSet<String>();
    private ArrayList<GeneDataTerm> geneTermlist;
    private ArrayList<String> genelist ;
    private double exponent = 1;
    private int permutation=1000;
    private double orginES=1;
    private double pvalue=1;
    private String genesetname="";
    
    
     double sumhitFC = 0;
        int Nhit=0;
        int Ntotal;
        final double kmiss;
    
    private final HashMap<String,String> hitmap=new HashMap<String,String>();
    

    public ssGSEA(String geneset, String Studyfile) {
        DataSetParse dp = new  DataSetParse(Studyfile);
        geneTermlist = dp.getDatalist();
        genelist=dp.getGenelist();
        BufferedReader br = null;
        String str[]=geneset.split("\t");
        genesetname =str[0];
        for (int i = 2; i < str.length; i++) {
             signatureList.add(str[i]);
        }
        
        ArrayList<GeneDataTerm> hitTermlist = new ArrayList<GeneDataTerm>();
       
        for (int i = 0; i < genelist.size(); i++) {
            if (signatureList.contains(genelist.get(i))) {
                Nhit++;
                sumhitFC += geneTermlist.get(i).getFoldChange();
                hitmap.put(genelist.get(i), "TRUE");
            } else {
                hitmap.put(genelist.get(i), "FALSE");
            }
        }

        Ntotal = geneTermlist.size();

        kmiss=(double)(1 / (Ntotal - Nhit));
        
        
        orginES=getSetES( geneTermlist);;
        Frequency f=new Frequency();
        for (int i = 0; i < permutation; i++) {
            shuffleGeneDataTermlist(geneTermlist);
//            System.out.print(geneTermlist.get(1).getGeneName());
            
            f.addValue(getSetES(geneTermlist));
//            System.out.println(getSetES(geneTermlist));
        }
        pvalue=(double)(100-f.getCumFreq(orginES))/100;
        
        
    }

    

    //获取某个基因的ES
    private double getSetES(ArrayList<GeneDataTerm> geneDatatermlist) {
        Collections.sort(geneDatatermlist, new SetdataCompare());
       double tempvalue=0;
       double tampes=0;
       
       double maxDivES=0;
       int maxDivESindex=0;
        for (int i = 0; i < geneDatatermlist.size(); i++) {
            //hit value
            String genename=geneDatatermlist.get(i).getGeneName();
//            System.out.println(genename+"\t"+hitmap.get(genename));
//            System.out.println(genename);
            if(hitmap.get(genename).equals("TRUE")){
                tempvalue=geneDatatermlist.get(i).getFoldChange()/sumhitFC;
//                System.out.println(tempvalue);
                tampes+=tempvalue;
                geneDatatermlist.get(i).setValue(tempvalue);
                geneDatatermlist.get(i).setIshit(true);
            }else{//miss value
                geneDatatermlist.get(i).setValue(kmiss);
                tampes+=kmiss;
            }
            geneDatatermlist.get(i).setESscore(tempvalue);
            if(Math.abs(tempvalue)>maxDivES){
               maxDivES= Math.abs(tempvalue);
               maxDivESindex=i;
            }
        }
        return geneDatatermlist.get(maxDivESindex).getESscore();
    }

    

   private void shuffleGeneDataTermlist (ArrayList<GeneDataTerm> geneDatatermlist){
       
      Collections.shuffle(genelist);
      for (int i = 0; i < geneDatatermlist.size(); i++) {
            geneDatatermlist.get(i).setGeneName(genelist.get(i));
        }
   }

    @Override
    public String toString() {
        return "genesetname= " + genesetname + "\torginES= " + orginES + "\tpvalue= " + pvalue;
    }
    
   
   
    public static void main(String[] args) throws FileNotFoundException, IOException {
        String genesetstr="CDK6_Q00534	P46379_1081	P46379_964	P46379_113	P46379_973	P46379_1117	P19338_145	P19338_67	Q9H4G0_544	Q9H4G0_678	Q9H4G0_686	Q9H4G0_546	Q9H4G0_784	Q9H4G0_378	Q9H4G0_475	P54259_168	P54259_632	P54259_661	Q01130_212	Q01130_189	Q01130_191	Q15149_125	Q15149_720	Q15149_1435	Q15149_4396	Q15149_4626	Q9P2M7_159	Q05397_910	Q8N163_569	Q8N163_675	Q8NEZ4_4267	Q8NEZ4_1493	Q8NEZ4_2811	P21333_1084	P21333_2599	P21333_1630	P21333_1459	P21333_2327	Q14207_1151	P45985_90	Q7Z5K2_221	Q7Z5K2_226	O75363_314	O15173_90	Q92890_247	Q9NQX3_188	Q9NQX3_198	Q9NQX3_194	P06400_356	P06400_249	P06400_821	P06400_826	P06400_807	P06400_373	P06400_795	P06400_788	Q03164_1837	Q03164_2121	Q03164_2098	Q03164_3527	Q14980_77	Q14980_1757	Q14980_169	Q14980_2000	Q4G0J3_258	Q4G0J3_261	Q4G0J3_300	P30622_204	P18754_11	O95817_386	O95817_377	O95817_285	O95817_289	O95817_406	O95817_173	P35658_433	P35658_678	P35658_437	P35658_940	P35658_657	P40189_667	P40189_670	O95359_2317	O95359_2512	O95359_2359	Q9NZT2_537	Q9NZT2_378	Q9NZT2_315	Q99638_328	Q99638_277	Q01082_2102	Q01082_2165	Q01082_2161	Q01082_2169	Q01082_2341	P52701_227	P52701_137	P52701_830	P98175_723	P98175_718	P98175_89	Q8WX92_557	P16989_38	Q9H7D0_1756	Q9H7L9_237	Q9H7L9_234	O00750_155	Q5XPI4_675	P17029_208	P17029_13	O75362_662	O75362_648	O75362_407	O43719_453	Q96S94_330	P05412_63	P05412_73	P46821_832	P46821_1501	P46821_1265	P46821_1779	P46821_1793	P46821_1797	O00566_163	Q9Y618_149	Q9Y618_152	Q9Y618_956	Q9Y618_2234	Q9Y618_2065	Q9Y618_2069	Q9Y618_2269	Q9Y618_2424	Q9Y618_1487	Q9Y618_939	Q9UBS0_24	Q92466_26	Q9H307_417	Q9H307_443	Q9H307_347	Q9H307_413	Q13263_541	Q13263_752	Q13263_19	Q13263_50	P11137_1782	P53999_17	P53999_19	P10636_498	Q12774_445	Q12774_450	Q12774_11	Q12774_184	Q9UK58_67	Q9UK58_352	Q9UK58_445	Q9UK58_335	Q7KZ85_1523	Q14004_871	Q14004_400	Q14004_1246	Q14004_317	Q14004_383	Q14004_439	Q14004_525	O43765_77	O43765_81	Q96T60_118	Q14008_832	O14920_672	Q08211_87	Q9BX63_113	Q9BX63_1032	Q9BX63_206	Q9BX63_930	Q9BX63_918	O00204_348	O00204_350	Q13330_449	Q13330_522	Q13330_564	Q96EP0_437	O95365_526	O95365_549	Q8TAQ2_548	P25205_722	P25205_672	P25205_674	O43303_372	Q9Y2W1_320	Q9Y2W1_248	Q9Y2W1_253	Q9Y2W1_243	Q9Y2W1_315	Q9Y2W1_682	Q9Y2W1_941	Q9Y2W1_698	P24928_1892	P24928_1920	P24928_1875	P24928_1913	P24928_1910	P24928_1917	P24928_1882	P24928_1878	Q9P2K5_17	P42858_1199	P42858_432	P15408_200	P15408_230	P15408_120	Q5TGY3_1399	Q5TGY3_1403	P42224_727	Q96ST2_235	Q96ST2_248	Q96ST2_398	Q96ST2_400	Q96ST2_287	Q96ST2_438	Q96ST2_440	P21127_752	Q8IXJ6_366	Q8IXJ6_23	P35222_191	Q9NYV4_893	Q9NYV4_692	Q9NYV4_345	Q9NYV4_276	Q9NYV4_423	Q9NYV4_1244	O00267_1034	P26045_425	P17535_255	P17535_259	P17535_90	Q9NRY4_975	Q9NRY4_970	P10644_83	Q15648_1032	P31321_83	P13051_23	P13051_60	P13051_64	Q7L2J0_60	Q7L2J0_69	Q7L2J0_254	Q7L2J0_213	Q12893_329	P04049_260	P04049_234	P84022_8	Q9HAW4_225	Q9HAW4_810	Q9HAW4_1289	Q9NQC3_15	Q96PK6_206	Q96PK6_582	O95292_206	P18858_183	P18858_76	P18858_195	P18858_51	P18887_453	P18887_447	P18887_266	Q16512_916	Q16512_562	P14618_37	P54727_160	Q9BTK6_237	P38432_303	Q2M2I8_620	Q2M2I8_624	Q2M2I8_606	Q13435_362	Q13435_309	Q13435_780	P06748_10	P06748_243	P06748_70	Q08170_458	Q08170_460	Q15131_196	Q8TDY2_647	Q9P2D1_2559	Q9P2D1_2956	O94913_1530	O94913_777	O94913_1493	P07900_231	Q9UQB3_276	Q93074_635	Q13469_236	P23193_100	P08621_226	P19838_907	Q96IF1_119	Q7Z3B3_991	P29966_101	P29966_150	P29966_118	P29966_27	P29966_145	O75448_873	Q53ET0_433	Q96MH2_29	Q96MH2_76	Q15020_10	Q9UHB7_814	Q9UHB7_487	Q9UHB7_703	Q9UHB7_1058	Q9UHB7_694	Q9UHB7_180	P54253_88	P22314_835	O14497_1755	O14497_363	O14497_696	O75962_2455	P49736_108	P49736_27	P49736_41	P49736_13	Q9UQR1_412	P28715_526	P28715_384	Q9Y6A5_434	Q9Y6A5_250	Q9Y6A5_177	Q9Y6A5_71	Q00839_271	Q14204_4368	Q99496_41	Q9ULH0_1555	O00429_616	Q07955_199	P04792_65	P10809_70	Q9UJU6_269	Q9UJU6_283	Q8N122_877	Q8N122_863	Q9Y4H2_391	Q14241_542	Q9NYB9_183	P00558_203	P25490_247	P62136_320	P36507_295	Q8TEK3_834	Q8TEK3_1104	O95402_447	Q92797_1171	Q92797_1175	Q92797_1243	Q92797_1257	Q92797_1259	P51858_165	Q99590_771	Q99590_776	Q99590_796	Q99590_802	Q99590_608	Q99590_405	P10244_266	Q07960_51	Q15545_201	Q14669_312	Q14669_1016	P51608_80	P51608_229	P46013_2708	P46013_2588	P46013_584	P46013_357	P46013_579	P46013_1983	P46013_1131	P46013_2223	P46013_1815	P46013_1253	P46013_1376	P46013_1861	P46013_2466	P46013_543	P46013_308	P46013_827	P46013_2828	P46013_761	P46013_1679	Q02078_408	P31751_451	Q13042_560	Q03252_14	Q03252_17	P35606_859	Q9UNE7_19	Q9UNE7_23	Q13247_303	Q86YC2_660	Q86YC2_387	Q13625_698	Q5FBB7_256	P24941_160	Q9Y5B0_872	Q53EL6_94	Q02086_78	P26368_79	P43243_598	P43243_604	Q15361_65	Q9UQ88_583	O15164_811	P67809_174	P67809_165	O43521_77	P62316_12	Q12778_329	Q15047_1066	P18031_352	O95243_428	Q13561_198	P40763_727	P08238_226	P39880_1270	O95997_165	P34932_780	P23771_162	O60508_43	Q6NYC8_224	P35251_69	Q9BQE9_122	Q9UJX2_588	Q71F56_1081	Q71F56_923	Q68E01_502	Q96HC4_111	Q96HC4_360	P52292_62	P46019_1044	Q9NZN8_165	P55199_442	Q96CB8_128	O60885_1117	P35568_348	P35568_453	P35568_636	P28749_640	P28749_988	O43524_12	P20700_20	P20700_575	P20700_23	P20700_391	P20700_393	P17706_304	Q14498_136	Q14498_97	P31749_124	Q9NZN5_637	Q9NVR2_231	Q8WYQ5_377	Q8WYQ5_95	Q9UBU7_359	Q9UPY3_1160	P35548_91	A5YM69_184	O00443_259	Q15366_173	Q15366_189	O75909_9	P49915_332	O60934_432	A0JLT2_226	Q15796_8	Q13541_37	P34897_266	Q96Q42_492	P55196_1779	P15923_379	P47736_484	P12956_455	Q15059_263	O15083_187	O94763_372	Q9NVD7_8	Q9NVD7_14	P06401_20	P06401_162	Q8NG31_32	P35659_301	P35659_303	Q15233_450	O14980_391	Q16181_334	Q16555_509	P51816_790	Q06265_306	Q9H0H5_580	Q15717_202	Q8WWY3_455	Q8WVV4_123	P25440_633	Q6FI81_183	Q6NZI2_366	P01106_58	P01106_62	Q9NVI1_407	O15297_40";
        BufferedReader br = new BufferedReader(new java.io.FileReader(new File("E:\\ucn_phos_mpep.txt.elm.ST.igps.txt.gmt")));
        
        while (br.ready()) {            
            ssGSEA sg=new ssGSEA(br.readLine(),"E:\\ucn_data_reverse.txt");
        System.out.println(sg.toString());
            
        }
        
    }
}
