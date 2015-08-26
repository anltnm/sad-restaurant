package com.se0865.sad.bom.controller;

import com.se0865.sad.bom.logic.FoodManager;
import com.se0865.sad.configuration.AppConfig;
import com.se0865.sad.configuration.ConstantManager;
import com.se0865.sad.dto.BomMessage;
import com.se0865.sad.dto.CategoryDto;
import com.se0865.sad.dto.ExtraFoodGroupDto;
import com.se0865.sad.dto.FoodDto;
import com.se0865.sad.entities.Category;
import com.se0865.sad.entities.ExtraFoodGroup;
import com.se0865.sad.entities.Food;
import com.se0865.sad.exception.NotLogInException;
import com.se0865.sad.utils.CommonUtil;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;

import javax.imageio.ImageIO;
import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by AnLTNM-SE60906 on 29/07/2015.
 */
@Controller
@RequestMapping("/food/")
public class FoodController {
    final static Logger logger = Logger.getLogger(FoodController.class);

    @Inject
    private FoodManager foodManager;

    @Inject
    private AppConfig appConfig;

    @Inject
    private CommonUtil util;


    @RequestMapping(value = "listFood", method = RequestMethod.GET)
    public ModelAndView listFood(HttpServletRequest request) {
        try {
            util.checkUserIsLogin(request);
        } catch (NotLogInException e) {
            ModelAndView loginPage = new ModelAndView(ConstantManager.REDIRECT_PAGE);
            return loginPage;
        }

        ModelAndView model = new ModelAndView("listFood");
        List<FoodDto> foodDtoList = foodManager.getOrderList();
        model.addObject("listFood", foodDtoList);
        return model;
    }

    @RequestMapping(value = "food_detail", method = RequestMethod.GET, params = {"id"})
    public ModelAndView foodDetail(@RequestParam("id") String id,
                                   HttpServletRequest request) {
        try {
            util.checkUserIsLogin(request);
        } catch (NotLogInException e) {
            ModelAndView loginPage = new ModelAndView(ConstantManager.REDIRECT_PAGE);
            return loginPage;
        }

        FoodDto foodDto;
        if (null != id && id.trim().length() != 0) {
            long parsedId = 0;
            try {
                parsedId = Long.valueOf(id);
            } catch (NumberFormatException e) {
                logger.info("Not found food_id: " + id);
            }
            if (parsedId != 0) {
                foodDto = foodManager.getFoodDetailInfor(parsedId);
            } else {
                //TODO show error message!
                foodDto = null;
            }
        } else {
            foodDto = null;
        }
        ModelAndView model = new ModelAndView("foodDetail");
        model.addObject("foodDetail", foodDto);
        model.addObject("viewMode", "view");
        return model;
    }

    @RequestMapping(value = "addFodView", method = RequestMethod.GET)
    public ModelAndView addFoodView(HttpServletRequest request) {
        try {
            util.checkUserIsLogin(request);
        } catch (NotLogInException e) {
            ModelAndView loginPage = new ModelAndView(ConstantManager.REDIRECT_PAGE);
            return loginPage;
        }

        FoodDto foodDto = null;
        List<ExtraFoodGroupDto> extraFoodGroupDto = null;
        List<CategoryDto> categoryDtoList = null;
        extraFoodGroupDto = foodManager.getListExtraFoodGroup();
        categoryDtoList = foodManager.getListCategory();

        ModelAndView model = new ModelAndView("foodDetail");
        model.addObject("foodDetail", foodDto);
        model.addObject("extraFoodGroup", extraFoodGroupDto);
        model.addObject("category", categoryDtoList);
        model.addObject("viewMode", "add");
        return model;
    }

