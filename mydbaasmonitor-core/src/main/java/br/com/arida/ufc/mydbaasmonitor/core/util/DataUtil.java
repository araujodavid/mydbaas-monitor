package main.java.br.com.arida.ufc.mydbaasmonitor.core.util;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public abstract class DataUtil {
    
	public static Date convertStringToDate(String data) {   
		if (data == null || data.equals(""))   
			return null;   
	           
	        Date date = null;   
	        try {   
	            DateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");   
	            date = (java.util.Date)formatter.parse(data);   
	        } catch (ParseException e) {               
	            System.err.println(e.getMessage());
	        }   
	        return date;   
	}
	  
	public static String convertDateToString(Date data) {   
	        if (data == null)   
	            return null;   
	           
	        String date = null;   
	        try {   
	            DateFormat formatter = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss"); 
	            date = formatter.format(data);   
	        } catch (Exception e) {               
	            System.err.println(e.getMessage());
	        }   
	        return date;   
	}
	
	public static String convertDateToStringAPI(Date data) {   
        if (data == null)   
            return null;   
           
        String date = null;   
        try {   
            DateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss"); 
            date = formatter.format(data);   
        } catch (Exception e) {               
            System.err.println(e.getMessage());
        }   
        return date;   
}
	
	public static String convertDateToStringUI(Date data) {   
        if (data == null)   
            return null;   
           
        String date = null;   
        try {   
            DateFormat formatter = new SimpleDateFormat("dd/MM/yyyy"); 
            date = formatter.format(data);   
        } catch (Exception e) {               
            System.err.println(e.getMessage());
        }   
        return date;   
	}	
}

