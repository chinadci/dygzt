<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib prefix="spring" uri="../lib/spring.tld"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE HTML>
<html>
<head>
<meta charset="utf-8" />
<title>调研工作台-调研表</title>
<%@include file="inc/file.inc.jsp"%>
</head>
<body>
	<div id="content">
		<%@include file="inc/header.inc.jsp"%>
		<div id="main">
			<div class="mod-content wrap3 autotable">
				<h3 class="title" style="margin-left:20px">
					<s class="file"></s>${table.bbmc} <input type="hidden" name="bbmc" id="bbmc" value="${table.bbmc }"></input>
					<span class="print-hidden btn-group">
						<input type="hidden" name="dytj" id="dytj" value="${table.dytj }"></input>
						<c:if test="${hasHistory }">
							<a class="btn_save" href="javascript:void(0)">存入历史</a>
						</c:if>
						<a class="table_print" href="javascript:void(0)">打印</a>
						<a href="tableExport.aj?dytj=${table.dytj.replace('+','%2B') }">导出</a>
					</span>
				</h3>
				<div class="line-5"></div>
				<div class="table-1" style="margin:10px 10px 20px 15px;">
					<table id="dytable" class="center">
						<c:forEach var="i" begin="0" end="${table.collevel-1 }" step="1">
								<tr>
									<c:forEach items="${table.valueList.get(i)}" var="cell">
										<th rowspan="${cell.rowspan}" colspan="${cell.colspan}">
											${cell.value}</th>
									</c:forEach>
								</tr>
						</c:forEach>

						<tbody>
							<c:forEach var="j" begin="${table.collevel }"
								end="${table.valueList.size()-1 }" step="1">
								<tr>
									<c:forEach items="${table.valueList.get(j)}" var="cell"
										varStatus="rowindex">
										<td rowspan="${cell.rowspan}" colspan="${cell.colspan}"
											<c:if test="${rowindex.index<table.rowlevel}">class="rowheader"</c:if>>
										<!-- ${cell.value}-->

											<c:choose>
												<%--如果该 cell 中的 sql 值，不为空，则把 sql 输出到 a 标签中，再赋值；否则直接赋值--%>
												<c:when test="${cell.sql!=null}">
													<a href="researchlb.do?base=${cell.base}&sql=${cell.sql}&fydm=${cell.fydm}"
													   target="_blank">
															${cell.value}
													</a>
													<label class="samePeriodLastYear" id="samePeriodLastYear">
														[${cell.samePeriodLastYearValue}]
													</label>
												</c:when>
												<c:otherwise>
													${cell.value}
													<label class="samePeriodLastYear" id="samePeriodLastYear">
															${cell.samePeriodLastYearValue}
													</label>
												</c:otherwise>
											</c:choose>

										</td>
									</c:forEach>
								</tr>
							</c:forEach>
						</tbody>
					</table>
				</div>
			</div>
		</div>
	</div>
<script type="text/javascript">
//init_body();
//setRemainingHeight(".autotable","#main");
initDataTable();
function initDataTable(){
	$('#dytable').DataTable({
		"bLengthChange": false,	//改变数据量
		"paging":         false,	//分页
		"bInfo" : false,	//页脚信息
		"bFilter": false,	//搜索
		"bSort":false,	//排序
 		"scrollY":         $(".autotable").height()-$(".table-1").position().top-parseInt($(".table-1").css("margin-bottom"))-parseInt($(".table-1").css("margin-top"))-$("#dytable thead").outerHeight(),
        "scrollX":        true,
        "scrollCollapse": true
	});
}
//打印
$(".table_print").click(function(){
	$("#dytable").DataTable().destroy(false);
	printTable($("#dytable"),$("#bbmc").val());
});
</script>
<script src="resources/js/bbsc/saveHistory.js"  type="text/javascript"></script>
</body>
</html>