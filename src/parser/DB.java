package parser;

import java.sql.Connection;
import java.sql.SQLException;


interface DB{// ��������� ���� ������
	
	//������� ����������� � ���� ������
	Connection get_connection();
	
	//�������� ����������� � ���� ������
	public void con_close() throws SQLException;
}