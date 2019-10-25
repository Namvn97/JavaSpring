package com.example.demo.com.example.demo;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Iterator;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFFont;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.IndexedColors;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.util.CellAddress;
import org.apache.poi.ss.util.CellRangeAddress;
import org.springframework.stereotype.Repository;

@Repository
public class UpdateExcelDemo {
	private static HSSFWorkbook workbook;
	public static final String EXCEL_FILE_PATH = "C:\\Users\\Admin\\Desktop\\testExcel.xls";

	public static void main(String[] args) throws IOException {
//		 
//	        File file = new File("C:\\Users\\Admin\\Desktop\\testExcel.xls");
//	  
//	       // Đọc một file XSL.
//	       FileInputStream inputStream = new FileInputStream(file);
//	  
//	       workbook = new HSSFWorkbook(inputStream);
//	  
//	       // Lấy ra sheet đầu tiên từ workbook
//	       HSSFSheet sheet = workbook.getSheetAt(0);
//	 
//	       HSSFCell cell = sheet.getRow(1).getCell(2);
//	       cell.setCellValue(cell.getNumericCellValue() * 2);
//	      
//	       cell = sheet.getRow(2).getCell(2);
//	       cell.setCellValue(cell.getNumericCellValue() * 2);
//	      
//	       cell = sheet.getRow(3).getCell(2);
//	       cell.setCellValue(cell.getNumericCellValue() * 2);
//	 
//	       inputStream.close();
//	 
//	       // Ghi file
//	       FileOutputStream out = new FileOutputStream(file);
//	       workbook.write(out);
//	       out.close();
	}

	public void createRow(Product product) throws IOException {
		File file = new File(EXCEL_FILE_PATH);

		// Đọc một file XSL.
		FileInputStream inputStream = new FileInputStream(file);

		workbook = new HSSFWorkbook(inputStream);

		// Lấy ra sheet đầu tiên từ workbook
		HSSFSheet sheet = workbook.getSheetAt(0);

		int rownum = sheet.getLastRowNum() + 1;
		Row row = sheet.createRow(rownum);
		Cell cell;

		// EmpName (A)
		cell = row.createCell(0, CellType.STRING);
		cell.setCellValue(product.getId());
		// EmpName (B)
		cell = row.createCell(1, CellType.STRING);
		cell.setCellValue(product.getName());
		// Salary (C)
		cell = row.createCell(2, CellType.NUMERIC);
		cell.setCellValue(product.getSalary());
		// Grade (D)
		cell = row.createCell(3, CellType.NUMERIC);
		cell.setCellValue(product.getGrade());
		// Bonus (E)
		String formula = "0.1*C" + (rownum + 1) + "*D" + (rownum + 1);
		cell = row.createCell(4, CellType.FORMULA);
		cell.setCellFormula(formula);

		inputStream.close();

		// Ghi file
		FileOutputStream out = new FileOutputStream(file);
		workbook.write(out);
		out.close();
		System.out.println("Đang tạo mới");
	}

	public void mergeCell () throws IOException {
		File file = new File(EXCEL_FILE_PATH);

		// Đọc một file XSL.
		FileInputStream inputStream = new FileInputStream(file);

		workbook = new HSSFWorkbook(inputStream);

		// Lấy ra sheet đầu tiên từ workbook
		HSSFSheet sheet = workbook.getSheetAt(0);
		
        CellRangeAddress cell = new CellRangeAddress(8, 10, 8, 10);
        sheet.addMergedRegionUnsafe(cell);
        
        inputStream.close();

		FileOutputStream out = new FileOutputStream(file);
		workbook.write(out);
		out.close();
        System.out.println("Ghép cột");
        
	}
	
	public void setStyleForOneCell(int indexRow) throws IOException {
		File file = new File(EXCEL_FILE_PATH);

		// Đọc một file XSL.
		FileInputStream inputStream = new FileInputStream(file);

		workbook = new HSSFWorkbook(inputStream);

		// Lấy ra sheet đầu tiên từ workbook
		HSSFSheet sheet = workbook.getSheetAt(0);
		// Font
		HSSFFont font = workbook.createFont();
		font.setBold(true);
		font.setItalic(true);
		
		// Font Height
		font.setFontHeightInPoints((short) 18);

		// Font Color
		font.setColor(IndexedColors.BLUE.index);
		
		// 
		sheet.autoSizeColumn(indexRow);

		// Style
		HSSFCellStyle style = workbook.createCellStyle();
		style.setFont(font);

		Row row = sheet.getRow(indexRow);
		
		Iterator<Cell> cellIterator = row.cellIterator();
		
		while (cellIterator.hasNext()) {
		Cell cell = cellIterator.next();
		cell.setCellStyle(style);
		}
				
		inputStream.close();

		FileOutputStream out = new FileOutputStream(file);
		workbook.write(out);
		out.close();
	}
}
