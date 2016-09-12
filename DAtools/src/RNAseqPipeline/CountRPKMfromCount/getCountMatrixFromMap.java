/*
 * Process series of count file into readscount matrix and RPKM matrix in excel format
 */
package RNAseqPipeline.CountRPKMfromCount;


import RNAseqPipeline.GeneLengthFileReader;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.IndexedColors;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import pub.FilelistReader;
import pub.Tools;

/**
 *
 * @author Zinky
 */
public class getCountMatrixFromMap {

    private HashMap<String, Integer> genelengthmap;
    private String suffix;
    public  String Dir = "./";
    private String outfile = "Result.xlsx";

    private ArrayList<String> filelist;
    ArrayList<HashMap<String, Integer>> countmaplist = new ArrayList<HashMap<String, Integer>>();
    ArrayList<HashMap<String, Double>> rpkmmaplist = new ArrayList<HashMap<String, Double>>();
    private HashSet<String> geneset = new HashSet<String>();

    public int thread = 1;

    public getCountMatrixFromMap(String lengthfile, String suffix, String outputfile) {
        try {
            this.genelengthmap = GeneLengthFileReader.getgeneLengthMap(lengthfile);
        } catch (IOException ex) {
            System.out.println(lengthfile + " IO error");
        }
        this.suffix = suffix;
        this.outfile = outputfile + ".xlsx";
    }

    public void process() throws FileNotFoundException, IOException {
        filelist = FilelistReader.getFileArrayList(Dir, suffix);
        HashMap<String, ArrayList<Integer>> readsCountMap = new HashMap();
        HashMap<String, ArrayList<Double>> rpkmMap = new HashMap();

        try {
            // run paralla
            ExecutorService pool = Executors.newFixedThreadPool(thread);//creat a new thread pool
            int size = filelist.size() / thread + 1;
            System.out.println(size);
            for (int i = 0; i < thread; i++) {
                
                //run extract thread
                if ((i + 1) * size > filelist.size()) {
                    calculateRPKM calculatedR = new calculateRPKM(i * size, filelist.size());
                    pool.submit(calculatedR);
                    System.out.println("2");
                    break;
                } else {
                    calculateRPKM calculatedR = new calculateRPKM(i * size, (i + 1) * size);
                    System.out.println(i * size + "\t" + (i + 1) * size);
                    pool.submit(calculatedR);
                    System.out.println("1");
                }

            }
            pool.shutdown();
            pool.awaitTermination(Long.MAX_VALUE, TimeUnit.DAYS);
            // write out 
        } catch (InterruptedException ex) {
            Logger.getLogger(calculateRPKM.class.getName()).log(Level.SEVERE, null, ex);
        }

        //countmap 
        for (Iterator it = geneset.iterator(); it.hasNext();) {
            String tempstr = (String) it.next();
            ArrayList<Integer> tempset = new ArrayList<Integer>();
            for (int i = 0; i < countmaplist.size(); i++) {
                tempset.add(countmaplist.get(i).get(tempstr));
            }
            readsCountMap.put(tempstr, tempset);

        }

        //rpkmmap 
        for (Iterator it = geneset.iterator(); it.hasNext();) {
            String tempstr1 = (String) it.next();
            ArrayList<Double> tempset1 = new ArrayList<Double>();
            for (int i = 0; i < rpkmmaplist.size(); i++) {
                tempset1.add(rpkmmaplist.get(i).get(tempstr1));
            }
            rpkmMap.put(tempstr1, tempset1);

        }

        //write out excel file
        Workbook wb = new XSSFWorkbook();
        //header style
        CellStyle style = wb.createCellStyle();
        style.setFillForegroundColor(IndexedColors.ORANGE.getIndex());
        style.setFillPattern(CellStyle.SOLID_FOREGROUND);

        //Readcount sheet
        Sheet sheet1 = wb.createSheet("ReadscountMatrix ");
        //Setting header
        ArrayList<String> namelist = new ArrayList<String>();
        namelist.add("Gene");
        for (int i = 0; i < filelist.size(); i++) {
            namelist.add(filelist.get(i));
        }
        Row row1 = sheet1.createRow((short) 0);
        for (int i = 0; i < namelist.size(); i++) {
            String colTitle = namelist.get(i);
            Cell cell1 = row1.createCell(i);
            cell1.setCellValue(colTitle);
            cell1.setCellStyle(style);
        }
        //setting values
        int rowIndex = 1;
        for (Iterator it = readsCountMap.keySet().iterator(); it.hasNext();) {
            String gene = (String) it.next();
            Row row = sheet1.createRow(filelist.size());
            ArrayList<Integer> countlist = readsCountMap.get(gene);
//                Cell cell = row.createCell((short) 0);
            row.createCell(0).setCellValue(gene);
            for (int i = 0; i < countlist.size(); i++) {
                row.createCell(i + 1).setCellValue(countlist.get(i));
            }
            rowIndex++;
        }

        //Rpkm sheet
        //Readcount sheet
        Sheet sheet2 = wb.createSheet("RpkmMatrix ");
        //Setting header
        Row row2 = sheet2.createRow((short) 0);
        for (int i = 0; i < namelist.size(); i++) {
            String colTitle = namelist.get(i);
            Cell cell1 = row2.createCell(i);
            cell1.setCellValue(colTitle);
            cell1.setCellStyle(style);
        }
        //setting values
        rowIndex = 1;
        for (Iterator it = rpkmMap.keySet().iterator(); it.hasNext();) {
            String gene = (String) it.next();
            Row row = sheet1.createRow(filelist.size());
            ArrayList<Double> rpkmlist = rpkmMap.get(gene);
            row.createCell(0).setCellValue(gene);
            for (int i = 0; i < rpkmlist.size(); i++) {
                row.createCell(i + 1).setCellValue(rpkmlist.get(i));
            }
            rowIndex++;
        }

        //Write Out
        FileOutputStream fileOut = new FileOutputStream(outfile);
        wb.write(fileOut);
        fileOut.close();
        System.out.println("Excel verson of output is generated and located in " + outfile);

    }

    class calculateRPKM implements Runnable {

        private int startindex;
        private int endindex;
        public calculateRPKM(int startindex, int endindex) {
            this.startindex = startindex;
            this.endindex = endindex;
        }
        @Override
        public void run() {

            for (int i = startindex; i < endindex; i++) {
                String file = filelist.get(i);
                computeRPKM cr = new computeRPKM(genelengthmap, file);
                HashMap<String, Integer> countmap = cr.getCountmap();
                HashMap<String, Double> rpkmmap = cr.getRpkmtmap();
                addcountlist(countmap);
                addrpkmlist(rpkmmap);
            }
        }

    }

    public synchronized void addcountlist(HashMap<String, Integer> countmap) {

        this.countmaplist.add(countmap);
    }

    public synchronized void addrpkmlist(HashMap<String, Double> rpkmmap) {

        this.rpkmmaplist.add(rpkmmap);
    }

    public static void main(String[] args) throws IOException {
        getCountMatrixFromMap gmm = new getCountMatrixFromMap("C:\\game\\genelength.txt", "_htseq.txt", "C:\\game\\result");
        gmm.Dir = "C:\\game";
        gmm.process();
    }
    
}
