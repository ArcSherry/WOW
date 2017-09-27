<%@ page language="java" import="java.util.*,entity.*,dao.*"
	pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
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

<title>My JSP 'modify.jsp' starting page</title>

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

.form span {
	margin: 0 10px;
	font-size: 14px;
	font-weight: bold;
}
</style>
<script type="text/javascript" src="js/jquery.js"></script>
<script type="text/javascript">
	$(document).ready(function() {
		$("#modify").click(function() {
			location.href = "stus?type=modify"
		})

	})
</script>
</head>
<body>
	<div id="main">
		<form id="form" action="bjs" method="post">
			<input type="hidden" name="type" value="modify"> <input
				type="hidden" name="selectId" value="${bjs.banji_id}">
			<div id="d1">
				<lable>班级</lable>
				<input type="text" name="name" class="form-control"
					placeholder="请输入班级" value="${bjs.banji_name}" />
			</div>
			<div id="btn">
				<button type="submit" id="modify" class="btn btn-danger">保存</button>
			</div>
		</form>
	</div>
</body>
</html>
