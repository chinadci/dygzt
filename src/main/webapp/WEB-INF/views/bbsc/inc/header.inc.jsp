<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<div id="welcome">
欢迎您，${yhxx.yhmc}${yhxx.yhsf} 【<c:if test="${yhxx.yhqx.contains('管理员')}"> <a href="javascript:void(0)" class="changemmbtn">修改密码</a> |</c:if> <a href="login2.do">退出系统</a> 】
</div>
<div id="header">
	<div id="header_bbsc"></div>
</div>
<script src="resources/js/changepassword.js"></script>
