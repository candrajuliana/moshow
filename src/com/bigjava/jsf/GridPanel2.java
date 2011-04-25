package com.bigjava.jsf;

import java.io.IOException;

/**
 * @author randikajonan
 *
 */
public class GridPanel2  extends Panel {
	JsonStore store = null; 
	public PagingToolbar paging = new PagingToolbar();
	public int selectedRow=-1;
	public SelectionEventListener callback=null;
	public DataEventListener content=null;
	
	public GridPanel2(ObjectPool pool) {
		super(pool);

		paging.setPageSize(20);	
		paging.setDisplayInfo(true);
		paging.setDisplayMsg("Displaying data {0} - {1} of {2}");
		paging.setEmptyMsg("No data to display");
				
        this.setAttribute("loadMask", true);   
		this.setAttribute("bbar", paging);
		setAutoScroll(true);
//		this.setAttribute("listeners",new JSCode("{ 'rowdblclick': function(g) {" +
//				"var s= g.getSelectionModel();" +
//				"var r = s.getSelected();" +
//				"Ext.Msg.alert('Selected Row',r.get('id'));" +
//				"Ext.TaskMgr.start(task);" +
//			"}}"));
	}
	
	public void setJsonStore(JsonStore i)
	{
		store = i;
		
		this.setAttribute("store", new JSCode(store.getId()));
		paging.setStore(store);
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
				e.printStackTrace();
			}
	}
	
	public void addColumn(String header, String field, String type, int width, boolean sortable, Boolean index, String dateFormat, JSCode renderer, Boolean hide) {
		GridColumn col = new GridColumn();
		col.setAttribute("header", header);
		col.setAttribute("dataIndex", field);
		col.setAttribute("width", width);
		col.setAttribute("sortable",sortable);
		col.setAttribute("hidden",hide);
		
		/**
		 * to-do
		 * ksh attribute hide pada column
		 */
		
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
