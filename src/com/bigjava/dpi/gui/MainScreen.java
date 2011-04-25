package com.bigjava.dpi.gui;

import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

import com.bigjava.jsf.AjaxEvent;
import com.bigjava.jsf.GridPanel2;
import com.bigjava.jsf.JSString;
import com.bigjava.jsf.JsonStore;
import com.bigjava.jsf.Layout;
import com.bigjava.jsf.Panel;
import com.bigjava.jsf.Region;
import com.bigjava.jsf.TabNode;
import com.bigjava.jsf.TabPanel;
import com.bigjava.jsf.TreeNode;
import com.bigjava.jsf.Viewport;

public class MainScreen 
{

	//INIT VARIABEL GLOBAL
	//DESC : Untuk mendeklarasi variabel global
	//Panel
		Panel north=null;
		Panel center=null;
//		Panel east=null;
		Panel pnlWelcome=null;
		
	//Tab panel
		TabPanel tabPanelCenterAllDevices=null;//untuk panel center modul all devices
		
	//Global
		RequestPool pnlRequestPool=null;//untuk menampung jfm_request menu atas
		String userlogin="";
		Explorer explorer;
		Viewport view;
		AjaxEvent jxeMain;
		
	//Content from other class
	// example : MoretoAllDevicesCenter2 pnlMoretoAllDevicesCenter2=null;

		
	//TabNode

		
	//Paging
		JsonStore storeAllDevice;
		GridPanel2 gridPanelAllDeviceCenter=null;
		JsonStore storeReporting;
		GridPanel2 gridPanelReportingCenter=null;
		
