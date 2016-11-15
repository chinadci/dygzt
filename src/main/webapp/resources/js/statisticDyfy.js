var chart;
$(function(){
	//初始化时间
	initDate();
	//初始化chart
	initChart();
	//初始化x轴
	initXAxis();
	//初始化数据
	initTable();
	
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
function initChart() {
	Highcharts.setOptions({
	    // 所有语言文字相关配置都设置在 lang 里
	    lang: {
	        resetZoom: '重置缩放比例',
	        resetZoomTitle: '重置缩放比例'
	    }
	});
	$('#image').highcharts({
	   chart: {
	       type: 'column',
	       options3d: {
	          enabled: true,
	          alpha: 15,
	          beta: 0,
	          viewDistance: 25,
	          depth: 60
	       },
	       backgroundColor:'#eeeeee',	//背景
	       marginTop: 50,
	       marginRight: 0,
	       zoomType: 'x',	//x轴缩放
	   },
	   title: {	//表标题
	       text: null
	   },
	   xAxis: {		//x轴标题
	    	title: {
	    		text: '被调研法院',
	    		style:{
	    			fontWeight: 'bold',
	    			fontSize: '14px'
	    		}
	    	},
	    	categories: []
	   },
       yAxis: {		//y轴标题
           allowDecimals: false, //控制数轴是否显示小数
           min: 0,	//控制数轴最小值
           title: {
        	   text: '调研数',
        	   style:{
        		   fontWeight: 'bold',
               		fontSize: '14px'
        	   }
           }
       },
	   tooltip: {
	       headerFormat: '<b>{point.key}</b><br>',
	       pointFormat: '<span style="color:{series.color}">\u25CF</span> {series.name}: {point.y} / {point.stackTotal}'
	   },
	   plotOptions: {
	       column: {
	          stacking: 'normal',
	       },
	       series: {
	          cursor: 'pointer',
	          events: {
	              click: function (event) {
	                  if(event.point.url){
	                      	window.open(event.point.url);
	                  }
	              }
	          }
	       }
	   },
	   credits: {	//禁用版权信息
	      	enabled: false
	   },
	   series: [],
	});
	chart = $('#image').highcharts();
}
function initXAxis(){
	$.ajax({
		url : 'statisticXAxisDyfy.aj',
		type : 'post',
		dataType:'json',
		data : {},
		success : function(json) {
			if(json.success==true){
				chart.xAxis[0].setCategories(json.text);
			}else{
				alertEmpty(json.text);
			}
		}
	});
	
}
function clearSeries(){
	var series=chart.series;            
    while(series.length > 0) {
         series[0].remove(false);
    }
}
/**
 * 改变报表数据
 */
function initTable(){
	$.ajax({
		url : 'statisticsDyfy.aj',
		type : 'post',
		dataType:'json',
		data : {},
		success : function(json) {
			if(json.success==true){
				clearSeries();
				chart.addSeries(json.bmsery);
				chart.addSeries(json.aysery);
				chart.addSeries(json.fysery);
				chart.addSeries(json.manualsery);
				chart.redraw();
			}else{
				alertEmpty(json.text);
			}
		}
	});
	
}