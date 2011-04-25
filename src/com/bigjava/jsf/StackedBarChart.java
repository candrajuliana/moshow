package com.bigjava.jsf;

import java.io.IOException;

public class StackedBarChart extends Component {
	GridStore store = new GridStore();
	JSObject font = new JSObject();
	JSObject legend = new JSObject();
	JSObject xstyle = new JSObject();
	JSObject s1a = new JSObject();
	JSObject s1b = new JSObject();
	JSObject s1c = new JSObject();
	JSObject s1d = new JSObject();
	JSObject s1e = new JSObject();
	private DataEventListener callback=null;
	
	public StackedBarChart(ObjectPool pool) {
		super(pool);
		
		setAttribute("store",store);
		store.setUrl("Dispatch?_evt=reqLoadContent&_obj=" + getId());
		setAttribute("yField", "year");
		store.setAttribute("autoLoad", true);
		store.addField("year");
		store.addField("comedy");
		store.addField("action");
		
		s1a.setAttribute("xField","comedy");
		s1a.setAttribute("displayName", "Bandwidth");
		s1b.setAttribute("xField","action");
		s1b.setAttribute("displayName", "Max Bandwidth");
		s1a.arrayAppend(s1b);
		setAttribute("series",s1a);
		
		font.setAttribute("family", "tahoma");
		font.setAttribute("size",13);
		
		legend.setAttribute("display", "none");
		legend.setAttribute("padding", 5);
		legend.setAttribute("font", font);
		
		xstyle.setAttribute("legend", legend);
		
		setAttribute("extraStyle", xstyle);
	}
	
	@Override
	public String getNamespace() {
		return "Ext.chart.StackedBarChart";
	}

	@Override
	public String getXtype() {
		return "stackedbarchart";
	}
	
	public void reqLoadContent(AjaxEvent jxe) {
		if(callback!=null)
			try {
				callback.onLoadContent(jxe.getReponse().getWriter(),jxe);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	}
	
	public void setDataListener(DataEventListener cb) {
		callback = cb;
	}
}
