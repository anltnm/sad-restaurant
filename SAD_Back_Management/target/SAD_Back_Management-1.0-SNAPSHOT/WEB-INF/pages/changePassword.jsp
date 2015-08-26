<%--
  Created by IntelliJ IDEA.
  User: AnLTNM-SE60906
  Date: 27/05/2015
  Time: 9:47 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring" %>
<div class="container-fluid">
    <div id="page-login" class="row">
        <div class="col-xs-12 col-md-4 col-md-offset-4 col-sm-6 col-sm-offset-3">
            <div class="text-right">
                <a href="page_register.html" class="txt-default">Need an account?</a>
            </div>
            <div class="box">
                <div class="box-content">
                    <div class="text-center">
                        <h3 class="page-header"><spring:message code="label.login.form.title"/></h3>
                        <c:if test="${error == true}">
                            <label style="color: #b8392d">${message}</label>
                        </c:if>
                    </div>
                    <form id="loginForm" method="post" action="changePassword">
                        <div class="form-group">
                            <label class="control-label">Current Password</label>
                            <input type="password" class="form-control" name="oldPassword"/>
                        </div>
                        <div class="form-group">
                            <label class="control-label">New Password</label>
                            <input type="password" id="newPassword" class="form-control" name="newPassword"/>
                        </div>
                        <div class="form-group">
                            <label class="control-label">Confirm Password</label>
                            <input type="password" id="confirmPassword" class="form-control" name="confirmPassword"/>
                        </div>
                        <div class="text-center">
                            <button type="submit" class="btn btn-primary">Submit</button>
                        </div>
                    </form>
                </div>
            </div>
        </div>
    </div>
</div>
<script src="<c:url value="/resources/lib/jquery/validator/lib/jquery.validate.min.js" />"></script>
<script>
    $(function () {
        jQuery.validator.addMethod("alphanumeric", function (value, element) {
            return this.optional(element) || /^\w+$/i.test(value);
        }, "Letters, numbers, and underscores only please");

        $("#loginForm").validate(
                {
                    onfocusout: function (element) {
                        $(element).valid();
                    },
                    rules: {
                        oldPassword: {
                            required: true,
                            rangelength: [8, 256],
                            alphanumeric: true
                        },
                        newPassword: {
                            required: true,
                            rangelength: [8, 256],
                            alphanumeric: true
                        },
                        confirmPassword: {
                            required: true,
                            rangelength: [8, 256],
                            alphanumeric: true,
                            equalTo: "#newPassword"
                        }
                    },
                    messages: {
                        username: {
                            required: "Password is not empty",
                            rangelength: "Password length from 8 to 256 character"
                        },
                        password: {
                            required: "Password is not empty",
                            rangelength: "Password length from 8 to 256 character"
                        },
                        confirmPassword: {
                            required: "Password is not empty",
                            rangelength: "Password length from 8 to 256 character",
                            equalTo: "Confirm password is not matched"
                        }
                    }
                }
        );
    });
</script>