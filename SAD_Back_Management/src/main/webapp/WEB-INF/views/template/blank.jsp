<%--
  Created by IntelliJ IDEA.
  User: Hiep
  Date: 6/2/2015
  Time: 10:43 AM
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
  <link href="<c:url value="/resources/css/style_v2.css" />" rel="stylesheet">
  <script src="<c:url value="/resources/lib/datatable/js/jquery.js" />"></script>
  <script src="<c:url value="/resources/lib/jquery/jquery-1.11.3.min.js" />"></script>
  <script src="<c:url value="/resources/lib/bootstrap/js/bootstrap.min.js" />"></script>

</head>
<body style="background-color: #FBFBF0">
    <tiles:insertAttribute name="body"/>
</body>
</html>
