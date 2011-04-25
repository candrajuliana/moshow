package com.bigjava.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;


public class EmailReport {
	static String url = "jdbc:mysql://127.0.0.1:3306/";
    static String dbName = "dpi";
    static String driver = "com.mysql.jdbc.Driver";
    static String userName = "bigjava";
    static String password = "B16java123";
    static Connection con = null; 
    static String[] recipients = new String[100];
    
    public void sendMail(){
    	boolean daily = false;
    	boolean weekly = false;
    	boolean monthly = false;
    	boolean annually = false;
    	boolean graph = false;
    	boolean traffic = false;
    	boolean live_session = false;
    	boolean dns = false;
    	boolean http = false;
    	boolean flash = false;
    	boolean p2p = false;
    	boolean settime = false;
    	boolean exclude = false;
    	String[] Query = new String[7];
    	String DailyHour = "";
    	String WeeklyDay = "";
    	String WeeklyHour = "";
    	String MonthlyDate = "";
    	String MonthlyHour = "";
    	String AnnuallyMonth = "";
    	String AnnuallyDate = "";
    	String AnnuallyHour = "";
    	String StartHour = "";
    	String EndHour = "";
    	String QuerySetTime = "";
    	String QueryExclude = "";
    	String[] downStartDate = new String[200];
    	String[] downEndDate = new String[200];
    	String[] downStartHour = new String[200];
    	String[] downEndHour = new String[200];
    	String QueryDowntime = "";
    	
    	
    	
    	try {
			Class.forName(driver);
			con = DriverManager.getConnection(url+dbName,userName,password);
			
			Statement st = con.createStatement();
		    ResultSet rs = st.executeQuery("SELECT daily, weekly, monthly, annually, daily_hour, weekly_day, weekly_hour, monthly_date, monthly_hour, annually_month, annually_date, annually_hour, graph, traffic, live_session, dns, http, flash, p2p, filter1, start_hour, end_hour, filter2 from report_schedule;");
			while (rs.next()){
				daily = rs.getBoolean("daily");
				weekly = rs.getBoolean("weekly");
				monthly = rs.getBoolean("monthly");
				annually = rs.getBoolean("annually");
				DailyHour = rs.getString("daily_hour");
				WeeklyDay = rs.getString("weekly_day");
				WeeklyHour = rs.getString("weekly_hour");
				MonthlyDate = rs.getString("monthly_date");
				MonthlyHour = rs.getString("monthly_hour");
				AnnuallyMonth = rs.getString("annually_month");
				AnnuallyDate = rs.getString("annually_date");
				AnnuallyHour = rs.getString("annually_hour");
				graph = rs.getBoolean("graph");
				traffic = rs.getBoolean("traffic");
				live_session = rs.getBoolean("live_session");
				dns = rs.getBoolean("dns");
				http = rs.getBoolean("http");
				flash = rs.getBoolean("flash");
				p2p = rs.getBoolean("p2p");
				settime = rs.getBoolean("filter1");
				exclude = rs.getBoolean("filter2");
				StartHour = rs.getString("start_hour");
				EndHour = rs.getString("end_hour");
			}
			
			//email recipient
			int i =0;
			ResultSet rsp = st.executeQuery("SELECT address from email_recipient;");
			while(rsp.next()){
				recipients[i] = rsp.getString(1);
				i++;
			}
			
			//downtime schedule
			int d =0;
			ResultSet rsdt = st.executeQuery("SELECT start_date, end_date, start_hour, end_hour from downtime_schedule;");
			while(rsdt.next()){
				downStartDate[d] = rsdt.getString(1);
				downEndDate[d] = rsdt.getString(2);
				downStartHour[d] = rsdt.getString(3);
				downEndHour[d] = rsdt.getString(4);
			//	System.out.println(downStartDate[d]);
				d++;
				
			}
			
			for(int l =0; l < d; l++){	
				QueryDowntime = QueryDowntime + String.format("and start_time not between '%s' and '%s' and start_hour not between '%s' and '%s' ", new Object[] {downStartDate[l],downEndDate[l],downStartHour[l],downEndHour[l]});		
			}
			//System.out.println(QueryDowntime);
		
			if(graph){
				Query[0]="graph";
			} else {
				Query[0]="";
			}
			
			if(traffic){
				Query[1]="traffic";
			} else {
				Query[1]="";
			}
			
			if(live_session){
				Query[2]="live";
			} else {
				Query[2]="";
			}
			
			if(dns){
				Query[3]="dns";
			} else {
				Query[3]="";
			}
			
			if(http){
				Query[4]="http";
			} else {
				Query[4]="";
			}
			
			if(flash){
				Query[5]="flash";
			} else {
				Query[5]="";
			}
			
			if(p2p){
				Query[6]="p2p";
			} else {
				Query[6]="";
			}
			
			if(settime){
				QuerySetTime = "and start_hour between '" + StartHour + "' and '" + EndHour +"'";
			}
			
			if(exclude){
				QueryExclude= "and week_flag=0";
			}
			
			CsvReport csv = new CsvReport();
			emailsent send = new emailsent();
			
			TimeValidation timecheck = new TimeValidation();
			if(daily){
				
					String tm = timecheck.GetDateYesterday();
					String td = timecheck.GetDateToday();
					
					csv.show(Query, con, tm, td, QuerySetTime, QueryExclude, QueryDowntime);
					send.sentmail(Query, recipients, i, "Daily");
				
			}
			
			if(weekly){
				
					String date1 = timecheck.GetDateWeek();
					String date2 = timecheck.GetDateToday();
					
					csv.show(Query, con, date1, date2, QuerySetTime, QueryExclude, QueryDowntime);
					send.sentmail(Query, recipients, i, "Weekly");
				
			}
			
			if(monthly){
				
					String date1 = timecheck.GetDateMonthFirst();
					String date2 = timecheck.GetDateMonthEnd();
					
					csv.show(Query, con, date1, date2, QuerySetTime, QueryExclude, QueryDowntime);
					send.sentmail(Query, recipients, i, "Monthly");
				
			}
			
			if(annually){
				
					String date1 = timecheck.GetDateYearFirst();
					String date2 = timecheck.GetDateYearEnd();
					
					csv.show(Query, con, date1, date2, QuerySetTime, QueryExclude, QueryDowntime);
					send.sentmail(Query, recipients, i, "Annually");
				
			}
			/*CsvFile csv = new CsvFile();
			csv.show(Query, con);
			
			email send = new email();
			send.sentmail(Query, recipients, i);*/
			
			con.close();
			return;
		}catch (Exception e) {
		      e.printStackTrace();
		}
    }

}
