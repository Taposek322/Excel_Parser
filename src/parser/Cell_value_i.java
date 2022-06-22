package parser;

import org.apache.poi.ss.usermodel.Cell;

public interface Cell_value_i {//»нтерфейс обработки €чейки excel файла
	
	//¬озрат значени€ считанной €чейки
	//cl - считанна€ €чейка
	//row_num - номер строки
	//cell_num - номер €чейки
	Object getCellValue(Cell cl,int row_num, int cell_num)throws Exception;
	
}
