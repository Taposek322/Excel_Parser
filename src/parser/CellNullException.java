package parser;

public class CellNullException extends Exception{//���������� ������ ������ � Excel �����
	
	private static final long serialVersionUID = 1L;
	CellNullException(int row_num,int cell_num){
		super("Error. "+ cell_num +" cell in "+row_num+ " row is empty");
	}
}
