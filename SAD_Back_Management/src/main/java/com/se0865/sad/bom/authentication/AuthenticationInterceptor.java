package com.se0865.sad.bom.authentication;

import com.se0865.sad.entities.User;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Created by AnLTNM-SE60906 on 03/08/2015.
 */
public class AuthenticationInterceptor extends HandlerInterceptorAdapter {
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        String uri = request.getRequestURI();
        if(!uri.endsWith("login") && !uri.endsWith("logout"))
        {
            User userData = (User) request.getSession().getAttribute("LOGGEDIN_USER");
            if(userData == null)
            {
                response.sendRedirect("login.do");
                return false;
            }
        }
        return true;
    }
}