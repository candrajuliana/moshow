package com.bigjava.dpi.gui;

import com.bigjava.jsf.AjaxEvent;
import com.bigjava.jsf.JSFServlet;

/**
 * Servlet implementation class Dispatch
 */
public class Dispatch extends JSFServlet
{
	private static final long serialVersionUID = 1L;
	
	LoginScreen scrLogin = new LoginScreen();
	MainScreen scrMain = new MainScreen();
	
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Dispatch() {
        super();
    }
   
	public void onLoad(AjaxEvent jxe) 
	{
		if(jxe.getRequest().getSession().getAttribute("username")==null)
			scrLogin.show(jxe);
		else
			scrMain.show(jxe);
	}
}
