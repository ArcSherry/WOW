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

<title>My JSP 'subject.jsp' starting page</title>

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
.score{
	width:50px;
}

</style>
<!--  int ye=(Integer)request.getAttribute("ye");%% -->
<script type="text/javascript" src="js/jquery.js"></script>
<script>
	$(document).ready(function() {
					var ye = ${p.ye};
					//这里接收maxYe，用于下面
					var maxYe = ${p.maxYe};
					//这里要定义一个变量来存放你选中的数据，默认为0
					var selectId = 0;
					$("#pre").click(function() {
								//大于1的时候没有前后台交互
								if (ye > 1) {
								var name = $("#name").val();
								location.href = "scores?type=search&ye="
										+ (ye - 1)
										+ "&name="
										+ name;
								} else {
										$("#mes").html("已经是第一页了");
											}
					})
					$("#next").click(function() {
								if (ye < maxYe) {
								var name = $("#name").val();
								location.href = "scores?type=search&ye="
										+ (ye + 1)
										+ "&name="
										+ name
								} else {
								//showMes("已经是最后一页了");
								$("#mes").html("已经是最后一页了");
											}
										})

					$("[name=numPage]").click(function() {
								var name = $("#name").val();
								ye = $(this).html();
								//html取其中间的值
								location.href = "subs?type=search&ye=" + ye
										+ "&name=" + name;
								})
					$("#add").click(function() {
							location.href = "subs?type=showAdd";
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
							location.href = "subs?type=showModify&selectId="
														+ selectId;
											}
										})
						$("#delete").click(function() {
							if (selectId == 0) {
							$("#mes").html("请选择一条数据");
							} else {
							location.href = "subs?type=delete&selectId="
														+ selectId;
											}
										})
						$("tbody tr").click(function() {
							selectId = $(this).data("id");
							/* alert(selectId); */
							$("tbody tr").css("background", "none");
							$(this).css("background", "red");
						})
					
					/* 这是班级级联的部分 */
	$("#bj").change(function(){
		$.ajax({
			url:"subs?type=searchSubByBj",
			data:{
				bjId:$(this).val()
				},
			type:"post",
			dataType:"json",
			success:function(data){
			var ops="";
		$.each(data,function(index,element){
			ops+="<option value="+element.id+">"
			+element.name+"</option>";
			})
		$("#sub").html(ops);
			}
	})
	
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
			<form action="scores" method="post">
				<input type="hidden" name="type" value="search" /> 姓名：<input
					type="text" id="name" name="name" value="${condition.stu.name}" />
				<%-- *****************************************************
				<label>班级</label> <select id="bj" name="bj"
					class="form-control value"><option value="0">请选择班级</option>
					<c:forEach items="${bjs}" var="bj">
						<option value="${bj.id}" <c:if test="${condition.bj.id==bj.id }">selected</c:if>>${bj.name}</option>
					</c:forEach>
				</select> 
				***************************************************** --%>
				<!-- value="${condition.stu.bj.banji_name} -->
				<label>班级</label> <select id="bj" name="bj">
					<option value="0">请选择班级</option>
					<c:forEach items="${bjs}" var="bj">
						<option value="${stu.bj.banji_id}"
							<c:if test="${condition.stu.bj.banji_id==banji_id}">selected</c:if>>
							${bj.banji_name}</option>
					</c:forEach>
				</select> 
				<label>科目</label> 
				<select id="sub" name="sub">
					<option>请选择科目</option>
					<c:forEach items="${subs}" var="sub">
						<%-- <c:if test="${condition.sub.id==subID }">这里的sub.id是subject里的id，等于的是relative里的subject里的id --%>
						<option value="${sub.id}"
							<c:if test="${condition.sub.id==subID }">selected</c:if>>${sub.sub_name}</option>
					</c:forEach>
				</select> <input type="submit" value="查询" />
			</form>
		</div>
		<div id="main">
			<table class='table  table-bordered'>
				<thead>
					<tr>
						<th>姓名</th>
						<th>班级</th>
						<th>课程</th>
						<th>等级</th>
						<th>成绩</th>
					</tr>
				</thead>
				<tbody>
					<c:forEach items="${scs}" var="sc">
						<!-- 獲得id-->
						<tr data-id="${sc.id}">
							<td>${sc.stu.name}</td>
							<td>${sc.stu.bj.banji_name}</td>
							<td>${sc.sub.sub_name}</td>
							<td>${sc.grade}</td>
							<%-- <td>
							<c:if test="${sc.score==null}">未录入</c:if>
							</td> --%>
							<%-- score转换成Integer类型--%>
							<td>
						    <input class="score"
								<c:choose>
								<c:when test="${sc.score!=null}">
								value="${sc.score}"
								</c:when>
								<c:otherwise>
								placeholder="未录入"
								</c:otherwise> 
								</c:choose>/>
							</td>
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
	</div>
	

</body>
</html>
