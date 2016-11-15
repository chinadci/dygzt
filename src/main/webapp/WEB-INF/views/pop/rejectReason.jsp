<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<div class="form2">
	<form action="reject.aj" method="post" class="reasonform">
		<label for="thyy" class="col20">退回原因：</label>
		<textarea name="thyy" id = "thyy" class="col75" style="height:60px"/></textarea>
		<div class="error-tip"></div>
	</form>
</div>