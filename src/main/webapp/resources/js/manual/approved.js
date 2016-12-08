$(function(){
	//初始化时间
	initDate();
	//初始化table
	initTable();
	
	//查看
	$(document).on("click",".cpt_menu_view",function(){
		window.open($(this).data("url"));
	});
});
function initDate() {
	var now = new Date();
	var end_date = now.getFullYear()+"-"+(now.getMonth()+1)+"-"+now.getDate();
	var before = new Date(now.getFullYear(),now.getMonth()-1,now.getDate());
	var begin_date = before.getFullYear()+"-"+(before.getMonth()+1)+"-"+before.getDate();
	$("#sjxz-start").text(begin_date);
	$("#sjxz-end").text(end_date);
	$("#ksrq").val(begin_date);
	$("#jsrq").val(end_date);
}
function initTable(){
	var tableHeight= $(".approvetable").height()-$(".table-2").position().top-parseInt($(".table-2").css("margin-top"))-parseInt($(".table-2").css("margin-bottom"))-89; //31(表头)+26(info)+32(分页)=125
	$("#table").DataTable({
		"bLengthChange": false,	//改变数据量
		"bSort":true,	//排序
		"aoColumnDefs": [ { "bSortable": false, "aTargets": [ 7 ] }],	//第一列从0开始,序号为6列的列不进行排序，别的列均可进行排序
		"order":[[0,"desc"]],	//表格初始化，第一列降序
		"bInfo" : true,	//页脚信息
		"paging":true,	//分页
		"pagingType": "full_numbers",
		// "pageLength": Math.floor(tableHeight/($("th").outerHeight()+5)),
		"pageLength":15,
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
		 "scrollY":tableHeight+12,	//表头固定
         "scrollX":true,
         "scrollCollapse": true,
         "bDestroy" : true,
		 "bProcessing" : true,	//以下为服务器分页
		 "bServerSide" : true,
		 "sAjaxSource": "manualApproveList.aj?ksrq="+$("#ksrq").val()+"&jsrq="+$("#jsrq").val(),
		 "aoColumns": [
		 	{ "mDataProp": "dyrq" },
		 	{ "mDataProp": "dymd" },
		 	{ "mDataProp": "ksrq" },
		 	{ "mDataProp": "jsrq" },
		 	{ "mDataProp": "dyfw" },
		 	{ "mDataProp": "dyxq" },
		 	{ "mDataProp": "dyzt" },
		 	{ "mDataProp": "btn" }
		 ]
	});
	
}