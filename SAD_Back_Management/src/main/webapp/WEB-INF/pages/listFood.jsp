<%--
  Created by IntelliJ IDEA.
  User: AnLTNM-SE60906
  Date: 27/05/2015
  Time: 8:59 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<link href="<c:url value="/resources/lib/datatable/css/jquery.dataTables.min.css" />" rel="stylesheet">
<link href="<c:url value="/resources/lib/datatable/css/jquery.dataTables_themeroller.css" />" rel="stylesheet">
<link href="<c:url value="/resources/lib/select2/css/select2.min.css" />" rel="stylesheet">
<div class="row">
    <div id="breadcrumb" class="col-xs-12">
        <ol class="breadcrumb pull-left">
            <li><a href="#">Dashboard</a></li>
            <li><a href="./listFood">List Food</a></li>
        </ol>
    </div>
</div>
<div class="row">
    <div class="form-group has-warning has-feedback">
        <div class="col-sm-1">
            <a href="./addFodView" class="btn btn-primary btn-label-left">
                Add dish
            </a>
        </div>

        <form method="get" id="searchForm" action="search">
            <label class="col-sm-1 control-label">Keyword</label>

            <div class="col-sm-4">
                <input type="text" name="keyword" class="form-control" value="${keyword}" placeholder="Please type ...">
            </div>
            <label class="col-sm-1 control-label">Search by</label>

            <div class="col-sm-4">
                <select class="form-control placeholder" name="category" style="width: 100%">
                    <option value="name"
                    ${category == "name" ? 'selected="selected"' : ''}>Food Name
                    </option>
                    <option value="cat"
                    ${category == "cat" ? 'selected="selected"' : ''}>Food Category
                    </option>
                </select>
            </div>
            <div class="col-sm-1">
                <button type="submit" class="btn btn-primary btn-label-left">Search</button>
            </div>
        </form>
    </div>
</div>

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
                        <th>Name</th>
                        <th>Category</th>
                        <th>Extra Food Group</th>
                        <th>Price</th>
                        <th></th>
                    </tr>
                    </thead>
                    <tbody>
                    <c:forEach begin="0" items="${listFood}" var="extraFoodItem" varStatus="index">
                        <tr>
                            <td>${index.index +1}</td>
                            <td><img class="img-rounded" width="70" src="${extraFoodItem.imageUrl}"
                                     onerror="this.src='/sad-bom/resources/img/img_not_found.gif'" alt="">
                                <a class="ajax-link"
                                   href="./food_detail?id=${extraFoodItem.id}"> ${extraFoodItem.name}
                                </a>
                            </td>
                            <td>${extraFoodItem.category.name}</td>
                            <td>
                                <c:forEach items="${extraFoodItem.extraFoodGroupList}" var="extraFood">
                                    <%--TODO implement click here to go to extra food--%>
                                    <a href="../extraFood/extraGroupDetail?id=${extraFood.id}">${extraFood.name}</a>
                                    <br/>
                                </c:forEach>
                            </td>
                            <td><fmt:formatNumber type="number" pattern="#,###" value="${extraFoodItem.price}"></fmt:formatNumber></td>
                            <td>
                                <div class="btn-group">
                                    <button type="button" class="btn btn-danger">Action</button>
                                    <button type="button" class="btn btn-danger dropdown-toggle" data-toggle="dropdown">
                                        <span class="caret"></span>
                                        <span class="sr-only">Toggle Dropdown</span>
                                    </button>
                                    <ul class="dropdown-menu" role="menu">
                                        <li><a href="./updateFoodView?id=${extraFoodItem.id}">Update</a></li>
                                        <li><a onclick="confirmDelete(${extraFoodItem.id})">Delete</a></li>
                                    </ul>
                                </div>
                            </td>
                        </tr>
                    </c:forEach>
                    </tbody>
                    <tfoot>
                    <tr>
                        <th>No.</th>
                        <th>Name</th>
                        <th>Category</th>
                        <th>Extra Food Group</th>
                        <th>Price</th>
                        <th></th>
                    </tr>
                    </tfoot>
                </table>
            </div>
        </div>
    </div>
</div>
<script src="<c:url value="/resources/lib/jquery/jquery-1.11.3.min.js" />"></script>
<script src="<c:url value="/resources/lib/select2/js/select2.full.min.js" />"></script>
<script src="<c:url value="/resources/lib/datatable/js/jquery.dataTables.min.js" />"></script>
<script src="<c:url value="/resources/lib/jquery/validator/lib/jquery.validate.min.js" />"></script>
<script>
    var id = -1;
    $(function () {
        $("#searchForm").validate({
            rules: {
                keyword: {
                    required: true,
                    rangelength: [1, 256]
                }
            },
            messages: {
                keyword: {
                    required: "Keyword is not empty",
                    rangelength: "Keyword length from 2 to 256 character"
                }
            }
        })
    });
    callbackFunction = function () {
        $.ajax({
                    type: "POST",
                    data: {
                        "productId": id
                    },
                    url: window.location.origin + "/sad-bom/food/delete_food",
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
        $("#popupMessage").text("Please confirm delete food ?")
        openPopup(true);
        id = _id;
    }
</script>
