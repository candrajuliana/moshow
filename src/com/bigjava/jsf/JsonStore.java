package com.bigjava.jsf;

/**
 * 
 * @author randikajonan
 *
 */
public class JsonStore extends Component {

	public JsonStore(ObjectPool pool) {
		super(pool);
	}
	
//	public String toString() {
//		return "new Ext.data.JsonStore(" + super.toString() + ")";
//	}
	
	public void addField(String name, String tp) {
		JSObject field = new JSObject();
		field.setAttribute("name", name);
		field.setAttribute("type", tp);
		setArrayAttribute("fields", field);
	}
	
	public void setScriptTagProxy(String url)
	{
		ScriptTagProxy proxy = new ScriptTagProxy();
		proxy.setUrl(url);
		setAttribute("proxy", proxy);
	}
	
	public void setRoot(String i)
	{
		setAttribute("root",i);
	}
	
	public void setTotalProperty(String i)
	{
		setAttribute("totalProperty",i);
	}
	
	public void setIdProperty(String i)
	{
		setAttribute("idProperty",i);
	}
	
	public void setRemoteSort(boolean i)
	{
		setAttribute("remoteSort",i);
	}

	public String getNamespace() {
		return "Ext.data.JsonStore";
	}

	public String getXtype() {
		return "jsonstore";
	}
}

/*
[{id:8,address:'234'},{id:2,address:'jonan@bigjava.com'},{id:3,address:'qwe@we.com'},{id:4,address:'a@a.com'},{id:5,address:'a@a.com'},{id:7,address:'123'},{id:9,address:'345'},{id:10,address:'456'},{id:11,address:'567'}]


{"topics":[{"id":"8","address":"234"},{"id":"2","address":"jonan@bigjava.com"},{"id":"3","address":"qwe@we.com"},{"id":"4","address":"a@a.com"},{"id":"5","address":"a@a.com"}],"totalCount":9} 

*/