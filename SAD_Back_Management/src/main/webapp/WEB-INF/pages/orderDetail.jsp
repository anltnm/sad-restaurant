<%--
  Created by IntelliJ IDEA.
  User: AnLTNM-SE60906
  Date: 29/05/2015
  Time: 6:09 AM
  To change this template use File | Settings | File Templates.
--%>
<div class="row">
  <div id="breadcrumb" class="col-xs-12">
    <a href="#" class="show-sidebar">
      <i class="fa fa-bars"></i>
    </a>
    <ol class="breadcrumb pull-left">
      <li><a href="index.html">Dashboard</a></li>
      <li><a href="#">Order List</a></li>
      <li><a href="#">Order Detail</a></li>
    </ol>
  </div>
</div>
<div class="row">
  <div class="col-xs-12">
    <div class="box">
      <div class="box-content">
        <div class="col-xs-6">
          <h3 class="invoice-header">Table information:</h3>
          <br/>
          <address>
            <strong>Table number: </strong>
            <a>4</a>
          </address>
          <address>
            <strong>Waiter: </strong>
            <a>Adam</a>
          </address>
          <address>
            <strong>Start time: </strong>
            <a>15-05-2014 7:30pm</a>
          </address>
          <address>
            <strong>End time: </strong>
            <a>15-05-2014 8:30pm</a>
          </address>
        </div>
        <div class="clearfix"></div>
        <div class="col-xs-12">
          <br/>
          <table class="table table-hover">
            <thead>
            <tr>
              <th>ITEMS</th>
              <th>QTY</th>
              <th>UNIT PRICE</th>
              <th>TOTAL</th>
            </tr>
            </thead>
            <tbody>
            <tr>
              <td class="m-ticker"><b>Ga xao gung</b><span>Ga, gung</span>
              </td>
              <td>2</td>
              <td>$12.5</td>
              <td>$25</td>
            </tr>
            <tr>
              <td class="m-ticker"><b>Tom hap nuoc dua</b><span>Tom duoc hap trong nuoc dua</span>
              </td>
              <td>2</td>
              <td>$25</td>
              <td>$50</td>
            </tr>
            <tr>
              <td class="m-ticker"><b>Cang cua bo hoa</b><span>Tom, thit, carot</span>
              </td>
              <td>2</td>
              <td>$50</td>
              <td>$100</td>
            </tr>
            <tr class="active">
              <td></td>
              <td></td>
              <td></td>
              <td><b>$175<sup>*</sup></b></td>
            </tr>
            </tbody>
          </table>
          <small>* VAT included</small>
        </div>
        <div class="clearfix"></div>
        <%--<div class="col-xs-6 col-xs-offset-6">--%>
          <%--<div class="col-xs-4">--%>
            <%--<a href="#" class="btn btn-default btn-label-left"><span><i class="fa fa-floppy-o"></i></span>--%>
              <%--Save as pdf</a>--%>
          <%--</div>--%>
          <%--<div class="col-xs-4 col-xs-offset-4">--%>
            <%--<a href="#" class="btn btn-primary btn-label-left"><span><i--%>
                    <%--class="fa fa-credit-card"></i></span> Pay now</a>--%>
          <%--</div>--%>
        <%--</div>--%>
        <div class="clearfix"></div>
      </div>
    </div>
  </div>
</div>
