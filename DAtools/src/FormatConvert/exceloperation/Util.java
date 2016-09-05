package FormatConvert.exceloperation;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.TreeSet;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.ss.util.CellRangeAddress;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFCellStyle;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;

/**
 *
 * @author jk getted from
 * http://jxls.cvs.sourceforge.net/jxls/jxls/src/java/org/jxls/util/Util.java?revision=1.8&view=markup
 * by Leonid Vysochyn and modified (adding styles copying) modified by Philipp
 * Löpmeier (replacing deprecated classes and methods, using generic types)
 * Modified by Qi Zhao
 * Copy sheet 
 */


public final class Util {

    /**
     * DEFAULT CONSTRUCTOR.
     */
    private Util() {
    }

    /**
     * @param newSheet the sheet to create from the copy.
     * @param sheet the sheet to copy.
     */
    public static void copySheets(XSSFSheet newSheet, XSSFSheet sheet) {
        copySheets(newSheet, sheet, true);
    }

     public static void copySheets(HSSFSheet newSheet, HSSFSheet sheet) {
        copySheets(newSheet, sheet, true);
    }

    /**
     * @param newSheet the sheet to create from the copy.
     * @param sheet the sheet to copy.
     * @param copyStyle true copy the style.
     */
    public static void copySheets(XSSFSheet newSheet, XSSFSheet sheet, boolean copyStyle) {
        int maxColumnNum = 0;
        Map<Integer, XSSFCellStyle> styleMap = (copyStyle) ? new HashMap<Integer, XSSFCellStyle>() : null;
        for (int i = sheet.getFirstRowNum(); i <= sheet.getLastRowNum(); i++) {
            XSSFRow srcRow = sheet.getRow(i);
            XSSFRow destRow = newSheet.createRow(i);
            if (srcRow != null) {
                Util.copyRow(sheet, newSheet, srcRow, destRow, styleMap);
                if (srcRow.getLastCellNum() > maxColumnNum) {
                    maxColumnNum = srcRow.getLastCellNum();
                }
            }
        }
        for (int i = 0; i <= maxColumnNum; i++) {
            newSheet.setColumnWidth(i, sheet.getColumnWidth(i));
        }
       //Util.copyPictures(newSheet,sheet) ;
    }

    /**
     * @param srcSheet the sheet to copy.
     * @param destSheet the sheet to create.
     * @param srcRow the row to copy.
     * @param destRow the row to create.
     * @param styleMap -
     */
    public static void copyRow(XSSFSheet srcSheet, XSSFSheet destSheet, XSSFRow srcRow, XSSFRow destRow, Map<Integer, XSSFCellStyle> styleMap) {
        // manage a list of merged zone in order to not insert two times a merged zone  
        Set<CellRangeAddressWrapper> mergedRegions = new TreeSet<CellRangeAddressWrapper>();
        destRow.setHeight(srcRow.getHeight());
        // pour chaque row  
        for (int j = srcRow.getFirstCellNum(); j <= srcRow.getLastCellNum(); j++) {
            if(j<0){
            }else{
                XSSFCell oldCell = srcRow.getCell(j);   // ancienne cell  
            XSSFCell newCell = destRow.getCell(j);  // new cell   
            if (oldCell != null) {
                if (newCell == null) {
                    newCell = destRow.createCell(j);
                }
                // copy chaque cell  
                copyCell(oldCell, newCell, styleMap);
                      // copy les informations de fusion entre les cellules  
                //System.out.println("row num: " + srcRow.getRowNum() + " , col: " + (short)oldCell.getColumnIndex());  
                CellRangeAddress mergedRegion = getMergedRegion(srcSheet, srcRow.getRowNum(), (short) oldCell.getColumnIndex());

                if (mergedRegion != null) {
                    //System.out.println("Selected merged region: " + mergedRegion.toString());  
                    CellRangeAddress newMergedRegion = new CellRangeAddress(mergedRegion.getFirstRow(), mergedRegion.getLastRow(), mergedRegion.getFirstColumn(), mergedRegion.getLastColumn());
                    //System.out.println("New merged region: " + newMergedRegion.toString());  
                    CellRangeAddressWrapper wrapper = new CellRangeAddressWrapper(newMergedRegion);
                    if (isNewMergedRegion(wrapper, mergedRegions)) {
                        mergedRegions.add(wrapper);
                        destSheet.addMergedRegion(wrapper.range);
                    }
                }
            }
            }
            
        }

    }

