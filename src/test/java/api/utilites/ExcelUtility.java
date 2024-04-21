package api.utilites;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;



public class ExcelUtility {

	public File file;
	public FileInputStream fis;
	public Workbook workbook;
	public Sheet sheet;
	public Row row;
	public Cell cell;
	String path;
public ExcelUtility(String path) {
	// TODO Auto-generated constructor stub
	this.path=path;
}

public int  getmaxRows(String SheetName) throws IOException {
	file=new File(path);
	fis=new FileInputStream(file);
	workbook=new XSSFWorkbook(fis);
	sheet=workbook.getSheet(SheetName);
	int maxRows=sheet.getLastRowNum();
	workbook.close();
	fis.close();
	return maxRows;	
}

public int getmmaxCell(String SheetName, int rowNum) throws IOException {
	file=new File(path);
	fis=new FileInputStream(file);
	workbook=new XSSFWorkbook(fis);
	sheet = workbook.getSheet(SheetName);
	row= sheet.getRow(rowNum);
	int c=row.getLastCellNum();
	workbook.close();
	fis.close();
	return c;
	
}
public String getCellValue(String sheetName,int rowNum,int colNum) throws IOException {
	file=new File(path);
	fis=new FileInputStream(file);
	workbook=new XSSFWorkbook(fis);
	sheet = workbook.getSheet(sheetName);
	row =  sheet.getRow(rowNum);
	cell = row.getCell(colNum);
	DataFormatter formatter=new DataFormatter();
	String data;
	try {
		data=formatter.formatCellValue(cell);
		
	}catch (Exception e) {
		// TODO: handle exception
		data="";
	}
	workbook.close();
	fis.close();
	return data;
}


	
}
