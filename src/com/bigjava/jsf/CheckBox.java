//last updated by jonan - 10 nov

package com.bigjava.jsf;
import com.bigjava.jsf.Component;

public class CheckBox extends Component {	
//	ComboBoxEventListener callback;
	
	public CheckBox(ObjectPool pool) {
		super(pool);
	}
	
	@Override
	public String getNamespace() {
		return "Ext.form.Checkbox";
	}

	@Override
	public String getXtype() {
		return "checkbox";
	}

}
