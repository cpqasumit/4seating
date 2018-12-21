package common;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class Common {

	public static File currentDir = new File("");
	static String filepath = currentDir.getAbsolutePath() + "\\Input.xlsx";
	

	public static ArrayList<String> GetURLList(String SheetName, int colIndex) throws IOException {
		
		ArrayList<String> line = new ArrayList<String>();
		FileInputStream fis = new FileInputStream(new File(filepath));
		@SuppressWarnings("resource")
		XSSFWorkbook workbook = new XSSFWorkbook(fis);
		XSSFSheet sheet = workbook.getSheet(SheetName);
		XSSFRow row;
		XSSFCell cell;
		for (int rowIndex = 1; rowIndex <= sheet.getLastRowNum(); rowIndex++) {
			row = sheet.getRow(rowIndex);
			if (row != null) {
				cell = row.getCell(colIndex);
				if (cell != null) {
					switch (cell.getCellTypeEnum()) {
					case BOOLEAN:
						line.add(String.valueOf(cell.getBooleanCellValue()));
						break;
					case NUMERIC:
						line.add(String.valueOf(cell.getNumericCellValue()));
						break;
					case STRING:
						line.add(String.valueOf(cell.getStringCellValue()));
						break;
					case BLANK:
						line.add(String.valueOf(""));
						break;
					default:
						break;
					}
				} else if (cell == null && SheetName == "Property") {
					line.add(String.valueOf(""));
				}
			}
		}
		return line;
	}
	
}
