package com.bigjava.jsf;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Servlet implementation class JSFServlet
 */
@SuppressWarnings("serial")
public abstract class JSFServlet extends HttpServlet {
	//private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public JSFServlet() {
        super();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String event = request.getParameter("_evt");
		
		HttpSession session = request.getSession();
		ObjectPool pool = (ObjectPool) session.getAttribute("objpool");
		if(pool==null) {
			pool = new ObjectPool();
			session.setAttribute("objpool", pool);
		}
		
		// Reset Code generator
		pool.resetScript();
		
		/**
		 * Id event is defined, then this is where the framework starts.
		 * It will try to find the Java object whose method is being called. 
		 * It will try to do so by using Reflection
		 */
		if(event!=null) {
			Object sender = getSender(request.getParameter("_obj"),pool);
			
			if(sender!=null) {
				System.out.println(String.format("_evt=%s,_obj=%s",event, request.getParameter("_obj")));
				try {
					AjaxEvent ajaxEvent = new AjaxEvent(request,response,pool);
					Method m = sender.getClass().getMethod(event,AjaxEvent.class);
					m.invoke(sender, ajaxEvent);
					
					String script = pool.getScript();
					if(script.length()>0) {
						System.out.print(script);
						response.getWriter().write(script);
					}
				} catch (SecurityException e) {
					e.printStackTrace();
				} catch (NoSuchMethodException e) {
					e.printStackTrace();
				} catch (IllegalArgumentException e) {
					e.printStackTrace();
				} catch (IllegalAccessException e) {
					e.printStackTrace();
				} catch (InvocationTargetException e) {
					e.printStackTrace();
				}
			}	
			
		} else {
		
			getServletContext().getRequestDispatcher("/view.jsp")
				.forward(request, response);
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}
	
	protected Object getSender(String sender, ObjectPool pool) {
		if(sender==null)
			return this;
		return pool.get(sender);
	}
	
	protected abstract void onLoad(AjaxEvent jxevent);
}
