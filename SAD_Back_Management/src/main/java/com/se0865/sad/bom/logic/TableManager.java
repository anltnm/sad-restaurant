package com.se0865.sad.bom.logic;

import com.se0865.sad.configuration.ConstantManager;
import com.se0865.sad.dto.OrderDetailForBomDto;
import com.se0865.sad.dto.OrderDto;
import com.se0865.sad.dto.SadTableDto;
import com.se0865.sad.dto.TableOrderDetailDto;
import com.se0865.sad.entities.ExtraFoodDetail;
import com.se0865.sad.entities.Order;
import com.se0865.sad.entities.OrderDetail;
import com.se0865.sad.entities.SadTable;
import com.se0865.sad.impl.OrderDaoImpl;
import com.se0865.sad.impl.OrderDetailDaoImpl;
import com.se0865.sad.impl.TableDaoImpl;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by AnLTNM-SE60906 on 04/08/2015.
 */
public class TableManager {
    @Autowired
    OrderDaoImpl orderDao;

    @Autowired
    TableDaoImpl tableDao;

    @Autowired
    OrderDetailDaoImpl orderDetailDao;


    public List<SadTableDto> getLastUpdateTable(long lastUpdate) {
        List<SadTable> listTable = tableDao.getLastUpdatedTable(lastUpdate);
        return parseListTableToListTableDto(listTable);
    }

    public TableOrderDetailDto getListOrderOrderByCurrentDate(long tableId) {
        List<OrderDto> orderDtoListList = null;
        List<Order> orderList = new ArrayList<Order>();
        TableOrderDetailDto tableOrderDetailDto = new TableOrderDetailDto();
        if (tableId != 0) {
            orderList = orderDao.getListOrderByCurrentDate(tableId);
            List<OrderDetail> orderDetails = null;
            List<ExtraFoodDetail> extraFoodDetails = null;
            Order currentOrder = null;
            double totalOrder = 0;
            if (orderList.size() != 0) {
                currentOrder = orderList.get(0);

                tableOrderDetailDto.setOrderId(currentOrder.getOrderId());
                if (null != currentOrder.getClosedDate()) {
                    tableOrderDetailDto.setClosedDate(new Date(currentOrder.getClosedDate()));
                }
                if (null != currentOrder.getCreatedDate()) {
                    tableOrderDetailDto.setCreatedDate(new Date(currentOrder.getCreatedDate()));
                }
                tableOrderDetailDto.setCusQuantity(currentOrder.getCusQuantity());
                tableOrderDetailDto.setDescription(currentOrder.getDescription());
                tableOrderDetailDto.setLastUpdate(new Date(currentOrder.getLastUpdate()));
                tableOrderDetailDto.setSadTable(currentOrder.getSadTable());
                tableOrderDetailDto.setStatus(currentOrder.getStatus());
                tableOrderDetailDto.setUser(currentOrder.getUser());

                orderDetails = currentOrder.getOrderDetailList();
                List<OrderDetailForBomDto> listOrderDetail = new ArrayList<OrderDetailForBomDto>();
                OrderDetailForBomDto orderDetailForBomDto = null;
                for (OrderDetail orderDetail : orderDetails) {
                    if (orderDetail.getStatus() == ConstantManager.STATUS_ENABLE) {
                        orderDetailForBomDto = new OrderDetailForBomDto();
                        orderDetailForBomDto.setDescription(orderDetail.getDescription());
                        orderDetailForBomDto.setFood(orderDetail.getFood());
                        orderDetailForBomDto.setOrder(orderDetail.getFoodOrder());
                        orderDetailForBomDto.setOrderId(orderDetail.getOrder().getOrderId());
                        orderDetailForBomDto.setOrDetailId(orderDetail.getOrderDetailId());
                        orderDetailForBomDto.setQuantity(orderDetail.getQuantity());
                        extraFoodDetails = orderDetail.getExtraFoodList();
                        double subItem = 0;

                        for (ExtraFoodDetail ex : extraFoodDetails) {
                            subItem += ex.getPrice();
                        }
                        subItem = ((orderDetail.getFood().getPrice() + subItem) * orderDetail.getQuantity());
                        totalOrder += subItem;
                        orderDetailForBomDto.setExtraFoods(extraFoodDetails);
                        orderDetailForBomDto.setFoodPrice(subItem);

                        listOrderDetail.add(orderDetailForBomDto);
                    }
                }

                tableOrderDetailDto.setOrderDetails(listOrderDetail);
                tableOrderDetailDto.setTotalOrder(totalOrder);
            }
        }
        return tableOrderDetailDto;
    }

    public OrderDetail findOrderDetailById(long id) {
        return orderDetailDao.find(id);
    }

    public Order findOrderById(long id) {
        return orderDao.find(id);
    }

    public SadTable findTableById(long id) {
        return tableDao.find(id);
    }

    public void updateOrder(Order order) {
        orderDao.update(order);
    }

    public void deleteOrderDetail(OrderDetail orderDetail) {
        Order order = orderDetail.getOrder();
        order.setLastUpdate(System.currentTimeMillis());
        orderDao.update(order);

        orderDetail.setStatus(ConstantManager.STATUS_DISABLE);
        orderDetail.setLastUpdate(System.currentTimeMillis());
        orderDetailDao.update(orderDetail);
    }

    public List<SadTableDto> getTableList() {
        List<SadTable> tableList = tableDao.getAllOrderByTableName();
        return parseListTableToListTableDto(tableList);
    }

    private List<SadTableDto> parseListTableToListTableDto(List<SadTable> tableList) {
        List<SadTableDto> tableDtoList = new ArrayList<SadTableDto>();
        SadTableDto sadTableDto = null;
        for (SadTable t : tableList) {
            sadTableDto = new SadTableDto();
            sadTableDto.setStatus(t.getStatus());
            sadTableDto.setFloorName(t.getFloorName());
            sadTableDto.setName(t.getName());
            sadTableDto.setId(t.getId());
            sadTableDto.setSeatNumber(t.getSeatNumber());
            sadTableDto.setLastUpdate(t.getLastUpdate());
            tableDtoList.add(sadTableDto);
        }
        return tableDtoList;
    }
}
