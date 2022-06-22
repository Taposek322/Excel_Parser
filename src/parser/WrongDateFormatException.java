package parser;

public class WrongDateFormatException extends Exception{//Исключение неправильного формата даты
	private static final long serialVersionUID = 1L;
	WrongDateFormatException(){
		super("Error. Wrong date format");
	}

}