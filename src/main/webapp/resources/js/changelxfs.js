$(function(){
	//修改联系方式
	$(".changelxfsbtn").click(function(){
		$.ajax({
			url : "changeLxfsForm.aj",
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
					title : '修改联系方式',
					buttons:{
						"确定":function(){
							var form = $(".lxfsform");
							$.ajax({
							url : form.attr('action'),
							type : form.attr('method'),
							dataType:'json',
							data : form.serialize(),
							success : function(data) {
								if(data.success==true){
									$('#dlg').dialog("close");
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