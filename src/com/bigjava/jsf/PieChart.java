package com.bigjava.jsf;

import java.io.IOException;

public class PieChart extends Component {
	private GridStore store = new GridStore();
	private JSObject font = new JSObject();
	private JSObject legend = new JSObject();
	private JSObject xstyle = new JSObject();
	private DataEventListener callback=null;
	
	public PieChart(ObjectPool pool) {
		super(pool);
		
		setAttribute("store",store);
		store.setUrl("Dispatch?_evt=reqLoadContent&_obj=" + getId());
		store.setAttribute("autoLoad", true);
		
		setAttribute("categoryField", "category");
		setAttribute("dataField", "data");
		
		store.addField("category");
		store.addField("data");
		
		
		font.setAttribute("family", "tahoma");
		font.setAttribute("size",13);
		
		legend.setAttribute("display", "right");
		legend.setAttribute("padding", 5);
		legend.setAttribute("font", font);
		
		xstyle.setAttribute("legend", legend);
		
		setAttribute("extraStyle", xstyle);
	}
	
	@Override
	public String getNamespace() {
		return "Ext.chart.PieChart";
	}

	@Override
	public String getXtype() {
		return "piechart";
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
