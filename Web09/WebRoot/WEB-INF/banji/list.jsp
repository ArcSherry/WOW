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

<title>My JSP 'banji.jsp' starting page</title>

<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="expires" content="0">
<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
<meta http-equiv="description" content="This is my page">

<link rel="stylesheet" type="text/css"
	href="bootstrap/css/bootstrap.min.css">
<!-- css是给程序员看的 -->
<style>
#main {
	width: 600px;
	margin: 0 auto;
}

.head1 {
	float: left;
	margin-top: 50px;
	margin-left: 300px;
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
</style>
<!--  int ye=(Integer)request.getAttribute("ye");%% -->
<script type="text/javascript" src="js/jquery.js"></script>

<script>
	$(document).ready(function() {
		var ye = ${p.ye};
		var maxYe = ${p.maxYe};
		var selectId = 0;
		$("#pre").click(function() {
			if (ye > 1) {
				var name = $("#name").val();
				location.href = "bjs?ye=" + (ye - 1) + "&name=" + name;
			} else {
				$("#mes").html("已经是第一页了");
			}
		})
		$("#next").click(function() {
			if (ye < maxYe) {
				var name = $("#name").val();
				location.href = "bjs?ye=" + (ye + 1) + "&name=" + name;
			} else {
				$("#mes").html("已经是最后一页了");
			}
		})
		$("[name=numPage]").click(function() {
			var name = $("#name").val();
			ye = $(this).html();
			location.href = "bjs?ye=" + ye + "&name=" + name;
		})
		$("#add").click(function() {
			location.href = "bjs?type=showAdd";
		})
		function showMes(mes) {
			$("#mes").html(mes);
			setTimeout(function() {
				$("#mes").html("");
			}, 1000);
		}
		$("#modify").click(function() {

			if (selectId == 0) {
				$("#mes").html("请选择一条数据");
			} else {
				location.href = "bjs?type=showModify&selectId=" + selectId;
			}
		})
		$("#delete").click(function() {
			if (selectId == 0) {
				$("#mes").html("请选择一条数据");
			} else {
				location.href = "bjs?type=delete&selectId=" + selectId;
			}
		})
		$("#manageSub").click(function() {
			if (selectId == 0) {
				$("#mes").html("请选择一条数据");
			} else {
				location.href = "bjs?type=manageSub&selectId=" + selectId;
			}
		})

		$("tbody tr").click(function() {
			selectId = $(this).data("id");
			/* alert(selectId); */
			$("tbody tr").css("background", "none");
			$(this).css("background", "red");

		})
	})
</script>
</head>
<body>
	<div id="container">
		<%
			//List<BanJi> list = (List<BanJi>) request.getAttribute("bjs");
		%>
		<div class="head1">
			<form action="bjs" method="post">
				<input type="hidden" name="type" value="search" />
				<%--  班级：<input
					type="name" id="name" value="${condition.banji_name}" /> <input
					type="submit" value="查询" /> --%>
				
				<label>班级</label>
				<select id="bj" name="bj">
				<option>请选择班级</option>
				<c:forEach items="${bjAll}" var="bj">
				<option value="${bj.banji_id}"
				<c:if test="${condition.banji_id==bj_id}">selected</c:if>>
				${bj.banji_name}</option>
				<!-- 这里的下拉框里的为什么会和当前页面显示的一致 -->
					</c:forEach>
				</select>
			</form>
		</div>
		<div id="main">

			<table class='table  table-bordered'>
				<thead>
					<tr>
						<th>班级</th>
						<th>班级人数</th>
					</tr>
				</thead>
				<tbody>
					<c:forEach items="${bjs}" var="bj">
						<!-- 獲得id-->
						<tr data-id="${bj.banji_id}">
							<td>${bj.banji_name}</td>
							<td>${bj.stuNums}</td>
						</tr>
					</c:forEach>
				</tbody>
			</table>
		</div>
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

		<div class="btn-group">
			<button id="add" type="button" class="btn btn-success">增加</button>
			<button id="modify" type="button" class="btn btn-warning">修改</button>
			<button id="delete" type="button" class="btn btn-danger">删除</button>
			<button id="manageSub" type="button" class="btn btn-primary">管理班級</button>
		</div>
</body>
</html>
