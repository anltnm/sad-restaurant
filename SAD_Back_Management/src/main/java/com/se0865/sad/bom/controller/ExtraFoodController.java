package com.se0865.sad.bom.controller;

import com.se0865.sad.bom.logic.ExtraFoodManager;
import com.se0865.sad.configuration.AppConfig;
import com.se0865.sad.configuration.ConstantManager;
import com.se0865.sad.dto.BomMessage;
import com.se0865.sad.dto.ExtraFoodDetailDto;
import com.se0865.sad.dto.ExtraFoodGroupDto;
import com.se0865.sad.entities.ExtraFoodDetail;
import com.se0865.sad.entities.ExtraFoodGroup;
import com.se0865.sad.exception.NotLogInException;
import com.se0865.sad.utils.CommonUtil;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;

import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * Created by AnLTNM-SE60906 on 02/08/2015.
 */
@Controller
@RequestMapping("extraFood")
public class ExtraFoodController {
    final static Logger logger = Logger.getLogger(FoodController.class);

    @Inject
    ExtraFoodManager extraFoodManager;

    @Inject
    CommonUtil util;

    @Inject
    AppConfig appConfig;

    @RequestMapping(value = "listExtraGroup", method = RequestMethod.GET)
    public ModelAndView listFood(HttpServletRequest request) {

        try {
            util.checkUserIsLogin(request);
        } catch (NotLogInException e) {
            ModelAndView loginPage = new ModelAndView(ConstantManager.REDIRECT_PAGE);
            return loginPage;
        }

        ModelAndView model = new ModelAndView("listExtraGroup");
        List<ExtraFoodGroupDto> foodDtoList = extraFoodManager.getExtraFoodGroupList();
        model.addObject("listExtraFoodGroup", foodDtoList);
        return model;
    }

    @RequestMapping(value = "extraGroupDetail", method = RequestMethod.GET, params = {"id"})
    public ModelAndView foodDetail(@RequestParam("id") String id,
                                   HttpServletRequest request) {
        try {
            util.checkUserIsLogin(request);
        } catch (NotLogInException e) {
            ModelAndView loginPage = new ModelAndView(ConstantManager.REDIRECT_PAGE);
            return loginPage;
        }

        ExtraFoodGroupDto extraFoodGroupDto;
        if (null != id && id.trim().length() != 0) {
            long parsedId = 0;
            try {
                parsedId = Long.valueOf(id);
            } catch (NumberFormatException e) {
                logger.info("Not found food_id: " + id);
            }
            if (parsedId != 0) {
                extraFoodGroupDto = extraFoodManager.getExtraFoodGroupDetailInfo(parsedId);
            } else {
                //TODO show error message!
                extraFoodGroupDto = null;
            }
        } else {
            extraFoodGroupDto = null;
        }
        ModelAndView model = new ModelAndView("extraFoodGroupDetail");
        model.addObject("extraFoodGroupDetail", extraFoodGroupDto);
        model.addObject("viewMode", "view");
        return model;
    }

    @RequestMapping(value = "updateExtraFoodGroupView", method = RequestMethod.GET, params = {"id"})
    public ModelAndView updateExtraFoodGroupView(@RequestParam("id") String id,
                                                 HttpServletRequest request) {
        try {
            util.checkUserIsLogin(request);
        } catch (NotLogInException e) {
            ModelAndView loginPage = new ModelAndView(ConstantManager.REDIRECT_PAGE);
            return loginPage;
        }

        ExtraFoodGroupDto extraFoodGroupDto;
        if (null != id && id.trim().length() != 0) {
            long parsedId = 0;
            try {
                parsedId = Long.valueOf(id);
            } catch (NumberFormatException e) {
                logger.info("Not found ExtraFoodGroup's id: " + id);
            }

            if (parsedId != 0) {
                extraFoodGroupDto = extraFoodManager.getExtraFoodGroupDetailInfo(parsedId);
            } else {
                //TODO show error message!
                extraFoodGroupDto = null;
            }
        } else {
            extraFoodGroupDto = null;
        }
        ModelAndView model = new ModelAndView("extraFoodGroupDetail");
        model.addObject("extraFoodGroupDetail", extraFoodGroupDto);
        model.addObject("viewMode", "update");
        return model;
    }