    /**
     * @param oldCell
     * @param newCell
     * @param styleMap
     */
    public static void copyCell(XSSFCell oldCell, XSSFCell newCell, Map<Integer, XSSFCellStyle> styleMap) {
        if (styleMap != null) {
            if (oldCell.getSheet().getWorkbook() == newCell.getSheet().getWorkbook()) {
                newCell.setCellStyle(oldCell.getCellStyle());
            } else {
                int stHashCode = oldCell.getCellStyle().hashCode();
                XSSFCellStyle newCellStyle = styleMap.get(stHashCode);
                if (newCellStyle == null) {
                    newCellStyle = newCell.getSheet().getWorkbook().createCellStyle();
                    newCellStyle.cloneStyleFrom(oldCell.getCellStyle());
                    styleMap.put(stHashCode, newCellStyle);
                }
                newCell.setCellStyle(newCellStyle);
            }
        }
        switch (oldCell.getCellType()) {
            case XSSFCell.CELL_TYPE_STRING:
                newCell.setCellValue(oldCell.getStringCellValue());
                break;
            case XSSFCell.CELL_TYPE_NUMERIC:
                newCell.setCellValue(oldCell.getNumericCellValue());
                break;
            case XSSFCell.CELL_TYPE_BLANK:
                newCell.setCellType(XSSFCell.CELL_TYPE_BLANK);
                break;
            case XSSFCell.CELL_TYPE_BOOLEAN:
                newCell.setCellValue(oldCell.getBooleanCellValue());
                break;
            case XSSFCell.CELL_TYPE_ERROR:
                newCell.setCellErrorValue(oldCell.getErrorCellValue());
                break;
            case XSSFCell.CELL_TYPE_FORMULA:
                newCell.setCellFormula(oldCell.getCellFormula());
                break;
            default:
                break;
        }

    }

    /**
     * Récupère les informations de fusion des cellules dans la sheet source
     * pour les appliquer à la sheet destination... Récupère toutes les zones
     * merged dans la sheet source et regarde pour chacune d'elle si elle se
     * trouve dans la current row que nous traitons. Si oui, retourne l'objet
     * CellRangeAddress.
     *
     * @param sheet the sheet containing the data.
     * @param rowNum the num of the row to copy.
     * @param cellNum the num of the cell to copy.
     * @return the CellRangeAddress created.
     */
    public static CellRangeAddress getMergedRegion(XSSFSheet sheet, int rowNum, short cellNum) {
        for (int i = 0; i < sheet.getNumMergedRegions(); i++) {
            CellRangeAddress merged = sheet.getMergedRegion(i);
            if (merged.isInRange(rowNum, cellNum)) {
                return merged;
            }
        }
        return null;
    }

    /**
     * Check that the merged region has been created in the destination sheet.
     *
     * @param newMergedRegion the merged region to copy or not in the
     * destination sheet.
     * @param mergedRegions the list containing all the merged region.
     * @return true if the merged region is already in the list or not.
     */
    private static boolean isNewMergedRegion(CellRangeAddressWrapper newMergedRegion, Set<CellRangeAddressWrapper> mergedRegions) {
        return !mergedRegions.contains(newMergedRegion);
    }
    
    public static void copySheetsHSSF(HSSFSheet newSheet, HSSFSheet sheet) {
        copySheets(newSheet, sheet, true);
    }

    /**
     * @param newSheet the sheet to create from the copy.
     * @param sheet the sheet to copy.
     * @param copyStyle true copy the style.
     */
    public static void copySheets(HSSFSheet newSheet, HSSFSheet sheet, boolean copyStyle) {
        int maxColumnNum = 0;
        Map<Integer, HSSFCellStyle> styleMap = (copyStyle) ? new HashMap<Integer, HSSFCellStyle>() : null;
        for (int i = sheet.getFirstRowNum(); i <= sheet.getLastRowNum(); i++) {
            HSSFRow srcRow = sheet.getRow(i);
            HSSFRow destRow = newSheet.createRow(i);
            if (srcRow != null) {
                Util.copyRow(sheet, newSheet, srcRow, destRow, styleMap);
                if (srcRow.getLastCellNum() > maxColumnNum) {
                    maxColumnNum = srcRow.getLastCellNum();
                }
            }
        }
        for (int i = 0; i <= maxColumnNum; i++) {
            newSheet.setColumnWidth(i, sheet.getColumnWidth(i));
        }
       //Util.copyPictures(newSheet,sheet) ;
    }

