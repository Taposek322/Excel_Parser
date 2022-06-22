package parser;


import java.lang.Exception;
import java.util.ArrayList;
import java.util.Map;


public class Parser {//����� �������
	
	protected DB database;//��������� ������ ��� ������ � ����� ������
	protected Excel_reader rdr;//��������� ������ ��� ���������� ������ �� Excel
	protected Query_ins_i qr;//��������� ������ ��� ������� ������ � ��
		
    //�����������	
	//databs - ��������� ������ ��� ������ � ����� ������
	//rd - ��������� ������ ��� ���������� ������ �� Excel
	//qry- ��������� ������ ��� ������� ������ � ��
	Parser(DB databs, Excel_reader rd,Query_ins_i qry){
		database=databs;
		rdr = rd;
		qr=qry;
	}
		
	//�������
	public void parse() throws Exception{
		Map<Integer, ArrayList<Object>> datas = rdr.read_file();//��������� ��� ������ �� Excel
		int row_count = rdr.get_row_count();//�������� ���������� ��������� �����
		for(int i=0;i<row_count;i++) {
			qr.insert(database.get_connection(),datas.get(i),i+4);//��������� ��������� � �� ������ 
		}
	}
	
}




