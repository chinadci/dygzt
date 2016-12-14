<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib prefix="spring" uri="../lib/spring.tld"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE HTML> 
<html lang="zh-CN">
<head>
<meta charset="UTF-8">
<title>调研工作台-登录</title>
<%@include file="inc/file.inc.jsp"%>
<script src="resources/js/jquery/css3-mediaqueries.js"></script>
<link rel="stylesheet" type="text/css" href="resources/css/login.css">
</head>
<body>
	<div class="container">
		<div class="content-wrapper">
			<div class="bg-img"></div>
			<div class="login-form">
				<form id="login-form" action="login.do" method="post" onsubmit="return check();">
					<div>
						<label >法院名称：</label>
						<input name="fym" id = "xzfy" type="text" class="text" data-method="xzfy.aj" value = "${fymc}"> 
						<input name="fydm" id = "fydm" type="hidden" value = "${fydm}">
					</div>
					<div>
						<label >用 户 名：</label>
						<input name="yhm" id="username" type="text" class="text" />
					</div>
					<div>
						<label>密&nbsp;&nbsp;码：</label>
						<input name="mm" id="password" type="password" class="text" />
					</div>
					<div class="rem-msg-div">
						<span class="rem-div">
							<input type="checkbox" id="remember">记住密码
						</span>
						<span class="msg-div">
							<span id="msg">${message}</span>
						</span>
					</div>
					<div class="button-div">
						<input type="submit" class="submit-button" id="submit" value="" onsubmit = "return check();" />
					</div>
				</form>
			</div>
		</div>
	</div>
	<script type="text/javascript">
//		max_body();
	</script>
	<script src="resources/js/xzfy.js"  type="text/javascript"></script>
	<script src="resources/js/login.js"  type="text/javascript"></script>
</body>
</html>
