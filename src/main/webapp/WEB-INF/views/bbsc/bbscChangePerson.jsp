<%@ page language="java" pageEncoding="UTF-8" %>
<%@ taglib prefix="spring" uri="../../lib/spring.tld" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE HTML>
<html>
<head>
    <meta charset="utf-8"/>
    <title>报表生成-管理法院人员</title>
    <%@include file="../inc/file.inc.jsp" %>
    <style type="text/css">
        #table input[type='text'] {
            width: 120px;
        }
    </style>
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

            <div class="mod-content wrap3 persontable">
                <div class="table-2">
                    <a class="btn1 datatablebtn" href="bbscAddPerson.do">新增</a>
                    <table id="table">
                        <thead>
                        <tr>
                            <th>序号</th>
                            <th>法院名称</th>
                            <th>用户名</th>
                            <th>姓名</th>
                            <th>操作</th>
                        </tr>
                        </thead>
                        <tbody>
                        </tbody>
                    </table>
                </div>
            </div>

        </div>

    </div>
</div>
</body>
<script type="text/javascript">
    setRemainingHeight(".persontable", "#right");
    var tableHeight = $(".persontable").height() - parseInt($(".table-2").css("margin-top")) - parseInt($(".table-2").css("margin-bottom")) - 125; //36(搜索框)+31(表头)+26(info)+32(分页)=125
    $("#table").DataTable({
        "bLengthChange": false,	//改变数据量
        "bSort": true,	//排序
        "bInfo": true,	//页脚信息
        "paging": true,	//分页
        "pagingType": "full_numbers",
        "aoColumnDefs": [{"bSortable": false, "aTargets": [4]}],	//第一列从0开始,序号为7列(第8列)的列不进行排序，别的列均可进行排序
        "pageLength": Math.floor(tableHeight / ($("th").outerHeight() + 5)),
        "bFilter": true,	//搜索
        "language": {
            "emptyTable": "无",
            "info": "共_TOTAL_条记录，当前第_PAGE_页，共_PAGES_页",
            "infoFiltered": "",
            "infoEmpty": "共0条记录",
            "loadingRecords": "正在加载，请稍候",
            "processing": "正在加载，请稍候",
            "paginate": {
                "first": "首页",
                "last": "尾页",
                "next": "下一页",
                "previous": "上一页",
            },
            "search": "搜索:",
            "searchPlaceholder": "请输入搜索条件",
            "zeroRecords": "无匹配",
        },
        "scrollY": tableHeight + 12,	//表头固定
        "scrollX": true,
        "scrollCollapse": true,
        "bDestroy": true,
        "bProcessing": true,	//以下为服务器分页
        "bServerSide": true,
        "sAjaxSource": "bbscChangePersonList.aj",
        "aoColumns": [
            {"mDataProp": "xh"},
            {"mDataProp": "fymc"},
            {"mDataProp": "yhm"},
            {"mDataProp": "name"},
            {"mDataProp": "btn"}
        ]
    });
</script>
<script src="resources/js/bbsc/bbscChangePerson.js" type="text/javascript"></script>
</html>
