package com.bigjava.jsf;

import java.io.IOException;

public class TreePanel extends Panel {
	private int nodeCount=0;
	private TreeNode root=new TreeNode(this);
	private String selectedId="";
	
	protected String nextNodeId() {
		if(nodeCount >= Integer.MAX_VALUE)
			nodeCount=0;
		
		return String.format("node%d", ++nodeCount);
	}
	
	private SelectionEventListener onSelectCallback=null;
	
	public TreePanel(ObjectPool pool) {
		super(pool);
		
		setAttribute("root",root); 
		setAttribute("listeners", new JSCode(
			"{" +
			"	render: function(tp) {" +
			"		tp.getSelectionModel().on('selectionchange', " +
			"					function(tree,node) {"	+
			"						jfm_request('onSelectionChange', '" + getId() + "', '&nodeid='+node.id);" +
			"					})"	+
			"		}" +
			"}"));
		
		setDataUrl("Dispatch?_evt=onLoadContent&_obj="+getId());
	}
	
	@Override
	public String getNamespace() {
		return "Ext.tree.TreePanel";
	}

	@Override
	public String getXtype() {
		return "treepanel";
	}
	
	public void setNodes(TreeNode nodes) {
		root.setAttribute("children", nodes);
	}
	
	public void onSelectionChange(AjaxEvent jxe) {
		if(jxe.getRequest().getParameter("nodeid")!=null) {
			selectedId = jxe.getRequest().getParameter("nodeid");
		}
		
		if(onSelectCallback != null)
			onSelectCallback.onSelectionChange(this);
	}
	
	public void onLoadContent(AjaxEvent jxe) {
		try {
			TreeNode nodes = null;
			JSAttribute attr = root.getAttribute("children");
			if(attr!=null)
				nodes = (TreeNode) attr.getValue();
			
			if(nodes!=null)
				jxe.getReponse().getWriter().write(nodes.toString());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public String getSelectedNodeId() {
		return selectedId;
	}
	
	public TreeNode getSelectedNode() {
		if(selectedId!=null && selectedId.length()>0) {
			return root.getById(selectedId);
		}
		
		return null;
	}
	
	public void setSelectionListener(SelectionEventListener e) {
		onSelectCallback=e;
	}
	
	public void setRootVisible(boolean val) {
		setAttribute("rootVisible",val);
	}
	
	public void setDataUrl(String url) {
		setAttribute("dataUrl", url);
	}
	
	public void setUseArrows(boolean val) {
		setAttribute("useArrows", val);
	}
	
	public void setFrame(boolean frame) {
		setAttribute("frame",frame);
	}
	
	public void setContainerScroll(boolean val) {
		setAttribute("containerScroll",val);
	}
	
	public TreeNode getRoot() {
		return root;
	}
}
