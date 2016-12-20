var optionfy = '<option value="researchfy">&nbsp;&nbsp;法&nbsp;&nbsp;院</option>';
var optionay = '<option value="researchay">&nbsp;&nbsp;案&nbsp;&nbsp;由</option>';
var optionbm = '<option value="researchbm">&nbsp;&nbsp;部&nbsp;&nbsp;门</option>';
var yjCondition;	//一级列
var colCondition;	//二级列
var bbbh = null;
$(function(){
	//初始化时间
	initDate();
	//初始化法院
	initFy();
	
	//调研范围和调研项之间的关系
	$("#xzfy").change(function (){
		var xzfydm = $("#fydm").val();
		if(xzfydm=='qsfy'||xzfydm=='yzy'||xzfydm=='ezy'||xzfydm=='bhxq'){
			if($("#bblx option[value=researchfy]").length==0){
				$("#bblx").append(optionfy);
				$("#bblx option[value=researchfy]").show();
			};
			if($("#bblx").val()=='researchbm'){
				$("#bblx").val('researchfy');
			}
			$("#bblx option[value=researchbm]").remove();
		}else{
			if($("#bblx option[value=researchbm]").length==0){
				$("#bblx").append(optionbm);
				$("#bblx option[value=researchbm]").show();
			};
			if($("#bblx").val()=='researchfy'){
				$("#bblx").val('researchbm');
			}
			$("#bblx option[value=researchfy]").remove();
		}
	});
	
	//让show-items面板的项目可以拖动
	//delay参数传递的是鼠标按住多少毫秒才开始移动
	$(function () {
	    $("#show-items").sortable({delay: 200}
	    );
	});

	$(function () {
		$("#toggle-btn").click(
			function(){
				$("#expend-list").toggle();
			}

			);
	});
	
	//二级菜单面板打开的关闭
	$(".ejl-list a").click(function(){
		$(".expand-list").hide();
		$(".expand-list[data-bbbh='"+$(this).data("bbbh")+"']").show();
		//增加按钮样式
		$(".ejl-list a").removeClass("active");
		$(this).addClass("active");
		//更改底下div的高度
		changeHeight();
	});
	
	// 二级复选框选中事件,同时把选中的内容放在面板中
	$("input[type='checkbox'][name='xxx']").click(function () {
	    if ($(this).prop("checked")) {
	         $("#show-items").append('<div class="show-item" data-bbbh="'+$(this).data("bbbh")+'" data-xxmc="'+$(this).data("xxmc")+'">'+$(this).data("xsmc")+'<span class="delete">×</span></div>');
	         $("#show-items").show();
	    }else{
	         $(".show-item[data-xxmc='"+$(this).data("xxmc")+"']").remove();
	    }
	    checkEjl();
	});
	
	//标签删除
	$(document).on("click", ".show-item .delete", function () {
	    var parentNode = $(this).parent();//parent方法是查找节点的直接父节点
	    $("input[type='checkbox'][name='xxx'][data-xxmc='"+parentNode.data("xxmc")+"']").removeAttr("checked");	//对应复选框不勾选
	    parentNode.remove();	//删除此节点
	    checkEjl();
	});
	
	//报表生成
	$("#btn_bbsc").click(function(){
		if(getFormData()){
			$.ajax({
				url : "bbscCustom.aj",
				type : "post",
				dataType:'html',
				data : {
					yjCondition:yjCondition,
					colCondition:colCondition,
					bbbh: bbbh,
					bblx:$("#bblx").val(),
					fyfw:$("#fydm").val(),
					ksrq:$("#ksrq").val(),
					jsrq:$("#jsrq").val(),
				},
				beforeSend:function(){
					showMask("数据获取中...");
				},
				success : function(html) {
					hideMask();
					$(".tableContent").html(html); //把后台生成的 HTML 添加到类为 .tableContent 的地方
				}
			});
		}
	});


	//报表生成
	$("#btn_bbsc_TB").click(function(){
		if(getFormData()){
			$.ajax({
				url : "bbscCustomSPLY.aj",//生成报表同时显示同比数据
				type : "post",
				dataType:'html',
				data : {
					yjCondition:yjCondition,
					colCondition:colCondition,
					bbbh: bbbh,
					bblx:$("#bblx").val(),
					fyfw:$("#fydm").val(),
					ksrq:$("#ksrq").val(),
					jsrq:$("#jsrq").val(),
				},
				beforeSend:function(){
					showMask("数据获取中...");
				},
				success : function(html) {
					hideMask();
					$(".tableContent").html(html); //把后台生成的 HTML 添加到类为 .tableContent 的地方
				}
			});
		}
	});


	
	//模板生成
	$("#btn_template").click(function(){
		if(getFormData()){
			$.ajax({
				url : "saveTemplateForm.aj",
				type : "post",
				dataType:'html',
				data : {},
				success : function(html) {
					$('#dlg').html(html).dialog({
						autoOpen : false,
						bgiframe : true,
						modal : true,
						resizable : false,
						dialogClass : 'dlg-wrap',
						width: 500,
						title : '模板名称',
						buttons:{
							"确定":function(){
								var form = $(".saveTemplateForm");
								$.ajax({
									url : form.attr('action'),
									type : form.attr('method'),
									dataType:'json',
									data : {
										yjCondition:yjCondition,
										colCondition:colCondition,
										bbbh: bbbh,
										bblx:$("#bblx").val(),
										fyfw:$("#fydm").val(),
										name:$("#mbmc").val()
									},
									success : function(data) {
										if(data.success==true){
											$('#dlg').dialog("close");
											alertEmpty("保存成功！");
										}else{
											$(".error-tip").html(data.text).show();
										}
									}
								});
							}
						}
					}).dialog('open');
				}
			});
		}
	});
	
});