    @RequestMapping(value = "add_food", method = RequestMethod.POST, params = {"foodName", "foodName", "foodPrice",
            "foodIngredient", "extraFood[]"})
    public ModelAndView addFoodPost(@RequestParam("foodName") String name,
                                    @RequestParam("foodCategory") String foodCategory,
                                    @RequestParam("foodPrice") String price,
                                    @RequestParam("foodIngredient") String ingredient,
                                    @RequestParam("extraFood[]") String[] extraFoods,
                                    @RequestParam("file") MultipartFile files,
                                    HttpServletRequest request) {
        try {
            util.checkUserIsLogin(request);
        } catch (NotLogInException e) {
            ModelAndView loginPage = new ModelAndView(ConstantManager.REDIRECT_PAGE);
            return loginPage;
        }

        Food foodDto = new Food();
        String message = "";
        ModelAndView model = null;

        if (util.isEmpty(name)) {
            message = appConfig.getFoodNameEmptyMsg();
        } else if (util.isEmpty(foodCategory)) {
            message = appConfig.getFoodCategoryEmptyMsg();
        } else if (util.isEmpty(price)) {
            message = appConfig.getFoodPriceEmptyMsg();
        } else {

            Category category = foodManager.findCategoryById(Long.valueOf(foodCategory));
            if (category == null) {
                message = appConfig.getCategoryNotFoundMsg();
                return redirectToErrorPage(message, "listFood");
            }

            List<ExtraFoodGroup> extraFoodGroups = new ArrayList<ExtraFoodGroup>();
            if (null != extraFoods && extraFoods.length > 0) {
                for (String str : extraFoods) {
                    if (str.equals("-1")) {
                        break;
                    }
                    if (!util.isEmpty(str)) {
                        try {
                            ExtraFoodGroup extraFoodGroup = foodManager.findExtraFoodGroupById(Long.valueOf(str));
                            if (null == extraFoodGroup) {
                                message = appConfig.getExtraFoodGroupNotFoundMsg();
                                return redirectToErrorPage(message, "listFood");
                            } else {
                                extraFoodGroups.add(extraFoodGroup);
                            }
                        } catch (NumberFormatException e) {
                            message = appConfig.getExtraFoodGroupNotFoundMsg();
                            return redirectToErrorPage(message, "listFood");
                        }
                    }
                }
            }

            String imageUrl = null;
            if (!files.isEmpty()) {
                StringBuilder fileName = new StringBuilder();
                fileName.append(System.currentTimeMillis());

                String contentType = files.getContentType();
                String contentStr = "png";
                if (contentType.toUpperCase().contains("JPG") || contentType.toUpperCase().contains("JPEG")) {
                    contentStr = "jpg";
                }
                StringBuilder filePath = new StringBuilder(appConfig.getImageFilePath()).append(fileName);
                try {
                    util.saveImageFile(files, contentStr, filePath.toString());
                } catch (Exception e) {
                    logger.info("You failed to upload " + filePath.toString() + ": " + e.getMessage() + "<br/>");
                    message = appConfig.getUploadImageFailedMsg();
                    return redirectToErrorPage(message, "listFood");
                }
                imageUrl = fileName.toString();
            }
            foodDto.setName(name);
            foodDto.setPrice(Float.valueOf(price));
            foodDto.setCategory(category);
            foodDto.setExtraFoodGroupList(extraFoodGroups);
            foodDto.setImageUrl(imageUrl);
            foodDto.setIngredient(ingredient);
            foodDto.setCreatedDate(System.currentTimeMillis());
            foodDto.setLastUpdate(System.currentTimeMillis());
            foodDto.setStatus(ConstantManager.STATUS_ENABLE);
            foodManager.addFood(foodDto);
            model = new ModelAndView(new RedirectView("listFood"));
            return model;
        }
        return redirectToErrorPage(message, "listFood");
    }

    @RequestMapping(value = "updateFoodView", method = RequestMethod.GET, params = {"id"})
    public ModelAndView updateFood(@RequestParam("id") String id,
                                   HttpServletRequest request) {
        try {
            util.checkUserIsLogin(request);
        } catch (NotLogInException e) {
            ModelAndView loginPage = new ModelAndView(ConstantManager.REDIRECT_PAGE);
            return loginPage;
        }

        FoodDto foodDto;
        List<ExtraFoodGroupDto> extraFoodGroupDto = null;
        List<CategoryDto> categoryDtoList = null;
        if (null != id && id.trim().length() != 0) {
            long parsedId = 0;
            try {
                parsedId = Long.valueOf(id);
            } catch (NumberFormatException e) {
                logger.info("Not found food_id: " + id);
            }

            if (parsedId != 0) {
                foodDto = foodManager.getFoodDetailInfor(parsedId);
                extraFoodGroupDto = foodManager.getListExtraFoodGroup();
                categoryDtoList = foodManager.getListCategory();
            } else {
                //TODO show error message!
                foodDto = null;
            }
        } else {
            foodDto = null;
        }
        ModelAndView model = new ModelAndView("foodDetail");
        model.addObject("foodDetail", foodDto);
        model.addObject("extraFoodGroup", extraFoodGroupDto);
        model.addObject("category", categoryDtoList);
        model.addObject("viewMode", "update");
        return model;
    }

