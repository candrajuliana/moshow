package com.bigjava.jsf;
// untuk ekspor file ke format csv
import java.io.*;
/*import java.net.*;
import javax.servlet.*;
import javax.servlet.http.*;*/

import java.sql.Connection;
import java.sql.ResultSet;
/*import java.sql.SQLException;*/
import java.sql.Statement;

import javax.naming.Context;
import javax.naming.InitialContext;
/*import javax.naming.NamingException;*/
import javax.sql.DataSource;


public class CsvUserManager { 
	public void show(AjaxEvent jxe)  {
		InitialContext initContext;
		Context envContext;
		StringBuilder temp = new StringBuilder();
		temp.append("[");
try
{
	initContext = new InitialContext();
	envContext = (Context)initContext.lookup("java:/comp/env");
	
	DataSource ds = (DataSource)(envContext).lookup("jdbc/DpiDB");
	Connection conn = ds.getConnection();
	Statement st = conn.createStatement();
	ResultSet rs = st.executeQuery("select username,privilages,status,log,usertarget from usermanager ;");
	boolean first=true;
	while(rs.next()) {
		if(first==true) {
			first=false;
		} else {
			temp.append(",");
		}
		temp.append(String.format("['%s','%s','%s','%s']", rs.getString(1),rs.getString(2),rs.getString(3),rs.getString(4)));
	
		String filename = "c:\\csv\\usermanager.csv";
	      FileWriter fw = new FileWriter(filename);
	 
	      fw.append("Username");
	      fw.append(',');
	      fw.append("Privileges");
	      fw.append(',');
	      fw.append("Status");
	      fw.append(',');
	      fw.append("log");
	      fw.append(',');
	      fw.append("usertarget");
	      fw.append('\n');

	      fw.append(rs.getString(1));
	      fw.append(',');
	      fw.append(rs.getString(2));
	      fw.append(',');
	      fw.append(rs.getString(3));
	      fw.append(',');
	      fw.append(rs.getString(4));
	      fw.append(',');
	      fw.append(rs.getString(5));
	      fw.append('\n');

	      
	 
	      
	      fw.flush();
	      fw.close();
	
	
	}
	rs.close();
	st.close();
	conn.close();
	
      
     

} 
catch (Exception ex) {
ex.printStackTrace ();
}
}
}