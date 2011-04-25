//Last updated by jonan - 18 nov 2010
//- addsubmitbutton

package com.bigjava.jsf;

import java.io.ByteArrayInputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;

import javax.servlet.ServletOutputStream;


/*import java.io.PrintWriter;*/

/*import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;*/
 
public class FormPanel extends Panel {
	private FormPanelEventListener fpelCallback;
	private ClickEventListener	celCallback;
	private int nilai;
	String namafiles;
	String kueris;
	String sql;
	/*yuna
	 * set date
	 */
	String Awalan;
	String Akhiran;
	
	public FormPanel(ObjectPool pool) {
		super(pool);
		
		String url = String.format("Dispatch?_obj=%s&_evt=%s", getId(), "reqSubmitted");
		setUrl(url);
		setAttribute("method","GET");
	}
	
	@Override
	public String getNamespace() {
		return "Ext.form.FormPanel";
	}

	@Override
	public String getXtype() {
		return "form";
	}
	
	public void setClickEventListener(ClickEventListener c) {
		celCallback = c;
	}
	
	public void setEventListener(FormPanelEventListener c) {
		fpelCallback = c;
	}
	
	public void setLabelWidth(int width){
		setAttribute("labelWidth", width);
	}
	
	public void setUrl(String url){
		setAttribute("url", url);
	}
	
	public void setBackgroundColor(String bgcolor) {
		setAttribute("bgcolor", bgcolor);
	}
	
	public void setTitle(String title){
		setAttribute("title", title);
	}
	
	public void setDefaultType(String defaulttype){
		setAttribute("defaultType", defaulttype);
	}
	
	public void setMonitorValid(boolean status){
		setAttribute("monitorValid", status);
	}
	
	public void setRegion(String region) {
		setAttribute("region", region);
	}	
	
	public void addTextField(String name, String label) {
		JSObject item = new JSObject();
		item.setAttribute("xtype", "textfield");
		item.setAttribute("name", name);
		item.setAttribute("fieldLabel", label);
		setArrayAttribute("items",item);
	}
	
	public void addTextArea(String name, String label, String w, String h) { 
		JSObject item = new JSObject();
		item.setAttribute("xtype", "textarea");
		item.setAttribute("name", name);
//		item.setAttribute("fieldLabel", label);
		item.setAttribute("width", w);
		item.setAttribute("height", h);
		setArrayAttribute("items",item);
	}
	
	public void addPasswordField(String name, String label) {
		JSObject item = new JSObject();
		item.setAttribute("xtype", "textfield");
		item.setAttribute("name", name);
		item.setAttribute("fieldLabel", label);
		item.setAttribute("inputType", "password");
		setArrayAttribute("items",item);
	}
	
	public void setFrame(boolean frame) {
		setAttribute("frame",frame);
	}
	
	public void addButton(String text) {
		JSObject button = new JSObject();
		button.setAttribute("text", text);
		button.setAttribute("listeners", new JSCode(
				"{" +
				"	click: function(button, event_object) {" +
				"		jfm_request('reqClick', '" + getId() + "', '&buttonid='+button.id);" +
				"		}" +
				"}"));
		setArrayAttribute("buttons",button);
	}
	
	public void addDummyButton() {
		JSObject button = new JSObject();
		button.setAttribute("hidden",true);
		setArrayAttribute("buttons",button);
	}
	
	public void setNilai(int nilai)
	{
		this.nilai = nilai;
	}
	
	public void addResetButton() {
		JSObject button = new JSObject();
		button.setAttribute("text", "Reset");
		button.setAttribute("handler", new JSCode("function() { var form = Ext.getCmp('" +
				getId() +
				"'); form.getForm().submit({ waitMsg: 'Loading...', " + 
				"success: function(form, action) { form.reset(); }, "+ 
				"failure: function(form, action) { form.reset(); }});}"));

		setArrayAttribute("buttons",button);
	}
	
	public void addSubmitButton(String text, String waitMsg) {
		JSObject button = new JSObject();
		button.setAttribute("xtype", "button");
		button.setAttribute("text", text);
		button.setAttribute("handler", new JSCode("function() { var form = Ext.getCmp('" +
				getId() +
				"'); form.getForm().submit({ waitMsg: '" + waitMsg + "', " + 
				"success: function(form, action) { action.result.action(form,action); form.reset(); }, "+ 
				"failure: function(form, action) { Ext.Msg.alert(action.result.title, action.result.message); form.reset(); }});}"));
		
		button.setAttribute("listeners", new JSCode(
				"{" +
				"	click: function() {" +
				"		Ext.Msg.alert('Test', 'Isi');" +
				"		}" +
				"}"));
		setArrayAttribute("buttons",button);
//		System.out.println("Formpanel - button : "+button);
	}
	
