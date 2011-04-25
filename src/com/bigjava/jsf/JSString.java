package com.bigjava.jsf;

public class JSString {
	private String buffer="";
	
	public JSString(String content) {
		// TODO will this copy the string of only hold reference?
		// This might lead to buffer corruption bug
		buffer = content;
	}
	
	public String toString() {
		return String.format("'%s'", buffer.toString());
	}
	
	public String getStringValue() {
		return buffer;
	}
}
