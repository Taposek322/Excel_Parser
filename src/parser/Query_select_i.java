package parser;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Date;

public interface Query_select_i {//��������� ��������� ������� �� ������� "select"
	
	//��������� ������� ������ � ����������� �����
	//con - ����������� � ���� ������
	//date - ����
	ResultSet select(Connection con,Date date) throws Exception;
	
	//�������� �������
	public void statement_close() throws Exception;
}
