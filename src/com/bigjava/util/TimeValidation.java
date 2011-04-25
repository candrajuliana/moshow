package com.bigjava.util;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;


public class TimeValidation {
	
	public TimeValidation(){
		super();
	}
	
	public boolean daily(String hour){
		boolean sendmail = false;
		String HourServer = "";
		DateFormat dtout = new SimpleDateFormat("HH:mm");
		Calendar cal = Calendar.getInstance(); 
		Date dateserver = cal.getTime(); 
	  	HourServer = dtout.format(dateserver);
		if(hour.equalsIgnoreCase(HourServer)){
			sendmail = true;
		}
		return sendmail;
	}
	
	public String GetDateToday(){
		String date = "";
		DateFormat dtout = new SimpleDateFormat("yyyy-MM-dd");
		Calendar cal = Calendar.getInstance(); 
		Date dateserver = cal.getTime(); 
	  	date = dtout.format(dateserver);
	  	System.out.print(date);
		
		return date;
	}
	
	public String GetDateYesterday(){
		String date = "";
		DateFormat dtout = new SimpleDateFormat("yyyy-MM-dd");
		Calendar cal = Calendar.getInstance(); 
		cal.add(Calendar.DATE, -1);
		Date dateserver = cal.getTime(); 
	  	date = dtout.format(dateserver);
	  	//System.out.print(date);
		
		return date;
	}
	
	public boolean weekly(String day, String hour){
		boolean sendmail = false;
		String DayServer = "";
		String HourServer = "";
		DateFormat dyout = new SimpleDateFormat("EEE");
		DateFormat hrout = new SimpleDateFormat("HH:mm");
		Calendar cal = Calendar.getInstance(); 
		Date dateserver = cal.getTime(); 
	  	DayServer = dyout.format(dateserver);
	  	HourServer = hrout.format(dateserver);
		if(day.equalsIgnoreCase(DayServer) && hour.equalsIgnoreCase(HourServer)){
			sendmail = true;
		}
		return sendmail;
	}
	
	public String GetDateWeek(){
		String date = "";
		DateFormat dtout = new SimpleDateFormat("yyyy-MM-dd");
		Calendar cal = Calendar.getInstance(); 
		cal.add(Calendar.DATE, -7);
		Date dateserver = cal.getTime(); 
	  	date = dtout.format(dateserver);
	  	//System.out.print(date);
		
		return date;
	}
	
	public boolean monthly(String day, String hour){
		boolean sendmail = false;
		String DayServer = "";
		String HourServer = "";
		
		DateFormat dyout = new SimpleDateFormat("dd");
		DateFormat hrout = new SimpleDateFormat("HH:mm");
		Calendar cal = Calendar.getInstance(); 
		Date dateserver = cal.getTime(); 
	  	DayServer = dyout.format(dateserver);
	  	HourServer = hrout.format(dateserver);
		if(day.equalsIgnoreCase(DayServer) && hour.equalsIgnoreCase(HourServer)){
			sendmail = true;
		}
		return sendmail;
	}
	
	public String GetDateMonthFirst(){
		String date = "";
		
		//get date
		DateFormat dtout = new SimpleDateFormat("yyyy-MM-dd");
		Calendar cal = Calendar.getInstance();
		cal.add(Calendar.MONTH, -1);
		cal.set(Calendar.DAY_OF_MONTH, cal.getActualMinimum(Calendar.DAY_OF_MONTH));
		Date dateserver = cal.getTime(); 
	  	date = dtout.format(dateserver);
	  	//System.out.print(date);
		
		return date;
	}
	
	public String GetDateMonthEnd(){
		String date = "";
		
		//get date
		DateFormat dtout = new SimpleDateFormat("yyyy-MM-dd");
		Calendar cal = Calendar.getInstance();
		cal.add(Calendar.MONTH, -1);
		cal.set(Calendar.DAY_OF_MONTH, cal.getActualMaximum(Calendar.DAY_OF_MONTH));
		Date dateserver = cal.getTime(); 
	  	date = dtout.format(dateserver);
	  	//System.out.print(date);
		
		return date;
	}
	
	public boolean annually(String month, String day, String hour){
		boolean sendmail = false;
		String MonthServer = "";
		String DayServer = "";
		String HourServer = "";
		
		DateFormat mtout = new SimpleDateFormat("MM");
		DateFormat dyout = new SimpleDateFormat("dd");
		DateFormat hrout = new SimpleDateFormat("HH:mm");
		Calendar cal = Calendar.getInstance(); 
		Date dateserver = cal.getTime(); 
		MonthServer = mtout.format(dateserver);
	  	DayServer = dyout.format(dateserver);
	  	HourServer = hrout.format(dateserver);
		if(day.equalsIgnoreCase(DayServer) && hour.equalsIgnoreCase(HourServer) && month.equalsIgnoreCase(MonthServer)){
			sendmail = true;
		}
		return sendmail;
	}
	
	public String GetDateYearEnd(){
		String date = "";
		
		//get date
		DateFormat dtout = new SimpleDateFormat("yyyy-MM-dd");
		Calendar cal = Calendar.getInstance();
		cal.add(Calendar.YEAR, -1);
		cal.set(Calendar.DAY_OF_YEAR, cal.getActualMaximum(Calendar.DAY_OF_YEAR));
		Date dateserver = cal.getTime(); 
	  	date = dtout.format(dateserver);
	  	//System.out.print(date);
		
		return date;
	}
	
	public String GetDateYearFirst(){
		String date = "";
		
		//get date
		DateFormat dtout = new SimpleDateFormat("yyyy-MM-dd");
		Calendar cal = Calendar.getInstance();
		cal.add(Calendar.YEAR, -1);
		cal.set(Calendar.DAY_OF_YEAR, cal.getActualMinimum(Calendar.DAY_OF_YEAR));
		Date dateserver = cal.getTime(); 
	  	date = dtout.format(dateserver);
	  	//System.out.print(date);
		
		return date;
	}
}
