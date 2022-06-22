package parser;

public class ExcelRowNullException extends Exception{//Исключение пустой строки в файле
	private static final long serialVersionUID = 1L;
	ExcelRowNullException(){
		super("Error. Excel file has empty row");
	}

}