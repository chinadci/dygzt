<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib prefix="spring" uri="../lib/spring.tld"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE HTML>
<html>
<head>
<meta charset="utf-8" />
<title>调研工作台-自助调研列表</title>
<%@include file="inc/file.inc.jsp"%>
</head>
<body>
	<div id="content">
		<%@include file="inc/header.inc.jsp"%>
		<div id="main">
			<div class="mod-content wrap3 autotable">
				<h3 class="title" style="margin-left:20px">
					<s class="file"></s>自助调研列表
				</h3>
				<div class="line-5"></div>
				<div class="table-4">
					<table id="table">
						<thead>
							<tr>
								<th style="width:100px">调研日期</th>
								<th style="width:120px">表格名称</th>
								<th style="width:120px">表格开始日期</th>
								<th style="width:120px">表格结束日期</th>
								<th style="width:80px">案件状态</th>
								<th style="width:80px">调研人</th>
								<th style="width:140px">调研人法院</th>
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
</body>
<script type="text/javascript" src="resources/js/research/autoStatisticTable.js"></script>
<script type="text/javascript">
	init_body();
	setRemainingHeight(".autotable","#main");
	var tableHeight = $(".autotable").height()-$(".table-4").position().top-parseInt($(".table-4").css("margin-top"))-parseInt($(".table-4").css("margin-bottom"))-84;//26(表头)+26(info)+32(分页)=84
	$("#table").DataTable({
		"bLengthChange": false,	//改变数据量
		"bSort":true,	//排序
		"aoColumnDefs": [ { "bSortable": false, "aTargets": [ 7 ] }],	//第一列从0开始,序号为6列的列不进行排序，别的列均可进行排序
		"bInfo" : true,	//页脚信息
		"paging":true,	//分页
		"pagingType": "full_numbers",
		"pageLength": Math.floor(tableHeight/($("th").outerHeight()+5)), 
		"bFilter": false,	//搜索
		"language": {
			 "emptyTable": "无",
			 "info": "共_TOTAL_条记录，当前第_PAGE_页，共_PAGES_页",
			 "infoFiltered": "",
			 "infoEmpty": "共0条记录",
			 "loadingRecords": "正在加载，请稍候",
			 "processing": "正在加载，请稍候",
			 "paginate": {
			 	"first": "首页",
			 	"last": "尾页",
			 	"next": "下一页",
			 	"previous": "上一页",
			 },
			 "search": "搜索:",
			 "searchPlaceholder": "请输入搜索条件",
			 "zeroRecords": "无匹配",
		 },		
		 "scrollY":tableHeight,	//表头固定
         "scrollX":true,
         "scrollCollapse": true,
		 "bDestroy" : true,		//如果存在datatable,则销毁重新初始化
		 "bProcessing" : true,	//以下为服务器分页
		 "bServerSide" : true,
		 "sAjaxSource": "autoSearchList.aj?${ condition }",
		 "aoColumns": [
		 	{ "mDataProp": "dyrq" },
		 	{ "mDataProp": "bgmc" },
		 	{ "mDataProp": "ksrq" },
		 	{ "mDataProp": "jsrq" },
		 	{ "mDataProp": "ajzt" },
		 	{ "mDataProp": "dyr" },
		 	{ "mDataProp": "dyrfydm" },
		 	{ "mDataProp": "btn" }
		 ]
	});
</script>
</html>
