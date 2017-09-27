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
* {
	margin: 0;
	padding: 0;
}

.btn-group {
	margin-left: 300px;
	clear: both;
}

#main1 {
	margin-top: 10px;
	clear: both;
	width: 800px;
	height: 150px;
}

#main2 {
	margin-top: 50px;
	clear: both;
	width: 800px;
	height: 150px;
}

ul {
	border: 1px solid black;
	width: 800px;
	height: 150px;
	overflow: hidden;
	list-style: none;
	overflow: hidden;
}

#sub li {
	list-style:none;
	float:left;
	margin-top: 10px;
	width: 100px;
	height: 30px;
	background: #6495ED;
	margin-right: 10px;
	text-align: center;
	line-height: 30px;
	color: #fff;
	cursor: pointer;
}

#nosub li {
	list-style:none;
	float:left;
	margin-top: 10px;
	width: 100px;
	height: 30px;
	background: #6495ED;
	margin-right: 10px;
	text-align: center;
	line-height: 30px;
	color: #fff;
	cursor: pointer;
}

#body {
	margin-top: 10px;
	margin-left: 10px;
	float: left;
	margin-left: 10px;
}

.selected {
	background: red !important;
}

#mes {
	float: right;
	width: 120px;
	height: 30px;
	margin-right: 640px;
}

