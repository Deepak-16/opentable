package com.opentable.assignment.service;

import javax.servlet.http.Part;
import java.io.IOException;

public interface ImageService {
    void upload(Part filePart) throws IOException;
}