    /**
     * @param srcSheet the sheet to copy.
     * @param destSheet the sheet to create.
     * @param srcRow the row to copy.
     * @param destRow the row to create.
     * @param styleMap -
     */
    public static void copyRow(HSSFSheet srcSheet, HSSFSheet destSheet, HSSFRow srcRow, HSSFRow destRow, Map<Integer, HSSFCellStyle> styleMap) {
        // manage a list of merged zone in order to not insert two times a merged zone  
        Set<CellRangeAddressWrapper> mergedRegions = new TreeSet<CellRangeAddressWrapper>();
        destRow.setHeight(srcRow.getHeight());
        // pour chaque row  
        for (int j = srcRow.getFirstCellNum(); j <= srcRow.getLastCellNum(); j++) {
            if(j<0){
            }else{
            HSSFCell oldCell = srcRow.getCell(j);   // ancienne cell  
            HSSFCell newCell = destRow.getCell(j);  // new cell   
            if (oldCell != null) {
                if (newCell == null) {
                    newCell = destRow.createCell(j);
                }
                // copy chaque cell  
                copyCell(oldCell, newCell, styleMap);
                      // copy les informations de fusion entre les cellules  
                //System.out.println("row num: " + srcRow.getRowNum() + " , col: " + (short)oldCell.getColumnIndex());  
                CellRangeAddress mergedRegion = getMergedRegion(srcSheet, srcRow.getRowNum(), (short) oldCell.getColumnIndex());

                if (mergedRegion != null) {
                    //System.out.println("Selected merged region: " + mergedRegion.toString());  
                    CellRangeAddress newMergedRegion = new CellRangeAddress(mergedRegion.getFirstRow(), mergedRegion.getLastRow(), mergedRegion.getFirstColumn(), mergedRegion.getLastColumn());
                    //System.out.println("New merged region: " + newMergedRegion.toString());  
                    CellRangeAddressWrapper wrapper = new CellRangeAddressWrapper(newMergedRegion);
                    if (isNewMergedRegion(wrapper, mergedRegions)) {
                        mergedRegions.add(wrapper);
                        destSheet.addMergedRegion(wrapper.range);
                    }
                }
            }
        }
        }

    }

    /**
     * @param oldCell
     * @param newCell
     * @param styleMap
     */
    public static void copyCell(HSSFCell oldCell, HSSFCell newCell, Map<Integer, HSSFCellStyle> styleMap) {
        if (styleMap != null) {
            if (oldCell.getSheet().getWorkbook() == newCell.getSheet().getWorkbook()) {
                newCell.setCellStyle(oldCell.getCellStyle());
            } else {
                int stHashCode = oldCell.getCellStyle().hashCode();
                HSSFCellStyle newCellStyle = styleMap.get(stHashCode);
                if (newCellStyle == null) {
                    newCellStyle = newCell.getSheet().getWorkbook().createCellStyle();
                    newCellStyle.cloneStyleFrom(oldCell.getCellStyle());
                    styleMap.put(stHashCode, newCellStyle);
                }
                newCell.setCellStyle(newCellStyle);
            }
        }
        switch (oldCell.getCellType()) {
            case HSSFCell.CELL_TYPE_STRING:
                newCell.setCellValue(oldCell.getStringCellValue());
                break;
            case HSSFCell.CELL_TYPE_NUMERIC:
                newCell.setCellValue(oldCell.getNumericCellValue());
                break;
            case HSSFCell.CELL_TYPE_BLANK:
                newCell.setCellType(HSSFCell.CELL_TYPE_BLANK);
                break;
            case HSSFCell.CELL_TYPE_BOOLEAN:
                newCell.setCellValue(oldCell.getBooleanCellValue());
                break;
            case HSSFCell.CELL_TYPE_ERROR:
                newCell.setCellErrorValue(oldCell.getErrorCellValue());
                break;
            case HSSFCell.CELL_TYPE_FORMULA:
                newCell.setCellFormula(oldCell.getCellFormula());
                break;
            default:
                break;
        }

    }

