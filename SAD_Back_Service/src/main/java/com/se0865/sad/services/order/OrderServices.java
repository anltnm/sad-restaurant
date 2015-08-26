package com.se0865.sad.services.order;

import com.se0865.sad.configuration.ConstantManager;
import com.se0865.sad.dto.OrderDto;
import com.se0865.sad.entities.Order;
import com.se0865.sad.entities.OrderDetail;
import com.se0865.sad.logic.OrderManager;
import com.se0865.sad.mapper.AddedOrder;
import com.se0865.sad.mapper.AddedOrderDetail;
import com.se0865.sad.response.SadResponse;
import org.apache.log4j.Logger;

import javax.inject.Inject;
import javax.validation.constraints.NotNull;
import javax.ws.rs.*;
import javax.ws.rs.core.Response;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by AnLTNM-SE60906 on 21/07/2015.
 */
@Path("service/order")
public class OrderServices {
    final static Logger logger = Logger.getLogger(OrderServices.class);

    @Inject
    private OrderManager orderManager;

    @POST
    @Path("post_order")
    @Produces("application/json")
    public Response createOrder(@FormParam("order") String orderString) throws IOException, InterruptedException {

        SadResponse sadResponse = new SadResponse();
        List<AddedOrderDetail> addedOrderDetails = new ArrayList<AddedOrderDetail>();
        List<OrderDetail> orderDetails = orderManager.processAddOrder(orderString);
        AddedOrder addedOrder = new AddedOrder();
        if (null != orderDetails && orderDetails.size() != 0) {
            addedOrder.setOrderId(orderDetails.get(0).getOrder().getOrderId());
            addedOrder.setLocalOrderId(orderDetails.get(0).getOrder().getLocalOrderId());
            addedOrder.setLastUpdate(orderDetails.get(0).getOrder().getLastUpdate());
            AddedOrderDetail addedOrderDetail = null;
            for (OrderDetail orderDetail : orderDetails) {
                addedOrderDetail = new AddedOrderDetail();
                addedOrderDetail.setOrDetailId(orderDetail.getOrderDetailId());
                addedOrderDetail.setOrDetailLocalId(orderDetail.getLocalOrderDetailId());
                addedOrderDetails.add(addedOrderDetail);
            }
            addedOrder.setAddedOrderDetail(addedOrderDetails);
        }
        sadResponse.setData(addedOrder);
        return sadResponse.ok();
    }

    @POST
    @Path("update_order")
    @Produces("application/json")
    public Response updateOrder(@FormParam("order") String orderString) throws IOException, InterruptedException {

        SadResponse sadResponse = new SadResponse();
        List<AddedOrderDetail> addedOrderDetails = new ArrayList<AddedOrderDetail>();
        List<OrderDetail> orderDetails = orderManager.processUpdateOrder(orderString);
        AddedOrder addedOrder = new AddedOrder();
        if (null != orderDetails && orderDetails.size() != 0) {
            addedOrder.setOrderId(orderDetails.get(0).getOrder().getOrderId());
            addedOrder.setLocalOrderId(orderDetails.get(0).getOrder().getLocalOrderId());
            addedOrder.setLastUpdate(orderDetails.get(0).getOrder().getLastUpdate());
            AddedOrderDetail addedOrderDetail = null;
            for (OrderDetail orderDetail : orderDetails) {
                addedOrderDetail = new AddedOrderDetail();
                addedOrderDetail.setOrDetailId(orderDetail.getOrderDetailId());
                addedOrderDetail.setOrDetailLocalId(orderDetail.getLocalOrderDetailId());
                addedOrderDetails.add(addedOrderDetail);
            }
            addedOrder.setAddedOrderDetail(addedOrderDetails);
        }
        sadResponse.setData(addedOrder);
        return sadResponse.ok();
    }

    @POST
    @Path("update_customer_quantity")
    @Produces("application/json")
    public Response updateCustomerQuantity(@FormParam("order") String orderString) throws IOException,
            InterruptedException {

        SadResponse sadResponse = new SadResponse();
        Order updatedOrder = orderManager.updateUserQuantity(orderString);
        if (null != updatedOrder) {
            logger.info("Update customer successfully !");
        } else {
            logger.info("Update customer successfully !");
        }
        sadResponse.setData(new String[]{});
        return sadResponse.ok();
    }

    @POST
    @Path("update_order_status")
    @Produces("application/json")
    public Response closeTable(@NotNull @FormParam("user_id") String userId,
                               @NotNull @FormParam("order_id") String orderId,
                               @NotNull @FormParam("update_mode") String mode) {
        SadResponse response = new SadResponse();
        Order order = orderManager.updateTableStatus(orderId, userId, Integer.valueOf(mode));
        if (order == null) {
            response.setMessage("Update order failed !");
            response.setStatusCode(ConstantManager.STATUS_CODE_FAIL);
        }
        return response.ok();
    }

    @POST
    @Path("update_order_information")
    @Produces("application/json")
    public Response updateTableInformation(@NotNull @FormParam("user_id") String userId,
                                           @NotNull @FormParam("order_id") String orderId,
                                           @NotNull @FormParam("cus_quantity") String cusQuantity,
                                           @NotNull @FormParam("description") String description) {
        SadResponse response = new SadResponse();
        Order order = orderManager.updateTableInformation(orderId, userId, cusQuantity,description);
        if (order == null) {
            response.setMessage("Update order failed !");
            response.setStatusCode(ConstantManager.STATUS_CODE_FAIL);
        }
        return response.ok();
    }

    @GET
    @Path("get_list_order")
    @Produces("application/json")
    public Response getListOrder(@QueryParam("last_update") Long lastUpdate,
                                 @QueryParam("user_id") String userId) {
        SadResponse sadResponse = new SadResponse();
        List<OrderDto> orderList = orderManager.getListOrder(lastUpdate);
        sadResponse.setData(orderList);
        return sadResponse.ok();
    }
}
