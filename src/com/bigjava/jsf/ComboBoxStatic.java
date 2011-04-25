//last updated by jonan - 10 nov

package com.bigjava.jsf;
import com.bigjava.jsf.Component;

public class ComboBoxStatic extends Component {	
//	ComboBoxEventListener callback;
	private GridStoreStatic store = new GridStoreStatic();
	
	public ComboBoxStatic(ObjectPool pool) {
		super(pool);
		setAttribute("store",store);
		store.setUrl("cmb.store");
//		store.setUrl("Dispatch?_evt=reqLoadContent&_obj=" + getId());
		//store.setAttribute("autoLoad", true);
		
		store.addField("abbr");
		store.addField("state");
		
		setAttribute("width",150);
		setAttribute("valueField","abbr");
		setAttribute("displayField","state");
		setAttribute("typeAhead",true);
		setAttribute("mode","local");
		setAttribute("triggerAction","all");
		setAttribute("emptyText","Select ...");
		setAttribute("selectOnFocus",true);
	}

	public void setData(String txt)
	{
		store.setData(txt);
	}
	
	@Override
	public String getNamespace() {
		return "Ext.form.ComboBox";
	}

	@Override
	public String getXtype() {
		return "combo";
	}

}
