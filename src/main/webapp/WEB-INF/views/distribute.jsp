<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib prefix="spring" uri="../lib/spring.tld"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE HTML>
<html>
<head>
<meta charset="utf-8" />
<title>调研工作台-调研待指派</title>
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
			<div class="mod-content wrap3 distributetable">
			<div class="table-2">
				<table id="table">
					<thead>
						<tr>
							<th style="width:80px">调研日期</th>
							<th style="width:200px">调研目的</th>
							<th style="width:50%">调研需求</th>
							<th style="width:100px">计算人</th>
							<th style="width:90px">操作</th>
						</tr>
					</thead>
					<tbody>
					<c:forEach items="${list}" var="manual">
						<tr>
							<td>${manual.dyrq}</td>
							<td>${manual.dymd}</td>
							<td>${manual.dyxq}</td>
							<td>
								<select name="jsr">
								<c:forEach items="${jsrs}" var="jsr">
									<option data-name="${jsr.name }" data-fydm="${jsr.fydm }">${jsr.name }</option>
								</c:forEach>
								</select>
							</td>
							<td>
								<div class='cpt_menu_distribute' data-researchid='${manual.btn}' title='指派'></div>
								<div class='cpt_menu_view' data-url='showManualResearch.do?researchid=${manual.btn}' title='查看'></div>
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
setRemainingHeight(".distributetable","#right");
</script>
<script src="resources/js/manual/distribute.js"></script>
</html>
