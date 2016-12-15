<%@ page language="java" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<div class="table-1">

    <h3>
        ${table.bbmc}
        <input type="hidden" name="bbmc" id="bbmc" value="${table.bbmc }"/>
        <%--
                <label>新收置信度 ${credit.XSCredit}</label>
                <label>新收置信度${credit.JCCredit}</label>
                <label>新收置信度${credit.YJCredit}</label>
                <label>新收置信度${credit.WJcredit}</label>--%>

        <span class="print-hidden btn-group">
		    <a class="table_print" href="javascript:void(0)">打印</a>
		    <a href="tableExport.aj?dytj=${table.dytj.replace('+','%2B') }">导出</a>
	    </span>
    </h3>

    <h3>
        <c:choose>
            <c:when test="${credit.XSCredit} == 0.0">
                <label class="credit">暂无新收置信度</label>
            </c:when>
            <c:otherwise>
                <label class="credit">
                    <fmt:formatNumber var="XSCredit" type="percent" maxFractionDigits="2" value="${credit.XSCredit}"/>
                    新收置信度: [${XSCredit}]
                </label>
            </c:otherwise>
        </c:choose>


        <c:choose>
            <c:when test="${credit.WJcredit} == 0.0">
                <label class="credit">暂无未结置信度</label>
            </c:when>
            <c:otherwise>
                <label class="credit">
                    <fmt:formatNumber var="WJcredit" type="percent" maxFractionDigits="2" value="${credit.WJcredit}"/>
                    未结置信度: [${WJcredit}]
                </label>
            </c:otherwise>
        </c:choose>

        <c:choose>
            <c:when test="${credit.YJCredit} == 0.0">
                <label class="credit"> 暂无已结置信度</label>
            </c:when>
            <c:otherwise>
                <label class="credit">
                    <fmt:formatNumber var="YJCredit" type="percent" maxFractionDigits="2" value="${credit.YJCredit}"/>
                    已结置信度: [${YJCredit}]
                </label>
            </c:otherwise>
        </c:choose>
        <c:choose>
            <c:when test="${credit.JCCredit} == 0.0">
                <label class="credit">暂无旧存信度</label>
            </c:when>
            <c:otherwise>
                <label class="credit">
                    <fmt:formatNumber var="JCCredit" type="percent" maxFractionDigits="2" value="${credit.JCCredit}"/>
                    旧存置信度: [${JCCredit}]
                </label>
            </c:otherwise>
        </c:choose>
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

        <c:forEach var="j" begin="${table.collevel }" end="${table.valueList.size()-1 }" step="1" varStatus="rowIndex">
            <tr>
                <c:forEach items="${table.valueList.get(j)}" var="cell" varStatus="cellIndex">
                    <td rowspan="${cell.rowspan}" colspan="${cell.colspan}"
                        <c:if test="${cellIndex.index<table.rowlevel}">class="rowheader"</c:if>>
                            <%--<!-- ${cell.value}-->--%>

                        <c:choose>
                            <%--如果该 cell 中的 sql 值，不为空，则把 sql 输出到 a 标签中，再赋值；否则直接赋值--%>
                            <c:when test="${cell.sql!=null}">
                                <a href="researchlb.do?base=${cell.base}&sql=${cell.sql}&fydm=${cell.fydm}"
                                   target="_blank">
                                        ${cell.value}
                                </a>
                                <%--该值的显示方式要判断--%>
                                <c:choose>
                                    <c:when test="${cell.samePeriodLastYearValue != null}">
                                        <%--   <label class="samePeriodLastYear" id="samePeriodLastYear">
                                               [${cell.samePeriodLastYearValue}]
                                               <c:set var="thisValue" value="${cell.value}" />
                                               <c:set var="lastValue" value="${cell.samePeriodLastYearValue}" />
                                               <fmt:formatNumber var="intVar3" type="percent" maxFractionDigits="2"  value="${(thisValue-lastValue)/lastValue}" />
                                               [${intVar3}]
                                           </label>
   --%>


                                        <label class="samePeriodLastYear" id="samePeriodLastYear">
                                            [${cell.samePeriodLastYearValue}]
                                            <c:choose>
                                                <c:when test="${cell.value == 0 && cell.samePeriodLastYearValue ==0}">
                                                    <%--如果分子分母都为0那么不出输出同比--%>
                                                </c:when>
                                                <c:otherwise>
                                                    <c:set var="thisValue" value="${cell.value}"/>
                                                    <c:set var="lastValue" value="${cell.samePeriodLastYearValue}"/>
                                                    <fmt:formatNumber var="intVar3" type="percent"
                                                                      maxFractionDigits="2"
                                                                      value="${(thisValue-lastValue)/lastValue}"/>
                                                    [${intVar3}]
                                                </c:otherwise>

                                            </c:choose>

                                        </label>


                                        <%--<fmt:parseNumber var="intVar4" integerOnly="true" value="${intVar1/intVar2}" /> //1--%>

                                    </c:when>
                                </c:choose>
                            </c:when>

                            <c:otherwise>
                                ${cell.value}
                                <%--该值的显示方式要判断--%>
                                <c:choose>
                                    <c:when test="${cell.samePeriodLastYearValue != null}">
                                        <%-- <label class="samePeriodLastYear" id="samePeriodLastYear">
                                             [${cell.samePeriodLastYearValue}]
                                             <c:set var="thisValue" value="${cell.value}" />
                                             <c:set var="lastValue" value="${cell.samePeriodLastYearValue}" />
                                             <fmt:formatNumber var="intVar3" type="percent" maxFractionDigits="2"  value="${(thisValue-lastValue)/lastValue}" />
                                             [${intVar3}]
                                         </label>--%>

                                        <label class="samePeriodLastYear" id="samePeriodLastYear">
                                            [${cell.samePeriodLastYearValue}]
                                            <c:choose>
                                                <c:when test="${cell.value == 0 && cell.samePeriodLastYearValue ==0}">
                                                    <%--如果分子分母都为0那么不出输出同比--%>
                                                </c:when>
                                                <c:otherwise>
                                                    <c:set var="thisValue" value="${cell.value}"/>
                                                    <c:set var="lastValue" value="${cell.samePeriodLastYearValue}"/>
                                                    <fmt:formatNumber var="intVar3" type="percent"
                                                                      maxFractionDigits="2"
                                                                      value="${(thisValue-lastValue)/lastValue}"/>
                                                    [${intVar3}]
                                                </c:otherwise>

                                            </c:choose>

                                        </label>

                                    </c:when>
                                </c:choose>
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
