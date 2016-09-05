/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ExomeSeqAnalysisPipe.snpeffanno;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

/**
 *
 * @author 媛子 modified by QiZhao
 */
public class SnpEffReader {

    SnpEffReader(String geneFile, String inputfiledir, String resultFile) throws IOException {
        ArrayList<String> geneList = new GeneFileReader(geneFile).getGeneList();
 
        
        File file = new File(inputfiledir);
        File[] list = file.listFiles();
        Workbook wb = new XSSFWorkbook();
        Sheet sheet1 = wb.createSheet("geneSizeTotal");
        Sheet sheet2 = wb.createSheet("geneSizeSys");
        Sheet sheet3 = wb.createSheet("geneSizeNonsys");
        Sheet sheet4 = wb.createSheet("geneIntervalStatic");
        Row geneRow1 = sheet1.createRow(0);
        Row geneRow2 = sheet2.createRow(0);
        Row geneRow3 = sheet3.createRow(0);
        Row geneRow4 = sheet4.createRow(0);
        for (int i = 0; i < list.length; i++) {
            HashMap<String, ArrayList<StatePosForm>> dataMap = new SnpEffOutputFileReader(list[i].getAbsolutePath()).getDataMap();
            int colIndex = 0;
            Row row1 = sheet1.createRow(i + 1);
            Row row2 = sheet2.createRow(i + 1);
            Row row3 = sheet3.createRow(i + 1);
            Row row4 = sheet4.createRow(i + 1);
            row1.createCell(0).setCellValue(list[i].getName().split("\\.")[0]);
            row2.createCell(0).setCellValue(list[i].getName().split("\\.")[0]);
            row3.createCell(0).setCellValue(list[i].getName().split("\\.")[0]);
            row4.createCell(0).setCellValue(list[i].getName().split("\\.")[0]);
            for (Iterator<String> it = geneList.iterator(); it.hasNext();) {
                String geneName = it.next();
                colIndex++;
                int size = 0;
                int synSize = 0;
                int nonsysSize = 0;
                String intervalInfo = "";
//                String homInfo="";String hetInfo="";
                geneRow1.createCell(colIndex).setCellValue(geneName);
                geneRow2.createCell(colIndex).setCellValue(geneName);
                geneRow3.createCell(colIndex).setCellValue(geneName);
                geneRow4.createCell(colIndex).setCellValue(geneName);
                if (dataMap.containsKey(geneName)) {
                    size = dataMap.get(geneName).size();
                    int tempPos = 0;
                    int intervalTemp = 0;
                    for (Iterator<StatePosForm> its = dataMap.get(geneName).iterator(); its.hasNext();) {
                        StatePosForm statePos = its.next();
                        String state = statePos.getSnpState();
                        int pos = statePos.getPosition();
                        if (state.startsWith("SYNONYMOUS_CODING")) {
                            synSize++;
                        } else if(state.startsWith("NON_SYNONYMOUS_CODING")){
                            nonsysSize++;

                        }
                        if (tempPos != 0) {
                            intervalTemp = pos - tempPos - 1;
                            intervalInfo += intervalTemp + " ";
                        }
                        tempPos = pos;
                    }
                }
                row1.createCell(colIndex).setCellValue(size);
                row2.createCell(colIndex).setCellValue(synSize);
                row3.createCell(colIndex).setCellValue(nonsysSize);
                row4.createCell(colIndex).setCellValue(intervalInfo.trim());
            }
        }
        FileOutputStream fileOut = new FileOutputStream(resultFile);
        wb.write(fileOut);
        fileOut.close();
    }
}
