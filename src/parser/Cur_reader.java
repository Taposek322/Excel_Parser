package parser;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;


public class Cur_reader implements Excel_reader {//  ласс чтени€ данных из Excel
	
	protected Workbook wb;//экземпл€р класса дл€ работы с Excel
	protected Sheet sheet;//экземпл€р класса страницы Excel
	protected int row_num;// число строк в таблице Excel
	protected Cell_value_i cll_value;//экземпл€р класса обработки €чейки Excel
	
	// онструктор
	//bk - экземпл€р класса дл€ работы с Excel
	//sh - экземпл€р класса страницы Excel
	//vl - экземпл€р класса обработки €чейки Excel
	Cur_reader(Workbook bk, Sheet sh,Cell_value_i vl) throws Exception{
		if(bk==null) {// если экземпл€р класса дл€ работы с Excel - null
			throw new IllegalArgumentException("Error. Wrong exemplar of Workbook");//выбрасываем исключение
		}
		wb=bk;
		if(sh==null) {// если экземпл€р класса дл€ страницы Excel - null
    		throw new IllegalArgumentException("Error. Wrong exemplar of \"Sheet\"");//выбрасываем исключение
    	}
		sheet=sh;
    	row_num= sheet.getLastRowNum();//получаем количество строк
    	if (row_num==-1) {// если строки отсутствуют - выбрасываем исключение
    		throw new ExcelEmptyException();
    	}
        Iterator<Row> row_it = sheet.iterator();
        for (int i = 0;i<3;i++) {// провер€ем, что присутствуют первые 3 строки заголовка
        	if(row_it.hasNext()) {
        		row_it.next();
        	}
        	else {// если не хватает строк заголовка - выбрасываем исключение
        		throw new ExcelHeaderFormatException();
        	}
        }
        if(!row_it.hasNext()) {// если после заголовка больше нет строк - выбрасываем исключение
        	//об отсутствии данных
        	throw new ExcelNoDataException();
        }
        row_num-=2;// не учитываем первые 3 строки загловка Excel
        cll_value = vl;
	}
	
	//¬озврат числа строк данных
	public int get_row_count() {
		return row_num;
	}
  
	//—читывание данных из Excel файла
	public Map<Integer,ArrayList<Object>> read_file() throws Exception{
		Map<Integer,ArrayList<Object>> datas = new HashMap<Integer,ArrayList<Object>>();//словарь <номер строки, данные>
		for(int i=0;i<row_num;i++) {
			Row r = sheet.getRow(i+3);//получаем строку Excel файла
			if (r==null) {//≈сли она пуста - выбрасываем исключение
				throw new ExcelRowNullException();
			}
			ArrayList<Object> lst = new ArrayList<Object>(11);//»нициализируем ArrayList дл€ хранени€ данных строки
			int col_num = r.getLastCellNum();//получаем число €чеек в строке
			if (col_num!=11) {//если оно не равно 11 - выбрасываем исключение
				throw new ExcelCellCountException(i+4);
			}
			for(int j=0;j<col_num;j++) {//проходим по всем €чейкам и добавл€ем данные в ArrayList
				lst.add(j,cll_value.getCellValue(r.getCell(j),i+4,j+1));
			}
			datas.put(i,lst);//ƒобавл€ем ArrayList в словарь
		}
		return datas;
	}
}