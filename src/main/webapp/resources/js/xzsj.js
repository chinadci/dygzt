$(function(){
	var sjlist;
	$('body').append('<div id="xzsjLB"></div');
	var $sjfw_dlg = $('#xzsjLB').dialog({
		title : "时间范围选择",
		autoOpen : false,
		resizable : false,
		modal : true,
		position:["center","center"],
		width: 680,
		dialogClass : 'dlg-wrap',
		buttons:{
			"查看":function(){
				$("#J_SJTab select:visible").trigger("change");
				//时间格式验证;
				var tStart = $("#startDate").val().replace(/-/g,"/"),
					tEnd = $("#endDate").val().replace(/-/g,"/");
				if(!tStart.match(/^[0-9]{4}\/[0-9]{1,2}\/[0-9]{1,2}$/) || 
					!tEnd.match(/^[0-9]{4}\/[0-9]{1,2}\/[0-9]{1,2}$/) || 
					!Date.parse(tStart) || !Date.parse(tEnd)){
					$("#xzsjLB .error-tip").text("时间格式不正确").show();
					return;
				}
				var now = new Date();
				var nowDate = now.getFullYear()+"/"+(now.getMonth()+1)+"/"+now.getDate();
				//超出当前时间, 给出提示
				if(Date.parse(tEnd) > Date.parse(nowDate)||Date.parse(tStart) > Date.parse(tEnd)){
					$("#xzsjLB .error-tip").text("当前时间不可用").show();
					return;
				}
				$("#xzsjLB .error-tip").hide();
				$("#sjxz-start").text($("#startDate").val());
				$("#sjxz-end").text($("#endDate").val());
				$("#ksrq").val($("#startDate").val());
				$("#jsrq").val($("#endDate").val());
				$sjfw_dlg.dialog("close");
				//刷新table
				if(typeof(initTable)!='undefined'){
					initTable();
				}
			}
		}
	});
	/*时间选择*/
	$(document).on("click",".sjxz a,#sjxz a",function(){
	if(!sjlist){
		$.ajax({
			url : "xzsj.aj",
			dataType : "html",
			type : "get",
			success : function(html) {
				sjlist = html;
				$sjfw_dlg.html(html).dialog("open");
				$('#sl-ck-qj1').val($("#sjxz-start").text());
				$('#sl-ck-qj2').val($("#sjxz-end").text());
				
				$sjfw_dlg.find("ul>li").on("click",function(){
					var $id=$(this).data("id");
					switch($id){
					case "sjf-n":
						$('#sl-ck-n2').trigger("change");
						break;
					case "sjf-jd":
						$('#sl-ck-jd2').trigger("change");
						break;
					case "sjf-zdy":
						$('#sl-ck-zdy2').trigger("change");
						break;
					case "sjf-qj":
						$('#sl-ck-qj2').trigger("change");
						break;
					default:
							break;
					}
				});
				
				//初始化tab
				$sjfw_dlg.find("#J_SJTab").tabs();
				$sjfw_dlg.find("ul.ui-tabs-nav li :visible").eq(0).trigger("click");
				//初始化click
				$('.datepicker').datepicker({
					monthChange:true,
					yearChange:true
				});
				
				//时间改变
				$('#sl-ck-n1,#sl-ck-n2').on('change',function(){
					var year = $('#sl-ck-n1').val();
					var range = $('#sl-ck-n2').val();
					if(range != ""){
						var start= "1990-1-1",end = "1990-12-31";
						var now = new Date();
						var nowYear = now.getFullYear();
						var nowDate = now.getFullYear()+"-"+(now.getMonth()+1)+"-"+now.getDate();
						var middle = year+"-6-30";
						if (range == 1) {	//上半年
							start = year+"-1-1";
							if(year==nowYear&&Date.parse(nowDate.replace(/-/g,"/")) <= Date.parse(middle.replace(/-/g,"/"))){
								end = nowDate;
							}else{
								end = year+"-6-30";
							}
						}else if(range == 2){	//下半年
							start = year+"-7-1";
							if(year==nowYear&&Date.parse(nowDate.replace(/-/g,"/")) >= Date.parse(middle.replace(/-/g,"/"))){
								end = nowDate;
							}else{
								end = year+"-12-31";
							}
						}else if(range == 3) {	//全年
							start = year+"-1-1";
							if(year==nowYear){	//如果是今年
								end = nowDate;
							}else{
								end = year+"-12-31";
							}
						}
						$("#startDate").val(start);
						$("#endDate").val(end);
					}
				});

				$('#sl-ck-jd1,#sl-ck-jd2').on('change',function(){
					var year = $('#sl-ck-jd1').val();
					var range = $('#sl-ck-jd2').val();
					if(range!=""){
						var start= "1990-1-1",end = "1990-3-1";
						if (range == 1) {//季度
							start = year+"-1-1";
							end = year+"-3-31";
						}else if(range == 2 || range == 3){//2 3 季度
							start = year+"-"+((range-1)*3+1)+"-1";
							end = year+"-"+range*3+"-30";
						}else {//第四季度
							start = year+"-10-1";
							end = year+"-12-31";
						}
						$("#startDate").val(start);
						$("#endDate").val(end);
					}
				});

				$('#sl-ck-zdy1,#sl-ck-zdy2').on('change',function(){
					var year = $('#sl-ck-zdy1').val();
					var range = $('#sl-ck-zdy2').val();
					var start= "1990-1-1",end = "1990-1-31";
					if(range!= ""){
						if (range == 1 ||range == 3||range == 5||range == 7||range == 8||range == 10||range == 12) {
							start = year+"-"+range+"-1";
							end = year+"-"+range+"-31";
						}else if(range == 2){
							start = year+"-2-1";
							//处理闰年 平年
							var date=new Date();
							end = year+"-2-"+date.getMaxDayOfMonth(year,2);
						}else {
							start = year+"-"+range+"-1";
							end = year+"-"+range+"-30";
						}
					}
					$("#startDate").val(start);
					$("#endDate").val(end);
				});

				$('#sl-ck-qj1,#sl-ck-qj2').on('change',function(){
					var start= $('#sl-ck-qj1').val();
					var end = $('#sl-ck-qj2').val();
					$("#startDate").val(start);
					$("#endDate").val(end);
				});
			}
		});
	}else{
		$('#xzsjLB').dialog('open');
	}
	});
});