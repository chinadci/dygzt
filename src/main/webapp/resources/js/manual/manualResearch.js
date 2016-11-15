$(function(){
	//初始化时间
	initDate();
	
	//时间类型中的其他
	$("#qt").on("focus",function(){
		$("#sjlx_qt").attr("checked",true);
	});
	$("#qt").on("change",function(){
		$("#sjlx_qt").val($("#qt").val());
	});
	
	//提交
	$("#btn_manual").click(function(){
		$(".manualform .error-warn").hide();
		if((!$.trim($("input[name='sjlx']:checked").val()))||(!$.trim($("#ajlx").val()))||
				(!$.trim($("#dymd").val()))||(!$.trim($("#dyxq").val()))){
			$(".manualform .error-warn").html("必填项不得为空").show();
			return;
		}
		//得到fileid
		var file = new Array();
		$(".manualform input[type='file']").each(function(index) {
			file[index] = $(this).attr("id");
		});
		showMask("上传中，请稍候……");
		$.ajaxFileUpload({
			secureuri : false,
			url : "manualRequest.aj",
			dataType : "json",
			data : {
				ksrq:$("#ksrq").val(),
				jsrq:$("#jsrq").val(),
				sjlx:$("input[name='sjlx']:checked").val(),
				fyfw:$("#xzfy").val(),
				ajlx:$("#ajlx").val(),
				dymd:$("#dymd").val(),
				dyxq:$("#dyxq").val(),
				fjname:$("#fj").val(),
				dyrphone:$("#phone").val(),
				dyrlxfs:$("#lxfs").val()
			},
			fileElementId : file,// file标签的id
			success : function(data,status) {
				hideMask();
				alertEmpty(data.text);
			}
		});		
	});
	
});
function initDate() {
	var now = new Date();
	var end_date = now.getFullYear()+"-"+(now.getMonth()+1)+"-"+now.getDate();
	var before = new Date(now.getFullYear()-1,now.getMonth(),now.getDate());
	var begin_date = before.getFullYear()+"-"+(before.getMonth()+1)+"-"+before.getDate();
	$("#sjxz-start").text(begin_date);
	$("#sjxz-end").text(end_date);
	$("#ksrq").val(begin_date);
	$("#jsrq").val(end_date);
}