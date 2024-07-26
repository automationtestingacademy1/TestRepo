package com.example.myapp.utils;

import java.io.File;
import java.io.FileInputStream;
import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;

public class ExcelUtils {
	public String[][] GetDataFromExcel(String excelLocation, String tcName , String sheetName) throws Exception {
		File f= new File(excelLocation);
		FileInputStream fis= new FileInputStream(f);
		Workbook wb= WorkbookFactory.create(fis);
		Sheet sheetname= wb.getSheet(sheetName);
		int rowcount=sheetname.getLastRowNum();
		Row rowcells=sheetname.getRow(0);
		int colcount=rowcells.getLastCellNum();
		System.out.println(rowcount);
		System.out.println(colcount);
		DataFormatter format= new DataFormatter();
		int p=0;
		for(int a=1; a<=rowcount;a++) {
			if(format.formatCellValue(sheetname.getRow(a).getCell(0)).toString().equals(tcName)){
				p=p+1;
			}
		}
		String testData[][]=new String[p][colcount];
		
		for(int i=1; i<=rowcount;i++) {
			int k=0;
			if(format.formatCellValue(sheetname.getRow(i).getCell(0)).toString().equals(tcName)){
				for(int j=0;j<colcount;j++) {
					testData[k][j]=format.formatCellValue(sheetname.getRow(i).getCell(j));
					
				}
				k=k+1;
			}
		}	
		return (testData);

	}
}
