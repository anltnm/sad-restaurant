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
            <li><a href="./listFood">List User</a></li>
        </ol>
    </div>
</div>
<div class="row">
    <div class="form-group has-warning has-feedback">
        <div class="col-sm-1">
            <a href="./addUserView" class="btn btn-primary btn-label-left">
                Add user
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
                    ${category == "name" ? 'selected="selected"' : ''}>Username
                    </option>
                    <option value="displayName"
                    ${category == "displayName" ? 'selected="selected"' : ''}>User display name
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
                        <th>Username</th>
                        <th>User display name</th>
                        <th></th>
                    </tr>
                    </thead>
                    <tbody>
                    <c:forEach begin="0" items="${listUser}" var="userItem" varStatus="index">
                        <tr>
                            <td>${index.index +1}</td>
                            <td>
                                <a class="ajax-link"
                                   href="./user_detail?id=${userItem.userId}"> ${userItem.userName}
                                </a>
                            </td>
                            <td>${userItem.displayName}</td>
                            <td>
                                <div class="btn-group">
                                    <button type="button" class="btn btn-danger">Action</button>
                                    <button type="button" class="btn btn-danger dropdown-toggle" data-toggle="dropdown">
                                        <span class="caret"></span>
                                        <span class="sr-only">Toggle Dropdown</span>
                                    </button>
                                    <ul class="dropdown-menu" role="menu">
                                        <li><a href="./updateUserView?id=${userItem.userId}">Update</a></li>
                                    </ul>
                                </div>
                            </td>
                        </tr>
                    </c:forEach>
                    </tbody>
                    <tfoot>
                    <tr>
                        <th>No.</th>
                        <th>Username</th>
                        <th>User display name</th>
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

</script>
