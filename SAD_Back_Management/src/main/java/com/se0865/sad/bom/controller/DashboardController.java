package com.se0865.sad.bom.controller;

import com.se0865.sad.bom.logic.TableManager;
import com.se0865.sad.configuration.AppConfig;
import com.se0865.sad.configuration.ConstantManager;
import com.se0865.sad.dto.BomMessage;
import com.se0865.sad.dto.SadTableDto;
import com.se0865.sad.dto.TableOrderDetailDto;
import com.se0865.sad.entities.Order;
import com.se0865.sad.entities.OrderDetail;
import com.se0865.sad.entities.SadTable;
import com.se0865.sad.exception.NotLogInException;
import com.se0865.sad.utils.CommonUtil;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * Created by AnLTNM-SE60906 on 27/05/2015.
 */
@Controller
@RequestMapping("/dashboard")
public class DashboardController {
    final static Logger logger = Logger.getLogger(DashboardController.class);

    @Inject
    TableManager tableManager;

    @Inject
    CommonUtil util;

    @Inject
    AppConfig appConfig;

    @RequestMapping(value = "/Floor-2", method = RequestMethod.GET)
    public ModelAndView viewFloor2() {
        ModelAndView model = new ModelAndView("Floor-2");
        model.addObject("msg", "hello world");
        return model;
    }

    @RequestMapping(value = "/Floor-1", method = RequestMethod.GET)
    public ModelAndView viewFloor1() {
        ModelAndView model = new ModelAndView("Floor-1");
        model.addObject("msg", "hello world");
        return model;
    }

    @RequestMapping(value = "/orderDetail", method = RequestMethod.GET)
    public ModelAndView orderDetail() {
        ModelAndView model = new ModelAndView("orderDetail");
        model.addObject("msg", "hello world");
        return model;
    }

    @RequestMapping(value = "/getLastUpdateTable", method = RequestMethod.GET, params = "lastUpdated")
    public
    @ResponseBody
    List<SadTableDto> getOrderList(@RequestParam("lastUpdated") String lastUpdate) {
        return tableManager.getLastUpdateTable(Long.valueOf(lastUpdate));
    }

    @RequestMapping(value = "/dashBoard", method = RequestMethod.GET)
    public ModelAndView viewDashboard(HttpServletRequest request) {

        try {
            util.checkUserIsLogin(request);
        } catch (NotLogInException e) {
            ModelAndView loginPage = new ModelAndView(ConstantManager.REDIRECT_PAGE);
            return loginPage;
        }

        ModelAndView model = new ModelAndView("dashBoard");
        model.addObject("lastUpdate", System.currentTimeMillis());
        List<SadTableDto> listTable = tableManager.getTableList();
        model.addObject("listTable", listTable);
        return model;
    }

    @RequestMapping(value = "tableDetail", method = RequestMethod.GET, params = {"id"})
    public ModelAndView viewTableDetail(@RequestParam("id") String id, HttpServletRequest request) {

        try {
            util.checkUserIsLogin(request);
        } catch (NotLogInException e) {
            ModelAndView loginPage = new ModelAndView(ConstantManager.REDIRECT_PAGE);
            return loginPage;
        }

        ModelAndView model = new ModelAndView("tableDetail");
        TableOrderDetailDto tableOrderDetailDto = tableManager.getListOrderOrderByCurrentDate(Long.valueOf(id));
        model.addObject("orderDetail", tableOrderDetailDto);
        return model;
    }

