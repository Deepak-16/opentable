package com.opentable.assignment.controller;

import com.opentable.assignment.service.GoogleCloudStorage;
import com.opentable.assignment.service.ImageService;
import com.opentable.assignment.util.Constants;
import org.apache.tomcat.util.http.fileupload.servlet.ServletFileUpload;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;
import java.io.IOException;
import java.util.List;

@Controller
public class ServletController {

    Logger logger = LoggerFactory.getLogger(ServletController.class);

    @Autowired
    GoogleCloudStorage googleCloudStorage;

    @Autowired
    ImageService imageService;

    @RequestMapping(value="/", method = RequestMethod.GET)
    public ModelAndView showLoginPage(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        ModelAndView result = new ModelAndView(Constants.View.HOME);
        List<String> list = googleCloudStorage.getList(Constants.Directory.ORIGINAL_IMAGE_DIR);
        result.addObject("image_list",list);
        return result;
    }

    @RequestMapping(value = "/upload", method = {RequestMethod.POST, RequestMethod.GET})
    public ModelAndView upload(HttpServletRequest request, HttpServletResponse response){
        ModelAndView result = new ModelAndView(Constants.View.HOME);
        try {
            if (ServletFileUpload.isMultipartContent(request)) {
                try {
                    Part filePart = request.getPart("file");
                    imageService.upload(filePart);
                    result.addObject("message", "File Uploaded Successfully");
                } catch (Exception ex) {
                    result.addObject("message", "File Upload Failed due to " + ex);
                }
            } else {
                result.addObject("message","Sorry this Servlet only handles file upload request");
            }
        }catch (Exception ex){
            logger.error("Error while upload file ", ex);
            result.addObject("message", "Error while uploading " + ex.getMessage());
        }
        List<String> list = googleCloudStorage.getList(Constants.Directory.ORIGINAL_IMAGE_DIR);
        result.addObject("image_list",list);
        return result;
    }

    @RequestMapping(value = "/list", method = {RequestMethod.GET})
    public ModelAndView list(){
        ModelAndView result = new ModelAndView(Constants.View.DASHBOARD);
        try {
            List<String> list = googleCloudStorage.getList(Constants.Directory.RESIZED_IMAGE_DIR);
            result.addObject("image_list",list);
        }catch (Exception ex){
            logger.error("Error while fetching list");
        }
        return result;
    }

    @RequestMapping(value = "/download", method = {RequestMethod.GET})
    public void download(HttpServletRequest request, HttpServletResponse response){
        try {
            String path = request.getParameter("path");
            String url = googleCloudStorage.getTemporaryFileLink(path);
            response.sendRedirect(url);
        }catch (Exception ex){
            logger.error("Error while downloading.", ex);
        }
    }

}
