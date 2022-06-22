package parser;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.PreparedStatement;
import java.sql.Date;
import java.util.ArrayList;


public class Cur_query_ins implements Query_ins_i {//Класс запроса вставки данных в таблицу
	//Вставка считанной строки в таблицу "data_t"
	//con - экземпляр подключения к базе данных
	// datas - данные строки, считанные из Excel файла
	//row_num - число строк в Excel файле
	public void insert(Connection con, ArrayList<Object> datas, int row_num) throws Exception {
		// запрос добавления данных в таблицу data_t
		PreparedStatement st = con.prepareStatement("INSERT INTO data_t (id,company_name,fact_qliq_data1,"
				+ "fact_qliq_data2, fact_qoil_data1,fact_qoil_data2,"
				+ "forecast_qliq_data1,forecast_qliq_data2,forecast_qoil_data1,"
				+ "forecast_qoil_data2,date) VALUES (?,?,?,?,?,?,?,?,?,?,?)");
		for (int i=0;i<11;i++) {// добавляем параметры в запрос
			switch (i){
				case 1:
				{
					st.setString(i+1, datas.get(i).toString());
					break;
				}
				case 10:
				{
					java.util.Date udt = (java.util.Date)datas.get(i);
					Date sqldt = new Date(udt.getTime());
					st.setDate(i+1,sqldt);
					break;
				}
				default:
				{
					st.setInt(i+1, (int)datas.get(i));
					break;
				}
			}	
		}
		int res=st.executeUpdate();
		if(res!=1) {//Если число добавленных строк не равно "1", выбрасываем исключение 
			throw new SQLException("Error. Cant add data from "+row_num+"row in database");
		}
		st.close();
	}
}
