package com.se0865.sad.services.food;

import com.se0865.sad.dto.ExtraFoodDetailDto;
import com.se0865.sad.dto.FoodDto;
import com.se0865.sad.logic.FoodManager;
import com.se0865.sad.response.SadResponse;
import org.apache.log4j.Logger;
import org.springframework.transaction.annotation.Transactional;

import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.Response;
import java.util.List;

/**
 * Created by AnLTNM-SE60906 on 20/07/2015.
 */
@Path("/service/food")
public class FoodServices {
    final static Logger logger = Logger.getLogger(FoodServices.class);

    @Inject
    FoodManager foodManager;

    @GET
    @Path("list_foood")
    @Transactional
    @Produces("application/json")
    public Response getFoodList(@QueryParam("last_update") Long lastUpdate,
                                @HeaderParam("session") String session) {

        SadResponse response = new SadResponse();
        List<FoodDto> listFood = foodManager.getListFood(lastUpdate);
        response.setData(listFood);
        return response.ok();
    }

    @GET
    @Path("list_extra_food")
    @Transactional
    @Produces("application/json")
    public Response getExtraFood(@QueryParam("last_update") Long lastUpdate,
                                 @HeaderParam("session") String session) {
        SadResponse response = new SadResponse();
        if (null != lastUpdate) {
            List<ExtraFoodDetailDto> groupedExtraFood = foodManager.getGroupedExtraFood(lastUpdate);
            response.setData(groupedExtraFood);
        }
        return response.ok();
    }
}

