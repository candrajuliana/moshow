package com.bigjava.dpi.gui;

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

/**
 * login to VDPI
 * @author randikajonan 10feb2011
 */
public class LoginScreen implements FormPanelEventListener {

	Window w;
	
	public void show(AjaxEvent jxe) {
		/*WINDOW*/
		w = new Window(jxe.getObjectPool());
		w.setTitle("");
		w.setPlain(false);
		w.setWidth(290);
		w.setHeight(280);
		w.setLayout(Layout.BORDER);
		w.setClosable(false);
		w.setResizable(false);
		w.setDraggable(true);
		w.setAttribute("bodyStyle", "background:#4e84b3 none;");

		/*LOGO ATAS*/
		Panel logoPanel = new Panel(jxe.getObjectPool());
		logoPanel.setRegion(Region.CENTER);
		logoPanel.setAttribute("bodyStyle", "background:#4e84b3 url(res/BannerLogo.png) no-repeat center center;");
		
		/*FORM LOGIN*/
		FormPanel form = new FormPanel(jxe.getObjectPool());
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
//		form.addSubmitButton("Submit", "Loading...");
		form.addDummyButton();
		
		w.addComponent(form);
		w.addComponent(logoPanel);
		jxe.getObjectPool().scriptCreateInstance(w);
		w.show();
	}
	/**
	 * database verification after submit button clicked 
	 * @author randikajonan 10feb2011
	 */
	public void onSubmitted(FormPanel form, FormPanelResult result,
			AjaxEvent jxe) 
	{
		String usernamedb="";
		String passworddb="";
		

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
			ResultSet rs = st.executeQuery("select * from UserManager WHERE UserName = '"+username+"' AND UserPassword = '"+password+"';");
			
			while(rs.next()) {
				usernamedb = rs.getString(2);
				passworddb = rs.getString(3);
			}
			rs.close();
			st.close();
			conn.close();
		} catch (NamingException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}	
		
		if(jxe.getRequest().getParameter("username") !=null 
				 && jxe.getRequest().getParameter("username").toString().equalsIgnoreCase(usernamedb)
				 && jxe.getRequest().getParameter("password").toString().equalsIgnoreCase(passworddb))
			
		 {
			jxe.getRequest().getSession().setAttribute("username", usernamedb);
			result.setSuccess(true);
			result.setSuccessAction(new JSFunction("form,action","window.location='Dispatch';" +
					"var wdw=Ext.getCmp('"+w.getId()+"');wdw.close();"));
		 }
		 else
		 {
			result.setTitle("error login");
			result.setMessage("Cannot login");
		 }
	}
}