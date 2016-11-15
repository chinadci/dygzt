<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<div class="form2">
	<form action="saveTemplate.aj" method="post" class="saveTemplateForm">
		<label for="mbmc" class="col20">模板名称：</label>
		<textarea name="mbmc" id = "mbmc" class="col75" style="height:60px"/></textarea>
		<div class="error-tip"></div>
	</form>
</div>