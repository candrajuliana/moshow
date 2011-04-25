<!-- Do NOT put any DOCTYPE here unless you want problems in IEs. -->
<html>
 
<!-- Each valid html page must have a head; let's create one. -->
<head>
  <!-- The following line defines content type and utf-8 as character set. -->
  <!-- If you want your application to work flawlessly with various local -->
  <!-- characters, just make ALL strings, on the page, json and database utf-8. -->
  <meta http-equiv="Content-Type" content="text/html; charset=utf-8">
 
  <!-- Ext relies on its default css so include it here. -->
  <!-- This must come BEFORE javascript includes! -->
  <link rel="stylesheet" type="text/css" href="ext/resources/css/ext-all.css">
 
  <!-- Include here your own css files if you have them. -->
 
  <!-- First of javascript includes must be an adapter... -->
  <script type="text/javascript" src="ext/adapter/ext/ext-base.js"></script>
 
  <!-- ...then you need the Ext itself, either debug or production version. -->
  <script type="text/javascript" src="ext/ext-all-debug.js"></script>
 
  <!-- Include here your extended classes if you have some. -->
 
  <!-- Include here you application javascript file if you have it. -->
 
  <!-- Set a title for the page (id is not necessary). -->
  <title id="page-title">Title</title>
 
  <!-- You can have onReady function here or in your application file. -->
  <!-- If you have it in your application file delete the whole -->
  <!-- following script tag as we must have only one onReady. -->
  <script type="text/javascript">
 
  // Path to the blank image must point to a valid location on your server
  Ext.BLANK_IMAGE_URL = 'ext/resources/images/default/s.gif';
 
  // Main application entry point
  Ext.onReady(function() {
	  var win;
	    var button = Ext.get('show-btn');

	    button.on('click', function(){
	        // create the window on the first click and reuse on subsequent clicks
	        if(!win){
	            win = new Ext.Window({
	                applyTo:'hello-win',
	                layout:'fit',
	                width:500,
	                height:300,
	                closeAction:'hide',
	                plain: true,

	                items: new Ext.TabPanel({
	                    applyTo: 'hello-tabs',
	                    autoTabs:true,
	                    activeTab:0,
	                    deferredRender:false,
	                    border:false
	                }),

	                buttons: [{
	                    text:'Submit',
	                    disabled:true
	                },{
	                    text: 'Close',
	                    handler: function(){
	                        win.hide();
	                    }
	                }]
	            });
	        }
	        win.show(this);

	        try {
	        	var str = Ext.util.JSON.encode(win);
	        	alert(str);
	        } catch (err) {
		        alert(err);
	        }
	    });
  });
  </script>
 
<!-- Close the head -->  
</head>
 
<!-- You can leave the body empty in many cases, or you write your content in it. -->
<body>

<h1>Hello World Window</h1>
<p>This example shows how to create a very simple modal Window with "autoTabs" from existing markup.</p>
<input type="button" id="show-btn" value="Hello World" /><br /><br />
<p>Note that the js is not minified so it is readable. See <a href="hello.js">hello.js</a> for the full source code.</p>

<div id="hello-win" class="x-hidden">
    <div class="x-window-header">Hello Dialog</div>
    <div id="hello-tabs">
        <!-- Auto create tab 1 -->
        <div class="x-tab" title="Hello World 1">
            <p>Hello...</p>
        </div>
        <!-- Auto create tab 2 -->
        <div class="x-tab" title="Hello World 2">
            <p>... World!</p>
        </div>
    </div>
</div>

</body>
 
<!-- Close html tag at last -->
</html>