package com.bigjava.jsf;

public class NumericAxis extends JSObject {

	public String toString() {
		return "new Ext.chart.NumericAxis(" + super.toString() + ")";
	}
	
	public void setStackingEnabled() {
		setAttribute("stackingEnabled",true);
	}
	
	public void setDisplayName(String text) {
		setAttribute("displayName",text);
	}
	
	public void setLabelRenderer(NumberRenderer nr) {
		setAttribute("labelRenderer",nr);
	}
	
	/*
	 xAxis: new Ext.chart.NumericAxis({
         stackingEnabled: true,
         labelRenderer: Ext.util.Format.usMoney
     }),*/
	
//	yAxis: new Ext.chart.NumericAxis({
//  displayName: 'Visits',
//  labelRenderer : Ext.util.Format.numberRenderer('0,0')
//})
	
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
