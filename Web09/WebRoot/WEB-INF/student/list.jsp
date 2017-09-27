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

<title>My JSP 'student.jsp' starting page</title>

<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="expires" content="0">
<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
<meta http-equiv="description" content="This is my page">
<!-- <link rel="stylesheet" href="http://cdn.static.runoob.com/libs/bootstrap/3.3.7/css/bootstrap.min.css"> -->
<link rel="stylesheet" type="text/css"
	href="bootstrap/css/bootstrap.min.css">
<!-- css是给程序员看的 -->
<style>
#main {
	width: 600px;
	margin: 0 auto;
}

.head1 {
	position: relative;
	float: left;
	margin-top: 50px;
	margin-left: 100px;
	overfloat: hidden;
	margin-bottom: 20px;
}

.td {
	width: 50px;
	height: 30px;
	margin-top: 40px;
}

#mes {
	float: right;
	width: 120px;
	height: 30px;
	margin-right: 640px;
}

.fenye {
	float: right;
	margin-right: 340px;
}

.fenye li {
	list-style: none;
	margin: 0px;
	padding: 6px 12px;
	float: left;
	font-size: 14px;
	color: #337ab7;
	line-height: 30px;
	text-align: center;
	border: 1px solid #ddd;
	margin-left: -1;
}

.fenye li:hover {
	background: #eee;
	cursor: pointer;
}

.fenye #pre {
	border-top-left-radius: 4px;
	border-bottom-left-radius: 4px;
}

.fenye #next {
	border-top-right-radius: 4px;
	border-bottom-right-radius: 4px;
}

.fenye .active:hover {
	color: #fff;
	background-color: #337ab7;
	border-color: #337ab7;
}

.fenye .active {
	color: #fff;
	background-color: #337ab7;
	border-color: #337ab7;
}

.btn-group {
	margin-left: 300px;
}
.photo{
	width: 25px;
	height: 25px;
}
#bigphotos{
	margin:0;
	padding:0;
	display:none;
	position:relative;
	width: 100px;
	height: 100px;
	border: 1px solid #666;
}

</style>
<!--  int ye=(Integer)request.getAttribute("ye");%% -->
<script type="text/javascript" src="js/jquery.js"></script>
<script>
	$(document).ready(function() {
				//这里要有一个页，是从servlet传过来的				
				var ye = ${p.ye};
				//这里接收maxYe，用于下面
				var maxYe = ${p.maxYe};
				//这里要定义一个变量来存放你选中的数据，默认为0
				var selectId = 0;
	$("#pre").click(function() {
				//大于1的时候没有前后台交互
				if (ye > 1) {
				var name = $("#name").val();
				var sex = $("#sex").val();
				var age = $("#age").val();
				location.href = "stus?type=search&ye="
						+ (ye - 1)
						+ "&name="
						+ name
						+ "&sex="
						+ sex
						+ "&age=" + age;
						} else {
						$("#mes").html("已经是第一页了");
						}
						})
						//function myFunction() {
						//	alter("已经是第一页了");
						//setTimeout(function() {
						//	alter("已经是第一页了");;
						//}, 1000);

	$("#next").click(function() {
						if (ye < maxYe) {
						var name = $("#name").val();
						var sex = $("#sex").val();
						var age = $("#age").val();
						location.href = "stus?type=search&ye="
							+ (ye + 1)
							+ "&name="
							+ name
							+ "&sex="
							+ sex
							+ "&age=" + age;
						} else {
						//showMes("已经是最后一页了");
						$("#mes").html("已经是最后一页了");
							}
							})
						//stus?ye=%=ye+1%>
						//stus?ye=${ye+1}
						//点击页码进入相应的页
						//$("[name=numPage]")这样就拿到他们了

						$("[name=numPage]").click(
								function() {
									var name = $("#name").val();
									var sex = $("#sex").val();
									var age = $("#age").val();
									ye = $(this).html();
									//html取其中间的值
									location.href = "stus?type=search&ye=" + ye
											+ "&name=" + name + "&sex=" + sex
											+ "&age=" + age;
								})
						//首先都清除
						//$("[name=numPage]").removeClass("active");
						//eq从0开始，对相等的我们进行active	
						//这里不能用ye-1做判断，要用……不然的话，跳转的页面是隔一个跳一个
						//$("[name=numPage]").eq(0).addClass("active");
						$("#add").click(function() {
							location.href = "stus?type=showAdd";
						})
						function showMes(mes) {
							$("#mes").html(mes);
							setTimeout(function() {
								$("#mes").html("");
							}, 1000);
						}
						$("#modify").click(function() {
								//var array = new Array();
								//$("tbody tr").each(function(index, element) {
								//if ($(this).attr("class") == "selected") {
								//array.push($(this).data("id"));
								//}
								//})
								if (selectId == 0) {
								$("#mes").html("请选择一条数据");
								} else {
								location.href = "stus?type=showModify&selectId="
										+ selectId;
								}
										})
						$("#delete").click(function() {
								if (selectId == 0) {
									$("#mes").html("请选择一条数据");
								    } else {
							location.href = "stus?type=delete&selectId="
														+ selectId;
											}
										})
						$("tbody tr").click(function() {
							selectId = $(this).data("id");
							/* alert(selectId); */
							$("tbody tr").css("background", "none");
							$(this).css("background", "red");

						})
						$(".photo").hover(function(event) {
							var str = $(this).attr("src");
							$("#bigphoto").show();
							$("#bigphoto").css({
							left:event.pageX,
							top:event.pageY
							})
							$("#bigphoto img").attr("src",str);
							},function(){
								$("#bigphoto").hide();
							})

						
					})
