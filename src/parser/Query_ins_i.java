package parser;

import java.sql.Connection;
import java.util.ArrayList;


public interface Query_ins_i {//��������� ������� ������ � ��
	//������� ��������� ������ � ������� "data_t"
	//con - ��������� ����������� � ���� ������
	// datas - ������ ������, ��������� �� Excel �����
	//row_num - ����� ����� � Excel �����
	void insert(Connection con, ArrayList<Object> datas,int row_num) throws Exception;
}
