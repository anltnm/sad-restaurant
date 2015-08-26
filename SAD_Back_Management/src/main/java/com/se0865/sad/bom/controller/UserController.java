package com.se0865.sad.bom.controller;

import com.se0865.sad.bom.logic.UserManager;
import com.se0865.sad.configuration.AppConfig;
import com.se0865.sad.configuration.ConstantManager;
import com.se0865.sad.dto.UserBomDto;
import com.se0865.sad.dto.UserDto;
import com.se0865.sad.entities.User;
import com.se0865.sad.exception.NotLogInException;
import com.se0865.sad.utils.CommonUtil;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;

import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * Created by AnLTNM-SE60906 on 09/08/2015.
 */
@Controller
@RequestMapping("/user/")
public class UserController {
    final static Logger logger = Logger.getLogger(UserController.class);

    @Inject
    private UserManager userManager;

    @Inject
    private CommonUtil util;

    @Inject
    private AppConfig appConfig;

    @RequestMapping(value = "userListView", method = RequestMethod.GET)
    public ModelAndView getUserListView(HttpServletRequest request) {
        ModelAndView model = new ModelAndView("listUser");
        List<UserDto> userDtoList = userManager.getListUser();
        model.addObject("listUser", userDtoList);
        return model;
    }

    @RequestMapping(value = "user_detail", method = RequestMethod.GET, params = {"id"})
    public ModelAndView foodDetail(@RequestParam("id") String id,
                                   HttpServletRequest request) {
        try {
            util.checkUserIsLogin(request);
        } catch (NotLogInException e) {
            ModelAndView loginPage = new ModelAndView(ConstantManager.REDIRECT_PAGE);
            return loginPage;
        }
        UserBomDto userDto = null;
        if (null != id && id.trim().length() != 0) {
            long parsedId = -1;
            try {
                parsedId = Long.valueOf(id);
            } catch (NumberFormatException e) {
                logger.info("Not found user_id: " + id);
            }
            if (parsedId != -1) {
                userDto = userManager.getUserDetail(parsedId);
            } else {
                userDto = null;
            }
        } else {
            userDto = null;
        }
        ModelAndView model = new ModelAndView("userDetail");
        model.addObject("useDetail", userDto);
        model.addObject("viewMode", "view");
        return model;
    }

    @RequestMapping(value = "addUserView", method = RequestMethod.GET)
    public ModelAndView addUserView(HttpServletRequest request) {
        try {
            util.checkUserIsLogin(request);
        } catch (NotLogInException e) {
            ModelAndView loginPage = new ModelAndView(ConstantManager.REDIRECT_PAGE);
            return loginPage;
        }

        ModelAndView model = new ModelAndView("userDetail");
        model.addObject("viewMode", "add");
        return model;
    }

    @RequestMapping(value = "add_user", method = RequestMethod.POST, params = {"userName", "password", "displayName"})
    public ModelAndView addUserPost(@RequestParam("userName") String userName,
                                    @RequestParam("password") String password,
                                    @RequestParam("displayName") String displayName,
                                    HttpServletRequest request) {
        try {
            util.checkUserIsLogin(request);
        } catch (NotLogInException e) {
            ModelAndView loginPage = new ModelAndView(ConstantManager.REDIRECT_PAGE);
            return loginPage;
        }

        User user = new User();
        String message = "";
        ModelAndView model = null;

        if (util.isEmpty(userName)) {
            message = appConfig.getFoodNameEmptyMsg();
        } else if (util.isEmpty(password)) {
            message = appConfig.getFoodPriceEmptyMsg();
        } else if (util.isEmpty(displayName)) {
            message = appConfig.getFoodPriceEmptyMsg();
        } else {
            if (userManager.isUserNameExisted(userName)) {
                model = new ModelAndView("userDetail");
                UserBomDto userDto = new UserBomDto();
                userDto.setDisplayName(displayName);
                userDto.setUserName(userName);
                model.addObject("userNameDuplicated", true);
                model.addObject("useDetail", userDto);
                return model;
            }

            user.setUserRole(ConstantManager.USER_ROLE_STAFF);
            user.setUserName(userName);
            user.setDisplayName(displayName);
            user.setUserPassword(password);
            user.setCreatedDate(System.currentTimeMillis());
            user.setLastUpdate(System.currentTimeMillis());
            userManager.addUser(user);
            model = new ModelAndView("listUser");
            return model;
        }
        return util.redirectToErrorPage(message, "userListView");
    }

