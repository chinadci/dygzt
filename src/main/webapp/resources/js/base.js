//等待图片
var waitImg = '<img src="resources/css/img/loading.gif">';
$(function(){

	/*每次在页面刷新的时候，初始化页面效果*/
	//边框效果
	$(".wrap1").wrap('<div class="wrap1L1"></div>');
	$(".wrap1L1").wrap('<div class="wrap1L2"></div>');
	
	$(".wrap2").wrap('<div class="wrap2L1"></div>');
	$(".wrap2L1").wrap('<div class="wrap2L2"></div>');

	$(".wrap1L1").css({
		'border':"1px solid #bcbcbc"
	});
	
	$(".wrap1L2").css({
		'border':"1px solid #d5d5d5",
		'margin-bottom':"12px"
	});
	
	$(".wrap2L1").css({
		'border':"1px solid #a5a5a5"
	});
	
	$(".wrap2L1").each(function(){
		if($(this).children()[0].tagName=="H3"){
			$(this).parent().css({
				'border':"1px solid #cecece"
			});
		}else{
			$(this).parent().css({
				'border':"1px solid #cecece",
				'margin-bottom':"8px"
			});
		}
	});
	
	$(".wrap3").wrap('<div class="wrap3L1"></div>'); //用 cloass = "wrap3L1" 包裹 wrap3，形成一个灰色边框的包裹层，
	$(".wrap3L1").wrap('<div class="wrap3L2"></div>');//用 cloass = "wrap3L2" 包裹 wrap3，形成一个灰色边框的包裹层，
	
	$(".wrap3L1").css({
		'border':"1px solid #a5a5a5"
	});
	$(".wrap3L2").css({
		'border':"1px solid #cecece"
	});
	
	/*法院列表*/
	function getFyContent(){}
	$(".treelb>ul>li").on("click",function(e){
		if(e.target!=this){return;}
		var $this=$(this);
		if($this.hasClass("active")){
			if($this.hasClass("fold")){
				$this.siblings(".unfold").removeClass("unfold").addClass("fold");
				$this.removeClass("fold").addClass("unfold");
			}else if($this.hasClass("unfold")){
				$this.removeClass("unfold").addClass("fold");
			}
		}else{
			if($this.hasClass("fold")){
				$this.siblings(".unfold").removeClass("unfold").addClass("fold");
				$this.removeClass("fold").addClass("unfold");
			}
		}
		$this.siblings().removeClass("active");
		$this.find("li").removeClass("active");
		$this.siblings(".fold").find("li").removeClass("active");
		$this.siblings(".unfold").find("li").removeClass("active");
		$this.addClass("active");
		getFyContent();
	});

	$(".treelb>ul>li>ul>li").on("click",function(e){
		var $this=$(this);
		var $parentLi=$this.parent().closest("li");
		$parentLi.siblings().removeClass("active");
		$parentLi.addClass("active");
		$parentLi.siblings(".fold").find("li").removeClass("active");
		$this.siblings().removeClass("active");
		$this.addClass("active");
		getFyContent();
	});

	/*隐藏左侧列表*/
	$(".collapse-button").on('click',function(){
		if($(this).hasClass("collapse")){
			$(this).removeClass("collapse");
			$("#left").removeClass("hide");
			$("#right").removeClass("expand");
		}else{
			$(this).addClass("collapse");
			$("#left").addClass("hide");
			$("#right").addClass("expand");
		}
		window.onresize();
	});
	
	//提示框
	$('body').append('<div id="empty_alert"></div>');
	$('#empty_alert').append('<p></p>');
	$('#empty_alert').dialog({
		autoOpen : false,
		bgiframe : true,
		modal : true,
		resizable : false,
		dialogClass : 'dlg-wrap',
		title : '提示信息',
		buttons:{
			"确定":function(){
				$('#empty_alert').dialog("close");
			}
		}
	});
	
	//弹框
	$('body').append('<div id="dlg"></div>');
	
	//遮罩层
	$('body').append('<div id="mask"><div class="wait_wrapper"><p class="J_WAIT"></p><p class="waittext">加载中，请稍等……</p></div></div>');
	
});


//初始化body
/*
function init_body(){
	max_body();
	setRemainingHeight("#main","body","#welcome","#header");
}
*/

