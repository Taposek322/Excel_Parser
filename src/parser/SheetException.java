package parser;

public class SheetException extends Exception {//���������� ��� ��������� ����� � Excel �����
	private static final long serialVersionUID = 1L;
	SheetException(){
		super("Error. Excel file doesnt contain first sheet");
	}

}
