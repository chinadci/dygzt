$(function(){
	//删除
	$(document).on("click",".cpt_menu_del",function(){
		var okbtn = $(this);
		var tr = $(this).parent().parent();
		$.ajax({
			url : "deletePerson.aj",
			type : "post",
			dataType:'json',
			data : {
				fydm:tr.find("input[name='fydm']").val(),
				yhm:tr.find("input[name='yhm']").val()
			},
			success : function(data) {
				if(data.success==true){ /*以前面的dataTpye 说明了要返回的数据是 jason ，所以这里的 data 是jason 格式数据，success 后台传过来的一个 jason 属性，可以直接这样调用*/
					tr.find('[data-edit="true"]').attr('disabled','disabled');
					okbtn.parent().html(data.text);
				}else{
					alertEmpty(data.text);
				}
			}
		});
	});
	
	//修改
	$(document).on("click",".cpt_menu_update",function(){
		$(".cpt_menu_save").trigger("click");	//保存其他正在修改的
		$(this).removeClass('cpt_menu_update').addClass('cpt_menu_save').attr('title','保存');	//更换图标
		$(this).parent().parent().find('[data-edit="true"]').removeAttr('disabled');	//改为可修改的
	});
	
	//保存
	$(document).on("click",".cpt_menu_save",function(){
		var tr = $(this).parent().parent();
		var btn = $(this);
		var jsonStr = '{';
		//获取权限
		var qx = "";
		tr.find("input[type='checkbox'][name='qx']:checked").each(function(){
			 var value=$.trim($(this).val());
			 qx += value + ";";
		});
		if(!qx){
			alertEmpty("权限不得为空！");
			return;
		}
		qx = qx.substring(0,qx.length -1);
		jsonStr += '"qx":"'+qx+'",';
		//其他
		tr.find("input[type='text']").each(function(){
			 var name=$.trim($(this).val());
			 if(name != ""){
				 jsonStr += '"'+$(this).attr("name")+'":"'+name+'",';
			 }
		});
		tr.find("input[type='hidden']").each(function(){
			 var name=$.trim($(this).val());
			 if(name != ""){
				 jsonStr += '"'+$(this).attr("name")+'":"'+name+'",';
			 }
		});
		jsonStr = jsonStr.substring(0,jsonStr.length -1);
		jsonStr += '}';
		var json = JSON.parse(jsonStr);
		$.ajax({
			 url:"changePerson.aj",
			 type:"POST",
			 dataType:"json",
			 data:json,
			 success:function(data){
			    if(data.success==true){
			    	btn.removeClass('cpt_menu_save').addClass('cpt_menu_update').attr('title','修改');
					tr.find('[data-edit="true"]').attr('disabled','disabled');
				}else{
					alertEmpty(data.text);
				}
			 }
		 });
	});
});
