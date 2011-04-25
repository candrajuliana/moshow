package com.bigjava.util;

import java.io.*;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

/**
 * Konversi File menjadi CSV dengan pilihan tanpa mengisi tanggal seleksi menggunakan parameter kueri, tanggal menggunakan sql
 * @author yuna
 */

public class CsvReport { 
	String kueris="";
	String kueri="";
	FileWriter fw;
	String sql;
	public void show(String[] Query,Connection con, String date1, String date2, String QueryTime, String exclude, String Downtime)  {
		
	try
	{
		
		Statement st = con.createStatement();
		for(int i =0; i <7; i++){
			kueris = Query[i];
			  if(kueris.length() > 0){
				if (kueris.equalsIgnoreCase("graph"))
				{
					kueri ="select prot,bw_in+bw_out from flow where start_time between '"+date1+"' and '"+date2+"' "+Downtime+" "+QueryTime+" "+exclude+" group by prot order by sum(bw_in+bw_out) desc limit 5;";
					System.out.println("csvreport - graph : "+kueri);
				}
				
				if (kueris.equalsIgnoreCase("traffic"))
				{
					kueri ="select prot,count(*), sum(bw_out),sum(bw_in),sum(pkt_out),sum(pkt_in),(8*avg(bw_out)/(end_time - start_time)),(8*avg(bw_in)/(end_time - start_time)) from flow where start_time between '"+date1+"' and '"+date2+"' "+Downtime+" "+QueryTime+" "+exclude+" group by prot;";
					System.out.println("csvreport - traffic : "+kueri);
				}
				
				
				if (kueris.equalsIgnoreCase("LIVE"))
				{
					kueri ="select ip_src, prot, start_time, bw_in, bw_out, param,(8*bw_out/(update_time - start_time)),(8*bw_in/(update_time - start_time)) from live_flow where start_time between '"+date1+"' and '"+date2+"' "+Downtime+" "+QueryTime+" "+exclude+" ;";
					System.out.println("csvreport - live : "+kueri);
				}
				
				
				if (kueris.equalsIgnoreCase("HTTP"))
				{
					kueri ="select param,count(*), sum(bw_out),sum(bw_in),sum(pkt_out),sum(pkt_in),(8*avg(bw_out)/(end_time - start_time)),(8*avg(bw_in)/(end_time - start_time)) from flow where prot='HTTP' and start_time between '"+date1+"' and '"+date2+"' "+Downtime+" "+QueryTime+" "+exclude+" group by param";
					System.out.println("csvreport - http : "+kueri);
				}
				
				if (kueris.equalsIgnoreCase("Flash"))
				{
					kueri ="select param,count(*),sum(bw_out),sum(bw_in),sum(pkt_out),sum(pkt_in),(8*avg(bw_out)/(end_time - start_time)),(8*avg(bw_in)/(end_time - start_time)) from flow where prot='Flash' and start_time between '"+date1+"' and '"+date2+"' "+Downtime+" "+QueryTime+" "+exclude+" group by param";
					System.out.println("csvreport - flash : "+kueri);
				}
				
				if (kueris.equalsIgnoreCase("P2P"))
				{
					kueri ="select ip_src,prot, count(*),sum(bw_out),sum(bw_in),sum(pkt_out),sum(pkt_in),(8*avg(bw_out)/(end_time - start_time)),(8*avg(bw_in)/(end_time - start_time)) from flow where prot in ('BitTorrent','DirectDownloadLink') and start_time between '"+date1+"' and '"+date2+"' "+Downtime+" "+QueryTime+" "+exclude+" group by ip_src,prot;";
					System.out.println("csvreport - p2p : "+kueri);
				}
				
				if (kueris.equalsIgnoreCase("DNS"))
				{
					kueri ="select param,count(*), sum(bw_out),sum(bw_in),sum(pkt_out),sum(pkt_in),(8*avg(bw_out)/(end_time - start_time)),(8*avg(bw_in)/(end_time - start_time)) from flow where prot='DNS' and start_time between '"+date1+"' and '"+date2+"' "+Downtime+" "+QueryTime+" "+exclude+" group by param;";
					System.out.println("csvreport - dns : "+kueri);
				}
				
				if (kueris.equalsIgnoreCase("DM11"))
				{
					kueri ="select a.id,a.errorcode,a.severity,a.progress,a.alarm_time,u.username,a.userid from alarm_cm a inner join users u on a.userid=u.userid;";
				}
				
				if (kueris.equalsIgnoreCase("PM11PRODUCT"))
				{
					kueri ="select product_id,product,policy,tipe,waktu,quota,Priority from policy_manager_product;";
				}
				
				if (kueris.equalsIgnoreCase("UM11HISTORY"))
				{
					kueri ="select username,privilages,status,selectpm from members;";
				}
				
				if (kueris.equalsIgnoreCase("UM11STATUS"))
				{
					kueri ="select username,privilages,status,selectpm from members;";	
				}
				
				if (kueris.equalsIgnoreCase("MORETO"))
				{
					kueri ="select a.id,a.errorcode,a.severity,a.progress,a.alarm_time,u.username from alarm_mrt a inner join users u on a.userid=u.userid;";	
				}
		
				ResultSet rs = st.executeQuery(kueri);
				
				boolean first=true;
		//		String filename = "/home/yulius/Desktop/csv/"+namafile+".csv";
				String filename = "/tmp/"+kueris+".csv";
//				String filename = "/Users/randikajonan/Documents/workspace/export_data/"+kueris+".csv";
			    fw = new FileWriter(filename);
			    if (kueris.equalsIgnoreCase("graph"))
				{
			    		fw.append("Graph");
			    		fw.append(',');
			    		fw.append("Bandwidth");
			    		fw.append('\n');      
				}
			    if (kueris.equalsIgnoreCase("traffic"))
				{
			    		fw.append("Traffic");
			    		fw.append(',');
			    		fw.append("Hits");
			    		fw.append(',');
			    		fw.append("Uplink");
			    		fw.append(',');
			    		fw.append("Downlink");
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
			    if (kueris.equalsIgnoreCase("LIVE"))
				{
			    	 	fw.append("User");
					    fw.append(',');
					    fw.append("Protocol");
					    fw.append(',');
					    fw.append("BW In");
					    fw.append(',');
					    fw.append("BW Out");
					    fw.append(',');
					    fw.append("Bitrate Out");
					    fw.append(',');
					    fw.append("Bitrate In");
					    fw.append(',');
					    fw.append("Service");
		
					    fw.append('\n');
				}
				if (kueris.equalsIgnoreCase("HTTP"))
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
				if (kueris.equalsIgnoreCase("Flash"))
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
				if (kueris.equalsIgnoreCase("P2P"))
				{
					 fw.append("User");
					    fw.append(',');
					    fw.append("Service");
					    fw.append(',');
					    fw.append("Hits");
					    fw.append(',');
					    fw.append("Uplink");
					    fw.append(',');
					    fw.append("Downlink");
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
				if (kueris.equalsIgnoreCase("DNS"))
				{
					fw.append("Domains");
				    fw.append(',');
				    fw.append("Hits");
				    fw.append(',');
				    fw.append("Uplink");
				    fw.append(',');
				    fw.append("Downlink");
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
				if (kueris.equalsIgnoreCase("DM11"))
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
				if (kueris.equalsIgnoreCase("PM11PRODUCT"))
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
				if (kueris.equalsIgnoreCase("UM11HISTORY"))
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
				if (kueris.equalsIgnoreCase("UM11STATUS"))
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
				if (kueris.equalsIgnoreCase("DM11"))
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
				if (kueris.equalsIgnoreCase("MORETO"))
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
					if (kueris.equalsIgnoreCase("graph"))
					{
						fw.append(String.format("%s,%s", rs.getString(1),rs.getString(2)));
						fw.append('\n');
					}
					if (kueris.equalsIgnoreCase("traffic"))
					{
						fw.append(String.format("%s,%s,%s,%s,%s,%s,%s,%s", rs.getString(1),rs.getString(2),rs.getString(3),rs.getString(4),rs.getString(5),rs.getString(6),rs.getString(7),rs.getString(8)));
						fw.append('\n');
					}
					if (kueris.equalsIgnoreCase("LIVE"))
					{
						fw.append(String.format("%s,%s,%s,%s,%s,%s,%s", rs.getString(1),rs.getString(2),rs.getInt(4),rs.getInt(5),rs.getInt(8),rs.getInt(7),rs.getString(6)));
						fw.append('\n');
					}
					if (kueris.equalsIgnoreCase("HTTP"))
					{
						fw.append(String.format("%s,%s,%s,%s,%s,%s,%s,%s", rs.getString(1),rs.getString(2),rs.getString(3),rs.getString(4),rs.getString(5),rs.getString(6),rs.getString(7),rs.getString(8)));
						fw.append('\n');
					}
					if (kueris.equalsIgnoreCase("Flash"))
					{
						fw.append(String.format("%s,%s,%s,%s,%s,%s,%s,%s", rs.getString(1),rs.getString(2),rs.getString(3),rs.getString(4),rs.getString(5),rs.getString(6),rs.getString(7),rs.getString(8)));
						fw.append('\n');
					}
					if (kueris.equalsIgnoreCase("P2P"))
					{
						fw.append(String.format("%s,%s,%s,%s,%s,%s,%s,%s,%s", rs.getString(1),rs.getString(2),rs.getString(3),rs.getString(4),rs.getString(5),rs.getString(6),rs.getString(7),rs.getString(8),rs.getString(9)));
						fw.append('\n');
					}
					if (kueris.equalsIgnoreCase("DNS"))
					{
						fw.append(String.format("%s,%s,%s,%s,%s,%s,%s,%s", rs.getString(1),rs.getString(2),rs.getString(3),rs.getString(4),rs.getString(5),rs.getString(6),rs.getString(7),rs.getString(8)));
						fw.append('\n');
					}
					if (kueris.equalsIgnoreCase("DM11"))
					{
						fw.append(String.format("%s,%s,%s,%s,%s", rs.getString(1),rs.getString(2),rs.getString(3),rs.getString(4),rs.getString(5)));
						fw.append('\n');
					}
					if (kueris.equalsIgnoreCase("PM11PRODUCT"))
					{
						fw.append(String.format("%s,%s,%s,%s,%s", rs.getString(1),rs.getString(2),rs.getString(3),rs.getString(4),rs.getString(5)));
						fw.append('\n');
					}
					if (kueris.equalsIgnoreCase("UM11HISTORY"))
					{
						fw.append(String.format("%s,%s,%s,%s", rs.getString(1),rs.getString(2),rs.getString(3),rs.getString(4)));
						fw.append('\n');	
					}
					if (kueris.equalsIgnoreCase("UM11STATUS"))
					{
						fw.append(String.format("%s,%s,%s,%s", rs.getString(1),rs.getString(2),rs.getString(3),rs.getString(4)));
						fw.append('\n');	
					}
					if (kueris.equalsIgnoreCase("MORETO"))
					{
						fw.append(String.format("%s,%s,%s,%s,%s,%s", rs.getString(1),rs.getString(2),rs.getString(3),rs.getString(4),rs.getString(5),rs.getString(6)));
						fw.append('\n');
					}
				}
			    fw.flush();
			    fw.close();
			    rs.close();
				
				
				} 
			  }
			
			st.close();
			con.close();
			}
	
			catch (Exception ex) {
			ex.printStackTrace ();
			}
		
	}
	

}