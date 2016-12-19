<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%--yhxx 这个变量是在拦截器中设置的，每次都会取得 usercontext 放在 yhxx 中--%>
<div id="welcome">
欢迎您，${yhxx.yhmc }${yhxx.yhsf} 【<c:if test="${yhxx.yhqx.contains('管理员')||yhxx.yhqx.contains('计算人')}"> <a href="javascript:void(0)" class="changemmbtn">修改密码</a> |</c:if> <a href="javascript:void(0)" class="changelxfsbtn">修改联系方式</a> | <a href="login.do">退出系统</a> 】
</div>
<div id="header">
	<div id="header1"></div>
</div>
<script src="resources/js/changepassword.js"></script>
<script src="resources/js/changelxfs.js"></script>