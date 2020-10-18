<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>
<!DOCTYPE html>
<html>
<head>
<base href="<%=basePath%>" />
<title>Mystery</title>
<link
	href="https://cdn.bootcss.com/twitter-bootstrap/4.1.3/css/bootstrap.min.css"
	rel="stylesheet">
<script src="https://cdn.bootcss.com/jquery/3.1.1/jquery.min.js"></script>
<script src="https://cdn.bootcss.com/vue/2.6.10/vue.min.js"></script>
<script src="https://cdn.bootcss.com/layer/2.2/layer.js"></script>
</head>
<body class="bg-a">

	<div class="mt-4 text-center  row" id="app">
		<div class="mt-2 text-info row">学生信息表</div>
		<c:forEach items="${list}" var="c">
			<td>{c.name}</td>
			<td>{c.sex}</td>
			<td>{c.birth}</td>
			<td>{c.department}</td>
			<td>{c.address}</td>
			<button class="ml-3" @click="selectScore(this.value) "
				value="{c.address}">查询成绩</button>
		</c:forEach>


	</div>




	<script>
		var app = new Vue({
			el : "#app",
			data : {
				list : [],
			},
			methods : {
				selectAll : function() {
					$.ajax({
						url : "/mystery/Student/selectAll",
						data : {},
						success : function(data) {
							app.list = data;
						},
						error : function() {
							layer.msg("网络故障")
						}
					})
				},
				selectById : function() {
					$.ajax({
						url : "/mystery/Student/selectById",
						data : {
							sid : $("#sid").val()
						},
						success : function(data) {
							app.list = data;
						},
						error : function() {
							layer.msg("网络故障")
						}
					})
				},
				selectScore : function(id) {
					layer.msg(id)
				}
			},
			mounted : function() {
				this.selectAll();
			}
		})
	</script>
</body>
</html>