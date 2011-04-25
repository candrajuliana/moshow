package com.bigjava.jsf;
//untuk ekspor file ke format csv by yulius 6 oktober 2010
import java.io.*;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;
/**
 * Konversi File menjadi CSV dengan pilihan tanpa mengisi tanggal seleksi menggunakan parameter kueri, tanggal menggunakan sql
 * @author yulius eka 13 oktober 2010
 */

public class CsvFile { 
	String kueri="";
	FileWriter fw;
	String sql;
	public void show(AjaxEvent jxe,String kueris,String namafile,String sqls)  {
		InitialContext initContext;
		Context envContext;
		sql = sqls;	
		System.out.println("hasil kueri yang tak kosong"+sql);
	try
	{
		initContext = new InitialContext();
		envContext = (Context)initContext.lookup("java:/comp/env");
		
		DataSource ds = (DataSource)(envContext).lookup("jdbc/DpiDB");
		Connection conn = ds.getConnection();
		Statement st = conn.createStatement();
        
		if (kueris=="CHART" && sql.length() < 1)
		{
			kueri ="select prot,bw_in+bw_out from flow group by prot order by sum(bw_in+bw_out) desc limit 5;";
		}
		if (kueris=="CHART" && sql.length()  > 1)
		{
			kueri =sql;
		}
		if (kueris=="traffic" && sql.length() < 1)
		{
			kueri ="select prot,count(*), sum(bw_out),sum(bw_in),sum(pkt_out),sum(pkt_in),(8*avg(bw_out)/(end_time - start_time)),(8*avg(bw_in)/(end_time - start_time)) from flow group by prot;";
		}
		if (kueris=="traffic" && sql.length()  > 1)
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
			kueri ="select param,count(*), sum(bw_out),sum(bw_in),sum(pkt_out),sum(pkt_in),(8*avg(bw_out)/(end_time - start_time)),(8*avg(bw_in)/(end_time - start_time)) from flow where prot='HTTP' group by param";
		}
		if (kueris=="HTTP" && sql.length()  > 1)
		{
			kueri =sql;
		}
		if (kueris=="Flash" && sql.length() < 1)
		{
			kueri ="select param,count(*),sum(bw_out),sum(bw_in),sum(pkt_out),sum(pkt_in),(8*avg(bw_out)/(end_time - start_time)),(8*avg(bw_in)/(end_time - start_time)) from flow where prot='Flash' group by param";
		}
		if (kueris=="Flash" && sql.length()  > 1)
		{
			kueri =sql;
		}
		if (kueris=="P2P" && sql.length() < 1)
		{
			kueri ="select ip_src,prot, count(*),sum(bw_out),sum(bw_in),sum(pkt_out),sum(pkt_in),(8*avg(bw_out)/(end_time - start_time)),(8*avg(bw_in)/(end_time - start_time)) from flow where prot in ('BitTorrent','DirectDownloadLink') group by ip_src,prot;";
		}
		if (kueris=="P2P" && sql.length()  > 1)
		{
			kueri =sql;
		}
		if (kueris=="DNS" && sql.length() < 1)
		{
			kueri ="select param,count(*), sum(bw_out),sum(bw_in),sum(pkt_out),sum(pkt_in),(8*avg(bw_out)/(end_time - start_time)),(8*avg(bw_in)/(end_time - start_time)) from flow where prot='DNS' group by param";
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
//		String filename = "/home/yulius/Desktop/csv/"+namafile+".csv";
		String filename = "/Users/randikajonan/Documents/workspace/export_data/"+namafile+".csv";
	    fw = new FileWriter(filename);
	    
	    if (kueris=="traffic")
		{
	    		fw.append("Traffic");
	    		fw.append(',');
	    		fw.append("Hits");
	    		fw.append(',');
	    		fw.append("BW Out");
	    		fw.append(',');
	    		fw.append("BW In");
	    		fw.append(',');
	    		fw.append("Pkt Out");
	    		fw.append(',');
	    		fw.append("Pkt In");
	    		fw.append(',');
	    		fw.append("Bitrate Out");
	    		fw.append(',');
	    		fw.append("Bitrate In");
	    		fw.append('\n');      
		}
	    if (kueris=="LIVE")
		{
	    	 	fw.append("User");
			    fw.append(',');
			    fw.append("Protocol");
			    fw.append(',');
			    fw.append("Start");
			    fw.append(',');
			    fw.append("Duration");
			    fw.append(',');
			    fw.append("BW In");
			    fw.append(',');
			    fw.append("BW Out");
			    fw.append(',');
			    fw.append("Service");

			    fw.append('\n');
		}
		if (kueris=="HTTP")
		{
			 	fw.append("Domains");
			 	fw.append(',');
			    fw.append("Hits");
			    fw.append(',');
			    fw.append("BW Out");
			    fw.append(',');
			    fw.append("BW In");
			    fw.append(',');
			    fw.append("Pkt Out");
			    fw.append(',');
			    fw.append("Pkt In");
			    fw.append(',');
			    fw.append("Bitrate Out");
			    fw.append(',');
			    fw.append("Bitrate In");
			    fw.append('\n');
		}
		if (kueris=="Flash")
		{
			fw.append("Domains");
		    fw.append(',');
			fw.append("Hits");
			fw.append(',');
		    fw.append("BW Out");
		    fw.append(',');
		    fw.append("BW In");
		    fw.append(',');
		    fw.append("Pkt Out");
		    fw.append(',');
		    fw.append("Pkt In");
		    fw.append(',');
		    fw.append("Bitrate Out");
		    fw.append(',');
		    fw.append("Bitrate In");
		    fw.append('\n');
		}
		if (kueris=="P2P")
		{
			 fw.append("User");
			    fw.append(',');
			    fw.append("Service");
			    fw.append(',');
			    fw.append("Hits");
			    fw.append(',');
			    fw.append("BW Out");
			    fw.append(',');
			    fw.append("BW In");
			    fw.append(',');
			    fw.append("Pkt Out");
			    fw.append(',');
			    fw.append("Pkt In");
			    fw.append(',');
			    fw.append("Bitrate Out");
			    fw.append(',');
			    fw.append("Bitrate In");
			    fw.append('\n');
		}
		if (kueris=="DNS")
		{
			fw.append("Domains");
		    fw.append(',');
		    fw.append("Hits");
		    fw.append(',');
		    fw.append("BW Out");
		    fw.append(',');
		    fw.append("BW In");
		    fw.append(',');
		    fw.append("Pkt Out");
		    fw.append(',');
		    fw.append("Pkt In");
		    fw.append(',');
		    fw.append("Bitrate Out");
		    fw.append(',');
		    fw.append("Bitrate In");
		    fw.append('\n');
		}
		if (kueris=="DM11")
		{
			fw.append("No");
		    fw.append(',');
		    fw.append("Code Error");
		    fw.append(',');
		    fw.append("Alarm");
		    fw.append(',');
		    fw.append("Status");
		    fw.append(',');
		    fw.append("Time");
		    fw.append(',');
		    fw.append("User");
		    fw.append('\n');
		}
		if (kueris=="PM11PRODUCT")
		{
			fw.append("No");
		    fw.append(',');
		    fw.append("Product");
		    fw.append(',');
		    fw.append("Policy");
		    fw.append(',');
		    fw.append("Type");
		    fw.append(',');
		    fw.append("Time(mnt)");
		    fw.append(',');
		    fw.append("Quota(mb)");
		    fw.append(',');
		    fw.append("Priority");
		    fw.append('\n');
		}
		if (kueris=="UM11HISTORY")
		{
			fw.append("No");
		    fw.append(',');
		    fw.append("Username");
		    fw.append(',');
		    fw.append("Log");
		    fw.append(',');
		    fw.append("Detail");
		    fw.append('\n');
		}
		if (kueris=="UM11STATUS")
		{
			fw.append("Username");
		    fw.append(',');
		    fw.append("Privileges");
		    fw.append(',');
		    fw.append("Status");
		    fw.append(',');
		    fw.append("PM");
		    fw.append('\n');		
		}
		if (kueris=="DM11")
		{
			fw.append("No");
		    fw.append(',');
		    fw.append("Code Error");
		    fw.append(',');
		    fw.append("Alarm");
		    fw.append(',');
		    fw.append("Status");
		    fw.append(',');
		    fw.append("Time");
		    fw.append(',');
		    fw.append("User");
		    fw.append('\n');
		}
		if (kueris=="MORETO")
		{
			fw.append("No");
		    fw.append(',');
		    fw.append("Code Error");
		    fw.append(',');
		    fw.append("Alarm");
		    fw.append(',');
		    fw.append("Status");
		    fw.append(',');
		    fw.append("Time");
		    fw.append(',');
		    fw.append("User");
		    fw.append('\n');
		}
		while(rs.next()) {
			if(first==true) {
				first=false;
			} else {
				/*fw.append(",");*/
			}
			if (kueris=="traffic")
			{
				fw.append(String.format("%s,%s,%s,%s,%s,%s,%s", rs.getString(1),rs.getString(2),rs.getString(3),rs.getString(4),rs.getString(5),rs.getString(6),rs.getString(7)));
				fw.append('\n');
			}
			if (kueris=="LIVE")
			{
				fw.append(String.format("%s,%s,%s,%s,%s,%s,%s", rs.getString(1),rs.getString(2),rs.getString(3),rs.getString(4),rs.getString(5),rs.getString(6),rs.getString(7)));
				fw.append('\n');
			}
			if (kueris=="HTTP")
			{
				fw.append(String.format("%s,%s,%s,%s,%s,%s,%s", rs.getString(1),rs.getString(2),rs.getString(3),rs.getString(4),rs.getString(5),rs.getString(6),rs.getString(7)));
				fw.append('\n');
			}
			if (kueris=="Flash")
			{
				fw.append(String.format("%s,%s,%s,%s,%s", rs.getString(1),rs.getString(2),rs.getString(3),rs.getString(4),rs.getString(5)));
				fw.append('\n');
			}
			if (kueris=="P2P")
			{
				fw.append(String.format("%s,%s,%s,%s,%s,%s,%s", rs.getString(1),rs.getString(2),rs.getString(3),rs.getString(4),rs.getString(5),rs.getString(6),rs.getString(7)));
				fw.append('\n');
			}
			if (kueris=="DNS")
			{
				fw.append(String.format("%s,%s,%s,%s,%s,%s,%s", rs.getString(1),rs.getString(2),rs.getString(3),rs.getString(4),rs.getString(5),rs.getString(6),rs.getString(7)));
				fw.append('\n');
			}
			if (kueris=="DM11")
			{
				fw.append(String.format("%s,%s,%s,%s,%s", rs.getString(1),rs.getString(2),rs.getString(3),rs.getString(4),rs.getString(5)));
				fw.append('\n');
			}
			if (kueris=="PM11PRODUCT")
			{
				fw.append(String.format("%s,%s,%s,%s,%s", rs.getString(1),rs.getString(2),rs.getString(3),rs.getString(4),rs.getString(5)));
				fw.append('\n');
			}
			if (kueris=="UM11HISTORY")
			{
				fw.append(String.format("%s,%s,%s,%s", rs.getString(1),rs.getString(2),rs.getString(3),rs.getString(4)));
				fw.append('\n');	
			}
			if (kueris=="UM11STATUS")
			{
				fw.append(String.format("%s,%s,%s,%s", rs.getString(1),rs.getString(2),rs.getString(3),rs.getString(4)));
				fw.append('\n');	
			}
			if (kueris=="MORETO")
			{
				fw.append(String.format("%s,%s,%s,%s,%s,%s", rs.getString(1),rs.getString(2),rs.getString(3),rs.getString(4),rs.getString(5),rs.getString(6)));
				fw.append('\n');
			}
		}
	    fw.flush();
	    fw.close();
		rs.close();
		st.close();
		conn.close();
	} 
	catch (Exception ex) {
	ex.printStackTrace ();
	}
	}

}