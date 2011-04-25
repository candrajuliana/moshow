package com.bigjava.jsf;

public class TabNode extends JSObject {
	
	public void setTitle(String title) {
		setAttribute("title", title);
	}
	
	public void setIconCls(String cls) {
		setAttribute("iconCls", cls);
	}
	
	public void setClosable(boolean closable) {
		setAttribute("closable", closable);
	}
}
