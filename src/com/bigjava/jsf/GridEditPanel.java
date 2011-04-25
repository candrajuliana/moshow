package com.bigjava.jsf;

import java.io.IOException;


public class GridEditPanel  extends Panel {
	private GridStore store = new GridStore();
	private PagingToolbar paging = new PagingToolbar();//1
	//private GridFilters filters = new GridFilters();//1
	private int selectedRow=-1;
	private SelectionEventListener callback=null;
	private DataEventListener content=null;
	
	public GridEditPanel(ObjectPool pool) {
		super(pool);

		// store.setAttribute("autoLoad", true);
		store.setAttribute("url", "Dispatch?_evt=reqLoadContent&_obj=" + this.getId());
		this.setAttribute("store", store);
		this.setAttribute("ds", store);
	
		this.setAttribute("frame", true);
		this.setAttribute("clicksToEdit", 1);
		
//		PAGING
//		paging.setStore(store);
		paging.setPageSize(5);//1*		
		paging.setDisplayInfo(true);
		paging.setDisplayMsg("Displaying topics {0} - {1} of {2}");
		paging.setEmptyMsg("No topics to display");
//		plugins: filters
//		filters.setFilters();
//		paging.setAttribute("plugins", filters);
		this.setAttribute("bbar", paging);
		//System.out.println("\n\nJNN - TOOLBAR : " + this.toString());
		
		setAttribute("sm", new JSCode("new Ext.grid.RowSelectionModel({ " +
                    "singleSelect: false, " +
                    "listeners: { " +
                    "    rowselect: function(sm, row, rec) { "+
                    "        jfm_request('onRowSelectionChange', '" + getId() + "', '&rowid='+row); grid.startEditing(0, 0);"+
                    "    }" +
                    "}" +
                "})"));
		

			
		
//		store.setAttribute("listeners", new JSCode(
//				"{" +
//				"	load: function(st, records, opts) {" +
//				"		var grid = Ext.getCmp('"+getId()+"'); if(grid!=null){ grid.setAutoScroll(true);} " +
//				"		}" +
//				"}"));
		
		setAutoScroll(true);
		//setBorder(false);
	}
	
	@Override
	public String getNamespace() {
		return "Ext.grid.RowEditor";
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