	public void show(AjaxEvent jxe) {
		
		//INIT VARIABEL
		//DESC : Untuk mendeklarasikan variabel
		//1 - Paging
			storeAllDevice = new JsonStore(jxe.getObjectPool());
			gridPanelAllDeviceCenter = new GridPanel2(jxe.getObjectPool());
			gridPanelAllDeviceCenter.setJsonStore(storeAllDevice);
			storeReporting = new JsonStore(jxe.getObjectPool());
			gridPanelReportingCenter = new GridPanel2(jxe.getObjectPool());
			gridPanelReportingCenter.setJsonStore(storeReporting);
			
		//2 - Panel
			north = new Panel(jxe.getObjectPool());
			Panel south = new Panel(jxe.getObjectPool());
			center = new Panel(jxe.getObjectPool());
			pnlWelcome = new Panel(jxe.getObjectPool());
			Panel southmessage = new Panel(jxe.getObjectPool());
			
		//3 - TabPanel
			tabPanelCenterAllDevices = new TabPanel(jxe.getObjectPool()); //untuk tabpanel center modul all devices
			
		//4 - Global			
			view = new Viewport(jxe.getObjectPool());
			setExplo(jxe, explorer,view);
			pnlRequestPool = new RequestPool(jxe.getObjectPool(),this);	
			userlogin = jxe.getRequest().getSession().getAttribute("username").toString();
			jxeMain=jxe;
			
		//5 - Global			
			explorer = new Explorer(jxe.getObjectPool(), this);
			
		//6 - Content from other class
		//Example : pnlMoretoAllDevicesCenter2 = new MoretoAllDevicesCenter2(jxe.getObjectPool(),this,gridPanelAllDeviceCenter,jxe);

			
		//ADDING ATTRIBUTE
		//DESC : Tempat menambah segala bentuk attribute
			//1 - north
			north.setAttribute("bodyStyle", "background:#4e84b3 url(res/logo_vdpimanager.png) no-repeat ;");
			north.setAttribute("html", 
					"<br><br><table style=\"color:WHITE;font-weight:bold;\" width=\"100%\" border=\"0\"><tr><td width=\"30%\"></td><td width=\"70%\" align=\"right\">Welcome,"+userlogin+"<br> " +
					"<a class=\"menu\" href=\"#\" onclick=\"jfm_request(\\'reqStatusPanel\\',\\'"+pnlRequestPool.getId()+"\\',\\'\\')\" >Logout</a></td></tr></table>"); 
			north.setRegion(Region.NORTH);
			north.setHeight(70);
			north.setBorder(false);
			
		//2 - east
//			east.setTitle("<img src=\"./res/details.png\" />");
//			east.setAttribute("bodyStyle", "background:blue url(img/logo_vdpi_manager1.png) no-repeat ;");
//			east.setRegion(Region.EAST);
//			east.setLayout(Layout.CARD);
//			east.setCollapsible(true);
//			east.setWidth(300);
//			east.setFloatable(false);
//			east.setBorder(false);
			
		//3 - south
			south.setAttribute("bodyStyle", "background:#4e84b3 url(res/logo_vdpimanager1.png) no-repeat ;height:40;");
			south.setRegion(Region.SOUTH);
			south.setBorder(false);
			southmessage.setRegion(Region.CENTER);
			southmessage.setAttribute("baseCls", "plain");
			southmessage.setTitle("<div style=\"vertical-align:bottom\" align=\"center\"><img src=\"./res/copyright_vdpi_manager.png\" /></div>");
		//4 - center
			center.setRegion(Region.CENTER);
			center.setAttribute("bodyStyle", "background:#4e84b3 url(res/logo_vdpi_manager1.png) no-repeat ;");
			center.setLayout(Layout.CARD);
			center.setBorder(false);
		//5 - pnl di center
			pnlWelcome.setTitle("Welcome");
			pnlWelcome.setAttribute("bodyStyle", "background:#dfe8f6 url(res/logo_vdpi_manager1.png) no-repeat ;");
			pnlWelcome.setBorder(false);
			pnlWelcome.setAttribute("html", "" +
					"<table width=\"400\" align=\"center\" height=\"300\" border=\"0\""+
					"cellpadding=\"0\" cellspacing=\"10\">"+
					    "<tr>"+
					        "<td colspan=\"3\">"+
					            "<div align=\"center\" style=\"font-family:Arial, Helvetica, sans-serif; font-weigh:bold;font-size:18px;\">"+
					                "<img src=\"res/welcome.png\" height=\"50\" />"+
					           "</div>"+        
					           "</td>"+
					    "</tr>"+
					    
					    "<tr>"+
					        "<td width=\"128\" style=\"padding-right:20px;\">"+
					            "<img src=\"res/wel_dm.png\" width=\"139\"/>            </td>"+
					        "<td style=\"padding-right:20px;\">"+
					            "<img src=\"res/wel_sm.png\" width=\"139\"/>            </td>"+
					        "<td>"+
					            "<img src=\"res/wel_pm.png\" width=\"139\"/>            </td>"+
					    "</tr>"+
					"</table>" +
					"");

			view.setLayout(Layout.BORDER);
			explorer.setAttribute("bodyStyle", "background:#dfe8f6 url(img/logo_vdpi_manager1.png) no-repeat ;");

		
		//ADDING COMPONENT
		//DESC : Tempat Menambahkan component
			view.addComponent(south);
			view.addComponent(north);
			view.addComponent(center);
			center.addComponent(pnlWelcome);
			center.addComponent(tabPanelCenterAllDevices);
			south.addComponent(southmessage);
		
		
		//RUNNING METHODS
		//DESC : Tempat untuk menjalankan method
			String task = String.format("var refreshGrid = {" +
					" run: function(){" +
						"Ext.getCmp('"+gridPanelAllDeviceCenter.getId()+"').getStore().reload();" +
						"Ext.getCmp('"+gridPanelReportingCenter.getId()+"').getStore().reload();" +
						"}," +
					"interval: 10000" +
					"};" +
					"Ext.TaskMgr.start(refreshGrid);");
			jxe.getObjectPool().scriptCreateTask(task);//task manager
			refreshExplo(jxe, explorer);
			createStore(jxe);
			
			
		//SETTING ACTIVE PANEL
		//DESC : Untuk men-set panel yg aktif pada kondisi awal
			center.setActivePanel(pnlWelcome);			
			
		//ADDING ATTRIBUTES FOR TABNODE
		//DESC : Tempat menambah segala bentuk attribute

			
		//ITEMS
		setMainScreenItems();
			
		//ADDING TABS
		addTabs();
	}
	
