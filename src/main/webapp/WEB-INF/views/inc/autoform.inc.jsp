<%@ page language="java" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<form action="showAutoTable.aj" method="post" class="researchform form1">
    <div>
        <input type="hidden" id="bbbh" name="bbbh" value="${bbbh }"/>
        <label class="col10">法院范围:</label>
        <input name="fym" id="xzfy" type="text" class="col22" data-method="xzfyhxq.aj"/>
        <input name="fyfw" id="fydm" type="hidden"/>

        <label for="bblx" class="col10">调研项:</label>
        <select id="bblx" name="bblx" class="col8">
        </select>

        <label for="ajzt" class="col10">案件状态:</label>
        <select id="ajzt" name="ajzt" class="col8 condition">
            <option value="新收">&nbsp;&nbsp;新&nbsp;&nbsp;收</option>
            <option value="未结">&nbsp;&nbsp;未&nbsp;&nbsp;结</option>
            <option value="已结">&nbsp;&nbsp;已&nbsp;&nbsp;结</option>
        </select>
    </div>
    <div>
			<span class="sjxz">
				<label class="col10">时间范围:</label>
				<span id="sjxz-start"></span>
				<input name="ksrq" id="ksrq" type="hidden"/>
				至
				<span id="sjxz-end"></span>
				<input name="jsrq" id="jsrq" type="hidden"/>
				<a class="btn2" href="javascript:void(0)">时间选择</a>
			</span>
        <span class="formButGroup">
            <a id="btn_research" class="btn1" href="javascript:void(0)">调研</a>
            <a id="btn_research_TB" class="btn1" href="javascript:void(0)">统计同比</a>
            <a id="btn_save" class="btn1" href="javascript:void(0)">存入调研历史</a>
            <a id="btn_reset" class="btn1" href="javascript:void(0)">重置</a>
        </span>
    </div>
</form>
<script src="resources/js/xzfy.js" type="text/javascript"></script>
<script src="resources/js/xzsj.js" type="text/javascript"></script>