</script>
</head>
<body>
	<div id="container">
		<%
			//List<Student> list = (List<Student>) request.getAttribute("stus");
		%>
		<div class="head1">
			<form action="stus" method="post">

				<input type="hidden" name="type" value="search" /> 姓名：<input
					type="text" id="name" name="name" value="${condition.name}" /> 性別：<select
					name="sex" id="sex" value="${condition.sex}">
					<option>请选择性别</option>
					<option value="男"
						<c:if test="${condition.sex eq '男'}">selected</c:if>>男</option>
					<option value="女"
						<c:if test="${condition.sex eq '女'}">selected</c:if>>女</option>
				</select> 年齡：<input type="text" name="age" id="age"
					<c:if test="${condition.age!=-1}">
				value="${condition.age}" </c:if> />
				<input type="submit" value="查询" />
			</form>
		</div>
		<div id="main">
			<table class='table  table-bordered'>
				<thead>
					<tr>
						<th>姓名</th>
						<th>性别</th>
						<th>年龄</th>
						<th>班级</th>
						<th>照片</th>
					</tr>
				</thead>
				<tbody>
					<c:forEach items="${stus}" var="stu">
						<!-- 獲得id-->
						<tr data-id="${stu.id}">
							<td>${stu.name}</td>
							<td>${stu.sex}</td>
							<td>${stu.age}</td>
							<td>${stu.bj.banji_name}</td>
							<c:if test="${empty stu.photo}">
							<c:set target="${stu}" property="photo"  value="vc.jsp"/>
							</c:if>
							<td><img src="photos/${stu.photo}" class="photo"/></td>
						
						</tr>

					</c:forEach>
				</tbody>
			</table>
		</div>
		<div id="bigphoto"><img src=""/></div>
		<div>
			<ul class="fenye">
				<li id="pre">上一页</li>
				<c:forEach begin="${p.begin}" end="${p.end}" varStatus="status">
					<li name="numPage"
						<c:if test="${p.ye==status.index}"> class="active"</c:if>>${status.index }</li>
				</c:forEach>
				<li id="next">下一页</li>
			</ul>
			<div id="mes"></div>
		</div>
	</div>
	<div class="btn-group">
		<button id="add" type="button" class="btn btn-success">增加</button>
		<button id="modify" type="button" class="btn btn-warning">修改</button>
		<button id="delete" type="button" class="btn btn-danger">删除</button>
	</div>
	
</body>
</html>
