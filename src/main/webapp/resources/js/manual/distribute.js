$(function(){
	var tableHeight = $(".distributetable").height()-parseInt($(".table-2").css("margin-top"))-parseInt($(".table-2").css("margin-bottom"))-125;//36(搜索框)+31(表头)+26(info)+32(分页)=125
	$("#table").DataTable({
		"bLengthChange": false,	//改变数据量
		"bSort":true,	//排序
		"order": [[ 0, "desc" ]],//跟数组下标一样，第一列从0开始，这里表格初始化时，第四列默认降序
		"aoColumnDefs": [ { "bSortable": false, "aTargets": [ 3 ] },{ "bSortable": false, "aTargets": [ 4 ] }],	//第一列从0开始,序号为6列的列不进行排序，别的列均可进行排序
		"bInfo" : true,	//页脚信息
		"paging":true,	//分页
		"pagingType": "full_numbers",
		"pageLength": Math.floor(tableHeight/($("th").outerHeight()+5)),
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
	});
	
	//查看
	$(document).on("click",".cpt_menu_view",function(){
		window.open($(this).data("url"));
	});
	
	
	//指派
	$(document).on("click",".cpt_menu_distribute",function(){
		var td = $(this).parent();
		var btns = td.html();
		td.html(waitImg);
		var sel = td.parent().find("select[name='jsr']");
		var op = sel.find("option:selected");
		$.ajax({
			url : "distribute.aj",
			type : "post",
			dataType:'json',
			data : {
				researchid:$(this).data("researchid"),
				jsr:op.data("name"),
				jsrfydm:op.data("fydm")
			},
			success : function(data) {
				if(data.success==true){
					td.html(data.text);
					sel.attr("disabled","disabled");
				}else{
					td.html(btns);
					alertEmpty(data.text);
				}
			}
		});
	});
	
});