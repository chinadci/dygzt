$(function(){
	//自定义生成时保存
	$("#btn_save").click(function(){
		if($.trim($(".tableContent").html())){
			saveTable();
		}else{
			alertEmpty("无已生成报表!");
		}
	});
	
	//模板生成时保存
	$(".btn_save").click(function(){
		saveTable();
	});
	
});
function saveTable(){
	var dytj = $("#dytj").val();
	var dytjlist = dytj.split(';');
	var defaultBgms = '';
	for(tj in dytjlist){
		var couple = dytjlist[tj].split(':');
		if(couple[0]=='bblx'){
			defaultBgms+="行：";
			if(couple[1].indexOf('researchfy')==0){
				defaultBgms+="法院 ";
			}else if(couple[1].indexOf('researchay')==0){
				defaultBgms+="案由 ";
			}else if(couple[1].indexOf('researchbm')==0){
				defaultBgms+="部门 ";
			}
			defaultBgms+="\r\n";
		}else if(couple[0]=='yjCondition'){
			var yjls = couple[1].split('\+');
			var yjlExpr = "";
			for(yjl in yjls){
				var yjl_key_value = yjls[yjl].split('/');
				yjlExpr +="("+yjl_key_value[1]+")+";
			}
			if(yjlExpr){
				yjlExpr = yjlExpr.substring(0, yjlExpr.length-1);
			}
			defaultBgms+="一级列："+yjlExpr+" ";
			defaultBgms+="\r\n";
		}else if(couple[0]=='colCondition'){
			defaultBgms+="二级列："+couple[1]+" ";
			defaultBgms+="\r\n";
		}
	}
	if(defaultBgms){
		defaultBgms = defaultBgms.substring(0, defaultBgms.length-2);
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
						data : {
							bgms:$("#bgms").val(),
							dytj:dytj,
							type:2
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
			$("#bgms").val(defaultBgms);
		}
	});
}