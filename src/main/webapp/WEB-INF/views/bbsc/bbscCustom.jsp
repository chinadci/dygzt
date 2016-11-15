<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib prefix="spring" uri="../../lib/spring.tld"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE HTML>
<html>
<head>
<meta charset="utf-8" />
<title>报表生成-自定义报表</title>
<%@include file="../inc/file.inc.jsp"%>
</head>
<body>
<div id="content">
	<%@include file="inc/header.inc.jsp"%>
	<div id="main">
		<div id="left">
			<%@include file="inc/menu.inc.jsp"%>
		</div>
		<div id="right">
			<div class="collapse-button"></div>
			<div class="mod-content wrap3 bbscform form4">
				<div class="formrow">
					<div class="row-head">统计列（一）:</div>
					<label class="check-item"><input type="checkbox" name="yjCondition" data-lx="ajzt" value="新收">新收</label>
                    <label class="check-item"><input type="checkbox" name="yjCondition" data-lx="ajzt" value="旧存"> 旧存</label>
                    <label class="check-item"><input type="checkbox" name="yjCondition" data-lx="ajzt" value="已结"> 已结</label>
                    <label class="check-item"><input type="checkbox" name="yjCondition" data-lx="ajzt" value="未结"> 未结</label>
				</div>
				<div class="formrow">
					<div class="row-head">统计列（二）:</div>
					<div style="display:inline-block;width:80%">
						<!-- 上级选择 -->
						<div class="ejl-list">
							<c:forEach var="lb" items="${lbList }" varStatus="status">
								<a data-bbbh="${lb}" class="btn2" href="javascript:void(0)">${lbmcList[status.index]}</a>
							</c:forEach>
                    	</div>
                    	<!-- 下级展开 -->
                    	<div>
                    		<c:forEach var="lb" items="${lbList }">
                    		<div data-bbbh="${lb}" class="expand-list">
                            	<c:forEach var="xx" items="${xxbbMap.get(lb) }">
                            		<input type="checkbox" name="xxx" data-bbbh="${lb}" data-xxmc="${xx.xxmc}" data-xsmc="${xx.xsmc}">${xx.xsmc}
                            	</c:forEach>
                            </div>
                    		</c:forEach>
                        </div>
                    	<!-- 最后拖拽 -->
                    	<div id="show-items" class="show-items">
                    		
                    	</div>
					</div>
				</div>
				<div class="formrow">
					<div class="row-head">统计行:</div>
					<select id="bblx" name="bblx" class="col8">
					</select>
					<label class="col10">统计范围:</label>
					<input name="fym" id = "xzfy" type="text" class="col22" data-method="xzfyhxq.aj"/>
					<input name="fyfw" id = "fydm" type="hidden"/>
				</div>
				<div>
					<div class="row-head">统计时间:</div>
					<span class="sjxz">
						<span id="sjxz-start"></span>
						<input name="ksrq" id = "ksrq" type="hidden"/>
						至
						<span id="sjxz-end"></span>
						<input name="jsrq" id = "jsrq" type="hidden"/>
						<a class="btn2" href="javascript:void(0)">时间选择</a>
					</span>
					<span class="formButGroup">
						<a id="btn_template" class="btn1" href="javascript:void(0)">保存模板</a>
						<a id="btn_bbsc" class="btn1" href="javascript:void(0)">生成报表</a>
						<a id="btn_save" class="btn1" href="javascript:void(0)">存入历史</a>
					</span>
				</div>
			</div>
			<div class="mod-content wrap3 tableContent">
			</div>
		</div>
	</div>
</div>	
</body>
<script type="text/javascript" >
setRemainingHeight(".tableContent","#right",".bbscform");
</script>
<script src="resources/js/xzsj.js"  type="text/javascript"></script>
<script src="resources/js/xzfy.js"  type="text/javascript"></script>
<script src="resources/js/bbsc/bbsc.js"  type="text/javascript"></script>
<script src="resources/js/bbsc/saveHistory.js"  type="text/javascript"></script>
</html>
