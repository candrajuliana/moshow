/*package com.bigjava.dpi.gui;

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
import com.bigjava.jsf.Window;

*//**
 * 
 * @author Jonan 13 desember
 *
 *//*
public class ChangePasswordWindow implements FormPanelEventListener {

	Window w=null;
	String userlogin="";
	String oldpas="";
	
	public void show(AjaxEvent jxe, String userlogs) {		
		userlogin = userlogs;
		
		getOldPas();
		
		WINDOW
		w = new Window(jxe.getObjectPool());
		w.setTitle("Change Password");//<img src=\"res/add_device.png\" />
		w.setPlain(false);
		w.setWidth(290);
		w.setHeight(175);
		w.setClosable(true);
		w.setResizable(false);
		w.setDraggable(true);
		w.setAttribute("modal", true);

		FORM LOGIN
		FormPanel form = new FormPanel(jxe.getObjectPool());
		form.setPlain(true);
		form.setEventListener(this);
		form.setAttribute("bodyStyle", "background:#dfe8f6;padding:5px;");//#dfe8f6 #BDD2F1;
		form.setBorder(false);
		
		form.addOldPasswordField("oldpassword", "Old Password",oldpas);
		form.addPasswordField("password", "New Password");
		form.addPasswordField("password2", "Password Confirmation");
		form.addSubmitButton("Submit", "Loading...");
		form.addDummyButton();
		
		w.addComponent(form);
		jxe.getObjectPool().scriptCreateInstance(w);
		w.show();
		
	}

	*//**
	 * Method untuk submit form ketika tombol submit ditekan.
	 * @author Jonan
	 * @param form FormPanel yang digunakan.
	 * @param result Nilai untuk hasil dari form.
	 * @param jxe Wadah untuk menjalankan ajax.js
	 *//*
	public void onSubmitted(FormPanel form, FormPanelResult result,
			AjaxEvent jxe) 
	{
		String[] passw = new String[5];
		passw[0] = jxe.getRequest().getParameter("password");
		passw[1] = jxe.getRequest().getParameter("password2");
		
		if(passw[0].equalsIgnoreCase(passw[1]))
		{
			String sql="update usermanager SET password ='"+ convertEnter(passw[0]) +"' WHERE username ='"+ userlogin+"'";
			
			InitialContext initContext;
			Context envContext;
			try {
				initContext = new InitialContext();
				envContext = (Context)initContext.lookup("java:/comp/env");
				
				DataSource ds = (DataSource)(envContext).lookup("jdbc/DpiDB");
				Connection conn = ds.getConnection();
				java.sql.PreparedStatement st = conn.prepareStatement(sql);
				st.executeUpdate();
				    
			      System.out.println("\n\nSuccessfully updated Your Record");
			    } catch (Exception e) {
			      e.printStackTrace();
			      System.out.println("\n\nUnable to insert");
			    }

//		    result.setTitle("Success");
//		    result.setMessage("Successfully Change Password");
			result.setSuccess(true);
		    result.setSuccessAction(new JSFunction("form,action",
		    		"Ext.Msg.alert('Success', 'Data saved!');" +
		    		"var wdw=Ext.getCmp('"+w.getId()+"');wdw.close();"));
//		    		"var txt = Ext.getCmp('oldpasstxt'); txt.setValue('test');" ));
		}
		else
		{
			result.setTitle("Success");
		    result.setMessage("Failed");
		}
		
		
	}
	
	*//**
	 * Convert semua inputan karakter 'enter' menjadi '<br>'.
	 * @author Jonan
	 * @param input Nilai masukkan yang akan di convert.
	 *//*
	public String convertEnter(String input)
	{
		int i = 10;
		String enter = new Character((char)i).toString();
		
		input = input.replaceAll(enter, "<br>");
		input = input.replaceAll("[^\\p{ASCII}]", "");

		i = 92;
		String blackslash = new Character((char)i).toString();
		input = input.replaceAll(blackslash+blackslash, "");
		
		i = 39;
		String petiksatu = new Character((char)i).toString();
		input = input.replaceAll(petiksatu, "");
		
		return input;
	}
	
	*//**
	 * @author yulius
	 *//*
	public void getOldPas()
	{
		InitialContext initContext;
		Context envContext;
	
		try {
			initContext = new InitialContext();
			envContext = (Context)initContext.lookup("java:/comp/env");
			
			DataSource ds = (DataSource)(envContext).lookup("jdbc/DpiDB");
			Connection conn = ds.getConnection();
			Statement st = conn.createStatement();
			ResultSet rs = st.executeQuery("select password from usermanager WHERE username ='"+ userlogin+"'");

			while(rs.next()) {
				
				oldpas = rs.getString(1);
			
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

}*/