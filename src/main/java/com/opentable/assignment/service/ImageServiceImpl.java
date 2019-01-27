package com.opentable.assignment.service;

import com.opentable.assignment.util.Constants;
import com.opentable.assignment.util.ImageModificationUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.io.FileInputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;


@Service
public class ImageServiceImpl {

    Logger logger = LoggerFactory.getLogger(ImageServiceImpl.class);

    @Autowired
    GoogleCloudStorage googleCloudStorage;

    @Value("${image.resize.cron.interval}")
    private Long interval;

    Set<String> filesProcessed = new HashSet<>();

    @PostConstruct
    public void resizeCron(){
            TimerTask repeatedTask = new TimerTask() {
                public void run() {
                    List<String> list = googleCloudStorage.getList(Constants.Directory.ORIGINAL_IMAGE_DIR);
                    for (String file : list) {
                        Path tempFile = null;
                        String outputImagePath = null;
                        if (!filesProcessed.contains(file)) {
                            try {
                                byte[] image = googleCloudStorage.downloadFile(file);
                                String format = file.substring(file.lastIndexOf("."));
                                tempFile = Files.createTempFile("resized", format);
                                outputImagePath = tempFile.getFileName().toAbsolutePath().toString();
                                ImageModificationUtil.resize(image, outputImagePath);
                                googleCloudStorage.uploadFile(new FileInputStream(outputImagePath),
                                       file.substring(file.indexOf("/")+1), Constants.Directory.RESIZED_IMAGE_DIR);
                            }catch (Exception ex){
                                logger.error("Error while running resize cron. "+file,ex);
                            }finally {
                                try {
                                    Files.deleteIfExists(Paths.get(outputImagePath));
                                } catch (IOException ex) {
                                    logger.error("Error while deleting file. "+file,ex);
                                }
                            }

                        }
                        filesProcessed.add(file);
                    }
                }
            };
            Timer timer = new Timer("Timer");
            timer.scheduleAtFixedRate(repeatedTask, 0L, interval);
    }

}
