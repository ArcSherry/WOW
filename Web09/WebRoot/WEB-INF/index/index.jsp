<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<base href="<%=basePath%>">

<title>My JSP 'index.jsp' starting page</title>
<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="expires" content="0">
<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
<meta http-equiv="description" content="This is my page">
<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->
<style>
#head {
	width: 100%;
	height: 70px;
	background-color: #6495ED;
}

h1 {
	text-align: center;
	font-weight: bold;
	color: #FFDEAD;
	margin: auto;
}

#foot {
	width: 100%;
	height: 60px;
	background-color: #6495ED
}

h5 {
	text-align: center;
	color: #FFDEAD;
	margin-top: 20px;
	line-height: 60px;
}
</style>
</head>
<body>
	<div id="head">
		<h1>********************</h1>
	</div>
	<iframe src="index?type=left" width="200px" height="400px"
		frameborder="0"></iframe>
	<iframe name="main" src="stus" width="800px" height="400px"
		frameborder="0"></iframe>
	<div id="foot">
		<h5>大美妞有限公司———— by大美妞本人</h5>
	</div>
</body>
</html>
