package parser;

public class ExcelCellCountException extends Exception{//Исключение количества ячеек в строке
	private static final long serialVersionUID = 1L;
	ExcelCellCountException(int row_num){
		super("Error.Cell count in "+row_num+" row isn`t 11");
	}

}