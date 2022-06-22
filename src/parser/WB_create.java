package parser;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.io.IOException;

import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class WB_create {//Класс для открытия xlsx файла
	protected XSSFWorkbook workBook;//Экземпляр класса для работы с xlsx файлом
	protected XSSFSheet sheet;//Экземпляр класса страницы xlsx файла
	
	//Открытие xlsx файла
	//filename - путь к xlsx файлу
	WB_create(String filename) throws Exception {
		int index = filename.lastIndexOf(".");//Находим индекс последнего вхождения символа "."
		if(index == -1) {//если символ "." отсутсвтует - выбрасываем исключение
			throw new FileNotFoundException("Error. Wrong path to xlsx file");
		}
		String exst = filename.substring(index);//получаем подстроку с расширением файла
		if (!exst.equalsIgnoreCase(".xlsx")) {//если расширение не "xlsx" - выбрасываем исключение
			throw new IllegalArgumentException ("Error. Wrong extension. File might must xlsx extension");
		}
		FileInputStream xlsx_file = new FileInputStream(new File(filename));// создаём файловый поток
		workBook = new XSSFWorkbook(xlsx_file);//Открываем xlsx файл
		sheet = workBook.getSheetAt(0);// получем первый листр файла
		if(sheet==null) {// если лист отсутсвует - выбрасываем исключение.
    		throw new SheetException();
    	}
	}
	
	//Возврат класса открытого xlsx файла и первого листа
	public ArrayList<Object> get_wb_and_sheet() {
		ArrayList<Object> wb_list = new ArrayList<Object>(2);
		wb_list.add(0,workBook);
		wb_list.add(1,sheet);
		return wb_list;
	}
	
	//Закрытие файла
	public void wb_close() throws IOException{
		if (workBook!=null) {
			workBook.close();
		}
	}

}
