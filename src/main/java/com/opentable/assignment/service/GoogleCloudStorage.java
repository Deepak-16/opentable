package com.opentable.assignment.service;

import com.google.api.gax.paging.Page;
import com.google.auth.Credentials;
import com.google.auth.oauth2.GoogleCredentials;
import com.google.auth.oauth2.ServiceAccountCredentials;
import com.google.cloud.ReadChannel;
import com.google.cloud.storage.*;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.io.*;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

@Service
public class GoogleCloudStorage {

    private static Storage storage = null;
    private static Credentials credentials = null;
    private static String projectId = "opentable";
    private static String path = "F:\\OpenTable-f4b9416be045.json";
    private static String bucketName = "opentable.appspot.com";
    private static Bucket bucket = null;

    @PostConstruct
    public void setDefaultStorageCredentials() {
        try {
            credentials = GoogleCredentials.fromStream(new FileInputStream(path));
            storage = StorageOptions.newBuilder().setCredentials(credentials)
                    .setProjectId(projectId).build().getService();
            bucket = storage.get(bucketName);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public Blob uploadFile(InputStream inputStream, String fileName, String folder) {
        try {
            return bucket.create(folder+fileName, inputStream);
        } catch (Exception e) {
            return null;
        }
    }

    public byte[] downloadFile(String filePath) throws IOException {
        return storage.get(bucketName).get(filePath).getContent();
    }

    public String getTemporaryFileLink(String filePath) throws Exception{
        Blob blob = storage.get(bucketName).get(filePath);
        String blobName = blob.getName();
        URL signedUrl = storage.signUrl(BlobInfo.newBuilder(bucketName, blobName).build(), 5, TimeUnit.MINUTES);
        return signedUrl.toExternalForm();
    }

    public boolean deleteFile(String filePath){
        return storage.delete(storage.get(bucketName).get(filePath).getBlobId());
    }

    public List<String> getList(String folder){
        List<String> files = new ArrayList<>();
        Page<Blob> blobs = bucket.list(Storage.BlobListOption.prefix(folder));
        for (Blob blob: blobs.getValues()) {
            files.add(blob.getName());
        }
        return files;
    }

}
