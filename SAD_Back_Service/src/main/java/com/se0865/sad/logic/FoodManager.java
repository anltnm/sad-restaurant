package com.se0865.sad.logic;

import com.se0865.sad.dto.ExtraFoodDetailDto;
import com.se0865.sad.dto.FoodDto;
import com.se0865.sad.entities.ExtraFoodDetail;
import com.se0865.sad.entities.Food;
import com.se0865.sad.impl.ExtraFoodDaoImpl;
import com.se0865.sad.impl.ExtraFoodGroupDaoImpl;
import com.se0865.sad.impl.FoodDaoImpl;
import org.apache.commons.lang.ArrayUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by AnLTNM-SE60906 on 20/07/2015.
 */
public class FoodManager {
    final static Logger logger = Logger.getLogger(FoodManager.class);

    @Autowired
    private FoodDaoImpl foodDao;

    @Autowired
    private ExtraFoodGroupDaoImpl extraFoodGroupDao;

    @Autowired
    private ExtraFoodDaoImpl extraFoodDao;

    public List<FoodDto> getListFood(long lastUpdate) {
        List<Food> foodList = null;
        if (lastUpdate == 0 || lastUpdate == 1) {
            List<Food> allEnableFood = foodDao.getAllEnableFood();
            List<Food> foodInIsOnlineOrderDetail = foodDao.getAllDisableFoodInOnlineOrderDetail();
            foodInIsOnlineOrderDetail.addAll(allEnableFood);
            foodList = foodInIsOnlineOrderDetail;
        } else {
            foodList = foodDao.getLastUpdateFood(lastUpdate);
        }
        List<FoodDto> foodDtoList = new ArrayList<FoodDto>();
        if (foodList != null && foodList.size() != 0) {
            for (Food f : foodList) {
                foodDtoList.add(f.parseToFoodDto());
            }
        } else {
            logger.info("These is no food in DB");
        }

        return foodDtoList;
    }

    public List<ExtraFoodDetailDto> getGroupedExtraFood(long lastUpdate) {
        // Get all extra group
        List<ExtraFoodDetail> extraFoodDetails = null;
        List<ExtraFoodDetailDto> extraFoodDetailDtoList = new ArrayList<ExtraFoodDetailDto>();
        ExtraFoodDetailDto extraFoodDetailDto = null;

        if (lastUpdate == 0 || lastUpdate == 1) {
            extraFoodDetails = extraFoodDao.getAll();
        } else {
            extraFoodDetails = extraFoodDao.getExtraFoodDetailList(lastUpdate);
        }

        for (ExtraFoodDetail extraFoodDetail : extraFoodDetails) {
            extraFoodDetailDto = new ExtraFoodDetailDto();
            extraFoodDetailDto.setId(extraFoodDetail.getId());
            extraFoodDetailDto.setName(extraFoodDetail.getName());
            extraFoodDetailDto.setPrice(extraFoodDetail.getPrice());
            extraFoodDetailDto.setSelected(extraFoodDetail.isSelected());
            extraFoodDetailDto.setGroupId(extraFoodDetail.getExtraFoodGroup()
                    .getId());
            extraFoodDetailDto.setLastUpdate(extraFoodDetail.getLastUpdate());
            extraFoodDetailDto.setStatus(extraFoodDetail.getStatus());
            extraFoodDetailDtoList.add(extraFoodDetailDto);
        }

        return extraFoodDetailDtoList;
    }
}
