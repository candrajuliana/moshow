package com.bigjava.jsf;

public class Viewport extends Container {
	
	public Viewport(ObjectPool pool) {
		super(pool);
	}
	
	@Override
	public String getNamespace() {
		return "Ext.Viewport";
	}

	@Override
	public String getXtype() {
		return "viewport";
	}
}
