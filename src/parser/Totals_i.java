package parser;

import java.sql.Connection;
import java.sql.Date;
import java.util.Map;

public interface Totals_i {//»нтерфейс расчЄта тотала
	
	// ќпределение расчЄтного тотала
	// select - экземпл€р класса выполнени€ запроса "select"
	//con - подключение к забе данных
	//date - дата
	Map<Integer,Object> get_total(Query_select_i select, Connection con,Date date) throws Exception;
}
