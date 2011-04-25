package com.bigjava.jsf;

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

public class logout implements FormPanelEventListener {

	public void show(AjaxEvent jxe) {
		/*WINDOW*/
		Window w = new Window(jxe.getObjectPool());
		w.setTitle("");
		w.setPlain(false);
		w.setWidth(300);
		w.setHeight(280);
		w.setLayout(Layout.BORDER);
		w.setClosable(false);
		w.setResizable(false);
		w.setDraggable(false);
		w.setAttribute("bodyStyle", "background:#4e84b3 none;");
		//w.setAttribute("baseCls", "plain");

		/*LOGO ATAS*/
		Panel logoPanel = new Panel(jxe.getObjectPool());
		logoPanel.setRegion(Region.CENTER);
		//logoPanel.setAttribute("baseCls", "plain");
		logoPanel.setAttribute("bodyStyle", "background:#4e84b3 url(res/BannerLogo.png) no-repeat left center;");
		//dfe8f6
		
		/*FORM LOGIN*/
		FormPanel form = new FormPanel(jxe.getObjectPool());
		//form.setAttribute("baseCls", "plain");
		form.setRegion(Region.SOUTH);
		form.setAttribute("bodyStyle", "background:#4e84b3 none; color:#222; padding:5px 15px;");
		form.setPlain(true);
		form.setEventListener(this);
		form.setAttribute("height", 120);
		form.setAttribute("labelWidth", 80);
		JSObject obj = new JSObject();
		obj.setAttribute("width", 150);
		form.setAttribute("defaults", obj);
		form.addTextField("username", "Username");
    	form.addPasswordField("password", "Password");
		form.addSubmitButton("Submit", "Loading...");
		form.addResetButton();
		//Ditambah function baru addPasswordField dan addResetButton
		
		w.addComponent(form);
		w.addComponent(logoPanel);
		jxe.getObjectPool().scriptCreateInstance(w);
		w.show();
	}
	
	

	public void onSubmitted(FormPanel form, FormPanelResult result,
			AjaxEvent jxe) 
	{
		
		
		String url = "jdbc:mysql://localhost:3306/";
	    String dbName = "dpibigjava";
	    String driver = "com.mysql.jdbc.Driver";
	    String userName = "root";
	    String password = "root";
	    Connection con = null;    
	    Statement stmt = null; 
	    ResultSet rs = null; 
	    
		System.out.println(jxe.getRequest().getQueryString());
		
		try {
		     
		      con =DriverManager.getConnection ("jdbc:mysql://localhost:3306/dpibigjava","root", "");
		      stmt = con.createStatement();
		      String username = jxe.getRequest().getParameter("username");
		  	String passwords = jxe.getRequest().getParameter("password");
		      rs = stmt.executeQuery("SELECT * FROM members WHERE account_accountid = '" + username + "' AND passwords = '" + passwords + "'");
		      boolean auth = false;
	
				
		      while(rs.next()){  
		    	//String pool = (String) session.getAttribute("username");
					//if(pool==null) {
						auth = false;
						
					//}
				
					
						
				}
		      if(auth){
		    	  jxe.getRequest().getSession().setAttribute("username", "admin");
					 result.setSuccess(true);
					 result.setSuccessAction(new JSFunction("form,action","window.location='Dispatch';"));
					}

		 else
		 {
			 result.setSuccess(false);
			 result.setTitle("logout message");
			 result.setMessage("Logout from VDPI");
			 //Message untuk sementara dibuat simple krn belum connect ke DB
		 }
		      
		      
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

		      
		      
	}
