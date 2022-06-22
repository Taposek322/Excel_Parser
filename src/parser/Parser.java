package parser;


import java.lang.Exception;
import java.util.ArrayList;
import java.util.Map;


public class Parser {// ласс парсера
	
	protected DB database;//Ёкземпл€р класса дл€ работы с базой данных
	protected Excel_reader rdr;//Ёкземпл€р класса дл€ считывани€ данных из Excel
	protected Query_ins_i qr;//Ёкземпл€р класса дл€ вставки данных в бд
		
    // онструктор	
	//databs - Ёкземпл€р класса дл€ работы с базой данных
	//rd - Ёкземпл€р класса дл€ считывани€ данных из Excel
	//qry- Ёкземпл€р класса дл€ вставки данных в бд
	Parser(DB databs, Excel_reader rd,Query_ins_i qry){
		database=databs;
		rdr = rd;
		qr=qry;
	}
		
	//ѕарсинг
	public void parse() throws Exception{
		Map<Integer, ArrayList<Object>> datas = rdr.read_file();//считываем все данных из Excel
		int row_count = rdr.get_row_count();//получаем количество считанных строк
		for(int i=0;i<row_count;i++) {
			qr.insert(database.get_connection(),datas.get(i),i+4);//построчно добавл€ем в бд данные 
		}
	}
	
}




