package com.bigjava.jsf;

public class DateField extends FormField {
	
	public DateField(ObjectPool pool) {
		super(pool);
	}

	@Override
	public String getNamespace() {
		return "Ext.form.DateField";
	}

	@Override
	public String getXtype() {
		return "datefield";
	}
	
	
}
