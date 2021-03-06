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
            <li><a href="./listExtraFood">Extra food list</a></li>
            <li><a href="#">Extra food  Management</a></li>
        </ol>
    </div>
</div>
<div class="row">
    <div class="col-xs-12 col-sm-8 col-sm-offset-2">
        <div class="box">
            <div class="box-content">
                <c:set var="extraFoodDetail" value="${extraFoodDetail}" scope="request"/>
                <c:set var="viewMode" value="${viewMode}" scope="request"/>
                <c:set var="extraFoodGroup" value="${extraFoodGroup}" scope="request"/>

                <c:choose>
                    <c:when test="${viewMode =='update' }">
                        <c:set var="title" value="Update Extra Food" scope="request"/>
                        <c:set var="method" value="update_extra_food" scope="request"/>
                    </c:when>
                    <c:when test="${viewMode =='add' }">
                        <c:set var="title" value="Add New Extra Food" scope="request"/>
                        <c:set var="method" value="add_extra_food" scope="request"/>
                    </c:when>
                    <c:otherwise>
                        <c:set var="title" value="View Extra Food" scope="request"/>
                        <c:set var="method" value="add_extra_food" scope="request"/>
                    </c:otherwise>
                </c:choose>
                <form id="foodForm" method="post" action="${method}" class="form-horizontal"
                      enctype="multipart/form-data">
                    <fieldset style="border-bottom: 1px dashed #B6B6B6;">
                        <legend>${title}</legend>
                        <div class="form-group">
                            <label class="col-sm-3 control-label">Name</label>

                            <div class="col-sm-5">
                                <c:if test="${viewMode == 'view'}">
                                    <input type="text" readonly class="form-control" name="extraFoodName"
                                           value="${extraFoodDetail.name}"/>
                                </c:if>
                                <c:if test="${viewMode != 'view'}">
                                    <input type="text" class="form-control" name="extraFoodName"
                                           value="${extraFoodDetail.name}"/>
                                </c:if>
                            </div>
                        </div>

                        <div class="form-group">
                            <label class="col-sm-3 control-label">Price</label>
                            <fmt:formatNumber var="formatFoodPrice" type="number" pattern="####"
                                              value="${extraFoodDetail.price}"></fmt:formatNumber>
                            <div class="col-sm-5">
                                <c:if test="${viewMode == 'view'}">
                                    <input type="text" class="form-control" readonly
                                           name="extraFoodPrice" value="${formatFoodPrice}"/>
                                </c:if>
                                <c:if test="${viewMode != 'view'}">
                                    <input type="text" class="form-control"
                                           name="extraFoodPrice" value="${formatFoodPrice}"/>
                                </c:if>
                            </div>
                            <label class="col-sm-3 control-label" style="text-align: left">[vnd]</label>
                        </div>

                        <div class="form-group">
                            <label class="col-sm-3 control-label">Extra food group</label>

                            <div class="col-sm-5">
                                <c:if test="${viewMode == 'view'}">
                                    <select class="form-control" disabled name="extraFoodGroup">
                                        <option selected="true"
                                                value="${extraFoodDetail.groupId}">${extraFoodDetail.groupName}</option>
                                    </select>
                                </c:if>
                                <c:if test="${viewMode != 'view'}">
                                    <select class="form-control" name="extraFoodGroup">
                                        <c:forEach var="extraFoodGroupItem" items="${extraFoodGroup}">
                                            <option value="${extraFoodGroupItem.id}"
                                                ${extraFoodGroupItem.id == extraFoodDetail.groupId ? 'selected="selected"' : ''}>
                                                    ${extraFoodGroupItem.name}</option>
                                        </c:forEach>
                                    </select>
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
                    <input type="hidden" value="${extraFoodDetail.id}" name="id">
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
        $("#foodForm").validate(
                {
                    onfocusout: function (element) {
                        $(element).valid();
                    },
                    rules: {
                        foodName: {
                            required: true,
                            rangelength: [2, 256]
                        },
                        foodPrice: {
                            required: true,
                            rangelength: [4, 10],
                            range: [1000, 1000000000]
                        },
                        foodCategory: {
                            required: true
                        },
                        foodIngredient: {
                            rangelength: [0, 256]
                        }
                    },
                    messages: {
                        foodName: {
                            required: "Food name is not empty",
                            rangelength: "Food name length from 2 to 256 character"
                        },
                        foodPrice: {
                            required: "Food price is not empty",
                            rangelength: "Food name length from 1000[vnd] to 1,000,000,000[vnd]",
                            range: "Food name length from 1000[vnd] to 1,000,000,000[vnd] "
                        },
                        foodCategory: {
                            required: "Food category is not empty"
                        },
                        foodName: {
                            rangelength: "Food ingredient length from 2 to 256 character"
                        }
                    }
                }
        );
    });
</script>
