package parser;

import org.apache.poi.ss.usermodel.Cell;

public interface Cell_value_i {//��������� ��������� ������ excel �����
	
	//������ �������� ��������� ������
	//cl - ��������� ������
	//row_num - ����� ������
	//cell_num - ����� ������
	Object getCellValue(Cell cl,int row_num, int cell_num)throws Exception;
	
}
