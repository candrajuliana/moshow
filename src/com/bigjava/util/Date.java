package com.bigjava.util;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;

public class Date extends java.util.Date {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public Date() {
		super();
	}
	
	public Date(java.util.Date date) {
		super(date.getTime());
	}
	
	public void addDay() {
		setTime(getTime()+(1000 * 60 * 60 * 24));
	}
	
	public void subtractDay() {
		setTime(getTime()-(1000 * 60 * 60 * 24));
	}
	
	public long diff(Date future) {
		 return future.getTime() - getTime();
	}
	
	public int dayDiff(Date future) {
		return (int) (this.diff(future) / (1000 * 60 * 60 * 24));
	}
	
	public int hourDiff(Date future) {
		return (int) (this.diff(future) / (1000 * 60 * 60));
	}
	
	public int minuteDiff(Date future) {
		return (int) (this.diff(future) / (1000 * 60));
	}
	
	/*static public Date yyyyMMdd(String date) throws ParseException {
		DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
		
		return new Date(df.parse(date));
	}*/
	
	static public Date MMddyyyy(String date) throws ParseException {
		DateFormat df = new SimpleDateFormat("MM-dd-yyyy");
		
		return new Date(df.parse(date));
	}
}
