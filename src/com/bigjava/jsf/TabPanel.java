package com.bigjava.jsf;

public class TabPanel extends Panel {

	public TabPanel(ObjectPool pool) {
		super(pool);
	}
	
	@Override
	public String getNamespace() {
		return "Ext.TabPanel";
	}

	@Override
	public String getXtype() {
		return "tabpanel";
	}
	
	public void addTab(TabNode tab) {
		pool.scriptCallFunction(this, "add(" + tab.toString()+ ").show()");
	}
	
	public void setActiveTab(int activeTab) {
		setAttribute("activeTab",activeTab);
	}

}
