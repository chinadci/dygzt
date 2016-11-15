var template_id;
$(function(){
	
	//生成报表
	$(document).on("click",".bbsc_btn",function(){
		template_id = $(this).data("id");
	});
	
	//删除
	$(document).on("click",".cpt_menu_del",function(){
		var okbtn = $(this);
		var tr = $(this).parent().parent();
		$.ajax({
			url : "deleteTemplate.aj",
			type : "post",
			dataType:'json',
			data : {
				id:tr.find("input[name='id']").val(),
			},
			success : function(data) {
				if(data.success==true){
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
		$.ajax({
			 url:"changeTemplate.aj",
			 type:"POST",
			 dataType:"json",
			 data:{
					id:tr.find("input[name='id']").val(),
					name:tr.find("input[name='name']").val()
				},
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

//选择时间后生成报表
function initTable(){
	window.open("bbscTemplate.do?id="+template_id+"&ksrq="+$("#ksrq").val()+"&jsrq="+$("#jsrq").val());
}
