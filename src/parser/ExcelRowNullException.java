package parser;

public class ExcelRowNullException extends Exception{//���������� ������ ������ � �����
	private static final long serialVersionUID = 1L;
	ExcelRowNullException(){
		super("Error. Excel file has empty row");
	}

}
