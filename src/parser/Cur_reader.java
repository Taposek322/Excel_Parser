package parser;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;


public class Cur_reader implements Excel_reader {// ����� ������ ������ �� Excel
	
	protected Workbook wb;//��������� ������ ��� ������ � Excel
	protected Sheet sheet;//��������� ������ �������� Excel
	protected int row_num;// ����� ����� � ������� Excel
	protected Cell_value_i cll_value;//��������� ������ ��������� ������ Excel
	
	//�����������
	//bk - ��������� ������ ��� ������ � Excel
	//sh - ��������� ������ �������� Excel
	//vl - ��������� ������ ��������� ������ Excel
	Cur_reader(Workbook bk, Sheet sh,Cell_value_i vl) throws Exception{
		if(bk==null) {// ���� ��������� ������ ��� ������ � Excel - null
			throw new IllegalArgumentException("Error. Wrong exemplar of Workbook");//����������� ����������
		}
		wb=bk;
		if(sh==null) {// ���� ��������� ������ ��� �������� Excel - null
    		throw new IllegalArgumentException("Error. Wrong exemplar of \"Sheet\"");//����������� ����������
    	}
		sheet=sh;
    	row_num= sheet.getLastRowNum();//�������� ���������� �����
    	if (row_num==-1) {// ���� ������ ����������� - ����������� ����������
    		throw new ExcelEmptyException();
    	}
        Iterator<Row> row_it = sheet.iterator();
        for (int i = 0;i<3;i++) {// ���������, ��� ������������ ������ 3 ������ ���������
        	if(row_it.hasNext()) {
        		row_it.next();
        	}
        	else {// ���� �� ������� ����� ��������� - ����������� ����������
        		throw new ExcelHeaderFormatException();
        	}
        }
        if(!row_it.hasNext()) {// ���� ����� ��������� ������ ��� ����� - ����������� ����������
        	//�� ���������� ������
        	throw new ExcelNoDataException();
        }
        row_num-=2;// �� ��������� ������ 3 ������ �������� Excel
        cll_value = vl;
	}
	
	//������� ����� ����� ������
	public int get_row_count() {
		return row_num;
	}
  
	//���������� ������ �� Excel �����
	public Map<Integer,ArrayList<Object>> read_file() throws Exception{
		Map<Integer,ArrayList<Object>> datas = new HashMap<Integer,ArrayList<Object>>();//������� <����� ������, ������>
		for(int i=0;i<row_num;i++) {
			Row r = sheet.getRow(i+3);//�������� ������ Excel �����
			if (r==null) {//���� ��� ����� - ����������� ����������
				throw new ExcelRowNullException();
			}
			ArrayList<Object> lst = new ArrayList<Object>(11);//�������������� ArrayList ��� �������� ������ ������
			int col_num = r.getLastCellNum();//�������� ����� ����� � ������
			if (col_num!=11) {//���� ��� �� ����� 11 - ����������� ����������
				throw new ExcelCellCountException(i+4);
			}
			for(int j=0;j<col_num;j++) {//�������� �� ���� ������� � ��������� ������ � ArrayList
				lst.add(j,cll_value.getCellValue(r.getCell(j),i+4,j+1));
			}
			datas.put(i,lst);//��������� ArrayList � �������
		}
		return datas;
	}
}