package atf.tv3hub.common;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import jxl.Cell;
import jxl.Sheet;
import jxl.Workbook;
import jxl.format.CellFormat;
import jxl.format.Colour;
import jxl.read.biff.BiffException;
import jxl.write.Formula;
import jxl.write.Label;
import jxl.write.Number;
import jxl.write.WritableCellFeatures;
import jxl.write.WritableCellFormat;
import jxl.write.WritableFont;
import jxl.write.WritableWorkbook;
import jxl.write.WriteException;
import jxl.write.biff.RowsExceededException;

import org.apache.commons.lang3.ArrayUtils;

public class ExcelUtil {

	Workbook workbook;
	WritableWorkbook writableWorkbook;
	Map<String, WritableCellFormat> customStyle=new HashMap<String, WritableCellFormat>();
	
	public ExcelUtil(String file,String mode){
		try {
			if (mode.equals("read")){
				workbook = Workbook.getWorkbook(new File(file));
				}else if(mode.equals("write")){
				writableWorkbook = Workbook.createWorkbook(new File(file));
					}else{
				System.out.println("Invalid Mode");
				}
			} catch (BiffException e) {
					e.printStackTrace();
				} catch (IOException e) {
					e.printStackTrace();
					}	
			};
	
	public ExcelUtil(String inputFile,String ouputFile,String mode){
		try {
				if(mode.equals("copy")){
					workbook = Workbook.getWorkbook(new File(inputFile));
					writableWorkbook = Workbook.createWorkbook(new File(ouputFile),workbook);
				}else{
					System.out.println("Invalid Mode");
				}
			} catch (BiffException e) {
					e.printStackTrace();
				} catch (IOException e) {
					e.printStackTrace();
				}
			};
	
	public String getDatawithKey(String sheet,int column,String key){
		if (workbook.getSheet(sheet).findCell(key)!=null){
			int keyrow= workbook.getSheet(sheet).findCell(key).getRow();
		    return workbook.getSheet(sheet).getCell(column, keyrow).getContents();
		}else{
			return null;
		}
  	}
	
		/*******************************************NEW METHODS****************************************/	
	
	public String getDataByRowKey(String sheetName, String key) {
		Sheet sheet = workbook.getSheet(sheetName);
		String paramName = null;
		Cell[] cells = sheet.getRow(sheet.findCell(key).getRow());
		for (int i = 1; i < cells.length; i++) {
			paramName = cells[i].getContents();
		}
		return paramName;
	}
	
	public String getDataByColumnKey(String sheetName, String key) {
		Sheet sheet = workbook.getSheet(sheetName);
		String paramName = null;
		Cell[] cells = sheet.getColumn(sheet.findCell(key).getColumn());
		for (int i = 1; i < cells.length; i++) {
			paramName = cells[i].getContents();
		}
		return paramName;
	}
	public List<String> getDataByRowKey(String fileName, String sheetName, String key) throws BiffException, IOException {
		Workbook workbook = Workbook.getWorkbook(new File(fileName));
		Sheet sheet = workbook.getSheet(sheetName);
		List<String> paramNames = new ArrayList<String>();
		Cell[] cells = sheet.getRow(sheet.findCell(key).getRow());
		for (int i = 1; i < cells.length; i++) {
			paramNames.add(cells[i].getContents());
		}
		return paramNames;
	}
	public List<String> getDataByRowKeyFromExcel(String sheetName, String key) throws BiffException, IOException {
		Sheet sheet = workbook.getSheet(sheetName);
		List<String> paramNames = new ArrayList<String>();
		Cell[] cells = sheet.getRow(sheet.findCell(key).getRow());
		for (int i = 1; i < cells.length; i++) {
			paramNames.add(cells[i].getContents());
		}
		//System.out.println(paramNames);
		return paramNames;
	}
	
	public String getDataByRowAndColumnKey(String fileName, String sheetName, String rowKey, String columnKey) throws BiffException, IOException{
		Workbook workbook = Workbook.getWorkbook(new File(fileName));
		Sheet sheet = workbook.getSheet(sheetName);
		String paramName = null;
		int keyRow=sheet.findCell(rowKey).getRow();
		int keyColumn=sheet.findCell(columnKey).getColumn();
		paramName=sheet.getCell(keyColumn, keyRow).getContents();
		return paramName;
	}
	public String getDataByRowAndColumnKey(String sheetName, String rowKey, String columnKey){
		Sheet sheet = workbook.getSheet(sheetName);
		String paramName = null;
		int keyRow=sheet.findCell(rowKey).getRow();
		int keyColumn=sheet.findCell(columnKey).getColumn();
		paramName=sheet.getCell(keyColumn, keyRow).getContents();
		return paramName;
	}
	
	public  List<String> getDataByTwoColumnNames(String sheetName, String key,String ColumnName1,String ColumnName2){
		List<String> paramNames = new ArrayList<String>();
		Sheet sheet = workbook.getSheet(sheetName);
		Cell[] cells = sheet.getRow(sheet.findCell(key).getRow());
		int ColumnNo1=workbook.getSheet(sheetName).findCell(ColumnName1).getColumn();
		int ColumnNo2=workbook.getSheet(sheetName).findCell(ColumnName2).getColumn();
		if(ColumnNo1<=ColumnNo2){						
			for (int i = ColumnNo1; i<=ColumnNo2; i++) { 
				paramNames.add(cells[i].getContents());
			}
		}
		else{
			System.out.println("Column 1 greater than Coulmn 2");
		}
		return paramNames;	
	}
	
/*******************************************************************************************************************************************/
	
