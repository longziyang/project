<%@ page language="java" pageEncoding="UTF-8"%>
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
<link rel="stylesheet" href="web-static/easy/css/bootstrap.css" />
<link rel="stylesheet" href="web-static/icon/css/font-awesome.min.css" />
</head>
<body class="bg-b">
	<div class="container-fluid">
		<div class="row">
			<div class="col text-center bg-primary text-white">M Q 测 试</div>
		</div>
		<div class="mt-1  text-white">
			<input id="str" type="text" placeholder="_请输入妖怪名单" />
			<button class=" bg-info text-white ml-2" onclick="set();">存入消息列队</button>
			<button class=" bg-danger text-white ml-2" onclick="get();">取出</button>
		</div>
		<div id="show"></div>
	</div>
	<!-- js -->
	<script src="web-static/easy/js/jquery.js"></script>
	<script src="web-static/easy/js/avalon.js"></script>
	<script src="web-static/page/jqpaginator.js"></script>
	<script src="web-static/layer/layer.js"></script>
	<script>
		function set() {

			var re = setMq();
			//alert(re);
			//$("#show").html(re);
		}

		function setMq() {
			var re = "";
			$.post("mq/addMq", {
				str : $("#str").val()
			}, function(data) {

				re = data;
				alert(re);
			})

			return re;
		}

		function get() {

			$.post("mq/subMq", {}, function(data) {

				alert(data);
			})

		}
		//
	</script>
</body>
</html>