<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib prefix="spring" uri="../lib/spring.tld"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE HTML>
<html>
<head>
<meta charset="utf-8" />
<title>调研工作台-人工调研查询</title>
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
			<div class="mod-content wrap3 autoform">
				<form action="/showManualSearchTable.aj" method="post" class="manualform form1">
					<div>
						<label for="dyzt" class="col10">调研状态:</label>
						<select id="dyzt" name="dyzt" class="col11 condition">
							<option value=""></option>
							<option value="待审批">待审批</option>
							<option value="审批不通过">审批不通过</option>
							<option value="待指派">待指派</option>
							<option value="待计算">待计算</option>
							<option value="调研完成">调研完成</option>
						</select>
						
						<label class="col10">调研法院:</label>
						<select id="dyfy" name="dyfy" class="col23">
							<option value=""></option>
						<c:forEach items="${fylistHxq }" var="fyHxq">
							<option value="${fyHxq.ms }">${fyHxq.ms}</option>
						</c:forEach>
						</select>
						
						<span class="sjxz">
							<label class="col10">调研时间:</label>
							<span id="sjxz-start"></span>
							<input name="ksrq" id = "ksrq" type="hidden"/>
							至
							<span id="sjxz-end"></span>
							<input name="jsrq" id = "jsrq" type="hidden"/>
							<a class="btn2" href="javascript:void(0)">时间选择</a>
						</span>
					</div>
					<div>
						<label class="col10">申请人:</label>
						<input name="dyr" id = "dyr" type="text" class="col10"/>
						
						<label class="col10" style="margin-left:6px">申请人法院:</label>
						<select id="dyrfydm" name="dyrfydm" class="col20">
							<option value=""></option>
						<c:forEach items="${fylist }" var="fy">
							<option value="${fy.bh }">${fy.ms}</option>
						</c:forEach>
						</select>
						
						<label class="col10">案件类型:</label>
						<input name="ajlx" id = "ajlx" type="text" class="col20"/>
					</div>
					
					<div>
					    <label class="col10">审批人:</label>
						<input name="spr" id = "spr" type="text" class="col10"/>
						
						<label class="col10" style="margin-left:6px">审批人法院:</label>
						<select id="sprfydm" name="sprfydm" class="col20">
							<option value=""></option>
						<c:forEach items="${fylist }" var="fy">
							<option value="${fy.bh }">${fy.ms}</option>
						</c:forEach>
						</select>
						
						<label class="col10">计算人:</label>
						<input name="jsr" id = "jsr" type="text" class="col10"/>
						
						<span class="formButGroup">
							<a id="btn_search" class="btn1" href="javascript:void(0)">查询</a>
		   					<a id="btn_reset" class="btn1" href="javascript:void(0)">重置</a>
						</span>
					</div>
				</form>
			</div>
			<div class="mod-content wrap3 searchTable" id="minTableContent">
				<div class="table-2">
					<table id="table">
						<thead>
							<tr>
								<th style="width:80px">调研日期</th>
								<th style="width:200px">调研目的</th>
								<th style="width:110px">表格开始日期</th>
								<th style="width:110px">表格结束日期</th>
								<th style="width:100px">调研范围</th>
								<th style="width:30%">调研需求</th>
								<th style="width:80px">调研状态</th>
								<th style="width:60px">操作</th>
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
//setRemainingHeight(".searchTable","#right",".autoform");
</script>
<script src="resources/js/xzsj.js"  type="text/javascript"></script>
<script src="resources/js/manual/manualSearch.js"></script>
</html>
