$(function(){
	//删除
	$(document).on("click",".cpt_menu_del",function(){
		var okbtn = $(this);
		var tr = $(this).parent().parent();
		$.ajax({
			url : "bbscDeletePerson.aj",
			type : "post",
			dataType:'json',
			data : {
				fydm:tr.find("input[name='fydm']").val(),
				yhm:tr.find("input[name='yhm']").val()
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
	
});