    /**
     * Récupère les informations de fusion des cellules dans la sheet source
     * pour les appliquer à la sheet destination... Récupère toutes les zones
     * merged dans la sheet source et regarde pour chacune d'elle si elle se
     * trouve dans la current row que nous traitons. Si oui, retourne l'objet
     * CellRangeAddress.
     *
     * @param sheet the sheet containing the data.
     * @param rowNum the num of the row to copy.
     * @param cellNum the num of the cell to copy.
     * @return the CellRangeAddress created.
     */
    public static CellRangeAddress getMergedRegion(HSSFSheet sheet, int rowNum, short cellNum) {
        for (int i = 0; i < sheet.getNumMergedRegions(); i++) {
            CellRangeAddress merged = sheet.getMergedRegion(i);
            if (merged.isInRange(rowNum, cellNum)) {
                return merged;
            }
        }
        return null;
    }

    
//    private static void copyPictures(Sheet newSheet, Sheet sheet) {  
//    Drawing drawingOld = sheet.createDrawingPatriarch();  
//    Drawing drawingNew = newSheet.createDrawingPatriarch();  
//    CreationHelper helper = newSheet.getWorkbook().getCreationHelper();  
//  
//    if (drawingNew instanceof HSSFPatriarch) {  
//        List<HSSFShape> shapes = ((HSSFPatriarch) drawingOld).getChildren();  
//        for (int i = 0; i < shapes.size(); i++) {  
//        if (shapes.get(i) instanceof HSSFPicture) {  
//            HSSFPicture pic = (HSSFPicture) shapes.get(i);  
//            HSSFPictureData picdata = pic.getPictureData();  
//            int pictureIndex = newSheet.getWorkbook().addPicture(picdata.getData(),picdata.getFormat());  
//            ClientAnchor anchor = null;  
//            if (pic.getAnchor() != null) {  
//            anchor = helper.createClientAnchor();  
//            anchor.setDx1(((HSSFClientAnchor) pic.getAnchor()).getDx1());  
//            anchor.setDx2(((HSSFClientAnchor) pic.getAnchor()).getDx2());  
//            anchor.setDy1(((HSSFClientAnchor) pic.getAnchor()).getDy1());  
//            anchor.setDy2(((HSSFClientAnchor) pic.getAnchor()).getDy2());  
//            anchor.setCol1(((HSSFClientAnchor) pic.getAnchor()).getCol1());  
//            anchor.setCol2(((HSSFClientAnchor) pic.getAnchor()).getCol2());  
//            anchor.setRow1(((HSSFClientAnchor) pic.getAnchor()).getRow1());  
//            anchor.setRow2(((HSSFClientAnchor) pic.getAnchor()).getRow2());  
//            anchor.setAnchorType(((HSSFClientAnchor) pic.getAnchor()).getAnchorType());  
//            }  
//            drawingNew.createPicture(anchor, pictureIndex);  
//        }  
//        }  
//    } else {  
//        if (drawingNew instanceof XSSFDrawing) {  
//        List<XSSFShape> shapes = ((XSSFDrawing) drawingOld).getShapes();  
//        for (int i = 0; i < shapes.size(); i++) {  
//            if (shapes.get(i) instanceof XSSFPicture) {  
//            XSSFPicture pic = (XSSFPicture) shapes.get(i);  
//            XSSFPictureData picdata = pic.getPictureData();  
//            int pictureIndex = newSheet.getWorkbook().addPicture(picdata.getData(), picdata.getPictureType());  
//            XSSFClientAnchor anchor = null;  
//            CTTwoCellAnchor oldAnchor = ((XSSFDrawing) drawingOld).getCTDrawing().getTwoCellAnchorArray(i);  
//            if (oldAnchor != null) {  
//                anchor = (XSSFClientAnchor) helper.createClientAnchor();  
//                CTMarker markerFrom = oldAnchor.getFrom();  
//                CTMarker markerTo = oldAnchor.getTo();  
//                anchor.setDx1((int) markerFrom.getColOff());  
//                anchor.setDx2((int) markerTo.getColOff());  
//                anchor.setDy1((int) markerFrom.getRowOff());  
//                anchor.setDy2((int) markerTo.getRowOff());  
//                anchor.setCol1(markerFrom.getCol());  
//                anchor.setCol2(markerTo.getCol());  
//                anchor.setRow1(markerFrom.getRow());  
//                anchor.setRow2(markerTo.getRow());  
//            }  
//            drawingNew.createPicture(anchor, pictureIndex);  
//            }  
//        }  
//        }  
//    }  
//    } 

}
