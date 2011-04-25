package com.bigjava.jsf;

public class FormPanelResult extends JSObject {
	public void setTitle(String title) {
		setAttribute("title", title);
	}
	
	public void setMessage(String msg) {
		setAttribute("message", msg);
	}
	
	public void setSuccess(boolean result) {
		setAttribute("success", result);
	}
	
	public void setSuccessAction(JSCode code) {
		setAttribute("action",code);
	}
}
