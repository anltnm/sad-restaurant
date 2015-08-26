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
            <li><a href="./userListView">User list</a></li>
            <li><a href="#">User Management</a></li>
        </ol>
    </div>
</div>
<div class="row">
    <div class="col-xs-12 col-sm-8 col-sm-offset-2">
        <div class="box">
            <div class="box-content">
                <c:set var="userDetail" value="${useDetail}" scope="request"/>
                <c:set var="viewMode" value="${viewMode}" scope="request"/>

                <c:choose>
                    <c:when test="${viewMode =='update' }">
                        <c:set var="title" value="Update Staff Information" scope="request"/>
                        <c:set var="method" value="update_user" scope="request"/>
                    </c:when>
                    <c:when test="${viewMode =='add' }">
                        <c:set var="title" value="Add New Staff" scope="request"/>
                        <c:set var="method" value="add_user" scope="request"/>
                    </c:when>
                    <c:otherwise>
                        <c:set var="title" value="View Staff" scope="request"/>
                        <c:set var="method" value="add_user" scope="request"/>
                    </c:otherwise>
                </c:choose>
                <form id="userForm" method="post" action="${method}" class="form-horizontal">
                    <fieldset style="border-bottom: 1px dashed #B6B6B6;">
                        <legend>${title}</legend>
                        <div class="form-group">
                            <label class="col-sm-3 control-label">UserName</label>

                            <div class="col-sm-5">
                                <c:if test="${viewMode != 'add'}">
                                    <input type="text" readonly class="form-control" name="userName"
                                           value="${userDetail.userName}"/>
                                </c:if>
                                <c:if test="${viewMode == 'add'}">
                                    <input type="text" class="form-control" name="userName"
                                           value="${userDetail.userName}"/>
                                </c:if>
                                <c:if test="${userNameDuplicated == true}">
                                    <label for="userName" class="error">User name is duplicated !</label>
                                </c:if>
                            </div>
                        </div>
                        <div class="form-group">
                            <label class="col-sm-3 control-label">Password</label>

                            <div class="col-sm-5">
                                <c:if test="${viewMode == 'view'}">
                                    <input type="password" readonly class="form-control" name="password"
                                           value="${userDetail.password}"/>
                                </c:if>
                                <c:if test="${viewMode != 'view'}">
                                    <input type="password" class="form-control" name="password"
                                           value="${userDetail.password}"/>
                                </c:if>
                            </div>
                        </div>
                        <div class="form-group">
                            <label class="col-sm-3 control-label">User display name</label>

                            <div class="col-sm-5">
                                <c:if test="${viewMode == 'view'}">
                                    <input type="text" class="form-control" readonly
                                           name="displayName" value="${userDetail.displayName}"/>
                                </c:if>
                                <c:if test="${viewMode != 'view'}">
                                    <input type="text" class="form-control"
                                           name="displayName" value="${userDetail.displayName}"/>
                                </c:if>
                            </div>
                        </div>


                        <div>
                            <div class="form-group">
                                <c:if test="${viewMode == 'view'}">
                                    <div class="col-sm-9 col-sm-offset-3">
                                        <button type="submit" disabled class="btn btn-primary">Submit</button>
                                    </div>
                                </c:if>
                                <c:if test="${viewMode != 'view'}">
                                    <div class="col-sm-9 col-sm-offset-3">
                                        <button type="submit" class="btn btn-primary">Submit</button>
                                    </div>
                                </c:if>
                            </div>
                        </div>
                    </fieldset>
                    <input type="hidden" value="${userDetail.userId}" name="id">
                </form>
            </div>
        </div>
    </div>
</div>
<script src="<c:url value="/resources/lib/jquery/jquery-1.11.3.min.js" />"></script>
<script src="<c:url value="/resources/lib/jquery/form/jquery.form.js" />"></script>
<script src="<c:url value="/resources/lib/jquery/validator/lib/jquery.validate.min.js" />"></script>
<script src="<c:url value="/resources/lib/select2/js/select2.full.min.js" />"></script>

<script>
    $(function () {
        jQuery.validator.addMethod("alphanumeric", function (value, element) {
            return this.optional(element) || /^\w+$/i.test(value);
        }, "Letters, numbers, and underscores only please");

        $("#userForm").validate(
                {
                    onfocusout: function (element) {
                        $(element).valid();
                    },
                    rules: {
                        userName: {
                            required: true,
                            rangelength: [8, 256],
                            alphanumeric: true
                        },
                        password: {
                            required: true,
                            rangelength: [8, 256],
                            alphanumeric: true
                        },
                        displayName: {
                            required: true,
                            rangelength: [1, 256],
                            alphanumeric: true
                        }

                    },
                    messages: {
                        userName: {
                            required: "Username is not empty",
                            rangelength: "Username length from 8 to 256 character"
                        },
                        password: {
                            required: "Password is not empty",
                            rangelength: "Password length from 8 to 256 character"
                        },
                        displayName: {
                            required: "Display Name is not empty",
                            rangelength: "Display Name length from 1 to 256 character"
                        }
                    }
                }
        );
    });
</script>
