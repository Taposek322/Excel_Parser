package parser;

public class ExcelNoDataException extends Exception{//���������� ���������� ������ � �����
	private static final long serialVersionUID = 1L;
	ExcelNoDataException(){
		super("Error. Excel file doesn`t have data");
	}
}
