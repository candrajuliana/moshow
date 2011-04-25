package com.bigjava.jsf;

import java.io.IOException;

/**
 * @author benniadham
 * @modif randikajonan
 *
 */
public class GridPanel  extends Panel {
	public GridStore store = new GridStore();
	public PagingToolbar paging = new PagingToolbar();
	public int selectedRow=-1;
	public SelectionEventListener callback=null;
	public DataEventListener content=null;
	
	public GridPanel(ObjectPool pool) {
		super(pool);

		// store.setAttribute("autoLoad", true);
		store.setAttribute("url", "Dispatch?_evt=reqLoadContent&_obj=" + this.getId());
//		store.setScriptTagProxy("Dispatch?_evt=reqLoadContent&_obj=" + this.getId());
		this.setAttribute("store", store);
//		this.setAttribute("ds", store);

//		JSObject item1 = new JSObject();
//	    item1.setAttribute("region", "Region.CENTER");
//	    item1.setAttribute("split", true);
//		item1.setAttribute("xtype", "textfield");
//		item1.setAttribute("name", "field1");
//		item1.setAttribute("fieldLabel", "Search ");
		
//		PAGING
//		paging.setStore(store);
		paging.setPageSize(5);	
		paging.setDisplayInfo(true);
		paging.setDisplayMsg("Displaying topics {0} - {1} of {2}");
		paging.setEmptyMsg("No topics to display");
		
		//Dispatch?start=25&limit=25&sort=lastpost&dir=DESC&_dc=1295471393433&callback=stcCallback1002
		
		this.setAttribute("bbar", paging);
		
//		setAttribute("sm", new JSCode("new Ext.grid.RowSelectionModel({ " +
//                    "singleSelect: true, " +
//                    "listeners: { " +
//                    "    rowselect: function(sm, row, rec) { "+
//                    "        jfm_request('onRowSelectionChange', '" + getId() + "', '&rowid='+row);"+
//                    "    }" +
//                    "}" +
//                "})"));
		
		setAutoScroll(true);
		
	}
	
	@Override
	public String getNamespace() {
		return "Ext.grid.GridPanel";
	}

	@Override
	public String getXtype() {
		return "grid";
	}
	
	public int getSelectedRowIndex() {
		return selectedRow;
	}
	
	public void onRowSelectionChange(AjaxEvent jxe) {
		if(jxe.getRequest().getParameter("rowid")!=null) {
			this.selectedRow = Integer.parseInt(jxe.getRequest().getParameter("rowid"));
		}
		
		if(callback!=null) {
			callback.onSelectionChange(this);
		}
	}
	
	public void reqLoadContent(AjaxEvent jxe) {
		if(content!=null)
			try {
				content.onLoadContent(jxe.getReponse().getWriter(),jxe);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	}
	
	public void addColumn(String header, String field, String type, int width, boolean sortable, Boolean index, String dateFormat, JSCode renderer) {
		GridColumn col = new GridColumn();
		col.setAttribute("header", header);
		col.setAttribute("dataIndex", field);
		col.setAttribute("width", width);
		col.setAttribute("sortable",sortable);
		
		if(renderer!=null) {
			col.setAttribute("renderer",renderer);
		}
		
		JSObject storefield = new JSObject();
		storefield.setAttribute("name",field);
		
		if(index != null && index) {
			storefield.setAttribute("id", field);
		}
		
		if(type!=null && type.length()>0) {
			storefield.setAttribute("type", type);
		}
		
		if(dateFormat != null && dateFormat.length()>0) {
			storefield.setAttribute("dateFormat", dateFormat);
		}
		
		setArrayAttribute("columns", col);
		store.setArrayAttribute("fields", storefield);
	}
	
	public void setSelectionListener(SelectionEventListener cb) {
		callback = cb;
	}
	
	public void setDataListener(DataEventListener cb) {
		content = cb;
	}
	
	public void setStripeRows(boolean stripeRows) {
		setAttribute("stripeRows", stripeRows);
	}
}