    @RequestMapping(value = "addExtraFoodGroupView", method = RequestMethod.GET)
    public ModelAndView updateExtraFoodGroupView(HttpServletRequest request) {
        try {
            util.checkUserIsLogin(request);
        } catch (NotLogInException e) {
            ModelAndView loginPage = new ModelAndView(ConstantManager.REDIRECT_PAGE);
            return loginPage;
        }

        ModelAndView model = new ModelAndView("extraFoodGroupDetail");
        model.addObject("viewMode", "add");
        return model;
    }

    @RequestMapping(value = "add_extra_food_group", method = RequestMethod.POST, params = {"name"})
    public ModelAndView addExtraFoodGroup(@RequestParam("name") String name,
                                          HttpServletRequest request) {
        try {
            util.checkUserIsLogin(request);
        } catch (NotLogInException e) {
            ModelAndView loginPage = new ModelAndView(ConstantManager.REDIRECT_PAGE);
            return loginPage;
        }

        String message = "";
        if (util.isEmpty(name)) {
            message = appConfig.getCategoryNameEmptyMsg();
        } else {
            ExtraFoodGroup extraFoodGroup = new ExtraFoodGroup();
            extraFoodGroup.setName(name);
            extraFoodGroup.setStatus(ConstantManager.STATUS_ENABLE);
            extraFoodGroup.setCreatedDate(System.currentTimeMillis());
            extraFoodGroup.setLastUpdate(System.currentTimeMillis());
            extraFoodManager.addNewExtraFoodGroup(extraFoodGroup);
            ModelAndView model = new ModelAndView(new RedirectView("listExtraGroup"));
            return model;
        }
        return util.redirectToErrorPage(message, "listExtraGroup");
    }

    @RequestMapping(value = "update_extra_food_group", method = RequestMethod.POST, params = {"name", "id"})
    public ModelAndView addCategory(@RequestParam("name") String name,
                                    @RequestParam("id") String id,
                                    HttpServletRequest request) {
        try {
            util.checkUserIsLogin(request);
        } catch (NotLogInException e) {
            ModelAndView loginPage = new ModelAndView(ConstantManager.REDIRECT_PAGE);
            return loginPage;
        }

        String message = "";
        if (util.isEmpty(name)) {
            message = appConfig.getExtraFoodGroupNameEmptyMsg();
        } else if (util.isEmpty(id)) {
            message = appConfig.getExtraFoodGroupIdEmptyMsg();
        } else {
            ExtraFoodGroup extraFoodGroup = null;
            try {
                extraFoodGroup = extraFoodManager.findExtraFoodGroupDetail(Long.valueOf(id));
            } catch (NumberFormatException e) {
                message = appConfig.getExtraFoodGroupNotFoundMsg();
                return util.redirectToErrorPage(message, "listCategory");
            }
            if (null == extraFoodGroup) {
                message = appConfig.getExtraFoodGroupNotFoundMsg();
                return util.redirectToErrorPage(message, "listCategory");
            }

            extraFoodGroup.setName(name);
            extraFoodGroup.setStatus(ConstantManager.STATUS_ENABLE);
            extraFoodGroup.setLastUpdate(System.currentTimeMillis());
            extraFoodManager.updateExtraFoodGroup(extraFoodGroup);
            ModelAndView model = new ModelAndView(new RedirectView("listExtraGroup"));
            return model;
        }
        return util.redirectToErrorPage(message, "listExtraGroup");
    }

