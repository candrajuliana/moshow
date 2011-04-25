package com.bigjava.jsf;

import java.util.HashMap;
import com.bigjava.util.StringList;

public class ObjectPool {
	private HashMap<String,Component> hash = new HashMap<String,Component>();
	private HashMap<String,Component> scope = new HashMap<String,Component>();
	private StringList script = new StringList();
	
	public void register(Component c) {
		if(!isRegistered(c))
			hash.put(c.getId(), c);
	}
	
	public Component get(String key) {
		return hash.get(key);
	}
	
	public void destroy(String key) {
		hash.remove(key);
	}
	
	public void resetScript() {
		scope.clear();
		script.clear();
	}
	
	public void addScope(Component c) {
		if(scope.containsKey(c.getId())==false)
		{
			scope.put(c.getId(), c);
			String line = String.format("var %s=Ext.getCmp('%s');", c.getId(),c.getId());
			script.add(line);
		}
	}
	
	public void scriptSetAttribute(Component c, JSAttribute attr) {
		addScope(c);
		//String line = String.format("%s.%s=%s;", c.getId(),attr.getName(),attr.getValue().toString());
		String funcname = "set" + attr.getName().substring(0, 1).toUpperCase() + attr.getName().substring(1);
		String line = String.format("%s.%s(%s);", c.getId(),funcname,attr.getValue().toString());
		script.add(line);
	}
	
	public void scriptCallFunction(Component c, String command) {
		addScope(c);
		String line = String.format("%s.%s;", c.getId(),command);
		script.add(line);
	}
	
	public void scriptCreateInstance(Component c) {
		if(scope.containsKey(c.getId())==false)
		{
			scope.put(c.getId(), c);
			String line = String.format("var %s= new %s(%s);", c.getId(),c.getNamespace(),c.getHead());
			script.add(line);
			register(c);
		}
	}
	
	/**
	 * @author randikajonan
	 * create custom script
	 */
	public void scriptCreateTask(String line) {
		script.add(line);
		//Ext.TaskMgr.start(task);
	}
	
	public String getScript()  {
		return script.toString("\r");
	}
	
	public boolean isRegistered(Component c) {
		return hash.containsValue(c);
	}
}
