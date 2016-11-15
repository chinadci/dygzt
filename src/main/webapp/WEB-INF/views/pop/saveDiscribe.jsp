<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<div class="form2">
	<form action="saveAuto.aj" method="post" class="saveform">
		<label for="bgms" class="col20">报表描述：</label>
		<textarea name="bgms" id = "bgms" class="col75" style="height:60px"/></textarea>
		<div class="error-tip"></div>
	</form>
</div>