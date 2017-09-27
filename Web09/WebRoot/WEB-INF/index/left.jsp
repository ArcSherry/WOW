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

<title>My JSP 'left.jsp' starting page</title>

<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="expires" content="0">
<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
<meta http-equiv="description" content="This is my page">
<!--
	<link rel="stylesheet" type="text/css" href="bootstrap/css/bootstrap.min.css">
	-->

<style>
#menu .yi {
	border: 1px solid black;
	background: #6495ED;
	width: 150px;
	height: 30px;
	line-height: 30px;
	font-size: 20px;
	color: #FFDEAD;
	font-weight: bold;
	text-align: center;
	border-radius: 3px;
	border-radius: 4px;
	margin-top: 15px;
	font-size: 30px;
}

#menu span {
	font-weight: bold;
	font-size: 14px;
}

#menu .yi:hover {
	background-color: silver;
	cursor: pointer
}

#menu .er li {
	background: #6495ED;
	border: 1px solid black;
	text-align: center;
	color: #FFDEAD;
	border-radius: 4px;
	width: 150px;
	font-weight: bold;
	margin-top: 5px;
	cursor: pointer
}

.er {
	margin: 0;
	padding: 0;
}

li {
	list-style-type: none;
}
</style>

<script type="text/javascript" src="js/jquery.js"></script>
<script type="text/javascript">
	$().ready(function() {
		$(".yi").click(function() {
			if ("none" == $(this).next().css("display")) {
				$(this).next().slideDown(500);
				$(this).find("span").html("[-]");
			} else {
				$(this).next().slideUp(500);
				$(this).find("span").html("[+]");
			}
		})
	})
</script>
</head>
<body>
	<div id="menu">
		<div class="yi">
			<span>[-]</span>学生管理
		</div>
		<ul class="er">
			<li><a href="stus" target="main">查看</a></li>
			<li><a href="stus?type=showAdd" target="main">新增</a></li>
		</ul>
		<div class="yi">
			<span>[-]</span>班级管理
		</div>
		<ul class="er">
			<li><a href="bjs" target="main">查看</a></li>
			<li><a href="bjs?type=showAdd" target="main">新增</a></li>
		</ul>
		<div class="yi">
			<span>[-]</span>科目管理
		</div>
		<ul class="er">
			<li><a href="subs" target="main">查看</a></li>
			<li><a href="subs?type=showAdd" target="main">新增</a></li>
		</ul>
		<div class="yi">
			<span>[-]</span>成绩管理
		</div>
		<ul class="er">
			<li><a href="scores" target="main">查看</a></li>
			<li><a href="scores?type=manage" target="main">管理</a></li>
		</ul>
	</div>
</body>
</html>
