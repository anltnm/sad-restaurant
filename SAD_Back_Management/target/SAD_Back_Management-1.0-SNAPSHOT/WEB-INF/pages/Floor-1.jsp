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


<!--TABLE 1-->
<div id="table1" title="Click vào để xem chi tiết" class="col-xs-12 col-sm-6 col-md-4 ow-server">
  <h4 class="page-header text-right">Table 1</h4>
  <div class="row ow-server-bottom">
    <div class="col-sm-8">
      <div class="row"><span id="1">Trạng thái: <span >Đã đặt</span> </span></div>
      <div class="row"></div>
      <div class="row"></div>
    </div>
  </div>
</div>
<!--TABLE 2-->
<div id="table2" title="Click vào để xem chi tiết" class="col-xs-12 col-sm-6 col-md-4 ow-server">
  <h4 class="page-header text-right">Table 2</h4>
  <div class="row ow-server-bottom">
    <div class="col-sm-8">
      <div class="row">Trạng thái: Đã đặt</div>
      <div class="row"></div>
      <div class="row"></div>
    </div>
  </div>
</div>
<!--TABLE 3-->
<div id="table3" title="Click vào để xem chi tiết" class="col-xs-12 col-sm-6 col-md-4 ow-server">
  <h4 class="page-header text-right">Table 3</h4>
  <div class="row ow-server-bottom">
    <div class="col-sm-8">
      <div class="row">Trạng thái: Đã đặt</div>
      <div class="row"></div>
      <div class="row"></div>
    </div>
  </div>
</div>
<!--TABLE 4-->
<div id="table4" title="Click vào để xem chi tiết" class="col-xs-12 col-sm-6 col-md-4 ow-server">
  <h4 class="page-header text-right">Table 4</h4>
  <div class="row ow-server-bottom">
    <div class="col-sm-8">
      <div class="row">Trạng thái: Đã đặt</div>
      <div class="row"></div>
      <div class="row"></div>
    </div>
  </div>
</div>
<!--TABLE 5-->
<div id="table5" title="Click vào để xem chi tiết" class="col-xs-12 col-sm-6 col-md-4 ow-server">
  <h4 class="page-header text-right">Table 5</h4>
  <div class="row ow-server-bottom">
    <div class="col-sm-8">
      <div class="row">Trạng thái: Đã đặt</div>
      <div class="row"></div>
      <div class="row"></div>
    </div>
  </div>
</div>
<!--TABLE 6-->
<div id="table6" title="Click vào để xem chi tiết" class="col-xs-12 col-sm-6 col-md-4 ow-server">
  <h4 class="page-header text-right">Table 6</h4>
  <div class="row ow-server-bottom">
    <div class="col-sm-8">
      <div class="row">Trạng thái: Đã đặt</div>
      <div class="row"></div>
      <div class="row"></div>
    </div>
  </div>
</div>
<!--TABLE 7-->
<div id="table7" title="Click vào để xem chi tiết" class="col-xs-12 col-sm-6 col-md-4 ow-server">
  <h4 class="page-header text-right">Table 7</h4>
  <div class="row ow-server-bottom">
    <div class="col-sm-8">
      <div class="row">Trạng thái: Đã đặt</div>
      <div class="row"></div>
      <div class="row"></div>
    </div>
  </div>
</div>
<!--TABLE 8-->
<div id="table8" title="Click vào để xem chi tiết" class="col-xs-12 col-sm-6 col-md-4 ow-server">
  <h4 class="page-header text-right">Table 8</h4>
  <div class="row ow-server-bottom">
    <div class="col-sm-8">
      <div class="row">Trạng thái: Đã đặt</div>
      <div class="row"></div>
      <div class="row"></div>
    </div>
  </div>
</div>
<!--TABLE 9-->
<div id="table9" title="Click vào để xem chi tiết" class="col-xs-12 col-sm-6 col-md-4 ow-server">
  <h4 class="page-header text-right">Table 9</h4>
  <div class="row ow-server-bottom">
    <div class="col-sm-8">
      <div class="row">Trạng thái: Đã đặt</div>
      <div class="row"></div>
      <div class="row"></div>
    </div>
  </div>
</div>


<!-- SCRIPT FLOOR 1-->
<script src="<c:url value="/resources/lib/jquery/jquery-1.11.3.min.js" />"></script>
<script src="<c:url value="/resources/lib/select2/js/select2.full.min.js" />"></script>
<script src="<c:url value="/resources/lib/datatable/js/jquery.dataTables.min.js" />"></script>
<script>
  $(document).ready(function() {
    $("#table1").addClass("table1")
    $(".table1").click(function () {

      var box = $(this)
      if(box.hasClass("table1")){
        box.removeClass("table1").addClass("table2")
        $("#1 > span").replaceWith("<span>Chưa đặt</span>")
      }
      else if(box.hasClass("table2")){
        box.removeClass("table2").addClass("table1")
        $("#1 > span").replaceWith("<span>Đã đặt</span>")
      }
    });
  })
</script>