    @RequestMapping(value = "delete_extra_food_group", method = RequestMethod.POST, params = {"extraFoodGroupId"})
    public
    @ResponseBody
    BomMessage addFoodPost(@RequestParam("extraFoodGroupId") String extraFoodGroupId,
                           HttpServletRequest request) {
        BomMessage bomMessage = new BomMessage();
        long id = 0;
        try {
            id = Long.valueOf(extraFoodGroupId);
        } catch (NumberFormatException e) {
            logger.info("Input value is invalid + " + extraFoodGroupId);
            bomMessage.setStatusCode(ConstantManager.STATUS_CODE_FAIL);
            bomMessage.setMessage("Cannot find extra food group");
            return bomMessage;
        }
        ExtraFoodGroup extraFoodGroup = extraFoodManager.findExtraFoodGroupDetail(id);
        if (extraFoodGroup != null) {
            extraFoodManager.deleteExtraFoodGroup(extraFoodGroup);
            List<ExtraFoodDetail> extraFoodDetails = extraFoodManager.getExtraFoodByExtraGroupId(extraFoodGroup);
            for (ExtraFoodDetail extraFoodDetail : extraFoodDetails) {
                extraFoodManager.deleteExtraFoodDetail(extraFoodDetail);
            }
            bomMessage.setMessage("Delete extra food successfully");
        } else {
            logger.info("Input value is invalid + " + extraFoodGroupId);
            bomMessage.setStatusCode(ConstantManager.STATUS_CODE_FAIL);
            bomMessage.setMessage("Cannot find extra food group");
            return bomMessage;
        }
        return bomMessage;
    }

    @RequestMapping(value = "search", method = RequestMethod.GET, params = {"keyword"})
    public ModelAndView searchFood(@RequestParam("keyword") String text,
                                   HttpServletRequest request) {
        try {
            util.checkUserIsLogin(request);
        } catch (NotLogInException e) {
            ModelAndView loginPage = new ModelAndView(ConstantManager.REDIRECT_PAGE);
            return loginPage;
        }

        ModelAndView model = new ModelAndView("listExtraGroup");
        if (!util.isEmpty(text)) {
            List<ExtraFoodGroupDto> extraFoodGroupDtoList = null;
            extraFoodGroupDtoList = extraFoodManager.searchExtraFoodGroupByName(text);
            model.addObject("listExtraFoodGroup", extraFoodGroupDtoList);
            model.addObject("keyword", text);
        }
        return model;
    }

    @RequestMapping(value = "listExtraFood", method = RequestMethod.GET)
    public ModelAndView listExtraFoodDetail(HttpServletRequest request) {
        try {
            util.checkUserIsLogin(request);
        } catch (NotLogInException e) {
            ModelAndView loginPage = new ModelAndView(ConstantManager.REDIRECT_PAGE);
            return loginPage;
        }

        ModelAndView model = new ModelAndView("listExtraFood");
        List<ExtraFoodDetailDto> extraFoodDetailList = extraFoodManager.getExtraFoodDetailList();
        model.addObject("listExtraFood", extraFoodDetailList);
        return model;
    }

    @RequestMapping(value = "extra_food_detail", method = RequestMethod.GET, params = {"id"})
    public ModelAndView extraFoodDetail(@RequestParam("id") String id,
                                        HttpServletRequest request) {
        try {
            util.checkUserIsLogin(request);
        } catch (NotLogInException e) {
            ModelAndView loginPage = new ModelAndView(ConstantManager.REDIRECT_PAGE);
            return loginPage;
        }

        ExtraFoodDetailDto extraFoodDetail;
        if (null != id && id.trim().length() != 0) {
            long parsedId = 0;
            try {
                parsedId = Long.valueOf(id);
            } catch (NumberFormatException e) {
                logger.info("Not found extra_food: " + id);
            }
            if (parsedId != 0) {
                extraFoodDetail = extraFoodManager.getExtraFoodDetailInfo(parsedId);
            } else {
                //TODO show error message!
                extraFoodDetail = null;
            }
        } else {
            extraFoodDetail = null;
        }
        ModelAndView model = new ModelAndView("extraFoodDetail");
        model.addObject("extraFoodDetail", extraFoodDetail);
        model.addObject("viewMode", "view");
        return model;
    }

