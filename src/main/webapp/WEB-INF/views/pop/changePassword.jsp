<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<div class="form2">
	<form action="changePassword.aj" method="post" class="passwordform">
		<div>
			<label for="oldpass" class="col30">原密码：</label>
			<input name="oldpass" id = "oldpass" type="text" class="col60" />
		</div>
		<div>
			<label for="newpass" class="col30">现密码：</label>
			<input name="newpass" id = "newpass" type="text" class="col60" />
		</div>
		<div>
			<label for="newpass1" class="col30">再输一次：</label>
			<input name="newpass1" id = "newpass1" type="text" class="col60" />
		</div>
		<div class="error-tip"></div>
	</form>
</div>