    @RequestMapping(value = "search", method = RequestMethod.GET, params = {"keyword", "category"})
    public ModelAndView searchFood(@RequestParam("keyword") String text,
                                   @RequestParam("category") String category,
                                   HttpServletRequest request) {
        try {
            util.checkUserIsLogin(request);
        } catch (NotLogInException e) {
            ModelAndView loginPage = new ModelAndView(ConstantManager.REDIRECT_PAGE);
            return loginPage;
        }

        ModelAndView model = new ModelAndView("listFood");
        if (!util.isEmpty(text) && !util.isEmpty(category)) {
            List<FoodDto> searchByNameFoodList = null;
            if (category.equals("name")) {
                searchByNameFoodList = foodManager.searchFoodByFoodName(text);
                model.addObject("listFood", searchByNameFoodList);
            } else if (category.equals("cat")) {
                searchByNameFoodList = foodManager.searchFoodByFoodCategoryName(text);
            }

            model.addObject("listFood", searchByNameFoodList);
            model.addObject("keyword", text);
            model.addObject("category", category);
        }

        return model;
    }

    @RequestMapping(value = "update_food", method = RequestMethod.POST, params = {"id", "foodName", "foodName", "foodPrice", "foodIngredient", "extraFood[]"})
    public ModelAndView updateFoodPost(@RequestParam("id") String id,
                                       @RequestParam("foodName") String name,
                                       @RequestParam("foodCategory") String foodCategory,
                                       @RequestParam("foodPrice") String price,
                                       @RequestParam("foodIngredient") String ingredient,
                                       @RequestParam("extraFood[]") String[] extraFoods,
                                       @RequestParam("file") MultipartFile files,
                                       HttpServletRequest request) {

        try {
            util.checkUserIsLogin(request);
        } catch (NotLogInException e) {
            ModelAndView loginPage = new ModelAndView(ConstantManager.REDIRECT_PAGE);
            return loginPage;
        }

        Food foodDto = null;
        String message = "";
        ModelAndView model = null;
        if (null != id && id.trim().length() != 0) {
            long parsedId = 0;
            try {
                parsedId = Long.valueOf(id);
            } catch (NumberFormatException e) {
                logger.info("Not found food_id: " + id);
                message = appConfig.getFoodNotFoundMessage();
                return redirectToErrorPage(message, "listFood");
            }
            if (parsedId != 0) {
                try {
                    foodDto = foodManager.findFoodById(Long.valueOf(parsedId));
                } catch (NumberFormatException e) {
                    logger.info("Not found food_id: " + id);
                    message = appConfig.getFoodNotFoundMessage();
                    return redirectToErrorPage(message, "listFood");
                }
            } else {
                logger.info("Not found food_id: " + id);
                message = appConfig.getFoodNotFoundMessage();
                return redirectToErrorPage(message, "listFood");
            }

            if (null == foodDto) {
                logger.info("Not found food_id: " + id);
                message = appConfig.getFoodNotFoundMessage();
                return redirectToErrorPage(message, "listFood");
            } else {

                if (util.isEmpty(name)) {
                    message = appConfig.getFoodNameEmptyMsg();
                } else if (util.isEmpty(foodCategory)) {
                    message = appConfig.getFoodCategoryEmptyMsg();
                } else if (util.isEmpty(price)) {
                    message = appConfig.getFoodPriceEmptyMsg();
                } else {

                    Category category = foodManager.findCategoryById(Long.valueOf(foodCategory));
                    if (category == null) {
                        message = appConfig.getCategoryNotFoundMsg();
                        return redirectToErrorPage(message, "listFood");
                    }

                    List<ExtraFoodGroup> extraFoodGroups = new ArrayList<ExtraFoodGroup>();
                    if (null != extraFoods && extraFoods.length > 0) {
                        for (String str : extraFoods) {
                            if (str.equals("-1")) {
                                break;
                            }
                            if (!util.isEmpty(str)) {
                                try {
                                    ExtraFoodGroup extraFoodGroup = foodManager.findExtraFoodGroupById(Long.valueOf(str));
                                    if (null == extraFoodGroup) {
                                        message = appConfig.getExtraFoodGroupNotFoundMsg();
                                        return redirectToErrorPage(message, "listFood");
                                    } else {
                                        extraFoodGroups.add(extraFoodGroup);
                                    }
                                } catch (NumberFormatException e) {
                                    message = appConfig.getExtraFoodGroupNotFoundMsg();
                                    return redirectToErrorPage(message, "listFood");
                                }
                            }
                        }
                    }
                    String imageUrl = null;
                    if (!files.isEmpty()) {
                        StringBuilder fileName = new StringBuilder();
                        fileName.append(System.currentTimeMillis());

                        String contentType = files.getContentType();
                        String contentStr = "png";
                        if (contentType.toUpperCase().contains("JPG") || contentType.toUpperCase().contains("JPEG")) {
                            contentStr = "jpg";
                        }
                        StringBuilder filePath = new StringBuilder(appConfig.getImageFilePath()).append(fileName);
                        try {
                            util.saveImageFile(files, contentStr, filePath.toString());
                        } catch (Exception e) {
                            logger.info("You failed to upload " + filePath.toString() + ": " + e.getMessage() + "<br/>");
                            message = appConfig.getUploadImageFailedMsg();
                            return redirectToErrorPage(message, "listFood");
                        }
                        imageUrl = fileName.toString();
                    } else if (!util.isEmpty(foodDto.getImageUrl())) {
                        imageUrl = foodDto.getImageUrl();
                    }

                    foodDto.setName(name);
                    foodDto.setPrice(Float.valueOf(price));
                    foodDto.setCategory(category);
                    foodDto.setExtraFoodGroupList(extraFoodGroups);
                    foodDto.setImageUrl(imageUrl);
                    foodDto.setIngredient(ingredient);
                    foodDto.setLastUpdate(System.currentTimeMillis());
                    foodManager.updateFood(foodDto);

                    model = new ModelAndView(new RedirectView("listFood"));
                    return model;
                }
                return redirectToErrorPage(message, "listFood");
            }
        } else {
            logger.info("Not found food_id: " + id);
            message = appConfig.getFoodNotFoundMessage();
            return redirectToErrorPage(message, "listFood");
        }

    }

