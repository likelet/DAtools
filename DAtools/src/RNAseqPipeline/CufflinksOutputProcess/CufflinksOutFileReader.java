/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package RNAseqPipeline.CufflinksOutputProcess;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.CreationHelper;
import org.apache.poi.ss.usermodel.IndexedColors;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

/**
 *
 * @author Yueyuan
 */
public class CufflinksOutFileReader {

    HashMap<String, ArrayList<Double>> readsCountMap = new HashMap();
    HashMap<String, ArrayList<Double>> fpkmMap = new HashMap();

    public CufflinksOutFileReader(String file1, String file2) {
        try {
            BufferedReader br = new BufferedReader(new java.io.FileReader(new File(file1)));
            this.readsCountMap = new HashMap();
            this.fpkmMap = new HashMap();
            String title = "tracking_id";
            while (br.ready()) {
                String data = br.readLine();
                String[] dataArr = data.split("\t");
                if (!dataArr[0].equals("tracking_id")) {
                    String trackingId = dataArr[0];
                    String condition = dataArr[1]+"_"+dataArr[2];
                    if (!title.contains(condition)) {
                        title += "\t" + condition;
                    }
                    double readsCount = Double.parseDouble(dataArr[3]);
                    double fpkm = Double.parseDouble(dataArr[6]);

                    ArrayList<Double> readsCountList = new ArrayList();
                    ArrayList<Double> fpkmList = new ArrayList();

                    if (!readsCountMap.containsKey(trackingId)) {
                        readsCountList = new ArrayList();
                        readsCountList.add(readsCount);
                        readsCountMap.put(trackingId, readsCountList);
                    } else {
                        readsCountMap.get(trackingId).add(readsCount);
                    }

                    if (!fpkmMap.containsKey(trackingId)) {
                        fpkmList = new ArrayList();
                        fpkmList.add(fpkm);
                        fpkmMap.put(trackingId, fpkmList);
                    } else {
                        fpkmMap.get(trackingId).add(fpkm);
                    }
                }

            }

            Workbook wb = new XSSFWorkbook();
            Sheet sheet1 = wb.createSheet("readsCount");
            Sheet sheet2 = wb.createSheet("gene expression_fpkm");

            CellStyle style = wb.createCellStyle();
            style.setFillForegroundColor(IndexedColors.ORANGE.getIndex());
            style.setFillPattern(CellStyle.SOLID_FOREGROUND);

            String[] titleArr = title.split("\t");
            Row row1=sheet1.createRow((short) 0);
            Row row2=sheet2.createRow((short) 0);
            for (int i = 0; i < titleArr.length; i++) {
                String colTitle = titleArr[i];               
                Cell cell1 =row1.createCell(i);
                cell1.setCellValue(colTitle);
                cell1.setCellStyle(style);
                Cell cell2 = row2.createCell(i);
                cell2.setCellValue(colTitle);
                cell2.setCellStyle(style);
            }
            
            int rowIndex = 1;
            for (Iterator<String> it = readsCountMap.keySet().iterator(); it.hasNext();) {
                String key = it.next();
                Row row = sheet1.createRow(rowIndex);
                Cell cell = row.createCell((short) 0);
                cell.setCellValue(key);
                int colIndex = 1;
                for (Iterator<Double> its = readsCountMap.get(key).iterator(); its.hasNext();) {
                    Double readsCount = its.next();
                    row.createCell(colIndex).setCellValue(readsCount);
                    colIndex++;
                }
                rowIndex++;
            }

            rowIndex = 1;
            for (Iterator<String> it = fpkmMap.keySet().iterator(); it.hasNext();) {
                String key = it.next();
                Row row = sheet2.createRow(rowIndex);
                Cell cell = row.createCell((short) 0);
                cell.setCellValue(key);
                int colIndex = 1;
                for (Iterator<Double> its = fpkmMap.get(key).iterator(); its.hasNext();) {
                    Double fpkm = its.next();
                    row.createCell(colIndex).setCellValue(fpkm);
                    colIndex++;
                }
                rowIndex++;
            }

            FileOutputStream fileOut = new FileOutputStream(file2);
            wb.write(fileOut);
            fileOut.close();
            System.out.println("result file is generated and located in "+file2);
        } catch (IOException ex) {
            System.out.println("IO error!");
        }
    }
}
