package parser;

public class ExcelHeaderFormatException extends Exception{// ���������� ������������� ���������� ����� � ��������� �����
	private static final long serialVersionUID = 1L;

	ExcelHeaderFormatException(){
			super("Error. Wrong format of excel`s header");
	}

}
