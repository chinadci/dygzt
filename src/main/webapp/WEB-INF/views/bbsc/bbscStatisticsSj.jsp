<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib prefix="spring" uri="../../lib/spring.tld"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE HTML>
<html>
<head>
	<meta charset="utf-8" />
	<title>报表生成-自定义报表</title>
	<%@include file="../inc/file.inc.jsp"%>
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
				<form action="/showAutoSearchTable.aj" method="post" class="researchform form1">
					<div>
						<label for="sjhf" class="col10">时间划分:</label>
						<input type="radio" name='sjhf' value="year"/>年
						<input type="radio" name='sjhf' value="month" checked/>月
						<input type="radio" name='sjhf' value="date"/>日
						<span class="sjxz">
							<label class="col10">时间范围:</label>
							<span id="sjxz-start"></span>
							<input name="ksrq" id = "ksrq" type="hidden"/>
							至
							<span id="sjxz-end"></span>
							<input name="jsrq" id = "jsrq" type="hidden"/>
							<a class="btn2" href="javascript:void(0)">时间选择</a>
						</span>
					</div>
				</form>
			</div>
			<div class="mod-content wrap3 tableContent">
				<div id="image" class="chartImageDiv"></div>
			</div>
		</div>
	</div>
</div>	
</body>
<script src="resources/js/jquery/highcharts.js"></script>
<script src="resources/js/jquery/highcharts-3d.js"></script>
<script src="resources/js/bbsc/bbscStatisticSj.js"></script>
<script type="text/javascript" >
//setRemainingHeight(".tableContent","#right",".autoform");
</script>
<script src="resources/js/xzsj.js"  type="text/javascript"></script>
</html>
