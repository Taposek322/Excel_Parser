package parser;

public class ExcelCellCountException extends Exception{//���������� ���������� ����� � ������
	private static final long serialVersionUID = 1L;
	ExcelCellCountException(int row_num){
		super("Error.Cell count in "+row_num+" row isn`t 11");
	}

}
