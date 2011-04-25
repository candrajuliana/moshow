package com.bigjava.jsf;
//untuk export traffic VDPI
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

import com.bigjava.jsf.AjaxEvent;
import com.bigjava.jsf.FormPanel;
import com.bigjava.jsf.FormPanelEventListener;
import com.bigjava.jsf.FormPanelResult;
import com.bigjava.jsf.JSFunction;
import com.bigjava.jsf.JSObject;
import com.bigjava.jsf.Layout;
import com.bigjava.jsf.Panel;
import com.bigjava.jsf.Region;
import com.bigjava.jsf.Window;
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

public class CsvDNSPanel implements FormPanelEventListener {

	public void show(AjaxEvent jxe) {
		InitialContext initContext;
		Context envContext;
		StringBuilder temp = new StringBuilder();
		temp.append("[");
		FileWriter fw = null;
		ResultSet rs;
		String jumlah="";
		int jumlahs;
try
{
	initContext = new InitialContext();
	envContext = (Context)initContext.lookup("java:/comp/env");
	
	DataSource ds = (DataSource)(envContext).lookup("jdbc/DpiDB");
	Connection conn = ds.getConnection();
	Statement st = conn.createStatement();
	rs = st.executeQuery("select count(distinct (prot)) as no, prot, sum(bw_out),sum(bw_in),sum(pkt_out),sum(pkt_in) from flow group by prot ;");
	
	String filename = "c:\\csv\\dns.csv";
    fw = new FileWriter(filename);

	while(rs.next()) {

		
		/*	temp.append(",");*/
			
		
	
		      fw.append("Traffic");
		      fw.append(',');
		      fw.append("BW Out");
		      fw.append(',');
		      fw.append("BW In");
		      fw.append(',');
		      fw.append("Pkt Out");
		      fw.append(',');
		      fw.append("Pkt In");
		      fw.append('\n');
		      System.out.println("\n\nhasilnya " +rs.getString(1));



		    
		    

		      
		      temp.append(String.format("['%s','%s','%s','%s','%s']", rs.getString(1),rs.getString(2),rs.getString(3),rs.getString(4),rs.getString(5)));	

		 	 jumlah = rs.getString(1).toString();	
		 	   
		     jumlahs = Integer.parseInt(jumlah);
		    /* jumlahs = jumlahs + 23;*/  
		   for (int i = 1; i <= 24; i++){
		       fw.append(rs.getString(2));
		       fw.append(',');
		       fw.append(rs.getString(3));
		       fw.append(',');
		       fw.append(rs.getString(4));
		       fw.append(',');
		       fw.append(rs.getString(5));
		       fw.append(',');
		       fw.append(rs.getString(6));
		       fw.append('\n');
		       rs.next();
		       }
		       
		       System.out.println("\n\nhasilnya " +fw);
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

	

	public void onSubmitted(FormPanel form, FormPanelResult result,
			AjaxEvent jxe) 
	{
		
		String username = jxe.getRequest().getParameter("username").toString();
		String password = jxe.getRequest().getParameter("password").toString();
		InitialContext initContext;
		Context envContext;
		try {
			initContext = new InitialContext();
			envContext = (Context)initContext.lookup("java:/comp/env");
			
			DataSource ds = (DataSource)(envContext).lookup("jdbc/DpiDB");
			Connection conn = ds.getConnection();
			Statement st = conn.createStatement();
			ResultSet rs = st.executeQuery("select * from usermanager WHERE username = '"+username+"' AND password = '"+password+"';");
			
			while(rs.next()) {
				String usernamedb = rs.getString(2);
				String passworddb = rs.getString(3);
				if(jxe.getRequest().getParameter("username") !=null 
						 && jxe.getRequest().getParameter("username").toString().equalsIgnoreCase(usernamedb)
						 && jxe.getRequest().getParameter("password").toString().equalsIgnoreCase(passworddb))
					 //Ditambah password
					 {
						jxe.getRequest().getSession().setAttribute("username", "admin");
						
						 result.setSuccess(true);
						 result.setSuccessAction(new JSFunction("form,action","window.location='Dispatch';"));
						/* result.setSuccessAction(new JSFunction("form,action","window.print(0);"));*/
					 }
					 else
					 {
						 result.setSuccess(false);
						 result.setTitle("error login");
						 result.setMessage("Cannot login");
						 //Message untuk sementara dibuat simple krn belum connect ke DB
					 }
				
				
				
			}
			rs.close();
			st.close();
			conn.close();
		} catch (NamingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	
			
			
		}
		
		
	}

