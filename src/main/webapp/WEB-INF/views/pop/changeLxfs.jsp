<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<div class="form2">
	<form action="changeLxfs.aj" method="post" class="lxfsform">
		<div>
			<label for="phone" class="col20">手机：</label>
			<input name="phone" id = "phone" type="text" class="col75" value="${yhxx.phone }"/>
		</div>
		<div>
			<label for="lxfs" class="col20">联系方式：</label>
			<input name="lxfs" id = "lxfs" type="text" class="col75" value="${yhxx.lxfs }"/>
		</div>
		<div class="error-tip"></div>
	</form>
</div>