<%@ page language="java" pageEncoding="UTF-8" %>
<%@ taglib prefix="spring" uri="../lib/spring.tld" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<!DOCTYPE HTML>
<html>
<head>
    <meta charset="utf-8"/>
    <title>报表生成-根据模板生成</title>
    <%@include file="inc/file.inc.jsp" %>
</head>
<body>
<div id="content">
    <%@include file="inc/header.inc.jsp" %>
    <div id="main">
        <div class="mod-content wrap3 autotable">
            <s class="file"></s>${table.bbmc} <input type="hidden" name="bbmc" id="bbmc"
                                                     value="${table.bbmc }">
            <span class="print-hidden btn-group">
						<input type="hidden" name="dytj" id="dytj" value="${table.dytj }">
						<c:if test="${hasHistory }">
                            <a class="btn_save" href="javascript:void(0)">存入历史</a>
                        </c:if>
						<a class="table_print" href="javascript:void(0)">打印</a>
						<a href="tableExport.aj?dytj=${table.dytj.replace('+','%2B') }">导出</a>
					</span>
            </h3>

            <h3 class="creditH3">

                <c:choose>
                    <%--当后台没有置信度的数据的时候，则告知没有计算--%>
                    <c:when test="${credit == null}">
                        <label class="creditNoData">后台暂无该时间段内的置信度数据</label>
                    </c:when>
                    <c:otherwise>

                        <c:choose>
                            <c:when test="${credit.XSCredit eq 0}">
                                <label class="credit">暂未计算新收置信度</label>
                            </c:when>
                            <c:otherwise>
                                <label class="credit">
                                    <fmt:formatNumber var="XSCredit" type="percent" maxFractionDigits="2"
                                                      value="${credit.XSCredit}"/>
                                    新收置信度: [${XSCredit}]
                                </label>
                            </c:otherwise>
                        </c:choose>


                        <c:choose>
                            <c:when test="${credit.WJcredit eq 0}">
                                <%--<label class="credit">暂未计算未结置信度</label>--%>
                            </c:when>
                            <c:otherwise>
                                <label class="credit">
                                    <fmt:formatNumber var="WJcredit" type="percent" maxFractionDigits="2"
                                                      value="${credit.WJcredit}"/>
                                    未结置信度: [${WJcredit}]
                                </label>
                            </c:otherwise>
                        </c:choose>

                        <c:choose>
                            <c:when test="${credit.YJCredit eq 0}">
                                <%--<label class="credit"> 暂未计算已结置信度</label>--%>
                            </c:when>
                            <c:otherwise>
                                <label class="credit">
                                    <fmt:formatNumber var="YJCredit" type="percent" maxFractionDigits="2"
                                                      value="${credit.YJCredit}"/>
                                    已结置信度: [${YJCredit}]
                                </label>
                            </c:otherwise>
                        </c:choose>
                        <c:choose>
                            <c:when test="${credit.JCCredit} eq 0">
                                <%--<label class="credit">暂未计算旧存信度</label>--%>
                            </c:when>
                            <c:otherwise>
                                <label class="credit">
                                    <fmt:formatNumber var="JCCredit" type="percent" maxFractionDigits="2"
                                                      value="${credit.JCCredit}"/>
                                    旧存置信度: [${JCCredit}]
                                </label>
                            </c:otherwise>
                        </c:choose>

                    </c:otherwise>
                </c:choose>

            </h3>
            <h3 class="creditH3">
                <label class="explian2">说明: 中括号的数据为同比数据，如果没有同比数据则代表去年同期该案件数为0</label>
            </h3>

            <div class="line-5"></div>
            <div class="table-1" style="margin:10px 10px 20px 15px;">
                <table id="dytable" class="center">
                    <c:forEach var="i" begin="0" end="${table.collevel-1 }" step="1">
                        <tr>
                            <c:forEach items="${table.valueList.get(i)}" var="cell">
                                <th rowspan="${cell.rowspan}" colspan="${cell.colspan}">
                                        ${cell.value}</th>
                            </c:forEach>
                        </tr>
                    </c:forEach>

                    <tbody>
                    <c:forEach var="j" begin="${table.collevel }"
                               end="${table.valueList.size()-1 }" step="1">
                        <tr>
                            <c:forEach items="${table.valueList.get(j)}" var="cell"
                                       varStatus="rowindex">
                                <td rowspan="${cell.rowspan}" colspan="${cell.colspan}"
                                    <c:if test="${rowindex.index<table.rowlevel}">class="rowheader"</c:if>>
                                    <!-- ${cell.value}-->

                                    <c:choose>
                                        <%--如果该 cell 中的 sql 值，不为空，则把 sql 输出到 a 标签中，再赋值；否则直接赋值--%>
                                        <c:when test="${cell.sql!=null}">
                                            <a href="researchlb.do?base=${cell.base}&sql=${cell.sql}&fydm=${cell.fydm}"
                                               target="_blank">
                                                    ${cell.value}
                                            </a>
                                            <c:choose>
                                                <c:when test="${cell.samePeriodLastYearValue != null}">
                                                    <label class="samePeriodLastYear" id="samePeriodLastYear">
                                                            <%--先不输出去年同期的值，值计算同比，如果需要输出去年同期忠诚，则去掉该注释--%>
                                                            <%--[${cell.samePeriodLastYearValue}]--%>
                                                        <c:choose>
                                                            <c:when test="${(cell.value == 0 && cell.samePeriodLastYearValue == 0) || cell.samePeriodLastYearValue ==0}">
                                                            <%--如果分子分母都为0那么不出输出同比--%>
                                                            </c:when>
                                                            <c:otherwise>
                                                                <c:set var="thisValue" value="${cell.value}"/>
                                                                <c:set var="lastValue"
                                                                       value="${cell.samePeriodLastYearValue}"/>
                                                                <fmt:formatNumber var="intVar3" type="percent"
                                                                                  maxFractionDigits="2"
                                                                                  value="${(thisValue-lastValue)/lastValue}"/>
                                                                [${intVar3}]
                                                            </c:otherwise>
                                                        </c:choose>
                                                    </label>
                                                </c:when>

                                            </c:choose>
                                        </c:when>

                                        <c:otherwise>
                                            ${cell.value}
                                            <c:choose>
                                                <c:when test="${cell.samePeriodLastYearValue != null}">
                                                    <label class="samePeriodLastYear" id="samePeriodLastYear">
                                                            <%--先不输出去年同期的值，值计算同比，如果需要输出去年同期忠诚，则去掉该注释--%>
                                                            <%--[${cell.samePeriodLastYearValue}]--%>
                                                        <c:choose>
                                                            <c:when test="${(cell.value == 0 && cell.samePeriodLastYearValue == 0) || cell.samePeriodLastYearValue ==0}">
                                                                <%--如果分子分母都为0，或者去年同期为0那么不出输出同比--%>
                                                            </c:when>
                                                            <c:otherwise>
                                                                <c:set var="thisValue" value="${cell.value}"/>
                                                                <c:set var="lastValue"
                                                                       value="${cell.samePeriodLastYearValue}"/>
                                                                <fmt:formatNumber var="intVar3" type="percent"
                                                                                  maxFractionDigits="2"
                                                                                  value="${(thisValue-lastValue)/lastValue}"/>
                                                                [${intVar3}]
                                                            </c:otherwise>

                                                        </c:choose>

                                                    </label>
                                                </c:when>
                                                <c:otherwise>

                                                </c:otherwise>
                                            </c:choose>

                                        </c:otherwise>
                                    </c:choose>

                                </td>
                            </c:forEach>
                        </tr>
                    </c:forEach>
                    </tbody>
                </table>
            </div>
        </div>
    </div>
</div>
<script type="text/javascript">
    //init_body();
    //setRemainingHeight(".autotable","#main");
    initDataTable();
    function initDataTable() {
        $('#dytable').DataTable({
            "bLengthChange": false,	//改变数据量
            "paging": false,	//分页
            "bInfo": false,	//页脚信息
            "bFilter": false,	//搜索
            "bSort": false,	//排序
            "scrollY": $(".autotable").height() - $(".table-1").position().top - parseInt($(".table-1").css("margin-bottom")) - parseInt($(".table-1").css("margin-top")) - $("#dytable thead").outerHeight(),
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
<script src="resources/js/bbsc/saveHistory.js" type="text/javascript"></script>
</body>
</html>