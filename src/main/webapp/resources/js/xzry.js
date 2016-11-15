$(function(){
	var rylist;
	var currentfydm;
    $('body').append('<div id="xzryLB"></div>');
    $('#xzryLB').dialog({
        autoOpen: false,
        title: '<img src="resources/css/img/icon-by.png" /> 选择人员',
        modal: true,
        position:["center",50],
        width: 300,
        height:600,
        dialogClass:"dialog1",
        buttons:{
        	'确定':function(e){
        		var yhdm=$('#yhdm').val();
        		var yhmc=$('#yhmc').val();
        		var yhbm=$('#yhbm').val();
        		if(yhdm=='' || yhmc==''|| yhbm==''){
        			$("#yhm").val($("#xzry-wrap a:first").data("yhdm"));
                	$("#name").val($("#xzry-wrap a:first").text());
                	$("#bm").val($("#xzry-wrap span:first").data("bmbh"));
        		}else{
        			$("#yhm").val(yhdm);
                	$("#name").val(yhmc);
                	$("#bm").val(yhbm);
        		}
        		$("#name").trigger("change");
        		$('#xzryLB').dialog('close');
        	},
        	'取消':function(){
        		$('#xzryLB').dialog('close');
        	}
        	
        }
    });
    
    $('#name').on('click',function(){
    	$tr=$(this).closest('tr');
    	if($("#fydm").val()&&((!rylist)||$("#fydm").val()!=currentfydm)){
    		$.ajax({
                type: "post",
                url: $(this).data('method'),
                dataType : "html",
                data:{
                	fydm:$("#fydm").val()
                },
                success: function(html){
                	rylist = html;
                    $('#xzryLB').html(html).dialog('open');
                    $("#xzry-tree").treeview({
                		collapsed: true,
                		unique: true
                	});
                    currentfydm = $("#fydm").val();
                    $("#xzry-wrap span").click(function(e){
                    	$(this).parent().find("a:first").trigger("click");
                    });
                    $("#xzry-wrap a.level-2").click(function(e){
                    	$("#xzry-wrap a.selected").removeClass("selected");
                    	$(this).addClass("selected");
                    	var yhmc = $(this).text();
                    	var yhdm = $(this).data("yhdm");
                    	var yhbm = $(this).parent().parent().parent().find("span:first").data("bmbh");
                    	$("#yhmc").val(yhmc);
                    	$("#yhdm").val(yhdm);
                    	$("#yhbm").val(yhbm);
                    });
               }
            });
    	}else{
    		$('#xzryLB').dialog('open');
    	}
    });
    
});