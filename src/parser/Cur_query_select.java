package parser;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class Cur_query_select implements Query_select_i{// Класс выполнения запроса "select"
	protected PreparedStatement st;// запрос к базе данных
	
	//Получение выборки данных с определённой датой
	//con - подключение к базе данных
	//date - дата
	public ResultSet select(Connection con,Date date) throws Exception {
		//запрос "select" к таблице data_t
		PreparedStatement st = con.prepareStatement("Select fact_qliq_data1,fact_qliq_data2,fact_qoil_data1,"
				+ "fact_qoil_data2,forecast_qliq_data1,forecast_qliq_data2,forecast_qoil_data1,forecast_qoil_data2 "
				+ "from data_t WHERE date = ?;");
		st.setDate(1, date);
		ResultSet res = st.executeQuery(); 
		return res;
	}
	
	//Закрытие запроса
	public void statement_close() throws Exception {
		if(st!=null) {
			st.close();
		}
	}

}