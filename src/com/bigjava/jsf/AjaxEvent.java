package com.bigjava.jsf;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class AjaxEvent {
	private HttpServletRequest request;
	private HttpServletResponse response;
	private ObjectPool pool;
	
	public AjaxEvent(HttpServletRequest request, HttpServletResponse response, ObjectPool pool)
	{
		this.request = request;
		this.response = response;
		this.pool = pool;
//		System.out.println("\n\nAjaxEvent.java - request,response,pool : "+request+" "+response+" "+pool);
	}
	
	public HttpServletRequest getRequest() { return this.request; }
	public HttpServletResponse getReponse() { return this.response; }
	public ObjectPool getObjectPool() { return this.pool; }
}