	public String getDatawithKey(String sheet,int column,String key,int Count){
		if (workbook.getSheet(sheet).findCell(key)!=null){
			int keyrow= workbook.getSheet(sheet).findCell(key).getRow();
			return workbook.getSheet(sheet).getCell(column, keyrow+Count).getContents();
		}else{
			return null;
		}
   	}
	public String[] getAllDatawithCondition(String sheet,int column,int condcol,String condvalue){
		String[] valuelist=new String[workbook.getSheet(sheet).getRows()-1];
		int i=0;
		int j=1;
		while(i < valuelist.length){
			if (workbook.getSheet(sheet).getCell(condcol, j).getContents().trim().equals(condvalue)){
				valuelist[i]=workbook.getSheet(sheet).getCell(column, j).getContents();
				i++;
			}else{
			valuelist=(String[]) ArrayUtils.remove(valuelist, i);
			}
			j++;
		}
		return valuelist;
	}
	public String getColumnDataWithKey(String sheet, String key, String columnName){
        
        int Column= workbook.getSheet(sheet).findCell(columnName).getColumn();
        int keyrow= workbook.getSheet(sheet).findCell(key).getRow();
        
        return workbook.getSheet(sheet).getCell(Column, keyrow).getContents();
	}
	
	public void putDatawithKey(String sheet,int column,String key,int Count,String data){
		
		if (writableWorkbook.getSheet(sheet).findCell(key)!=null){
			int keyrow= writableWorkbook.getSheet(sheet).findCell(key).getRow();
			putData(sheet, column, keyrow+Count, data);
		}
   	}
	
	public void putDatawithKey(String sheet,int column,String key,String data){
		
		if (writableWorkbook.getSheet(sheet).findCell(key)!=null){
			int keyrow= writableWorkbook.getSheet(sheet).findCell(key).getRow();
			putData(sheet, column, keyrow, data);
		}
   	}
	public void putData(String sheet,int column,int row,String data){
			try {
				if (data.startsWith("=")){
					writableWorkbook.getSheet(sheet).addCell(new Formula(column, row, data.split("=",2)[1]));
					System.out.println(data);
				}else{
					writableWorkbook.getSheet(sheet).addCell(new Label(column,row,data));
				}
			} catch (RowsExceededException e) {
				e.printStackTrace();
			} catch (WriteException e) {
				e.printStackTrace();
			}		
   	}

    public void putDataWithColumnName(String sheet,String columnName,String key,String data){
    
    	System.out.println("Sheet Name:"+"==>"+sheet+" "+"coulmnName:"+"==>"+columnName+" "+"Key: "+"==>"+key+" "+"Data:"+"==>"+data+" ");
    	if (writableWorkbook.getSheet(sheet).findCell(key)!=null){
       	    int Column= workbook.getSheet(sheet).findCell(columnName).getColumn();
			int keyrow= writableWorkbook.getSheet(sheet).findCell(key).getRow();
			putData(sheet, Column, keyrow, data);
			}
    }
    
	public void putData(String sheet,int column,int row,Double data){
		try {
			writableWorkbook.getSheet(sheet).addCell(new Number(column,row,data));
			} catch (RowsExceededException e) {
				e.printStackTrace();
			} catch (WriteException e) {
				e.printStackTrace();
		}
	}
	
	public void putComment(String sheet,int column,int row,String comment){
		WritableCellFeatures feature=new WritableCellFeatures();
		feature.setReadComment(comment, 5, (3+comment.split("\n").length));
		writableWorkbook.getSheet(sheet).getWritableCell(column, row).setCellFeatures(feature);
	}
	
	public void setHeaderName(String sheet,int column,int row,String name,String style){
		try {
			WritableCellFormat format=new WritableCellFormat(new WritableFont(WritableFont.ARIAL,10,WritableFont.BOLD));
		if (style.equals("Heading1")){
			writableWorkbook.setColourRGB(Colour.CORAL,200,200,200);
			format.setBackground(Colour.CORAL);
		}else if (style.equals("Heading2")){
			writableWorkbook.setColourRGB(Colour.AQUA,232,232,232);
			format.setBackground(Colour.AQUA);
		}			
		putData(sheet, column, row, name);
		writableWorkbook.getSheet(sheet).getWritableCell(column, row).setCellFormat(format);

		} catch (RowsExceededException e) {
			e.printStackTrace();
		} catch (WriteException e) {
			e.printStackTrace();
		}
	}
	
	public void groupRows(String sheet,int srtRow,int endRow){
		try {
			writableWorkbook.getSheet(sheet).setRowGroup(srtRow, endRow, true);
			
		} catch (RowsExceededException e) {
			e.printStackTrace();
		} catch (WriteException e) {			
			e.printStackTrace();
		}
	}
	
	public CellFormat getHeaderFormat(String sheet){
		
		return workbook.getSheet(sheet).getCell(0,0).getCellFormat();
	}
	
	public void saveExcel() throws WriteException{
		try {
			writableWorkbook.write();
			writableWorkbook.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