/*
function max_body(){
	var myw = screen.availWidth; //定义一个myw，接受到当前全屏的宽 
	var myh = screen.availHeight; //定义一个myw，接受到当前全屏的高 
	window.moveTo(0, 0); //把window放在左上脚 
	window.resizeTo(myw, myh); //把当前窗体的长宽跳转为myw和myh 
	var windowHeight = $(window).height();
	$("body").outerHeight(windowHeight<700?700:windowHeight);
}*/
//弹提示框
function alertEmpty(msg,title) {
	$('#empty_alert p').html(msg);
	if(title){
		$('#empty_alert').dialog( "option", "title", title);
	}else{
		$('#empty_alert').dialog( "option", "title",'提示信息');
	}
	$('#empty_alert').dialog('open');
}
//弹遮罩层
function showMask(msg) {
	if(msg){
		$('#mask .waittext').html(msg);
	}
	$('#mask').show();
}

//隐藏遮罩层
function hideMask() {
	$('#mask .waittext').html("加载中，请稍等……");
	$('#mask').hide();
}
/*
//me:当前，father：父元素，后面可以放无数个其他占地方的元素
function setRemainingHeight(me,father){
	var length = arguments.length;
	var height = 0;
	for(var i =2;i<length;i++){
		height+=$(arguments[i]).outerHeight();
	}
	$(me).outerHeight($(father).height()-height);
}*/
//date1和date2都是string
function dateDiff(date1,date2){
	return Date.parse(date1)>Date.parse(date2);
}
//reset(".researchform");
function reset(father){
	 $(father+" select").each(function(){
		 $(this).children(":first").attr("selected","selected");
	 });
	 $(father+" input[type='checkbox']").each(function(){
		 $(this).removeAttr("checked");
	 });
	 $(father+" input[type='text']").val("");
} 
function table_1_init(){
	$(".table-1 tr:nth-child(even)").css("background","rgb(211,223,237)");
	$(".table-1 tr:nth-child(odd)").css("background","#FFF");
}
//date_init(".time_input","#ksrq","#jsrq",da,new Date());
function date_init(selector,dateselector,dateselector2,date,date2){
	$(selector).datepicker({
		changeYear : true,
		changeMonth : true,
		dateFormat: "yy-mm-dd",
		maxDate:0,
		onSelect: function(dateText,inst){
				var ksrq = $(dateselector).val();
				var jsrq = $(dateselector2).val();
				if(dateDiff(ksrq,jsrq)){
					$(dateselector).val(jsrq);
					$(dateselector2).val(ksrq);
				}
			}
	});
	$(dateselector2).datepicker("setDate", date2);
	$(dateselector).datepicker("setDate", date);
}
function printTable(ta_dom,p_title){
	var p_table = ta_dom.find("thead").html();
	p_table += ta_dom.find("tbody").html();
	var p_title = $("#bbmc").val();
	var p_html = "<div><div class='print_table'><h3>"+p_title+"</h3><table>"+p_table+"</table></div>";
	myPrint(p_html,p_title);
	initDataTable();
}
function myPrint(input,title){

	var headobj = document.getElementsByTagName("head").item(0);
	var headHtml = headobj.outerHTML;
	var reg = new RegExp("<title>.*</title>","g");
	headHtml = headHtml.replace(reg,"<title>"+title+"</title>");
	printFrame = document.getElementById("lldxi_printRegionFrame_2012_0112");
	if(printFrame!=null)
	{document.body.removeChild(printFrame);}

	printFrame = document.createElement("iframe");
	printFrame.setAttribute("src", "about:blank");
	printFrame.setAttribute("id", "lldxi_printRegionFrame_2012_0112");
	printFrame.setAttribute("marginheight", 0);
	printFrame.setAttribute("marginwidth", 0);
	printFrame.style.display = "none";
	document.body.appendChild(printFrame);

	if(navigator.userAgent.indexOf("MSIE")>0){
		var htmlobj = printFrame.contentWindow.document.createElement("html");
		var bodyobj = printFrame.contentWindow.document.createElement("body");
		bodyobj.innerHTML = input;
		htmlobj.appendChild(headobj.cloneNode(true));
		htmlobj.appendChild(bodyobj);
		printFrame.contentWindow.document.appendChild(htmlobj);
		printFrame.contentWindow.document.execCommand("Print",true);
	}

	if(navigator.userAgent.indexOf("Chrome")>0){
		htmlstr = "<html>" + headHtml + "<body>" + input+"<\/body>"+"<\/html>";
		printFrame.contentWindow.document.write(htmlstr);
		printFrame.contentWindow.print();
	}
}