    @RequestMapping(value = "updateExtraFoodView", method = RequestMethod.GET, params = {"id"})
    public ModelAndView updateExtraFoodView(@RequestParam("id") String id,
                                            HttpServletRequest request) {
        try {
            util.checkUserIsLogin(request);
        } catch (NotLogInException e) {
            ModelAndView loginPage = new ModelAndView(ConstantManager.REDIRECT_PAGE);
            return loginPage;
        }

        ExtraFoodDetailDto extraFoodDetail;
        List<ExtraFoodGroupDto> extraFoodGroupDtoList = null;
        if (null != id && id.trim().length() != 0) {
            long parsedId = 0;
            try {
                parsedId = Long.valueOf(id);
            } catch (NumberFormatException e) {
                logger.info("Not found extra_food: " + id);
            }

            if (parsedId != 0) {
                extraFoodDetail = extraFoodManager.getExtraFoodDetailInfo(parsedId);
                extraFoodGroupDtoList = extraFoodManager.getExtraFoodGroupList();
            } else {
                //TODO show error message!
                extraFoodDetail = null;
            }
        } else {
            extraFoodDetail = null;
        }
        ModelAndView model = new ModelAndView("extraFoodDetail");
        model.addObject("extraFoodDetail", extraFoodDetail);
        model.addObject("extraFoodGroup", extraFoodGroupDtoList);
        model.addObject("viewMode", "update");
        return model;
    }

    @RequestMapping(value = "addExtraFoodView", method = RequestMethod.GET)
    public ModelAndView updateExtraFoodView(HttpServletRequest request) {
        try {
            util.checkUserIsLogin(request);
        } catch (NotLogInException e) {
            ModelAndView loginPage = new ModelAndView(ConstantManager.REDIRECT_PAGE);
            return loginPage;
        }

        ModelAndView model = new ModelAndView("extraFoodDetail");
        List<ExtraFoodGroupDto> extraFoodGroupDtoList = extraFoodManager.getExtraFoodGroupList();
        model.addObject("extraFoodGroup", extraFoodGroupDtoList);
        model.addObject("viewMode", "add");
        return model;
    }

    @RequestMapping(value = "add_extra_food", method = RequestMethod.POST, params = {"extraFoodName", "extraFoodGroup",
            "extraFoodPrice"})
    public ModelAndView addExtraFood(@RequestParam("extraFoodName") String name,
                                     @RequestParam("extraFoodGroup") String extraFoodGroup,
                                     @RequestParam("extraFoodPrice") String price,
                                     HttpServletRequest request) {
        try {
            util.checkUserIsLogin(request);
        } catch (NotLogInException e) {
            ModelAndView loginPage = new ModelAndView(ConstantManager.REDIRECT_PAGE);
            return loginPage;
        }

        ExtraFoodDetail extraFoodDetail = new ExtraFoodDetail();
        String message = "";
        ModelAndView model = null;

        if (util.isEmpty(name)) {
            message = appConfig.getExtraFoodDetailNameNotFoundMsg();
        } else if (util.isEmpty(extraFoodGroup)) {
            message = appConfig.getExtraFoodGroupNotFoundMsg();
        } else if (util.isEmpty(price)) {
            message = appConfig.getExtraFoodDetailPriceNotFoundMsg();
        } else {

            ExtraFoodGroup exFoodGroup = extraFoodManager.findExtraFoodGroupDetail(Long.valueOf(extraFoodGroup));
            float extraFoodPrice = 0;
            if (exFoodGroup == null) {
                message = appConfig.getExtraFoodGroupNotFoundMsg();
                return util.redirectToErrorPage(message, "extraFoodDetail");
            }
            try {
                extraFoodPrice = Float.valueOf(price);
            } catch (NumberFormatException e) {
                message = appConfig.getExtraFoodDetailPriceNotFoundMsg();
                return util.redirectToErrorPage(message, "listExtraFood");
            }

            extraFoodDetail.setName(name);
            extraFoodDetail.setPrice(extraFoodPrice);
            extraFoodDetail.setCreatedDate(System.currentTimeMillis());
            extraFoodDetail.setLastUpdate(System.currentTimeMillis());
            extraFoodDetail.setExtraFoodGroup(exFoodGroup);
            extraFoodDetail.setStatus(ConstantManager.STATUS_ENABLE);

            extraFoodManager.addExtraFoodDetail(extraFoodDetail);
            model = new ModelAndView(new RedirectView("listExtraFood"));
            return model;
        }
        return util.redirectToErrorPage(message, "listExtraFood");
    }

