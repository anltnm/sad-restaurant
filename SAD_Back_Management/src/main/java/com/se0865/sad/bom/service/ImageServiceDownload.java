package com.se0865.sad.bom.service;

import javax.imageio.ImageIO;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Response;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;

/**
 * Created by AnLTNM-SE60906 on 30/07/2015.
 */
@Path("/download")
public class ImageServiceDownload {

    @GET
    @Path("image")
    @Produces("image/png")
    public Response getImage(@QueryParam("url") String fileName) {

        BufferedImage image = null;
        byte[] imageData = null;
        try {
            image = ImageIO.read(new File("F:\\FPT\\7-Summer2015\\Back_end_image\\url\\" + fileName));
            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            ImageIO.write(image, "png", baos);
            imageData = baos.toByteArray();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return Response.ok(new ByteArrayInputStream(imageData)).build();
    }
}
