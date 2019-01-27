package com.opentable.assignment.util;

import org.springframework.stereotype.Service;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.IOException;

public class ImageModificationUtil {

    public static void resize(byte[] inputImageStream, String outputImagePath) throws IOException {
        BufferedImage inputImage = ImageIO.read(new ByteArrayInputStream(inputImageStream));
        Dimension imgSize = new Dimension(inputImage.getWidth(), inputImage.getHeight());
        Dimension boundary = new Dimension(640, 480);
        Dimension scaled = getScaledDimension(imgSize,boundary);
        resize(inputImage, outputImagePath, (int)scaled.getWidth(), (int)scaled.getHeight());
    }

    private static void resize(BufferedImage inputImage,
                              String outputImagePath, int scaledWidth, int scaledHeight)
            throws IOException {
        BufferedImage outputImage = new BufferedImage(scaledWidth, scaledHeight, inputImage.getType());

        Graphics2D g2d = outputImage.createGraphics();
        g2d.drawImage(inputImage, 0, 0, scaledWidth, scaledHeight, null);
        g2d.dispose();

        String formatName = outputImagePath.substring(outputImagePath.lastIndexOf(".") + 1);

        ImageIO.write(outputImage, formatName, new File(outputImagePath));
    }

    private static Dimension getScaledDimension(Dimension imgSize, Dimension boundary) {

        int original_width = imgSize.width;
        int original_height = imgSize.height;
        int bound_width = boundary.width;
        int bound_height = boundary.height;
        int new_width = original_width;
        int new_height = original_height;
        if (original_width > bound_width) {
            new_width = bound_width;
            new_height = (new_width * original_height) / original_width;
        }
        if (new_height > bound_height) {
            new_height = bound_height;
            new_width = (new_height * original_width) / original_height;
        }

        return new Dimension(new_width, new_height);
    }
}
