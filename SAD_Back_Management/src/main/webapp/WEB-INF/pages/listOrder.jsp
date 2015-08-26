<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<link href="<c:url value="/resources/lib/datatable/css/jquery.dataTables.min.css" />" rel="stylesheet">
<link href="<c:url value="/resources/lib/datatable/css/jquery.dataTables_themeroller.css" />" rel="stylesheet">
<link href="<c:url value="/resources/lib/jquery/jqueryui/jquery-ui.structure.min.css" />" rel="stylesheet">
<link href="<c:url value="/resources/lib/jquery/jqueryui/jquery-ui.theme.min.css" />" rel="stylesheet">
<link href="<c:url value="/resources/lib/select2/css/select2.min.css" />" rel="stylesheet">
<div class="row">
    <div id="breadcrumb" class="col-xs-12">
        <a href="#" class="show-sidebar">
            <i class="fa fa-bars"></i>
        </a>
        <ol class="breadcrumb pull-left">
            <li><a href="#">Dashboard</a></li>
            <li><a href="#">List Order</a></li>
        </ol>
    </div>
</div>
<div class="row">
    <div class="col-sm-1">
        <a href="./foodDetail" class="btn btn-primary btn-label-left">
            Add order
        </a>
    </div>
</div>
<div class="row">
    <div class="form-group has-warning has-feedback col-sm-offset-1">
        <label class="col-sm-1 control-label">Start Date</label>

        <div class="col-sm-2">
            <input type="text" class="form-control" id="date3_example" placeholder="Date period">
        </div>
        <label class="col-sm-1 control-label">End Date</label>

        <div class="col-sm-2">
            <input type="text" class="form-control" id="date3-1_example" placeholder="Date period">
        </div>
        <label class="col-sm-1 control-label">Search by</label>

        <div class="col-sm-3">
            <select class="populate placeholder" name="country" id="s2_country" style="width: 100%">
                <option value="">-- Select order category --</option>
                <option value="jp">Payed</option>
                <option value="ru">Cancel</option>
                <option value="gb">Free</option>
                <option value="gb">Discount</option>
            </select>
        </div>
        <div class="col-sm-1">
            <button type="submit" class="btn btn-primary">Search</button>
        </div>
    </div>
</div>
<div class="row">
    <div class="col-xs-12">
        <div class="box">
            <div class="box-header">
                <div class="box-name">
                    <%--<i class="fa fa-usd"></i>--%>
                    <span>List order</span>
                </div>
            </div>
            <div class="box-content no-padding">
                <table class="table table-bordered table-striped table-hover table-heading"
                       id="datatable-1">
                    <thead>
                    <tr>
                        <th>No.</th>
                        <th>OrderId</th>
                        <th>Start time</th>
                        <th>End Time</th>
                        <th>Total</th>
                    </tr>
                    </thead>
                    <tbody>
                    <!-- Start: list_row -->
                    <tr>
                        <td>1</td>
                        <td><a href="./orderDetail">1</a></td>
                        <td>15-05-2014 7:30</td>
                        <td>15-05-2014 8:30</td>
                        <td>$73</td>
                    </tr>
                    <tr>
                        <td>2</td>
                        <td>2</td>
                        <td>15-05-2014 7:30</td>
                        <td>15-05-2014 8:30</td>
                        <td>$73</td>
                    </tr>
                    <tr>
                        <td>3</td>
                        <td>3</td>
                        <td>15-05-2014 7:30</td>
                        <td>15-05-2014 8:30</td>
                        <td>$73</td>
                    </tr>
                    <tr>
                        <td>4</td>
                        <td>4</td>
                        <td>15-05-2014 7:30</td>
                        <td>15-05-2014 8:30</td>
                        <td>$73</td>
                    </tr>
                    <tr>
                        <td>5</td>
                        <td>5</td>
                        <td>15-05-2014 7:30</td>
                        <td>15-05-2014 8:30</td>
                        <td>$73</td>
                    </tr>
                    <tr>
                        <td>6</td>
                        <td>6</td>
                        <td>15-05-2014 7:30</td>
                        <td>15-05-2014 8:30</td>
                        <td>$73</td>
                        >
                    </tr>
                    <tr>
                        <td>6</td>
                        <td>7</td>
                        <td>15-05-2014 7:30</td>
                        <td>15-05-2014 8:30</td>
                        <td>$73</td>
                    </tr>
                    <tr>
                        <td>8</td>
                        <td>8</td>
                        <td>15-05-2014 7:30</td>
                        <td>15-05-2014 8:30</td>
                        <td>$73</td>
                    </tr>
                    <tr>
                        <td>9</td>
                        <td>9</td>
                        <td>15-05-2014 7:30</td>
                        <td>15-05-2014 8:30</td>
                        <td>$73</td>
                    </tr>
                    <tr>
                        <td>10</td>
                        <td>10</td>
                        <td>15-05-2014 7:30</td>
                        <td>15-05-2014 8:30</td>
                        <td>$73</td>
                    </tr>
                    <tfoot>
                    <tr>
                        <th>Total</th>
                        <th></th>
                        <th></th>
                        <th></th>
                        <th>$730</th>
                    </tr>
                    </tfoot>
                    <!-- End: list_row -->
                    </tbody>
                </table>
            </div>
        </div>
    </div>
</div>
<script src="<c:url value="/resources/lib/jquery/jquery-1.11.3.min.js" />"></script>
<script src="<c:url value="/resources/lib/jquery/jqueryui/jquery-ui.min.js" />"></script>
<script src="<c:url value="/resources/lib/select2/js/select2.full.min.js" />"></script>
<script src="<c:url value="/resources/lib/datatable/js/jquery.dataTables.min.js" />"></script>
<script>
    //    $('#datatable-1').dataTable();
    $('#s2_country').select2();
    $('#date3_example').datepicker({numberOfMonths: 1, showButtonPanel: true});
    $('#date3-1_example').datepicker({numberOfMonths: 1, showButtonPanel: true});
</script>