<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<form action="xzfy.aj" method="GET">
	<div class="row">
		<input id="fym" name="fym" type="hidden" />
		<input id="xzfydm" name="xzfydm"  type="hidden"/>
	</div>
	<div id="xzfy-wrap">
		<ul id="xzfy-tree" class="dlgtree">
			<li>
			<a data-fydm="${fylist.fydm }" data-fybh="${fylist.fybh }" class="level-1 xzfy_btn">${fylist.fymc}</a>
			<c:forEach items="${fylist.xjfyList}" var="gylb">
			<ul>
				<li>
					<a data-fydm="${gylb.fydm }" data-fybh="${gylb.fybh }" class="level-2 xzfy_btn">${gylb.fymc }</a>
					<c:forEach items="${gylb.xjfyList }" var="xjfy">
					<ul>
						<li>
							<a class="level-3 xzfy_btn" data-fydm="${xjfy.fydm }" data-fybh="${xjfy.fybh }">${xjfy.fymc }</a>
						</li>
					</ul>
					</c:forEach>
				</li>
			</ul>
			</c:forEach>
			</li>
		</ul>
	</div>
</form>