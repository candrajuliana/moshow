package com.bigjava.jsf;

public class Msg extends Panel {
	
	public Boolean cfg_closable	= true;
	public Boolean cfg_resizable= true;
	
	public Msg(ObjectPool pool) {
		super(pool);
	}
	
	@Override
	public String getNamespace() {
		return "Ext.Msg";
	}

	@Override
	public String getXtype() {
		return "msg";
	}
	
	
	
}
