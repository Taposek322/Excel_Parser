package parser;

import java.sql.Connection;
import java.util.ArrayList;


public interface Query_ins_i {//Интерфейс вставки данных в бд
	//Вставка считанной строки в таблицу "data_t"
	//con - экземпляр подключения к базе данных
	// datas - данные строки, считанные из Excel файла
	//row_num - число строк в Excel файле
	void insert(Connection con, ArrayList<Object> datas,int row_num) throws Exception;
}