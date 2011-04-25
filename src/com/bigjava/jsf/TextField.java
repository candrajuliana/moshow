package com.bigjava.jsf;

public class TextField extends FormField {
	
	public TextField(ObjectPool pool) {
		super(pool);
	}

	@Override
	public String getNamespace() {
		return "Ext.form.TwinTriggerField";
	}

	@Override
	public String getXtype() {
		return "textfield";
	}
	
	
}