package api.utilities;

import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.ss.usermodel.FillPatternType;
import org.apache.poi.ss.usermodel.IndexedColors;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.*;

public class XLUtility {

    public FileInputStream fi;
    public FileOutputStream fo;
    public XSSFWorkbook workbook;
    public XSSFSheet sheet;
    public XSSFRow row;
    public XSSFCell cell;
    public CellStyle style;
    String path;

    //Constructor to initiate the path of xl sheet
    public XLUtility(String path) {
        this.path = path;
    }

    public int getRowCount(String sheetName) throws IOException {
        fi = new FileInputStream(path);
        workbook = new XSSFWorkbook(fi);
        sheet = workbook.getSheet(sheetName);
        int rowcount = sheet.getLastRowNum();
        workbook.close();
        fi.close();
        return rowcount;
    }

    public int getCellCount(String sheetName, int rownum) throws IOException {
        fi = new FileInputStream(path);
        workbook = new XSSFWorkbook(fi);
        sheet = workbook.getSheet(sheetName);
        row = sheet.getRow(rownum); //get the row number from rownum
        int cellCount = row.getLastCellNum();
        workbook.close();
        fi.close();
        return cellCount;
    }

    //Get the data inside the cell
    public String getCellData(String sheetName, int rownum, int column) throws IOException {
        fi = new FileInputStream(path);
        workbook = new XSSFWorkbook(fi);
        sheet = workbook.getSheet(sheetName);
        row = sheet.getRow(rownum);
        cell = row.getCell(column);

        //Read data in string format
        DataFormatter formatter = new DataFormatter();
        String data;
        try {
            //return the formatted value of the cell  as a String regardless of cell type
            data = formatter.formatCellValue(cell);
        } catch (Exception e) {
            data = "";
        }
        workbook.close();
        fi.close();
        return data;
    }

    public void setCellData(String sheetName, int rownum, int column, String data) throws IOException {
        File xlfile = new File(path);
        //if file does not exist, create one
        if(!xlfile.exists())
        {
            workbook = new XSSFWorkbook();
            fo = new FileOutputStream(path);
            workbook.write(fo);
        }
        ////if file does exist, Open the file
        fi = new FileInputStream(path);
        workbook = new XSSFWorkbook(fi);

        //if sheet does not exist create one
        if(workbook.getSheetIndex(sheetName)==-1)
            workbook.createSheet(sheetName);
        //if sheet does exist, get sheet from workbook and store
        sheet = workbook.getSheet(sheetName);

        //if row does not exist, create one
        if(sheet.getRow(rownum)==null)
            sheet.createRow(rownum);
        //if row does exist, get row
        row = sheet.getRow(rownum);

        cell = row.createCell(column);
        cell.setCellValue(data);
        //Update the cell data
        fo = new FileOutputStream(path);
        workbook.write(fo);
        workbook.close();
        fi.close();
        fo.close();
    }

    public void fillGreenColour(String sheetName, int rownum, int column) throws IOException {
        fi = new FileInputStream(path);
        workbook = new XSSFWorkbook(fi);
        sheet = workbook.getSheet(sheetName);

        row = sheet.getRow(rownum);
        cell = row.getCell(column);

        style = workbook.createCellStyle();

        style.setFillForegroundColor(IndexedColors.GREEN.getIndex());
        style.setFillPattern(FillPatternType.SOLID_FOREGROUND);

        cell.setCellStyle(style);
        workbook.write(fo);
        workbook.close();
        fi.close();
        fo.close();
    }

    public void fillRedColour(String sheetName, int rownum, int column) throws IOException {
        fi = new FileInputStream(path);
        workbook = new XSSFWorkbook(fi);
        sheet = workbook.getSheet(sheetName);
        row = sheet.getRow(rownum);
        cell = row.getCell(column);

        style = workbook.createCellStyle();

        style.setFillForegroundColor(IndexedColors.RED.getIndex());
        style.setFillPattern(FillPatternType.SOLID_FOREGROUND);

        cell.setCellStyle(style);
        workbook.write(fo);
        workbook.close();
        fi.close();
        fo.close();
    }


}
