package parser;

public class ExcelEmptyException extends Exception{//���������� ������� Excel �����
	private static final long serialVersionUID = 1L;
	ExcelEmptyException(){
		super("Error. Excel file is empty");
	}
}
