
package com.bigjava.dpi.gui;

import com.bigjava.jsf.ObjectPool;
import java.io.IOException;

import com.bigjava.jsf.AjaxEvent;
import com.bigjava.jsf.FormPanel;

/**
 * Menampung data sementara untuk ditransfer lagi ke class lain
 * @author Jonan
 *
 */
public class RequestPool extends FormPanel {

	MainScreen scrMain;
	String idTrigger=null;
	String pageid="";
	
	public RequestPool(ObjectPool pool, MainScreen screen) {
		super(pool);
		
		scrMain = screen;
	}

	/**
	 * Untuk modul logout
	 * @author Jonan
	 */
	public void reqStatusPanel(AjaxEvent jxe) throws IOException {
		jxe.getRequest().getSession().removeAttribute("username");
		jxe.getReponse().getWriter().write(String.format("window.location='Dispatch';"));
	}
	
//	/**
//	 * @author randikajonan
//	 * @param jxe
//	 */
//	public void reqChangePasswordWindow(AjaxEvent jxe) 
//	{
//		String userlogin=jxe.getRequest().getParameter("userlogin");
//		ChangePasswordWindow wdw = new ChangePasswordWindow();
//		wdw.show(jxe,userlogin);	
//	}
}
