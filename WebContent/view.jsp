<html>
<head>
  <meta http-equiv="Content-Type" content="text/html; charset=utf-8">
  <link rel="stylesheet" type="text/css" href="ext/resources/css/ext-all.css">
  <link rel="stylesheet" type="text/css" href="ext/resources/css/examples.css">

  <META HTTP-EQUIV="CACHE-CONTROL" CONTENT="NO-CACHE">
  <!-- Include here your own css files if you have them. -->
 
  <script type="text/javascript" src="ext/adapter/ext/ext-base.js"></script>
  <script type="text/javascript" src="ext/ext-all-debug.js"></script>
 
  <!-- Include here your extended classes if you have some. -->
 
  <!-- Include here you application javascript file if you have it. -->
  <script type="text/javascript" src="js/ajax.js"></script>
 
  <!-- Set a title for the page (id is not necessary). -->
  <title id="page-title">Application</title>
  <style type="text/css">
	<!--
	.milton-icon {
	background: url(res/dm.png) no-repeat;
	}
	-->
  </style>

  <script type="text/javascript">

  // Path to Chart
  Ext.chart.Chart.CHART_URL = 'res/charts.swf';
  // Path to the blank image must point to a valid location on your server
  Ext.BLANK_IMAGE_URL = 'ext/resources/images/default/s.gif';
 
  // Main application entry point
  Ext.onReady(function() {
	  jfm_request('onLoad',null,null);
  });
  </script>
 
<!-- Close the head -->  
</head>
 
<!-- You can leave the body empty in many cases, or you write your content in it. -->
<!-- bgcolor=#000000#4e84b3 -->
<body>

<div id="hello-win" class="x-hidden">
    
</div>

</body>
 
<!-- Close html tag at last -->
</html>