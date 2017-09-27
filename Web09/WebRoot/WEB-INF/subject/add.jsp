<%@ page language="java" import="java.util.*,entity.*,dao.*"
	pageEncoding="utf-8"%>
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

<title>My JSP 'add.jsp' starting page</title>

<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="expires" content="0">
<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
<meta http-equiv="description" content="This is my page">

<link rel="stylesheet" type="text/css"
	href="bootstrap/css/bootstrap.min.css">

<style>
#main {
	width: 240px;
	margin: 10px auto;
}

#form lable {
	float: left;
	width: 50px;
	height: 30px;
	text-align: center;
	text-height: 40px;
	float: left;
}

#form .value {
	float: left;
	width: 200px;
}

#form #d1 input {
	width: 150px;
	height: 30px;
	border-radius: 4px;
}


#form div {
	clear: both;
	margin-top: 30px;
	overflow: hidden;
}

#btn {
	text-align: center;
}
</style>
<script type="text/javascript" src="js/jquery.js"></script>
<script type="text/javascript">
	$(document).ready(function() {
		$("#add").click(function() {
			location.href = "subs?type=add"
		})

	})
</script>
</head>

<body>
	<div id="main">
		<form id="form" action="subs" method="post">
			<input type="hidden" name="type" value="add">
			<div id="d1">
				<lable>课程</lable>
				<!-- form表单通过name传值 -->
				<input type="text" name="sub_name" class="form-control"
					placeholder="请输入课程名称" />
			</div>
			<div id="btn">
			<button type="submit" id="add" class="btn btn-danger">保存</button>
			</div>
		</form>
	</div>
</body>
</html>
