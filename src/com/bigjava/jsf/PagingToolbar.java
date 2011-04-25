package com.bigjava.jsf;

/**
 * 
 * @author randikajonan
 *
 */
public class PagingToolbar extends JSObject {

	public String toString() {
		return "new Ext.PagingToolbar(" + super.toString() + ")";
	}
	
	public void setPageSize(int size) {
		setAttribute("pageSize",size);
	}
	
	public void setStore(JsonStore store) {
		setAttribute("store",new JSCode(store.getId()));
	}
	
	public void setDisplayInfo(boolean info) {
		setAttribute("displayInfo",info);
	}
	
	public void setDisplayMsg(String msg) {
		setAttribute("displayMsg",msg);
	}

	public void setEmptyMsg(String msg) {
		setAttribute("emptyMsg",msg);
	}
	
	public void setstartEditing(String startEditing) {
		setAttribute("startEditing",startEditing);
	}
}
