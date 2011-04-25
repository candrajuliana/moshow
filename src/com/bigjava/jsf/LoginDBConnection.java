package com.bigjava.jsf;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * Servlet implementation class DBConnection
 * dbcon = new DBConnection(driver, url, dbName, userName, password);
 */
public class LoginDBConnection {
	 private String driver = "";
	 private String url = "";
	 private String dbName = "";
	 private String userName = "";
	 private String password = "";

	 public LoginDBConnection(String driver, String url, String dbName, String userName, String password){
		 this.driver = driver;
		 this.url = url;
		 this.dbName = dbName;
		 this.userName = userName;
		 this.password = password;

		 
	 }
	 public Connection getConnection()
	  {
	    String url = "jdbc:mysql://localhost:3306/";
	    String dbName = "dpibigjava";
	    String driver = "com.mysql.jdbc.Driver";
	    String userName = "root";
	    String password = "root";
	    Connection con = null;    
	    Statement stmt = null; 
	    ResultSet rs = null; 
	    
	    try {
	      Class.forName(driver);
	      con =DriverManager.getConnection ("jdbc:mysql://localhost:3306/dpibigjava","root", "");
	      stmt = con.createStatement();
	      rs = stmt.executeQuery("SELECT * FROM tabeldpi");
	      

	      
			String filename = "C:/Documents and Settings/yulius/JFM/WebContent/line.store";
			FileWriter fw = new FileWriter(filename);
			while(rs.next()){

		     
			fw.append("[");
			/*fw.append("[" +
					rs.getObject(1).toString() +
					"," +
					rs.getObject(2).toString() +
					"]");*/
			fw.append("['01'," +
					rs.getObject(2).toString() +
					"]");
			fw.append(',');
			fw.append("['02',5900],['03',4000],['04',7500],['05',9500],['06',2000],['07',1500],['08',5900],['08',4000],['10',7500],['11',9500],['12',2000],['13',1500],['14',5900],['15',4000],['16',7500],['17',9500],['18',2000],['19',1500]");
			fw.append(']');
			

		 
			fw.flush();
			fw.close();
			
			
			 }
	      
	      
	      return DriverManager.getConnection(url + dbName, userName, password);
	    }
	    catch (Exception e)
	    {
	      e.printStackTrace();
	    }
	    return null;
	  }
	 
}