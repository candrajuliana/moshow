package com.bigjava.jsf;



public class Window extends Panel {
	
	public Boolean cfg_closable	= true;
	public Boolean cfg_resizable= true;
	
	public Window(ObjectPool pool) {
		super(pool);
	}
	
	@Override
	public String getNamespace() {
		return "Ext.Window";
	}

	@Override
	public String getXtype() {
		return "window";
	}
	
	public void setClosable(boolean closable) {
		setAttribute("closable", closable);
	}
	
	public void setResizable(boolean resizable) {
		setAttribute("resizable",resizable);
	}
	
	public void setDraggable(boolean draggable) {
		setAttribute("draggable",draggable);
	}

	
	
	
}
