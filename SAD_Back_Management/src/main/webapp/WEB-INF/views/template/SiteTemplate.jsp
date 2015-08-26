<%--
  Created by IntelliJ IDEA.
  User: AnLTNM-SE60906
  Date: 27/05/2015
  Time: 5:07 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles" %>
<html>
<head>
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
    <link href="<c:url value="/resources/lib/bootstrap/css/bootstrap.css" />" rel="stylesheet">
    <link href="<c:url value="/resources/css/sad.css" />" rel="stylesheet">
    <link href="<c:url value="/resources/css/style_v2.css" />" rel="stylesheet">
    <link href="<c:url value="/resources/lib/font-awesome-4.4.0/css/font-awesome.min.css" />" rel="stylesheet">
    <script src="<c:url value="/resources/lib/datatable/js/jquery.js" />"></script>
    <script src="<c:url value="/resources/lib/jquery/jquery-1.11.3.min.js" />"></script>
    <script src="<c:url value="/resources/lib/bootstrap/js/bootstrap.min.js" />"></script>
    <script src="<c:url value="/resources/lib/jquery/datetimeformat/jquery.formatDateTime.js" />"></script>

</head>
<body>
<tiles:insertAttribute name="header"/>
<tiles:insertAttribute name ="popup"/>
<div id="main" class="container-fluid">
    <div class="row">
        <div id="sidebar-left" class="col-xs-2 col-sm-2">
            <tiles:insertAttribute name="menu"/>
        </div>
        <div id="content" class="col-xs-12 col-sm-10">
            <tiles:insertAttribute name="body"/>
        </div>
        <tiles:insertAttribute name="footer"/>
    </div>
</div>

</body>
</html>
