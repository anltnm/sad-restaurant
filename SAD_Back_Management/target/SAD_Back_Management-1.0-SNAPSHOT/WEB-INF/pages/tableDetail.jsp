<%--
  Created by IntelliJ IDEA.
  User: AnLTNM-SE60906
  Date: 28/05/2015
  Time: 8:56 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<link href="<c:url value="/resources/lib/select2/css/select2.min.css" />" rel="stylesheet">
<div class="row">
    <div id="breadcrumb" class="col-xs-12">
        <ol class="breadcrumb pull-left">
            <li><a href="#">Dashboard</a></li>
            <li><a href="#">Table Detail</a></li>
        </ol>
    </div>
</div>
<div class="row">
    <div class="col-xs-12 col-sm-10 col-sm-offset-1">
        <div class="box">
            <div class="box-content">
                <c:set var="orderDetail" value="${orderDetail}"></c:set>

                <form id="foodForm" method="post" class="form-horizontal" action="close_table">
                    <fieldset style="border-bottom: 1px dashed #B6B6B6;">
                        <legend>Table order detail</legend>
                        <div class="form-group">
                            <label class="col-sm-3 control-label">Table name</label>

                            <div class="col-sm-5">
                                <input type="text" readonly class="form-control" name="tableName"
                                       value="${orderDetail.sadTable.name}"/>
                            </div>
                        </div>

                        <div class="form-group">
                            <label class="col-sm-3 control-label">Floor</label>

                            <div class="col-sm-5">
                                <input type="text" readonly class="form-control" name="floorName"
                                       value="${orderDetail.sadTable.floorName}"/>
                            </div>
                        </div>

                        <div class="form-group">
                            <label class="col-sm-3 control-label">Customer</label>

                            <div class="col-sm-5">
                                <input type="text" class="form-control" readonly
                                       name="customer" value="${orderDetail.cusQuantity}"/>
                            </div>
                        </div>

                        <div class="form-group">
                            <label class="col-sm-3 control-label">Order placed time: </label>

                            <div class="col-sm-5">
                                <fmt:formatDate var="date" pattern="dd/MM/yyyy HH:mm"
                                                value="${orderDetail.createdDate}"></fmt:formatDate>
                                <input type="text" class="form-control" readonly
                                       name="orderCreateDate" value="${date}"/>
                            </div>
                        </div>

                        <div class="form-group">
                            <label class="col-sm-3 control-label">Server</label>

                            <div class="col-sm-5">
                                <input type="text" class="form-control" readonly
                                       name="userDisplayName" value="${orderDetail.user.displayName}"/>
                            </div>
                        </div>

                        <c:set var="orderDetailList" value="${orderDetail.orderDetails}"></c:set>

                        <div class="row">
                            <div class="col-xs-12">
                                <div class="box">
                                    <div class="box-header">
                                        <div class="box-name">
                                            <i class="fa fa-usd"></i>
                                            <span>List food</span>
                                        </div>
                                    </div>
                                    <div class="box-content no-padding">
                                        <table class="table table-bordered table-striped table-hover table-heading"
                                               id="datatable-1">
                                            <thead>
                                            <tr>
                                                <th>No.</th>
                                                <th>Food Name</th>
                                                <th>Quantity</th>
                                                <th>Extra Food Group</th>
                                                <th>Sub-Total</th>
                                                <th></th>
                                            </tr>
                                            </thead>
                                            <tbody>
                                            <c:forEach begin="0" items="${orderDetailList}" var="orderDetailItem"
                                                       varStatus="index">
                                                <tr>
                                                    <td>${index.index +1 }</td>
                                                    <td>${orderDetailItem.food.name}</td>
                                                    <td>${orderDetailItem.quantity}</td>
                                                    <td>
                                                        <c:set var="totalExtra" value="0"></c:set>
                                                        <c:forEach items="${orderDetailItem.extraFoods}" var="extraFood">
                                                            <a>${extraFood.name}</a>
                                                            <a style="float:right;">${extraFood.price}</a>
                                                            <br/>
                                                        </c:forEach>
                                                    </td>
                                                    <td>
                                                        <fmt:formatNumber type="number" pattern="#,###"
                                                                          value="${orderDetailItem.foodPrice}">
                                                        </fmt:formatNumber>
                                                    </td>
                                                    <td>
                                                        <div class="btn-group">
                                                            <button type="button" class="btn btn-danger">Action</button>
                                                            <button type="button" class="btn btn-danger dropdown-toggle"
                                                                    data-toggle="dropdown">
                                                                <span class="caret"></span>
                                                                <span class="sr-only">Toggle Dropdown</span>
                                                            </button>
                                                            <ul class="dropdown-menu" role="menu">
                                                                <li>
                                                                    <a onclick="confirmDelete(${orderDetailItem.orDetailId})">
                                                                        Delete
                                                                    </a>
                                                                </li>
                                                            </ul>
                                                        </div>
                                                    </td>
                                                </tr>
                                            </c:forEach>

                                            </tbody>
                                            <tfoot>
                                            <tr>
                                                <th></th>
                                                <th></th>
                                                <th></th>
                                                <th>Total</th>
                                                <th>${orderDetail.totalOrder}</th>
                                                <th></th>
                                            </tr>
                                            </tfoot>
                                        </table>
                                    </div>
                                </div>
                            </div>
                        </div>
                        <div>
                            <div class="form-group">
                                <div class="col-sm-9 col-sm-offset-3">
                                    <button type="submit" class="btn btn-primary">Submit</button>
                                </div>
                            </div>
                        </div>
                    </fieldset>
                    <input type="hidden" value="${orderDetail.orderId}" name="id">
                </form>
            </div>
        </div>
    </div>
</div>
<script src="<c:url value="/resources/lib/jquery/jquery-1.11.3.min.js" />"></script>
<script src="<c:url value="/resources/lib/jquery/form/jquery.form.js" />"></script>
<script src="<c:url value="/resources/lib/jquery/validator/lib/jquery.validate.min.js" />"></script>
<script>
    var id = -1;
    callbackFunction = function () {
        $.ajax({
                    type: "POST",
                    data: {
                        "id": id
                    },
                    url: window.location.origin + "/sad-bom/dashboard/delete_order_detail",
                    async: false,
                    success: function (data) {
                        if (data.statusCode == 0) {
                            console.log(data);
                            window.location.reload();
                        } else if (data.statusCode == 4) {
                            $("#popupMessage").text("Delete food is failed !");
                            openPopup(false);
                        }
                    }
                }
        )
        console.log("call at listfood");
    }
    function confirmDelete(_id) {
        $("#popupMessage").text("Please confirm delete food?");
        openPopup(true);
        id = _id;
    }
</script>
