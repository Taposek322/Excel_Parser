package parser;

import java.sql.Connection;
import java.sql.Date;
import java.sql.ResultSet;
import java.util.HashMap;
import java.util.Map;

public class Cur_totals implements Totals_i {// Класс определения "расчётного тотала"

	// Определение расчётного тотала
	// select - экземпляр класса выполнения запроса "select"
	//con - подключение к забе данных
	//date - дата
	public Map<Integer, Object> get_total(Query_select_i select, Connection con,Date date) throws Exception{
		Map<Integer,Object> totals = new HashMap<Integer,Object>();// словарь расчётного тотала
		//Ключ "0" - fact qliq
		//Ключ "1" - fact qoil
		//Ключ "2" - forecast qliq
		//Ключ "3" - forecast qoil
		//Ключ "4" - длина выборки 
		for(int i = 0; i<5;i++) {
			totals.put(i, 0);
		}
		ResultSet select_sample = select.select(con, date);//получаем выборку запроса "select"
		int row_count = 0;
		while (select_sample.next()) {//пока в выборке есть строки - подсчитываем тотал
			int j=0;
			for(int i=1;i<9;i++) {
				int var = (int)totals.get(j);
				var+=select_sample.getInt(i);
				totals.put(j, var);
				if(i%2==0) {
					j++;
				}
			}
			row_count++;
		}
		totals.put(4, row_count);// добавляем число строк в словарь
		select.statement_close();
		return totals;
	}

}