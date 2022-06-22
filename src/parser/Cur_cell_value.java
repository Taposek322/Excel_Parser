package parser;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.DateUtil;


public class Cur_cell_value implements Cell_value_i{// Класс обработки значения ячейки Excel файла
	//Возрат значения считанной ячейки
	//cl - считанная ячейка
	//row_num - номер строки
	//cell_num - номер ячейки
	public Object getCellValue(Cell cl, int row_num, int cell_num)throws Exception{
		Object cell_value="";// Значение ячейки
		if (cl==null) {// Если ячейка пустая - выбрасываем исключение
			throw new CellNullException(row_num,cell_num);
		}
		//Считываем значение ячейки в зависимости от его типа
		//Т.к. в нашем файле мы работаем только с целыми числами,
		//датами и строками, будет обрабатывать только эти типы данных
		switch(cl.getCellType()) {
			case NUMERIC:{
				if (DateUtil.isCellDateFormatted(cl)) {//Если считанная ячейка - дата
					cell_value =cl.getDateCellValue();
				}else {
					cell_value = (int)cl.getNumericCellValue();//Если считанная ячейка - число
				}
				break;
			}
			case STRING:{
				cell_value = cl.getStringCellValue();//Если считанная ячейка - строка
				break;
			}
			default://если совпадений нет - выбрасываем исключение
			{
				throw new ExcelCellFormatException(row_num,cell_num);
			}
		}
		return cell_value;	
	}
}