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
				<li
					class="<c:choose><c:when test="${tab.startsWith('auto_')}">unfold active</c:when><c:otherwise>fold</c:otherwise></c:choose>">
					<span></span>自助调研
					<ul>
						<li <c:if test="${tab == 'auto_xstj' }">class="active"</c:if>>
							<a href="autoresearch.do?bbbh=xstj">刑事案件统计表</a></li>
						<li <c:if test="${tab == 'auto_mstj' }">class="active"</c:if>>
							<a href="autoresearch.do?bbbh=mstj">民事案件统计表</a></li>
						<li <c:if test="${tab == 'auto_xztj' }">class="active"</c:if>>
							<a href="autoresearch.do?bbbh=xztj">行政案件统计表</a></li>
						<li <c:if test="${tab == 'auto_zxtj' }">class="active"</c:if>>
							<a href="autoresearch.do?bbbh=zxtj">执行案件统计表</a></li>
						<li <c:if test="${tab == 'auto_pctj' }">class="active"</c:if>>
							<a href="autoresearch.do?bbbh=pctj">赔偿案件统计表</a></li>
						<li <c:if test="${tab == 'auto_qttj' }">class="active"</c:if>>
							<a href="autoresearch.do?bbbh=qttj">其他案件统计表</a></li>
					</ul></li>
				<li <c:if test="${tab == 'manual' }">class="active"</c:if>><a
					href="manual.do">人工调研</a></li>
				<li
					class="<c:choose><c:when test="${tab.startsWith('history_')}">unfold active</c:when><c:otherwise>fold</c:otherwise></c:choose>">
					<span></span>调研历史
					<ul>
						<li <c:if test="${tab == 'history_auto' }">class="active"</c:if>>
							<a href="autoHistory.do">自助调研历史</a></li>
						<li <c:if test="${tab == 'history_manual' }">class="active"</c:if>>
							<a href="manualHistory.do">人工调研历史</a></li>
					</ul></li>
			</c:if>

			<c:if test="${yhxx.yhqx.contains('审批人')}">
				<li <c:if test="${tab == 'approve' }">class="active"</c:if>><a
					href="approve.do">待审批列表</a></li>
				<li <c:if test="${tab == 'approved' }">class="active"</c:if>>
					<a href="approved.do">已审批列表</a></li>
			</c:if>

			<c:if test="${yhxx.yhqx.contains('计算人')}">
				<li <c:if test="${tab == 'compute' }">class="active"</c:if>><a
					href="compute.do">待计算列表</a></li>
				<li <c:if test="${tab == 'computed' }">class="active"</c:if>>
					<a href="computed.do">已计算列表</a></li>
			</c:if>

			<c:if test="${yhxx.yhqx.contains('管理员')}">
				<li <c:if test="${tab == 'distribute' }">class="active"</c:if>>
					<a href="distribute.do">指派人工调研</a></li>
				<li <c:if test="${tab == 'changePerson' }">class="active"</c:if>>
					<a href="changePerson.do">管理系统使用人员</a></li>
				<li <c:if test="${tab == 'changeSE' }">class="active"</c:if>>
					<a href="changeSE.do">管理系统人管理员</a></li>
				<li
					class="<c:choose><c:when test="${tab.startsWith('search_')}">unfold active</c:when><c:otherwise>fold</c:otherwise></c:choose>">
					<span></span>调研历史查询
					<ul>
						<li <c:if test="${tab == 'search_auto' }">class="active"</c:if>>
							<a href="autoSearchForm.do">自助调研查询</a></li>
						<li <c:if test="${tab == 'search_manual' }">class="active"</c:if>>
							<a href="manualSearchForm.do">人工调研查询</a></li>
					</ul></li>
				<li
					class="<c:choose><c:when test="${tab.startsWith('sta_')}">unfold active</c:when><c:otherwise>fold</c:otherwise></c:choose>">
					<span></span>统计分析
					<ul>
						<!-- 
					<li <c:if test="${tab == 'sta_dyfy' }">class="active"</c:if> >
						<a href="statisticsDyfy.do">被调研法院分布图</a>
					</li>
					 -->
						<li <c:if test="${tab == 'sta_dyrfy' }">class="active"</c:if>>
							<a href="statisticsDyrfy.do">调研人法院分布图</a></li>
						<li <c:if test="${tab == 'sta_sj' }">class="active"</c:if>>
							<a href="statisticsSj.do">调研数目走势图</a></li>
					</ul></li>
			</c:if>
		</ul>
	</div>
</div>
<script type="text/javascript">
	init_body();
	setRemainingHeight("#menu", ".menu", ".menu h3", ".line-5");
</script>