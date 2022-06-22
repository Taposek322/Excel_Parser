package parser;

public class ExcelCellFormatException extends Exception{//Исключение формата ячейки
	private static final long serialVersionUID = 1L;
	ExcelCellFormatException(int row_num, int cell_num){
		super("Error. "+cell_num+" cell in "+row_num+" row has wrong format");
	}

}