    @RequestMapping(value = "delete_food", method = RequestMethod.POST, params = {"productId"})
    public
    @ResponseBody
    BomMessage addFoodPost(@RequestParam("productId") String productId) {
        BomMessage bomMessage = new BomMessage();
        long id = 0;
        try {
            id = Long.valueOf(productId);
        } catch (NumberFormatException e) {
            logger.info("Input value is invalid + " + productId);
            bomMessage.setStatusCode(ConstantManager.STATUS_CODE_FAIL);
            bomMessage.setMessage("Cannot find food");
            return bomMessage;
        }
        Food f = foodManager.findFoodById(id);
        if (f != null) {
            foodManager.deleteFood(f);
            bomMessage.setMessage("Delete food successfully");
        } else {
            logger.info("Input value is invalid + " + productId);
            bomMessage.setStatusCode(ConstantManager.STATUS_CODE_FAIL);
            bomMessage.setMessage("Cannot find food");
            return bomMessage;
        }
        return bomMessage;
    }

    @RequestMapping(value = "delete", method = RequestMethod.GET, params = {"id"})
    public ModelAndView deleteFood(@RequestParam("id") String id) {

        ModelAndView model = new ModelAndView("foodDetail");
        model.addObject("msg", "hello world");
        return model;
    }


    private ModelAndView redirectToErrorPage(String message, String url) {
        ModelAndView model = new ModelAndView(new RedirectView("message"));
        model.addObject("url", url);
        model.addObject("message", message);
        return model;
    }


}
