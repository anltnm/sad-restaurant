package com.se0865.sad.bom.logic;

import com.se0865.sad.configuration.ConstantManager;
import com.se0865.sad.dto.ExtraFoodDetailDto;
import com.se0865.sad.dto.ExtraFoodGroupDto;
import com.se0865.sad.entities.ExtraFoodDetail;
import com.se0865.sad.entities.ExtraFoodGroup;
import com.se0865.sad.impl.ExtraFoodDaoImpl;
import com.se0865.sad.impl.ExtraFoodGroupDaoImpl;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by AnLTNM-SE60906 on 02/08/2015.
 */
public class ExtraFoodManager {
    @Autowired
    ExtraFoodGroupDaoImpl extraFoodGroupDao;

    @Autowired
    ExtraFoodDaoImpl extraFoodDao;

    public List<ExtraFoodGroupDto> getExtraFoodGroupList() {
        List<ExtraFoodGroup> extraFoodGroupList = extraFoodGroupDao.getAll();
        return parseExtraGroupToExtraDtoGroup(extraFoodGroupList);
    }

    public List<ExtraFoodDetailDto> getExtraFoodDetailList() {
        List<ExtraFoodDetail> extraFoodDetails = extraFoodDao.getAllOrderByExtraFoodName();
        return parseExtraDetailListToExtraDetailDtoList(extraFoodDetails);
    }

    public ExtraFoodGroupDto getExtraFoodGroupDetailInfo(long id) {
        ExtraFoodGroup extraFoodGroup = extraFoodGroupDao.find(id);
        if (null != extraFoodGroup) {
            ExtraFoodGroupDto extraFoodGroupDto = new ExtraFoodGroupDto();
            extraFoodGroupDto.setName(extraFoodGroup.getName());
            extraFoodGroupDto.setId(extraFoodGroup.getId());
            return extraFoodGroupDto;
        } else {
            return null;
        }
    }

    public ExtraFoodDetailDto getExtraFoodDetailInfo(long id) {
        ExtraFoodDetail extraFoodDetail = extraFoodDao.find(id);
        if (null != extraFoodDetail) {
            ExtraFoodDetailDto extraFoodDetailDto = new ExtraFoodDetailDto();
            extraFoodDetailDto.setName(extraFoodDetail.getName());
            extraFoodDetailDto.setId(extraFoodDetail.getId());
            extraFoodDetailDto.setPrice(extraFoodDetail.getPrice());
            extraFoodDetailDto.setGroupName(extraFoodDetail.getExtraFoodGroup().getName());
            extraFoodDetailDto.setGroupId(extraFoodDetail.getExtraFoodGroup().getId());
            return extraFoodDetailDto;
        } else {
            return null;
        }
    }

    public void addNewExtraFoodGroup(ExtraFoodGroup extraFoodGroup) {
        extraFoodGroupDao.add(extraFoodGroup);
    }

    public void updateExtraFoodGroup(ExtraFoodGroup extraFoodGroup) {
        extraFoodGroupDao.update(extraFoodGroup);
    }

    public void deleteExtraFoodGroup(ExtraFoodGroup extraFoodGroup) {
        extraFoodGroup.setLastUpdate(System.currentTimeMillis());
        extraFoodGroup.setStatus(ConstantManager.STATUS_DISABLE);
        extraFoodGroupDao.update(extraFoodGroup);
    }

    public ExtraFoodGroup findExtraFoodGroupDetail(long id) {
        return extraFoodGroupDao.find(id);
    }

    public List<ExtraFoodDetailDto> getExtraFoodByExtraFoodName(String foodName) {
        List<ExtraFoodDetail> extraFoodDetails = extraFoodDao.getExtraFoodByExtraFoodName(foodName);
        return parseExtraDetailListToExtraDetailDtoList(extraFoodDetails);
    }

    public List<ExtraFoodDetailDto> getExtraFoodByExtraFoodGroupName(String groupName) {
        List<ExtraFoodDetail> extraFoodDetails =  extraFoodDao.getExtraFoodByExtraFoodGroupName(groupName);
        return parseExtraDetailListToExtraDetailDtoList(extraFoodDetails);
    }

    public ExtraFoodDetail findExtraFoodDetail(long id) {
        return extraFoodDao.find(id);
    }

    public void addExtraFoodDetail(ExtraFoodDetail extraFoodDetail) {
        extraFoodDao.add(extraFoodDetail);
    }

    public void updateExtraFoodDetail(ExtraFoodDetail extraFoodDetail) {
        extraFoodDao.update(extraFoodDetail);
    }

    public void deleteExtraFoodDetail(ExtraFoodDetail extraFoodDetail) {
        extraFoodDetail.setLastUpdate(System.currentTimeMillis());
        extraFoodDetail.setStatus(ConstantManager.STATUS_DISABLE);
        extraFoodDao.update(extraFoodDetail);
    }

    public List<ExtraFoodGroupDto> searchExtraFoodGroupByName(String name) {
        List<ExtraFoodGroup> extraFoodGroupByName = extraFoodGroupDao.getExtraFoodGroupByName(name);
        return parseExtraGroupToExtraDtoGroup(extraFoodGroupByName);
    }

    public List<ExtraFoodDetail> getExtraFoodByExtraGroupId(ExtraFoodGroup extraFoodGroup){
        return extraFoodDao.getExtraFoodDetailsByGroup(extraFoodGroup);
    }

    public List<ExtraFoodDetailDto> getExtraFoodDetailByExtraGroupId(ExtraFoodGroup extraFoodGroup){
        List<ExtraFoodDetail> extraFoodGroups = extraFoodDao.getExtraFoodDetailsByGroup(extraFoodGroup);
        return parseExtraDetailListToExtraDetailDtoList(extraFoodGroups);
    }
    private List<ExtraFoodGroupDto> parseExtraGroupToExtraDtoGroup(List<ExtraFoodGroup> extraFoodGroups) {
        ExtraFoodGroupDto extraFoodGroupDto = null;
        List<ExtraFoodGroupDto> foodDtoList = new ArrayList<ExtraFoodGroupDto>();
        for (ExtraFoodGroup f : extraFoodGroups) {
            extraFoodGroupDto = new ExtraFoodGroupDto();
            extraFoodGroupDto.setId(f.getId());
            extraFoodGroupDto.setName(f.getName());
            foodDtoList.add(extraFoodGroupDto);
        }
        return foodDtoList;
    }

    private List<ExtraFoodDetailDto> parseExtraDetailListToExtraDetailDtoList(List<ExtraFoodDetail> extraFoodDetails) {
        ExtraFoodDetailDto extraFoodDetailDto = null;
        List<ExtraFoodDetailDto> extraFoodDetailDtoList = new ArrayList<ExtraFoodDetailDto>();
        for (ExtraFoodDetail f : extraFoodDetails) {
            extraFoodDetailDto = new ExtraFoodDetailDto();
            extraFoodDetailDto.setId(f.getId());
            extraFoodDetailDto.setName(f.getName());
            extraFoodDetailDto.setPrice(f.getPrice());
            extraFoodDetailDto.setGroupId(f.getExtraFoodGroup().getId());
            extraFoodDetailDto.setGroupName(f.getExtraFoodGroup().getName());
            extraFoodDetailDtoList.add(extraFoodDetailDto);
        }
        return extraFoodDetailDtoList;
    }
}
