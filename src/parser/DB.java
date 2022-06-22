package parser;

import java.sql.Connection;
import java.sql.SQLException;


interface DB{// интерфейс базы данных
	
	//Возврат подключения к базе данных
	Connection get_connection();
	
	//закрытие подключения к базе данных
	public void con_close() throws SQLException;
}