	/**
	 * Tempat untuk menambah items
	 * @author randikajonan
	 */
	public void setMainScreenItems()
	{
		//DESC : Tempat untuk menambah items tipe class
		//Example :	tabNodeCenterAllDevices2.setAttribute("items", pnlMoretoAllDevicesCenter2);

	}
	
	/**
	 * Tempat untuk menambah tab baru
	 * @author randikajonan
	 */
	public void addTabs()
	{

	}
	
	public void onExplorerSelection(TreeNode node) 
	{			
		if(node!=null && node.getTag() != null) 
		{
			String tag = node.getTag().toString();
			if(tag.equalsIgnoreCase("moshow")) {
					center.setActivePanel(tabPanelCenterAllDevices);
					tabPanelCenterAllDevices.setActiveTab(0);
			}
		}
		else
		{	center.setActivePanel(tabPanelCenterAllDevices);
			tabPanelCenterAllDevices.setActiveTab(0);
		}
		if(node!=null) {
			JSString obj = (JSString)node.getAttribute("text").getValue();
			north.setTitle(obj.getStringValue());
		}
	}
	
	/**
	 * @author yulius
	 * @param jxe
	 * @param explorer1
	 * @param view1
	 */
	public void setExplo(AjaxEvent jxe,Explorer explorer1,Viewport view1)
	{
		explorer=explorer1;
		view = view1;	
	}

	/**
	 * @author yulius
	 * @param jxe
	 * @param explorers
	 */
	public void refreshExplo(AjaxEvent jxe, Explorer explorers) 
	{

		explorer=explorers;
		explorer.init(jxe);
		view.addComponent(explorer);
		jxe.getObjectPool().scriptCreateInstance(view);
		
	}

	/**
	 * @author jonan
	 * @param jxe
	 */
	public void createStore(AjaxEvent jxe) {
		//all devices
		storeAllDevice.setRoot("alldevice");
		storeAllDevice.setTotalProperty("totalCount");
		storeAllDevice.setIdProperty("threadid");
		storeAllDevice.setRemoteSort(true);
		storeAllDevice.setScriptTagProxy(getUrlStore());
		jxe.getObjectPool().scriptCreateInstance(storeAllDevice);
		jxe.getObjectPool().scriptCallFunction(storeAllDevice, String.format("setBaseParam('_evt','reqLoadContent')"));
		jxe.getObjectPool().scriptCallFunction(storeAllDevice, String.format("setBaseParam('_obj','%s')",gridPanelAllDeviceCenter.getId()));
		jxe.getObjectPool().scriptCallFunction(storeAllDevice, String.format("load({params:{start:0,limit:20}})"));
		
		//reporting
		storeReporting.setRoot("reporting");
		storeReporting.setTotalProperty("totalCount");
		storeReporting.setIdProperty("threadid");
		storeReporting.setRemoteSort(true);
		storeReporting.setScriptTagProxy(getUrlStore());
		jxe.getObjectPool().scriptCreateInstance(storeReporting);
		jxe.getObjectPool().scriptCallFunction(storeReporting, String.format("setBaseParam('_evt','reqLoadContent')"));
		jxe.getObjectPool().scriptCallFunction(storeReporting, String.format("setBaseParam('_obj','%s')",gridPanelReportingCenter.getId()));
		jxe.getObjectPool().scriptCallFunction(storeReporting, String.format("load({params:{start:0,limit:20}})"));
		
	}
	
	/**
	 * Load url store
	 * @author Jonan
	 */
	public String getUrlStore() {
		String url="";
		ResultSet rs=null;
		
		InitialContext initContext;
		Context envContext;
		try {
			initContext = new InitialContext();
			envContext = (Context)initContext.lookup("java:/comp/env");
			
			DataSource ds = (DataSource)(envContext).lookup("jdbc/DpiDB");
			Connection conn = ds.getConnection();
			Statement st = conn.createStatement();
			
			String sql = String.format("select url from url_ipaddress where flag='urlstore';");
			rs = st.executeQuery(sql);
			while(rs.next()) {
				url = rs.getString(1);
			}
			rs.close();
			st.close();
			conn.close();
		} catch (NamingException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return url;
	}
}