package com.bigjava.dpi.gui.formatter;

public class HourFormater extends DateFormater {

	public HourFormater(String date) {
		super(date);
		// TODO Auto-generated constructor stub
	}

	@Override
	public String format(String date) {
		return String.format("%s:00", date.substring(date.length()-2));
	}

}