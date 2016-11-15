<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<div class="form2">
	<h3>
		<s class="ok"></s>申请人工调研
	</h3>
	<div class="line-5"></div>
	<form action="manualRequest.aj" method="post" class="manualform" style="margin: 15px 0px 0px 35px;">
		<div class="sjxz">
			<label class="col10">时间范围<span style="color:red">*</span>:</label>
			<span id="sjxz-start"></span>
			<input name="ksrq" id = "ksrq" type="hidden">
			至
			<span id="sjxz-end"></span>
			<input name="jsrq" id = "jsrq" type="hidden">
			<a class="btn2" href="javascript:void(0)">时间选择</a>
			
			<label class="col10">时间类型<span style="color:red">*</span>:</label>
			<input type="radio" name='sjlx' value="立案日期" checked/>立案日期
			<input type="radio" name='sjlx' value="结案日期"/>结案日期
			<input type="radio" name='sjlx' id="sjlx_qt" value=""/>其他<input name="ajlx_qt" id = "qt" class="col11">
		</div>
		<div>
			<label class="col10">法院范围<span style="color:red">*</span>:</label>
			<input name="fym" id = "xzfy" type="text" class="col24_5" data-method="xzfyhxq.aj" value="全市法院"/>
			<input name="fyfw" id = "fydm" type="hidden" value="qsfy"/>
		</div>
		<div>
			<label class="col10">案件类型<span style="color:red">*</span>:</label>
			<textarea name="ajlx" id="ajlx" class="col70" data-method="xzajlx.aj" style="height:80px"/></textarea>
			<input name="ajlxid" id="ajlxid" type="hidden"/>
		</div>
		<div style="margin-top: 8px;">
			<label for="dymd" class="col10">调研目的<span style="color:red">*</span>:</label>
			<textarea name="dymd" id = "dymd" class="col70" style="height:80px"/></textarea>
		</div>
		<div style="margin-top: 8px;">
			<label for="dyxq" class="col10">调研需求<span style="color:red">*</span>:</label>
			<textarea name="dyxq" id = "dyxq" class="col70" style="height:80px"/></textarea>
		</div>
		<div style="margin-top: 10px;">
			<label for="fj" class="col10">附件:</label>
			<input name="fj" id="fj" type="file" class="col25"/>
			<label for="phone">手机:</label>
			<input name="phone" id="phone" class="col15" type="text" value="${yhxx.phone }"/>
			<label for="lxfs" class="col8">Cocall:</label>
			<input name="lxfs" id="lxfs" class="col15" type="text" value="${yhxx.lxfs }"/>
		</div>
		<div class="error-warn"></div>
		<div class="formButGroup">
			<a id="btn_manual" class="btn1" href="javascript:void(0)">申请调研</a>
		</div>
	</form>
</div>
<link rel="stylesheet" href="resources/css/zTreeStyle.css" />
<script src="resources/js/jquery/jquery.ztree.all.min.js" type="text/javascript"></script>
<script src="resources/js/xzfy.js"  type="text/javascript"></script>
<script src="resources/js/xzsj.js"  type="text/javascript"></script>
<script src="resources/js/xzajlx.js"  type="text/javascript"></script>