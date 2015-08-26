package com.se0865.sad.logic;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.se0865.sad.configuration.ConstantManager;
import com.se0865.sad.dto.OrderDetailDto;
import com.se0865.sad.dto.OrderDto;
import com.se0865.sad.entities.*;
import com.se0865.sad.impl.*;
import com.se0865.sad.utils.CommonUtil;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by AnLTNM-SE60906 on 21/07/2015.
 */
@Component
public class OrderManager {
    final static Logger logger = Logger.getLogger(OrderManager.class);

    @Autowired
    private OrderDaoImpl orderDao;

    @Autowired
    private FoodDaoImpl foodDao;

    @Autowired
    private TableDaoImpl tableDao;

    @Autowired
    private OrderDetailDaoImpl orderDetailDao;

    @Autowired
    private ExtraFoodDaoImpl extraFoodDao;

    @Autowired
    private UserDaoImpl userDao;

    @Autowired
    private CommonUtil util;

    public List<OrderDetail> processAddOrder(String addOrderString) throws IOException, InterruptedException {
        ObjectMapper mapper = new ObjectMapper();
        OrderDto orderDto = mapper.readValue(addOrderString, OrderDto.class);
        Order order = null;
        List<OrderDetail> orderDetailList = null;
        if (orderDto.getSeverOrderId() == 0) {
            if (ConstantManager.TABLE_STATUS_IS_OPENING == orderDto.getStatus()) {
                order = new Order();
                order.setDescription(orderDto.getDescription());
                order.setStatus(orderDto.getStatus());
                order.setLocalOrderId((int) orderDto.getOrderId());
                order.setCusQuantity(orderDto.getCusQuantity());

                User u = userDao.find(Long.valueOf(orderDto.getUserId()));
                if (u != null) {
                    order.setUser(u);
                } else {
                    return null;
                }

                List<OrderDetailDto> orderDetailDtoList = orderDto.getOrderDetailDtoList();
                SadTable table = tableDao.find(Long.valueOf(orderDto.getTableId()));
                if (table != null) {
                    if (table.getStatus() == ConstantManager.TABLE_STATUS_CLOSE || table.getStatus() == ConstantManager.TABLE_STATUS_IS_OPENING) {
                        logger.info("This table is close change to open: " + orderDto.getTableId());
                        table.setStatus(ConstantManager.TABLE_STATUS_IS_OPENING);
                        table.setLastUpdate(System.currentTimeMillis());
                    } else {
                        logger.info("This table is open reject: " + orderDto.getTableId());
//                        return null;
                    }
                    order.setSadTable(table);
                } else {
                    logger.info("Cannot find this table in db: " + orderDto.getTableId());
                    return null;
                }
                order.setLastUpdate(System.currentTimeMillis());
                order.setCreatedDate(System.currentTimeMillis());
                orderDao.add(order);
                //Add order detail to DB
                orderDetailList = addOrderDetail(order, orderDetailDtoList);
            }
        }
        return orderDetailList;
    }

