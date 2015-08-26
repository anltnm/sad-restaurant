package com.se0865.sad.services.authen;

import com.se0865.sad.configuration.ConstantManager;
import com.se0865.sad.dto.UserDto;
import com.se0865.sad.logic.UserManager;
import com.se0865.sad.response.SadResponse;
import com.se0865.sad.utils.CommonUtil;
import org.springframework.transaction.annotation.Transactional;

import javax.inject.Inject;
import javax.validation.constraints.NotNull;
import javax.ws.rs.FormParam;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Response;

/**
 * Created by AnLTNM-SE60906 on 26/07/2015.
 */
@Path("service/authen")
public class UserAccountServices {
    @Inject
    UserManager userManager;

    @POST
    @Transactional
    @Path("login_account")
    @Produces("application/json")
    public Response loginAccount(@NotNull @FormParam("user_name") String userName,
                                 @NotNull @FormParam("password") String password) {
        SadResponse sadResponse = new SadResponse();
        if (CommonUtil.isNotNull(userName) && CommonUtil.isNotNull(password)
                && userName.trim().length() != 0 && password.trim().length() != 0) {
            UserDto u = userManager.loginAccount(userName, password);
            if (null == u) {
                sadResponse.setMessage("Login fail !");
                sadResponse.setStatusCode(ConstantManager.STATUS_CODE_FAIL);
            } else {
                sadResponse.setData(u);
                sadResponse.setMessage("Login successful");
            }
        } else {
            sadResponse.setStatusCode(ConstantManager.STATUS_CODE_MISSING_PARAMETER);
            sadResponse.setMessage("Missing parameter !");
        }
        return sadResponse.ok();
    }
}
