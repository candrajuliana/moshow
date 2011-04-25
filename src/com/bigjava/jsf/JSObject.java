package com.bigjava.jsf;

import java.util.Collection;
import java.util.HashMap;

/**
 * Java representation of JSON object.
 * @author benniadham
 *
 */
public class JSObject {
	private HashMap<String,JSAttribute> attributes = new HashMap<String,JSAttribute>();
	private JSObject next	= null;
	private JSObject prev	= null;
	private Object value 	= null;
	
	public JSObject(Object value) {
		this.value = value;
	}
	
	public JSObject() {
		this.value = null;
	}
	
	public Object getValue() {
		return value;
	}
	
	/**
	 * Set attribute of array type, such as children, columns, items, etc...
	 * 
	 * @param name The attribute's name
	 * @param val The value
	 * @return The attribute
	 */
	public JSAttribute setArrayAttribute(String name, JSObject val) {
		JSAttribute attr = this.getAttribute(name);
		if(attr==null) {
			attr = this.setAttribute(name, val);
		} else {
			JSObject obj = (JSObject) attr.getValue();
			if(obj!=null)
				obj.arrayAppend(val);
		}
		
		return attr;
	}
	
	/**
	 * Set Attribute of the JSON object. 
	 * It will create one if it does not exist yet.
	 * @param name The name of the attribute.
	 * @param val The value of the attribute.
	 * @return The attribute
	 */
	public JSAttribute setAttribute(String name, Object val) {
		JSAttribute attr = attributes.get(name);
		if(attr!=null)
			attr.setValue(val);
		else
			attr = addAttributes(new JSAttribute(name,val));
		return attr;
	}
	
	/**
	 * Add a new attribute to the object
	 * @param attr The attribute
	 */
	private JSAttribute addAttributes(JSAttribute attr) {
		attributes.put(attr.getName(), attr);
		return attr;
	}
	
	/**
	 * Wrapper for boolean primitive
	 * @param name The name of the attribute.
	 * @param val The boolean value.
	 */
	public void setAttribute(String name, boolean val) {
		setAttribute(name,new Boolean(val));
	}
	
	/**
	 * Wrapper for integer primitive
	 * @param name The name of the attribute.
	 * @param val The integer value.
	 */
	public void setAttribute(String name, int val) {
		setAttribute(name,new Integer(val));
	}
	
	/**
	 * Wrapper for float primitive
	 * @param name The name of the attribute.
	 * @param val The float value.
	 */
	public void setAttribute(String name, float val) {
		setAttribute(name,new Float(val));
	}
	
	public void setAttribute(String name, String val) {
		setAttribute(name, new JSString(val));
	}
	
	/**
	 * Get the attribute specified by the attribute name
	 * @param name The name of the attribute
	 * @return The attribute
	 */
	public JSAttribute getAttribute(String name) {
		return attributes.get(name);
	}
	
	/**
	 * Add a new element to the end of the JSON array
	 * @param element The new array element to be inserted to the array.
	 */
	public void arrayAppend(JSObject element) {
		if(next != null) {
			next.arrayAppend(element);
		} else {
			next = element;
			element.prev = this;
		}
	}
	
	/**
	 * Indicates whether the current JSON object is part of an array.
	 * @return true if the object is part on an array.
	 */
	public boolean isArray() {
		return next!=null || prev!=null ? true:false;
	}
	
	/**
	 * Returns the head of the array. If the object is not an element of an array
	 * then it will return itself.
	 * @return The head (the first element) of the array.
	 */
	public JSObject getHead() {
		if(prev==null)
			return this;
		else 
			return prev.getHead();
	}
	
	/**
	 * Indicates if the current object is the first element of an array
	 * @return true or false.
	 */
	public boolean isHeadOfArray() {
		return next!=null && prev==null?true:false;
	}
	
	/**
	 * Indicates if the current object is th last element of an array
	 * @return true or false
	 */
	public boolean isTailOfArray() {
		return prev!=null && next==null?true:false;
	}
	
	/**
	 * Print the attributes to a string buffer
	 * @param buffer The buffer to receive the data
	 */
	protected void printAttributes(StringBuilder buffer) {
		boolean first=true;
		Collection<JSAttribute> coll = attributes.values();
		for(JSAttribute attr : coll)
		{
			if(first)
				first = false;
			else
				buffer.append(",");
			buffer.append(attr.toString());
		}
	}
	
	public String toString() {
		
		StringBuilder buffer = new StringBuilder();
	
		if(isHeadOfArray())
			buffer.append("[");
		
		if(value==null) {
			buffer.append("{");
			printAttributes(buffer);
			buffer.append("}");
		}
		else
			buffer.append(value instanceof String ? String.format("'%s'",value.toString()) : value.toString());
		
		
		if(next != null) {
			buffer.append(",");
			buffer.append( next.toString());
		}
		
		if(isTailOfArray()) {
			buffer.append("]");
		}
		
		return buffer.toString();
		
	}
	
	public JSObject getNext() {
		return next;
	}
	
	public JSObject getPrevious() {
		return prev;
	}
}
