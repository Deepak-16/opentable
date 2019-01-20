package com.opentable.assignment;

import com.google.api.gax.paging.Page;
import com.google.auth.Credentials;
import com.google.auth.oauth2.GoogleCredentials;
import com.google.cloud.storage.Blob;
import com.google.cloud.storage.Bucket;
import com.google.cloud.storage.Storage;
import com.google.cloud.storage.StorageOptions;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import java.io.FileInputStream;
import java.nio.file.Files;
import java.nio.file.Paths;

@Controller
public class Example {

    @RequestMapping(value="/login", method = RequestMethod.GET)
    public ModelAndView showLoginPage(ModelMap model){
        return new ModelAndView("login");
    }

    @RequestMapping("/")
    String home() {
        try {
            Credentials credentials = GoogleCredentials
                    .fromStream(new FileInputStream("F:\\OpenTable-f4b9416be045.json"));
            Storage storage = StorageOptions.newBuilder().setCredentials(credentials)
                    .setProjectId("opentable").build().getService();
            Bucket bucket = storage.get("opentable.appspot.com");
            String value = "Test blob!";
            byte[] bytes = Files.readAllBytes(Paths.get("F:\\WhatsApp Image 2018-10-26 at 23.33.28.jpeg"));
            Blob blob1 = bucket.create("blob-2", bytes);
            Page<Blob> blobs = bucket.list();
            for (Blob blob: blobs.getValues()) {
                if ("blob-2".equals(blob.getName())) {
                    return new String(blob.getContent());
                }
            }
        }catch (Exception e){
            return "Error"+e;
        }

        return "Hello World!";
    }

}
