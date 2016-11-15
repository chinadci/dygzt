var chart;
$(function(){
	//初始化时间
	initDate();
	//初始化chart
	initChart();
	//初始化数据
	initTable();
	$("input[name='sjhf']").change(function(){
		initTable();
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
	       type: 'line',	//折线图
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
	    		text: '调研时间',
	    		style:{
	    			fontWeight: 'bold',
	    			fontSize: '14px'
	    		}
	    	},
	    	tickmarkPlacement:'on',	//点对在轴上，而不是中间
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
	   plotOptions: {
		   line: {
               dataLabels: {
                   enabled: true
               }
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
		url : 'statisticsXAxisSj.aj',
		type : 'post',
		dataType:'json',
		data : {
			sjhf:$("input[name='sjhf']:checked").val(),
			ksrq:$("#ksrq").val(),
			jsrq:$("#jsrq").val()
		},
		success : function(json) {
			if(json.success==true){
				clearSeries();
				chart.xAxis[0].setCategories(json.text,false);
				$.ajax({
					url : 'statisticsResearchSj.aj',
					type : 'post',
					dataType:'json',
					data : {
						sjhf:$("input[name='sjhf']:checked").val(),
						ksrq:$("#ksrq").val(),
						jsrq:$("#jsrq").val()
					},
					success : function(json_series) {
						if(json_series.success==true){
							chart.addSeries(json_series.text);
							chart.redraw();
						}else{
							alertEmpty(json_series.text);
						}
					}
				});
				$.ajax({
					url : 'statisticsManualSj.aj',
					type : 'post',
					dataType:'json',
					data : {
						sjhf:$("input[name='sjhf']:checked").val(),
						ksrq:$("#ksrq").val(),
						jsrq:$("#jsrq").val()
					},
					success : function(json_series) {
						if(json_series.success==true){
							chart.addSeries(json_series.text);
							chart.redraw();
						}else{
							alertEmpty(json_series.text);
						}
					}
				});
			}else{
				alertEmpty(json.text);
			}
		}
	});
	
}