<%--
  Created by IntelliJ IDEA.
  User: Hiep
  Date: 6/1/2015
  Time: 3:14 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<link href="<c:url value="/resources/js/floorMenu/style.css" />" rel="stylesheet">


<!--TABLE 10-->
<div id="table10" title="Click vào để xem chi tiết" class="col-xs-12 col-sm-6 col-md-4 ow-server">
  <h4 class="page-header text-right">Table 10</h4>
  <div class="row ow-server-bottom">
    <div class="col-sm-8">
      <div class="row"><span id="1">Trạng thái: <span >Đã đặt</span> </span></div>
      <div class="row"></div>
      <div class="row"></div>
    </div>
  </div>
</div>
<!--TABLE 11-->
<div id="table11" title="Click vào để xem chi tiết" class="col-xs-12 col-sm-6 col-md-4 ow-server">
  <h4 class="page-header text-right">Table 11</h4>
  <div class="row ow-server-bottom">
    <div class="col-sm-8">
      <div class="row">Trạng thái: Đã đặt</div>
      <div class="row"></div>
      <div class="row"></div>
    </div>
  </div>
</div>
<!--TABLE 12-->
<div id="table12" title="Click vào để xem chi tiết" class="col-xs-12 col-sm-6 col-md-4 ow-server">
  <h4 class="page-header text-right">Table 12</h4>
  <div class="row ow-server-bottom">
    <div class="col-sm-8">
      <div class="row">Trạng thái: Đã đặt</div>
      <div class="row"></div>
      <div class="row"></div>
    </div>
  </div>
</div>
<!--TABLE 13-->
<div id="table13" title="Click vào để xem chi tiết" class="col-xs-12 col-sm-6 col-md-4 ow-server">
  <h4 class="page-header text-right">Table 13</h4>
  <div class="row ow-server-bottom">
    <div class="col-sm-8">
      <div class="row">Trạng thái: Đã đặt</div>
      <div class="row"></div>
      <div class="row"></div>
    </div>
  </div>
</div>
<!--TABLE 14-->
<div id="table14" title="Click vào để xem chi tiết" class="col-xs-12 col-sm-6 col-md-4 ow-server">
  <h4 class="page-header text-right">Table 14</h4>
  <div class="row ow-server-bottom">
    <div class="col-sm-8">
      <div class="row">Trạng thái: Đã đặt</div>
      <div class="row"></div>
      <div class="row"></div>
    </div>
  </div>
</div>
<!--TABLE 15-->
<div id="table15" title="Click vào để xem chi tiết" class="col-xs-12 col-sm-6 col-md-4 ow-server">
  <h4 class="page-header text-right">Table 15</h4>
  <div class="row ow-server-bottom">
    <div class="col-sm-8">
      <div class="row">Trạng thái: Đã đặt</div>
      <div class="row"></div>
      <div class="row"></div>
    </div>
  </div>
</div>
<!--TABLE 16-->
<div id="table16" title="Click vào để xem chi tiết" class="col-xs-12 col-sm-6 col-md-4 ow-server">
  <h4 class="page-header text-right">Table 16</h4>
  <div class="row ow-server-bottom">
    <div class="col-sm-8">
      <div class="row">Trạng thái: Đã đặt</div>
      <div class="row"></div>
      <div class="row"></div>
    </div>
  </div>
</div>
<!--TABLE 17-->
<div id="table17" title="Click vào để xem chi tiết" class="col-xs-12 col-sm-6 col-md-4 ow-server">
  <h4 class="page-header text-right">Table 17</h4>
  <div class="row ow-server-bottom">
    <div class="col-sm-8">
      <div class="row">Trạng thái: Đã đặt</div>
      <div class="row"></div>
      <div class="row"></div>
    </div>
  </div>
</div>
<!--TABLE 18-->
<div id="table18" title="Click vào để xem chi tiết" class="col-xs-12 col-sm-6 col-md-4 ow-server">
  <h4 class="page-header text-right">Table 18</h4>
  <div class="row ow-server-bottom">
    <div class="col-sm-8">
      <div class="row">Trạng thái: Đã đặt</div>
      <div class="row"></div>
      <div class="row"></div>
    </div>
  </div>
</div>

<!-- SCRIPT FLOOR 2-->

<script src="<c:url value="/resources/lib/jquery/jquery-1.11.3.min.js" />"></script>
<script src="<c:url value="/resources/lib/select2/js/select2.full.min.js" />"></script>
<script src="<c:url value="/resources/lib/datatable/js/jquery.dataTables.min.js" />"></script>
<script>
  $(document).ready(function() {
    $("#table10").addClass("table10")
    $(".table10").click(function () {

      var box = $(this)
      if(box.hasClass("table10")){
        box.removeClass("table10").addClass("table12")
        $("#1 > span").replaceWith("<span>Chưa đặt</span>")
      }
      else if(box.hasClass("table12")){
        box.removeClass("table12").addClass("table10")
        $("#1 > span").replaceWith("<span>Đã đặt</span>")
      }
    });
  })
</script>
