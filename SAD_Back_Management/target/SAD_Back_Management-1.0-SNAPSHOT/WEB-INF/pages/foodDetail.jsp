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
            <li><a href="./listFood">Food list</a></li>
            <li><a href="#">Food Management</a></li>
        </ol>
    </div>
</div>
<div class="row">
    <div class="col-xs-12 col-sm-8 col-sm-offset-2">
        <div class="box">
            <div class="box-content">
                <c:set var="extraFoodGroupDetail" value="${foodDetail}" scope="request"/>
                <c:set var="viewMode" value="${viewMode}" scope="request"/>
                <c:set var="category" value="${category}" scope="request"/>
                <c:set var="extraFoodGroup" value="${extraFoodGroup}" scope="request"/>

                <c:choose>
                    <c:when test="${viewMode =='update' }">
                        <c:set var="title" value="Update Food" scope="request"/>
                        <c:set var="method" value="update_food" scope="request"/>
                    </c:when>
                    <c:when test="${viewMode =='add' }">
                        <c:set var="title" value="Add New Food" scope="request"/>
                        <c:set var="method" value="add_food" scope="request"/>
                    </c:when>
                    <c:otherwise>
                        <c:set var="title" value="View Food" scope="request"/>
                        <c:set var="method" value="add_food" scope="request"/>
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
                                    <input type="text" readonly class="form-control" name="foodNatame"
                                           value="${extraFoodGroupDetail.name}"/>
                                </c:if>
                                <c:if test="${viewMode != 'view'}">
                                    <input type="text" class="form-control" name="foodName"
                                           value="${extraFoodGroupDetail.name}"/>
                                </c:if>
                            </div>
                        </div>

                        <div class="form-group">
                            <label class="col-sm-3 control-label">Price</label>
                            <fmt:formatNumber var="formatFoodPrice" type="number" pattern="####"
                                              value="${extraFoodGroupDetail.price}"></fmt:formatNumber>
                            <div class="col-sm-5">
                                <c:if test="${viewMode == 'view'}">
                                    <input type="text" class="form-control" readonly
                                           name="foodPrice" value="${formatFoodPrice}"/>
                                </c:if>
                                <c:if test="${viewMode != 'view'}">
                                    <input type="text" class="form-control"
                                           name="foodPrice" value="${formatFoodPrice}"/>
                                </c:if>
                            </div>
                            <label class="col-sm-3 control-label" style="text-align: left">Vnd</label>
                        </div>

                        <div class="form-group">
                            <label class="col-sm-3 control-label">Category</label>

                            <div class="col-sm-5">
                                <c:if test="${viewMode == 'view'}">
                                    <select class="form-control" disabled id="foodCategory" name="foodCategory">
                                        <option selected="true"
                                                value="${extraFoodGroupDetail.category.id}">${extraFoodGroupDetail.category.name}</option>
                                    </select>
                                </c:if>
                                <c:if test="${viewMode != 'view'}">
                                    <select class="form-control" id="foodCategory" name="foodCategory">
                                        <c:forEach var="cat" items="${category}">
                                            <option value="${cat.id}"
                                                ${cat.id == extraFoodGroupDetail.category.id ? 'selected="selected"' : ''}>
                                                    ${cat.name}</option>
                                        </c:forEach>
                                    </select>
                                </c:if>
                            </div>
                        </div>
                        <div class="form-group">
                            <label class="col-sm-3 control-label">Extra Food Group</label>

                            <div class="col-sm-5">
                                <c:if test="${viewMode == 'view'}">
                                    <c:forEach var="extraFood" items="${extraFoodGroupDetail.extraFoodGroupList}"
                                               varStatus="index">
                                        <div class="checkbox">
                                            <label>
                                                <input type="checkbox" checked="true" name="extraFood[]"
                                                       value="${extraFood.id}"> ${extraFood.name}
                                                <i class="fa fa-square-o"></i>
                                            </label>
                                        </div>
                                    </c:forEach>
                                </c:if>
                                <c:if test="${viewMode != 'view'}">
                                    <%--<c:forEach var="extraFood" items="${foodDetail.extraFoodGroupList}"--%>
                                    <c:forEach var="extraFoodItem" items="${extraFoodGroup}"
                                               varStatus="index">
                                        <c:set var="found" value="false" scope="request"/>
                                        <c:forEach var="extraFood" items="${extraFoodGroupDetail.extraFoodGroupList}"
                                                   varStatus="index2">
                                            <c:if test="${extraFoodItem.id == extraFood.id}">
                                                <c:set var="found" value="true" scope="request"/>
                                            </c:if>
                                        </c:forEach>
                                        <div class="checkbox">
                                            <label>
                                                <input type="checkbox" name="extraFood[]"
                                                       value="${extraFoodItem.id}"
                                                    ${found == "true" ? 'checked="checked"' : ''}/>
                                                    ${extraFoodItem.name}
                                                <i class="fa fa-square-o"></i>
                                            </label>
                                        </div>
                                    </c:forEach>
                                    <input type="hidden" value="-1" name="extraFood[]"/>
                                </c:if>
                            </div>
                        </div>
                        <div class="form-group">
                            <label class="col-sm-3 control-label">Ingredient</label>
                            <c:if test="${viewMode == 'view'}">
                                <div class="col-sm-5">
                                        <textarea
                                                class="form-control" rows="3" readonly="readonly"
                                                name="foodIngredient">${extraFoodGroupDetail.ingredient}</textarea>
                                </div>
                            </c:if>
                            <c:if test="${viewMode != 'view'}">
                                <div class="col-sm-5">
                                    <textarea class="form-control" rows="3"
                                              name="foodIngredient">${extraFoodGroupDetail.ingredient}</textarea>
                                </div>
                            </c:if>
                        </div>
                        <div class="form-group">
                            <label class="col-sm-3 control-label">Image</label>

                            <div class="col-sm-5">
                                <img class="img-rounded"
                                     width="270" src="${extraFoodGroupDetail.imageUrl}"
                                     onerror="this.src='/sad-bom/resources/img/img_not_found.gif'" alt="">
                            </div>
                        </div>
                        <div class="form-group">

                            <label class="col-sm-3 control-label">Upload image</label>

                            <div class="col-sm-5">
                                File to upload:
                                <c:if test="${viewMode != 'view'}">
                                    <input type="file" type="file" name="file" accept="image/jpeg, image/png"/>
                                </c:if>
                                <c:if test="${viewMode == 'view'}">
                                    <input type="file" disabled="true" type="file" name="file"/>
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
                    <input type="hidden" value="${extraFoodGroupDetail.id}" name="id">
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
