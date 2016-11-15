$(function(){
	//修改联系方式
	$(".changemmbtn").click(function(){
		$.ajax({
			url : "changePasswordForm.aj",
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
					width: 400,
					title : '修改密码',
					buttons:{
						"确定":function(){
							var oldpass = $("#oldpass").val();
							var newpass = $("#newpass").val();
							var newpass1 = $("#newpass1").val();
							if((!oldpass)||(!newpass)||(!newpass1)){
								$(".error-tip").html("密码不得为空").show();
								return;
							}
							if(newpass!=newpass1){
								$(".error-tip").html("两次密码不相同").show();
								return;
							}
							var form = $(".passwordform");
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