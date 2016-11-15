<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib prefix="spring" uri="../lib/spring.tld"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE HTML>
<html>
<head>
<meta charset="utf-8" />
<title>调研工作台-增加系统人员</title>
<%@include file="inc/file.inc.jsp"%>
</head>
<body>
<div id="content">
	<%@include file="inc/header.inc.jsp"%>
	<div id="main">
		<div id="left">
			<%@include file="inc/menu.inc.jsp"%>
		</div>
		<div id="right">
			<div class="collapse-button"></div>
			<div class="mod-content wrap3 addtable">
				<div class="form2">
				<h3>
					<s class="ok"></s>增加系统人员
				</h3>
				<div class="line-5"></div>
				<form action="addPerson.aj" method="post" class="addPersonform" style="margin-top: 15px;">
					<input name="fydm" id = "fydm" type="hidden" value="120000 200"/>
					<div>
						<label for="yhm" class="col10">用户名<span style="color:red">*</span>:</label>
						<input name="yhm" id="yhm" class="col20" type="text" />
					</div>
					<div>
						<label for="name" class="col10">姓名<span style="color:red">*</span>:</label>
						<input name="name" id="name" class="col20" type="text" />
					</div>
					<div>
						<label class="col10">权限<span style="color:red">*</span>:</label>
						<input type="checkbox" name="qxxq" value="计算人" >计算人
						<input type="checkbox" name="qxxq" value="管理员" >管理员
					</div>
					<div>
						<label for="phone" class="col10">手机:</label>
						<input name="phone" id="phone" class="col20" type="text" />
					</div>
					<div>
						<label for="lxfs" class="col10">Coco:</label>
						<input name="lxfs" id="lxfs" class="col20" type="text"/>
					</div>
					<div class="error-warn"></div>
					<div class="formButGroup">
						<a id="btn_add" class="btn1" href="javascript:void(0)">提交</a>
					</div>
				</form>
			</div>
			</div>
		</div>
	</div>
</div>	
</body>
<script type="text/javascript" >
setRemainingHeight(".addtable","#right");
</script>
<script src="resources/js/addSE.js"  type="text/javascript"></script>
</html>
