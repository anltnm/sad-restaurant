<%--
  Created by IntelliJ IDEA.
  User: AnLTNM-SE60906
  Date: 28/05/2015
  Time: 8:56 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<link href="<c:url value="/resources/lib/select2/css/select2.min.css" />" rel="stylesheet">
<link href="<c:url value="/resources/lib/font-awesome-4.4.0/css/font-awesome.min.css" />" rel="stylesheet">
<div class="row">
    <div id="breadcrumb" class="col-xs-12">
        <%--<a href="#" class="show-sidebar">--%>
        <%--<i class="fa fa-bars"></i>--%>
        <%--</a>--%>
        <%--<ol class="breadcrumb pull-left">--%>
        <%--<li><a href="#">Dashboard</a></li>--%>
        <%--<li><a href="./listFood">Food list</a></li>--%>
        <%--<li><a href="#">Add new food</a></li>--%>
        <%--</ol>--%>
        <%--<div id="social" class="pull-right">--%>
        <%--<a href="#"><i class="fa fa-google-plus"></i></a>--%>
        <%--<a href="#"><i class="fa fa-facebook"></i></a>--%>
        <%--<a href="#"><i class="fa fa-twitter"></i></a>--%>
        <%--<a href="#"><i class="fa fa-linkedin"></i></a>--%>
        <%--<a href="#"><i class="fa fa-youtube"></i></a>--%>
        <%--</div>--%>
    </div>
</div>
<div class="row">
    <div class="col-xs-12 col-sm-8 col-sm-offset-2">
        <div class="box">
            <div class="box-content">
                <c:set var="message" value="${message}"></c:set>
                <c:set var="url" value="${url}"></c:set>
                    <p class="page-header">Submit error</p>
                    <p>
                    <blockquote>
                        <p>${message}</p>
                        <footer> <a href="${url}">Go to previous page !</a>></footer>
                    </blockquote>
                    </p>
            </div>
        </div>
    </div>
</div>