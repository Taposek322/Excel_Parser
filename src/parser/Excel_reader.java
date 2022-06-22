package parser;

import java.util.ArrayList;
import java.util.Map;

interface Excel_reader{// интерфейс чтения данных из Excel
	
	//Считывание данных из Excel файла
	Map<Integer,ArrayList<Object>> read_file() throws Exception;
	
	//Возврат числа строк данных
	int get_row_count();
}
