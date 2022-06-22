package parser;

public class ExcelHeaderFormatException extends Exception{// Исключение неправильного количества строк в заголовке файла
	private static final long serialVersionUID = 1L;

	ExcelHeaderFormatException(){
			super("Error. Wrong format of excel`s header");
	}

}
