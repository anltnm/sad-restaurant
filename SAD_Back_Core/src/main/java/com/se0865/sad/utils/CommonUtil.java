package com.se0865.sad.utils;

import com.se0865.sad.exception.NotLogInException;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

/**
 * Created by AnLTNM-SE60906 on 19/07/2015.
 */
public class CommonUtil {
    final static Logger logger = Logger.getLogger(CommonUtil.class);

    public static boolean isNotNull(Object obj) {
        if (obj != null && !obj.toString().isEmpty()) {
            return true;
        }
        return false;
    }

    public static boolean isEmpty(String str) {
        if (str != null && !str.trim().isEmpty()) {
            return false;
        }
        return true;
    }

    public static ModelAndView redirectToErrorPage(String message, String url) {
        ModelAndView model = new ModelAndView(("message"));
        model.addObject("url", url);
        model.addObject("message", message);
        return model;
    }

    public static void checkUserIsLogin(HttpServletRequest request) throws NotLogInException {
        Object userId = request.getSession().getAttribute("user_id");
        if (userId == null) {
            throw new NotLogInException("User is not login exception !");
        } else {
            String id = String.valueOf(userId);
            logger.info("User have id: " + id + " is access server");
        }
    }

    public static void saveImageFile(MultipartFile files, String imageType, String filePath) throws IOException {


        BufferedImage originalImage = ImageIO.read(files.getInputStream());
        Dimension imgSize = new Dimension(originalImage.getWidth(), originalImage.getHeight());
        Dimension boundary = new Dimension(300, 300);
        Dimension currentDimension = getScaledDimension(imgSize, boundary);

        BufferedImage bufferedImage = new BufferedImage((int) currentDimension.getWidth(), (int) currentDimension.getHeight(), BufferedImage.TYPE_INT_RGB);
        Graphics2D g = bufferedImage.createGraphics();
        g.drawImage(originalImage, 0, 0, (int) currentDimension.getWidth(), (int) currentDimension.getHeight(), null);
        g.dispose();

        ImageIO.write(bufferedImage, imageType, new File(filePath));
    }

    private static Dimension getScaledDimension(Dimension imgSize, Dimension boundary) {

        int original_width = imgSize.width;
        int original_height = imgSize.height;
        int bound_width = boundary.width;
        int bound_height = boundary.height;
        int new_width = original_width;
        int new_height = original_height;

        // first check if we need to scale width
        if (original_width > bound_width) {
            //scale width to fit
            new_width = bound_width;
            //scale height to maintain aspect ratio
            new_height = (new_width * original_height) / original_width;
        }

        // then check if we need to scale even with the new height
        if (new_height > bound_height) {
            //scale height to fit instead
            new_height = bound_height;
            //scale width to maintain aspect ratio
            new_width = (new_height * original_width) / original_height;
        }

        return new Dimension(new_width, new_height);
    }
}
