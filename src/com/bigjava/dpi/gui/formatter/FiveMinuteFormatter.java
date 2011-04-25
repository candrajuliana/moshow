package com.bigjava.dpi.gui.formatter;

public class FiveMinuteFormatter extends DateFormater {

	public FiveMinuteFormatter(String date) {
		super(date);
		// TODO Auto-generated constructor stub
	}

	@Override
	public String format(String date) {
		return String.format("%s:%s", date.substring(date.length()-4,date.length()-2), date.substring(date.length()-2));
	}

}