function checkEjl(){
	var xxlist = $(".show-item");
	if(xxlist.length==0){	//删光了隐藏面板并显示案由
		$("#show-items").hide();
		if($("#bblx option[value=researchay]").length==0){
			$("#bblx").prepend(optionay);
			$("#bblx option[value=researchay]").show();
		};
	}else{
		if(canResearchAy()==true){		//可以加案由，如果没有，加上
			if($("#bblx option[value=researchay]").length==0){
				$("#bblx").prepend(optionay);
				$("#bblx option[value=researchay]").show();
			};
		}else{	//不能加案由，删掉
			if($("#bblx").val()=='researchay'){
				$("#bblx").val($("#bblx option[value=researchay]").next().val());
			}
			$("#bblx option[value=researchay]").remove();
		}
	}
	//更改底下div的高度
	changeHeight();
}

//可以检查是否能选择生成案由表，还可以生成bbbh
function canResearchAy(){
	var res = true;
	$(".show-item").each(function(){
		var xx_bbbh = $(this).data("bbbh");
		if(xx_bbbh=='qbtj'||xx_bbbh=='qttj'){
			bbbh = null;
			res = false;
			return false;
		}
		if(!bbbh){	//bbbh为空代表第一次访问，赋值即可
			bbbh = xx_bbbh;
		}else{
			if(bbbh!=xx_bbbh){	//如果有两个或以上的报表编号
				bbbh = null;
				res = false;
				return false;
			}
		}
	});
	return res;
}

function changeHeight(){
	// setRemainingHeight(".tableContent","#right",".bbscform");
	if($('#dytable').length>0){
		$('#dytable').DataTable().destroy(false);
		initDataTable();
	}
}

function getFormData(){
	//获取一级列
	yjCondition = "";
	$("input[type='checkbox'][name='yjCondition']:checked").each(function(){
		 var value=$.trim($(this).val());
		 yjCondition += $(this).data("lx") + "/" +value + "+";
	});
	if(!yjCondition){
		alertEmpty("一级列不得为空！");
		return false;
	}
	yjCondition = yjCondition.substring(0,yjCondition.length -1);
	//获取二级列
	colCondition = "";
	$(".show-item").each(function(){
		 colCondition += "("+$(this).data("xxmc")+")"+"+";
	});
	if(!colCondition){
		alertEmpty("二级列不得为空！");
		return false;
	}
	colCondition = colCondition.substring(0,colCondition.length -1);
	//获取bbbh
	canResearchAy();
	return true;
}

function initDate() {
	var now = new Date();
	var after = new Date(now.getFullYear(),now.getMonth(),now.getDate()-1);
	var end_date = after.getFullYear()+"-"+(after.getMonth()+1)+"-"+after.getDate();
	var before = new Date(now.getFullYear(),now.getMonth()-1,now.getDate()-1);
	var begin_date = before.getFullYear()+"-"+(before.getMonth()+1)+"-"+before.getDate();
	$("#sjxz-start").text(begin_date);
	$("#sjxz-end").text(end_date);
	$("#ksrq").val(begin_date);
	$("#jsrq").val(end_date);
}
function initFy() {
	$("#xzfy").val("全市法院");
	$("#fydm").val("qsfy");
	$("#bblx").empty();
	$("#bblx").append(optionay);
	$("#bblx").append(optionfy);
}