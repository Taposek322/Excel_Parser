package parser;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.io.IOException;

import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class WB_create {//����� ��� �������� xlsx �����
	protected XSSFWorkbook workBook;//��������� ������ ��� ������ � xlsx ������
	protected XSSFSheet sheet;//��������� ������ �������� xlsx �����
	
	//�������� xlsx �����
	//filename - ���� � xlsx �����
	WB_create(String filename) throws Exception {
		int index = filename.lastIndexOf(".");//������� ������ ���������� ��������� ������� "."
		if(index == -1) {//���� ������ "." ����������� - ����������� ����������
			throw new FileNotFoundException("Error. Wrong path to xlsx file");
		}
		String exst = filename.substring(index);//�������� ��������� � ����������� �����
		if (!exst.equalsIgnoreCase(".xlsx")) {//���� ���������� �� "xlsx" - ����������� ����������
			throw new IllegalArgumentException ("Error. Wrong extension. File might must xlsx extension");
		}
		FileInputStream xlsx_file = new FileInputStream(new File(filename));// ������ �������� �����
		workBook = new XSSFWorkbook(xlsx_file);//��������� xlsx ����
		sheet = workBook.getSheetAt(0);// ������� ������ ����� �����
		if(sheet==null) {// ���� ���� ���������� - ����������� ����������.
    		throw new SheetException();
    	}
	}
	
	//������� ������ ��������� xlsx ����� � ������� �����
	public ArrayList<Object> get_wb_and_sheet() {
		ArrayList<Object> wb_list = new ArrayList<Object>(2);
		wb_list.add(0,workBook);
		wb_list.add(1,sheet);
		return wb_list;
	}
	
	//�������� �����
	public void wb_close() throws IOException{
		if (workBook!=null) {
			workBook.close();
		}
	}

}
