package com.se0865.sad.configuration;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

/**
 * Created by AnLTNM-SE60906 on 14/05/2015.
 */
public class AppConfig {
    private static AppConfig appConfig = null;
    private Properties properties;

    protected AppConfig() throws IOException {
        properties = new Properties();
        InputStream is = AppConfig.class.getClassLoader().getResourceAsStream("app-config.properties");
        properties.load(is);
        is.close();
    }

    public static AppConfig getAppConfig() throws IOException {
        if (null == appConfig) {
            try {
                appConfig = new AppConfig();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return appConfig;
    }

    public String getValue(String key) {
        return properties.getProperty(key);
    }

    public String getJdbcUserName() {
        return getValue("jdbc.url");
    }
    public String getFoodNotFoundMessage() {
        return getValue("sad.bom.message.notFound.foodId");
    }

    public String getImageFilePath(){
        return getValue("sad.image.filePath");
    }

    public String getFoodNameEmptyMsg(){
        return getValue("sad.bom.message.food.nameIsEmpty");
    }
    public String getFoodCategoryEmptyMsg(){
        return getValue("sad.bom.message.food.categoryIsEmpty");
    }
    public String getFoodPriceEmptyMsg(){
        return getValue("sad.bom.message.food.priceIsEmpty");
    }
    public String getCategoryNotFoundMsg(){
        return getValue("sad.bom.message.category.notFound");
    }
    public String getExtraFoodGroupNotFoundMsg(){
        return getValue("sad.bom.message.extraFood.group.notFound");
    }
    public String getExtraFoodDetailNotFoundMsg(){
        return getValue("sad.bom.message.extraFood.detail.idIsNotFound");
    }
    public String getExtraFoodDetailNameNotFoundMsg(){
        return getValue("sad.bom.message.extraFood.detail.nameIsNotEmpty");
    }
    public String getExtraFoodDetailPriceNotFoundMsg(){
        return getValue("sad.bom.message.extraFood.detail.priceIsNotEmpty");
    }
    public String getUploadImageFailedMsg(){
        return getValue("sad.bom.message.write.image.fail");
    }
    public String getBomImageUrl(){
        return getValue("sad.bom.image.url");
    }
    public String getCategoryNameEmptyMsg(){
        return getValue("sad.bom.message.category.categoryNameIsEmpty");
    }
    public String getCategoryIdEmptyMsg(){
        return getValue("sad.bom.message.category.categoryIdIsEmpty");
    }
    public String getExtraFoodGroupNameEmptyMsg(){
        return getValue("sad.bom.message.extraFood.group.nameIsNotEmpty");
    }
    public String getExtraFoodGroupIdEmptyMsg(){
        return getValue("sad.bom.message.extraFood.group.idIsNotEmpty");
    }
    public String getOrderNotFoundMsg(){
        return getValue("sad.bom.message.order.notFound");
    }


}
