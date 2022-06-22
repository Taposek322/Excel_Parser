package parser;

import org.apache.poi.ss.usermodel.Cell;

public interface Cell_value_i {//Интерфейс обработки ячейки excel файла
	
	//Возрат значения считанной ячейки
	//cl - считанная ячейка
	//row_num - номер строки
	//cell_num - номер ячейки
	Object getCellValue(Cell cl,int row_num, int cell_num)throws Exception;
	
}
