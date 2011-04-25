package com.bigjava.jsf;

public class Toolbar extends JSObject {

	public String toString() {
		return "new Ext.Toolbar(" + super.toString() + ")";
	}
	
	public void setWidth(int width) {
		setAttribute("width",width);
	}
	
	public void setHeight(int height) {
		setAttribute("height",height);
	}

	public void setRenderTo() {
		setAttribute("renderTo","document.body");
	}
}
