<%@ page language="java" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<div class="table-1">
    <h3>
        ${table.bbmc}
        <input type="hidden" name="bbmc" id="bbmc" value="${table.bbmc }"/>
        <span class="print-hidden btn-group">
		    <a class="table_print" href="javascript:void(0)">打印</a>
		    <a href="tableExport.aj?dytj=${table.dytj.replace('+','%2B') }">导出</a>
	    </span>
    </h3>
    <input type="hidden" name="dytj" id="dytj" value="${table.dytj }"/>

    <table id="dytable" class="center">
        <%--collevel参数代表列的--%>
        <c:forEach var="i" begin="0" end="${table.collevel-1 }" step="1">
            <tr>
                <c:forEach items="${table.valueList.get(i)}" var="cell">
                    <th rowspan="${cell.rowspan}" colspan="${cell.colspan}">
                            ${cell.value}
                    </th>
                </c:forEach>
            </tr>
        </c:forEach>

        <c:forEach var="j" begin="${table.collevel }" end="${table.valueList.size()-1 }" step="1">
            <tr>
                <c:forEach items="${table.valueList.get(j)}" var="cell" varStatus="rowindex">
                    <td rowspan="${cell.rowspan}" colspan="${cell.colspan}"
                        <c:if test="${rowindex.index<table.rowlevel}">class="rowheader"</c:if>>
                            <%--<!-- ${cell.value}-->--%>

                        <c:choose>
                            <%--如果该 cell 中的 sql 值，不为空，则把 sql 输出到 a 标签中，再赋值；否则直接赋值--%>
                            <c:when test="${cell.sql!=null}">
                                <a href="researchlb.do?base=${cell.base}&sql=${cell.sql}&fydm=${cell.fydm}"
                                   target="_blank">
                                        ${cell.value}
                                </a>
                                <label class="samePeriodLastYear" id="samePeriodLastYear">
                                        <%--同比增长速度=（本期发展水平-去年同期水平）/去年同期水平×100%--%>
                                    [${cell.samePeriodLastYearValue}]
                                </label>
                            </c:when>
                            <c:otherwise>
                                ${cell.value}
                                <label class="samePeriodLastYear" id="samePeriodLastYear">
                                        ${cell.samePeriodLastYearValue}
                                </label>
                            </c:otherwise>
                        </c:choose>

                    </td>
                </c:forEach>
            </tr>
        </c:forEach>

    </table>
</div>
<script type="text/javascript">
    initDataTable();
    function initDataTable() {
        $('#dytable').DataTable({
            "bLengthChange": false,	//改变数据量
            "paging": false,	//分页
            "bInfo": false,	//页脚信息
            "bFilter": false,	//搜索
            "bSort": false,	//排序
            "scrollY": true,

//            "scrollY": $(".table-1").parent().height() - parseInt($(".table-1").css("margin-bottom")) - $(".table-1 h3").outerHeight() - parseInt($(".table-1 h3").css("margin-top")) - parseInt($(".table-1 h3").css("margin-bottom")) - $("#dytable thead").outerHeight() - 10,
            "scrollX": true,
            "scrollCollapse": true
        });
    }

    //打印
    $(".table_print").click(function () {
        $("#dytable").DataTable().destroy(false);
        printTable($("#dytable"), $("#bbmc").val());
    });
</script>
