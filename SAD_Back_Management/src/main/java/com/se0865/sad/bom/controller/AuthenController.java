package com.se0865.sad.bom.controller;

import com.se0865.sad.bom.logic.AuthenManager;
import com.se0865.sad.configuration.AppConfig;
import com.se0865.sad.configuration.ConstantManager;
import com.se0865.sad.dto.UserDto;
import com.se0865.sad.entities.User;
import com.se0865.sad.exception.NotLogInException;
import com.se0865.sad.utils.CommonUtil;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;

import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

/**
 * Created by AnLTNM-SE60906 on 03/08/2015.
 */
@Controller
@RequestMapping("authen")
public class AuthenController {
    @Inject
    CommonUtil util;

    @Inject
    AppConfig appConfig;

    @Inject
    AuthenManager authenManager;

    @RequestMapping(value = "login", method = RequestMethod.GET)
    public ModelAndView getLoginView(HttpServletRequest request) {
        String userId = (String) request.getSession().getAttribute("user_id");
        ModelAndView model = null;
        if (userId == null) {
            model = new ModelAndView("loginPage");
        } else {
            model = new ModelAndView("redirect:/dashboard/dashBoard");
        }
        return model;
    }

    @RequestMapping(value = "changePassword", method = RequestMethod.GET)
    public ModelAndView changePasswordView(HttpServletRequest request) {
        try {
            util.checkUserIsLogin(request);
        } catch (NotLogInException e) {
            ModelAndView loginPage = new ModelAndView(ConstantManager.REDIRECT_PAGE);
            return loginPage;
        }
        ModelAndView model = new ModelAndView("changePassPage");
        return model;
    }

    @RequestMapping(value = "changePassword", method = RequestMethod.POST, params = {"oldPassword", "newPassword", "confirmPassword"})
    public ModelAndView loginAccount(@RequestParam("oldPassword") String oldPassword,
                                     @RequestParam("newPassword") String newPassword,
                                     @RequestParam("confirmPassword") String confirmPassWord,
                                     HttpServletRequest request) {

        ModelAndView model = null;
        if (!util.isEmpty(oldPassword) && !util.isEmpty(newPassword) && !util.isEmpty(confirmPassWord)) {
            if (newPassword.equals(confirmPassWord)) {
                Object userId = request.getSession().getAttribute("user_id");
                if (null != userId) {
                    String uId = String.valueOf(userId);
                    User isValidatedUser = authenManager.validatePassword(uId, oldPassword, newPassword);
                    if (null != isValidatedUser) {
                        model = new ModelAndView("redirect:/dashboard/dashBoard");
                    } else {
                        model = new ModelAndView(("changePassPage"));
                        model.addObject("error", true);
                        model.addObject("message", "The old password you entered don't match.");
                    }
                } else {
                    model = new ModelAndView("loginPage");
                }
            } else {
                model = new ModelAndView(("changePassPage"));
                model.addObject("error", true);
                model.addObject("message", "The new password and confirm password you entered don't match.");
            }

        } else {
            model = new ModelAndView(new RedirectView("login"));
            model.addObject("error", true);
            model.addObject("message", "Login failed !");
        }
        return model;
    }

    @RequestMapping(value = "login", method = RequestMethod.POST, params = {"username", "password"})
    public ModelAndView loginAccount(@RequestParam("username") String userName,
                                     @RequestParam("password") String passWord,
                                     HttpServletRequest request) {

        ModelAndView model = null;
        if (!util.isEmpty(userName) && !util.isEmpty(passWord)) {
            UserDto isValidatedUser = authenManager.validateUser(userName, passWord);
            if (null != isValidatedUser) {
                model = new ModelAndView("redirect:/dashboard/dashBoard");
                request.getSession().setAttribute("user_id", isValidatedUser.getUserId());
            } else {
                model = new ModelAndView(("loginPage"));
                model.addObject("error", true);
                model.addObject("message", "The username and password you entered don't match.");
            }
        } else {
            model = new ModelAndView(new RedirectView("login"));
            model.addObject("error", true);
            model.addObject("message", "Login failed !");
        }
        return model;
    }

    @RequestMapping(value = "logout", method = RequestMethod.GET)
    public ModelAndView logoutAccount(HttpServletRequest request) {

        HttpSession session = request.getSession(false);
        if (session != null) {
            session.invalidate();
        }
        ModelAndView model = new ModelAndView(new RedirectView("login"));
        return model;
    }
}
