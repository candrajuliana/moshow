package com.bigjava.dpi.gui;

import com.bigjava.jsf.AjaxEvent;
import com.bigjava.jsf.Component;
import com.bigjava.jsf.ObjectPool;
import com.bigjava.jsf.Region;
import com.bigjava.jsf.SelectionEventListener;
import com.bigjava.jsf.TreeNode;
import com.bigjava.jsf.TreePanel;

/**
 * Menu treepanel (region west)
 * @author randikajonan 10feb2011
 */
public class Explorer extends TreePanel implements SelectionEventListener {
	MainScreen scrMain;
	TreePanel VDPI;
	
	public Explorer(ObjectPool pool, MainScreen screen) {
		super(pool);
		scrMain = screen;

	}
	
	/**
	 * load PMDB, SMDB, MDB for VDPI and Moreto
	 * @author Yulius 21 oct 2010
	 */
	public void init(AjaxEvent jxe) {

		setRegion(Region.WEST);
		getRoot().setAttribute("nodeType", "async");
		setRootVisible(false);
		setSelectionListener(this);
		setSplit(true);
		setWidth(200);
		setFloatable(false);
		setCollapsible(true);
		initData();
	}
	
	/**
	 * load tree panel VDPI and Moreto
	 * @author Yulius
	 * @modif Jonan
	 */
	public void initData() {
		
		VDPI = new TreePanel(pool);
		VDPI.setAttribute("title", "Bigjava MAG");
		VDPI.setRootVisible(false);
		VDPI.setSelectionListener(this);
		VDPI.setSplit(true);
		VDPI.setAttribute("bodyStyle", "background:#dfe8f6 none no-repeat;");
		setArrayAttribute("items", VDPI);
		
		//MENU DASHBOARD VDPI
		TreeNode dash = new TreeNode(this);
		dash.setTreenode(true, "wheel_white.png", "Dashboard", "dash");
		dash.setAttribute("hidden",true);

		TreeNode sm = new TreeNode(this);
		sm.setTreenode(true, "wheel_white.png", "MoShow", "moshow");
		dash.arrayAppend(sm);

		
		VDPI.setNodes(dash);
	}
		
	/**
	 * @author benniadham
	 */
	public void onSelectionChange(Component com) {
		TreePanel tree = (TreePanel)com;
		TreeNode node = tree.getSelectedNode();
		scrMain.onExplorerSelection(node);
	}
}