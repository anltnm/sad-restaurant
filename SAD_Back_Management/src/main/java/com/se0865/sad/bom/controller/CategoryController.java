package com.se0865.sad.bom.controller;

import com.se0865.sad.bom.logic.CategoryManager;
import com.se0865.sad.bom.logic.FoodManager;
import com.se0865.sad.configuration.AppConfig;
import com.se0865.sad.configuration.ConstantManager;
import com.se0865.sad.dto.BomMessage;
import com.se0865.sad.dto.CategoryDto;
import com.se0865.sad.dto.FoodDto;
import com.se0865.sad.entities.Category;
import com.se0865.sad.entities.Food;
import com.se0865.sad.exception.NotLogInException;
import com.se0865.sad.utils.CommonUtil;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;

import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * Created by AnLTNM-SE60906 on 02/08/2015.
 */
@Controller
@RequestMapping("category")
public class CategoryController {
    final static Logger logger = Logger.getLogger(CategoryController.class);

    @Inject
    CategoryManager categoryManager;

    @Inject
    private CommonUtil util;

    @Inject
    private AppConfig appConfig;

    @RequestMapping(value = "listCategory", method = RequestMethod.GET)
    public ModelAndView listCategory() {
        ModelAndView model = new ModelAndView("listCategory");
        List<CategoryDto> categoryDtoList = categoryManager.listCategory();
        model.addObject("listCategory", categoryDtoList);
        return model;
    }

    @RequestMapping(value = "categoryDetail", method = RequestMethod.GET, params = {"id"})
    public ModelAndView foodDetail(@RequestParam("id") String id,
                                   HttpServletRequest request) {
        try {
            util.checkUserIsLogin(request);
        } catch (NotLogInException e) {
            ModelAndView loginPage = new ModelAndView(ConstantManager.REDIRECT_PAGE);
            return loginPage;
        }

        CategoryDto categoryDto;
        if (null != id && id.trim().length() != 0) {
            long parsedId = 0;
            try {
                parsedId = Long.valueOf(id);
            } catch (NumberFormatException e) {
                logger.info("Not found category_id: " + id);
            }
            if (parsedId != 0) {
                categoryDto = categoryManager.getCategoryDetailInfo(parsedId);
            } else {
                //TODO show error message!
                categoryDto = null;
            }
        } else {
            categoryDto = null;
        }
        ModelAndView model = new ModelAndView("categoryDetail");
        model.addObject("categoryDetail", categoryDto);
        model.addObject("viewMode", "view");
        return model;
    }

    @RequestMapping(value = "updateCategoryView", method = RequestMethod.GET, params = {"id"})
    public ModelAndView updateFood(@RequestParam("id") String id,
                                   HttpServletRequest request) {
        try {
            util.checkUserIsLogin(request);
        } catch (NotLogInException e) {
            ModelAndView loginPage = new ModelAndView(ConstantManager.REDIRECT_PAGE);
            return loginPage;
        }

        CategoryDto categoryDto;
        if (null != id && id.trim().length() != 0) {
            long parsedId = 0;
            try {
                parsedId = Long.valueOf(id);
            } catch (NumberFormatException e) {
                logger.info("Not found food_id: " + id);
            }

            if (parsedId != 0) {
                categoryDto = categoryManager.getCategoryDetailInfo(parsedId);
            } else {
                //TODO show error message!
                categoryDto = null;
            }
        } else {
            categoryDto = null;
        }
        ModelAndView model = new ModelAndView("categoryDetail");
        model.addObject("categoryDetail", categoryDto);
        model.addObject("viewMode", "update");
        return model;
    }

    @RequestMapping(value = "addCategoryView", method = RequestMethod.GET)
    public ModelAndView updateFood(HttpServletRequest request) {

        try {
            util.checkUserIsLogin(request);
        } catch (NotLogInException e) {
            ModelAndView loginPage = new ModelAndView(ConstantManager.REDIRECT_PAGE);
            return loginPage;
        }

        ModelAndView model = new ModelAndView("categoryDetail");
        model.addObject("viewMode", "add");
        return model;
    }

    @RequestMapping(value = "add_category", method = RequestMethod.POST, params = {"name"})
    public ModelAndView addCategory(@RequestParam("name") String name,
                                    HttpServletRequest request) {
        try {
            util.checkUserIsLogin(request);
        } catch (NotLogInException e) {
            ModelAndView loginPage = new ModelAndView(ConstantManager.REDIRECT_PAGE);
            return loginPage;
        }

        String message = "";
        if (util.isEmpty(name)) {
            message = appConfig.getCategoryNameEmptyMsg();
        } else {
            Category category = new Category();
            category.setName(name);
            category.setStatus(ConstantManager.STATUS_ENABLE);
            category.setCreatedDate(System.currentTimeMillis());
            category.setLastUpdate(System.currentTimeMillis());
            categoryManager.addNewCategory(category);
            ModelAndView model = new ModelAndView(new RedirectView("listCategory"));
            return model;
        }
        return util.redirectToErrorPage(message, "listCategory");
    }

