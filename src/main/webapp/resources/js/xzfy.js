$(function(){
	var fylist;
    $('body').append('<div id="xzfyLB"></div>');
    $('#xzfyLB').dialog({
        autoOpen: false,
        title: '<img src="resources/css/img/icon-by.png" /> 选择法院',
        modal: true,
        position:["center",50],
		width: 350,
        height:470,
        dialogClass:"dialog1",
        buttons:{
        	'确定':function(e){
        		var fym=$('#fym').val();
        		var xzfydm=$('#xzfydm').val();
        		if(fym=='' || xzfydm==''){
        			$("#xzfy").val($("#xzfy-wrap a:first").text());
                	$("#fydm").val($("#xzfy-wrap a:first").data("fydm"));
        		}else{
        			$("#xzfy").val(fym);
                	$("#fydm").val(xzfydm);
        		}
        		$("#xzfy").trigger("change");
        		$('#xzfyLB').dialog('close');
        	},
        	'取消':function(){
        		$('#xzfyLB').dialog('close');
        	}
        	
        }
    });
    
    $('#xzfy').on('click',function(){
    	$tr=$(this).closest('tr');
    	if(!fylist){
    		$.ajax({
                type: "post",
                url: $(this).data('method'),
                dataType : "html",
                success: function(html){
                	fylist = html;
                    $('#xzfyLB').html(html).dialog('open');
                    $("#xzfy-tree").treeview({
                		collapsed: true,
                		unique: true
                	});
                    $("#xzfy-wrap a").click(function(e){
                    	$("#xzfy-wrap a.selected").removeClass("selected");
                    	$(this).addClass("selected");
                    	var fy = $(this).text();
                    	var fydm = $(this).data("fydm");
                    	$("#fym").val(fy);
                    	$("#xzfydm").val(fydm);
                    	$(this).prev().click();
                    });
                    $('#xzfyLB .level-1').click();
               }
            });
    	}else{
    		$('#xzfyLB').dialog('open');
    	}
    });
    
    
    var asFy=new Array();
	$('#xzfy-wrap a').each(function(){
		asFy.push($(this).text());
	});
	
    $('#xzfy').autocomplete({
        autoFocus: true,
        delay: 0,
        source: asFy
    });
    
});