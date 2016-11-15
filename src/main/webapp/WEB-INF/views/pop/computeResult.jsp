<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<div class="form2">
	<form action="compute.aj" method="post" class="resultform">
		<div>
			<label for="dyjg" class="col20">调研结果：</label>
			<input name="dyjg" id = "dyjg" type="file"/>
		</div>
		<div>
			<label for="dydm" class="col20">调研代码：</label>
			<input name="dydm" id = "dydm" type="file"/>
		</div>
		<div class="error-tip"></div>
	</form>
</div>