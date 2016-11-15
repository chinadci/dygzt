<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<div class="menu wrap1">
	<h3>
		<s></s>菜单
	</h3>
	<div class="line-5"></div>
	<div class="treelb" id="menu">
		<ul>
			<c:if test="${yhxx.yhqx.contains('调研人')}">
				<li	class="<c:choose><c:when test="${tab2.startsWith('bbsc_')}">unfold active</c:when><c:otherwise>fold</c:otherwise></c:choose>">
					<span></span>报表生成
					<ul>
						<li <c:if test="${tab2 == 'bbsc_custom' }">class="active"</c:if>>
							<a href="bbsc.do">自定义报表生成</a></li>
						<li <c:if test="${tab2 == 'bbsc_template' }">class="active"</c:if>>
							<a href="bbscByMyTemplate.do">根据模板生成</a></li>
					</ul>
				</li>
				<li <c:if test="${tab2 == 'templateManagement' }">class="active"</c:if>>
					<a href="templateManagement.do">模板管理</a>
				</li>
				<li <c:if test="${tab2 == 'bbscHistory' }">class="active"</c:if>>
					<a href="bbscHistory.do">报表生成历史</a>
				</li>
			</c:if>

			<c:if test="${yhxx.yhqx.contains('管理员')}">
				<li <c:if test="${tab2 == 'bbscSearch' }">class="active"</c:if>>
					<a href="bbscSearch.do">报表生成记录查询</a>
				</li>
				<li class="<c:choose><c:when test="${tab2.startsWith('sta_')}">unfold active</c:when><c:otherwise>fold</c:otherwise></c:choose>">
					<span></span>报表生成记录统计
					<ul>
						<li <c:if test="${tab2 == 'sta_dyrfy' }">class="active"</c:if>>
							<a href="bbscStatisticsDyrfy.do">统计人法院分布图</a></li>
						<li <c:if test="${tab2 == 'sta_sj' }">class="active"</c:if>>
							<a href="bbscStatisticsSj.do">统计数目走势图</a></li>
					</ul>
				</li>

				<li <c:if test="${tab2 == 'bbscChangePerson' }">class="active"</c:if>>
					<a href="bbscChangePerson.do">报表生成用户管理</a>
				</li>


			</c:if>
		</ul>
	</div>
</div>
<script type="text/javascript">
	init_body();
	setRemainingHeight("#menu", ".menu", ".menu h3", ".line-5");
</script>