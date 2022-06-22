package parser;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class Cur_query_select implements Query_select_i{// ����� ���������� ������� "select"
	protected PreparedStatement st;// ������ � ���� ������
	
	//��������� ������� ������ � ����������� �����
	//con - ����������� � ���� ������
	//date - ����
	public ResultSet select(Connection con,Date date) throws Exception {
		//������ "select" � ������� data_t
		PreparedStatement st = con.prepareStatement("Select fact_qliq_data1,fact_qliq_data2,fact_qoil_data1,"
				+ "fact_qoil_data2,forecast_qliq_data1,forecast_qliq_data2,forecast_qoil_data1,forecast_qoil_data2 "
				+ "from data_t WHERE date = ?;");
		st.setDate(1, date);
		ResultSet res = st.executeQuery(); 
		return res;
	}
	
	//�������� �������
	public void statement_close() throws Exception {
		if(st!=null) {
			st.close();
		}
	}

}
