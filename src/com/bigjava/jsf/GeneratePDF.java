package com.bigjava.jsf;
//untuk ekspor file ke format csv by yulius 6 oktober 2010
import java.io.*;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletResponse;
import javax.sql.DataSource;
import java.io.FileOutputStream;
import java.awt.Color;
import com.itextpdf.text.Anchor;
import com.itextpdf.text.BadElementException;
import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Chapter;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Element;
import com.itextpdf.text.Font;
import com.itextpdf.text.FontFactory;
import com.itextpdf.text.List;
import com.itextpdf.text.ListItem;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.Section;
import com.itextpdf.text.pdf.CMYKColor;
import com.itextpdf.text.pdf.PdfContentByte;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import java.awt.Color;


public class GeneratePDF { 
	String kueri="";
	Document dokumen;
	PdfPTable tabel;
	String sql;
	public void prints(AjaxEvent jxe, String pa, String kueris) throws DocumentException, IOException
	{
		float leading = (float)100.400;
		//HttpServletResponse response =  jxe.getReponse();
		// response.setContentType("application/pdf");
		
		dokumen = new Document();
		
		String pall = kueris+" Report";
	
		Paragraph par = new Paragraph(kueris+" Report");
        par.getFont().setStyle(Font.BOLD);
        Paragraph spasi = new Paragraph(".");
		
//		PdfWriter.getInstance(dokumen, new FileOutputStream("/home/yulius/Desktop/csv/"+kueris+".pdf"));
        ///Users/randikajonan/Documents/workspace/export_data
        PdfWriter.getInstance(dokumen, new FileOutputStream("/Users/randikajonan/Documents/workspace/export_data/"+kueris+".pdf"));
        //PdfWriter.getInstance(dokumen,  response.getOutputStream());
        
        
		dokumen.open();
		dokumen.add(par);
		dokumen.add(spasi);
		dokumen.add(tabel);
		dokumen.close();
		
		
	    /*try{
	 
		  Process p = Runtime.getRuntime().exec("rundll32 url.dll,FileProtocolHandler c:/csv/"+kueris+".pdf");
		  p.waitFor();
	 
		  System.out.println("Done");
	 
	     }catch(Exception ex){
		  ex.printStackTrace();
	     }*/
	}
	