#bj {
	width: 120px;
	height: 30px;
	margin-bottom: 10px;
	font-size: 20px;
	font-weight: bold;
	font-size: 20px;
	font-size: 20px
}
</style>
<!--  int ye=(Integer)request.getAttribute("ye");%% -->
<script type="text/javascript" src="js/jquery.js"></script>
<script>
	$(document).ready(function() {
				/* 	$(document).on("click", "li", function() {
				=//$("li").click(function() {
				=$(this).toggleClass("selected");
				}) */
				//必须得选中东西，选中东西之后，才能对这条数据进行添加和删除
				var searchID;
				$(document).on("click","li",function(){
				$(this).toggleClass("selected");
				searchID=$(this).val();
					})
		$("#add").click(function() {
				var subIds = new Array();
				
				$("#nosub li").each(function(index,elment) {
				if ($(this).attr("class") == "selected") {
					subIds.push($(this).data("banjiID"));}
						})
				if (subIds.length > 0) {
					$.ajax({
						url : "bjs",
						data : "type=addSub&bjId="
						+$("#havaSelect").data("id")+ "&subIds="+searchID+"${sub.id}",
						type : "get",
						dataType : "text",
						success : function(data) {
						var str=data.split("|"); //将传回的字符串拆分成已有的课程和没有的课程
						var subHaveList=str[0]; //已有的课程
						var subNoList=str[1];  //没有的课程
						//已有的课程
						var subHave = subHaveList.split(";")
						var obs ="";
					for(var i=0;i<subHave.length;i++){
						//拆分出已有的组（id+课程名）
						var subID=subHave[i].split(",")[0];
						var subName=subHave[i].split(",")[1];
						obs+="<li value="+subID+">"+subName+"</li>"
					$("#sub").html(obs);
						}
					//没有的课程
						var subNo=subNoList.split(";")
						var obs2="";
					for(var j=0;j<subNo.length;j++){
						var subID=subNo[j].split(",")[0];
						var subName=subNo[j].split(",")[1];
						obs2+="<li value="+subID+">"+subName+"</li>"
						$("#nosub").html(obs2);
							}
						/* 	var strs = data.split("-|-");
						var subs = JSON.parse(strs[0]);
						var noSubs = JSON.parse(strs[1]);
						var subsStr = "";
					for (var i = 0; i < subs.length; i++) {
						subsStr += "<li data-subid="+subs[i].id+">"
						+ subs[i].name
						+ "</li>"
							}
					$("#sub").html(subsStr);
						var noSubsStr = "";
					for (var i = 0; i < noSubs.length; i++) {
						noSubsStr += "<li data-subid="+noSubs[i].id+">"
						+ noSubs[i].name
						+ "</li>"
							}
					$("#noSub").html(noSubsStr); */
						}
							})
					} else {
					showMes("请选中一条数据");
						}
					})
		$("#delete").click(function() {
					var subIds = new Array();
					$("#sub li").each(function(index,element) {
					if ($(this).attr("class") == "selected") {
					subIds.push($(this).data("banjiID"));
						}
					   		})
					if (subIds.length > 0) {
					$.ajax({
							url : "bjs",
							data : "type=deleteSub&bjId="
							+ $("#havaSelect").data("id")
							+ "&subIds="+ searchID,
							type : "get",
							dataType : "text",
							success : function(data) {
							var str=data.split("|"); //将传回的字符串拆分成已有的课程和没有的课程
							var subHaveList=str[0]; //已有的课程
							var subNoList=str[1];  //没有的课程
							//已有的课程
							var subHave = subHaveList.split(";")
							var obs ="";
							for(var i=0;i<subHave.length;i++){
							//拆分出已有的组（id+课程名）
							var subID=subHave[i].split(",")[0];
							var subName=subHave[i].split(",")[1];
							obs+="<li value="+subID+">"+subName+"</li>"
							$("#sub").html(obs);
								}
							//没有的课程
							var subNo=subNoList.split(";")
							var obs2="";
							for(var j=0;j<subNo.length;j++){
							var subID=subNo[j].split(",")[0];
							var subName=subNo[j].split(",")[1];
							obs2+="<li value="+subID+">"+subName+"</li>"
							$("#nosub").html(obs2);
								}
							/* function(data) {
							var strs = data.split("-|-");
							var subs = JSON.parse(strs[0]);
							var noSubs = JSON.parse(strs[1]);
							var subsStr = "";
							for (var i = 0; i < subs.length; i++) {
							subsStr += "<li data-subid="+subs[i].id+">"+ subs[i].name+"</li>"
									}
							$("#sub").html(subsStr);
							var noSubsStr = "";
							for (var i = 0; i < noSubs.length; i++) {
							noSubsStr += "<li data-subid="+noSubs[i].id+">"
							+ noSubs[i].name
							+ "</li>"
									}
							$("#noSub").html(noSubsStr); */
															}
														})

											} else {

												showMes("请选中一条数据");
											}

										})

						//	$("#delete").click(function() {
						//var subIds = new Array();
						//$("#nosub").each(function(index, elment) {
						//if ($(this).attr("class") == "selected") {
						//	subIds.push($(this).data("id"));
						//}
						//})
						//if (subIds.length > 0) {
						//$.ajax({
						//url : "bj",
						//data : {
						//	subIds : subIds
						//},
						//type : "post",
						//dataType : "text",
						//success : function(data) {
						//}
						//})
						//} else {
						//	$("#mes").html("请选中一条数据");
						//}
						//})
						$("#bj").change(function() {
									$.ajax({
												url : "bj",
												data : "type=searchSub&bjId="
													    + $("#bj").val(),
												type : "get",
												dataType : "text",
												success : function(data) {
									 var strs = data.split("-|-");
									 var subs = JSON.parse(strs[0]);
									 var noSubs = JSON.parse(strs[1]);
									 var subsStr = "";
									 for (var i = 0; i < subs.length; i++) {
										subsStr += "<li data-subid="+subs[i].id+">"
											+ subs[i].name
											+ "</li>"
											}
									$("#sub").html(subsStr);
										var noSubsStr = "";
										for (var i = 0; i < noSubs.length; i++) {
										noSubsStr += "<li data-subid="+noSubs[i].id+">"
													+ noSubs[i].name
													+ "</li>"
															}
															$("#noSub").html(
																	noSubsStr);
														}
													})
										})
					})
</script>
</head>
<body>
<%-- 	<select id="bj"><c:forEach items="${bjs}" var="bj">
			<option <c:if test="${banji_id==banjiID}" >selected</c:if>
				data-bjid="${banji_id}" value="${banji_id}">${banji_name }</option>
		</c:forEach>
	</select>  --%>
	<div id="main1">
		<form method="post">
			<ul id="sub" class="box">
				<c:forEach items="${bj.subs}" var="sub">
					<li data-subid="${sub.id}" value="${sub.id}" >${sub.sub_name}</li>
				</c:forEach>
			</ul>
		</form>
	</div>
	<div id="havaSelect" data-id="${bjId}">
		
		<input id="add" type="button" class="btn btn-success" value="↑" /> 
		<input id="delete" type="button" class="btn btn-success" value="↓" />
		<div id="mes"></div>
	</div>
	<div id="main2">
		<form method="post">
			<ul id="nosub" class="box">
				<c:forEach items="${noSubs}" var="sub">
					<li data-subid="${sub.id}" value="${sub.id}" style="float: left">${sub.sub_name}</li>
				</c:forEach>
			</ul>
		</form>
	</div>

</body>
</html>



