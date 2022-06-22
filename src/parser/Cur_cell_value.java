package parser;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.DateUtil;


public class Cur_cell_value implements Cell_value_i{// ����� ��������� �������� ������ Excel �����
	//������ �������� ��������� ������
	//cl - ��������� ������
	//row_num - ����� ������
	//cell_num - ����� ������
	public Object getCellValue(Cell cl, int row_num, int cell_num)throws Exception{
		Object cell_value="";// �������� ������
		if (cl==null) {// ���� ������ ������ - ����������� ����������
			throw new CellNullException(row_num,cell_num);
		}
		//��������� �������� ������ � ����������� �� ��� ����
		//�.�. � ����� ����� �� �������� ������ � ������ �������,
		//������ � ��������, ����� ������������ ������ ��� ���� ������
		switch(cl.getCellType()) {
			case NUMERIC:{
				if (DateUtil.isCellDateFormatted(cl)) {//���� ��������� ������ - ����
					cell_value =cl.getDateCellValue();
				}else {
					cell_value = (int)cl.getNumericCellValue();//���� ��������� ������ - �����
				}
				break;
			}
			case STRING:{
				cell_value = cl.getStringCellValue();//���� ��������� ������ - ������
				break;
			}
			default://���� ���������� ��� - ����������� ����������
			{
				throw new ExcelCellFormatException(row_num,cell_num);
			}
		}
		return cell_value;	
	}
}
