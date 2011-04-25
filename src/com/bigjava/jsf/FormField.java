package com.bigjava.jsf;

public abstract class FormField extends Component {
	public FormField(ObjectPool pool) {
		super(pool);
	}

	public void setFieldLabel(String label) {
		setAttribute("fieldLabel",label);
	}
	
	public void setName(String name) {
		setAttribute("name", name);
	}
	
	public void setValidationType(String vtype) {
		setAttribute("vtype",vtype);
	}
	
	public void setStartDate(DateField start) {
		setAttribute("startDateField", start.getId());
	}
	
	public void setEndDate(DateField end) {
		setAttribute("endDateField", end.getId());
	}
	
	public void setWidth(int width) {
		setAttribute("width", width);
	}
}
