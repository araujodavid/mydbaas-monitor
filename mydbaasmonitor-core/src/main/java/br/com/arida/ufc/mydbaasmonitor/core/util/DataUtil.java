package main.java.br.com.arida.ufc.mydbaasmonitor.util;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

public abstract class DataUtil {
    
	// Metodos de manipulacao de datas para JDBC
	public static Date converteStringParaDate(String data) {   
		if (data == null || data.equals(""))   
			return null;   
	           
	        Date date = null;   
	        try {   
	            DateFormat formatter = new SimpleDateFormat("yyyy.MM.dd HH.mm.ss");   
	            date = (java.util.Date)formatter.parse(data);   
	        } catch (ParseException e) {               
	            System.err.println(e.getMessage());
	        }   
	        return date;   
	}
	  
	public static String converteDateParaString(Date data) {   
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
    
	public static String formataDataJDBC(String data){
	   return data.substring(6,10) + "-" + data.substring(3,5) + "-" + data.substring(0,2);    
	}
	  
	public static java.sql.Date DataFormatadaToDataSql(String data){
		   return java.sql.Date.valueOf(data.substring(6,10) + "-" + data.substring(3,5) + "-" + data.substring(0,2));    
	}
	  
	public static String formataDataHoraJDBC(String data_hora){
	      return data_hora.substring(6,10) + "-" + data_hora.substring(3,5) + "-" +
	             data_hora.substring(0,2) + " " + data_hora.substring(11,19);
	}
	  
	public static String formataDataHoraJDBC(String data, String hora){
	     return data.substring(6,10) + "-" + data.substring(3,5) + "-" + data.substring(0,2) + " " + hora;
	}
	  
	public static long adicionarHora(long horario, int qtd_hora){
		return (horario + 1000 * 60 * (60*qtd_hora) * 1);
	}
	
	public static long adicionarMinuto(long horario, int qtd_min){
		return (horario + 1000 * 60 * qtd_min * 1);
	}
	
	public static int idade(String dataNasc, String pattern){
	    DateFormat sdf = new SimpleDateFormat(pattern);
	    Date nasc = null;
	    
	    try{
	        nasc = sdf.parse(dataNasc);
	    }catch(Exception exc){
	        System.out.println("Exception: " + exc.getMessage());
	    }
	    
	    Calendar diaNasc = new GregorianCalendar();
	    diaNasc.setTime(nasc);
	    
	    Calendar hoje = Calendar.getInstance();
	    int idade = hoje.get(Calendar.YEAR) - diaNasc.get(Calendar.YEAR);
	    
	    diaNasc.add(Calendar.YEAR, idade);
	    
	    if (hoje.before(diaNasc)){
	        idade--;
	    }
	    
	    return idade;
	}
	
	public static int calculaQuantidadeDias(String vinicio, String vtermino, String pattern){
	    DateFormat sdf = new SimpleDateFormat(pattern);
	    Date inicio = null;
	    Date termino = null;
	    
	    try{
	        inicio = sdf.parse(vinicio);
	        termino = sdf.parse(vtermino);
	    }catch(Exception exc){
	        System.out.println("Exception: " + exc.getMessage());
	    }
	    
	    Calendar inicioCal = new GregorianCalendar();
	    Calendar terminoCal = new GregorianCalendar();
	    
	    inicioCal.setTime(inicio);
	    terminoCal.setTime(termino);
	    
	    int dias = terminoCal.get(Calendar.DATE) - inicioCal.get(Calendar.DATE);
	    
	    return dias;
	}
	
	public static boolean isDataValida(String date)
	{
	   // set date format, this can be changed to whatever format
	   // you want, MM-dd-yyyy, MM.dd.yyyy, dd.MM.yyyy etc.
	   // you can read more about it here:
	   // http://java.sun.com/j2se/1.4.2/docs/api/index.html
	   
	   SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
	   
	   // declare and initialize testDate variable, this is what will hold
	   // our converted string
	   
	   Date testDate = null;

	   // we will now try to parse the string into date form
	   try
	   {
	     testDate = sdf.parse(date);
	   }

	   // if the format of the string provided doesn't match the format we 
	   // declared in SimpleDateFormat() we will get an exception

	   catch (ParseException e)
	   {
	     return false;
	   }

	   // dateformat.parse will accept any date as long as it's in the format
	   // you defined, it simply rolls dates over, for example, december 32 
	   // becomes jan 1 and december 0 becomes november 30
	   // This statement will make sure that once the string 
	   // has been checked for proper formatting that the date is still the 
	   // date that was entered, if it's not, we assume that the date is invalid

	   if (!sdf.format(testDate).equals(date)) 
	   {
	     return false;
	   }
	   
	   // if we make it to here without getting an error it is assumed that
	   // the date was a valid one and that it's in the proper format

	   return true;

	} // end isValidDate
	
}

