package lucas.webscraperproj.util;

import java.time.LocalDate;
import java.util.Calendar;
import java.util.TimeZone;

public class Datas {
	public static String dtAtual()
	{
		String dtEmLong;
    	LocalDate d = LocalDate.now();
    	
    	Calendar cal = Calendar.getInstance();
    	cal.set(d.getYear(), d.getMonthValue() - 1, d.getDayOfMonth(),0,0,0);
    	cal.setTimeZone(TimeZone.getTimeZone("UTC"));
    	
    	dtEmLong = Long.toString(cal.getTime().getTime());
    	dtEmLong = dtEmLong.substring(0, dtEmLong.length() - 3);
    	dtEmLong = dtEmLong + "000";
    	
    	return dtEmLong;
	}
	
	public static String somarSemanas(int semanas)
	{
		
		String dtEmLong;
    	LocalDate d = LocalDate.now().plusWeeks(semanas);
    	
    	Calendar cal = Calendar.getInstance();
    	cal.set(d.getYear(), d.getMonthValue() - 1, d.getDayOfMonth(),0,0,0);
    	cal.setTimeZone(TimeZone.getTimeZone("UTC"));
    	
    	dtEmLong = Long.toString(cal.getTime().getTime());
    	dtEmLong = dtEmLong.substring(0, dtEmLong.length() - 3);
    	dtEmLong = dtEmLong + "000";
    	return dtEmLong;
	}
}
