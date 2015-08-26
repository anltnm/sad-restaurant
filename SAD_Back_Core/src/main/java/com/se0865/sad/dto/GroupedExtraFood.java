package com.se0865.sad.dto;

import java.util.List;

/**
 * Created by AnLTNM-SE60906 on 20/07/2015.
 */
public class GroupedExtraFood {
    private long extraFoodGroupId;
    private List<ExtraFoodDetailDto> extraFoodDetailList;

    public long getExtraFoodGroupId() {
        return extraFoodGroupId;
    }

    public void setExtraFoodGroupId(long extraFoodGroupId) {
        this.extraFoodGroupId = extraFoodGroupId;
    }

    public List<ExtraFoodDetailDto> getExtraFoodDetailList() {
        return extraFoodDetailList;
    }

    public void setExtraFoodDetailList(List<ExtraFoodDetailDto> extraFoodDetailList) {
        this.extraFoodDetailList = extraFoodDetailList;
    }
}
