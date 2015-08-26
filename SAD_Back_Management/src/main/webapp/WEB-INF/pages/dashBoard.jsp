<%--
  Created by IntelliJ IDEA.
  User: Hiep
  Date: 6/1/2015
  Time: 3:14 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<link href="<c:url value="/resources/js/floorMenu/style.css" />" rel="stylesheet">

<div id="menu">
    <ul id="nav">

        <li><a class="Floor1" href="Floor-1" title="Tang 1">Floor 1</a></li>
        <li><a class="Floor2" href="Floor-2" title="Tang 2">Floor 2</a></li>
        <%--<li><a class="Floor3" href="#" title="Tang 3">Floor 3</a></li>--%>
    </ul>
</div>

<div id="viewFloor">
    <!--TABLE 1-->
    <c:set var="tableList" value="${listTable}"></c:set>
    <c:set var="lastUpdate" value="${lastUpdate}"></c:set>
    <c:forEach begin="0" var="table" items="${tableList}">
        <c:choose>
            <c:when test="${table.status == 1}">
                <c:set var="color" value="#6FC86B"></c:set>
                <c:set var="message" value="Openning"></c:set>
            </c:when>
            <c:when test="${table.status == 4}">
                <c:set var="color" value="#D36964"></c:set>
                <c:set var="message" value="Check out"></c:set>
            </c:when>
            <c:otherwise>
                <c:set var="color" value="transparent"></c:set>
                <c:set var="message" value="Closed"></c:set>
            </c:otherwise>
        </c:choose>

        <%--<div id="${table.id}" title="Click vào để xem chi tiết" class="col-xs-12 col-sm-6 col-md-4 ow-server"--%>
        <div id="table${table.id}" title="Click vào để xem chi tiết" class=" col-sm-2 ow-server"
             style="background-color: ${color}" onclick="openTable('${table.id}')">
            <h4 class="page-header text-right">${table.name}</h4>

            <div class="row ow-server-bottom">
                <div class="col-sm-8">
                    <div class="row"><span>Status: <span id="message${table.id}">${message}</span> </span></div>
                    <div class="row"></div>
                    <div class="row"></div>
                </div>
            </div>
        </div>
    </c:forEach>


</div>


<!-- SCRIPT DASHBOARD-->


<script src="<c:url value="/resources/lib/jquery/jquery-1.11.3.min.js" />"></script>
<script src="<c:url value="/resources/lib/select2/js/select2.full.min.js" />"></script>
<script src="<c:url value="/resources/lib/datatable/js/jquery.dataTables.min.js" />"></script>
<script>
    function openTable(id) {
        $.ajax({
                    type: "GET",
                    data: {
                        "id": id
                    },
                    url: window.location.origin + "/sad-bom/dashboard/get_table_status",
                    async: true,
                    success: function (data) {
                        console.log(data);
                        if (data.statusCode == 0) {
                            var table = data.data;
                            if (table.status == 1 || table.status == 4) {
                                location.href = "../dashboard/tableDetail?id=" + id;
                            } else {
                                window.location.reload();
                            }
                        } else if (data.statusCode == 4) {

                        }
                    }
                }
        );
    }
    var lastUpdateLocal = ${lastUpdate};
    $(function () {
        var intervalId = setInterval(function () {
            $.ajax({
                        type: "GET",
                        data: {
                            "lastUpdated": lastUpdateLocal
                        },
                        url: window.location.origin + "/sad-bom/dashboard/getLastUpdateTable",
                        async: false,
                        success: function (data) {
                            console.log(data);
                            var listOrder = data;
                            if (listOrder.length != 0) {
                                lastUpdateLocal = listOrder[listOrder.length - 1].lastUpdate;
                                console.log(lastUpdateLocal);
                                for (var i = 0; i < listOrder.length; i++) {
                                    var order = listOrder[i];
                                    var color = "";
                                    var message = "";
                                    if (order.status == 1) {
                                        color = "#6FC86B";
                                        message = "Opening";
                                        $("#table" + order.id).addClass("blink");
                                    } else if (order.status == 4) {
                                        color = "#D36964";
                                        message = "Check out";
                                    } else {
                                        color = "transparent";
                                        message = "Closed";
                                    }
                                    $("#table" + order.id).css("background-color", color);
                                    $("#message" + order.id).text(message);
                                }
                            }
                        }
                    }
            )
        }, 5000);
        console.log("Interval id: " + intervalId);
    })
</script>

<!-- SCRIP CHUYEN FLOOR-->

<script>
    $(document).ready(function () {
        $(".Floor2").click(function (e) {

            $("#viewFloor").load($(".Floor2").attr("href"))
            e.preventDefault()

        });

    })
</script>

<script>
    $(document).ready(function () {
        $(".Floor1").click(function (e) {

            $("#viewFloor").load($(".Floor1").attr("href"))
            e.preventDefault()
        });
    })
</script>

