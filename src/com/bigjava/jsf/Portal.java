package com.bigjava.jsf;

public class Portal extends Panel {
	
	public Portal(ObjectPool pool) {
		super(pool);
	}
	
	@Override
	public String getNamespace() {
		return "Ext.ux.Portal";
	}

	@Override
	public String getXtype() {
		return "panel";
	}
	
	public void setHeight(int height) {
		setAttribute("height", height);
	}
	
	public void setWidth(int width) {
		setAttribute("width", width);
	}
	
	public void setPlain(boolean plain) {
		setAttribute("plain", plain);
	}
	
	public void setBorder(boolean border) {
		setAttribute("border",border);
	}
	
	public void setSplit(boolean split) {
		setAttribute("split",split);
	}
	
	public void setTitle(String title) {
		setAttribute("title",title);
	}
	
	public void setRegion(String region) {
		setAttribute("region", region);
	}
	
	public void setMargins(String margins) {
		setAttribute("margins", margins);
	}
	
	public void setFloatable(boolean floatable) {
		setAttribute("floatable",floatable);
	}
	
	public void setBodyBorder(boolean bodyBorder) {
		setAttribute("bodyBorder",bodyBorder);
	}
	
	public void setCollapsible(boolean collapsible) {
		setAttribute("collapsible",collapsible);
	}
	
	public void setHidden(boolean hidden) {
		setAttribute("hidden", hidden);
	}
	
	/*TEST TAMBAH MODUL - jnn*/
	public void setItems(FormPanel i) {
		setAttribute("items", i);
	}
	
	public void setActivePanel(Portal pnl) {
		setActivePanel(pnl.getId());
	}
	
	public void setActivePanel(String id) {
		pool.scriptCallFunction(this, String.format("layout.setActiveItem('%s')",id));
	}
	
	public void setAutoScroll(boolean autoScroll) {
		setAttribute("autoScroll",autoScroll);
	}
	public void setdragover(boolean dragover) {
		setAttribute("dragover",dragover);
	}


}