    public List<OrderDetail> processUpdateOrder(String orderString) throws IOException {
        ObjectMapper mapper = new ObjectMapper();
        OrderDto orderDto = mapper.readValue(orderString, OrderDto.class);
        Order order = orderDao.find(orderDto.getSeverOrderId());
        List<OrderDetail> orderDetailList = null;

        if (orderDto.getSeverOrderId() != 0 && null != order) {
            if (ConstantManager.TABLE_STATUS_IS_OPENING == orderDto.getStatus()) {
                order.setDescription(orderDto.getDescription());
                order.setStatus(orderDto.getStatus());
                order.setLocalOrderId((int) orderDto.getOrderId());
                order.setCusQuantity(orderDto.getCusQuantity());
                User u = userDao.find(Long.valueOf(orderDto.getUserId()));
                if (u != null) {
                    order.setUser(u);
                } else {
                    return null;
                }
                SadTable table = tableDao.find(Long.valueOf(orderDto.getTableId()));
                if (table != null) {
                    if (table.getStatus() == ConstantManager.TABLE_STATUS_CLOSE) {
                        logger.info("Table is closed " + orderDto.getTableId());
                        return null;
                    } else if (table.getStatus() == ConstantManager.TABLE_STATUS_IS_OPENING) {
                        logger.info("This table is opening: " + orderDto.getTableId());
                    } else {
                        logger.info("This table is open reject: " + orderDto.getTableId() + " has status: " + table.getStatus());
                    }
                    table.setLastUpdate(System.currentTimeMillis());
                    order.setSadTable(table);
                } else {
                    logger.info("Cannot find this table in db: " + orderDto.getTableId());
                    return null;
                }
                order.setLastUpdate(System.currentTimeMillis());
                orderDao.update(order);
                List<OrderDetailDto> orderDetailDtoList = orderDto.getOrderDetailDtoList();
                //Add order detail to DB
                orderDetailList = updateOrderDetail(order, orderDetailDtoList);
            }
        }
        return orderDetailList;
    }

    public Order updateUserQuantity(String orderString) throws IOException {
        ObjectMapper mapper = new ObjectMapper();
        OrderDto orderDto = mapper.readValue(orderString, OrderDto.class);
        Order order = orderDao.find(orderDto.getSeverOrderId());
        if (orderDto.getSeverOrderId() != 0 && null != order) {
            if (ConstantManager.TABLE_STATUS_IS_OPENING == orderDto.getStatus()) {
                order.setDescription(orderDto.getDescription());
                order.setStatus(orderDto.getStatus());
                order.setLocalOrderId((int) orderDto.getOrderId());
                order.setCusQuantity(orderDto.getCusQuantity());
                order.setLastUpdate(System.currentTimeMillis());

                User u = userDao.find(Long.valueOf(orderDto.getUserId()));
                if (u != null) {
                    order.setUser(u);
                } else {
                    return null;
                }

                SadTable table = tableDao.find(Long.valueOf(orderDto.getTableId()));
                if (table != null) {
                    if (table.getStatus() == ConstantManager.TABLE_STATUS_CLOSE) {
                        logger.info("Table is closed " + orderDto.getTableId());
                        return null;
                    } else if (table.getStatus() == ConstantManager.TABLE_STATUS_IS_OPENING) {
                        logger.info("This table is opening: " + orderDto.getTableId());
                    } else {
                        logger.info("This table is open reject: " + orderDto.getTableId());
                    }
                    table.setLastUpdate(System.currentTimeMillis());
                } else {
                    logger.info("Cannot find this table in db: " + orderDto.getTableId());
                }
                orderDao.update(order);
            }
        } else {
            logger.info("Cannot find this table in db: " + orderDto.getSeverOrderId());
        }
        return order;
    }

