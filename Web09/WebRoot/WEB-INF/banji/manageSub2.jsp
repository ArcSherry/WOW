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
	$(document)
			.ready(
					function() {
						$(document).on("click", "li", function() {
							//$("li").click(function() {
							$(this).toggleClass("selected");
						})
						//必须得选中东西，选中东西之后，才能对这条数据进行添加和删除
						$("#add")
								.click(
										function() {
											var subIds = new Array();
											$("#nosub li")
													.each(
															function(index,
																	elment) {
																if ($(this)
																		.attr(
																				"class") == "selected") {
																	subIds
																			.push($(
																					this)
																					.data(
																							"banjiID"));
																}
															})
											if (subIds.length > 0) {
												$
														.ajax({
															url : "bjs",
															data : "type=addSub&bjId="
																	+ $("#bj")
																			.val()
																	+ "&subIds="
																	+ subIds,
															type : "get",
															dataType : "text",
															success : function(
																	data) {
																var strs = data
																		.split("-|-");
																var subs = JSON
																		.parse(strs[0]);
																var noSubs = JSON
																		.parse(strs[1]);
																var subsStr = "";

																for (var i = 0; i < subs.length; i++) {

																	subsStr += "<li data-subid="+subs[i].id+">"
																			+ subs[i].name
																			+ "</li>"
																}
																$("#sub")
																		.html(
																				subsStr);
																var noSubsStr = "";
																for (var i = 0; i < noSubs.length; i++) {

																	noSubsStr += "<li data-subid="+noSubs[i].id+">"
																			+ noSubs[i].name
																			+ "</li>"

																}
																$("#noSub")
																		.html(
																				noSubsStr);
															}
														})

											} else {

												showMes("请选中一条数据");
											}

										})
						$("#delete")
								.click(
										function() {
											var subIds = new Array();
											$("#sub li")
													.each(
															function(index,
																	element) {
																if ($(this)
																		.attr(
																				"class") == "selected") {
																	subIds
																			.push($(
																					this)
																					.data(
																							"banjiID"));

																}
															})
											if (subIds.length > 0) {
												$
														.ajax({
															url : "bj",
															data : "type=deleteSub&bjId="
																	+ $("#bj")
																			.val()
																	+ "&subIds="
																	+ subIds,
															type : "get",
															dataType : "text",
															success : function(
																	data) {

																var strs = data
																		.split("-|-");

																var subs = JSON
																		.parse(strs[0]);
																var noSubs = JSON
																		.parse(strs[1]);
																var subsStr = "";
																for (var i = 0; i < subs.length; i++) {

																	subsStr += "<li data-subid="+subs[i].id+">"
																			+ subs[i].name
																			+ "</li>"
																}
																$("#sub")
																		.html(
																				subsStr);
																var noSubsStr = "";
																for (var i = 0; i < noSubs.length; i++) {
																	noSubsStr += "<li data-subid="+noSubs[i].id+">"
																			+ noSubs[i].name
																			+ "</li>"

																}
																$("#noSub")
																		.html(
																				noSubsStr);

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
						$("#bj")
								.change(
										function() {
											$
													.ajax({
														url : "bj",
														data : "type=searchSub&bjId="
																+ $("#bj")
																		.val(),
														type : "get",
														dataType : "text",
														success : function(data) {
															var strs = data
																	.split("-|-");
															var subs = JSON
																	.parse(strs[0]);
															var noSubs = JSON
																	.parse(strs[1]);
															var subsStr = "";
															for (var i = 0; i < subs.length; i++) {
																subsStr += "<li data-subid="+subs[i].id+">"
																		+ subs[i].name
																		+ "</li>"
															}
															$("#sub").html(
																	subsStr);
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
	<div class="modal fade" id="myModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
	<div class="modal-dialog">
		<div class="modal-content">
			<div class="modal-header">
				<button type="button" class="close" data-dismiss="modal" aria-hidden="true">
					&times;
				</button>
				<h4 class="modal-title" id="myModalLabel">
					模态框（Modal）标题
				</h4>
			</div>
			<div class="modal-body">
				在这里添加一些文本
			</div>
			<div class="modal-footer">
				<button type="button" class="btn btn-default" data-dismiss="modal">关闭
				</button>
				<button type="button" class="btn btn-primary">
					提交更改
				</button>
			</div>
		</div><!-- /.modal-content -->
	</div><!-- /.modal -->
</div>

</body>
</html>



