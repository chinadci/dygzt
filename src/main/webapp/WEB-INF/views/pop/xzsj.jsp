<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<div>
	<div id="J_SJTab">
		<ul>
			<li id="sjf-n" data-id="sjf-n">
				<a href="#tabs-1">年</a>
			</li>
			<li id="sjf-jd" data-id="sjf-jd">
				<a href="#tabs-2">季度</a>
			</li>
			<li id="sjf-zdy" data-id="sjf-zdy">
				<a href="#tabs-3">指定月</a>
			</li>
			<li id="sjf-qj" data-id="sjf-qj">
				<a href="#tabs-4">期间</a>
			</li> 
		</ul>
		<div id="tabs-1" class="timetab-content">
			<select id="sl-ck-n1">
				<c:forEach items="${yearList}" var="year">
					<option value="${year}">
						${year}年
					</option>
				</c:forEach>
			</select>
			<select id="sl-ck-n2">
				<option value="1">
					上半年
				</option>
				<option value="2">
					下半年
				</option>
				<option value="3" selected>
					全年
				</option>
			</select>
		</div>
		<div id="tabs-2" class="timetab-content">
			<select id="sl-ck-jd1">
				<c:forEach items="${yearList}" var="year">
					<option value="${year}">
						${year}年
					</option>
				</c:forEach>
			</select>
			<select id="sl-ck-jd2">
				<option value="1">
					第一季度
				</option>
				<option value="2">
					第二季度
				</option>
				<option value="3">
					第三季度
				</option>
				<option value="4">
					第四季度
				</option>
			</select>
		</div>
		<div id="tabs-3" class="timetab-content">
			<select id="sl-ck-zdy1">
				<c:forEach items="${yearList}" var="year">
					<option value="${year}">
						${year}年
					</option>
				</c:forEach>
			</select>
			<select id="sl-ck-zdy2">
				<c:forEach items="${monthList}" var="month">
					<option value="${month}">
						${month}月
					</option>
				</c:forEach>
			</select>
		</div>
		<div id="tabs-4" class="timetab-content">
			从 <input id="sl-ck-qj1" name="start" class="datepicker" />&nbsp;至&nbsp;
			<input id="sl-ck-qj2"name="end" class="datepicker" />
		</div>
	</div>
	<div class="error-tip"></div>
</div>

<input type="hidden" name="start" id="startDate"></input>
<input type="hidden" name="end" id="endDate"></input>