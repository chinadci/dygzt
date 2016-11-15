$(function(){
	var ajlxlist;
	var setting = {
		view:{
			dblClickExpand:false,
			showLine:false,
			showIcon:false,
			showTitle:false,
		},
		check: {
			enable: true,
		},
		callback: {
			onClick:zTreeOnClick,
			onDblClick:zTreeOnDblClick
		}
	};
    $('body').append('<div id="xzajlxLB"></div>');
    $('#xzajlxLB').dialog({
        autoOpen: false,
        title: '<img src="resources/css/img/icon-by.png" /> 选择案件类型',
        modal: true,
        position:["center",50],
        width: 300,
        height:600,
        dialogClass:"dialog1",
        buttons:{
        	'确定':function(e){
        		var zTree=$.fn.zTree.getZTreeObj("ajlx-tree");
        		var ajlxtext='';
        		var ajlxid='';
        		if(zTree.getCheckedNodes().length>0){
        			var nodes=zTree.getNodesByFilter(filter);
            		for(var i=0;i<nodes.length;i++){
    					var node=nodes[i];
    					ajlxtext+=node.name+"、";
    					ajlxid+=node.dmid+";";
    				}
            		ajlxtext = ajlxtext.substring(0,ajlxtext.length-1);
            		ajlxid = ajlxid.substring(0,ajlxid.length-1);
				}
        		$("#ajlx").val(ajlxtext);
                $("#ajlxid").val(ajlxid);
        		$("#ajlx").trigger("change");
        		$('#xzajlxLB').dialog('close');
        	},
        	'取消':function(){
        		$('#xzajlxLB').dialog('close');
        	}
        }
    });
    
    $('#ajlx').on('click',function(){
    	if(!ajlxlist){
    		$.ajax({
                type: "post",
                url: $(this).data('method'),
                dataType : "json",
                success: function(json){
                	ajlxlist = json;
                	var html='<div class="tree_wrapper">';
					html+='<ul id="ajlx-tree" class="ztree"></ul></div>';
                    $('#xzajlxLB').html(html).dialog('open');
                    $.fn.zTree.init($("#ajlx-tree"), setting, json);
               }
            });
    	}else{
    		$('#xzajlxLB').dialog('open');
    	}
    });
    
    function filter(node){
		return (node.checked==true && node.isParent==false);
	}
    
    var clickFun = null;
    function zTreeOnClick(event,treeId,treeNode){
    	clearTimeout(clickFun);
    	clickFun = setTimeout(function(){
    		if(treeNode.isParent){
        		var zTree=$.fn.zTree.getZTreeObj(treeId);
        		zTree.expandNode(treeNode,!(treeNode.open),false);
        	}
    	},300);
    }
    function zTreeOnDblClick(event,treeId,treeNode){
    	clearTimeout(clickFun);
    	var zTree=$.fn.zTree.getZTreeObj(treeId);
    	zTree.checkNode(treeNode,!(treeNode.checked),true,false);
    	zTree.expandNode(treeNode,true,false);
    }
});