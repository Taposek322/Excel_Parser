package parser;


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class SQLite_db implements DB{// класс бд SQLite
	
	protected Connection con;//Экземпляр подключения к бд
	protected String sql_con = "jdbc:sqlite:";// Часть строки подключения к бд
	
	//Конструктор создания подключения к бд
	//db_name - путь к бд
	SQLite_db(String db_name) throws SQLException{
		con = DriverManager.getConnection(sql_con+db_name);
	}

	//закрытие подключения к базе данных
	public void con_close() throws SQLException{
		if (con!=null) {
			con.close();
		}
	}
	
	//Возврат подключения к базе данных
	public Connection get_connection(){
		return con;
	}
	
}