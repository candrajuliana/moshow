package com.bigjava.jsf;

import org.json.JSONArray;
import org.json.JSONObject;

public class Test {
	
	public static void main(String[] args)
	{
		
		
		try{
			

			JSObject id = new JSObject();
			id.setAttribute("id","2");
			id.setAttribute("address","qwe");
		
		System.out.println("anak : "+id);
		
		} catch(Exception e)
		{
			
		}
	}
}


/*
{
"topics": [

{"id": "8","address": "234"}

,
{
   "id": "2",
   "address": "jonan@bigjava.com"
},
{
   "id": "3",
   "address": "qwe@we.com"
},
{
   "id": "4",
   "address": "a@a.com"
},
{
   "id": "5",
   "address": "a@a.com"
}],
"totalCount": 9
}
*/