    @RequestMapping(value = "update_extra_food", method = RequestMethod.POST, params = {"id", "extraFoodName", "extraFoodGroup",
            "extraFoodPrice"})
    public ModelAndView updateExtraFood(@RequestParam("extraFoodName") String name,
                                        @RequestParam("extraFoodGroup") String extraFoodGroup,
                                        @RequestParam("id") String id,
                                        @RequestParam("extraFoodPrice") String price,
                                        HttpServletRequest request) {
        try {
            util.checkUserIsLogin(request);
        } catch (NotLogInException e) {
            ModelAndView loginPage = new ModelAndView(ConstantManager.REDIRECT_PAGE);
            return loginPage;
        }

        ExtraFoodDetail extraFoodDetail = null;
        String message = "";
        ModelAndView model = null;
        long parsedId = 0;

        try {
            parsedId = Long.valueOf(id);
        } catch (NumberFormatException e) {
            logger.info("Not found extra_food: " + id);
            message = appConfig.getExtraFoodDetailNotFoundMsg();
            return util.redirectToErrorPage(message, "listExtraFood");
        }
        if (parsedId != 0) {
            try {
                extraFoodDetail = extraFoodManager.findExtraFoodDetail(parsedId);
            } catch (NumberFormatException e) {
                logger.info("Not found extra_food: " + id);
                message = appConfig.getExtraFoodDetailNotFoundMsg();
                return util.redirectToErrorPage(message, "listExtraFood");
            }
        } else {
            logger.info("Not found extra_food: " + id);
            message = appConfig.getExtraFoodDetailNotFoundMsg();
            return util.redirectToErrorPage(message, "listExtraFood");
        }

        if (null == extraFoodDetail) {
            logger.info("Not found extra_food: " + id);
            message = appConfig.getExtraFoodDetailNotFoundMsg();
            return util.redirectToErrorPage(message, "listExtraFood");
        } else {
            if (util.isEmpty(id)) {
                message = appConfig.getExtraFoodDetailNotFoundMsg();
            } else if (util.isEmpty(name)) {
                message = appConfig.getExtraFoodDetailNameNotFoundMsg();
            } else if (util.isEmpty(extraFoodGroup)) {
                message = appConfig.getExtraFoodGroupNotFoundMsg();
            } else if (util.isEmpty(price)) {
                message = appConfig.getExtraFoodDetailPriceNotFoundMsg();
            } else {

                ExtraFoodGroup exFoodGroup = extraFoodManager.findExtraFoodGroupDetail(Long.valueOf(extraFoodGroup));
                float extraFoodPrice = 0;
                if (exFoodGroup == null) {
                    message = appConfig.getExtraFoodGroupNotFoundMsg();
                    return util.redirectToErrorPage(message, "listExtraFood");
                }
                try {
                    extraFoodPrice = Float.valueOf(price);
                } catch (NumberFormatException e) {
                    message = appConfig.getExtraFoodDetailPriceNotFoundMsg();
                    return util.redirectToErrorPage(message, "listExtraFood");
                }

                extraFoodDetail.setName(name);
                extraFoodDetail.setPrice(extraFoodPrice);
                extraFoodDetail.setLastUpdate(System.currentTimeMillis());
                extraFoodDetail.setExtraFoodGroup(exFoodGroup);

                extraFoodManager.updateExtraFoodDetail(extraFoodDetail);
                model = new ModelAndView(new RedirectView("listExtraFood"));
                return model;
            }
        }
        return util.redirectToErrorPage(message, "listExtraFood");
    }

