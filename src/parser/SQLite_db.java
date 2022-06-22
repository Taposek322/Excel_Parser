package parser;


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class SQLite_db implements DB{// ����� �� SQLite
	
	protected Connection con;//��������� ����������� � ��
	protected String sql_con = "jdbc:sqlite:";// ����� ������ ����������� � ��
	
	//����������� �������� ����������� � ��
	//db_name - ���� � ��
	SQLite_db(String db_name) throws SQLException{
		con = DriverManager.getConnection(sql_con+db_name);
	}

	//�������� ����������� � ���� ������
	public void con_close() throws SQLException{
		if (con!=null) {
			con.close();
		}
	}
	
	//������� ����������� � ���� ������
	public Connection get_connection(){
		return con;
	}
	
}