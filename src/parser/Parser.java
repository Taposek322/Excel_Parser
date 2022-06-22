package parser;


import java.lang.Exception;
import java.util.ArrayList;
import java.util.Map;


public class Parser {//Класс парсера
	
	protected DB database;//Экземпляр класса для работы с базой данных
	protected Excel_reader rdr;//Экземпляр класса для считывания данных из Excel
	protected Query_ins_i qr;//Экземпляр класса для вставки данных в бд
		
    //Конструктор	
	//databs - Экземпляр класса для работы с базой данных
	//rd - Экземпляр класса для считывания данных из Excel
	//qry- Экземпляр класса для вставки данных в бд
	Parser(DB databs, Excel_reader rd,Query_ins_i qry){
		database=databs;
		rdr = rd;
		qr=qry;
	}
		
	//Парсинг
	public void parse() throws Exception{
		Map<Integer, ArrayList<Object>> datas = rdr.read_file();//считываем все данных из Excel
		int row_count = rdr.get_row_count();//получаем количество считанных строк
		for(int i=0;i<row_count;i++) {
			qr.insert(database.get_connection(),datas.get(i),i+4);//построчно добавляем в бд данные 
		}
	}
	
}
