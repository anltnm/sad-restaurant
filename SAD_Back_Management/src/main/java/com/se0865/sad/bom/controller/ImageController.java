package com.se0865.sad.bom.controller;

import com.se0865.sad.configuration.AppConfig;
import org.apache.commons.io.IOUtils;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.imageio.ImageIO;
import javax.inject.Inject;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

/**
 * Created by AnLTNM-SE60906 on 01/08/2015.
 */
@Controller
@RequestMapping("/image/download")
public class ImageController {

    @Inject
    private AppConfig appConfig;

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public ResponseEntity<byte[]> getImage(@PathVariable("id") String id) throws IOException {
        InputStream ip = new FileInputStream(new File(appConfig.getImageFilePath() + id));

        final HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.IMAGE_PNG);

        return new ResponseEntity<byte[]>(IOUtils.toByteArray(ip), headers, HttpStatus.CREATED);
    }
}
