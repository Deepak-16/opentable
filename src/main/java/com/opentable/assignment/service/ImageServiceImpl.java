package com.opentable.assignment.service;

import com.opentable.assignment.util.Constants;
import com.opentable.assignment.util.ImageModificationUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.*;


@Service
public class ImageServiceImpl {

    Logger logger = LoggerFactory.getLogger(ImageServiceImpl.class);

    @Autowired
    GoogleCloudStorage googleCloudStorage;

    Set<String> filesProcessed = new HashSet<>();

    @PostConstruct
    public void resizeCron(){
            TimerTask repeatedTask = new TimerTask() {
                public void run() {
                    List<String> list = googleCloudStorage.getList(Constants.Directory.ORIGINAL_IMAGE_DIR);
                    for (String file : list) {
                        Path tempFile = null;
                        if (!filesProcessed.contains(file)) {
                            try {
                                byte[] image = googleCloudStorage.downloadFile(file);
                                String format = file.substring(file.lastIndexOf("."));
                                tempFile = Files.createTempFile("resized", format);
                                ImageModificationUtil.resize(image, tempFile.getFileName().toAbsolutePath().toString());
                                //googleCloudStorage.uploadFile(new FileInputStream(tempFile.getFileName().toAbsolutePath().toString()),
                                //       file.substring(file.indexOf("/")+1), Constants.Directory.RESIZED_IMAGE_DIR);
                            }catch (Exception ex){
                                logger.error("Error while running resize cron.",ex);
                            }finally {
                                try {
                                    Files.delete(tempFile);
                                } catch (IOException ex) {
                                    logger.error("Error while deleting file.",ex);
                                }
                            }

                        }
                        filesProcessed.add(file);
                    }
                }
            };
            Timer timer = new Timer("Timer");

            long delay  = 0L;
            long period = 1000000L;
            timer.scheduleAtFixedRate(repeatedTask, delay, period);
    }

}
