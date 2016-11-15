$(function(){
	//定位
	$("#menu>ul>li").click(function(){
		window.location.hash=$(this).data("url");
	});
	
	//下载
	$(document).on("click",".cpt_menu_download",function(){
		location.href = $(this).data("url");
	});
	
	//通过
	$(document).on("click",".cpt_ok",function(){
		showMask("审批中，请稍候……");
		$.ajax({
			url : "approve.aj",
			type : "post",
			dataType:'json',
			data : {
				researchid:$(this).data("researchid")
			},
			success : function(data) {
				hideMask();
				if(data.success==true){
					location.reload();
				}else{
					alertEmpty(data.text);
				}
			}
		});
	});
	
	//拒绝
	$(document).on("click",".cpt_no",function(){
		var $btn = $(this);
		$.ajax({
			url : "rejectReason.aj",
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
					title : '退回原因',
					buttons:{
						"确定":function(){
							showMask("审批中，请稍候……");
							var form = $(".reasonform");
							$.ajax({
							url : form.attr('action'),
							type : form.attr('method'),
							dataType:'json',
							data : form.serialize()+"&researchid="+$btn.data("researchid"),
							success : function(data) {
								hideMask();
								if(data.success==true){
									$('#dlg').dialog("close");
									location.reload();
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
	});
	
	//上传
	$(document).on("click",".cpt_menu_upload",function(){
		var $btn = $(this);
		$.ajax({
			url : "computeResult.aj",
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
					title : '调研结果',
					buttons:{
						"确定":function(){
							//检查
							var dyjg =$("#dyjg").val(),dydm = $("#dydm").val();
							if((!dyjg)||(!dydm)){
								$(".error-tip").html("上传文件不能为空！").show();
								return;
							}
							//得到fileid
							var file = new Array();
							$(".resultform input[type='file']").each(function(index) {
								file[index] = $(this).attr("id");
							});
							showMask("上传中，请稍候……");
							$.ajaxFileUpload({
								secureuri : false,
								url : "compute.aj",
								dataType : "json",
								data : {
									"researchid":$btn.data("researchid"),
									"dyjg":dyjg,
									"dydm":dydm,
								},
								fileElementId : file,// file标签的id
								success : function(data,status) {
									hideMask();
									if(data.success==true){
										$('#dlg').dialog("close");
										location.reload();
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
	});
});