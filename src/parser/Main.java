package parser;

import java.io.File;
import java.io.FileInputStream;
import java.util.ArrayList;
import java.util.Map;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.Properties;
import java.sql.Date;

import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;

public class Main {

	public static void main(String[] args) {
		int cmd=-1;//�������
		try (Scanner in = new Scanner(System.in)){//������ ��������� ������ ��� ���������� ������ �� �������
			Properties prop = new Properties();//������ ��������� ������ ��� ���������� ������ �� ������ �����
			prop.load(new FileInputStream(new File("config\\db.ini")));// �������� ���� � ���� ������
			DB database = new SQLite_db(prop.getProperty("DB_path"));// ������ ��������� ������ ��� ������ � SQLite
			do {
				System.out.println("Choose operation:");
				System.out.println("1)Parse data form excel to database");
				System.out.println("2)Get total;");
				System.out.println("0)End program;");
				cmd = in.nextInt();
				switch(cmd) {
					case 1://������
					{
						System.out.println("Insert path to excel file:");
						String filepath = in.next();//��������� ���� � �����
						WB_create wb = new WB_create(filepath);//������ ��������� ������ ��� ������ � Excel ������
						Cell_value_i cll_v = new Cur_cell_value();//������ ��������� ������ ��� ��������� ����� Excel
						ArrayList<Object> w = wb.get_wb_and_sheet();
						Cur_reader rd = new Cur_reader((Workbook)w.get(0), (Sheet)w.get(1),cll_v);//������ ��������� ������ ��� ���������� ������ �� Excel
						Query_ins_i qr = new Cur_query_ins();// ������ ��������� ������ ��� ���������� ������ � ���� ������
						Parser p = new Parser(database,rd,qr);//������ ���������� ������ �������
						p.parse();//�������� ����� ���������� ������ � ��
						System.out.println("Data added successfully to database");
						wb.wb_close();//��������� Excel ����
						break;
					}
					case 2://����� ������ � ����������� �����
					{
						System.out.println("Insert date in format \"yyyy-mm-dd\"");
						String date = in.next();// ��������� ����
						Pattern reg = Pattern.compile("^\\d{4}-\\d{2}-\\d{2}$");// ���������, ��� ���� � ������� "yyyy-mm-dd"
						Matcher m = reg.matcher(date);
						if(!m.find()) {//���� ������ ���� �� ��������� "yyyy-mm-dd" - ����������� ����������
							throw new WrongDateFormatException();
						}
						Totals_i ttl_class = new Cur_totals();//������ ��������� ������ ��� ����������� ���������� ������
						Query_select_i sel = new Cur_query_select();//������ ��������� ������ ��� ��������� ������� �� ������� "select"
						//�������� ��������� ������ � ���������� ����� � ������� � ���� �������
						Map<Integer,Object> totals = ttl_class.get_total(sel,database.get_connection(),	Date.valueOf(date));
						if ((int)totals.get(4)==0) {//���� � ������� ��� �����
							System.out.println("There is no data with entered date");
						}else {// ���� � ������� ���� ������ - ������� �����
							System.out.println("Fact qliq:"+(int)totals.get(0));
							System.out.println("Fact qoil:"+(int)totals.get(1));
							System.out.println("Forecast qliq:"+(int)totals.get(2));
							System.out.println("Forecast qoil:"+(int)totals.get(3));
						}
						break;
					}
					case 0://����� �� ���������
					{
						break;
					}
					default:
					{
						System.out.println("Wrong operation");
					}
				}
			}while(cmd!=0);
			database.con_close();//��������� ���������� � ��
		}catch (Exception e) {
			System.out.println(e.getMessage());
		}
		
		
		
	}
}
