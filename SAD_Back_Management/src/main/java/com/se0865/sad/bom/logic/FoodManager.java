package com.se0865.sad.bom.logic;

import com.se0865.sad.configuration.AppConfig;
import com.se0865.sad.configuration.ConstantManager;
import com.se0865.sad.dto.CategoryDto;
import com.se0865.sad.dto.ExtraFoodGroupDto;
import com.se0865.sad.dto.FoodDto;
import com.se0865.sad.entities.Category;
import com.se0865.sad.entities.ExtraFoodGroup;
import com.se0865.sad.entities.Food;
import com.se0865.sad.impl.CategoryDaoImpl;
import com.se0865.sad.impl.ExtraFoodGroupDaoImpl;
import com.se0865.sad.impl.FoodDaoImpl;
import org.springframework.beans.factory.annotation.Autowired;

import java.security.CodeSigner;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by AnLTNM-SE60906 on 29/07/2015.
 */
public class FoodManager {
    @Autowired
    FoodDaoImpl foodDao;

    @Autowired
    ExtraFoodGroupDaoImpl extraFoodGroupDao;

    @Autowired
    CategoryDaoImpl categoryDao;

    @Autowired
    AppConfig appConfig;

    public List<FoodDto> getOrderList() {
        List<Food> foodList = foodDao.getAllOrderByFoodName();

        Food f = null;
        List<ExtraFoodGroup> extraFoodGroups = null;
        int size = foodList.size();
        List<ExtraFoodGroup> extraFoodGroupEnable = null;
        for (int i = 0; i < size; i++) {
            f = foodList.get(i);
            extraFoodGroupEnable = new ArrayList<ExtraFoodGroup>();
            extraFoodGroups = f.getExtraFoodGroupList();
            for (ExtraFoodGroup ex : extraFoodGroups) {
                if (ex.getStatus() != ConstantManager.STATUS_DISABLE) {
                    extraFoodGroupEnable.add(ex);
                }
            }
            f.setExtraFoodGroupList(extraFoodGroupEnable);
            foodList.set(i, f);
        }
        return parseListFoodToListFoodDto(foodList);
    }

    public Category findCategoryById(Long id) {
        return categoryDao.find(id);
    }

    public ExtraFoodGroup findExtraFoodGroupById(Long id) {
        return extraFoodGroupDao.find(id);
    }

    public Food findFoodById(Long id) {
        return foodDao.find(id);
    }

    public void updateFood(Food food) {
        foodDao.update(food);
    }

    public void addFood(Food food) {
        foodDao.add(food);
    }

    public void deleteFood(Food food) {
        food.setStatus(ConstantManager.STATUS_DISABLE);
        food.setLastUpdate(System.currentTimeMillis());
        foodDao.update(food);
    }

    public List<FoodDto> searchFoodByFoodName(String name) {
        List<Food> foodGroupedByName = foodDao.searchFoodByName(name);
        // parse food to foodDto neu modify nho modify o tren luon
        return parseListFoodToListFoodDto(foodGroupedByName);
    }

    public List<FoodDto> searchFoodByFoodCategoryName(String categoryName) {
        List<Food> foodGroupedByName = foodDao.searchFoodByCategoryName(categoryName);
        // parse food to foodDto neu modify nho modify o tren luon
        return parseListFoodToListFoodDto(foodGroupedByName);
    }

    public List<ExtraFoodGroupDto> getListExtraFoodGroup() {
        List<ExtraFoodGroup> extraFoodGroups = extraFoodGroupDao.getAll();
        ExtraFoodGroupDto extraFoodGroupDto = null;
        List<ExtraFoodGroupDto> extraFoodGroupDtoList = new ArrayList<ExtraFoodGroupDto>();
        for (ExtraFoodGroup exGroup : extraFoodGroups) {
            extraFoodGroupDto = new ExtraFoodGroupDto();
            extraFoodGroupDto.setName(exGroup.getName());
            extraFoodGroupDto.setId(exGroup.getId());
            extraFoodGroupDtoList.add(extraFoodGroupDto);
        }
        return extraFoodGroupDtoList;
    }

    public List<CategoryDto> getListCategory() {
        List<Category> categories = categoryDao.getAll();
        CategoryDto categoryDto = null;
        List<CategoryDto> categoryDtoList = new ArrayList<CategoryDto>();
        for (Category c : categories) {
            categoryDto = new CategoryDto();
            categoryDto.setId(c.getId());
            categoryDto.setName(c.getName());
            categoryDtoList.add(categoryDto);
        }
        return categoryDtoList;
    }

    public FoodDto getFoodDetailInfor(long id) {
        Food food = foodDao.find(id);
        if (null != food) {
            FoodDto foodDto = new FoodDto();
            foodDto.setId(food.getId());
            foodDto.setName(food.getName());
            foodDto.setPrice(food.getPrice());
            foodDto.setIngredient(food.getIngredient());
            foodDto.setImageUrl(appConfig.getBomImageUrl() + food.getImageUrl());

            CategoryDto categoryDto = new CategoryDto();

            categoryDto.setName(food.getCategory().getName());
            categoryDto.setId(food.getCategory().getId());
            foodDto.setCategory(categoryDto);

            List<ExtraFoodGroup> extraFoodGroups = food.getExtraFoodGroupList();
            List<ExtraFoodGroupDto> extraFoodGroupList = new ArrayList<ExtraFoodGroupDto>();
            ExtraFoodGroupDto extraGroupDto = null;

            for (ExtraFoodGroup extraGroup : extraFoodGroups) {
                if (extraGroup.getStatus() == ConstantManager.STATUS_ENABLE) {
                    extraGroupDto = new ExtraFoodGroupDto();
                    extraGroupDto.setName(extraGroup.getName());
                    extraGroupDto.setId(extraGroup.getId());
                    extraFoodGroupList.add(extraGroupDto);
                }
            }

            foodDto.setExtraFoodGroupList(extraFoodGroupList);
            return foodDto;
        } else {
            return null;
        }
    }

    public List<FoodDto> parseListFoodToListFoodDto(List<Food> foods) {
        FoodDto foodDto = null;
        List<FoodDto> foodDtoList = new ArrayList<FoodDto>();
        for (Food f : foods) {
            foodDto = new FoodDto();
            foodDto.setName(f.getName());
            foodDto.setId(f.getId());


            CategoryDto categoryDto = new CategoryDto();
            categoryDto.setName(f.getCategory().getName());
            categoryDto.setId(f.getCategory().getId());

            foodDto.setCategory(categoryDto);
            foodDto.setPrice(f.getPrice());
            foodDto.setLastUpdate(f.getLastUpdate());
            foodDto.setImageUrl(appConfig.getBomImageUrl() + f.getImageUrl());

            List<ExtraFoodGroup> extraFoodGroups = f.getExtraFoodGroupList();
            List<ExtraFoodGroupDto> extraFoodGroupList = new ArrayList<ExtraFoodGroupDto>();
            ExtraFoodGroupDto extraGroupDto = null;

            for (ExtraFoodGroup extraGroup : extraFoodGroups) {
                extraGroupDto = new ExtraFoodGroupDto();
                extraGroupDto.setName(extraGroup.getName());
                extraGroupDto.setId(extraGroup.getId());
                extraFoodGroupList.add(extraGroupDto);
            }

            foodDto.setExtraFoodGroupList(extraFoodGroupList);
            foodDtoList.add(foodDto);
        }
        return foodDtoList;
    }
}
