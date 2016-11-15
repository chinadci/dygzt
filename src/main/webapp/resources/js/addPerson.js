$(function(){
	//增加法院人员
	$("#btn_add").click(function(){
		$(".addPersonform .error-warn").hide();
		if(!$("#xzfy").val()){
			$(".addPersonform .error-warn").html("法院不得为空").show();
			return;
		}
		if(!$("#name").val()){
			$(".addPersonform .error-warn").html("用户名不得为空").show();
			return;
		}
		//获取权限
		var qx = "";
		$("input[type='checkbox'][name='qxxq']:checked").each(function(){
			 var value=$.trim($(this).val());
			 qx += value + ";";
		});
		if(!qx){
			$(".addPersonform .error-warn").html("权限不得为空").show();
			return;
		}
		qx = qx.substring(0,qx.length -1);
		var form = $(".addPersonform");
		$.ajax({
			url : form.attr('action'),
			type : form.attr('method'),
			dataType:'json',
			data : form.serialize()+"&qx="+qx,
			success : function(json) {
				if(json.success==true){
					alertEmpty(json.text);
				}else{
					$(".addPersonform .error-warn").html(json.text).show();
				}
			}
		});
	});
	
});