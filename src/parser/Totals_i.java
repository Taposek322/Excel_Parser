package parser;

import java.sql.Connection;
import java.sql.Date;
import java.util.Map;

public interface Totals_i {//��������� ������� ������
	
	// ����������� ���������� ������
	// select - ��������� ������ ���������� ������� "select"
	//con - ����������� � ���� ������
	//date - ����
	Map<Integer,Object> get_total(Query_select_i select, Connection con,Date date) throws Exception;
}
