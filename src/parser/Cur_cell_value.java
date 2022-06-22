package parser;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.DateUtil;


public class Cur_cell_value implements Cell_value_i{//  ласс обработки значени€ €чейки Excel файла
	//¬озрат значени€ считанной €чейки
	//cl - считанна€ €чейка
	//row_num - номер строки
	//cell_num - номер €чейки
	public Object getCellValue(Cell cl, int row_num, int cell_num)throws Exception{
		Object cell_value="";// «начение €чейки
		if (cl==null) {// ≈сли €чейка пуста€ - выбрасываем исключение
			throw new CellNullException(row_num,cell_num);
		}
		//—читываем значение €чейки в зависимости от его типа
		//“.к. в нашем файле мы работаем только с целыми числами,
		//датами и строками, будет обрабатывать только эти типы данных
		switch(cl.getCellType()) {
			case NUMERIC:{
				if (DateUtil.isCellDateFormatted(cl)) {//≈сли считанна€ €чейка - дата
					cell_value =cl.getDateCellValue();
				}else {
					cell_value = (int)cl.getNumericCellValue();//≈сли считанна€ €чейка - число
				}
				break;
			}
			case STRING:{
				cell_value = cl.getStringCellValue();//≈сли считанна€ €чейка - строка
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
