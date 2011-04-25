package com.bigjava.util;

import java.util.LinkedList;

public class StringList extends LinkedList<String> {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public StringList() {
		
	}
	
	public String toString() {
		StringBuilder buffer = new StringBuilder();
		
		for(String item : this) {
			buffer.append(item);
		}
		return buffer.toString();
	}
	
	public String toString(String separator) {
		StringBuilder buffer = new StringBuilder();
		
		for(String item : this) {
			buffer.append(item+separator);
		}
		return buffer.toString();
	}
}
