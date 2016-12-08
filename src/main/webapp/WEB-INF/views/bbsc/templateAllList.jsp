<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib prefix="spring" uri="../../lib/spring.tld"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE HTML>
<html>
<head>
<meta charset="utf-8" />
<title>报表生成-${modelname}</title>
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
			<div class="mod-content wrap3 templateTable">
				<h3 class="title"><s class="file"></s>所有模板
				</h3>
				<div class="line-5"></div>
				<div class="table-2">
					<input name="ksrq" id = "ksrq" type="hidden">
					<input name="jsrq" id = "jsrq" type="hidden">
					<a class="btn2 datatablebtn" style="right:100px" href="bbscByMyTemplate.do">我的模板</a>
					<a class="btn2 active datatablebtn" href="javascript:void(0)">所有模板</a>
					<table id="table">
						<thead>
							<tr>
								<th style="width:120px">创建时间</th>
								<th style="width:100px">模板名</th>
								<th style="width:120px">一级列条件</th>
								<th style="width:120px">二级列条件</th>
								<th style="width:70px">行</th>
								<th style="width:100px">范围</th>
								<th style="width:90px">创建人</th>
								<th style="width:100px">创建人法院</th>
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
<script src="resources/js/xzsj.js"  type="text/javascript"></script>
<script src="resources/js/bbsc/template.js"></script>
<script type="text/javascript" >
//setRemainingHeight(".templateTable","#right");
//初始化table
	var tableHeight= $(".templateTable").height()-$(".table-2").position().top-parseInt($(".table-2").css("margin-top"))-parseInt($(".table-2").css("margin-bottom"))-89; //31(表头)+26(info)+32(分页)=125
	$("#table").DataTable({
		"bLengthChange": false,	//改变数据量
		"bSort":true,	//排序
		"aoColumnDefs": [ { "bSortable": false, "aTargets": [ 8 ] }],	//第一列从0开始,序号为6列的列不进行排序，别的列均可进行排序
		"order":[[0,"desc"]],	//表格初始化，第三列降序
		"bInfo" : true,	//页脚信息
		"paging":true,	//分页
		"pagingType": "full_numbers",
		//"pageLength": Math.floor(tableHeight/($("th").outerHeight()+5)),
		"pageLength":15,
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
		 "scrollY":tableHeight+12,	//表头固定
         "scrollX":true,
         "scrollCollapse": true,
		 "bDestroy" : true,		//如果存在datatable,则销毁重新初始化
		 "bProcessing" : true,	//以下为服务器分页
		 "bServerSide" : true,
		 "sAjaxSource": "${method}",
		 "aoColumns": [
		 	{ "mDataProp": "cjsj" },
		 	{ "mDataProp": "name" },
		 	{ "mDataProp": "yjCondition" },
		 	{ "mDataProp": "colCondition" },
		 	{ "mDataProp": "bblx" },
		 	{ "mDataProp": "fyfw" },
		 	{ "mDataProp": "username" },
		 	{ "mDataProp": "userfymc" },
		 	{ "mDataProp": "btn" }
		 ]
	});
</script>
</html>