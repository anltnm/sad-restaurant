package com.se0865.sad.bom.logic;

import com.se0865.sad.configuration.ConstantManager;
import com.se0865.sad.dto.CategoryDto;
import com.se0865.sad.dto.FoodDto;
import com.se0865.sad.entities.Category;
import com.se0865.sad.entities.Food;
import com.se0865.sad.impl.CategoryDaoImpl;
import com.se0865.sad.impl.FoodDaoImpl;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by AnLTNM-SE60906 on 02/08/2015.
 */
public class CategoryManager {

    @Autowired
    CategoryDaoImpl categoryDao;

    @Autowired
    FoodDaoImpl foodDao;

    @Autowired
    FoodManager foodManager;

    public List<CategoryDto> listCategory() {
        List<Category> listCategory = categoryDao.getAllOrderByCategoryName();
        return parseListCategoryToListCategoryDto(listCategory);
    }

    public CategoryDto getCategoryDetailInfo(long id) {
        Category category = categoryDao.find(id);
        if (null != category) {
            CategoryDto categoryDto = new CategoryDto();
            categoryDto.setId(category.getId());
            categoryDto.setName(category.getName());
            return categoryDto;
        } else {
            return null;
        }
    }

    public void addNewCategory(Category category) {
        categoryDao.add(category);
    }

    public void updateCategory(Category category) {
        categoryDao.update(category);
    }

    public List<Food> getListFoodByCategory(long catId) {
        return foodDao.getFoodByCategory(catId);
    }

    public List<FoodDto> getListFoodDtoByCategory(long catId) {
        List<Food> foodList = foodDao.getFoodByCategory(catId);
        return foodManager.parseListFoodToListFoodDto(foodList);
    }

    public void updateFood(Food food) {
        foodDao.update(food);
    }


    public List<CategoryDto> searchCategoryByName(String categoryName) {
        List<Category> categoryList = categoryDao.searchCategoryByName(categoryName);
        return parseListCategoryToListCategoryDto(categoryList);
    }

    public void deleteCategory(Category category) {
        category.setStatus(ConstantManager.STATUS_DISABLE);
        category.setLastUpdate(System.currentTimeMillis());
        categoryDao.update(category);
    }

    public Category findCategoryById(long id) {
        return categoryDao.find(id);
    }

    private List<CategoryDto> parseListCategoryToListCategoryDto(List<Category> categoryList) {
        CategoryDto categoryDto = null;
        List<CategoryDto> categoryDtoList = new ArrayList<CategoryDto>();
        for (Category cat : categoryList) {
            categoryDto = new CategoryDto();
            categoryDto.setName(cat.getName());
            categoryDto.setId(cat.getId());
            categoryDtoList.add(categoryDto);
        }
        return categoryDtoList;
    }
}
