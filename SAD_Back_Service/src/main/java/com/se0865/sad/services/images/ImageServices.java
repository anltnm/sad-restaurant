package com.se0865.sad.services.images;

import com.se0865.sad.configuration.AppConfig;
import org.apache.commons.io.IOUtils;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;

import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Response;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

/**
 * Created by AnLTNM-SE60906 on 06/08/2015.
 */
@Path("service/image")
public class ImageServices {
    @Inject
    AppConfig appConfig;

    @GET
    @Path("download")
    public Response getImage(@QueryParam("id") String id) throws IOException {
        InputStream ip = new FileInputStream(new File(appConfig.getImageFilePath() + id));

        final HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.IMAGE_PNG);

        return Response.ok(ip).build();
    }
}