    @RequestMapping(value = "updateUserView", method = RequestMethod.GET, params = {"id"})
    public ModelAndView updateUserView(HttpServletRequest request,
                                       @RequestParam("id") String id) {
        try {
            util.checkUserIsLogin(request);
        } catch (NotLogInException e) {
            ModelAndView loginPage = new ModelAndView(ConstantManager.REDIRECT_PAGE);
            return loginPage;
        }
        String message = "";
        long parsedId = -1;
        UserBomDto userDto = null;
        try {
            parsedId = Long.valueOf(id);
        } catch (NumberFormatException e) {
            logger.info("Not found user_id: " + id);
            message = appConfig.getFoodPriceEmptyMsg();
            return util.redirectToErrorPage(message, "userListView");
        }
        if (parsedId != -1) {
            userDto = userManager.getUserDetail(parsedId);
        } else {
            logger.info("Not found user_id: " + id);
            message = appConfig.getFoodPriceEmptyMsg();
            return util.redirectToErrorPage(message, "userListView");
        }
        ModelAndView model = new ModelAndView("userDetail");
        model.addObject("useDetail", userDto);
        model.addObject("viewMode", "update");
        return model;
    }

    @RequestMapping(value = "update_user", method = RequestMethod.POST, params = {"id", "password", "displayName"})
    public ModelAndView updateUserInformation(@RequestParam("id") String id,
                                              @RequestParam("password") String password,
                                              @RequestParam("displayName") String displayName,
                                              HttpServletRequest request) {
        try {
            util.checkUserIsLogin(request);
        } catch (NotLogInException e) {
            ModelAndView loginPage = new ModelAndView(ConstantManager.REDIRECT_PAGE);
            return loginPage;
        }

        String message = "";
        ModelAndView model = null;

        if (util.isEmpty(id)) {
            message = appConfig.getFoodPriceEmptyMsg();
        } else if (util.isEmpty(password)) {
            message = appConfig.getFoodPriceEmptyMsg();
        } else if (util.isEmpty(displayName)) {
            message = appConfig.getFoodPriceEmptyMsg();
        } else {
            long parsedId = -1;
            try {
                parsedId = Long.valueOf(id);
            } catch (NumberFormatException e) {
                logger.info("Not found user_id: " + id);
                message = appConfig.getFoodPriceEmptyMsg();
                return util.redirectToErrorPage(message, "userListView");
            }
            if (parsedId != -1) {
                User u = userManager.getUserById(parsedId);
                if (u != null) {
                    u.setDisplayName(displayName);
                    u.setUserPassword(password);
                    u.setLastUpdate(System.currentTimeMillis());
                    userManager.updateUser(u);
                    model = new ModelAndView("redirect:/user/userListView");
                    return model;
                }
            } else {
                message = appConfig.getFoodPriceEmptyMsg();
                util.redirectToErrorPage(message, "userListView");
            }

        }
        return util.redirectToErrorPage(message, "userListView");
    }

    @RequestMapping(value = "search", method = RequestMethod.GET, params = {"keyword", "category"})
    public ModelAndView searchFood(@RequestParam("keyword") String text,
                                   @RequestParam("category") String category,
                                   HttpServletRequest request) {
        try {
            util.checkUserIsLogin(request);
        } catch (NotLogInException e) {
            ModelAndView loginPage = new ModelAndView(ConstantManager.REDIRECT_PAGE);
            return loginPage;
        }

        ModelAndView model = new ModelAndView("listUser");
        if (!util.isEmpty(text) && !util.isEmpty(category)) {
            List<UserDto> userDtoList = null;
            if (category.equals("name")) {
                userDtoList = userManager.getUsersByUserName(text);
            } else if (category.equals("displayName")) {
                userDtoList = userManager.getUsersByUserDisplayName(text);
            }
            model.addObject("listUser", userDtoList);
            model.addObject("keyword", text);
            model.addObject("category", category);
        }

        return model;
    }
}
