<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib prefix="spring" uri="../lib/spring.tld"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE HTML>
<html>
<head>
<meta charset="utf-8" />
<title>调研工作台-案件列表</title>
<%@include file="inc/file.inc.jsp"%>
</head>
<body>
<div id="content">
	<%@include file="inc/header.inc.jsp"%>
		<div id="main">
			<div class="mod-content wrap3 autotable">
			 	<h3 class="title" style="margin-left:20px"><s class="file"></s>案件列表
			 	</h3>
				<div class="line-5"></div>
				<div class="table-4">
					<table id="ajtable">
						<thead>
							<tr>
								<th width="45px">序号</th>
								<th width="260px">案号</th>
								<th width="60%">案件名称</th>
								<th width="100px">立案日期</th>
								<th width="100px">结案日期</th>
							</tr>
						</thead>
						<tbody>
						</tbody>
					</table>
				</div>
			</div>
		</div>
	</div>	
	
<script type="text/javascript">
	init_body();
	setRemainingHeight(".autotable","#main");
	var tableHeight = $(".autotable").height()-$(".table-4").position().top-parseInt($(".table-4").css("margin-top"))-parseInt($(".table-4").css("margin-bottom"))-130;//39(搜索框)+31(表头)+26(info)+32(分页)=125
	$("#ajtable").DataTable({
		"bLengthChange": false,	//改变数据量
		"bSort":true,	//排序
		"bInfo" : true,	//页脚信息
		"paging":true,	//分页
		"pagingType": "full_numbers",
		"pageLength": Math.floor(tableHeight/$("th").outerHeight()), 
		"bFilter": true,	//搜索
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
		 "scrollY":tableHeight+$("th").outerHeight(),	//表头固定
         "scrollX":true,
         "scrollCollapse": true,
         "bDestroy" : true,
		 "bProcessing" : true,	//以下为服务器分页
		 "bServerSide" : true,
		 "sAjaxSource": "researchlbpage.aj?base=${base}&sql=${sql}&fydm=${fydm}",
		 "aoColumns": [
		 	{ "mDataProp": "xh" },
		 	{ "mDataProp": "ahstr" },
		 	{ "mDataProp": "ajmc" },
		 	{ "mDataProp": "larq" },
		 	{ "mDataProp": "jarq" },
		 ]
	});
</script>
</body>
</html>
