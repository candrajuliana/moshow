//update by jonan 02 des 2010
package com.bigjava.jsf;

public class TreeNode extends JSObject {
	private String id = "";
	private Object tag = null;
	int machineId;
	String machineIp;
	
	public TreeNode(TreePanel panel)
	{
		id = panel.nextNodeId();
		setAttribute("id", id);
	}
	
	/**
	 * get id of treenode
	 * @author randikajonan
	 * 22jan 2011
	 */
	public String getId()
	{
		return id;
	}
	
	public void setLeaf(boolean leaf) {
		setAttribute("leaf",leaf);
	}
	
	/** set treenode leaf,text agar jadi satu dan tidak berulang
	 * @author yulius eka 11 nov 2010
	 */
	public void setTreenode(boolean leaf,String icon,String message,Object tag) {
		setAttribute("leaf",leaf);
		setAttribute("text", String.format("<img src=\"./res/%s\" height=\"16\" />%s",icon,message));
		this.tag=tag;
	}
	public void setTagText(Object tag,String message) {
		this.tag=tag;
		setAttribute("text", String.format("%s",message));	
	}
	public void setAppend(String cabang,TreeNode duas,TreeNode tigas,TreeNode empats,TreeNode limas,TreeNode enams,TreeNode tujuhs,TreeNode delapans,TreeNode sembilans,TreeNode sepuluhs)
	{
		if (cabang.equalsIgnoreCase("dua"))
		{
		arrayAppend(duas);
		}
		if (cabang.equalsIgnoreCase("tiga"))
		{
		arrayAppend(duas);
		arrayAppend(tigas);
		}
		if (cabang.equalsIgnoreCase("empat"))
		{
		arrayAppend(duas);
		arrayAppend(tigas);
		arrayAppend(empats);
		}
		if (cabang.equalsIgnoreCase("lima"))
		{
		arrayAppend(duas);
		arrayAppend(tigas);
		arrayAppend(empats);
		arrayAppend(limas);
		}
		if (cabang.equalsIgnoreCase("enam"))
		{
		arrayAppend(duas);
		arrayAppend(tigas);
		arrayAppend(empats);
		arrayAppend(limas);
		arrayAppend(enams);
		}
			
	}
	
	public TreeNode getById(String ids) {
		TreeNode result=null;
		
		if(this.id.equalsIgnoreCase(ids))
			return this;
		
		// DFS search children first
		TreeNode child = getChild();
		if(child!=null)
			result = child.getById(ids);
		
		// if child failed turn to sibling
		if(result==null && getNext()!=null)
			result = ((TreeNode)getNext()).getById(ids);
		
		return result;
	}
	
	public TreeNode getChild() {
		JSAttribute attr = getAttribute("children");
		if(attr!=null) {
			return (TreeNode) attr.getValue();
		}
		return null;
	}
	
	public void setTag(Object tag) {
		this.tag=tag;
	}
	
	public Object getTag() {
		return tag;
	}
	
	/**
	 * @author randikajonan
	 * @param i
	 */
	public void setMachineAttrs(int id, String ip)
	{
		machineId=id;
		machineIp=ip;
	}
		
	/**
	 * @authoe randikajonan
	 * @return
	 */
	public int getMachineId()
	{
		return machineId;
	}
	public String getMachineIp()
	{
		return machineIp;
	}
}
