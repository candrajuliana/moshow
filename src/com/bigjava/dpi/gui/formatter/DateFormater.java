package com.bigjava.dpi.gui.formatter;

public abstract class DateFormater {
	String data;
	protected String[] months = {"un","Jan","Feb","Mar","Apr","May","Jun","Jul","Aug","Sep","Oct","Nov","Dec"};
	public DateFormater(String date) {
		data = format(date);
	}
	
	public abstract String format(String date);
	
	public String toString() {
		return data;
	}
}