    public List<OrderDto> getListOrder(long lastUpdate) {
        List<OrderDto> orderDtoListList = null;
        List<Order> orderList = null;
        if (lastUpdate != 0 && lastUpdate != 1) {
            orderList = orderDao.getListOrder(lastUpdate);
        } else {
            orderList = orderDao.getListOrderByOpeningAndRequestPayment();
        }
        OrderDto orderDto = null;
        List<OrderDetailDto> orderDetailDtoList = null;
        List<OrderDetail> orderDetailList = null;
        OrderDetailDto orderDetailDto = null;
        List<Integer> extraFoodDetailDtoList = null;
        List<ExtraFoodDetail> extraFoodDetails = null;
        orderDtoListList = new ArrayList<OrderDto>();
        for (Order o : orderList) {
            if (o != null) {
                orderDto = new OrderDto();
                orderDto.setDescription(o.getDescription());
                orderDto.setStatus(o.getStatus());
                orderDto.setLastUpdate(o.getLastUpdate());
                orderDto.setCusQuantity(o.getCusQuantity());
                orderDto.setOrderId(o.getLocalOrderId());
                orderDto.setSeverOrderId(o.getOrderId());
                orderDto.setTableId((int) o.getSadTable().getId());
                orderDto.setUserId((int) o.getUser().getUserId());

                orderDetailList = o.getOrderDetailList();
                orderDetailDtoList = new ArrayList<OrderDetailDto>();
                for (OrderDetail orderDetail : orderDetailList) {
                    if (((lastUpdate == 0 || lastUpdate == 1) && orderDetail.getStatus() != ConstantManager.STATUS_DISABLE) ||
                            (lastUpdate != 0 && lastUpdate != 1)) {
                        orderDetailDto = new OrderDetailDto();
                        orderDetailDto.setDescription(orderDetail.getDescription());
                        orderDetailDto.setFoodId((int) orderDetail.getFood().getId());
                        orderDetailDto.setOrder(orderDetail.getFoodOrder());
                        orderDetailDto.setOrderId((int) orderDetail.getOrder().getOrderId());
                        orderDetailDto.setOrDetailId(orderDetail.getLocalOrderDetailId());
                        orderDetailDto.setOrDetailServerId(orderDetail.getOrderDetailId());
                        orderDetailDto.setQuantity(orderDetail.getQuantity());
                        orderDetailDto.setStatus(orderDetail.getStatus());

                        extraFoodDetailDtoList = new ArrayList<Integer>();
                        extraFoodDetails = orderDetail.getExtraFoodList();
                        for (ExtraFoodDetail ex : extraFoodDetails) {
                            extraFoodDetailDtoList.add((int) ex.getId());
                        }
                        orderDetailDto.setIdExtraFoods(extraFoodDetailDtoList);
                        orderDetailDtoList.add(orderDetailDto);
                    }
                }

                orderDto.setOrderDetailDtoList(orderDetailDtoList);
            }
            orderDtoListList.add(orderDto);
        }

        return orderDtoListList;
    }

    public Order updateTableStatus(String orderId, String userId, int mode) {
        if (null != orderId) {
            Order order = orderDao.find(Long.valueOf(orderId));
            if (order != null) {
                if (mode == ConstantManager.UPDATE_TABLE_MODE_CHECK_OUT) {
                    if (order.getStatus() == ConstantManager.TABLE_STATUS_CLOSE) {
                        return null;
                    } else {
                        order.setStatus(ConstantManager.TABLE_STATUS_REQUEST_PAYMENT);
                        order.setLastUpdate(System.currentTimeMillis());
                        SadTable table = order.getSadTable();
                        table.setStatus(ConstantManager.TABLE_STATUS_REQUEST_PAYMENT);
                        table.setLastUpdate(System.currentTimeMillis());
                        orderDao.update(order);
                        return order;
                    }
                }
            }
        }
        return null;
    }

    public Order updateTableInformation(String orderId, String userId, String cusQuantity, String description) {
        if (null != orderId) {
            Order order = orderDao.find(Long.valueOf(orderId));
            if (order != null && order.getStatus() != ConstantManager.TABLE_STATUS_CLOSE) {
                if (!util.isEmpty(cusQuantity)) {
                    order.setCusQuantity(Integer.valueOf(cusQuantity));
                }
                if (!util.isEmpty(description)) {
                    order.setDescription(description);
                }
                order.setLastUpdate(System.currentTimeMillis());
                orderDao.update(order);
                return order;
            }
        }
        return null;
    }

    private List<OrderDetail> addOrderDetail(Order order, List<OrderDetailDto> orderDetailDtoList) {
        List<OrderDetail> orderDetailList = new ArrayList<OrderDetail>();
        OrderDetail orderDetail = null;

        for (OrderDetailDto orderDetailDto : orderDetailDtoList) {
            orderDetail = parseOrderDtoToOrderDetail(order, orderDetailDto);
            // Set status to enable
            orderDetail.setStatus(ConstantManager.STATUS_ENABLE);
            orderDetailDao.add(orderDetail);
            orderDetailList.add(orderDetail);
        }
        return orderDetailList;
    }

