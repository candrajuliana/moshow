package com.bigjava.jsf;

import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.sql.DataSource;

import org.json.JSONArray;
import org.json.JSONObject;

/**
 * Servlet implementation class Export
 */
public class Export extends HttpServlet {
	public static final long serialVersionUID = 1L;
    GridPanel2 grid;   
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Export() {
        super();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 * @author junatandarmawan
	 */
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	
		 	response.setContentType("text/html");

		    //use a PrintWriter send text data to the client who has requested the
		    // servlet
		 	java.io.PrintWriter out = response.getWriter();
		 	
		 	
		 	String id ="";
		 	String add ="";
		 	String start = "0";
		 	String limit = "5";
		 	
		 	if (request.getParameter("start") != null){
		    	start = request.getParameter("start");
		    	
		    }
		    
		    if (request.getParameter("limit") != null){
		    	limit = request.getParameter("limit");
		    }
		 	
		    int i =0;
		    String sql="";
		 	InitialContext initContext;
			Context envContext;
			JSONObject json = new JSONObject();
			try {
				initContext = new InitialContext();
				envContext = (Context)initContext.lookup("java:/comp/env");
				
				DataSource ds = (DataSource)(envContext).lookup("jdbc/DpiDB");
				Connection conn = ds.getConnection();
				Statement st = conn.createStatement();
				ResultSet rs=null;
				ResultSet rs2=null;
				
				
				//RS2
				rs2 = st.executeQuery("select count(*) from email_recipient;");
				while( rs2.next()){
					i=rs2.getInt(1);
				}
				
				//RS1
				JSONArray arrayObj=new JSONArray();
				sql=String.format("Select id_recipient, address from email_recipient limit %s,%s;", start,limit);
				rs = st.executeQuery(sql);
				
				
				while( rs.next()){
					id = rs.getString("id_recipient");
					add = rs.getString("address");
					
					
					JSONObject topic = new JSONObject();
					topic.put("id", id);
					topic.put("address", add);
					arrayObj.put(topic);
				}

				json.put("totalCount", i);
				json.put("topics", arrayObj);
				
				
				//out.println(json_string);
				
			} catch (NamingException e) {
				
				e.printStackTrace();
			} catch (SQLException e) {
			
				e.printStackTrace();
			}  catch (Exception e) {
	            e.printStackTrace();
	        }
			
			
			String json_string = json.toString();
			
		    String callback = request.getParameter("callback");
		    
		    
		    /*if (request.getParameter("start") != null){
		    	start = request.getParameter("start");
		    }
		    
		    if (request.getParameter("limit") != null){
		    	limit = request.getParameter("limit");
		    }
		    //System.out.println("start =" + start );
		try {    
			JSONObject json = new JSONObject();
			json.putOnce("totalCount", "2");
		
			
			
			
			
			
			JSONObject topic = new JSONObject();
			topic.put("threadid","120999");
			topic.put("forumid","6");
			topic.put("forumtitle","Ext: Open Discussion");
			topic.put("title","Ever use yottaa.com?");
			topic.put("author","stever");
			topic.put("lastposter","cwei");
			topic.put("lastpost","1294972647");
			topic.put("excerpt","I came across them on twitter somehow, looks kinda cool. Curious what other people's performance score is? Does make me realize that I need to get around to breaking up ExtJS and dynamically loading packages as needed. Ug.");
			topic.put("replycount","2");
			
			JSONObject topic2 = new JSONObject();
			topic2.put("threadid","120999");
			topic2.put("forumid","7");
			topic2.put("forumtitle","Ext: Open Discussion");
			topic2.put("title","Wow Ken Lee?");
			topic2.put("author","Jonan");
			topic2.put("lastposter","maho");
			topic2.put("lastpost","1294972647");
			topic2.put("excerpt","looks kinda cool. Curious what other people's performance score is? Does make me realize that I need to get around to breaking up ExtJS and dynamically loading packages as needed. Ug.");
			topic2.put("replycount","2");
			 JSONArray arrayObj=new JSONArray();
			 if(start.equalsIgnoreCase("0")){
			   arrayObj.put(topic);
			 } else {
			   arrayObj.put(topic2);
			 }
			 
			json.put("topics",arrayObj);*/
			
			
			String response_string = "";
			if (callback != null) {
				response_string = callback + "(" + json_string + ")";
				 //System.out.println("true");
			} else {
				response_string = json_string;
				 //System.out.println("false");
			}
	        out.println(response_string);
	        //System.out.println(response_string);
			
            
		/*} catch (Exception e) {
            e.printStackTrace();
        }*/
		    
//	        System.out.println("\n\nExport - grid : "+grid);
	        
	}

	/**
	 * untuk men-set grid
	 * @author randikajonan
	 * 19jan2011
	 */
	public void setGrid(GridPanel2 g)
	{
		grid = g;
//		System.out.println("\n\nExport - setGrid : "+g);
	}
	
	public void reloadGrid()
	{
//		System.out.println("\n\nExport - reloadGrid");
	}
	
	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	
	}

}
