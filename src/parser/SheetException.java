package parser;

public class SheetException extends Exception {//Исключение при отсутсвии листа в Excel файле
	private static final long serialVersionUID = 1L;
	SheetException(){
		super("Error. Excel file doesnt contain first sheet");
	}

}
