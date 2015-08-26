package com.se0865.sad.dao;

import com.se0865.sad.entities.ExtraFoodDetail;
import com.se0865.sad.entities.ExtraFoodGroup;

import java.util.List;

/**
 * Created by AnLTNM-SE60906 on 20/07/2015.
 */
public interface ExtraFoodDao {
    public List<ExtraFoodDetail> getExtraFoodDetailsByGroup(ExtraFoodGroup extraFoodGroup);
}
