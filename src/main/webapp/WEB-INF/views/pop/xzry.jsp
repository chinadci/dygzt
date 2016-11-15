<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<form action="xzry.aj" method="GET">
	<div class="row">
		<input id="yhdm" name="yhdm" type="hidden" />
		<input id="yhmc" name="yhmc"  type="hidden"/>
		<input id="yhbm" name="yhbm"  type="hidden"/>
	</div>
	<div id="xzry-wrap">
		<ul id="xzry-tree" class="dlgtree">
		<c:forEach items="${bmlist}" var="bm">
			<li>
				<span data-bmbh="${bm.bmbh }" class="level-1 xzry_btn">${bm.bmmc }</span>
				<c:forEach items="${bm.xtyhs }" var="xtyh">
				<ul>
					<li>
						<a class="level-2 xzry_btn" data-yhdm="${xtyh.yhm }" >${xtyh.name }</a>
					</li>
				</ul>
				</c:forEach>
			</li>
		</c:forEach>
		</ul>
</div>
</form>
