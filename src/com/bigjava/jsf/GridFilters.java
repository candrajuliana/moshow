package com.bigjava.jsf;

public class GridFilters extends JSObject {

	public String toString() {
		return "new Ext.ux.grid.GridFilters(" + super.toString() + ")";
	}
	
	public void setFilters() {
		JSObject type1 = new JSObject();
		type1.setAttribute("type", "numeric");
		type1.setAttribute("dataIndex", "no");
		JSObject type2 = new JSObject();
		type2.setAttribute("type", "string");
		type2.setAttribute("dataIndex", "username");

		type1.arrayAppend(type2);
		setAttribute("filters",type1);
	}
	
//	var filters = new Ext.ux.grid.GridFilters({
//	filters:[
//		{type: 'numeric',  dataIndex: 'id'},
//		{type: 'string',  dataIndex: 'name'},
//		{type: 'numeric', dataIndex: 'price'},
//		{type: 'date',  dataIndex: 'dateAdded'},
//		{
//			type: 'list',  
//			dataIndex: 'size', 
//			options: ['extra small', 'small', 'medium', 'large', 'extra large'],
//			phpMode: true},
//		{type: 'boolean', dataIndex: 'visible'}
//	                                    		]});
}