	public void show(AjaxEvent jxe,String kueris,String namafile,String sqls, String awalan, String akhiran)  {
		InitialContext initContext;
		Context envContext;
		sql = sqls;	
		
	try
	{
		initContext = new InitialContext();
		envContext = (Context)initContext.lookup("java:/comp/env");
		
		DataSource ds = (DataSource)(envContext).lookup("jdbc/DpiDB");
		Connection conn = ds.getConnection();
		Statement st = conn.createStatement();
		if (kueris=="CHART" && sql.length() < 1)
		{
			kueri ="select prot,bw_in+bw_out from flow start_time between '"+awalan+"' and '"+akhiran+"' group by prot order by sum(bw_in+bw_out) desc limit 5;";
		}
		if (kueris=="CHART" && sql.length()  > 1)
		{
			kueri =sql;
		}
		if (kueris.equalsIgnoreCase("traffic") && sql.length() < 1)
		{
			kueri ="select prot,count(*), sum(bw_out),sum(bw_in),sum(pkt_out),sum(pkt_in),(8*avg(bw_out)/(end_time - start_time)),(8*avg(bw_in)/(end_time - start_time)) from flow where start_time between '"+awalan+"' and '"+akhiran+"' group by prot;";
		}
		if (kueris.equalsIgnoreCase("traffic") && sql.length()  > 1)
		{
			kueri =sql;
		}
		
		if (kueris=="LIVE" && sql.length() < 1)
		{
			kueri ="select ip_src, prot, start_time, bw_in, bw_out, param from live_flow;";
		}
		if (kueris=="LIVE" && sql.length()  > 1)
		{
			kueri =sql;
		}
		
		if (kueris=="HTTP" && sql.length() < 1)
		{
			kueri ="select param,count(*), sum(bw_out),sum(bw_in),sum(pkt_out),sum(pkt_in),(8*avg(bw_out)/(end_time - start_time)),(8*avg(bw_in)/(end_time - start_time)) from flow where prot='HTTP' and start_time between '"+awalan+"' and '"+akhiran+"' group by param";
		}
		if (kueris=="HTTP" && sql.length()  > 1)
		{
			kueri =sql;
		}
		if (kueris=="Flash" && sql.length() < 1)
		{
			kueri ="select param,count(*),sum(bw_out),sum(bw_in),sum(pkt_out),sum(pkt_in),(8*avg(bw_out)/(end_time - start_time)),(8*avg(bw_in)/(end_time - start_time)) from flow where prot='Flash' and start_time between '"+awalan+"' and '"+akhiran+"' group by param";
		}
		if (kueris=="Flash" && sql.length()  > 1)
		{
			kueri =sql;
		}
		if (kueris=="P2P" && sql.length() < 1)
		{
			kueri ="select ip_src,prot, count(*),sum(bw_out),sum(bw_in),sum(pkt_out),sum(pkt_in),(8*avg(bw_out)/(end_time - start_time)),(8*avg(bw_in)/(end_time - start_time)) from flow where prot in ('BitTorrent','DirectDownloadLink') start_time between '"+awalan+"' and '"+akhiran+"' group by ip_src,prot;";
		}
		if (kueris=="P2P" && sql.length()  > 1)
		{
			kueri =sql;
		}
		if (kueris=="DNS" && sql.length() < 1)
		{
			kueri ="select param,count(*), sum(bw_out),sum(bw_in),sum(pkt_out),sum(pkt_in),(8*avg(bw_out)/(end_time - start_time)),(8*avg(bw_in)/(end_time - start_time)) from flow where prot='DNS' and start_time between '"+awalan+"' and '"+akhiran+"' group by param";
		}
		if (kueris=="DNS" && sql.length()  > 1)
		{
			kueri =sql;
		}
		if (kueris=="DM11" && sql.length() < 1)
		{
			kueri ="select a.id,a.errorcode,a.severity,a.progress,a.alarm_time,u.username,a.userid from alarm_cm a inner join users u on a.userid=u.userid;";
		}
		if (kueris=="DM11" && sql.length()  > 1)
		{
			kueri =sql;
		}
		if (kueris=="PM11PRODUCT" && sql.length() < 1)
		{
			kueri ="select product_id,product,policy,tipe,waktu,quota,Priority from policy_manager_product;";
		}
		if (kueris=="PM11PRODUCT" && sql.length()  > 1)
		{
			kueri =sql;
		}
		if (kueris=="UM11HISTORY" && sql.length() < 1)
		{
			kueri ="select username,privilages,status,selectpm from members;";
		}
		if (kueris=="UM11HISTORY" && sql.length()  > 1)
		{
			kueri =sql;
		}
		if (kueris=="UM11STATUS" && sql.length() < 1)
		{
			kueri ="select username,privilages,status,selectpm from members;";	
		}
		if (kueris=="UM11STATUS" && sql.length()  > 1)
		{
			kueri =sql;
		}
		if (kueris=="MORETO" && sql.length() > 1)
		{
			kueri ="select a.id,a.errorcode,a.severity,a.progress,a.alarm_time,u.username from alarm_mrt a inner join users u on a.userid=u.userid;";	
		}

		ResultSet rs = st.executeQuery(kueri);
		
		boolean first=true;
	    
	    if (kueris=="traffic")
		{
     
    	    	float[] widths = {0.28f, 0.065f, 0.12f, 0.12f,0.09f, 0.09f, 0.125f, 0.11f};
              
	    	    
	    	    tabel = new PdfPTable(widths);
	    	    
	    	    tabel.setWidthPercentage(100);
	    		PdfPCell c1 = new PdfPCell(new Phrase("Traffic"));
	    		c1.setBackgroundColor(BaseColor.LIGHT_GRAY);
	    		c1.setHorizontalAlignment(Element.ALIGN_LEFT);
	    		tabel.addCell(c1);

	    		c1 = new PdfPCell(new Phrase("Hits"));
	    		c1.setBackgroundColor(BaseColor.LIGHT_GRAY);
	    		c1.setHorizontalAlignment(Element.ALIGN_LEFT);
	    		tabel.addCell(c1);
	    		
	    		c1 = new PdfPCell(new Phrase("BW Out"));
	    		c1.setBackgroundColor(BaseColor.LIGHT_GRAY);
	    		c1.setHorizontalAlignment(Element.ALIGN_LEFT);
	    		tabel.addCell(c1);

	    		c1 = new PdfPCell(new Phrase("BW In"));
	    		c1.setBackgroundColor(BaseColor.LIGHT_GRAY);
	    		c1.setHorizontalAlignment(Element.ALIGN_LEFT);
	    		tabel.addCell(c1);
	    		
	    		c1 = new PdfPCell(new Phrase("Pkt Out"));
	    		c1.setBackgroundColor(BaseColor.LIGHT_GRAY);
	    		c1.setHorizontalAlignment(Element.ALIGN_LEFT);
	    		tabel.addCell(c1);

	    		c1 = new PdfPCell(new Phrase("Pkt In"));
	    		c1.setBackgroundColor(BaseColor.LIGHT_GRAY);
	    		c1.setHorizontalAlignment(Element.ALIGN_LEFT);
	    		tabel.addCell(c1);
	    		
	    		c1 = new PdfPCell(new Phrase("bitrate Out"));
	    		c1.setBackgroundColor(BaseColor.LIGHT_GRAY);
	    		c1.setHorizontalAlignment(Element.ALIGN_LEFT);
	    		tabel.addCell(c1);

	    		c1 = new PdfPCell(new Phrase("bitrate In"));
	    		c1.setBackgroundColor(BaseColor.LIGHT_GRAY);
	    		c1.setHorizontalAlignment(Element.ALIGN_LEFT);
	    		tabel.addCell(c1);
	    		
	    		tabel.setHeaderRows(1);
   
		}
	    if (kueris=="LIVE")
		{
	    
		    float[] widths = {0.44f, 0.065f, 0.1f, 0.1f,0.09f, 0.08f, 0.125f};
		    tabel = new PdfPTable(widths);
    		
		    tabel.setWidthPercentage(100);
    		PdfPCell c1 = new PdfPCell(new Phrase("User"));
    		c1.setBackgroundColor(BaseColor.LIGHT_GRAY);
    		c1.setHorizontalAlignment(Element.ALIGN_LEFT);
    		tabel.addCell(c1);

    		c1 = new PdfPCell(new Phrase("Protocol"));
    		c1.setBackgroundColor(BaseColor.LIGHT_GRAY);
    		c1.setHorizontalAlignment(Element.ALIGN_LEFT);
    		tabel.addCell(c1);
    		
    		c1 = new PdfPCell(new Phrase("Start"));
    		c1.setBackgroundColor(BaseColor.LIGHT_GRAY);
    		c1.setHorizontalAlignment(Element.ALIGN_LEFT);
    		tabel.addCell(c1);
    		
    		c1 = new PdfPCell(new Phrase("Duration"));
    		c1.setBackgroundColor(BaseColor.LIGHT_GRAY);
    		c1.setHorizontalAlignment(Element.ALIGN_LEFT);
    		tabel.addCell(c1);
    		
    		c1 = new PdfPCell(new Phrase("BW In"));
    		c1.setBackgroundColor(BaseColor.LIGHT_GRAY);
    		c1.setHorizontalAlignment(Element.ALIGN_LEFT);
    		tabel.addCell(c1);

    		c1 = new PdfPCell(new Phrase("BW Out"));
    		c1.setBackgroundColor(BaseColor.LIGHT_GRAY);
    		c1.setHorizontalAlignment(Element.ALIGN_LEFT);
    		tabel.addCell(c1);
    		
    		c1 = new PdfPCell(new Phrase("Service"));
    		c1.setBackgroundColor(BaseColor.LIGHT_GRAY);
    		c1.setHorizontalAlignment(Element.ALIGN_LEFT);
    		tabel.addCell(c1);

    		
    		tabel.setHeaderRows(1);
	    
		}
		if (kueris=="HTTP")
		{

		    
		    float[] widths = {0.33f, 0.065f, 0.1f, 0.1f,0.09f, 0.08f, 0.125f, 0.11f};
		    tabel = new PdfPTable(widths);
    		
		    tabel.setWidthPercentage(100);
    		PdfPCell c1 = new PdfPCell(new Phrase("Domains"));
    		c1.setBackgroundColor(BaseColor.LIGHT_GRAY);
    		c1.setHorizontalAlignment(Element.ALIGN_LEFT);
    		tabel.addCell(c1);

    		c1 = new PdfPCell(new Phrase("Hits"));
    		c1.setBackgroundColor(BaseColor.LIGHT_GRAY);
    		c1.setHorizontalAlignment(Element.ALIGN_LEFT);
    		tabel.addCell(c1);
    		
    		c1 = new PdfPCell(new Phrase("BW Out"));
    		c1.setBackgroundColor(BaseColor.LIGHT_GRAY);
    		c1.setHorizontalAlignment(Element.ALIGN_LEFT);
    		tabel.addCell(c1);
    		
    		c1 = new PdfPCell(new Phrase("BW In"));
    		c1.setBackgroundColor(BaseColor.LIGHT_GRAY);
    		c1.setHorizontalAlignment(Element.ALIGN_LEFT);
    		tabel.addCell(c1);
    		
    		c1 = new PdfPCell(new Phrase("Pkt Out"));
    		c1.setBackgroundColor(BaseColor.LIGHT_GRAY);
    		c1.setHorizontalAlignment(Element.ALIGN_LEFT);
    		tabel.addCell(c1);

    		c1 = new PdfPCell(new Phrase("Pkt In"));
    		c1.setBackgroundColor(BaseColor.LIGHT_GRAY);
    		c1.setHorizontalAlignment(Element.ALIGN_LEFT);
    		tabel.addCell(c1);
    		
    		c1 = new PdfPCell(new Phrase("bitrate Out"));
    		c1.setBackgroundColor(BaseColor.LIGHT_GRAY);
    		c1.setHorizontalAlignment(Element.ALIGN_LEFT);
    		tabel.addCell(c1);

    		c1 = new PdfPCell(new Phrase("bitrate In"));
    		c1.setBackgroundColor(BaseColor.LIGHT_GRAY);
    		c1.setHorizontalAlignment(Element.ALIGN_LEFT);
    		tabel.addCell(c1);
    		
    		tabel.setHeaderRows(1);
	    
		}
		if (kueris=="Flash")
		{
  
			    float[] widths = {0.345f, 0.05f, 0.1f, 0.11f,0.09f, 0.08f, 0.125f, 0.1f};
			    tabel = new PdfPTable(widths);
	    		
			    tabel.setWidthPercentage(100);
	    		PdfPCell c1 = new PdfPCell(new Phrase("Domains"));
	    		c1.setBackgroundColor(BaseColor.LIGHT_GRAY);
	    		c1.setHorizontalAlignment(Element.ALIGN_LEFT);
	    		tabel.addCell(c1);
	    		
	    		c1 = new PdfPCell(new Phrase("Hits"));
	    		c1.setBackgroundColor(BaseColor.LIGHT_GRAY);
	    		c1.setHorizontalAlignment(Element.ALIGN_LEFT);
	    		tabel.addCell(c1);

	    		c1 = new PdfPCell(new Phrase("BW Out"));
	    		c1.setBackgroundColor(BaseColor.LIGHT_GRAY);
	    		c1.setHorizontalAlignment(Element.ALIGN_LEFT);
	    		tabel.addCell(c1);
	    		
	    		c1 = new PdfPCell(new Phrase("BW In"));
	    		c1.setBackgroundColor(BaseColor.LIGHT_GRAY);
	    		c1.setHorizontalAlignment(Element.ALIGN_LEFT);
	    		tabel.addCell(c1);
	    		
	    		c1 = new PdfPCell(new Phrase("Pkt Out"));
	    		c1.setBackgroundColor(BaseColor.LIGHT_GRAY);
	    		c1.setHorizontalAlignment(Element.ALIGN_LEFT);
	    		tabel.addCell(c1);

	    		c1 = new PdfPCell(new Phrase("Pkt In"));
	    		c1.setBackgroundColor(BaseColor.LIGHT_GRAY);
	    		c1.setHorizontalAlignment(Element.ALIGN_LEFT);
	    		tabel.addCell(c1);
	    		
	    		c1 = new PdfPCell(new Phrase("bitrate Out"));
	    		c1.setBackgroundColor(BaseColor.LIGHT_GRAY);
	    		c1.setHorizontalAlignment(Element.ALIGN_LEFT);
	    		tabel.addCell(c1);

	    		c1 = new PdfPCell(new Phrase("bitrate In"));
	    		c1.setBackgroundColor(BaseColor.LIGHT_GRAY);
	    		c1.setHorizontalAlignment(Element.ALIGN_LEFT);
	    		tabel.addCell(c1);
	    		
	    		tabel.setHeaderRows(1);
	    	    
		}
		if (kueris=="P2P")
		{    
			    tabel = new PdfPTable(9);

			    tabel.setWidthPercentage(100);
	    		PdfPCell c1 = new PdfPCell(new Phrase("User"));
	    		c1.setBackgroundColor(BaseColor.LIGHT_GRAY);
	    		c1.setHorizontalAlignment(Element.ALIGN_LEFT);
	    		tabel.addCell(c1);
	    		
	    		c1 = new PdfPCell(new Phrase("Service"));
	    		c1.setBackgroundColor(BaseColor.LIGHT_GRAY);
	    		c1.setHorizontalAlignment(Element.ALIGN_LEFT);
	    		tabel.addCell(c1);

	    		c1 = new PdfPCell(new Phrase("Hits"));
	    		c1.setBackgroundColor(BaseColor.LIGHT_GRAY);
	    		c1.setHorizontalAlignment(Element.ALIGN_LEFT);
	    		tabel.addCell(c1);

	    		c1 = new PdfPCell(new Phrase("BW Out"));
	    		c1.setBackgroundColor(BaseColor.LIGHT_GRAY);
	    		c1.setHorizontalAlignment(Element.ALIGN_LEFT);
	    		tabel.addCell(c1);
	    		
	    		c1 = new PdfPCell(new Phrase("BW In"));
	    		c1.setBackgroundColor(BaseColor.LIGHT_GRAY);
	    		c1.setHorizontalAlignment(Element.ALIGN_LEFT);
	    		tabel.addCell(c1);
	    		
	    		c1 = new PdfPCell(new Phrase("Packet Out"));
	    		c1.setBackgroundColor(BaseColor.LIGHT_GRAY);
	    		c1.setHorizontalAlignment(Element.ALIGN_LEFT);
	    		tabel.addCell(c1);

	    		c1 = new PdfPCell(new Phrase("Packet In"));
	    		c1.setBackgroundColor(BaseColor.LIGHT_GRAY);
	    		c1.setHorizontalAlignment(Element.ALIGN_LEFT);
	    		tabel.addCell(c1);
	    		
	    		c1 = new PdfPCell(new Phrase("bitrate Out"));
	    		c1.setBackgroundColor(BaseColor.LIGHT_GRAY);
	    		c1.setHorizontalAlignment(Element.ALIGN_LEFT);
	    		tabel.addCell(c1);

	    		c1 = new PdfPCell(new Phrase("bitrate In"));
	    		c1.setBackgroundColor(BaseColor.LIGHT_GRAY);
	    		c1.setHorizontalAlignment(Element.ALIGN_LEFT);
	    		tabel.addCell(c1);
	    		
	    		tabel.setHeaderRows(1);
		    
		}
		if (kueris=="DNS")
		{
				float[] widths = {0.32f, 0.065f, 0.1f, 0.1f,0.09f, 0.09f, 0.125f, 0.11f};
			    tabel = new PdfPTable(widths);
	    		
 				tabel.setWidthPercentage(100);
	    		PdfPCell c1 = new PdfPCell(new Phrase("Domains"));
	    		c1.setBackgroundColor(BaseColor.LIGHT_GRAY);
	    		c1.setHorizontalAlignment(Element.ALIGN_LEFT);
	    		tabel.addCell(c1);

	    		c1 = new PdfPCell(new Phrase("Hits"));
	    		c1.setBackgroundColor(BaseColor.LIGHT_GRAY);
	    		c1.setHorizontalAlignment(Element.ALIGN_LEFT);
	    		tabel.addCell(c1);

	    		c1 = new PdfPCell(new Phrase("BW Out"));
	    		c1.setBackgroundColor(BaseColor.LIGHT_GRAY);
	    		c1.setHorizontalAlignment(Element.ALIGN_LEFT);
	    		tabel.addCell(c1);
	    		
	    		c1 = new PdfPCell(new Phrase("BW In"));
	    		c1.setBackgroundColor(BaseColor.LIGHT_GRAY);
	    		c1.setHorizontalAlignment(Element.ALIGN_LEFT);
	    		tabel.addCell(c1);
	    		
	    		c1 = new PdfPCell(new Phrase("Packet Out"));
	    		c1.setBackgroundColor(BaseColor.LIGHT_GRAY);
	    		c1.setHorizontalAlignment(Element.ALIGN_LEFT);
	    		tabel.addCell(c1);

	    		c1 = new PdfPCell(new Phrase("Packet In"));
	    		c1.setBackgroundColor(BaseColor.LIGHT_GRAY);
	    		c1.setHorizontalAlignment(Element.ALIGN_LEFT);
	    		tabel.addCell(c1);
	    		
	    		c1 = new PdfPCell(new Phrase("bitrate Out"));
	    		c1.setBackgroundColor(BaseColor.LIGHT_GRAY);
	    		c1.setHorizontalAlignment(Element.ALIGN_LEFT);
	    		tabel.addCell(c1);

	    		c1 = new PdfPCell(new Phrase("bitrate In"));
	    		c1.setBackgroundColor(BaseColor.LIGHT_GRAY);
	    		c1.setHorizontalAlignment(Element.ALIGN_LEFT);
	    		tabel.addCell(c1);
	    		
	    		tabel.setHeaderRows(1);

	    	   
	    		
		}
		if (kueris=="DM11")
		{
		
			    tabel = new PdfPTable(6);
			    tabel.setWidthPercentage(100);
	    		PdfPCell c1 = new PdfPCell(new Phrase("No"));
	    		c1.setBackgroundColor(BaseColor.LIGHT_GRAY);
	    		c1.setHorizontalAlignment(Element.ALIGN_LEFT);
	    		tabel.addCell(c1);

	    		c1 = new PdfPCell(new Phrase("Code Error"));
	    		c1.setBackgroundColor(BaseColor.LIGHT_GRAY);
	    		c1.setHorizontalAlignment(Element.ALIGN_LEFT);
	    		tabel.addCell(c1);

	    		c1 = new PdfPCell(new Phrase("Alarm"));
	    		c1.setBackgroundColor(BaseColor.LIGHT_GRAY);
	    		c1.setHorizontalAlignment(Element.ALIGN_LEFT);
	    		tabel.addCell(c1);
	    		
	    		c1 = new PdfPCell(new Phrase("Status"));
	    		c1.setBackgroundColor(BaseColor.LIGHT_GRAY);
	    		c1.setHorizontalAlignment(Element.ALIGN_LEFT);
	    		tabel.addCell(c1);
	    		
	    		c1 = new PdfPCell(new Phrase("Time"));
	    		c1.setBackgroundColor(BaseColor.LIGHT_GRAY);
	    		c1.setHorizontalAlignment(Element.ALIGN_LEFT);
	    		tabel.addCell(c1);

	    		c1 = new PdfPCell(new Phrase("User"));
	    		c1.setBackgroundColor(BaseColor.LIGHT_GRAY);
	    		c1.setHorizontalAlignment(Element.ALIGN_LEFT);
	    		tabel.addCell(c1);
	    	
	    		tabel.setHeaderRows(1);


	    		
		}
		if (kueris=="PM11PRODUCT")
		{
	    
		    tabel = new PdfPTable(7);
    		
		    tabel.setWidthPercentage(100);
    		PdfPCell c1 = new PdfPCell(new Phrase("No"));
    		c1.setBackgroundColor(BaseColor.LIGHT_GRAY);
    		c1.setHorizontalAlignment(Element.ALIGN_LEFT);
    		tabel.addCell(c1);

    		c1 = new PdfPCell(new Phrase("Product"));
    		c1.setBackgroundColor(BaseColor.LIGHT_GRAY);
    		c1.setHorizontalAlignment(Element.ALIGN_LEFT);
    		tabel.addCell(c1);

    		c1 = new PdfPCell(new Phrase("Policy"));
    		c1.setBackgroundColor(BaseColor.LIGHT_GRAY);
    		c1.setHorizontalAlignment(Element.ALIGN_LEFT);
    		tabel.addCell(c1);
    		
    		c1 = new PdfPCell(new Phrase("Type"));
    		c1.setBackgroundColor(BaseColor.LIGHT_GRAY);
    		c1.setHorizontalAlignment(Element.ALIGN_LEFT);
    		tabel.addCell(c1);
    		
    		c1 = new PdfPCell(new Phrase("Time(mnt)"));
    		c1.setBackgroundColor(BaseColor.LIGHT_GRAY);
    		c1.setHorizontalAlignment(Element.ALIGN_LEFT);
    		tabel.addCell(c1);

    		c1 = new PdfPCell(new Phrase("Quota(mb)"));
    		c1.setBackgroundColor(BaseColor.LIGHT_GRAY);
    		c1.setHorizontalAlignment(Element.ALIGN_LEFT);
    		tabel.addCell(c1);
    		
    		c1 = new PdfPCell(new Phrase("Priority"));
    		c1.setBackgroundColor(BaseColor.LIGHT_GRAY);
    		c1.setHorizontalAlignment(Element.ALIGN_LEFT);
    		tabel.addCell(c1);
    		
    		tabel.setHeaderRows(1);

    	    
		    
		}
		if (kueris=="UM11HISTORY")
		{
   
			tabel = new PdfPTable(4);
		    tabel.setWidthPercentage(100);
    		PdfPCell c1 = new PdfPCell(new Phrase("No"));
    		c1.setBackgroundColor(BaseColor.LIGHT_GRAY);
    		c1.setHorizontalAlignment(Element.ALIGN_LEFT);
    		tabel.addCell(c1);

    		c1 = new PdfPCell(new Phrase("Username"));
    		c1.setBackgroundColor(BaseColor.LIGHT_GRAY);
    		c1.setHorizontalAlignment(Element.ALIGN_LEFT);
    		tabel.addCell(c1);

    		c1 = new PdfPCell(new Phrase("Log"));
    		c1.setBackgroundColor(BaseColor.LIGHT_GRAY);
    		c1.setHorizontalAlignment(Element.ALIGN_LEFT);
    		tabel.addCell(c1);
    		
    		c1 = new PdfPCell(new Phrase("Detail"));
    		c1.setBackgroundColor(BaseColor.LIGHT_GRAY);
    		c1.setHorizontalAlignment(Element.ALIGN_LEFT);
    		tabel.addCell(c1);
    		
    		tabel.setHeaderRows(1);
		    
		}
		if (kueris=="UM11STATUS")
		{
		    
		    tabel = new PdfPTable(4);
		    tabel.setWidthPercentage(100);
    		PdfPCell c1 = new PdfPCell(new Phrase("Username"));
    		c1.setBackgroundColor(BaseColor.LIGHT_GRAY);
    		c1.setHorizontalAlignment(Element.ALIGN_LEFT);
    		tabel.addCell(c1);

    		c1 = new PdfPCell(new Phrase("Privileges"));
    		c1.setBackgroundColor(BaseColor.LIGHT_GRAY);
    		c1.setHorizontalAlignment(Element.ALIGN_LEFT);
    		tabel.addCell(c1);

    		c1 = new PdfPCell(new Phrase("Status"));
    		c1.setBackgroundColor(BaseColor.LIGHT_GRAY);
    		c1.setHorizontalAlignment(Element.ALIGN_LEFT);
    		tabel.addCell(c1);
    		
    		c1 = new PdfPCell(new Phrase("PM"));
    		c1.setBackgroundColor(BaseColor.LIGHT_GRAY);
    		c1.setHorizontalAlignment(Element.ALIGN_LEFT);
    		tabel.addCell(c1);
   		
    		tabel.setHeaderRows(1);

		    
		}
		
		if (kueris=="MORETO")
		{
	    
		    float[] widths = {0.14f, 0.165f, 0.1f, 0.1f,0.29f, 0.205f};
		    tabel = new PdfPTable(6);
    		
		    tabel.setWidthPercentage(100);
    		PdfPCell c1 = new PdfPCell(new Phrase("No"));
    		c1.setBackgroundColor(BaseColor.LIGHT_GRAY);
    		c1.setHorizontalAlignment(Element.ALIGN_LEFT);
    		tabel.addCell(c1);

    		c1 = new PdfPCell(new Phrase("Code Error"));
    		c1.setBackgroundColor(BaseColor.LIGHT_GRAY);
    		c1.setHorizontalAlignment(Element.ALIGN_LEFT);
    		tabel.addCell(c1);
    		
    		c1 = new PdfPCell(new Phrase("Alarm"));
    		c1.setBackgroundColor(BaseColor.LIGHT_GRAY);
    		c1.setHorizontalAlignment(Element.ALIGN_LEFT);
    		tabel.addCell(c1);
    		
    		c1 = new PdfPCell(new Phrase("Status"));
    		c1.setBackgroundColor(BaseColor.LIGHT_GRAY);
    		c1.setHorizontalAlignment(Element.ALIGN_LEFT);
    		tabel.addCell(c1);
    		
    		c1 = new PdfPCell(new Phrase("Time"));
    		c1.setBackgroundColor(BaseColor.LIGHT_GRAY);
    		c1.setHorizontalAlignment(Element.ALIGN_LEFT);
    		tabel.addCell(c1);

    		c1 = new PdfPCell(new Phrase("User"));
    		c1.setBackgroundColor(BaseColor.LIGHT_GRAY);
    		c1.setHorizontalAlignment(Element.ALIGN_LEFT);
    		tabel.addCell(c1);
    		
   		
    		tabel.setHeaderRows(1);
	    
		}
		while(rs.next()) {
			if(first==true) {
				first=false;
			} else {
				/*fw.append(",");*/
			}
			if (kueris.equalsIgnoreCase("traffic"))
			{
	
					String pa =String.format("%s,%s,%s,%s,%s,%s,%s,%s", rs.getString(1),rs.getString(2),rs.getString(3),rs.getString(4),rs.getString(5),rs.getString(6),rs.getString(7),rs.getString(8));
			
				    tabel.addCell(rs.getString(1));
		    		tabel.addCell(rs.getString(2));
		    		tabel.addCell(rs.getString(3));
		    		tabel.addCell(rs.getString(4));
		    		tabel.addCell(rs.getString(5));
		    		tabel.addCell(rs.getString(6));
		    		tabel.addCell(rs.getString(7));
		    		tabel.addCell(rs.getString(8));
		    		prints(jxe,pa,"Traffic");
				
				
			}
			if (kueris=="LIVE")
			{
				
					String pa =String.format("%s,%s,%s,%s,%s,%s,%s", rs.getString(1),rs.getString(2),rs.getString(3),rs.getString(4),rs.getString(5),rs.getString(6),rs.getString(7));

					tabel.addCell(rs.getString(1));
		    		tabel.addCell(rs.getString(2));
		    		tabel.addCell(rs.getString(3));
		    		tabel.addCell(rs.getString(4));
		    		tabel.addCell(rs.getString(5));
		    		tabel.addCell(rs.getString(6));
		    		tabel.addCell(rs.getString(7));
		   
		    		prints(jxe,pa,kueris);
				
			}
			if (kueris=="HTTP")
			{

				String pa = String.format("%s,%s,%s,%s,%s,%s,%s,%s", rs.getString(1),rs.getString(2),rs.getString(3),rs.getString(4),rs.getString(5),rs.getString(6),rs.getString(7),rs.getString(8));
				
				 	tabel.addCell(rs.getString(1));
		    		tabel.addCell(rs.getString(2));
		    		tabel.addCell(rs.getString(3));
		    		tabel.addCell(rs.getString(4));
		    		tabel.addCell(rs.getString(5));
		    		tabel.addCell(rs.getString(6));
		    		tabel.addCell(rs.getString(7));
		    		tabel.addCell(rs.getString(8));
				
		    		prints(jxe,pa,kueris);
			}
			if (kueris=="Flash")
			{
			
				String pa =String.format("%s,%s,%s,%s,%s,%s,%s,%s", rs.getString(1),rs.getString(2),rs.getString(3),rs.getString(4),rs.getString(5),rs.getString(6),rs.getString(7),rs.getString(8));
				
				
				tabel.addCell(rs.getString(1));
	    		tabel.addCell(rs.getString(2));
	    		tabel.addCell(rs.getString(3));
	    		tabel.addCell(rs.getString(4));
	    		tabel.addCell(rs.getString(5));
	    		tabel.addCell(rs.getString(6));
	    		tabel.addCell(rs.getString(7));
	    		tabel.addCell(rs.getString(8));
				prints(jxe,pa,kueris);
			}
			if (kueris=="P2P")
			{
				
					String pa = String.format("%s,%s,%s,%s,%s,%s,%s,%s,%s", rs.getString(1),rs.getString(2),rs.getString(3),rs.getString(4),rs.getString(5),rs.getString(6),rs.getString(7),rs.getString(8),rs.getString(9));
			
				   	tabel.addCell(rs.getString(1));
		    		tabel.addCell(rs.getString(2));
		    		tabel.addCell(rs.getString(3));
		    		tabel.addCell(rs.getString(4));
		    		tabel.addCell(rs.getString(5));
		    		tabel.addCell(rs.getString(6));
		    		tabel.addCell(rs.getString(7));
		    		tabel.addCell(rs.getString(8));
		    		tabel.addCell(rs.getString(9));
		    		prints(jxe,pa,kueris);
			}
			if (kueris=="DNS")
			{
				
					String pa =String.format("%s,%s,%s,%s,%s,%s,%s,%s", rs.getString(1),rs.getString(2),rs.getString(3),rs.getString(4),rs.getString(5),rs.getString(6),rs.getString(7),rs.getString(8));
				
				 	tabel.addCell(rs.getString(1));
		    		tabel.addCell(rs.getString(2));
		    		tabel.addCell(rs.getString(3));
		    		tabel.addCell(rs.getString(4));
		    		tabel.addCell(rs.getString(5));
		    		tabel.addCell(rs.getString(6));
		    		tabel.addCell(rs.getString(7));
		    		tabel.addCell(rs.getString(8));
		    		prints(jxe,pa,kueris);
			}
			if (kueris=="DM11")
			{
				
				String pa = String.format("%s,%s,%s,%s,%s,%s", rs.getString(1),rs.getString(2),rs.getString(3),rs.getString(4),rs.getString(5),rs.getString(6));
			
	    	    tabel.addCell(rs.getString(1));
	    		tabel.addCell(rs.getString(2));
	    		tabel.addCell(rs.getString(3));
	    		tabel.addCell(rs.getString(4));
	    		tabel.addCell(rs.getString(5));
	    		tabel.addCell(rs.getString(6));
				prints(jxe,pa,kueris);
			}
			if (kueris=="PM11PRODUCT")
			{
				
				String pa = String.format("%s,%s,%s,%s,%s,%s,%s", rs.getString(1),rs.getString(2),rs.getString(3),rs.getString(4),rs.getString(5),rs.getString(6),rs.getString(7));

	    	    tabel.addCell(rs.getString(1));
	    		tabel.addCell(rs.getString(2));
	    		tabel.addCell(rs.getString(3));
	    		tabel.addCell(rs.getString(4));
	    		tabel.addCell(rs.getString(5));
	    		tabel.addCell(rs.getString(6));
	    		tabel.addCell(rs.getString(7));
				prints(jxe,pa,kueris);
			}
			if (kueris=="UM11HISTORY")
			{
			
				String pa = String.format("%s,%s,%s,%s", rs.getString(1),rs.getString(2),rs.getString(3),rs.getString(4));
				
	    	    tabel.addCell(rs.getString(1));
	    		tabel.addCell(rs.getString(2));
	    		tabel.addCell(rs.getString(3));
	    		tabel.addCell(rs.getString(4));
				prints(jxe,pa,kueris);
			}
			if (kueris=="UM11STATUS")
			{
				
				String pa = String.format("%s,%s,%s,%s", rs.getString(1),rs.getString(2),rs.getString(3),rs.getString(4));
			
	    	    tabel.addCell(rs.getString(1));
	    		tabel.addCell(rs.getString(2));
	    		tabel.addCell(rs.getString(3));
	    		tabel.addCell(rs.getString(4));
				prints(jxe,pa,kueris);
			}
			if (kueris=="MORETO")
			{
				
					String pa =String.format("%s,%s,%s,%s,%s,%s", rs.getString(1),rs.getString(2),rs.getString(3),rs.getString(4),rs.getString(5),rs.getString(6));

					tabel.addCell(rs.getString(1));
		    		tabel.addCell(rs.getString(2));
		    		tabel.addCell(rs.getString(3));
		    		tabel.addCell(rs.getString(4));
		    		tabel.addCell(rs.getString(5));
		    		tabel.addCell(rs.getString(6));
		   
		    		prints(jxe,pa,kueris);
				
			}
			
		}
		
		rs.close();
		st.close();
		conn.close();
	} 
	catch (Exception ex) {
	ex.printStackTrace ();
	}
	}

}