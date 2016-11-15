<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib prefix="spring" uri="../lib/spring.tld"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE HTML>
<html>
<head>
<meta charset="utf-8" />
<title>调研工作台-已计算</title>
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
			<div class="mod-content wrap3 computetable">
			 <h3 class="title"><s class="file"></s>已计算列表
					<div class="sjxz" style="display: inline-block;float: right;font-weight: normal;margin-right:10px">
						<label class="col10">时间范围:</label>
						<span id="sjxz-start"></span>
						<input name="ksrq" id = "ksrq" type="hidden">
						至
						<span id="sjxz-end"></span>
						<input name="jsrq" id = "jsrq" type="hidden">
						<a class="btn2" href="javascript:void(0)">时间选择</a>
					</div>
				</h3>
				<div class="line-5"></div>
				<div class="table-2">
				<table id="table">
					<thead>
						<tr>
							<th style="width:80px">调研日期</th>
							<th style="width:200px">调研目的</th>
							<th style="width:100px">表格开始日期</th>
							<th style="width:100px">表格结束日期</th>
							<th style="width:100px">调研范围</th>
							<th style="width:40%">调研需求</th>
							<th style="width:80px">操作</th>
						</tr>
					</thead>
					<tbody>
					</tbody>
				</table>
				</div>
			</div>
		</div>
	</div>
</div>	
</body>
<script type="text/javascript" >
setRemainingHeight(".computetable","#right");
</script>
<script src="resources/js/xzsj.js"  type="text/javascript"></script>
<script src="resources/js/manual/computed.js"  type="text/javascript"></script>
</html>
