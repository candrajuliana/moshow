package com.bigjava.jsf;

public abstract class Container extends Component {
	
	public Container(ObjectPool pool) {
		super(pool);
	}
	
	public void addComponent(Component c)  {
		JSAttribute attr = getAttribute("items");
		
		if(attr == null) {
			setAttribute("items",c);
		} else {
			JSObject head = (JSObject) attr.getValue();
			head.arrayAppend(c);
		}
	}
	
	public void setLayout(String layout) {
		setAttribute("layout",layout);
	}
}
