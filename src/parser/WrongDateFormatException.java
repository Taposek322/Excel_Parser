package parser;

public class WrongDateFormatException extends Exception{//���������� ������������� ������� ����
	private static final long serialVersionUID = 1L;
	WrongDateFormatException(){
		super("Error. Wrong date format");
	}

}