    private List<OrderDetail> updateOrderDetail(Order order, List<OrderDetailDto> orderDetailDtoList) {
        List<OrderDetail> orderDetailList = new ArrayList<OrderDetail>();
        OrderDetail orderDetail = null;

        for (OrderDetailDto orderDetailDto : orderDetailDtoList) {
            OrderDetail orderDetailFromDB = orderDetailDao.find(orderDetailDto.getOrDetailServerId());
            orderDetail = parseOrderDtoToOrderDetail(order, orderDetailDto);
            if (null != orderDetailFromDB) {
                if (0 != orderDetail.getLocalOrderDetailId()) {
                    orderDetailFromDB.setLocalOrderDetailId(orderDetail.getLocalOrderDetailId());
                }
                if (null != orderDetail.getExtraFoodList() && orderDetail.getExtraFoodList().size() != 0) {
                    orderDetailFromDB.setExtraFoodList(orderDetail.getExtraFoodList());
                }
                if (null != orderDetail.getFood()) {
                    orderDetailFromDB.setFood(orderDetail.getFood());
                }
                if (orderDetailFromDB.getFoodOrder() != orderDetail.getFoodOrder()) {
                    orderDetailFromDB.setFoodOrder(orderDetail.getFoodOrder());
                }
                if (orderDetailFromDB.getQuantity() == 0) {
                    //TODO implement remove this food if this user has role admin
                } else if (orderDetailFromDB.getQuantity() != 0) {
                    orderDetailFromDB.setQuantity(orderDetail.getQuantity());
                }
                if (null != orderDetail.getDescription()) {
                    orderDetailFromDB.setDescription(orderDetail.getDescription());
                }


                orderDetailFromDB.setOrder(order);
                orderDetailFromDB.setLastUpdate(System.currentTimeMillis());

                orderDetailDao.update(orderDetailFromDB);
                orderDetail = orderDetailFromDB;
            } else {
                orderDetailDao.add(orderDetail);
            }
            orderDetailList.add(orderDetail);
        }
        return orderDetailList;
    }

    private OrderDetail parseOrderDtoToOrderDetail(Order order, OrderDetailDto orderDetailDto) {
        ExtraFoodDetail extraFood = null;
        Food food = null;
        OrderDetail orderDetail = null;
        List<Integer> extraItems = null;
        List<ExtraFoodDetail> extraFoodDetails = null;

        orderDetail = new OrderDetail();
        orderDetail.setOrder(order);
        orderDetail.setLocalOrderDetailId((int) orderDetailDto.getOrDetailId());
        orderDetail.setDescription(orderDetailDto.getDescription());
        orderDetail.setQuantity(orderDetailDto.getQuantity());
        orderDetail.setLastUpdate(System.currentTimeMillis());
        orderDetail.setFoodOrder(orderDetailDto.getOrder());
        orderDetail.setOrderDetailId(orderDetailDto.getOrDetailId());

        food = foodDao.find(Long.valueOf(orderDetailDto.getFoodId()));
        if (food != null) {
            orderDetail.setFood(food);
        } else {
            logger.info("Cannot find food: " + orderDetailDto.getFoodId());
        }
        extraItems = orderDetailDto.getIdExtraFoods();
        extraFoodDetails = new ArrayList<ExtraFoodDetail>();
        for (int i : extraItems) {
            extraFood = extraFoodDao.find(Long.valueOf(i));
            extraFoodDetails.add(extraFood);
        }
        orderDetail.setExtraFoodList(extraFoodDetails);
        orderDetail.setCreatedDate(System.currentTimeMillis());
        orderDetail.setLastUpdate(System.currentTimeMillis());
        orderDetail.setStatus(ConstantManager.STATUS_ENABLE);

        return orderDetail;
    }
}