    @RequestMapping(value = "update_category", method = RequestMethod.POST, params = {"name", "id"})
    public ModelAndView addCategory(@RequestParam("name") String name,
                                    @RequestParam("id") String id,
                                    HttpServletRequest request) {
        try {
            util.checkUserIsLogin(request);
        } catch (NotLogInException e) {
            ModelAndView loginPage = new ModelAndView(ConstantManager.REDIRECT_PAGE);
            return loginPage;
        }

        String message = "";
        if (util.isEmpty(name)) {
            message = appConfig.getCategoryNameEmptyMsg();
        } else if (util.isEmpty(id)) {
            message = appConfig.getCategoryIdEmptyMsg();
        } else {
            Category category = null;
            try {
                category = categoryManager.findCategoryById(Long.valueOf(id));
            } catch (NumberFormatException e) {
                message = appConfig.getCategoryNotFoundMsg();
                return util.redirectToErrorPage(message, "listCategory");
            }
            if (null == category) {
                message = appConfig.getCategoryNotFoundMsg();
                return util.redirectToErrorPage(message, "listCategory");
            }

            category.setName(name);
            category.setStatus(ConstantManager.STATUS_ENABLE);
            category.setLastUpdate(System.currentTimeMillis());
            categoryManager.updateCategory(category);
            List<Food> foods = categoryManager.getListFoodByCategory(category.getId());
            for(Food food: foods){
                food.setLastUpdate(System.currentTimeMillis());
                categoryManager.updateFood(food);
            }
            ModelAndView model = new ModelAndView(new RedirectView("listCategory"));
            return model;
        }
        return util.redirectToErrorPage(message, "listCategory");
    }

    @RequestMapping(value = "delete_category", method = RequestMethod.POST, params = {"categoryId"})
    public
    @ResponseBody
    BomMessage addFoodPost(@RequestParam("categoryId") String productId) {
        BomMessage bomMessage = new BomMessage();
        long id = 0;
        try {
            id = Long.valueOf(productId);
        } catch (NumberFormatException e) {
            logger.info("Input value is invalid + " + productId);
            bomMessage.setStatusCode(ConstantManager.STATUS_CODE_FAIL);
            bomMessage.setMessage("Cannot find category");
            return bomMessage;
        }
        Category f = categoryManager.findCategoryById(id);
        if (f != null) {
            categoryManager.deleteCategory(f);
            List<Food> foods = categoryManager.getListFoodByCategory(f.getId());
            for(Food food: foods){
                food.setStatus(ConstantManager.STATUS_DISABLE);
                food.setLastUpdate(System.currentTimeMillis());
                categoryManager.updateFood(food);
            }
            bomMessage.setMessage("Delete category successfully");
        } else {
            logger.info("Input value is invalid + " + productId);
            bomMessage.setStatusCode(ConstantManager.STATUS_CODE_FAIL);
            bomMessage.setMessage("Cannot find category");
            return bomMessage;
        }
        return bomMessage;
    }

    @RequestMapping(value = "search", method = RequestMethod.GET, params = {"keyword"})
    public ModelAndView searchFood(@RequestParam("keyword") String text,
                                   HttpServletRequest request) {
        try {
            util.checkUserIsLogin(request);
        } catch (NotLogInException e) {
            ModelAndView loginPage = new ModelAndView(ConstantManager.REDIRECT_PAGE);
            return loginPage;
        }

        ModelAndView model = new ModelAndView("listCategory");
        if (!util.isEmpty(text)) {
            List<CategoryDto> searchByNameFoodList = null;
            searchByNameFoodList = categoryManager.searchCategoryByName(text);
            model.addObject("listCategory", searchByNameFoodList);
            model.addObject("keyword", text);
        }

        return model;
    }

    @RequestMapping(value = "foodGroupByCategory", method = RequestMethod.GET, params = {"id"})
    public ModelAndView getFoodListGroupByCategory(@RequestParam("id") String id,
                                   HttpServletRequest request) {
        try {
            util.checkUserIsLogin(request);
        } catch (NotLogInException e) {
            ModelAndView loginPage = new ModelAndView(ConstantManager.REDIRECT_PAGE);
            return loginPage;
        }

        ModelAndView model = new ModelAndView("listFood");
        long categoryId = 0;
        if (!util.isEmpty(id)) {
            try {
                categoryId = Long.valueOf(id);
            } catch (NumberFormatException e) {
                logger.info("Input value is invalid + " + id);
            }
            List<FoodDto> searchByNameFoodList = categoryManager.getListFoodDtoByCategory(categoryId);
            model.addObject("listFood", searchByNameFoodList);
        }

        return model;
    }
}
