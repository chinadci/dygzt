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
				<form action="/showAutoSearchTable.aj" method="post" class="searchform form1">
					<div>
						<label class="col10">统计法院:</label>
						<select id="dyfy" name="dyfy" class="col23">
							<option value=""></option>
						<c:forEach items="${fylistHxq }" var="fyHxq">
							<option value="${fyHxq.bh }">${fyHxq.ms}</option>
						</c:forEach>
						</select>
						
						<label for="bblx" class="col10">统计项:</label>
						<select id="bblx" name="bblx" class="col8">
							<option value=""></option>
							<option value="researchfy">&nbsp;&nbsp;法&nbsp;&nbsp;院</option>
							<option value="researchay">&nbsp;&nbsp;案&nbsp;&nbsp;由</option>
							<option value="researchbm">&nbsp;&nbsp;部&nbsp;&nbsp;门</option>
						</select>
						
						<%--<label for="ajzt" class="col10">案件状态:</label>--%>
						<%--<select id="ajzt" name="ajzt" class="col8 condition">--%>
							<%--<option value=""></option>--%>
							<%--<option value="新收">&nbsp;&nbsp;新&nbsp;&nbsp;收</option>--%>
							<%--<option value="未结">&nbsp;&nbsp;未&nbsp;&nbsp;结</option>--%>
							<%--<option value="已结">&nbsp;&nbsp;已&nbsp;&nbsp;结</option>--%>
						<%--</select>--%>

						<%----%>
						<label class="col10">统计人:</label>
						<input name="dyr" id = "dyr" type="text" class="col10"/>
					</div>
					<div>
					    <label class="col10">统计人法院:</label>
						<select id="dyrfydm" name="dyrfydm" class="col23">
							<option value=""></option>
						<c:forEach items="${fylist }" var="fy">
							<option value="${fy.bh }">${fy.ms}</option>
						</c:forEach>
						</select>
						
						<span class="sjxz">
							<label class="col10">统计时间:</label>
							<span id="sjxz-start"></span>
							<input name="ksrq" id = "ksrq" type="hidden"/>
							至
							<span id="sjxz-end"></span>
							<input name="jsrq" id = "jsrq" type="hidden"/>
							<a class="btn2" href="javascript:void(0)">时间选择</a>
						</span>
						<span class="formButGroup">
							<a id="btn_search" class="btn1" href="javascript:void(0)">查询</a>
		   					<a id="btn_reset" class="btn1" href="javascript:void(0)">重置</a>
						</span>
					</div>
				</form>
			</div>
			<div class="mod-content wrap3 searchTable">
				<div class="table-2" id="minTableContent">
					<table id="table">
						<thead>
							<tr>
								<th style="width:80px">统计日期</th>
								<th style="width:120px">表格名称</th>
								<th style="width:100px">表格开始日期</th>
								<th style="width:100px">表格结束日期</th>
								<%--<th style="width:70px">案件状态</th>--%>
								<th style="width:70px">统计人</th>
								<th style="width:120px">统计人法院</th>
								<th style="width:70px">操作</th>
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
<script src="resources/js/bbsc/bbscAutoSearch.js"></script>
</html>