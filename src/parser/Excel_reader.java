package parser;

import java.util.ArrayList;
import java.util.Map;

interface Excel_reader{// ��������� ������ ������ �� Excel
	
	//���������� ������ �� Excel �����
	Map<Integer,ArrayList<Object>> read_file() throws Exception;
	
	//������� ����� ����� ������
	int get_row_count();
}