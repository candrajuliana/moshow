//last updated by jonan 23 nov
//- add getTodayConv()

package com.bigjava.jsf;

public class TimeFunc {
	
	public TimeFunc()
	{
		
	}
	
	public String getToday()
	{
		String tmp_date=getTimestamp().toString();
		//Traffic.java - time : 2010-10-11 11:49:56.749
		
		return tmp_date.substring(0, 10);
	}
	
	public String getTodayConv()
	{
		String tmp_date="";
		String time="";
		String []con_date;
		String con_date2;
		
//		2010-08-20 13:55:34.159
		tmp_date = getTimestamp().toString();
		time = tmp_date.substring(0,10);
		//buat konversi dari 2010-08-20 menjadi 20100820
		con_date=time.toString().split("");
		con_date2 = con_date[1]+con_date[2]+con_date[3]+con_date[4]+con_date[6]+con_date[7]+con_date[9]+con_date[10];
		
		return con_date2;
	}
	
	public static java.sql.Timestamp getTimestamp() 
	{
	    java.util.Date today = new java.util.Date();
	    return new java.sql.Timestamp(today.getTime());
	}
}