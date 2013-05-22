package main.java.br.com.arida.ufc.mydbaasmonitor.core.util;

/**
 * Class to translate the Java types to SQL types.
 * @author David Araújo
 * @version 2.0
 * @since April 2, 2013 
 */
public class TypeTranslater {

	/**
	 * Method that takes a Java type and returns the equivalent in SQL
	 * @param typeClass
	 * @return a String with the SQL type
	 */
	public static String getSQLType(Class<?> typeClass) {
		String sqlType = null;
		
		switch (typeClass.getSimpleName()) {
			case "String":
				sqlType = "text";
				break;
			case "double":
			case "Double":
			case "float":
			case "Float":
				sqlType = "double";
				break;
			case "int":
			case "Integer":
				sqlType = "integer";
				break;
			case "long":
				sqlType = "bigint";
				break;
		}
		
		return sqlType;		
	}
}
