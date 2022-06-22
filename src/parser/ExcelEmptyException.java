package parser;

public class ExcelEmptyException extends Exception{//Исключение пустого Excel файла
	private static final long serialVersionUID = 1L;
	ExcelEmptyException(){
		super("Error. Excel file is empty");
	}
}
