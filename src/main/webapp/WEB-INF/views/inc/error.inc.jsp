<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!doctype html>
<div>
	<img src="resources/css/img/error.png"
		style="margin-top: 150px; margin-left: 260px; float: left;" />
	<div style="float: left; margin-top: 175px; line-height: 23px;">
		<h1 style="font-size: 16px; font-weight: bold">
			您访问的网页暂时不能显示
		</h1>
		<span style="font-size: 14px;">
		<c:choose>
			<c:when test="${error!=null }">
			${error }
			</c:when>
			<c:otherwise>
			您正在浏览的页面可能已经被删除、重命名或暂时不可用
			</c:otherwise>
		</c:choose>
		</span>
	</div>
</div>