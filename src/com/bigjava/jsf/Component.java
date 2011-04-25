/**
 * 
 */
package com.bigjava.jsf;

/**
 * @author benniadham
 *
 */
public abstract class Component extends JSObject {
	private String objectId=null;
	// private String xtype="";
	// private String namespace="";
	private static int id=0;
	protected ObjectPool pool;
	
	Component(ObjectPool pool) 
	{
		objectId = this.getClass().getSimpleName() + Integer.toString(++id);
		this.pool = pool;
		super.setAttribute("id", objectId);
		//pool.insert(this);
		initComponent();
	}
	
	private void initComponent() 
	{
		//this.xtype = xtype;
		//this.namespace=namespace;
		super.setAttribute("xtype", getXtype());
	}
	
	public String getId() {
		return objectId;
	}
	
	/*
	public void setId(String id) {
		objectId = id;
	}*/

	public String getConfig() {
		if(!pool.isRegistered(this))
			pool.register(this);
		return super.toString();
	}

	public String toString() {
		return getConfig();
	}
	
	public JSAttribute setAttribute(String name, Object val) {
		JSAttribute attr = super.setAttribute(name, val);
		
		// Only generate script if the object is already registered/created
		if(pool.isRegistered(this)) {
			pool.scriptSetAttribute(this, attr);
			// String funcname = name.
		}
		return attr;
	}
	
	public void show() {
		pool.scriptCallFunction(this, "show()");
	}
	
	public void hide() {
		pool.scriptCallFunction(this, "hide()");
	}
	
	public void renderTo(String div) {
		pool.scriptCallFunction(this, "render('"+div+"')");
	}
	
	public abstract String getNamespace();
	public abstract String getXtype();
}
