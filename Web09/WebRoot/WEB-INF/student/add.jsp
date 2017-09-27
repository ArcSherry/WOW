<%@ page language="java" import="java.util.*,entity.*,dao.*"
	pageEncoding="utf-8"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core"  prefix="c" %>
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
	margin: 8px auto;
}

#form lable {
	float: left;
	width: 50px;
	height: 30px;
	text-align: center;
	text-height: 40px;
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

#form #d3 input {
	width: 150px;
	height: 30px;
	border-radius: 4px;
}

#form #d4 {
	width: 150px;
	height: 30px;
	border-radius: 4px;
}
#form #d5 input {
	width: 150px;
	height: 30px;
	border-radius: 4px;
}
#form div {
	clear: both;
	margin-top: 20px;
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
			location.href = "stus?type=add"
		})

	})
</script>
</head>

<body>
	<div id="main">
		<form id="form" action="stus?type=add" method="post" enctype="multipart/form-data">
			<!-- <input type="hidden" name="type" value="add"> -->
			<div id="d1">
				<lable>姓名</lable>
				<input type="text" name="name" class="form-control"
					placeholder="请输入姓名" />
			</div>
			<div id="d2">
				<lable>性别</lable>
				<label class="radio-inline"> <input type="radio" name="sex"
					 value="男" checked>男
				</label> <label class="radio-inline"> <input type="radio" name="sex"
					 value="女">女
				</label>
			</div>
			<div id="d3">
				<lable>年龄</lable>
				<input type="text" name="age" class="form-control"
					placeholder="请输入年龄" />
			</div>
			<div id="d4">
				<lable>班級</lable>
				<select id="bjSelect" name="bj" >
					<c:forEach items="${bjs}" var="bj">
						<option value="${bj.banji_id}">${bj.banji_name}</option>
					</c:forEach>
				</select>					
			</div>
			<div id="d5">
				<lable>照片</lable>
				<input type="file" name="photo" class="form-control"
					placeholder="请输入姓名" />
			</div>
			<div id="btn">
				<button type="submit" id="add" class="btn btn-danger">保存</button>
			</div>
		</form>
	</div>
</body>
</html>
