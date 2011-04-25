package com.bigjava.jsf;

/**
 * 
 * @author randikajonan
 *
 */
public class ScriptTagProxy extends JSObject {

	public String toString() {
		return "new Ext.data.ScriptTagProxy(" + super.toString() + ")";
	}
	
	public void setUrl(String url) {
		setAttribute("url",url);
	}
	
//	new Ext.data.ScriptTagProxy({

//        url: 'http://localhost:8080/vdpi/Export'
//    })
	
}
