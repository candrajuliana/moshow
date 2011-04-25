package com.bigjava.jsf;

public class GridStoreStatic extends JSObject {

	public String toString() {
		return "new Ext.data.JsonStore(" + super.toString() + ")";
	}
	
	public void addField(String name) {
		JSObject field = new JSObject();
		field.setAttribute("name", name);
		setArrayAttribute("fields", field);
	}
	
	public void setUrl(String url) {
		setAttribute("url",url);
	}
	
	public void setData(String value)
	{
		JSObject data = new JSObject();
		data.setAttribute("abbr", value);
		data.setAttribute("state", value);
		setArrayAttribute("data",data);
	}
}

/*
var store = new Ext.data.JsonStore({
        fields:['name', 'visits', 'views'],
        data: [
            {name:'Jul 07', visits: 245000, views: 3000000},
            {name:'Aug 07', visits: 240000, views: 3500000},
            {name:'Sep 07', visits: 355000, views: 4000000},
            {name:'Oct 07', visits: 375000, views: 4200000},
            {name:'Nov 07', visits: 490000, views: 4500000},
            {name:'Dec 07', visits: 495000, views: 5800000},
            {name:'Jan 08', visits: 520000, views: 6000000},
            {name:'Feb 08', visits: 620000, views: 7500000}
        ]
    });

*/