package main.java.br.com.arida.ufc.mydbaasmonitor.util;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import main.java.br.com.arida.ufc.mydbaasmonitor.common.entity.metric.Machine;

public class Main {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		Machine cpu = new Machine();
		
		List<Field> fields = new ArrayList<Field>();		
		//Gets the class of the metric
		Class<?> clazz = cpu.getClass();		
		//Gets fields from the class
		fields.addAll(Arrays.asList(clazz.getDeclaredFields()));
		
		for (Field field : fields) {
			System.out.println(field.getType().getSimpleName());
		}

	}

}
