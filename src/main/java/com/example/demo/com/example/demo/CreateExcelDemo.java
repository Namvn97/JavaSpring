package com.example.demo.com.example.demo;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;

import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFFont;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.Row;

public class CreateExcelDemo {
	
	private static HSSFCellStyle createStyleForTitle(HSSFWorkbook workbook) {
        HSSFFont font = workbook.createFont();
        font.setBold(true);
        HSSFCellStyle style = workbook.createCellStyle();
        style.setFont(font);
        return style;
    }
	
	public static void main(String[] args) throws IOException {
		HSSFWorkbook workbook = new HSSFWorkbook();
	        HSSFSheet sheet = workbook.createSheet("Product sheet");
	 
	        List<Product> list = ProductDAO.listProducts();
	 
	        int rownum = 0;
	        Cell cell;
	        Row row;
	        //
	        HSSFCellStyle style = createStyleForTitle(workbook);
	 
	        row = sheet.createRow(rownum);
	 
	        // EmpNo
	        cell = row.createCell(0, CellType.STRING);
	        cell.setCellValue("ProductID");
	        cell.setCellStyle(style);
	        // EmpName
	        cell = row.createCell(1, CellType.STRING);
	        cell.setCellValue("ProductName");
	        cell.setCellStyle(style);
	        // Salary
	        cell = row.createCell(2, CellType.STRING);
	        cell.setCellValue("Salary");
	        cell.setCellStyle(style);
	        // Grade
	        cell = row.createCell(3, CellType.STRING);
	        cell.setCellValue("Grade");
	        cell.setCellStyle(style);
	        // Bonus
	        cell = row.createCell(4, CellType.STRING);
	        cell.setCellValue("Bonus");
	        cell.setCellStyle(style);
	 
	        // Data
	        for (Product product : list) {
	            rownum++;
	            row = sheet.createRow(rownum);
	 
	            // EmpNo (A)
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
	        }
	        File file = new File("C:\\Users\\Admin\\Desktop\\testExcel.xls");
	        file.getParentFile().mkdirs();
	 
	        FileOutputStream outFile = new FileOutputStream(file);
	        workbook.write(outFile);
	        System.out.println("Created file: " + file.getAbsolutePath());
	    }		
}