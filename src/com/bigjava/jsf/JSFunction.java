package com.bigjava.jsf;

public class JSFunction extends JSCode {
	private String params;
	
	public JSFunction(String params, String code) {
		super(code);
		this.params = params;
	}
	
	public String toString() {
		return "function("+params+") { "  + super.toString() + "}";
	}
}
