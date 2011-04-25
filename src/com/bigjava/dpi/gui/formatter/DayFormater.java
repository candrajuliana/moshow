package com.bigjava.dpi.gui.formatter;


public class DayFormater extends DateFormater {
	
	
	public DayFormater(String date) {
		super(date);
	}

	@Override
	public String format(String date) {
		int id =0;
		id = Integer.parseInt(date.substring(4,6));
		
		String tmp = String.format("%s %s", months[id], date.substring(6,8));
		
//		if(date.length()>8)
//			tmp += String.format(" %s", date.substring(8, 10));
//		if(date.length()>10)
//			tmp += String.format(":%s", date.substring(10));
//		else
//			tmp += String.format(":00");
//		
//		System.out.println("Day formatter: "+tmp+" "+date);
		return tmp;
	}

}