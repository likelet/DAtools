/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package FormatConvert.exceloperation;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

/**
 *
 * @author zhaoqi
 */
public class ExcelOperation {

    public static void SeperatorExcel2sheet(String excelfile, String targetdir) {
        try {
            FileInputStream is = (new FileInputStream(excelfile));
            if (excelfile.endsWith(".xlsx")) {
                XSSFWorkbook wb = new XSSFWorkbook(is);
                int sheetnub = wb.getNumberOfSheets();
                for (int i = 0; i < sheetnub; i++) {
                    XSSFSheet sheet = wb.getSheetAt(i);
                    if (sheet.toString() != null) {
                        Workbook wb2 = new XSSFWorkbook();
                        XSSFSheet tempsheet = (XSSFSheet) wb2.createSheet();
                        Util.copySheets(tempsheet, sheet, true);
                    //tempsheet=wb.cloneSheet(i);
                        //tempsheet = sheet;
                        String temfile = targetdir + "\\" + sheet.getSheetName() + ".xlsx";
                        FileOutputStream fileOut = new FileOutputStream(temfile);
                        wb2.write(fileOut);
                        fileOut.close();
                    }
                }
            } else {
                HSSFWorkbook wb = new HSSFWorkbook(is);
                int sheetnub = wb.getNumberOfSheets();
                for (int i = 0; i < sheetnub; i++) {
                    HSSFSheet sheet = wb.getSheetAt(i);
                    if (sheet.toString() != null) {
                        Workbook wb2 = new HSSFWorkbook();
                        HSSFSheet tempsheet = (HSSFSheet) wb2.createSheet();
                        Util.copySheets(tempsheet, sheet, true);
                    //tempsheet=wb.cloneSheet(i);
                        //tempsheet = sheet;
                        String temfile = targetdir + "\\" + sheet.getSheetName() + ".xlsx";
                        FileOutputStream fileOut = new FileOutputStream(temfile);
                        wb2.write(fileOut);
                        fileOut.close();
                    }
                }
            }

        } catch (Exception ioe) {
            ioe.printStackTrace();
        }
    }

    public static void MergeExcel(String sourceDir, String targetexcel) {
         try {
             File[] filelist=FilelistReader.getFileList(sourceDir);
             FileOutputStream fileOut = new FileOutputStream(targetexcel);
              Workbook wb = new XSSFWorkbook();        
             for (int i = 0; i < filelist.length; i++) {
                 File f=filelist[i];
                 if(f.getName().endsWith(".xlsx")){
                     FileInputStream is = (new FileInputStream(f));
                     XSSFWorkbook wb2 = new XSSFWorkbook(is);
                     XSSFSheet sheet = wb2.getSheetAt(0);
                     Sheet sheetnew=wb.createSheet(f.getName());
                     //System.out.println(sheet.get);
                     Util.copySheets((XSSFSheet) sheetnew,sheet);
                     
                 }else if(f.getName().endsWith(".xls")){
                       FileInputStream is = (new FileInputStream(f));
                       HSSFWorkbook wb2 = new HSSFWorkbook(is);
                     HSSFSheet sheet = wb2.getSheetAt(0);
                    Sheet sheetnew=wb.createSheet(f.getName());
                     Util.copySheets((HSSFSheet) sheetnew,sheet);
                 }
             }
            wb.write(fileOut);

        } catch (Exception ioe) {
            ioe.printStackTrace();
        }
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        //ExcelOperation.SeperatorExcel2sheet("D:\\EmploymentSharesBlogRemake.xlsx", "D:\\test");
        ExcelOperation.MergeExcel("G:\\Blast", "D:\\new.xlsx");
        // TODO code application logic here
    }

}
