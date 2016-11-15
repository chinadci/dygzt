<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib prefix="spring" uri="../lib/spring.tld"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE HTML>
<html>
<head>
<meta charset="utf-8" />
<title>调研工作台-待计算</title>
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
			<div class="table-2">
				<table id="table">
					<thead>
						<tr>
							<th style="width:80px">调研日期</th>
							<th style="width:200px">调研目的</th>
							<th style="width:100px">表格开始日期</th>
							<th style="width:100px">表格结束日期</th>
							<th style="width:100px">调研范围</th>
							<th style="width:50%">调研需求</th>
							<th style="width:80px">操作</th>
						</tr>
					</thead>
					<tbody>
					<c:forEach items="${list}" var="manual">
						<tr>
							<td>${manual.dyrq}</td>
							<td>${manual.dymd}</td>
							<td>${manual.ksrq}</td>
							<td>${manual.jsrq}</td>
							<td>${manual.dyfw}</td>
							<td>${manual.dyxq}</td>
							<td>
								<div class='cpt_menu_view' data-url='showManualResearch.do?researchid=${manual.btn}' title='查看'></div>
								<div class='cpt_menu_upload' data-researchid='${manual.btn}' title='上传结果'></div>
							</td>
						</tr>
					</c:forEach>
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
<script src="resources/js/jquery/ajaxfileupload.js"></script>
<script src="resources/js/manual/compute.js"></script>
</html>
