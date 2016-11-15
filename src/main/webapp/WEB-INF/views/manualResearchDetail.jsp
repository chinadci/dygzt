<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib prefix="spring" uri="../lib/spring.tld"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE HTML>
<html>
<head>
<meta charset="utf-8" />
<title>调研工作台-人工调研需求</title>
<%@include file="inc/file.inc.jsp"%>
</head>
<body>
<div id="content">
	<%@include file="inc/header.inc.jsp"%>
	<div id="main">
		<div id="left">
			<div class="menu wrap1">
				<h3>
					<s></s>菜单
				</h3>
				<div class="line-5"></div>
				<div class="treelb" id="menu">
					<ul>
						<li class="active" data-url="#jbxxform">
							基本信息
						</li>
						<li data-url="#fjform">
							附件
						</li>
						<li data-url="#sqrform">
							申请人信息
						</li>
						<li data-url="#spxxform">
							审批信息
						</li>
						<li data-url="#dyjgform">
							调研结果
						</li>
					</ul>
				</div>
			</div>
		</div>
		<div id="right">
			<div class="collapse-button"></div>
			<div class="mod-content wrap3 manualdiv" style="overflow:auto">
				<div id="jbxxform" class="form3">
					<input type="hidden" id="researchid" value="${vo.id }"/>
					<h3><s class="ok"></s>基本信息</h3>
					<div class="line-5"></div>
					<div class="block">
						<table style="line-height:2;">
							<colgroup>
								<col width="80px"></col>
								<col width="370px"></col>
								<col width="80px"></col>
								<col></col>
							</colgroup>
							<tbody>
								<tr>
									<td><label>调研时间：</label></td>
									<td>${vo.dyrq }</td>
									<td><label>调研状态：</label></td>
									<td>${vo.dyzt }</td>
								</tr>
								<tr>
									<td><label>时间范围：</label></td>
									<td>${vo.ksrq } 到 ${vo.jsrq }</td>
									<td><label>时间类型：</label></td>
									<td>${vo.sjlx }</td>
								</tr>
								<tr>
									<td><label>法院范围：</label></td>
									<td>${vo.fyfw }</td>
									<td></td>
									<td></td>
								</tr>
							</tbody>
						</table>
					</div>
					<div class="text">
						<label>案件类型：</label>
						<span>${vo.ajlx }</span>
					</div>
					<div class="text">
						<label>调研目的：</label>
						<span>${vo.dymd }</span>
					</div>
					<div class="text" style="padding-bottom:0px;">
						<label>调研需求：</label>
						<span>${vo.dyxq }</span>
					</div>
				</div>
				<div id="fjform" class="form3">
					<h3><s class="ok"></s>附件</h3>
					<div class="line-5"></div>
					<c:if test="${vo.fjm!=null }">
					<div class="block">
						<ul style="margin-left:24px">
							<li>
								<a href="manualFjDownload.aj?researchid=${vo.id }">${vo.fjm }</a>
							</li>
						</ul>
					</div>
					</c:if>
				</div>
				<div id="sqrform" class="form3">
					<h3><s class="ok"></s>申请人信息</h3>
					<div class="line-5"></div>
					<div class="block">
						<table style="line-height:2;">
							<colgroup>
								<col width="100px"></col>
								<col width="350px"></col>
								<col width="80px"></col>
								<col></col>
							</colgroup>
							<tbody>
								<tr>
									<td><label>申请人姓名：</label></td>
									<td>${vo.dyr }</td>
									<td><label>所在法院：</label></td>
									<td>${vo.dyrfy }</td>
								</tr>
								<tr>
									<td><label>电话：</label></td>
									<td>${vo.dyrphone }</td>
									<td><label>Cocall：</label></td>
									<td>${vo.dyrlxfs }</td>
								</tr>
							</tbody>
						</table>
					</div>
				</div>
				<div id="spxxform" class="form3">
					<h3><s class="ok"></s>审批信息
					<c:if test="${vo.dyzt=='待审批'&&user.yhqx.contains('审批人') }">
					<div style="float:right;margin-right:20px">
						<div class="cpt_ok" data-researchid='${vo.id}' title="审批通过"></div>
						<div class="cpt_no" data-researchid='${vo.id}' title="审批不通过"></div>
					</div>
					</c:if>
					</h3>
					<div class="line-5"></div>
					<c:if test="${vo.spr!=null}">
					<div class="block">
						<table style="line-height:2;">
							<colgroup>
								<col width="100px"></col>
								<col width="350px"></col>
								<col width="80px"></col>
								<col></col>
							</colgroup>
							<tbody>
								<tr>
									<td><label>审批人姓名：</label></td>
									<td>${vo.spr }</td>
									<td><label>所在法院：</label></td>
									<td>${vo.sprfy }</td>
								</tr>
								<tr>
									<td><label>审批结果：</label></td>
									<td>${vo.spryj }</td>
									<td><label>审批时间：</label></td>
									<td>${vo.sprspsj }</td>
								</tr>
							</tbody>
						</table>
					</div>
					<c:if test="${vo.spryj=='不通过'}">
					<div class="text" style="padding-bottom:0px;">
						<label>退回理由：</label>
						<span>${vo.sprthyy }</span>
					</div>
					</c:if>
					</c:if>
				</div>
				<div id="dyjgform" class="form3" style="margin-bottom:20px">
					<h3><s class="ok"></s>调研结果
					<c:if test="${vo.dyzt=='待计算'&&user.yhqx.contains('计算人') }">
					<div style="float:right;margin-right:20px">
						<div class="cpt_menu_upload" data-researchid="${vo.id}" title="上传结果"></div>
					</div>
					</c:if>
					</h3>
					<div class="line-5"></div>
					<c:if test="${vo.dyjgm!=null}">
					<div class="table-3">
						<table>
							<thead>
								<tr>
									<th width="100px"></th>
									<th>文件名</th>
									<th width="100px">上传者</th>
									<th width="30px">操作</th>
								</tr>
							</thead>
							<tbody>
								<tr>
									<td>调研结果</td>
									<td>${vo.dyjgm }</td>
									<td>${vo.jsr }</td>
									<td><div class="cpt_menu_download" data-url="manualTableDownload.aj?researchid=${vo.id }" title="下载"></div></td>
								</tr>
								<c:if test="${user.yhqx.contains('计算人') }">
								<tr>
									<td>计算代码</td>
									<td>${vo.dydmm }</td>
									<td>${vo.jsr }</td>
									<td><div class="cpt_menu_download" data-url="computeCode.aj?researchid=${vo.id }" title="下载"></div></td>
								</tr>
								</c:if>
							</tbody>
						</table>
					</div>
					</c:if>
				</div>
			</div>
		</div>
	</div>
</div>	
</body>
<script type="text/javascript" >
init_body();
setRemainingHeight("#menu",".menu",".menu h3",".line-5");
setRemainingHeight(".manualdiv","#right");
</script>
<script src="resources/js/jquery/ajaxfileupload.js"></script>
<script src="resources/js/manual/manualResearchDetail.js"></script>
</html>