    @RequestMapping(value = "delete_order_detail", method = RequestMethod.POST, params = {"id"})
    public
    @ResponseBody
    BomMessage deleteOrderDetail(@RequestParam("id") String id) {
        BomMessage bomMessage = new BomMessage();
        long orderDetailId = 0;
        try {
            orderDetailId = Long.valueOf(id);
        } catch (NumberFormatException e) {
            logger.info("Input value is invalid + " + id);
            bomMessage.setStatusCode(ConstantManager.STATUS_CODE_FAIL);
            bomMessage.setMessage("Cannot find category");
            return bomMessage;
        }
        OrderDetail f = tableManager.findOrderDetailById(orderDetailId);
        if (f != null) {
            tableManager.deleteOrderDetail(f);
            bomMessage.setMessage("Delete category successfully");
        } else {
            logger.info("Input value is invalid + " + orderDetailId);
            bomMessage.setStatusCode(ConstantManager.STATUS_CODE_FAIL);
            bomMessage.setMessage("Cannot find category");
            return bomMessage;
        }
        return bomMessage;
    }

    @RequestMapping(value = "get_table_status", method = RequestMethod.GET, params = {"id"})
    public
    @ResponseBody
    BomMessage checkTableStatus(@RequestParam("id") String id) {
        BomMessage bomMessage = new BomMessage();
        long orderDetailId = 0;
        try {
            orderDetailId = Long.valueOf(id);
        } catch (NumberFormatException e) {
            logger.info("Input value is invalid + " + id);
            bomMessage.setStatusCode(ConstantManager.STATUS_CODE_FAIL);
            bomMessage.setMessage("Cannot find category");
            return bomMessage;
        }
        SadTable t = tableManager.findTableById(orderDetailId);
        if (t != null) {
            bomMessage.setData(t);
            bomMessage.setMessage("OK !");
        } else {
            logger.info("Input value is invalid + " + orderDetailId);
            bomMessage.setStatusCode(ConstantManager.STATUS_CODE_FAIL);
            bomMessage.setMessage("Cannot find category");
            return bomMessage;
        }
        return bomMessage;
    }

    @RequestMapping(value = "close_table", method = RequestMethod.POST, params = "id")
    public ModelAndView closeTable(@RequestParam("id") String orderId, HttpServletRequest request) {

        try {
            util.checkUserIsLogin(request);
        } catch (NotLogInException e) {
            ModelAndView loginPage = new ModelAndView(ConstantManager.REDIRECT_PAGE);
            return loginPage;
        }

        String message = "";
        long id = 0;
        if (util.isEmpty(orderId)) {
            message = appConfig.getOrderNotFoundMsg();
        } else {
            try {
                id = Long.valueOf(orderId);
            } catch (NumberFormatException e) {
                message = appConfig.getOrderNotFoundMsg();
                return util.redirectToErrorPage(message, "dashBoard");
            }

            Order order = tableManager.findOrderById(id);
            if (order == null) {
                message = appConfig.getOrderNotFoundMsg();
                return util.redirectToErrorPage(message, "dashBoard");
            }

            if (order.getStatus() == ConstantManager.TABLE_STATUS_IS_OPENING || order.getStatus() == ConstantManager.TABLE_STATUS_REQUEST_PAYMENT) {
                order.setStatus(ConstantManager.TABLE_STATUS_CLOSE);
                order.setLastUpdate(System.currentTimeMillis());
                SadTable sadTable = order.getSadTable();
                if (sadTable.getStatus() == ConstantManager.TABLE_STATUS_IS_OPENING || sadTable.getStatus() == ConstantManager.TABLE_STATUS_REQUEST_PAYMENT) {
                    sadTable.setStatus(ConstantManager.TABLE_STATUS_CLOSE);
                    sadTable.setLastUpdate(System.currentTimeMillis());
                    order.setSadTable(sadTable);

                    tableManager.updateOrder(order);
                    ModelAndView model = new ModelAndView("redirect:/dashboard/dashBoard");
                    return model;
                } else {
                    message = appConfig.getOrderNotFoundMsg();
                    return util.redirectToErrorPage(message, "dashBoard");
                }

            } else {
                message = appConfig.getOrderNotFoundMsg();
                return util.redirectToErrorPage(message, "dashBoard");
            }
        }
        return util.redirectToErrorPage(message, "dashBoard");
    }

}