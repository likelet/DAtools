/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package FormatConvert.exceloperation;

import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Map;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.xssf.usermodel.XSSFCellStyle;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

/**
 * <p>
 * Excel2csv</p>
 * <p>
 * Created on 2015-9-2 16:05:50</p>
 * <p>
 * Author Email: zhaoqi3@mail2.sysu.edu.cn</p>
 *
 * @author ZHAO Qi
 * @date 2015-9-2 16:05:50
 * @version java 1.6.0
 * @version
 */
public class Excel2csv {

    public static void SeperatorExcel2sheet(String excelfile, String targetdir) {
        try {
            FileInputStream is = (new FileInputStream(excelfile));
            if (excelfile.endsWith(".xlsx")) {
                XSSFWorkbook wb = new XSSFWorkbook(is);
                int sheetnub = wb.getNumberOfSheets();
                for (int i = 0; i < sheetnub; i++) {
                    XSSFSheet sheet = wb.getSheetAt(i);
                    if (sheet.toString() != null) {
                        String temfile = targetdir + "\\" + sheet.getSheetName() + ".csv";
                        Excel2csv.copySheets2CSV(sheet, temfile);
                    }
                }
            } else {
                HSSFWorkbook wb = new HSSFWorkbook(is);
                int sheetnub = wb.getNumberOfSheets();
                for (int i = 0; i < sheetnub; i++) {
                    HSSFSheet sheet = wb.getSheetAt(i);
                    if (sheet.toString() != null) {
                        String temfile = targetdir + "\\" + sheet.getSheetName() + ".csv";
                        Excel2csv.copySheets2CSV(sheet, temfile);
                    }
                }
            }

        } catch (Exception ioe) {
            ioe.printStackTrace();
        }
    }

    public static void copySheets2CSV(HSSFSheet sheet, String csvfile) {
        int maxColumnNum = 0;

        try {
            FileWriter fw = new FileWriter(csvfile);

            String str = "";
            for (int i = sheet.getFirstRowNum(); i <= sheet.getLastRowNum(); i++) {
                HSSFRow srcRow = sheet.getRow(i);

                if (srcRow != null) {
                    for (int j = srcRow.getFirstCellNum(); j <= srcRow.getLastCellNum(); j++) {
                       if (j != srcRow.getLastCellNum()) {
                            str = str +srcRow.getCell(j).getStringCellValue()+ ",";
                        } else {
                            str = str +srcRow.getCell(j).getStringCellValue()+ "\r\n";
                        }

                    }
                    fw.append(str);
                }
                str = "";
            }

            fw.flush();
            fw.close();
        } catch (IOException ex) {

        }
        //Util.copyPictures(newSheet,sheet) ;
    }

    public static void copySheets2CSV(XSSFSheet sheet, String csvfile) {
        int maxColumnNum = 0;
        Map<Integer, XSSFCellStyle> styleMap = null;

        try {
            FileWriter fw = new FileWriter(csvfile);

            String str = "";
            for (int i = sheet.getFirstRowNum(); i <= sheet.getLastRowNum(); i++) {
                XSSFRow srcRow = sheet.getRow(i);
                if (srcRow != null) {
                    System.out.println(srcRow.getLastCellNum());
                    System.out.println(srcRow.getFirstCellNum());
//                    System.out.println(srcRow.getCell(srcRow.getLastCellNum()).toString());
                    for (int j = srcRow.getFirstCellNum(); j < srcRow.getLastCellNum(); j++) {
                        
                        if (srcRow.getCell(j)!=null&&j != srcRow.getLastCellNum()-1) {
                            srcRow.getCell(j).setCellType(1);
                            
                            str = str +srcRow.getCell(j).getReference()+ ",";
                        } else if(srcRow.getCell(j)!=null){
                            srcRow.getCell(j).setCellType(1);
                            
                            str = str +srcRow.getCell(j).getStringCellValue()+ "\r\n";
                        }
//
                    }
                    fw.append(str);
                }
                str = "";
            }

            fw.flush();
            fw.close();
        } catch (IOException ex) {

        }//Util.copyPictures(newSheet,sheet) ;
    }

    public static void main(String[] args) {
        Excel2csv.SeperatorExcel2sheet("F:\\resouces\\projects\\SUMO-FC\\jiang20150806\\systemanlysis of sumo  data\\FunctionalAnaysis.xlsx", "F:\\resouces\\projects\\SUMO-FC\\jiang20150806\\systemanlysis of sumo  data\\list");
    }
}
