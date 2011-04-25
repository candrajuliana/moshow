var JFM_GLOBAL_SCOPE = new Array();

function jfm_put_gs(key, val) {
	JFM_GLOBAL_SCOPE[key] = val;
}

function jfm_traffic(key) {
	return alert('There was a problem with the request.');
}

function jfm_get_gs(key) {
	return JFM_GLOBAL_SCOPE[key];
}

function jfm_unit_renderer(bw) {
	if(bw>1073741824) {
		return (bw/1073741824).toFixed(2)+'GB';
	}
	if(bw>1048576) {
		return (bw/1048576).toFixed(2)+'MB';
	}
	if(bw>1024) {
		return (bw/1024).toFixed(2)+'KB';
	}
	
	return bw;
}
// tambahan untuk bitrate
function bitrate_renderer(bw) {
	if(bw>1073741824) {
		return (bw/1000000000).toFixed(2)+' Gbps';
	}
	if(bw>1048576) {
		return (bw/1000000).toFixed(2)+' Mbps';
	}
	if(bw>1024) {
		return (bw/1000).toFixed(2)+' Kbps';
	}
	if(bw<1024) {
		return (bw).toFixed(2)+' bps';
	}
	
	return bw;
}

function on_response(){
	if(httpObject.readyState == 4){
		var scriptExec = httpObject.responseText;
		if(scriptExec!=null)
		{
			try {
				//alert(scriptExec);
				eval(scriptExec);
			}catch(err)
			{
				alert(err);
				alert(scriptExec);
			}
		}
	}
}

function get_http_object(){
	if (window.ActiveXObject) 
		return new ActiveXObject("Microsoft.XMLHTTP");
	else if (window.XMLHttpRequest) 
		return new XMLHttpRequest();
	else {
		return null;
	}
}

function jfm_params(src)
{
	var params=""; 
	
	if(src!=null)
	{		
		objContent = document.getElementById(src);
		if(objContent == null)
			return;		
		
		var elms = objContent.getElementsByTagName("*");
		var elm;

		for(var i = 0, maxI = elms.length; i < maxI; ++i) {						
	        var elm = elms[i];
			
	        switch(elm.type) {
	          case "text":
	          case "textarea":
	          case "button":
	          case "reset":
	          case "submit":
	          case "file":
	          case "hidden":
	          case "password":
	          case "image":
	          //case "radio":
	          case "checkbox":
	          case "select-one":
	          case "select-multiple":
				params = params + "&" + elm.id + "=" + escape(elm.value);
		  case "radio":
			if(elm.checked)
				params = params + "&" + elm.id + "=" + elm.value;
	        }
	    }
	}
	
	return params;
}

function jfm_request(event,sender,uparam){
	var params="_evt="+event;
	var objContent;
	
	if(sender!=null)
	{		
		if(typeof sender != "string")
			sender = sender.id
		params = params + "&_obj=" + sender;
	}

	if(uparam!=null)
	{
		params = params+uparam;
	}

	//alert(params);

	httpObject = get_http_object();
	if (httpObject != null) {	
		httpObject.open("GET", "Dispatch?"+params, true);		
		httpObject.send(null);
		httpObject.onreadystatechange = on_response;
	}
}
