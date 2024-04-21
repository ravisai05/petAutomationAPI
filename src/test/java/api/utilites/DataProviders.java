package api.utilites;

import java.io.IOException;

import org.testng.annotations.DataProvider;

public class DataProviders {

	@DataProvider(name="data")
	public String[][] getData() throws IOException{
		String path=".\\test-Data\\rest.xlsx";
		ExcelUtility ex=new ExcelUtility(path);
		int rowc=ex.getmaxRows("Sheet1");
		int colc=ex.getmmaxCell("Sheet1", 1);
		String[][] apidata=new String[rowc][colc];
		for(int r=1;r<=rowc;r++) {
			for(int c=0;c<colc;c++) {
				apidata[r-1][c]=ex.getCellValue("Sheet1", r, c);
			}
		}
		return apidata;
		
	}
	@DataProvider(name="UserName")
	public String[] getUserName() throws IOException {
		String path=".\\test-Data\\rest.xlsx";
		ExcelUtility ex=new ExcelUtility(path);
		int rowc=ex.getmaxRows("Sheet1");
		String[] username=new String[rowc]; 
		for(int r=1;r<=rowc;r++) {
			username[r-1]=ex.getCellValue("Sheet1", r, 1);
		}
		return username;
		
	}
}
