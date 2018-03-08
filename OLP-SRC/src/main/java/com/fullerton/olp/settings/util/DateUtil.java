package com.fullerton.olp.settings.util;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.Month;
import java.time.Period;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

public class DateUtil {
	public static final String MONTH_YEAR = "MMM yy";
	public static final String CP_WS_DATE_FORMAT = "yyyyMMdd";
	public static final String DB_DATE_FORMAT = "yyyy-MM-dd";
	public static final String FULL_DATE_FORMAT = "yyyyMMddhhmmss";

	public static String getDateCPString(Date date) {
		if (date == null) {
			return "";
		}
		DateFormat format = new SimpleDateFormat(CP_WS_DATE_FORMAT);
		return format.format(date);
	}
	public static String getDateString(Date date) {
		if (date == null) {
			return "";
		}
		DateFormat format = new SimpleDateFormat(FULL_DATE_FORMAT);
		return format.format(date);
	}
	
	public static String getMonthYearString(Date date) {
		if (date == null) {
			return "";
		}
		DateFormat format = new SimpleDateFormat(MONTH_YEAR);
		return format.format(date);
	}
	
	public static String getDbDateString(Date date) {
		if (date == null) {
			return "";
		}
		DateFormat format = new SimpleDateFormat(DB_DATE_FORMAT);
		return format.format(date);
	}
	
	 
	
	public static int getDiffYears(Date date) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		LocalDate bday = LocalDate.of(calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH)+1, calendar.get(Calendar.DATE)); 
		LocalDate today = LocalDate.now(); 
		Period age = Period.between(bday, today);
		int years = age.getYears(); 
	    return years;
	}
	public static void main(String[] args) {
		
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(new Date(1390933800000l));
		System.out.println(getDiffYears(calendar.getTime()));
		System.out.println(getDiffMonths(calendar.getTime()));
	}

	
	public static int getDiffMonths(Date date) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		LocalDate bday = LocalDate.of(calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH)+1, calendar.get(Calendar.DATE)); 
		LocalDate today = LocalDate.now(); 
		Period age = Period.between(bday, today);
		int months = age.getMonths();
	    return months;
	}

	public static Calendar getCalendar(Date date) {
	    Calendar cal = Calendar.getInstance(Locale.US);
	    cal.setTime(date);
	    return cal;
	}


	public static Date parseCPDate(String dateStr) {
		DateFormat format = new SimpleDateFormat(CP_WS_DATE_FORMAT);
		try {
			return format.parse(dateStr);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
}