    @RequestMapping(value = "delete_extra_food", method = RequestMethod.POST, params = {"extraFoodId"})
    public
    @ResponseBody
    BomMessage deleteExtraFood(@RequestParam("extraFoodId") String extraFoodId) {
        BomMessage bomMessage = new BomMessage();
        long id = 0;
        try {
            id = Long.valueOf(extraFoodId);
        } catch (NumberFormatException e) {
            logger.info("Input value is invalid + " + extraFoodId);
            bomMessage.setStatusCode(ConstantManager.STATUS_CODE_FAIL);
            bomMessage.setMessage("Cannot find food");
            return bomMessage;
        }
        ExtraFoodDetail exFood = extraFoodManager.findExtraFoodDetail(id);
        if (exFood != null) {
            extraFoodManager.deleteExtraFoodDetail(exFood);
            bomMessage.setMessage("Delete extra food successfully");
        } else {
            logger.info("Input value is invalid + " + extraFoodId);
            bomMessage.setStatusCode(ConstantManager.STATUS_CODE_FAIL);
            bomMessage.setMessage("Cannot extrafind food");
            return bomMessage;
        }
        return bomMessage;
    }

    @RequestMapping(value = "extraFoodDetail/search", method = RequestMethod.GET, params = {"keyword", "group"})
    public ModelAndView searchExtraFood(@RequestParam("keyword") String text,
                                        @RequestParam("group") String group,
                                        HttpServletRequest request) {
        try {
            util.checkUserIsLogin(request);
        } catch (NotLogInException e) {
            ModelAndView loginPage = new ModelAndView(ConstantManager.REDIRECT_PAGE);
            return loginPage;
        }

        ModelAndView model = new ModelAndView("listExtraFood");
        if (!util.isEmpty(text) && !util.isEmpty(group)) {
            List<ExtraFoodDetailDto> extraFoodDetailList = null;
            if (group.equals("name")) {
                extraFoodDetailList = extraFoodManager.getExtraFoodByExtraFoodName(text);
            } else if (group.equals("group")) {
                extraFoodDetailList = extraFoodManager.getExtraFoodByExtraFoodGroupName(text);
            }
            model.addObject("listExtraFood", extraFoodDetailList);
            model.addObject("keyword", text);
            model.addObject("group", group);
        }

        return model;
    }

    @RequestMapping(value = "extraFoodDetailGroupByExtraGroup", method = RequestMethod.GET, params = {"id"})
    public ModelAndView searchExtraFood(@RequestParam("id") String extraGroupId,
                                        HttpServletRequest request) {
        try {
            util.checkUserIsLogin(request);
        } catch (NotLogInException e) {
            ModelAndView loginPage = new ModelAndView(ConstantManager.REDIRECT_PAGE);
            return loginPage;
        }
        ModelAndView model = new ModelAndView("listExtraFood");
        if (!util.isEmpty(extraGroupId)) {
            long parsedId = 0;
            try {
                parsedId = Long.valueOf(extraGroupId);
            } catch (NumberFormatException e) {
                logger.info("Not found extra_food: " + extraGroupId);
            }
            List<ExtraFoodDetailDto> extraFoodDetailList = null;
            if (parsedId != 0) {
                ExtraFoodGroup extraFoodGroup = extraFoodManager.findExtraFoodGroupDetail(parsedId);
                if (extraFoodGroup != null) {
                    extraFoodDetailList = extraFoodManager.getExtraFoodDetailByExtraGroupId(extraFoodGroup);
                } else {
                    return model;
                }
            }
            model.addObject("listExtraFood", extraFoodDetailList);
        }

        return model;
    }
}
