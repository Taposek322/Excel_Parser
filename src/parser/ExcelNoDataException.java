package parser;

public class ExcelNoDataException extends Exception{//Исключение отсутсвтие данных в файле
	private static final long serialVersionUID = 1L;
	ExcelNoDataException(){
		super("Error. Excel file doesn`t have data");
	}
}
