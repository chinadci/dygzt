<%@ page language="java" pageEncoding="UTF-8" %>
<%@ taglib prefix="spring" uri="../lib/spring.tld" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE HTML>
<html>
<head>
    <meta charset="utf-8"/>
    <title>调研工作台-调研待审批</title>
    <%@include file="inc/file.inc.jsp" %>
</head>
<body>
<div id="content">
    <%@include file="inc/header.inc.jsp" %>
    <div id="main">
        <div id="left">
            <%@include file="inc/menu.inc.jsp" %>
        </div>
        <div id="right">
            <div class="collapse-button"></div>
            <div class="mod-content wrap3 approvetable" >
                <div class="table-2" id="minTableContent">
                    <table id="table" >
                        <thead>
                        <tr>
                            <th style="width:80px">调研日期</th>
                            <th style="width:200px">调研目的</th>
                            <th style="width:100px">表格开始日期</th>
                            <th style="width:100px">表格结束日期</th>
                            <th style="width:100px">调研范围</th>
                            <th style="width:40%">调研需求</th>
                            <th style="width:90px">操作</th>
                        </tr>
                        </thead>
                        <tbody>
                        <c:forEach items="${list}" var="manual">
                            <tr>
                                <td>${manual.dyrq}</td>
                                <td>${manual.dymd}</td>
                                <td>${manual.ksrq}</td>
                                <td>${manual.jsrq}</td>
                                <td>${manual.dyfw}</td>
                                <td>${manual.dyxq}</td>
                                <td>
                                    <div class='cpt_menu_view' data-url='showManualResearch.do?researchid=${manual.btn}'
                                         title='查看'></div>
                                    <div class='cpt_ok' data-researchid='${manual.btn}' title='审批通过'></div>
                                    <div class='cpt_no' data-researchid='${manual.btn}' title='审批不通过'></div>
                                </td>
                            </tr>
                        </c:forEach>
                        </tbody>
                    </table>
                </div>
            </div>
        </div>
    </div>
</div>
</body>
<script type="text/javascript">
    //setRemainingHeight(".approvetable","#right");
</script>
<script src="resources/js/manual/approve.js"></script>
</html>
