//last updated by jonan - 26 oct

package com.bigjava.jsf;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.LinkedList;

import com.bigjava.jsf.Component;

public class ComboBox extends Component {
	class ComboBoxItem {
		public String text;
		public String tag;
		
		public ComboBoxItem(String text, String tag) {
			this.text=text;
			this.tag=tag;
		}
	}
	
	ComboBoxEventListener callback;
	
	private LinkedList<ComboBoxItem> items = new LinkedList<ComboBoxItem>();
	private GridStore store = new GridStore();
	
	public ComboBox(ObjectPool pool) {
		super(pool);
//		System.out.println("\n\nComboBox - main()");
		setAttribute("store",store);
		store.setUrl("Dispatch?_evt=reqLoadContent&_obj=" + getId());
		//store.setAttribute("autoLoad", true);
		
		store.addField("tag");
		store.addField("text");
		
		setAttribute("width",150);
		setAttribute("valueField","tag");
		setAttribute("displayField","text");
		setAttribute("mode","local");
		setAttribute("emptyText","Select ...");
		
		setAttribute("listeners", new JSCode(
				"{" +
				"	select: function(combo, record, index) {" +
				"		jfm_request('reqSelect', '" + getId() + "', '&value='+combo.getValue());" +
				"		}" +
				"}"));
	}

	@Override
	public String getNamespace() {
		return "Ext.form.ComboBox";
	}

	@Override
	public String getXtype() {
		return "combo";
	}

	public void setEditable(boolean editable) {
		setAttribute("editable", editable);
	}
	
	public void reqLoadContent(AjaxEvent jxe) {
		System.out.println("\n\nComboBox - reqLoadContent()");
		try {
			StringBuffer temp = new StringBuffer();
			PrintWriter writer = jxe.getReponse().getWriter();
			
			temp.append("[");
			boolean first=true;
			for(ComboBoxItem item:items) {
				if(first)
					first=false;
				else
					temp.append(",");
				temp.append(String.format("['%s','%s']",item.tag,item.text));
//				System.out.println("\nComboBox.java - item.tag & item.text : "+item.tag+" "+item.text);
			}
			temp.append("]");
			writer.write(temp.toString());
//			System.out.println(temp.toString());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	public void reqSelect(AjaxEvent jxe) {
		String ids = jxe.getRequest().getParameter("value");
		if(ids==null)return;
		
		if(callback!=null) {
			callback.onSelect(this, ids, jxe);
		}
	}
	
	public void addItem(String tag, String text) {
		items.add(new ComboBoxItem(text,tag));
	}
	
	public void clear() {
		items.clear();
	}
	
	public void setEventListener(ComboBoxEventListener c) {
		callback=c;
	}
}
