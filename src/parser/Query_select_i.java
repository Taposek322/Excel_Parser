package parser;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Date;

public interface Query_select_i {//Интерфейс получения выборки по запросу "select"
	
	//Получение выборки данных с определённой датой
	//con - подключение к базе данных
	//date - дата
	ResultSet select(Connection con,Date date) throws Exception;
	
	//Закрытие запроса
	public void statement_close() throws Exception;
}
