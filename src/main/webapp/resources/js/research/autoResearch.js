var optionfy = '<option value="researchfy">&nbsp;&nbsp;法&nbsp;&nbsp;院</option>';
var optionay = '<option value="researchay">&nbsp;&nbsp;案&nbsp;&nbsp;由</option>';
var optionbm = '<option value="researchbm">&nbsp;&nbsp;部&nbsp;&nbsp;门</option>';
$(function(){
	//初始化时间
	initDate();
	//初始化法院
	initFy();
	//重置
	$("#btn_reset").click(function(){
		reset(".researchform");
		initDate();
		initFy();
	});
	
	$("#btn_research").click(function(){
		var form = $(".researchform");
		var condition = "";
		$(".condition").each(function(){
			var ele = $(this);
			if(ele.val()&&$.trim(ele.val())){
				condition += ele.attr("name")+":"+$.trim(ele.val())+";";
			}
		});
		if(condition){
			condition = condition.substring(0, condition.length-1);
		}
		$.ajax({
			url : form.attr('action'),
			type : form.attr('method'),
			dataType:'html',
			data : form.serialize()+"&condition="+condition,
			beforeSend:function(){
				showMask("数据获取中，请稍等...");
			},
			success : function(html) {
				hideMask();
				$(".tableContent").html(html);
			}
		});
	});
	
	$("#btn_save").click(function(){
		if($.trim($(".tableContent").html())){
			var dytj = $("#dytj").val();
			var dytjlist = dytj.split(';');
			var defaultBgms = '';
			for(tj in dytjlist){
				var couple = dytjlist[tj].split(':');
				if(couple[0]=='bblx'){
					defaultBgms+="调研项：";
					if(couple[1].indexOf('researchfy')==0){
						defaultBgms+="法院 ";
					}else if(couple[1].indexOf('researchay')==0){
						defaultBgms+="案由 ";
					}else if(couple[1].indexOf('researchbm')==0){
						defaultBgms+="部门 ";
					}
				}else if(couple[0]=='ajzt'){
					defaultBgms+="案件状态："+couple[1]+" ";
				}
			}
			$.ajax({
				url : "saveForm.aj",
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
						title : '报表描述',
						buttons:{
							"确定":function(){
								var form = $(".saveform");
								$.ajax({
								url : form.attr('action'),
								type : form.attr('method'),
								dataType:'json',
								data : form.serialize()+"&dytj="+dytj+"&type=1",
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
					$("#bgms").val(defaultBgms);
				}
			});
		}else{
			alertEmpty("无已生成报表!");
		}
	});
	
	//调研范围和调研项之间的关系
	$("#xzfy").change(function (){
		var xzfydm = $("#fydm").val();
		if($("#bbbh").val()!='qttj'){
			if(xzfydm=='qsfy'||xzfydm=='yzy'||xzfydm=='ezy'||xzfydm=='bhxq'){
				if($("#bblx option[value=researchfy]").length==0){
					$("#bblx").prepend(optionfy);
					$("#bblx option[value=researchfy]").show();
				};
				if($("#bblx").val()=='researchbm'){
					$("#bblx").val('researchfy');
				}
				$("#bblx option[value=researchbm]").remove();
				if($("#bblx option[value=researchay]").length==0){
					$("#bblx").prepend(optionay);
					$("#bblx option[value=researchay]").show();
				};
			}else{
				if($("#bblx option[value=researchbm]").length==0){
					$("#bblx").append(optionbm);
					$("#bblx option[value=researchbm]").show();
				};
				if($("#bblx").val()=='researchfy'){
					$("#bblx").val('researchay');
				}
				$("#bblx option[value=researchfy]").remove();
				if($("#bblx option[value=researchay]").length==0){
					$("#bblx").append(optionay);
					$("#bblx option[value=researchay]").show();
				};
			}
		}else{
			if(xzfydm=='qsfy'||xzfydm=='yzy'||xzfydm=='ezy'||xzfydm=='bhxq'){
				$("#bblx").html(optionfy);
				$("#bblx").val('researchfy');
			}else{
				$("#bblx").html(optionbm);
				$("#bblx").val('researchbm');
			}
		}
	});
});
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
	$("#bblx").append(optionfy);
	if($("#bbbh").val()!='qttj'){
		$("#bblx").append(optionay);
	}
}