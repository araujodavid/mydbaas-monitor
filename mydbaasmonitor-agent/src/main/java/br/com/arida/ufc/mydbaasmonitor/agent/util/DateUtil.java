package main.java.br.com.arida.ufc.mydbaasmonitor.agent.util;

import java.text.Format;
import java.text.SimpleDateFormat;
import java.util.Date;

public class DateUtil {

	public static String formatDate(Date date) {
		String formatedDate;
		Format formatter;
		formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		formatedDate = formatter.format(date);
		return formatedDate;		
	}
}
