package com.bigjava.jsf;

public class JSAttribute {
	private String name=null;
	private Object value=null;
	
	public JSAttribute(String name) {
		this.name = name;
	}
	
	public JSAttribute(String name, Object value) {
		this.name = name;
		this.setValue(value);
	}

	/**
	 * @param value the value to set
	 */
	public void setValue(Object value) {
		this.value = value;
	}

	/**
	 * @return the value
	 */
	public Object getValue() {
		return value;
	}

	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}
	
	/**
	 * @return The string representation of the object in JSON format.
	 */
	public String toString() {
		StringBuilder buffer = new StringBuilder();
		
		buffer.append(name);
		buffer.append(":");
		if(value instanceof String) {
			buffer.append("'");
			buffer.append(value.toString());
			buffer.append("'");
		} else
			buffer.append(value.toString());
		return buffer.toString();
	}
}
