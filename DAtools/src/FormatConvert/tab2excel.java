/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package FormatConvert;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.IndexedColors;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

/**
 *
 * @author ZHAO Qi
 * @date 2015-4-27 12:28:41
 * @version 1.6.0
 */
public class tab2excel {

    private String input;
    private String output;
    private String seperator="\t";

    public tab2excel(String input) {
        this.input = input;
        this.output=input;
    }

    public tab2excel(String input, String output) {
        this.input = input;
        this.output = output;
    }

    public tab2excel(String input, String output,String seperator) {
        this.input = input;
        this.output = output;
        this.seperator=seperator;
    }
    public void tab2excel() throws FileNotFoundException, IOException {
        Workbook wb = new XSSFWorkbook();
        Sheet sheet1 = wb.createSheet("input");

        CellStyle style = wb.createCellStyle();
        style.setFillForegroundColor(IndexedColors.ORANGE.getIndex());
        style.setFillPattern(CellStyle.SOLID_FOREGROUND);

        int rowIndex = 0;
        BufferedReader br = null;
        try {
            br = new BufferedReader(new FileReader(new File(input)));
            String[] str;
            while (br.ready()) {
                str = br.readLine().split(seperator);

                Row row = sheet1.createRow(rowIndex);
                for (int i = 0; i < str.length; i++) {
                    row.createCell(i).setCellValue(str[i]);
                }
                rowIndex++;
            }
            br.close();
        } catch (FileNotFoundException ex) {
            System.out.println(input + " is not found! please check your filepath ");
        } catch (IOException ex) {
            System.out.println("IO error");
        }

        FileOutputStream fileOut = new FileOutputStream(output+".xlsx");
        wb.write(fileOut);
        fileOut.close();
          System.out.println("Convert finished. The output file is named as "+output+".xlsx");
    }

    public String getOutput() {
        return output;
    }

    public void setOutput(String output) {
        this.output = output;
    }

    public String getSeperator() {
        return seperator;
    }

    public void setSeperator(String seperator) {
        this.seperator = seperator;
    }
    
    
    

    
 
}
