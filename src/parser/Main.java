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
		int cmd=-1;//команда
		try (Scanner in = new Scanner(System.in)){//Создаём экземпляр класса для считывание данных из консоли
			Properties prop = new Properties();//Создаём экземпляр класса для считывания данных из конфиг файла
			prop.load(new FileInputStream(new File("config\\db.ini")));// получаем путь к базе данных
			DB database = new SQLite_db(prop.getProperty("DB_path"));// Создаём экземпляр класса для работы с SQLite
			do {
				System.out.println("Choose operation:");
				System.out.println("1)Parse data form excel to database");
				System.out.println("2)Get total;");
				System.out.println("0)End program;");
				cmd = in.nextInt();
				switch(cmd) {
					case 1://Парсер
					{
						System.out.println("Insert path to excel file:");
						String filepath = in.next();//считываем путь к файлу
						WB_create wb = new WB_create(filepath);//Создаём экземпляр класса для работы с Excel файлом
						Cell_value_i cll_v = new Cur_cell_value();//Создаём экземпляр класса для обработки ячеек Excel
						ArrayList<Object> w = wb.get_wb_and_sheet();
						Cur_reader rd = new Cur_reader((Workbook)w.get(0), (Sheet)w.get(1),cll_v);//Создаём экземпляр класса для считывания данных из Excel
						Query_ins_i qr = new Cur_query_ins();// Создаём экземпляр класса для добавление данных в базу данных
						Parser p = new Parser(database,rd,qr);//Создаём экземплеяр класса парсера
						p.parse();//Вызываем метод добавления данных в бд
						System.out.println("Data added successfully to database");
						wb.wb_close();//Закрываем Excel файл
						break;
					}
					case 2://Вывод тотала с определённой датой
					{
						System.out.println("Insert date in format \"yyyy-mm-dd\"");
						String date = in.next();// Считываем дату
						Pattern reg = Pattern.compile("^\\d{4}-\\d{2}-\\d{2}$");// Проверяем, что дата в формате "yyyy-mm-dd"
						Matcher m = reg.matcher(date);
						if(!m.find()) {//если формат даты не совпадает "yyyy-mm-dd" - выбрасываем исключение
							throw new WrongDateFormatException();
						}
						Totals_i ttl_class = new Cur_totals();//Создаём экземпляр класса для определения расчётного тотала
						Query_select_i sel = new Cur_query_select();//Создаём экземпляр класса для получения выборки по запросу "select"
						//получаем тотальный расчёт и количество строк в запросе в виде словаря
						Map<Integer,Object> totals = ttl_class.get_total(sel,database.get_connection(),	Date.valueOf(date));
						if ((int)totals.get(4)==0) {//если в запросе нет строк
							System.out.println("There is no data with entered date");
						}else {// если в запросе есть строки - выводим тотал
							System.out.println("Fact qliq:"+(int)totals.get(0));
							System.out.println("Fact qoil:"+(int)totals.get(1));
							System.out.println("Forecast qliq:"+(int)totals.get(2));
							System.out.println("Forecast qoil:"+(int)totals.get(3));
						}
						break;
					}
					case 0://выход из программы
					{
						break;
					}
					default:
					{
						System.out.println("Wrong operation");
					}
				}
			}while(cmd!=0);
			database.con_close();//закрываем соединение с бд
		}catch (Exception e) {
			System.out.println(e.getMessage());
		}
		
	}
}