	public void addSearchButton(String text, String waitMsg) {
		JSObject button = new JSObject();
		button.setAttribute("xtype", "button");
		button.setAttribute("text", text);
		button.setAttribute("handler", new JSCode("function() { var form = Ext.getCmp('" +
				getId() +
				"'); form.getForm().submit({ waitMsg: '" + waitMsg + "', " + 
				"success: function(form, action) { Ext.Msg.alert('Teeeeeest', 'oi sukses'); }, "+ 
				"failure: function(form, action) { Ext.Msg.alert(action.result.title, action.result.message); form.reset(); }});}"));
		setArrayAttribute("buttons",button);
	}
	
	public void addPrintButton(AjaxEvent jxe,String kueri) {
			
		 	JSObject button = new JSObject();
			button.setAttribute("text", "Print");
			button.setAttribute("listeners", new JSCode(
					"{" +
					"	click: function(button, event_object) {" +
					"		jfm_request('prin', '" + getId() + "', '&buttonid='+button.id);" +
					"		}" +
					"}"));
			setArrayAttribute("buttons",button);
			setSqlTraffic();
	}
	
	public void addPrintButtonTest(AjaxEvent jxe,String kueri, String awalan, String akhiran) {
		Awalan = awalan;
		Akhiran = akhiran;
	 	JSObject button = new JSObject();
		button.setAttribute("text", "Print");
		button.setAttribute("listeners", new JSCode(
				"{" +
				"	click: function(button, event_object) {" +
				"		jfm_request('prin', '" + getId() + "', '&buttonid='+button.id);" +
				"		}" +
				"}"));
		setArrayAttribute("buttons",button);
		setSqlTraffic();
}
	
    public void prin(AjaxEvent jxe) {
		
		try {
			GeneratePDF cetaks1 = new GeneratePDF();
			cetaks1.show(jxe,kueris,namafiles,setSqlTraffic(),Awalan,Akhiran);
			//System.out.println(awalan)
			//Printer cetak = new Printer(kueris);
			//new Printing1("/home/yulius/Desktop/csv/traffic.csv");
			Process p = Runtime.getRuntime().exec("rundll32 url.dll,FileProtocolHandler c:/csv/"+kueris+".pdf");
			p.waitFor();
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	
	}

	public void expor(AjaxEvent jxe) throws Exception {
	
//		System.out.println("coba lihat ekspor sql"+setSqlTraffic());
		CsvFile cetaks = new CsvFile();
		cetaks.show(jxe,kueris, namafiles,setSqlTraffic());
		
	
		
	}
	
	public String setSqlTraffic()
	{
		return sql;	
	}
	
	public void addExportButton(AjaxEvent jxe,String kueri,String sqls) {
		
        kueris = kueri;
        namafiles = kueri;
        sql = sqls;

        JSObject button = new JSObject();
		button.setAttribute("text", "Export");
		button.setAttribute("listeners", new JSCode(
				"{" +
				"	click: function(button, event_object) {" +
				"		jfm_request('expor', '" + getId() + "', '&buttonid='+button.id);" +
				"		}" +
				"}"));
		setArrayAttribute("buttons",button);
		setSqlTraffic();
	}
	
	public void reqClick(AjaxEvent jxe) {
		if(celCallback != null) {
			celCallback.onClick(this, jxe);
		}
	}
	
	public void reqSubmitted(AjaxEvent jxe) {
//		System.out.println("\n\nFormpanel - jalan euy!!");
		
		FormPanelResult result = new FormPanelResult();
		
		if(fpelCallback!=null) {
			
			fpelCallback.onSubmitted(this, result, jxe);
			
		} else {
			result.setSuccess(false);
			result.setTitle("Error");
			result.setMessage("No response from server");
		}
		
		try {
			jxe.getReponse().getWriter().write(result.toString());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void hideLabels(boolean hide){
		setAttribute("hideLabels", hide);
	}
	
	/**
	 * @author yulius
	 */
	public void addOldPasswordField(String name, String label,String passw) {
		
		JSObject item = new JSObject();
		item.setAttribute("xtype", "textfield");
		item.setAttribute("name", name);
		item.setAttribute("fieldLabel", label);
		item.setAttribute("value", passw);
		item.setAttribute("inputType", "password");
		setArrayAttribute("items",item);
	}
}
